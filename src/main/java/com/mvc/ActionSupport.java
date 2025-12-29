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
	// -> http://localhost:8000/member/crudMember.ks
	// 입력일 때 -> 메서드 이름 -> memberInsert(req, res):int
	// 수정일 때 -> 메서드 이름 -> memberUpdate(req, res):int
	// 삭제일 때 -> 메서드 이름 -> memberDelete(req, res):int
	// 단건 조회일 때 -> memberDetail(req,res):Map or DeptVO
	// n건 조회일 때 -> memberList(req,res):List<Map>	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.info("ActionSupport service");
		String requestURI = req.getRequestURI();
		log.info(requestURI);
		String contextPath = req.getContextPath();//-> / or /dev-jsp
		log.info(contextPath);// -> / -> server.xml
	}

}
