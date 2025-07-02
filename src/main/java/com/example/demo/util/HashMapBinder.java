package com.example.demo.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
//NullPointerException 디버깅
//요청객체는 서블릿만 가질 수  있다.
public class HashMapBinder {
  HttpServletRequest req = null;
  public HashMapBinder(HttpServletRequest req){
    //this.req = req;
  }
  //파라미터로 부터 자료구조를 받아온다. -pmap

  public void binder(Map<String, Object> pmap){
    pmap.clear();//새로 담기 위해서 기존에 있다면 비우자
    Enumeration<String> en = req.getParameterNames();//id, passwd, name,,,
    while(en.hasMoreElements()){
      String key = en.nextElement();//id, passwd, name,,,,,
      pmap.put(key,req.getParameter(key));
    }//end of while
  }//end of binder
}

/*
<input type="text" name="id">
String id = request.getParameter("id");
<input type="text" name="passwd">
String passwd = request.getParameter("passwd");
<input type="text" name="name">
String name = request.getParameter("name");

 */
