package com.example.demo.model2;

import com.example.demo.util.DBConnectionMgr;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class MemberDao {
    DBConnectionMgr dbMgr = null;
    Connection con = null;
    public MemberDao() {
        dbMgr = DBConnectionMgr.getInstance();
    }
// 사용자 로그인 메소드 - 조회된 정보는 보안상 중요함.  세션에 저장하기
// TypeScript사용하게 됨.
// session.setAttribute("s_id","kiwi");
// String sid = (String)session.getAttribute("s_id");
// 매개변수로 받은 ID와 PASSWD로 DataBase에 접속하여 일치하는 데이터 조회
// 파라미터는 사용자가 화면에서 입력한 값들이 전달되는 곳이다.
// 사용자가 화면에서 입력한 값을 받아오기
// String id = req.getParameter("id") - 사용자가 입력한 값 청취하는데 서블릿
// String passwd = req.getParameter("passwd")
    public Member loginMember(String id, String passwd) {
        log.info(id + ", "+ passwd);
        Member m = null;
        con = dbMgr.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        //부적합한 식별자 - 테이블에 선언된 컬럼명과 다른 경우 발생함.
        String query = "SELECT id,passwd,name,email FROM MEMBER0630 WHERE ID = ? AND PASSWD = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);  // 첫번째 ‘?’ 에 id 값 대입
            pstmt.setString(2, passwd);  // 두번째 ‘?’ 에 passwd 값 대입
            rset = pstmt.executeQuery();
            if(rset.next()) {
                m = new Member();
                m.setId(rset.getString("id"));  // 받아온 ID 컬럼 값을 member변수에 대입
                m.setPasswd(rset.getString("passwd"));
                m.setName(rset.getString("name"));
                m.setEmail(rset.getString("email"));
            }else{
                log.info("아이디와 비번이 틀렸거나 존재하지 않을 때");
                //조회 결과가 존재하지 않을 때 m은 어떻게 되는가?
                //m = null;NullPointerException
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {  // ResultSet과 PreparedStatement 리소스를 반환
            dbMgr.freeConnection(con, pstmt, rset);
        }
        return m;  // 조회하여 가져온 Member 객체를 반환한다
    }
    // ID 값의 중복을 조회하는 메소드
    public int dupIdChk(String id) {
        con = dbMgr.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        int result = 0;
        // id로 테이블을 조회하여 있으면 1 이상, 없으면 0인 쿼리 작성
        String query = "SELECT COUNT(*) FROM KH_MEMBER WHERE ID = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            rset = pstmt.executeQuery();
            if(rset.next()) {
                result = rset.getInt(1);   // rset의 첫 컬럼의 숫자값을 가져온다
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbMgr.freeConnection(con, pstmt, rset);
        }
        return result;
    }
    // DataBase에 Member 객체를 추가하는 메소드
    public int insertMember(Member m) {
        Connection con = dbMgr.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "INSERT INTO KH_MEMBER VALUES(?, ?, ?, ?)";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, m.getId());
            pstmt.setString(2, m.getPasswd());
            pstmt.setString(3, m.getName());
            pstmt.setString(4, m.getEmail());
            // executeupdate() 는 실행 결과를 반영된 행의 개수로 리턴하므로
            // 1 이상은 실행 성공, 0 이하(구문 에러 포함)는 실패이다.
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbMgr.freeConnection(con, pstmt);
        }
        return result;
    }
    // 기존 사용자의 정보를 수정하여 DataBase의 데이터를 수정하는 메소드
    public int updateMember(Member m) {
        Connection con = dbMgr.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "UPDATE KH_MEMBER SET password = ?, NAME = ?, EMAIL = ? WHERE ID = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, m.getPasswd());
            pstmt.setString(2, m.getName());
            pstmt.setString(3, m.getEmail());
            pstmt.setString(4, m.getId());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbMgr.freeConnection(con, pstmt);
        }
        return result;
    }
    // 기존 사용자를 DataBase에서 삭제하는 메소드
    public int deleteMember(String id) {
        Connection con = dbMgr.getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        String query = "DELETE FROM KH_MEMBER WHERE ID = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbMgr.freeConnection(con, pstmt);
        }
        return result;
    }
}