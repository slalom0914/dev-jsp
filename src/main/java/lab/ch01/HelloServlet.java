package lab.ch01;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//서블릿에서 화면(UI/UX)을 그리는 건 비효율적임 -> 그래서 JSP API내놓았음
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
	{
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter out = res.getWriter();
		String msg = "서블릿";
		out.println("<h2>Hello "+ msg +"</h2>");
	}
}
