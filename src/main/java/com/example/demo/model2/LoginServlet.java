package com.example.demo.model2;

import com.example.demo.util.HashMapBinder;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
//로그인 할 때 역할
//비번이 포함되므로 get이 아니라 post하였다.
//사용자가 입력한 값을 받아올 때 - request.getParameter():String
//사용자가 입력해야 하는 값이 몇 개 인가요? - 4개 - request.getParameter("컬럼명")4번
//SELECT id, passwd, name, email FROM 회원
//컬럼이 변수이다.- MemberVO
//반복되는 코드는 줄여야 한다. - 업무 규칙은 언제나 언제든 변할 수 있다.
//우리는 늘 수정을 해야 하는 상황에 있다.
//스프링에서는 파라미터 자리를 통해서 이러한 반복되는 코드를 줄여준다 
//LoginServlet에서 직접 MemberDao클래스의 메서드를 호출하는 것이 아니라
//중간에 MemberService경유하는 코드로 구성됨 
// XXXServlet - XXXService - XXXDao(MyBatis, Hibernate 유연한 구조)
// 인스턴스화 - 생성자 호출 - 싱글톤 - 객체 주입(DI, IoC)
// 인스턴스화 시점이나 위치에 따라 차이가 있다. - NullPointerException
// 이른 객체 주입 또는 게으른 객체 주입 -> 퍼포먼스(효율성)
// 자바에서는 객체에 대한 생명 주기를 관리한다.
// 세션을 생성할 때도 request객체가 필요하다.
// 세션을 사용하면 설정한 시간동안 객체가 유지된다.
// page/request/session/application
// 저장할 때 setAttribute(키,값)
// 읽을 때 getAttribute(키):Object - ClassCastingException

@Log4j2
@WebServlet("/model2/login.lo")
public class LoginServlet extends HttpServlet {
    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("doPost 호출");
        MemberService mservice = new MemberService();

        //request body의 JSON을 String으로 읽기
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line=null;
        while((line=reader.readLine())!=null){
            sb.append(line);
        }
        String jsonData = sb.toString();
        //JSON파싱(Gson)
        Gson g = new Gson();
        Member member = g.fromJson(jsonData, Member.class);
        //파싱된 값을 출력해 보기
        log.info("리액트에서 넘어온 아이디 : " + member.getId());
        log.info("리액트에서 넘어온 비번 : " + member.getPasswd());
        // 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember()호출
        // select한 결과를 리턴 받아서 세션에 저장하기
        Member m = mservice.loginMember(member);
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
        //다시 백엔드에서 프론트엔드 내보내는 값(결과물)
        //아래도 스프링에서는 어노테이션으로 제공이 된다.
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();//out객체
        Gson gson = new Gson();
        String json = gson.toJson(map);
        out.println(json);
        out.flush();//한 페이지의 크기 8kb - 여기까지 읽은 내용을 즉시 메모리에서 비우고 로딩
        out.close();//사용한 자원 닫기 - 안하면 위변조 노출
    }
}