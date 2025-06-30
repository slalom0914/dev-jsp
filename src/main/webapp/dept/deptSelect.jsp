<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
  //스크립틀릿 - 자바코드를 작성할 있다.
  List<Map<String,Object>> list = (List)request.getAttribute("list");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>부서목록조회</title>
</head>
<body>
  <h2>부서 목록 페이지</h2>

  <table border="1" width="500px">
    <tr>
      <th>deptno</th>
      <th>dname</th>
      <th>loc</th>
    </tr>
<%
  //스크립틀릿, 스크립틀릿, 스크립틀릿  
  //익스프레션, 익스프레션, 익스프레션
  if(list !=null){
    for(int i=0;i<3;i++){
      Map<String,Object> rmap = list.get(i);
%>
    <tr>
      <td><%= rmap.get("deptno") %></td>
      <td><%= rmap.get("dname") %></td>
      <td><%= rmap.get("loc") %></td>
    </tr>
<%
  }//end of for
}//end of if
else{
%>
  <tr>
    <td align="center" colspan="3">조회결과가 없습니다.</td>
  </tr>
<%
}//end of else
%>
  </table>

</body>
</html>