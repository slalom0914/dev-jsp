package com.example.demo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.util.DBConnectionMgr;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class EmpDao {
  DBConnectionMgr dbMgr = null;
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;

  public EmpDao() {
    dbMgr = DBConnectionMgr.getInstance();
  }

  public List<Map<String, Object>> empList(int deptno) {
    log.info("empList호출");
    List<Map<String, Object>> list = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT empno, ename, job FROM emp");
    sql.append(" WHERE deptno = "+deptno);
    try {
      conn = dbMgr.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql.toString());
      Map<String, Object> rmap = null;
      while (rs.next()) {
        log.info("while");
        rmap = new HashMap<>();
        rmap.put("empno", rs.getInt("empno"));
        rmap.put("ename", rs.getString("ename"));
        rmap.put("job", rs.getString("job"));
        list.add(rmap);
      }
      log.info(list);
    } catch (Exception e) {
      e.printStackTrace();
      dbMgr.freeConnection(conn, stmt, rs);
    }
    return list;
  }

  // insert here -> main
  public static void main(String[] args) {
    EmpDao mDao = new EmpDao();
    //mDao.empList();
  }
}
