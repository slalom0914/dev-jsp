package com.example.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;//톰캣엔진제공함
import jakarta.servlet.http.HttpServlet;//톰캣엔진제공함
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@WebServlet("/dept/deptView")//http://localhost:8000/dept/deptView
public class DeptView extends HttpServlet{@Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=utf-8");
    PrintWriter out = resp.getWriter();
    String msg = "Hello World";
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>"+msg+"</h1>");
    out.println("</body>");
    out.println("</html>");
  }
  
}
