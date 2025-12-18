package lab.ch01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//서블릿도 브라우저의 URL요청으로 호출을 할 수 있다.
@WebServlet("/crudDept")//@Controller + @RequestMapping
public class CrudDeptServlet extends HttpServlet {
	// trace > debug > info >,,,
	Logger log = Logger.getLogger(CrudDeptServlet.class);
	//삭제하기 구현
	/****************************************************************
	 * DELETE FROM dept
	 *  WHERE deptno = ?
	 * @param deptno 10, 20, 30
	 * @return int 1이면 삭제 성공 0이면 삭제 실패
	 ****************************************************************/		
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doDelete");
		resp.sendRedirect("/dept/deptDeleteOk.jsp");
	}//end of doDelete
	//조회,상세조회 - 주문조회, 로그인
	/****************************************************************
	 * SELECT deptno, dname, loc FROM dept
	 * @return List<Map>, List<DeptVO>
	 ****************************************************************/		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doGet");
		//insert here - 조회결과 쥐고 있다.
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
		//요청이 유지도는 동안에는 이 주소번지로 공유가능함.
		//공유가 안되면 NullPointerException -> 500 -> Runtime에러
		//setAttribute의 소유주는 요청객체이다.
		//setAttribute의 파라미터는 두 가지이다.
		req.setAttribute("list", list);
		//jsp페이지 호출하기 -forward로 해야 한다.
		//왜냐하면 servlet과 jsp가 요청이 계속 유지되고 있다 라고 생각
		//비상태 프로토콜이란 요청 URL이 바뀌면 기존에 요청이 끊어지고 새로운요청이 발생함.
		//유지가 안됨
		//기존의 요청 URL이 그대로 인데 실제 화면은 /dept/deptList.jsp가 출력됨
		RequestDispatcher view = req.getRequestDispatcher("/dept/deptList.jsp");
		view.forward(req, resp);		
		
		
	}//end of doGet
	//입력, 저장, 파일업로드, 조회인데 보안상 값이 노출되지 않도록 할 때
	/****************************************************************
	 * INSERT INTO dept(deptno, dname, loc)
	 *           VALUES(:deptno,:dname,:loc)
	 *  @param 사용자가 선택한 부서번호 - deptno
	 *  @param 사용자가 입력한 부서명 - dname
	 *  @param 사용자가 입력한 지역명 - loc
	 *  @return int 1이면 입력 성공 0이면 입력 실패
	 *  주의 : 브라우저의 URL을 통해서는 POST방식을 테스트할 수 없다.
	 *  만일 요청을 해보면 조회가 된다. - 원하는게 아니다.-> 입력
	 *  반드시 postman, swagger, javascript
	 ****************************************************************/	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost");
		resp.sendRedirect("/dept/deptInsertOk.jsp");
	}//end of doPost
	//수정하기 구현
	/****************************************************************
	 * UPDATE dept(집합이름)
	 *    SET dname = 수정할 부서명(1)
	 *       ,loc = 수정할 지역명(2)
	 *  WHERE deptno = 수정할 부서번호(pk)(3)
	 *  @param 사용자가 입력한 부서명 - dname
	 *  @param 사용자가 입력한 지역명 - loc
	 *  @param 사용자가 선택한 부서번호 - deptno
	 *  @return int 1이면 수정 성공 0이면 수정 실패
	 *  질문 : doPut메서드의 리턴 타입이 void인데 1 또는 0을 어떻게 jsp페이지에 
	 *  전달할 수 있나요?
	 *  1)query string을 이용한다.
	 *  2)request scope를 이용한다.
	 *  왜 jsp페이지에 1또는 0을 넘겨야 하나요?
	 *  1이면 사용자에게 수정이 성공하였습니다. 또는 0일 땐 실패하였습니다. 후처리
	 ****************************************************************/
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPut");
		//1.입력-청취 -> req.getParameter("dname");
		//1.입력-청취 -> req.getParameter("loc");
		//1.입력-청취 -> req.getParameter("deptno");
		resp.sendRedirect("/dept/deptUpdateOk.jsp");
	}

}
