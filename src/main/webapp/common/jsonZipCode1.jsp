<%@ page language="java" contentType="application/json; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.example.demo.vo.ZipCodeVO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.google.gson.Gson" %>
<%
  // 스크립틀릿
  // http://localhost:8000/common/jsonZipCode.jsp
  // json -> List<VO>, List<Map>
  // List<VO>, List<Map> -> json
  List<ZipCodeVO> list = new ArrayList<>();
  ZipCodeVO zvo = new ZipCodeVO();
  zvo.setZipcode(25678);
  zvo.setAddress("서울시 마포구 공덕동");
  list.add(zvo);
  zvo = new ZipCodeVO();
  zvo.setZipcode(25688);
  zvo.setAddress("서울시 영등포구 당산동");
  list.add(zvo);
  Gson g = new Gson();
  String temp = g.toJson(list);
  out.print(temp);
%>