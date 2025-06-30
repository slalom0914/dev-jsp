<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="com.example.demo.model2.*" %>
<%
    Member m = (Member)session.getAttribute("member");
    out.print("m : "+m);
%>
<!-- html영역 -->
<%
//스크립틀릿 - 자바코드 작성이 가능합니다 
if (m == null){//세션에 로그인한 정보가 없을 때 
%>
<div class="box">  <!— 로그인 From -->
    <form id="f_login" action="/model2/login.lo" method="post">
    <p>&nbsp; &nbsp;아이디 :
        <input type="text" name="id" id="userid"></p>
    <p>비밀번호 : <input type="password" name="passwd" id ="userpwd"></p>
    <button type="button" id="btnLogin">로그인하기</button>
    &nbsp;&nbsp;
    <button type="button" id="btnEnroll" onclick=
            "location.href='enroll.jsp';">회원가입</button>
    </form>
</div>
<% } else { %>
<div class="box">
    <h3>환영합니다, <%= m.getName() %>님!!!</h3>
    <p>오늘도 좋은 하루 되세요~!!</p>
    <button onclick="location.href='myInfo.jsp'">회원정보보기
    </button>
    <button onclick="location.href='/model2/logout.lo'">로그아웃</button>
</div>
<% } %>
<script>
    const btnLogin = document.getElementById("btnLogin");
    const f_login = document.getElementById("f_login");
    if(btnLogin){
        btnLogin.onclick = function(){
            console.log("로그인")
            f_login.submit();
        }
    }

</script>
<hr style="clear:both;">  <!— 이전 float 속성을 지우고 구분선을 표시한다. -->
