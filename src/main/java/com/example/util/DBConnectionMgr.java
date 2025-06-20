package com.example.util;

import java.sql.*;
public class DBConnectionMgr {
    private static DBConnectionMgr dbMgr = null;
    private final String _DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String _URL = "jdbc:oracle:thin:@localhost:1521:orcl11";
    private final String _USER = "scott";
    private final String _PASSWORD = "tiger";
    private DBConnectionMgr() {
        try {
            Class.forName(_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't load JDBC driver");
        }
    }//end of 디폴트 생성자 - 접근제한자를 private함
    public static synchronized DBConnectionMgr getInstance() {
        if (dbMgr == null) {
            dbMgr = new  DBConnectionMgr();
        }
        return dbMgr;
    }//외부에서 객체 주입을 받을 때 호출하는 메서드
    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(_URL, _USER, _PASSWORD);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }//end of getConnection
    public void freeConnection(Connection conn, Statement stmt, ResultSet rs){
        try {
            if(rs != null){
                rs.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

