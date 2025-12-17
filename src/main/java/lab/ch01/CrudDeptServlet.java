package lab.ch01;

import java.io.IOException;

import org.apache.log4j.Logger;

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
	}//end of doDelete
	//조회,상세조회 - 주문조회, 로그인
	/****************************************************************
	 * SELECT deptno, dname, loc FROM dept
	 * @return List<Map>, List<DeptVO>
	 ****************************************************************/		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doGet");
	}//end of doGet
	//입력, 저장, 파일업로드, 조회인데 보안상 값이 노출되지 않도록 할 때
	/****************************************************************
	 * INSERT INTO dept(deptno, dname, loc)
	 *           VALUES(:deptno,:dname,:loc)
	 *  @param 사용자가 선택한 부서번호 - deptno
	 *  @param 사용자가 입력한 부서명 - dname
	 *  @param 사용자가 입력한 지역명 - loc
	 *  @return int 1이면 입력 성공 0이면 입력 실패
	 ****************************************************************/	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPost");
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
	 ****************************************************************/
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		log.info("doPut");
		//1.입력-청취 -> req.getParameter("dname");
		//1.입력-청취 -> req.getParameter("loc");
		//1.입력-청취 -> req.getParameter("deptno");
		
	}

}
