package com.example.demo.model2;
/*
DevOps, 데이터파이프라인
  DDL
  create table member(
    id varchar2(10) constraints pk_member_id primary key,
    passwd varchar2(10) not null,
    name varchar2(30),
    email varchar2(50)
  )
  getter/setter 클래스 설계
  varchar2, varchar -> String
  number(7,2) 99999.99 -> double, float
  number(2) 99 -> stack memory overflow

  오라클, MySQL -> JAVA

  Lombok사용하면 생략한다

  JDBC API - MyBatis(SQL매핑오픈소스) - Hibernate(ORM- SQL문없음- Member)
  Hibernate 는 클래스 설계가 곧 테이블 설계이다.
  하나 일 때는 괜찮은데 테이블 두 개이상일 때는 JOIN -> 카타시안곱 -> 무한루프
  많은 부분이 생략됨 - 직관적이지 않다. - 눈에 안보임
 */
public class Member { //VO(value object), DTO(data transfer object)
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