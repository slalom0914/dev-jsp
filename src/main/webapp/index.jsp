<%@ page language="java" contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
hello jsp
<%
	//스크립틀릿 - 자바 변수선언이나 제어문을 쓸 수 있는 영역임.
	//스크립틀릿에서 선언한 변수는 모두 지변이다.
	String msg = "안녕";
%>
<!-- html주석임
아래 스크립트를 익스프레션
 -->
<%=msg %>
</body>
</html>