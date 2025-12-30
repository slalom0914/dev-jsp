package com.mvc;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HandlerMapping {
	static Logger log = Logger.getLogger(HandlerMapping.class);
	/***********************************************************
	 * 
	 * @param command - dept/deptInsert , emp/empDelete
	 *               , member/memberUpdate,,,
	 * @param res 
	 * @param req 
	 * @return String 이거나 ModelAndView
	 **********************************************************/
	public static Object getController(String command, HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException
	{
		Object controller = null;
		Object obj = null;
		String commands[] = command.split("/");
		//commands[0] => emp, dept, member
		//commands[1] => empInsert, deptUpdate, memberDelete
		if(commands.length == 2) {
			String work = commands[0];
			String methodName = commands[1];
			try {
				//부서관리인가?
				//개발자가 직접 코드로 인스턴스화를 하였다. 
				//- 객체관리 책임이 개발자에게 있다.: 의존성 주입이 아니다
				if("dept".equals(work)) {
					controller = new DeptController();
					obj = invokeMethod(controller, methodName, req, res);
				}
				//사원관리인가?
				else if("emp".equals(work)) {
					controller = new EmpController();
					obj = invokeMethod(controller, methodName, req, res);
				}
				//회원관리인가?
				else if("member".equals(work)) {
					controller = new MemberController();
					obj = invokeMethod(controller, methodName, req, res);
				}				
			}catch(Exception e) {
				log.error("메서드 호출 중 오류 발생 : " + e.getMessage());
				throw new ServletException("컨트롤러 메서드 호출 실패",e);
			}

		}
		return obj;
	}//end of getController
	/*********************************************************************
	 * Controller객체 안에 있는 methodName을 문자열로 찾아서 실행하는 메서드 구현(리플렉션API)
	 * 리플렉션을 사용하여 동적으로 메서드를 호출합니다.
	 * @param controller실행 대상(예: new DeptController())
	 * @param methodName 실행할 메서드 이름(예: deptInsert, deptList,,)
	 * @param request - 사용자가 보낸 요청 정보(파라미터/세션 등)
	 * @param response - 서버가 응답할 때 사용하는 객체(출력/리다이렉트 등)
	 * @return 각 메서드 실행 결과
	 ********************************************************************/
	private static Object invokeMethod(Object controller, String methodName
			,HttpServletRequest req, HttpServletResponse res) throws Exception
	{
		log.info(methodName + "메서드 호출");
/* 메서드 찾기 단계
 * controller.getClass(): 컨트롤러의 설계도(Class)를 얻기
 * getMethod(...): public 메서드 중에서 이름이 methodName이고
 * 파라미터가 HttpServletRequest, HttpServletResponse인 
 * 메서드를 찾아서 Method객체로 가져온다.		
 */
		Method method =controller.getClass().getMethod(methodName
				, HttpServletRequest.class, HttpServletResponse.class);
		log.info(method);
		//메서드 호출
		return method.invoke(controller, req, res);
	}//end of invokeMethod
	
	
}
