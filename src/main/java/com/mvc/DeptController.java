package com.mvc;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.*;

public class DeptController implements DeptControllerInterface{
	Logger log = Logger.getLogger(DeptController.class);
	public DeptController() {
		log.info("DeptController 디폴트생성자 호출");
	}
	@Override
	public Object deptInsert(HttpServletRequest req, HttpServletResponse res){
		log.info("deptInsert");
		int result = -1;
		return "redirect:./deptCRUDResult.jsp?gubun=insert";
	}
	@Override
	public Object deptUpdate(HttpServletRequest req, HttpServletResponse res){
		log.info("deptUpdate");
		int result = -1;
		return "redirect:./deptCRUDResult.jsp?gubun=update";
	}
	@Override
	public Object deptDelete(HttpServletRequest req, HttpServletResponse res){
		log.info("deptDelete");
		int result = -1;
		return "redirect:./deptCRUDResult.jsp?gubun=delete";
	}
	@Override
	public Object deptDetail(HttpServletRequest req, HttpServletResponse res){
		log.info("deptDetail");
		return null;
	}

	
	@Override
	public Object deptList(HttpServletRequest req, HttpServletResponse res){
		log.info("deptList");
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("deptno",10);
		map.put("dname","총무부");
		map.put("loc","서울");
		list.add(map);
		map = new HashMap<>();
		map.put("deptno",20);
		map.put("dname","개발부");
		map.put("loc","부산");
		list.add(map);
		map = new HashMap<>();
		map.put("deptno",30);
		map.put("dname","운영부");
		map.put("loc","제주");
		list.add(map);
		ModelAndView mav = new ModelAndView(req);
		mav.addObject("list", list);
		mav.setViewName("dept/deptList");
		return mav;
	}

	@Override
	public Object jsonDeptList(HttpServletRequest req, HttpServletResponse res) {
		log.info("jsonDeptList");
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("deptno",10);
		map.put("dname","총무부");
		map.put("loc","서울");
		list.add(map);
		map = new HashMap<>();
		map.put("deptno",20);
		map.put("dname","개발부");
		map.put("loc","부산");
		list.add(map);
		map = new HashMap<>();
		map.put("deptno",30);
		map.put("dname","운영부");
		map.put("loc","제주");
		list.add(map);
		Gson g = new Gson();
		String temp = g.toJson(list);
		//그러나 아래서는 :이 들어간다. 왜냐면 JSON 포맷으로 변경되었으니까....
		log.info(temp);
		return temp;		
	}	
	
}
