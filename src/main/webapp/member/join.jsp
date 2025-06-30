<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <script>
      const myHeaders = new Headers();
      myHeaders.append("Cookie", "JSESSIONID=7E1C5DA35F2FE74C65552878CBA33464");

      const requestOptions = {
        method: "GET",
        headers: myHeaders,
        redirect: "follow"
      };

      fetch("http://localhost:8000/common/zipcodeSearch?type=json&dong=당산", requestOptions)
        .then((response) => response.text())
        .then((result) => console.log(result))
        .catch((error) => console.error(error));      
    </script>
    <h1>Welcome to Page Title</h1>
    <p>This is a reusable JSP template.</p>
</body>
</html>