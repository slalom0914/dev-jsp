<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  //jsp스크립트  - 스크립틀릿
  String message = request.getParameter("message");
  out.print(message);
%>