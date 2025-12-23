package lab.ch01;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
//서블릿에게 컨트롤계층의 역할을 맡김
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/dept")
public class DeptController extends HttpServlet {
	Logger log = Logger.getLogger(DeptController.class);
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doDelete(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//사용자(3000번 서버)가 파라미터로 넘긴 값 출력해 보기
		String deptno = req.getParameter("deptno");
		log.info("사용자가 입력한 부서번호 : "+deptno);
		resp.setContentType("application/json;utf-8");
		PrintWriter out = resp.getWriter();
		
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
		
		String imsi = null;
		Gson g = new Gson();
		imsi = g.toJson(list);
		out.print(imsi);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost");
		String deptno = req.getParameter("deptno");
		String dname = req.getParameter("dname");
		String loc = req.getParameter("loc");
		log.info(deptno + ", " + dname + ", " + loc);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPut(req, resp);
	}

}
