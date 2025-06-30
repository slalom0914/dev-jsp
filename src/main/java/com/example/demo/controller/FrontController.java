package com.example.demo.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

//@WebServlet("*.do")
//http://localhost:8000/api/hello
//http://localhost:8000/api/member
//http://localhost:8000/api/login.do
//브라우저에서 오는 요청에 api가 들어 있으면 내가 인터셉트 할께
//실제 일을 하는 컨트롤러 클래스 앞에 별도의 FrontController 추가함.
//api가 들어있는 요청은 리액트 서버에서 들어오는 요청이다.
//-> /api/workname/methodName 요청은 모두 내가 가로 챌께
//이 요청이 회원관리인가, 게시판구현인가 는 workname구분함.
//ControllerMaping 경유해서 실제 처리할 클래스가 결정됨.
@Log4j2
@WebServlet("/api/*")
public class FrontController extends HttpServlet{

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //내가 프레임워크 직접 만들어 쓴다.
    // /api/common/zipcodeSearch
    String contextPath = req.getContextPath();//-> /dev_jsp
    String requestURI = req.getRequestURI();
    System.out.println("requestURI:"+requestURI);
    // 맨앞에 / 제외시킨다.
    String command = requestURI.substring(contextPath.length()+1);
    System.out.println("command:"+command);
    String[] pagePath = null;//api, common(업무이름:공통), zipcodeSearch(메서드)
    if(command !=null){
      pagePath = command.split("/");
    }
    Controller controller = null;
    try {
      //controller = ControllerMapping.getController(pagePath);
      //pagePath[1] == workname - 업무이름 - dept, emp, board,,,
      //두번째 원소를 가지고 구현체 클래스의 이름을 결정하였다. - 다형성
      //doGet(req,res), doPost(req,res), doPut(req,res), doDelete(req,res)
      //execute(req,res)
      //doGet의 req와 execute의 req는 같은 객체 참조 - only one - reference by value
      //NullPointerException
      //Controller controller(I) = new DeptController, EmpController, BoardController
      //pagePath[2] == methodName
      controller = ControllerMapping.getController(pagePath[1]+"/"+pagePath[2]);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    if(controller !=null){//ControllerMapping경유했어야 한다.
      //매칭이 되어 있지 않으면 null
      //응답 페이지 처리에 대한 추가 코드 필요함.
      //하나는 응답페이지가 JSP나가는 경우
      //두번째는 응답페이지가 리액트 서버(3000)에서 나가야 하므로
      //백엔드는 json형식으로 반환을 한다.
      //doGet():void, doPost():void, doPut():void, doDelete():void
      //그런데 나는 execute메서드의 리턴타입을 String으로 변경함.
      //왜냐면 어떤 경우는 jsp응답이 나가야 하고 어떤 경우는 리액트에서
      //응답이 나가도록 설계 할거야
      String[] pageMove = null;//return "forward:XXX.jsp" or "redirect:XXX.jsp"
      try {
        String result = controller.execute(req, resp);
        pageMove = result.split(":");
      } catch (Exception e) {
        log.info(e.getMessage());
      }
      if(pageMove !=null){
        //pageMove[0] = redirect or forward
        //pageMove[1] = XXX.jsp
        log.info(pageMove[0]+", "+pageMove[1]);
        if("redirect".equals(pageMove[0])){
          resp.sendRedirect(pageMove[1]);
        }else if("forward".equals(pageMove[0])){
          RequestDispatcher view = req.getRequestDispatcher(pageMove[1]);
          view.forward(req, resp);
        }
      }
    }
  }

}
