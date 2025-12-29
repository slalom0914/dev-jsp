package com.pojo2;


import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
public interface Controller {
	/******************************************************************************************
	 * 
	 * @param req
	 * @param res
	 * @return "redirect:boardList.jsp", or  "forward:boardList.jsp" or  "redirect:boardList.pj2"
	 * @throws Exception
	 *****************************************************************************************/
	public abstract String execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;//추상메소드
}
