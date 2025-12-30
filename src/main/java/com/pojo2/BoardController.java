package com.pojo2;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.util.HashMapBinder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BoardController implements Controller {
	Logger logger = Logger.getLogger(BoardController.class);
	private BoardLogic boardLogic = new BoardLogic();
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		logger.info("execute");
		String upmu[] = (String[])req.getAttribute("upmu");
		String page = null;
		int result = 0;//입력이 수정이 삭제가 성공하면 1을 반환하고 실패하면 0을 반환한다
		//인터페이스는 반드시 구현체 클래스가 필요함
		Map<String,Object> pMap = new HashMap<>();
		//req.getParameter의 반복을 방어하기 위해서 만듦
		//서블릿에서 제공되는 req를 사용할것
		//요청객체와 응답객체는 톰캣 서버로 부터 요청이 일어나면 그 때 주입을 받음
		HashMapBinder hmb = new HashMapBinder(req);
		//너 전체 조회를 원해? - forward
		//-> /board/boardList.pj2
		if("boardList".equals(upmu[1])) {
			//board/boardList.jsp
			logger.info("boardList");//콘솔에 문자열 출력이 안되었다면 오라클서버를 경유하지 않았다
			List<Map<String,Object>> bList = null;
			//파라미터로 들어오는 값이 담김 - pMap
			//XXX.pj2?b_no=5&b_title=제목
			//pMap.put("b_no",5);
			//pMap.put("b_title","입력받은 제목...");
			hmb.bind(pMap);
			bList = boardLogic.boardList(pMap);
			//요청이 유지되는 동안에는 사용할 수 있다. - 조회결과를 가진 주소번지
			//첫번째 파라미터는 문자열값이고
			//두번째 파라미터는 주소번지다
			req.setAttribute("bList",bList);
			page = "forward:board/boardList";//-> pageMove[0]=forward, pageMove[1]=boardList.jsp
		}
		else if("jsonBoardList".equals(upmu[1])) {// Front-End, Vue.js, React.js, jEasyUI
			//board/boardList.jsp
			logger.info("boardList");//콘솔에 문자열 출력이 안되었다면 오라클서버를 경유하지 않았다
			List<Map<String,Object>> bList = null;
			hmb.bind(pMap);
			bList = boardLogic.boardList(pMap);
			req.setAttribute("bList",bList);
			page = "forward:jsonBoardList";//-> pageMove[0]=forward, pageMove[1]=boardList.jsp
		}
		//상세내용 보고싶다 - forward
		else if("boardDetail".equals(upmu[1])) {
			logger.info("boardDetail");
			List<Map<String,Object>> bList = null;
			hmb.bind(pMap);
			bList = boardLogic.boardList(pMap);//pMap.get("b_no") => 1
			req.setAttribute("bList",bList);
			page = "forward:boardDetail.jsp";
		}
		//글 등록할거야? - redirect
		else if("boardInsert".equals(upmu[1])) {
			logger.info("boardInsert");
			//사용자가 입력한 값 만큼 req.getParameter가 반복되므로 이 코드를 줄여줌
			hmb.bind(pMap);//HashMapBinder의 bind메소드 호출함- 사용자가 입력한 값을 담아줄 주소번지 넘겨줌
			//javascript에서 제공하는 API 를 사용해서 JSON다루게 됩니다.
			logger.info(pMap);//한글처리된 값 출력해 보기 {키=값, 키2=값2, } -> JSON형식으로 변경처리
			//result변수는 어떤 역할이지? - 오라클 서버에 insert문 요청하면 오라클 서버가 리턴해주는 값을 담아요
			result = 0;
			result = boardLogic.boardInsert(pMap);
			if(result==1) {//입력 성공한 경우
				page = "redirect:boardList.pj2";//pj2가 붙는 이유는 오라클경유하니까
			}
			//실패했을 때
			else {//result =0이면  이쪽으로 오게됨 - 흐름이 바뀐다(if문,  switch문)
				page = "redirect:boardError.jsp";
			}
		}
		//수정하고 싶어요 - POST방식
		else if("boardUpdate".equals(upmu[1])) {
			logger.info("boardUpdate");
			hmb.bind(pMap);//사용자가 입력한 값을 담아줌
			logger.info(pMap);//확인하는 곳
			result = 0;
			result = boardLogic.boardUpdate(pMap);
			if(result==1) {//입력 성공한 경우
				page = "redirect:boardList.pj2";//pj2가 붙는 이유는 오라클경유하니까
			}
			//실패했을 때
			else {
				page = "redirect:boardError.jsp";
			}
		}
		//이젠 삭제할게요
		else if("boardDelete".equals(upmu[1])) {
			logger.info("boardDelete");
			hmb.bind(pMap);
			logger.info(pMap);
			result = 0;
			//insert here - 비교하지 전에  미리 해야 한다.
			result = boardLogic.boardDelete(pMap);//pMap.get("b_no");
			if(result==1) {//입력 성공한 경우
				page = "redirect:boardList.pj2";//pj2가 붙는 이유는 오라클경유하니까
			}
			//여기는 왜 안되지?
			//실패했을 때
			else {
				page = "redirect:boardError.jsp";
			}
		}
		return page;
	}

}