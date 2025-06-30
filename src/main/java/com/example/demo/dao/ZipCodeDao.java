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
public class ZipCodeDao {
  DBConnectionMgr dbMgr = null;
  Connection conn = null;
  Statement stmt = null;
  ResultSet rs = null;
  public ZipCodeDao() {
    dbMgr = DBConnectionMgr.getInstance();
  }
  public List<Map<String,Object>> zipcodeList(String dong){
    List<Map<String,Object>> zlist = new ArrayList<>();
    StringBuilder sql = new StringBuilder();
    sql.append("SELECT zipcode, address");
    sql.append("  FROM zipcode_t ");
    sql.append("WHERE dong LIKE '"+dong+"'||'%'");
    try {
      conn = dbMgr.getConnection();
      stmt = conn.createStatement();
      rs = stmt.executeQuery(sql.toString());
      Map<String, Object> rmap = null;
      while (rs.next()) {
        log.info("while");
        rmap = new HashMap<>();
        rmap.put("zipcode", rs.getInt("zipcode"));
        rmap.put("address", rs.getString("address"));
        zlist.add(rmap);
      }
      log.info(zlist);
    } catch (Exception e) {
      e.printStackTrace();
      dbMgr.freeConnection(conn, stmt, rs);
    }
    return zlist;
  }
}
