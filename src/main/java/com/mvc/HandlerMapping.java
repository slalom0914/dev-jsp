package com.mvc;

import org.apache.log4j.Logger;

public class HandlerMapping {
	Logger log = Logger.getLogger(HandlerMapping.class);
	/***********************************************************
	 * 
	 * @param command - dept/deptInsert , emp/empDelete
	 *               , member/memberUpdate,,,
	 * @return
	 **********************************************************/
	public static Controller getController(String command) {
		Controller controller = null;
		String commands[] = command.split("/");
		//commands[0] => emp, dept, member
		//commands[1] => empInsert, deptUpdate, memberDelete
		if(commands.length == 2) {
			String work = commands[0];
			String methodName = commands[1];
			//부서관리인가?
			//개발자가 직접 코드로 인스턴스화를 하였다. 
			//- 객체관리 책임이 개발자에게 있다.: 의존성 주입이 아니다
			if("dept".equals(work)) {
				controller = new DeptController();
			}
			//사원관리인가?
			else if("emp".equals(work)) {
				controller = new EmpController();
			}
			//회원관리인가?
			else if("member".equals(work)) {
				controller = new MemberController();
			}
		}
		return controller;
	}
}
