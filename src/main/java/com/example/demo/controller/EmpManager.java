package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.demo.dao.EmpDao;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/emp/crudEmp") //@Controller + RequestMapping
public class EmpManager extends HttpServlet{@Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("service호출");
    String sdeptno = req.getParameter("deptno");
    int deptno = Integer.parseInt(sdeptno);
    List<Map<String,Object>> list = new ArrayList<>();
    EmpDao empDao = new EmpDao();//생성자 DBConnectionMgr초기화
    list = empDao.empList(deptno);
    req.setAttribute("list", list);
    RequestDispatcher view = 
        req.getRequestDispatcher("./empList.jsp");
    view.forward(req, resp);//request는 저장소이다.
  }

}
