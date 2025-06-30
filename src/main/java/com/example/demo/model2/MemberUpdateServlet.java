package com.example.demo.model2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/model2/mupdate.lo")
public class MemberUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MemberUpdateServlet() { }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
// 기존의 생성된 세션과 세션에 담겨있던 “member” 객체를 불러온다.
        HttpSession session = request.getSession(false);
        Member m = (Member) session.getAttribute("member");

        MemberService mservice = new MemberService();

        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        PrintWriter out = response.getWriter();
        if(m != null && m.getId().equals(id)) {  // 만약 ID 값이 기존값과 일치한다면 수정 실행
            m.setPasswd(passwd);
            m.setName(name);
            m.setEmail(email);
            if(mservice.updateMember(m) > 0) {
                session.setAttribute("member", m);
                response.sendRedirect("index.jsp");
            } else {
                out.append("<script>alert('회원 정보 수정 오류!\n 관리자에게 문의하세요!');</script>");
            }
        } else {
            RequestDispatcher view =
                    request.getRequestDispatcher("views/error/errorPage.jsp");
            request.setAttribute("msg", "회원 정보 수정 오류 발생!!");
            view.forward(request, response);
        }
        out.flush();
        out.close();
    }
}
