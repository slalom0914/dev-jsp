package com.example.demo.vo;
// VO패턴은 제네릭, 객체배열, Map
// 캡슐화 - private
// DataSet은 테이블 구조를 가짐 - UI/UX 바인딩
public class ZipCodeVO {
  private int    uid_no  =    0;
  private int    zipcode =    0;//                  
  private String zdo     = null;//                  
  private String sigu    = null;//                  
  private String dong    = null;//
  private String ri      = null;//
  private String bungi   = null;//
  private String aptname = null;//
  private String upd_date= null;//
  private String address = null;//  
  public int getUid_no() {
    return uid_no;
  }
  public void setUid_no(int uid_no) {
    this.uid_no = uid_no;
  }
  public int getZipcode() {
    return zipcode;
  }
  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }
  public String getZdo() {
    return zdo;
  }
  public void setZdo(String zdo) {
    this.zdo = zdo;
  }
  public String getSigu() {
    return sigu;
  }
  public void setSigu(String sigu) {
    this.sigu = sigu;
  }
  public String getDong() {
    return dong;
  }
  public void setDong(String dong) {
    this.dong = dong;
  }
  public String getRi() {
    return ri;
  }
  public void setRi(String ri) {
    this.ri = ri;
  }
  public String getBungi() {
    return bungi;
  }
  public void setBungi(String bungi) {
    this.bungi = bungi;
  }
  public String getAptname() {
    return aptname;
  }
  public void setAptname(String aptname) {
    this.aptname = aptname;
  }
  public String getUpd_date() {
    return upd_date;
  }
  public void setUpd_date(String upd_date) {
    this.upd_date = upd_date;
  }
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }
}
