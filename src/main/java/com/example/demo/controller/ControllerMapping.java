package com.example.demo.controller;
//pagePath[2] = 메서드 이름
//주문관리  - OrderController - OrderService - OrderDao(Data Access Object) 
// - Oracle, MySql, SQLServer, DB2, Infomix, ,,,,
//회원관리 - MemberController - MemberService - MemberDao
//게시판 - BoardController - BoardService - BoardDao
//XXXService +  XXXDao = 모델계층( data )
public class ControllerMapping {
  /*******************************************************
   * 
   * @param command - api떼고 가져온다. 
   * 예) dept/crudDept, emp/crudEmp, board/crudBoard
   * @return
   ******************************************************/
  // 업무이름 - 폴더이름, 메서드 이름
  public static Controller getController(String command){
    Controller controller = null;
    String[] commands = command.split("/");
    //업무이름에 따라서 XXXController결정된다.
    String workname = commands[0];//업무이름 - 폴더 이름
    //TO DO - req, res
    String methodName = commands[1];//메서드이름
    if("member".equals(workname)){
      controller = new MemberController(methodName);
    }
    else if("board".equals(workname)){
      controller = new BoardController(methodName);
    }
    else if("common".equals(workname)){
      controller = new CommonController(methodName);
    }
    return controller;
  }
}
