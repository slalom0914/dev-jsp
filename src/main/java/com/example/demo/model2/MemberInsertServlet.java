package com.example.demo.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/model2/minsert.lo")
public class MemberInsertServlet extends HttpServlet {
    public MemberInsertServlet() { }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemberService mservice = new MemberService();
        // 전달 받은 파라미터 값을 변수에 담아 새로운 객체를 생성한다
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        int result = mservice.insertMember(new Member(id, passwd, name, email));
        if (result > 0) {
            response.sendRedirect("index.jsp");
        } else { }
    }
}
