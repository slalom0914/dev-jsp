package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.util.DBConnectionMgr;
import com.example.demo.vo.DeptVO;

import lombok.extern.log4j.Log4j2;

@Log4j2 //공통된 관심사
public class DeptDao {
  private DBConnectionMgr dbMgr = null;
  Connection conn = null;//오라클 서버 채널-mysql, sqlserver
  Statement stmt = null;//오라클 서버에 개발자 작성한 쿼리문 전달, 요청하기
  ResultSet rs = null;//오라클 옵티마이저가 조회한 결과를 커서를 조작해서 읽어오기
  public DeptDao(){
    dbMgr = DBConnectionMgr.getInstance();
  }
  public List<DeptVO> deptList(){
    log.info("deptList");
    List<DeptVO> dlist = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    try {
      sql.append("SELECT deptno, dname, loc");
      sql.append(" FROM dept");
      conn = dbMgr.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql.toString());
      DeptVO dvo = null;
      while (rs.next()) {
        dvo = new DeptVO();
        //dvo.setDeptno(rs.getInt(1));
        dvo.setDeptno(rs.getInt("deptno"));
        dvo.setDname(rs.getString("dname"));
        dvo.setLoc(rs.getString("loc"));
        dlist.add(dvo);
      }
    } catch (SQLException se){
      System.out.println(se.getMessage());
      System.out.println("[query]"+sql.toString());
    } catch (Exception e) {
      e.printStackTrace();//stack쌓인 에러 메시지와 라인번호 같이 출력
    }
    return dlist;
  }//end of deptList
}//end of DeptDao
