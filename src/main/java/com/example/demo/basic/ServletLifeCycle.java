package com.example.demo.basic;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
// @Controller + @RequestMapping
@WebServlet("/hello") //http://localhost:8000/hello
public class ServletLifeCycle extends HttpServlet{
  @Override
  public void init() throws ServletException {
    // 서블릿 초기화 - 서블릿 생성 또는 리로딩 때 단 한번만 수행
    log.info("ServletLifeCycle 초기화");
  }
  //URL요청이 있을 때 마다 반복적으로 수행됨
  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    log.info("ServletLifeCycle service");
    //1. 입력
    //2. 처리
    //3. 출력
    
    // 응답 설정
    resp.setContentType("text/html; charset=UTF-8");
    resp.getWriter().println("<html><body>");
    resp.getWriter().println("<h1>Hello from Servlet!</h1>");
    resp.getWriter().println("<p>서블릿이 정상적으로 작동하고 있습니다.</p>");
    resp.getWriter().println("</body></html>");
  }
  //뒷 정리 작업 - 서블릿이 제거될 때 단 한 번만 수행됨
  @Override
  public void destroy() {
    log.info("ServletLifeCycle destroy");
  }
}
