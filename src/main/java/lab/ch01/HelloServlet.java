package lab.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//서블릿에서 화면(UI/UX)을 그리는 건 비효율적임 -> 그래서 JSP API내놓았음
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	Logger log = Logger.getLogger(HelloServlet.class);

	// 수정할 때 - UPDATE member SET mem_pw = 123 WHERE mem_id=kiwi
	public void doPut(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// System.out.println("doPut호출");
		log.info("doPut " + req.getParameter("mem_id")
				+ ", " + req.getParameter("mem_pw"));
	}

	// 삭제할 때 - DELETE FROM member WHERE mem_id='kiwi'
	public void doDelete(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// System.out.println("doDelete호출");
		log.info("doDelete " + req.getParameter("mem_id")
				+ ", " + req.getParameter("mem_pw"));
	}

	// 입력 또는 보안상 중요한 요청일 때 -> 노출이 안되니까
	// INSERT INTO member VALUES('kiwi','123','키위',,,,)
	// SELECT mem_name FROM member
	// WHERE mem_id='kiwi' AND mem_pw='123'
	// 개발시에는 get방식으로 단위테스트 활용해서 마무리되면 post방식으로 변경해서 커밋함.
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		// System.out.println("doPost호출");
		log.info("doPost " + req.getParameter("mem_id")
				+ ", " + req.getParameter("mem_pw"));
	}

	// 서블릿의 doGet메서드는 Restful API가 제공하는 get 메서드와 같다.
	// 파라미터 자리는 변수를 선언하는 자리 -> null
	// SELECT mem_id, mem_name, mem_sal FROM member
	// 주의:사용자가 화면에 입력한 값이 URL통해서 노출이 되므로 알고 사용하기
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.info("doGet " + req.getParameter("mem_id")
				+ ", " + req.getParameter("mem_pw"));
		// GET방식으로 서버에 요청할 때 쿼리스트링을 사용할 수 있다.
		// -> http://localhost:8000/hello?mem_id=kiwi&mem_pw=123
		// res변수에 대해 생성이 안되었지만 NullPointerException발생하지 않음.
		// null참조하지 않고 있다.
		// 요청객체가 있어야 사용자가 화면에 입력한 값을 서버에서 읽을 수 있다.
		String mem_id = req.getParameter("mem_id");
		String mem_pw = req.getParameter("mem_pw");
		System.out.println(mem_id + ", " + mem_pw);
		res.setContentType("text/html;charset=UTF-8");
		// 응답객체를 통해서 생성한 out객체는 브라우저에 쓰기를 하는 것이고
		// System.out.print하는 것은 콘솔에 출력됨.
		PrintWriter out = res.getWriter();
		String msg = "서블릿";
		out.println("<html>");
		out.println("<head><title>서블릿</title></head>");
		out.println("<body>");
		out.println("<h2>Hello " + msg + "</h2>");
		out.println("</body>");
		out.println("</html>");
	}
}
