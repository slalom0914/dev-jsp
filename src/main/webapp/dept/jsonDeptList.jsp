<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
	//스크립틀릿 - 자바땅 - 변수,제어문, 메서드 안됨, 전역변수안됨
	List<Map<String,Object>> list = new ArrayList<>();
	Map<String, Object> map = new HashMap<>();
	map.put("deptno",10);
	map.put("dname","총무부");
	map.put("loc","서울");
	list.add(map);
	map = new HashMap<>();
	map.put("deptno",20);
	map.put("dname","개발부");
	map.put("loc","부산");
	list.add(map);
	map = new HashMap<>();
	map.put("deptno",30);
	map.put("dname","운영부");
	map.put("loc","제주");
	list.add(map);	
	com.google.gson.Gson g = new com.google.gson.Gson();
	String temp = null;
	temp = g.toJson(list);
	out.print(temp);
%>