package com.example.demo.model2;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.example.demo.util.HashMapBinder;
@Log4j2
@WebServlet("/model2/minsert.lo")
public class MemberInsertServlet extends HttpServlet {
    public MemberInsertServlet() { }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MemberService mservice = new MemberService();
        // 전달 받은 파라미터 값을 변수에 담아 새로운 객체를 생성한다
        HashMapBinder hmb = new HashMapBinder(request);
        Map<String,Object> pmap = new HashMap<>();
        hmb.binder(pmap);

        int result = mservice.insertMember(pmap);
        log.info(result);//0이면 등록 실패 1이면 등록 성공
        if (result > 0) {
            response.sendRedirect("./index.jsp");
        } else { 
            response.sendRedirect("./memberError.jsp");
        }
    }
}
