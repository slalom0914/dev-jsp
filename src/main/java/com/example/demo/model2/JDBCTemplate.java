package com.example.demo.model2;

import lombok.extern.log4j.Log4j2;

import java.io.*;
import java.sql.*; 
import java.util.Properties; 
@Log4j2
public class JDBCTemplate { 
  public JDBCTemplate() {} 
  public static Connection getConnection() { 
    Connection con = null; 
    Properties prop = new Properties(); 
    try {
      // resources/driver.properties를 리소스 스트림으로 읽음
      InputStream input = JDBCTemplate.class.getClassLoader().getResourceAsStream("driver.properties");
      if (input == null) {
        throw new FileNotFoundException("driver.properties 파일을 classpath에서 찾을 수 없습니다.");
      }
      prop.load(input);
      Class.forName(prop.getProperty("driver")); 
      con = DriverManager.getConnection(prop.getProperty("url"), 
      prop.getProperty("user"), 
      prop.getProperty("pwd"));
      log.info(con);
    } catch (Exception e) { 
      e.printStackTrace(); 
    } 
    return con; 
  } 
  public static void close(Connection con) { 
    try { 
      if (con != null && !con.isClosed()) { con.close(); } 
    } catch (SQLException e) { e.printStackTrace(); } 
  } 
  public static void close(Statement stmt) { 
    try { 
      if (stmt != null && !stmt.isClosed()) { stmt.close(); } 
    } catch (SQLException e) { e.printStackTrace(); } 
  } 
  public static void close(ResultSet rset) { 
    try { 
      if (rset != null && !rset.isClosed()) { rset.close(); } 
    } catch (SQLException e) { e.printStackTrace(); } 
  } 
  public static void commit(Connection con) { 
    try { 
      if (con != null && !con.isClosed()) { con.commit(); }  
} catch (SQLException e) { e.printStackTrace(); } 
  } 
  public static void rollback(Connection con) { 
    try { 
      if (con != null && !con.isClosed()) { con.rollback();  } 
    } catch (SQLException e) { e.printStackTrace(); } 
  }

  public static void main(String[] args) {
    JDBCTemplate jdbcTemplate = new JDBCTemplate();
    Connection con = jdbcTemplate.getConnection();
    log.info(con);
  }
} 