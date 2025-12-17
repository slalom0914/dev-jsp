<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>    
<%
	List<Map<String,Object>> list =
		(List<Map<String,Object>>)request.getAttribute("list");
	out.print(list);
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서목록[webapp]</title>
</head>
<body>
	<h2>부서목록</h2>
	<table border="1" width="500px" height="150px">
		<tr>
			<th>부서번호</th><th>부서명</th><th>지역</th>
		</tr>
<%
//스크립틀릿 - 지역변수, 제어문, 연산자
	for(int i=0;i<3;i++){
		Map<String,Object> rmap = list.get(i);
%>		
		<tr>
			<td><%=rmap.get("deptno") %></td>
			<td><%=rmap.get("dname") %></td>
			<td><%=rmap.get("loc") %></td>
		</tr>
<%
	}
%>		
	</table>
</body>
</html>