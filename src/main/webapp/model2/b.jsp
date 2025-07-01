<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList" %>
<%@ page import="java.util.HashMap, com.google.gson.Gson" %>
<%
/*
  스크립틀릿 - 처리 주체 서버이다.
  웹브라우저로 다운로드 되는 데이터는 이미 결정이 되어 있다.
  데이터를 처리하는 주체는 순수한 자바이다.
  MVC패턴
  Model계층 - MemberService + MemberDao(JDBC API-반복되는코드-> ORM)
  클래스 설계시 Dao패턴을 두는 이유는
  MyBatis나 Hibernate 외부 라이브러리를 적용하기 쉽게 하기 위함.
  진입장벽이 높다
  JSON출력은 jsp 보다는 Servlet이나 @Controller에서 처리하는 것을 권장
  JSP는 출력물에 HTML주입 위험이 있으므로 REST API에서 jsp를 피하고
  직접 PrintWriter로 출력하는 것이 더 안전함
*/
  List<Map<String,Object>> list = new ArrayList<>();
  Map<String,Object> map = new HashMap<>();
  map.put("id","kiwi");
  map.put("pw","123");
  map.put("name","키위");
  list.add(map);
  map = new HashMap<>();
  map.put("id","tomato");
  map.put("pw","123");
  map.put("name","토마토");
  list.add(map);
  //out.print(list);
  //out은 내장객체 제공됨, request, response
  //내장객체는 별도의 인스턴스화 없이도 바로 사용이 가능함
  Gson g = new Gson();
  String temp = g.toJson(list);
  out.print(temp);
%>
