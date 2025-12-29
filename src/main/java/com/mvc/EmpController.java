package com.mvc;

import org.apache.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EmpController implements Controller{
	Logger log = Logger.getLogger(EmpController.class);
	public EmpController() {
		log.info("EmpController 디폴트생성자 호출");
	}
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res, String viewName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object deptInsert(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object deptUpdate(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object deptDelete(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object deptDetail(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object deptList(HttpServletRequest req, HttpServletResponse res){
		// TODO Auto-generated method stub
		return null;
	}
}
