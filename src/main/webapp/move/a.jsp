<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//a.jsp페이지에서 저장해둔 상태값 강감찬이 b.jsp에서도 유지되나요?
	request.setAttribute("name","강감찬");
	//스크립틀릿 - 자바코드
	//여기는 서버에서 실행되고 실행결과를 응답으로 내보낸다.
	response.sendRedirect("b.jsp");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>a.jsp</title>
</head>
<body>
a.jsp 본문
</body>
</html>