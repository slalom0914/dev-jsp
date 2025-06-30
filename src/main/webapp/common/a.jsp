<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  //-> http://localhost:8000/common/a.jsp?dong=가산
  String dong = request.getParameter("dong");
  out.print(dong);
%>