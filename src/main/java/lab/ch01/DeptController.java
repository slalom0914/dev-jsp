package lab.ch01;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	ObjectMapper mapper = new ObjectMapper();
	CrudDeptDao deptDao = new CrudDeptDao();
	//부서 정보 삭제하기
	//delete from dept where deptno = ?
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doDelete");
		//-> http://localhost:8000/dept?deptno=50 //쿼리스트링
		String deptno = req.getParameter("deptno");
		log.info(deptno);		
		int i_deptno = Integer.parseInt(deptno);
		//1이면 삭제 성공, 0이면 삭제 실패
		int result = -1;//Front-End로 전달해서 후처리하기
		result = deptDao.deptDelete(i_deptno);
		resp.setContentType("application/json;charset=utf-8");
		//resp.setContentType("text/plain;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(result);
		out.flush();
	}
	//부서 정보 조회하기
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//사용자(3000번 서버)가 파라미터로 넘긴 값 출력해 보기
		String dname = req.getParameter("dname");
		log.info("사용자가 입력한 부서번호 : "+dname);
		resp.setContentType("application/json;utf-8");
		PrintWriter out = resp.getWriter();
		
		List<Map<String,Object>> list = new ArrayList<>();
		//메서드 오버로딩 - 같은이름의 메서드를 여러개 가진다.단 타입이 다르거나 갯수가 다를때만
		list = deptDao.deptList(dname);
		
		String imsi = null;
		Gson g = new Gson();
		imsi = g.toJson(list);
		out.print(imsi);
	}
	//부서 정보 등록하기
	//사용자로 부터 입력받은 값을 전달 받아서 처리하기
	//Front-End : React -> JSON형식
	//jsp : 폼 전송 받음 , 
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost");
		//요청 본문이 JSON포맷일 때 입력값 요청하기
		Map<String,Object> map = 
				mapper.readValue(req.getInputStream(), Map.class);
		log.info(map.get("deptno") + ", " 
			   + map.get("dname") + ", " + map.get("loc"));
		int result = -1;
		
		result = deptDao.deptInsert(map);
		resp.setContentType("application/json; charset=utf-8");
		PrintWriter out = resp.getWriter();
		//서블릿에서 오라클 연동하기 까지 처리함. - myBatis외부 라이브러리 사용ㅇ
		out.print(result);//등록성공이면 : 1, 등록실패이면 :  0
		out.flush();
	}
	//부서 정보 수정하기
	//update dept set dname = ?, loc = ? where deptno = ?
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPut");
		//요청 본문이 JSON포맷일 때 입력값 요청하기
		Map<String,Object> map = 
				mapper.readValue(req.getInputStream(), Map.class);
		log.info(map.get("deptno") + ", " 
			   + map.get("dname") + ", " + map.get("loc"));	
		int result = deptDao.deptUpdate(map);
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter out = resp.getWriter();
		out.print(result);//1 아니면 0
		out.flush();
	}

}
