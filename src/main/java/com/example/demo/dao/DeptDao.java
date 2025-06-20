package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.vo.DeptVO;
import com.example.util.DBConnectionMgr;

import lombok.extern.log4j.Log4j2;

@Log4j2 //공통된 관심사
public class DeptDao {
  private DBConnectionMgr dbMgr = null;
  public DeptDao(){
    dbMgr = DBConnectionMgr.getInstance();
  }
  public List<DeptVO> deptList(){
    log.info("deptList");
    List<DeptVO> dlist = new ArrayList<>();
    try {
      
    } catch (Exception e) {
      // TODO: handle exception
    }
    return dlist;
  }//end of deptList
}//end of DeptDao
