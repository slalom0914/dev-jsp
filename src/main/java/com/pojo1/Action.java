package com.pojo1;
/*
 * 클래스
 * 전역변수, 메서드, 생성자, 인스턴스화, 파라미터, 리턴타입
 * 
 */
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/*
 * doGet의 리턴타입을 다른 타입으로 변경할 수 있다.
 * 인터페이스에 선언된 모든 메소드는 추상메소드 이다.
 * void에서 ActionForward로 한 이유는 redirect인지 forward인지와
 * 업무마다 응답페이지의 path정보가 다르므로 변수로 처리하고자 하였다.
 */
public interface Action {
	//서블릿이 제공하는 Rest API메서드는 리턴타입이 void 였다.
	//표준이 제공하는 메서드의 리턴타입을 내가 변경할 수 있나?
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException;
}
