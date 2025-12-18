package lab.ch01;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//PNG를 Base64로 변환하는 웹사이트
//https://products.aspose.app/pdf/ko/conversion/png-to-base64
@WebServlet("/api/upload")
public class Base64Servlet extends HttpServlet {
	Logger log = Logger.getLogger(Base64Servlet.class);
    /**
     * Postman에서 multipart/mixed로 보낸 데이터를 여기서 받는다.
     * 예) 텍스트 파트 + 이미지(Base64) 파트를 boundary로 구분해서 보냄
     */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost");
        // 1) Content-Type 확인 + boundary 추출
        String contentType = req.getContentType();
        log.info("Content-Type = " + contentType);
        // 만약 multipart/mixed가 아니면 우리가 기대한 형식이 아니므로 에러 처리
        if (contentType == null || !contentType.toLowerCase().startsWith("multipart/mixed")) {
            resp.setStatus(400);
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().println("Content-Type이 multipart/mixed가 아닙니다.");
            return;
        }
        // =========================================
        // 2) boundary 값 추출
        // =========================================
        // boundary는 "파트와 파트를 구분하는 경계선 이름"
        // 예: boundary=boundary
        String boundary = extractBoundary(contentType);
        if (boundary == null) {
            resp.setStatus(400);
            resp.setContentType("text/plain; charset=UTF-8");
            resp.getWriter().println("boundary를 찾지 못했습니다.");
            return;
        }

        // multipart 본문에서 실제 경계선은 "--" + boundary
        // 실제 본문에서 쓰는 경계선은 "--"가 붙는다.
        // 즉 boundary=boundary 라면 본문에는 --boundary 가 등장한다.
        String boundaryLine = "--" + boundary;      // 파트 시작 표시
        String endBoundaryLine = boundaryLine + "--"; // 전체 종료 표시

        log.info("boundary = " + boundary);
        log.info("boundaryLine = " + boundaryLine);
        log.info("endBoundaryLine = " + endBoundaryLine);

        // 2) body 전체를 문자열로 읽기(실습용)
        //    ※ 운영에서는 큰 파일이면 스트리밍 파싱해야 함.
        String body = readBodyAsString(req);
        log.info("=== RAW BODY START ===");
        log.info(body);
        log.info("=== RAW BODY END ===");

        // 3) boundary 기준으로 파트 분리
        // 결과: 텍스트 파트 1개, 이미지 파트 1개 ... 이런 식으로 List에 들어감
        List<String> rawParts = splitByBoundary(body, boundaryLine, endBoundaryLine);
        log.info("parts count = " + rawParts.size());

        String textPart = null;
        String base64Part = null;
        String imageContentType = null;
        // =========================================
        // 5) 각 파트를 하나씩 분석하기
        // =========================================
        for (int i = 0; i < rawParts.size(); i++) {
        	// 양쪽 공백/개행 제거(분석하기 편하게)
            String part = rawParts.get(i).trim();
            if (part.isEmpty()) continue;

            // -----------------------------------------
            // 5-1) 파트는 "헤더"와 "데이터"로 구성됨
            // 헤더와 데이터는 "빈 줄"로 구분됨
            // -----------------------------------------
            // 일반적으로 \r\n\r\n(윈도우 방식) 또는 \n\n(리눅스 방식) 둘 다 가능
            int headerEnd = part.indexOf("\r\n\r\n");
            int sepLen = 4;
            if (headerEnd < 0) {
                headerEnd = part.indexOf("\n\n");
                sepLen = 2;
            }
            // 빈 줄이 없으면 헤더/바디를 구분 못 하므로 경고 후 다음 파트로
            if (headerEnd < 0) {
                log.warn("파트에서 헤더/바디 구분(빈줄)을 찾지 못함: index=" + i);
                continue;
            }
            // 헤더 부분(예: Content-Type: text/plain 등)
            String headersBlock = part.substring(0, headerEnd);
            // 데이터 부분(예: "안녕하세요..." 또는 Base64 문자열)
            String dataBlock = part.substring(headerEnd + sepLen);
            // -----------------------------------------
            // 5-2) 헤더를 Map으로 파싱(키/값 형태로 저장)
            // -----------------------------------------
            Map<String, String> headers = parseHeaders(headersBlock);
            // 이번 파트의 Content-Type / 인코딩 방식 확인
            String partContentType = headers.getOrDefault("content-type", "");
            String transferEncoding = headers.getOrDefault("content-transfer-encoding", "");

            log.info("---- PART #" + i + " ----");
            log.info("Content-Type: " + partContentType);
            log.info("Content-Transfer-Encoding: " + transferEncoding);
            log.info("DATA(앞 100자): " + preview(dataBlock, 100));
            // -----------------------------------------
            // 5-3) 텍스트 파트 처리
            // -----------------------------------------
            // Content-Type이 text/plain이면 그냥 문자열로 저장
            // 5) 텍스트 파트 처리
            if (partContentType.toLowerCase().startsWith("text/plain")) {
                textPart = dataBlock.trim();
                log.info("[TEXT PART] = " + textPart);
            }

            // -----------------------------------------
            // 5-4) 이미지 파트 처리
            // -----------------------------------------
            // Content-Type이 image/png, image/jpeg 같은 경우
            // Base64 문자열은 줄바꿈이 섞여있을 수 있으므로 제거 후 저장
            if (partContentType.toLowerCase().startsWith("image/")) {
                imageContentType = partContentType;
                // Base64는 줄바꿈이 섞여 있으니 공백/개행 제거
                String b64 = dataBlock.replaceAll("\\s+", "");
                base64Part = b64;
                log.info("[IMAGE BASE64 LENGTH] = " + b64.length());
            }
        }

        // =========================================
        // 6) Base64 이미지가 있으면 디코딩해서 실제 파일로 저장(선택)
        // =========================================
        String savedPath = null;
        if (base64Part != null) {
            byte[] imageBytes;
            try {
            	// Base64 문자열 -> byte[] (실제 이미지 바이너리)
                imageBytes = Base64.getDecoder().decode(base64Part);
            } catch (IllegalArgumentException e) {
                resp.setStatus(400);
                resp.setContentType("text/plain; charset=UTF-8");
                resp.getWriter().println("Base64 디코딩 실패: " + e.getMessage());
                return;
            }

            // 서버 임시 폴더에 저장(예: .../tomcat/temp)
            File dir = new File(System.getProperty("java.io.tmpdir"), "upload-test");
            if (!dir.exists()) dir.mkdirs();

            String ext = guessExt(imageContentType); // image/png -> png
            File out = new File(dir, "postman_upload_" + System.currentTimeMillis() + "." + ext);

            try (FileOutputStream fos = new FileOutputStream(out)) {
                fos.write(imageBytes);
            }
            savedPath = out.getAbsolutePath();
            log.info("이미지 저장 완료: " + savedPath);
        }

        // =========================================
        // 7) 클라이언트(Postman)에게 결과 JSON 응답
        // =========================================
        resp.setStatus(200);
        resp.setContentType("application/json; charset=UTF-8");
        // textPart와 savedPath를 응답으로 보내서 "서버가 제대로 받았는지" 확인 가능
        resp.getWriter().println("{");
        resp.getWriter().println("  \"ok\": true,");
        resp.getWriter().println("  \"boundary\": \"" + escapeJson(boundary) + "\",");
        resp.getWriter().println("  \"text\": \"" + escapeJson(textPart == null ? "" : textPart) + "\",");
        resp.getWriter().println("  \"imageSavedPath\": \"" + escapeJson(savedPath == null ? "" : savedPath) + "\"");
        resp.getWriter().println("}");		
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}
    // ---------------------------------------------------------------------
    // Helper 메서드들(핵심 로직은 아니지만, parsing을 쉽게 만드는 도구들)
    // ---------------------------------------------------------------------
    /**
     * Content-Type 헤더에서 boundary 값을 추출
     * 예) multipart/mixed; boundary=boundary
     * 예) multipart/mixed; boundary="boundary"
     */
    private static String extractBoundary(String contentType) {
        // 예: multipart/mixed; boundary=boundary
        //     multipart/mixed; boundary="boundary"
        String[] tokens = contentType.split(";");
        for (String t : tokens) {
            String s = t.trim();
            if (s.toLowerCase().startsWith("boundary=")) {
                String b = s.substring("boundary=".length()).trim();
                if (b.startsWith("\"") && b.endsWith("\"") && b.length() >= 2) {
                    b = b.substring(1, b.length() - 1);
                }
                return b;
            }
        }
        return null;
    }
    /**
     * 요청 Body를 InputStream으로 읽어서 UTF-8 문자열로 변환(실습용)
     * - multipart/mixed의 원본 구조를 그대로 확인하기 위해 사용
     */
    private static String readBodyAsString(HttpServletRequest req) throws IOException {
        // 실습용: 그대로 읽기
        try (InputStream is = req.getInputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[4096];
            int n;
            while ((n = is.read(buf)) != -1) {
                baos.write(buf, 0, n);
            }
            // Postman이 \n만 보낼 수도 있어 UTF-8로 디코딩
            return baos.toString(StandardCharsets.UTF_8);
        }
    }
    /**
     * boundaryLine 기준으로 body를 잘라서 각 파트를 List로 반환
     * - 마지막 endBoundaryLine 이후는 버린다.
     */
    private static List<String> splitByBoundary(String body, String boundaryLine, String endBoundaryLine) {
        // body 안에는 \r\n 또는 \n 혼재 가능 -> 경계선을 기준으로 단순 분리
        // 시작 경계선/종료 경계선 제거하고 파트 덩어리만 리턴
        List<String> parts = new ArrayList<>();

        // 일단 종료 경계선까지 자르기
        int endIdx = body.indexOf(endBoundaryLine);
        String main = (endIdx >= 0) ? body.substring(0, endIdx) : body;

        // boundaryLine로 split
        String[] arr = main.split(Pattern.quote(boundaryLine));
        for (String s : arr) {
            String trimmed = s.trim();
            if (!trimmed.isEmpty()) {
                parts.add(trimmed);
            }
        }
        return parts;
    }
    /**
     * 파트 헤더 블록을 Map 형태로 변환
     * 예) "Content-Type: text/plain" -> key="content-type", value="text/plain"
     */
    private static Map<String, String> parseHeaders(String headersBlock) {
        Map<String, String> map = new HashMap<>();
        String[] lines = headersBlock.split("\\r?\\n");
        for (String line : lines) {
            int idx = line.indexOf(':');
            if (idx > 0) {
                String k = line.substring(0, idx).trim().toLowerCase();
                String v = line.substring(idx + 1).trim();
                map.put(k, v);
            }
        }
        return map;
    }
    /**
     * 로그에서 데이터가 너무 길면 보기 힘드니,
     * 앞쪽 일부만 잘라서 보여주는 함수
     */
    private static String preview(String s, int max) {
        String t = s.trim();
        if (t.length() <= max) return t;
        return t.substring(0, max) + "...";
    }
    /**
     * image/png 같은 Content-Type을 보고 파일 확장자를 결정
     */
    private static String guessExt(String contentType) {
        if (contentType == null) return "bin";
        String ct = contentType.toLowerCase();
        if (ct.contains("image/png")) return "png";
        if (ct.contains("image/jpeg") || ct.contains("image/jpg")) return "jpg";
        if (ct.contains("image/gif")) return "gif";
        return "bin";
    }
    /**
     * JSON 문자열에 들어가면 깨지는 문자들(따옴표, 줄바꿈 등)을 안전하게 변환
     * - 응답 JSON이 망가지지 않게 하는 방어 코드
     */
    private static String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\r", "\\r")
                .replace("\n", "\\n");
    }
}
