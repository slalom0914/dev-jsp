<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map, java.util.HashMap" %>
<%@ page import="jakarta.servlet.RequestDispatcher" %>
<%
  //스크립틀릿 - 변수선언(지변이다), 메서드 선언하거나 클래스 선언 불가함.
  //인스턴스화, 제어문 사용
  //서버에서 처리되는 부분이다. - 서버에서 결정된다.
  Map<String, Object> map = new HashMap<>();
  map.put("deptno", 10);
  request.setAttribute("map", map);
  out.print(map.size());//0
  out.print("<br/>");
  RequestDispatcher view = request.getRequestDispatcher("./b1.jsp");
  view.forward(request, response);
%>
<br />
<%="map.size() : "+map.size()%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Welcome to Page Title</h1>
    <p>This is a reusable JSP template.</p>
</body>
</html>