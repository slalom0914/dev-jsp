<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  //스크립틀릿 - 여기 선언된 변수는 지역변수 이다.
  int i = 1;
%>
<!DOCTYPE html>
<html>
<head>
    <title>a.jsp</title>
</head>
<body>
    <h1>Welcome to Page Title</h1>
    <jsp:include page="b.jsp" flush="false" />
    <p>This is a reusable JSP template.</p>
</body>
</html>