package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.example.demo.dao.ZipCodeDao;
import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
// 하나의 요청에서 어떤 경우는 jsp페이지로 나가고
// 또 다른 경우는 json포맷으로 내보낸다.
// http://localhost:8000/common/zipcodeSearch?type=jsp
// http://localhost:8000/common/zipcodeSearch?type=json
@WebServlet("/common/zipcodeSearch")//@Controller + @RequestMapping
public class ZipCodeMgr extends HttpServlet{

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //사용자가 선택한 요청에 따라 jsp로 나가거나 json형식으로 내보낸다.
    String type = req.getParameter("type");//jsp or json
    //사용자가 입력한 동 이름을 받아온다.
    // http://localhost:8000/common/zipcodeSearch?dong=가산
    String dong = req.getParameter("dong");
    List<Map<String, Object>> zlist = null;
    ZipCodeDao zDao = new ZipCodeDao();
    zlist = zDao.zipcodeList(dong);
    if("jsp".equals(type)){//jsp일 때
      req.setAttribute("zlist", zlist);
      RequestDispatcher view = req.getRequestDispatcher("./zipcodeList.jsp");
      view.forward(req, resp);
    }else if("json".equals(type)){//json일 때
      resp.setContentType("application/json;charset=utf-8");
      PrintWriter out = resp.getWriter();
      Gson g = new Gson();
      String temp = g.toJson(zlist);
      out.print(temp);
    }

  }//end of service
  
}//end of ZipCodeMgr
