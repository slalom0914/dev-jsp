<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enroll Member</title>
    <link rel="stylesheet" href="../../css/myStyle.css" />
</head>
<body>
<h2 align="center">회원 가입 하기</h2>
<hr>
<section id="myinfo">
    <form action="../../minsert.lo" id="enrollForm" method="post">
        <table>
            <tr>
                <td>ID :</td>
                <td><input type="text" name="id" id="userid"></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input type="password" name="passwd"></td>
            </tr>
            <tr>
                <td>이름 :</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>E-Mail :</td>
                <td><input type="email" name="email"></td>
            </tr>
        </table>
        <p align="center">
            <button type="submit">회원 가입하기</button> &nbsp; &nbsp;
            <button type="reset">작성 양식 초기화</button>
        </p>
    </form>
</section>
<br> <br>
<p align="center">
    <button type="button" onclick="location.href='/model2/index.jsp';">메인으로
        가기</button>
</p>
<script type="text/javascript">
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById("enrollForm");
        const userid = document.getElementById("userid");
        const regex = /^[A-Za-z0-9]{4,14}$/;

        form.addEventListener("submit", function(event) {
            let chk = 0;

            if (userid.value.length < 4) {
                alert("아이디는 4자 이상이어야 합니다.");
            } else if (!regex.test(userid.value)) {
                alert("아이디는 영문자와 숫자만 가능합니다.");
            } else {
                // 동기 AJAX: XMLHttpRequest (async: false)
                // 동기 호출은 권장되지 않으나, 기존 jQuery 동기 호출을 유지함
                const xhr = new XMLHttpRequest();
                xhr.open("POST", "../../dupid.lo", false); // 동기 호출
                xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

                // FormData나 URLSearchParams 사용 가능
                const params = "id=" + encodeURIComponent(userid.value);

                xhr.send(params);

                if (xhr.status === 200) {
                    const value = xhr.responseText.trim();
                    if (value === "fail") {
                        alert("이미 존재하는 아이디입니다.\n 다른 아이디로 정하십시오.");
                    } else {
                        alert("정상 가입이 되었습니다.");
                        chk++;
                    }
                } else {
                    alert("code:" + xhr.status + "\n" + "message:" + xhr.responseText + "\n" + "error:" + xhr.statusText);
                }
            }

            if (chk === 0) event.preventDefault();
        });
    });
</script>
</body>
</html>