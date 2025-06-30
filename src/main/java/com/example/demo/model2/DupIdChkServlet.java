package com.example.demo.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/model2/dupid.lo")
public class DupIdChkServlet extends HttpServlet {

    public DupIdChkServlet() { }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemberService mservice = new MemberService();
        int result = mservice.dupIdChk(request.getParameter("id"));
        PrintWriter out = response.getWriter();

        if(result > 0) {
            out.append("fail");  // 만약 dupIdChk()의 결과값이 0 이상이면 ‘fail’
        } else {
            out.append("ok");  // 결과값이 0 보다 크지 않으면, ‘ok’를 담아서 보낸다.
        }
        out.flush();
        out.close();
    }
}
