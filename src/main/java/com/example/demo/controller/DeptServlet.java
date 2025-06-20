package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.example.demo.dao.DeptDao;
import com.example.demo.vo.DeptVO;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/dept/jsonDeptList")
public class DeptServlet extends HttpServlet{@Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("doGet호출 성공");
    DeptDao deptDao = new DeptDao();
    List<DeptVO> list = deptDao.deptList();
    Gson gson = new Gson();
    String temp = gson.toJson(list);
    //log.info(temp);
    resp.setContentType("application/json;charset=utf-8");
    PrintWriter out = resp.getWriter();
    out.println(temp);
    //out.flush();
    out.close();
  }
  
}
