package com.example.demo.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
// http://localhost:8000/dept/crudDept 요청하면 DeptManager서블릿이 호출됨.
//서블릿이 되기 위한 조건이 뭔가요? HttpServlet상속
// 입력 - http://localhost:8000/dept/crudDept?gubun=insert
// 수정 - http://localhost:8000/dept/crudDept?gubun=update
// 삭제 - http://localhost:8000/dept/crudDept?gubun=delete
// 전체조회 - http://localhost:8000/dept/crudDept?gubun=select
@Log4j2
@WebServlet("/dept/crudDept")
public class DeptManager extends HttpServlet{@Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //-> insert | update | delete | select
    String gubun = req.getParameter("gubun");
    if("insert".equals(gubun)){
      log.info("입력");
      //입력을 누르면 오라클 서버에 insert를 하고 추가된 데이터 포함하는
      //전체 목록(select)을 새로고침 처리 한다.
      resp.sendRedirect("/dept/deptInsert.jsp");
    }
    else if("update".equals(gubun)){
      log.info("수정");
      //수정을 누르면 오라클 서버에 update를 하고 수정된 데이터 포함하는
      //전체 목록(select)을 새로고침 처리 한다.  
      resp.sendRedirect("/dept/deptUpdate.jsp");    
    }
    else if("delete".equals(gubun)){
      log.info("삭제");
      //삭제를 누르면 오라클 서버에 delete를 하고 삭제된 데이터가 포함되지 
      //않는 전체 목록(select)을 새로고침 처리 한다. 
      resp.sendRedirect("/dept/deptDelete.jsp"); 
    }
    else if("select".equals(gubun)){
      log.info("전체조회");
      List<Map<String,Object>> list = new ArrayList<>();
      Map<String,Object> map = new HashMap<>();
      map.put("deptno",10);
      map.put("dname","인사부");
      map.put("loc","서울");
      list.add(map);//list.size() =1
      map = new HashMap<>();
      map.put("deptno",20);
      map.put("dname","총무부");
      map.put("loc","인천");
      list.add(map);//list.size() =1
      map = new HashMap<>();
      map.put("deptno",30);
      map.put("dname","개발부");
      map.put("loc","제주");
      list.add(map);//list.size() =1
      req.setAttribute("list", list);
      //DeptManager.java 와 deptSelect.jsp 사이에 유지된다.
      //오라클 서버에서 부서테이블에 있는 모든 데이터를 출력한다.
      //resp.sendRedirect("/dept/deptSelect.jsp"); 
      RequestDispatcher view = req.getRequestDispatcher("/dept/deptSelect.jsp");
      view.forward(req, resp);

    }
  }//end of service
}//end of DeptManager
