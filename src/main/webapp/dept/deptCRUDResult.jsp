<%@ page language="java" contentType="text/plain; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// insert, update, delete
	String gubun = request.getParameter("gubun");
	if("insert".equals(gubun)){
		out.print("부서 등록 성공");
	}
	if("update".equals(gubun)){
		out.print("부서 수정 성공");
	}
	if("delete".equals(gubun)){
		out.print("부서 삭제 성공");
	}	
%>    
