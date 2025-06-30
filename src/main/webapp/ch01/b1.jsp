<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%
  //스크립틀릿 
  Map<String, Object> map = (Map)request.getAttribute("map");
%>
<!DOCTYPE html>
<html>
<head>
    <title>b1.jsp</title>
</head>
<body>
    <h1>여기는 b1.jsp페이지</h1>
    <h2><%="map"+map.get("deptno") %></h2>
</body>
</html>