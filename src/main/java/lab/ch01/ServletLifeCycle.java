package lab.ch01;
import java.io.IOException;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
/*
 * 웹요청을 받아서 자바코드로 응답을 만드는 서버 프로그램
 * 역할 : 요청 수신(사용자가 입력한 아이디와 비번을 청취), 로직처리, HTML/JSON 응답 생성
 * 응답을 처리할 때 마임타입이 중요함 - application/json, text/html
 * JSP, Spring MVC도 내부적으로 서블릿 위에서 동작함.
 * Spring에서 사용하는 서블릿이름은 DispatcherServlet
 * 
 * 서블릿은 언제 생성되고, 어떤 메서드가 몇번 호출이 되는지?
 * init() -> service() -> destroy()
 * init() - 서블릿이 최초 생성시 - 단 1번 호출됨 - 싱글톤패턴이라서 하나만 가지고 공유
 * service() - 요청을 할 때마다  호출됨 -> 여러번 호출이 됨
 * destroy() - 1번만 호출됨
 * 개발자는 service()에 관련한 코드만 작성하면 됨.
 * 
 */
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//-> http://localhost:8000/life
//-> http://localhost:8000/dev-jsp/life->  404번 발생
@WebServlet("/life")//@Controller + @RequestMapping
public class ServletLifeCycle extends HttpServlet {
	Logger log = Logger.getLogger(ServletLifeCycle.class);
	//전처리 하기 -> 필터, 인터셉터, CORS이슈
	@Override
	public void init(ServletConfig config) throws ServletException {
		log.info("서블릿 초기화");
		//서블릿 객체 생성시 단 한 번 실행
		//DB연결, 설정, 자원준비
		//테스트방법: 새로고침(F5)해도 다시 호출되지 않음.
		//개발자가 호출하는 메서드가 아님
	}//end of init

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.info("ServletLifeCycle service");
		//1.입력
		//2.처리
		//3.출력
		res.setContentType("text/html;charset=UTF-8");
		res.getWriter().println("<h2>서블릿라이프사이클 테스트</h2>");
		res.getWriter().println("<p>서블릿이 정상적으로 동작하고 있습니다.</p>");
	}//end of service
	//뒷 정리 작업(후처리)
	//서블릿이 제거될 때 딱 한번 호출이 됨 - 자원반납, 스레드 반납
	//지변으로 사용하는 것을 권장
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		log.info("ServletLifeCycle destroy");
	}//end of destroy

}
