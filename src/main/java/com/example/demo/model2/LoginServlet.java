package com.example.demo.model2;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Log4j2
@WebServlet("/model2/login.lo")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doPost 호출");
        MemberService mservice = new MemberService();
        String id = request.getParameter("id");
        String passwd = request.getParameter("passwd");
        log.info(id + " , " + passwd);
        // 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember()호출
        // select한 결과를 리턴 받아서 세션에 저장하기
        Member m = mservice.loginMember(id, passwd);
        Map<String, Object> map = new HashMap<>();
        //조회결과가 존재하면? true, 존재하지 않으면 false
        if (m != null) {//조회결과가 있을 때
            HttpSession session = request.getSession();
            session.setAttribute("member", m);
            map.put("result", "ok");
            map.put("name", m.getName());
        } else {
            map.put("result", "fail");
        }
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();//out객체
        Gson gson = new Gson();
        String json = gson.toJson(map);
        out.println(json);
        out.flush();//한 페이지의 크기 8kb - 여기까지 읽은 내용을 즉시 메모리에서 비우고 로딩
        out.close();//사용한 자원 닫기 - 안하면 위변조 노출
    }
}