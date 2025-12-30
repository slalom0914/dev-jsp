package com.pojo1;

import java.io.IOException;
import org.apache.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontMVC extends HttpServlet {
	Logger logger = Logger.getLogger(FrontMVC.class);
	//사용자 정의 메소드
	//아래 메소드를 호출하는 URL패턴은 무엇입니까?
	//http://localhost:9000/notice/noticeList.pj1
	//http://localhost:9000/XXX.pj1
	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		logger.info("doService");
		//응답 화면에 대한 결정과 redirect로 할지 forward로 할지는 NoticeController 가 결정할 수 있다.
		ActionForward af = null;
		String uri = req.getRequestURI();//=> /notice/noticeList.pj1
		logger.info(uri);
		//context정보는 server.xml에서 확인 가능함
		String context = req.getContextPath();// => /
		String command = uri.substring(context.length()+1);
		int end = command.lastIndexOf(".");//.pj1이 있는 위치 정보를 가져옴
		command = command.substring(0,end);//.pj1이 잘려나간 문자열만 남음 -> /notice/noticeList
		String upmu[] = null;//upmu[0]=notice, upmu[1]=noticeList-> 페이지이름과 메소드이름통일함
		//슬래시를 기준으로 문자열을 썰어서 배열에 순서대로 담아줌
		upmu = command.split("/");
		//테스트하기 - http://localhost:9000/notice/noticeList.pj1엔터
		for(String str:upmu) {//개선된 for문. 전체 출력할 땐
			//logger.info(str);2번 출력됨
		}
		//여기까지 pj1으로 요청이 들어왔을 때 web.xml을 통해서 FrontMVC클래스의 doService메소드 호출됨을
		//확인하였다. 그 다음은 NoticeController의 execute 메소드 호출하기이다
		NoticeController noticeController = new NoticeController();
		if("notice".equals(upmu[0])) {//split 배열 담음-첫번째방 -work
			req.setAttribute("upmu", upmu);//배열의 주소번지
			af = noticeController.execute(req, resp);
		}
		//꼭 필요한 부분이다 - 페이지를 출력하는데 
		/////////////////// [[ spring에서는 ViewResolver클래스가 지원하는 부분임]] ////////////////////
		if(af !=null) {
			if(af.isRedirect()) {
				resp.sendRedirect(af.getPath());
				return;
			}//end of redirect - insert, update, delete
			else {// forward - 유지, 주소안변함. 그런데 페이지는 바뀌었다
				RequestDispatcher view = req.getRequestDispatcher(af.getPath());//상수가 아니라 변수 변수아니라 메소드 처리하는 코드- 세련됨
				view.forward(req, resp);
			}//end of forward - select
		}//end of if - ActionForward결과로 후처리하는 코드 끝나는 부분이었습니다........
		//힌트 - 업무폴더이름으로 가능한가? 아니면 페이지 이름으로 하는게 좋은가?
		/////////////////// [[ ]] ////////////////////
		
	}
	//서블릿에서 정의된 메소드를 재정의하는 것
	//요청을 받아주는 메서드 이름은 중요하지 않다.
	//그러나 파라미터 자리에 request, response 객체는 톰캣으로 부터 주입을 받음
	//두 가지 객체는 원본을 사용함.
	//개발자 입장에서 보면 GET, POST, PUT, DELETE 상관없이 처리해 주어야 한다.
	//요청에 대한 처리 창구를 일원화 할 필요가 있다.
	// request 할 수 있는 것 - 사용자가 입력한 값을 청취
	// response 할 수 있는 것 - UI를 통해서 입력 받은 값 -> 처리 -> 응답(html, json, xml, text)
	// react -> useEffect -> 의존성배열, 상태값 반영(useState)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(req, resp);
	}
	//서블릿에서 정의된 메소드를 재정의하는 것
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doService(req, resp);
	}
	
}
