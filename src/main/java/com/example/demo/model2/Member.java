package com.example.demo.model2;

public class Member { 
  private String id;//varchar2(10) 
  private String passwd;//varchar2(10)
  private String name;//varchar2(30)
  private String email;//varchar2(50) 
  public Member() {} 
  public Member(String id, String passwd, String name, String email) { 
    super(); 
    this.id = id; 
    this.passwd = passwd; 
    this.name = name; 
    this.email = email; 
  } 
  @Override  // toString() 재정의 
  public String toString() { 
    return "Member [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]"; 
  } 
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getPasswd() {
    return passwd;
  }
  public void setPasswd(String passwd) {
    this.passwd = passwd;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }
}