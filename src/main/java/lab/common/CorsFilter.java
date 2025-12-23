package lab.common;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
/*
────────────────────────────────────────────────────────────────────────────
✅ CorsFilter (CORS 문제 해결용 서블릿 필터)

1) 왜 필요한가?
- 프론트(React/Vite)와 백엔드(서블릿/스프링)가 "서로 다른 출처(Origin)"일 때
  브라우저가 보안상 요청을 막는 문제(CORS)가 자주 발생한다.
  예) 프론트: http://localhost:5173  →  백엔드: http://localhost:8080
      (포트가 다르면 다른 Origin이라서 브라우저가 차단할 수 있음)

2) 이 필터가 하는 일
- 응답(Response)에 "Access-Control-Allow-*" 헤더를 붙여서
  "이 Origin에서 온 요청은 허용해도 된다"는 신호를 브라우저에 준다.

3) 중요한 포인트
- 운영(배포) 환경에서는 "*" 허용은 위험 → 반드시 특정 Origin만 허용 권장
- 브라우저는 실제 요청 전에 OPTIONS(Preflight)로 "사전 확인"을 보낼 수 있는데
  이 OPTIONS 요청을 200으로 바로 끝내주지 않으면 CORS 에러가 계속 난다.
────────────────────────────────────────────────────────────────────────────
*/
public class CorsFilter implements Filter {
	// 필터가 처음 생성될 때 1번만 호출됨 (초기화 작업이 필요하면 여기서)
    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }
    /*
     * doFilter는 "요청이 들어올 때마다" 실행된다.
     * 즉, 모든 요청의 입구에서 먼저 통과하는 관문 역할이다.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // ServletRequest/Response는 공통 타입이라서
        // HTTP 헤더/메서드 등을 쓰려면 HttpServletRequest/Response로 캐스팅 필요
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // ─────────────────────────────────────────────────────────────
        // ✅ 1) 어떤 Origin(출처)에서 온 요청인지 확인
        // - 브라우저가 요청 헤더에 Origin을 넣어서 보낸다.
        // - 예: "http://localhost:5173"
        // ─────────────────────────────────────────────────────────────
        String origin = req.getHeader("Origin");
        // ─────────────────────────────────────────────────────────────
        // ✅ 2) 허용할 Origin만 "화이트리스트"로 지정 (운영에서 특히 중요)
        // - 아래 3개 Origin에서 온 요청만 허용한다.
        // - origin이 null인 경우는:
        //   * 같은 출처 요청이거나
        //   * Postman 같은 도구에서 요청하거나
        //   * 일부 상황에서 Origin 헤더가 없을 수도 있다.
        // ─────────────────────────────────────────────────────────────        
        if (origin != null && (
                origin.equals("http://localhost:5500") ||
                origin.equals("http://localhost:5173") ||
                origin.equals("http://localhost:3000")
        )) {
        	// 이 Origin은 허용한다(브라우저에게 OK 신호)
            res.setHeader("Access-Control-Allow-Origin", origin);
            // 캐시가 Origin에 따라 다르게 동작해야 하므로 Vary를 설정
            // (안 넣으면 다른 Origin 요청에도 잘못 캐싱된 헤더가 적용될 수 있음)            
            res.setHeader("Vary", "Origin"); // 캐시 이슈 방지
            // 쿠키/세션(인증정보)을 포함한 요청을 허용할지 여부
            // - true면 프론트 fetch/axios도 credentials 옵션을 맞춰야 함
            //   fetch: credentials: "include"
            //   axios: withCredentials: true            
            res.setHeader("Access-Control-Allow-Credentials", "true");
        }

        // ─────────────────────────────────────────────────────────────
        // ✅ 3) 허용할 HTTP 메서드 지정
        // - 브라우저가 "이 메서드로 요청해도 되나요?"를 Preflight에서 묻는다.
        // ─────────────────────────────────────────────────────────────
        res.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,DELETE,OPTIONS");
        // ─────────────────────────────────────────────────────────────
        // ✅ 4) 허용할 요청 헤더 지정
        // - Content-Type(예: application/json) 같은 헤더를 보내면
        //   브라우저가 Preflight에서 허용 여부를 확인한다.
        // Preflight는 브라우저가 이 요청을 보내도 되나요? 하고 서버에 미리 물어보는 확인 요청임.
        // - Authorization은 JWT 토큰 같은 인증 헤더를 쓸 때 필요
        // ─────────────────────────────────────────────────────────────        
        res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, X-Requested-With");
        // ─────────────────────────────────────────────────────────────
        // ✅ 5) Preflight 결과를 브라우저가 얼마 동안 캐싱할지(초 단위)
        // - 3600초(1시간) 동안은 같은 조건의 Preflight를 다시 안 보낼 수 있음
        // ─────────────────────────────────────────────────────────────        
        res.setHeader("Access-Control-Max-Age", "3600");

        // ─────────────────────────────────────────────────────────────
        // ✅ 6) Preflight(OPTIONS) 요청 처리
        // - 브라우저가 본 요청 전에 OPTIONS로 "사전 허용 확인"을 보낼 수 있다.
        // - 이때는 본 요청이 아니므로, 여기서 200으로 바로 끝내야 한다.
        // - chain.doFilter로 넘기면 서블릿이 OPTIONS를 처리 못 해서 에러가 날 수 있음
        // ─────────────────────────────────────────────────────────────
        if ("OPTIONS".equalsIgnoreCase(req.getMethod())) {
            res.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        // ─────────────────────────────────────────────────────────────
        // ✅ 7) 실제 요청(GET/POST/...)은 다음 단계로 넘긴다.
        // - 다음 필터가 있으면 다음 필터로, 없으면 최종 서블릿/컨트롤러로 이동
        // ─────────────────────────────────────────────────────────────
        chain.doFilter(request, response);
    }
    // 서버 종료/필터 제거 시 정리 작업이 필요하면 여기에 작성
    @Override
    public void destroy() { }
}
