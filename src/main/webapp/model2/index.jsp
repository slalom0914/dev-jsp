<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>MVC2 패턴</title>
    <link rel="stylesheet" href="/css/myStyle.css"/>
</head>
<body>
<h1>MVC2 Web Project</h1>
<%@include file="header.jsp"%>
<br><br><br>
<h2>index page 입니다.</h2>
</body>
</html>
<!-- 
로그인 화면은 header.jsp 페이지 제공
jsp페이지 내부에서 다른 jsp페이지를 include할 수 있다.
1)액션태그 - 처리 결과가 포함됨 - 파일이 각각 존재함
index_jsp.java - jsp컨테이너 역할
자바클래스 이름은 WAS에서 결정됨
index_jsp.class - Servlet컨테이너 역할
html주석과 jsp주석은 구분해서 사용할것.
jsp액션태그 방식은 jsp주석으로 처리해야 함.
include액션태그
2)include directive
파일이 하나로 합쳐진다.
index.jsp와 header.jsp가 하나의 java파일로 만들어진다
class파일도 하나이다
-->