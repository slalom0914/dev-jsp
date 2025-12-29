package com.mvc;

import org.apache.log4j.Logger;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeptController implements Controller{
	Logger log = Logger.getLogger(DeptController.class);

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		log.info("execute():ModelAndView");
		return null;
	}

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res, String viewName) throws Exception {
		log.info("execute():String");
		return null;
	}
}
