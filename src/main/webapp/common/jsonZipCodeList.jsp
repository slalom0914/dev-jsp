<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="com.google.gson.Gson" %>
<%
  //스크립틀릿, 익스프레션, page directive
  List<Map<String,Object>> zlist = (List)request.getAttribute("zlist");
  Gson g = new Gson();
  String temp = g.toJson(zlist);
  out.print(temp);
%>