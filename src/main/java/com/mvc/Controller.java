package com.mvc;
// Controller controller = new DeptController();
// Controller controller = new EmpController();
// Controller controller = new MemberController();
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
//단독으로 인스턴스화 할 수 없다.- 생성자 가질 수 없다. 오직 추상메서드만 가짐
public interface Controller {
	// 스프링에서 ModelAndView는 컨트롤러 클래스가 DispatcherServlet에게
	// 응답으로 전달하는 객체임
	// ModelAndView역할 - 1)응답페이지 이름가짐, 2)조회결과에 대한 자료구조를 가짐
	public ModelAndView execute(HttpServletRequest req
			                  , HttpServletResponse res)
	throws Exception;
	public String execute(HttpServletRequest req
                        , HttpServletResponse res, String viewName)
    throws Exception;
	public Object deptInsert(HttpServletRequest req
            , HttpServletResponse res);	
	public Object deptUpdate(HttpServletRequest req
            , HttpServletResponse res);
	public Object deptDelete(HttpServletRequest req
            , HttpServletResponse res);
	public Object deptDetail(HttpServletRequest req
            , HttpServletResponse res);
	public Object deptList(HttpServletRequest req
            , HttpServletResponse res);	
/*
	public Object empInsert(HttpServletRequest req
            , HttpServletResponse res);	
	public Object empUpdate(HttpServletRequest req
            , HttpServletResponse res);
	public Object empDelete(HttpServletRequest req
            , HttpServletResponse res);
	public Object empDetail(HttpServletRequest req
            , HttpServletResponse res);
	public Object empList(HttpServletRequest req
            , HttpServletResponse res);	
	
	public Object memberInsert(HttpServletRequest req
            , HttpServletResponse res);	
	public Object memberUpdate(HttpServletRequest req
            , HttpServletResponse res);
	public Object memberDelete(HttpServletRequest req
            , HttpServletResponse res);
	public Object memberDetail(HttpServletRequest req
            , HttpServletResponse res);
	public Object memberList(HttpServletRequest req
            , HttpServletResponse res);		
*/	
}
