package com.example.demo.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//아키텍쳐 설계가 잘 만들어질 수록 뭔가 코딩하는 느낌이 없어진다.
//복사 붙여넣기로 구현하고 테이블명과 컬럼명과 변경하면 
//CRUD처리 되고 있다.
//Framework - 실력차 줄어들고, 생산성이 올라간다.
//프레임워크를 사용하면 적은 코딩으로도 기능을 수정할 수 있다.
public class BoardController implements Controller{
  String methodName = null;
  String path = null;
  //파라미터가 없는 디폴트 생성자는 생략이 가능하지만
  //파라미터가 있는 생성자는 개발자의 의도가 포함된 것이므로
  //직접 구현한다.
  public BoardController(String methodName){
    this.methodName = methodName;
  }
  //doGet, doPost, doPut, doDelete
  //좋아요, 싫어요, 댓글처리
  @Override
  public String execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    
    if(methodName.equals("게시글목록")){//GET
      //select처리
      path = "redirect:XXX.jsp";
    }
    else if(methodName.equals("게시글등록")){//POST
      //insert into 회원 values(?,?,?,..)
      path = "redirect:XXX.jsp";
    }
    else if(methodName.equals("게시글수정")){//PUT
      //update 회원 set mem_name=?, mem_address = ?
      // where 회원아이디 = ?
      path = "redirect:XXX.jsp";
    }
    else if(methodName.equals("게시글삭제")){//PUT
      //delete from 회원 where 회원아이디 = ?
      path = "redirect:XXX.jsp";
    }

    return path;//json, jsp
  }

}
