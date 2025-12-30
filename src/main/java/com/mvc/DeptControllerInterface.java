package com.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * 부서 관련 컨트롤러 인터페이스
 * 부서 관리에 필요한 메서드만 정의
 */
public interface DeptControllerInterface {
	public Object deptInsert(HttpServletRequest req, HttpServletResponse res);	
	public Object deptUpdate(HttpServletRequest req, HttpServletResponse res);
	public Object deptDelete(HttpServletRequest req, HttpServletResponse res);
	public Object deptDetail(HttpServletRequest req, HttpServletResponse res);
	public Object deptList(HttpServletRequest req, HttpServletResponse res);	
	public Object jsonDeptList(HttpServletRequest req, HttpServletResponse res);	
}
