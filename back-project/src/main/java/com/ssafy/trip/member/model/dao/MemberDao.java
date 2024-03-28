package com.ssafy.trip.member.model.dao;

import java.sql.SQLException;

import com.ssafy.trip.member.model.Member;

public interface MemberDao {
	
	// c (회원가입)
	int insertMember(Member member) throws SQLException;
	
	// ReadOne (로그인)
	Member selectMemberByUserIdPassword(Member member) throws SQLException;
	
	// 아이디 중복 검사
	boolean selectMemberByUserId (String userId) throws SQLException;
	
	// 회원 정보 수정
	int updateMember(Member member) throws SQLException;
	
	// 회원 삭제
	int deleteMember(String userId) throws SQLException;
}
