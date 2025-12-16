package lab.ch01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;//싱글스레드 안전 - 그래서 읽고쓰기 속도가 Vector빠름
import java.util.HashMap;//싱글스레드 안전 - 읽고 쓰기 속도가 빠름 - 랜덤 저장
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//@Controller + @RequestMapping -> Spring boot -> DispatcherServlet
@WebServlet("/jsonDept")
public class JsonDeptServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException
	{
		res.setContentType("application/json;charset=UTF-8");
		
		List<Map<String,Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("deptno",10);
		map.put("dname","총무부");
		map.put("loc","서울");
		list.add(map);
		map = new HashMap<>();
		map.put("deptno",20);
		map.put("dname","개발부");
		map.put("loc","부산");
		list.add(map);
		map = new HashMap<>();
		map.put("deptno",30);
		map.put("dname","운영부");
		map.put("loc","제주");
		list.add(map);	
		com.google.gson.Gson g = new com.google.gson.Gson();
		String temp = null;
		temp = g.toJson(list);
		PrintWriter out = res.getWriter();
		out.print(temp);		
		
	}//end of doGet
}//end of JsonDeptServlet
