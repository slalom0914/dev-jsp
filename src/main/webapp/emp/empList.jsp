<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map" %>
<%
  //스크립틀릿 
  //테스트 시나리오
  //jsp를 직접 요청하면 -> 오라클 서버를 경유하지 않는다.
  //서블릿을 경유하게 되면 -> EmpDao empDao = new EmpDao() -> MyBatis, Hibernate
  List<Map<String, Object>> list = (List)request.getAttribute("list");
  int size = 0;
  if(list !=null){
    size = list.size();
    out.print(size);
  }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Welcome to Page Title</h1>
    <ul>
<%
  //스크립틀릿
  for(int i=0;i<size;i++){
    Map<String,Object> map =list.get(i);
%>
      <li><%=map.get("ename") %> : <%=map.get("job") %></li>
<%
  }//end of for
%>
    </ul>
</body>
</html>