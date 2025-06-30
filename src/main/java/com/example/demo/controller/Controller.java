package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//doGet(req,res)
//doPost(req,res)
//doPut(req,res)
//doDelete(req,res)
//execute내가 설계한 메서드 이름이다.
//문제는 톰캣서버로 부터 어떻게 요청객체와 응답객체를 주입 받을 것인가?
//pass by value, refer by value(이것으로 해결한다.)
//요청객체와 응답객체는 개발자 정의하지 않는다. - WAS
public interface Controller {
  //사용자 정의 메서드에 파라미터 자리에 요청객체와 응답객체를 
  //톰캣 서버로 부터 어떻게 주입을 받아올 것인가? - 싱글톤
  public String execute(HttpServletRequest req, HttpServletResponse res)
  throws Exception;
}
//용어정리
//IoC(제어역전, 역제어), DI(의존성주입), POJO
//추상클래스, 인터페이스
//인스턴스화, 생성자, 파라미터, 리턴타입
//RestAPI
//sendRedirect, forward