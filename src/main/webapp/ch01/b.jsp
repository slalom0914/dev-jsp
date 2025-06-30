<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Map" %>
<%
    Map<String,Object> map = (Map)request.getAttribute("map");
    out.print(map);//null
%>
<!DOCTYPE html>
<html>
<head>
    <title>b.jsp</title>
</head>
<body>
    <h1>여기는 b.jsp페이지</h1>
    <h2><%="데이터"%></h2>
</body>
</html>