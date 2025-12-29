package com.mvc;
import java.io.IOException;

import org.apache.log4j.Logger;

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
		String[] path = command.split("/");
		for(String s:path) {
			log.info(s);
		}
	}

}
