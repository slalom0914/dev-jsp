package com.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
//이 클래스는 select인 경우에만 사용한다.
public class ModelAndView {
	Logger log = Logger.getLogger(ModelAndView.class);
	private String viewName = null;
	HttpServletRequest req = null;
	List<Map<String,Object>> reqList = new ArrayList<>();
	public ModelAndView() {}
	public ModelAndView(HttpServletRequest req) {
		this.req = req;
	}
	public void addObject(String name, Object obj) {
		Map<String,Object> map = new HashMap<>();
		map.put(name, obj);
		reqList.add(map);
		req.setAttribute(name, obj);
	}
	public void setViewName(String viewName) {
		this.viewName = viewName;
	}
	public String getViewName() {
		return viewName;
	}
}
