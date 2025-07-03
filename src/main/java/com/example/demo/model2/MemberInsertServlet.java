package com.example.demo.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.util.HashMapBinder;
import com.google.gson.Gson;
@Log4j2
@WebServlet("/model2/minsert.lo")
public class MemberInsertServlet extends HttpServlet {
    public MemberInsertServlet() { }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemberService mservice = new MemberService();
        // 전달 받은 파라미터 값을 변수에 담아 새로운 객체를 생성한다
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line = null;
        while((line = reader.readLine())!=null){
            sb.append(line);
        }
        String jsonData = sb.toString();
        //JSON 파싱
        Gson g = new Gson();
        Member member = g.fromJson(jsonData, Member.class);
        int result = mservice.insertMember(member);
        log.info(result);//0이면 등록 실패 1이면 등록 성공
        if (result > 0) {
            response.sendRedirect("./index.jsp");
        } else { 
            response.sendRedirect("./memberError.jsp");
        }
    }
}
