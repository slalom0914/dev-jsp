package com.example.demo.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/model2/mdelete.lo")
public class MemberDeleteServlet extends HttpServlet {

    public MemberDeleteServlet() { }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemberService mservice = new MemberService();
        int result = mservice.deleteMember(request.getParameter("id"));
        if(result > 0) {
            // 기존에 생성되어 있던 세션을 만료
            HttpSession session = request.getSession(false);
            session.invalidate();
        }
        // index 페이지로 이동
        response.sendRedirect("index.jsp");
    }
}
