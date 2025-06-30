<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%
  List<Map<String,Object>> zlist = (List)request.getAttribute("zlist");
  int size = 0;
  //아래에서 size는 컬럼의 수가 아니라 로우 수이다.
  if(zlist !=null) size = zlist.size();
  out.print(size);//0이면 조회결과 없음. 
%>
<!DOCTYPE html>
<html>
<head>
    <title>우편번호 검색기</title>
</head>
<body>
    <h1>우편번호 검색기</h1>
    <p>검색 결과 목록 처리</p>
</body>
</html>