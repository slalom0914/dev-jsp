package com.example.demo.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/model2/logout.lo")
public class LogoutServlet extends HttpServlet {

    public LogoutServlet() { }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션을 request를 통해 받아올 때, 기존에 생성 되어 있지 않다면
        // 새로 만드는 것을 원치 않기 때문에 false 값을 인자로 한다.
        // 톰캣에서 세션 디폴트 값은 30분이다.
        HttpSession session = request.getSession(false);

        // 만약 세션이 null이 아니라면 해당 세션을 만료 시켜 세션 정보를 없앤다.
        // 세션 저장소에 담긴 모든 값을 삭제할 때 invalidate()사용함
        if(session != null) session.invalidate();
        response.sendRedirect("./index.jsp");
    }
}
