package com.example.demo.model2;

import java.util.Map;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class MemberService {
    MemberDao  memberDao = new MemberDao();
    public MemberService() {
        memberDao = new MemberDao();
    }

    // 로그인 시 Member 객체를 받아오는 메소드
    public Member loginMember(Map<String,Object> pmap) {
        Member m = memberDao.loginMember(pmap);
        // 만일 아이디와 비번이 존재하지 않거나 오타이거나 하면
        //m은 null인 상태이다.
        return m;
    }

    // ID 중복 체크를 위한 메소드
    public int dupIdChk(String id) {
        int result = memberDao.dupIdChk(id);
        return result;
    }

    // Member 객체를 추가하는 메소드
    public int insertMember(Member m) {
        int result = memberDao.insertMember(m);
        //result가 1이면 회원가입 성공 0이면 실패
        log.info("result : "+result);
        return result;
    }
    // 기존 Member 객체의 정보를 수정하는 메소드
    public int updateMember(Member m) {
        log.info("updateMember : "+m);
        int result = memberDao.updateMember(m);
        return result;
    }
    // 멤버 객체의 삭제를 요청하는 메소드
    public int deleteMember(String id) {
        int result = memberDao.deleteMember(id);
        return result;
    }
}