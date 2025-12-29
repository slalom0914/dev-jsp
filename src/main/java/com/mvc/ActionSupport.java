package com.mvc;
import java.io.IOException;

import org.apache.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
// -> *.ks 확장자가 ks로 끝나는 모든 요청에 대해서는 내가 가로챈다???
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ActionSupport extends HttpServlet {
	Logger log = Logger.getLogger(ActionSupport.class);
	// -> http://localhost:8000/dept/crudDept.ks
	// 도메인 뒤에 폴더 이름을 업무 명으로 한다.
	// 슬래쉬에 뒤에 오는 이름을 업무(입력/수정/삭제/조회)에 대한 메서드 이름으로 정한다.
	// 입력, 수정, 삭제는 리턴 타입이 int이다.
	// 조회 경우 : 1)n건일 때(List<Map>,List<XXXVO>)
	//, 2)1건일 때(Map, XXXVO)
	// 입력일 때 -> 메서드 이름 -> deptInsert(req, res):int
	// 수정일 때 -> 메서드 이름 -> deptUpdate(req, res):int
	// 삭제일 때 -> 메서드 이름 -> deptDelete(req, res):int
	// 단건 조회일 때 -> deptDetail(req,res):Map or DeptVO
	// n건 조회일 때 -> deptList(req,res):List<Map>
	// -> http://localhost:8000/emp/crudEmp.ks
	// 입력일 때 -> 메서드 이름 -> empInsert(req, res):int
	// 수정일 때 -> 메서드 이름 -> empUpdate(req, res):int
	// 삭제일 때 -> 메서드 이름 -> empDelete(req, res):int
	// 단건 조회일 때 -> empDetail(req,res):Map or DeptVO
	// n건 조회일 때 -> empList(req,res):List<Map>
	// -> http://localhost:8000/member/memberInsert.ks
	// 입력일 때 -> 메서드 이름 -> memberInsert(req, res):int
	//http://localhost:8000/member/memberUpdate.ks
	// 수정일 때 -> 메서드 이름 -> memberUpdate(req, res):int
	//http://localhost:8000/member/memberDelete.ks
	// 삭제일 때 -> 메서드 이름 -> memberDelete(req, res):int
	// 단건 조회일 때 -> memberDetail(req,res):Map or DeptVO
	// n건 조회일 때 -> memberList(req,res):List<Map>	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.info("ActionSupport service");
		String requestURI = req.getRequestURI();
		log.info(requestURI);//-> /dept/deptInsert.ks
		String contextPath = req.getContextPath();//-> / or /dev-jsp
		log.info(contextPath);// -> / -> server.xml
		//URL요청을 활용해서 업무이름과 메서드 이름을 알아낸다.
		//dept앞에 슬래쉬를 제거한다.-> 슬래쉬를 기준으로 문자열 잘라서 배열에 담기
		String command = requestURI.substring(contextPath.length()+1);
		int end = command.lastIndexOf(".");
		//-> dept/deptInsert.ks
		log.info("end : "+end);//15
		command = command.substring(0, end);
		log.info(command);//-> dept/deptInsert
		//요청 받은 내용을 해당 업무에 대응되는 XXXController전달하고
		//응답처리를 위한 후처리를 해야 한다.
		Object obj = null;//ModelAndView 또는 String
		//viewName -> return "redirect:./deptInsertOk.jsp"
		//viewName -> return "forward:./deptInsertOk.jsp"
		//viewName -> return "./deptInsertOk.jsp"
		//viewName -> return new ModelAndView()
		//insert here - HandlerMapping연결하기
		obj = HandlerMapping.getController(command);
		String[] pageMove = null;
		if(obj != null) {
			ModelAndView mav = null;
			//Object안에는 두 가지 타입이 있다.String(Insert,Update,Delete)
			//, ModelAndView(select)
			if(obj instanceof String) {
				log.info("리턴타입이 String 일 때");
				if(((String)obj).contains(":")) {//redirect이거나 forward
					log.info("내 안에 콜론(:)이 있다.");
					pageMove = obj.toString().split(":");
				}else if(((String)obj).contains("/")) {
					log.info("내 안에 슬래쉬(/)가 있다.");
					pageMove = obj.toString().split("/");
				}else {
					log.info("내 안에 슬래쉬(/)도 없고 콜론도 없다.");
				}
			}//컨트롤러 요청메서드의 리턴타입이 String인 경우 끝
			else if(obj instanceof ModelAndView) {
				log.info("리턴타입이 ModelAndView 일 때");
				
				mav = (ModelAndView)obj;
				pageMove = new String[2];
				pageMove[0] = "modelAndView";
				pageMove[1] = mav.getViewName();
				
			}//컨트롤러 요청메서드의 리턴타입이 ModelAndView인 경우 끝
		}//end of obj가 널이 아닐 때 - NullPointerException예방하는 코드
		//////////////////////////////////////////////////////
		/////////////////[[ ViewResolver ]]//////////////
		if(pageMove !=null && pageMove.length == 2) {
			log.info("pageMove배열의 원소의 갯수가 2개 일 때");
			String path = pageMove[1];//각 컨트롤러 클래스의 메서드가 정한 페이지이름
			if("redirect".equals(pageMove[0])){
				res.sendRedirect(path);
			}else if("forward".equals(pageMove[1])) {
				RequestDispatcher view = 
						req.getRequestDispatcher("/"+path+".jsp");
				view.forward(req, res);
			}
			//리턴타입이 String인데 redirect: 이거나 forward: 이 없는 경우
			else {
				RequestDispatcher view = 
						req.getRequestDispatcher("/WEB-INF/views/"+path+".jsp");
				view.forward(req, res);				
			}
			//스프링 부트에서는 요청에 대한 응답 URL을 완성해주는 ViewResolver클래스가 제공됨
			/////////////////// pageMove의 원소의 갯수가 2개 일때 끝
		}
		
	}//end of service

}
