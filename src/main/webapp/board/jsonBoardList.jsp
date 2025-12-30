<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%@ page import="com.google.gson.Gson" %>    
<%
	//out.print("json포맷");
	List<Map<String,Object>> list = (List)request.getAttribute("list");
	Gson g = new Gson();
	String temp = g.toJson(list);
	out.print(temp);
%>