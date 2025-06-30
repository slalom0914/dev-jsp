package com.example.demo.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//응답페이지 처리 분리해 본다.
//응답페이지 내보내기 - sendRedirect(), forward(req,res)
public class ViewResolver {
  public ViewResolver(HttpServletRequest req
                    , HttpServletResponse res, String[] pageMove)
  throws ServletException, IOException                  
  {
    String path = pageMove[1];
    //redirect:XXX.jsp or foward:XXX.jsp
    if("redirect".equals(pageMove[0])){
      res.sendRedirect(path);
    }else if("forward".equals(pageMove[0])){
      RequestDispatcher view = req.getRequestDispatcher("/"+path+".jsp");
      view.forward(req, res);//서블릿의 원본 객체 - 요청이 유지되는 동안에 기억할께
    }else{
      path = pageMove[0]+"/"+ pageMove[1];
      RequestDispatcher view = req.getRequestDispatcher("/WEB-INF/views/"+path+".jsp");
      view.forward(req, res);      
    }                  
  }
}
