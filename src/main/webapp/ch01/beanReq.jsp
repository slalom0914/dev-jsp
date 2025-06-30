<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script>
      function send(){
        console.log('send 호출')
        document.querySelector("#f_msg").submit()
      }
    </script>
</head>
<body>
    <h1>간단한 자바빈즈 프로그램</h1>
    <hr color="red" />
    <form id="f_msg" method="get" action="./beanRes.jsp">
      메시지 : <input type="text" name="message" />
      <br />
      <input type="button" value="전송" onclick="javascript:send()">
    </form>
</body>
</html>