package com.ssafy.trip.member.model.dao;

import java.sql.SQLException;

import com.ssafy.trip.member.model.Member;

public interface MemberDao {
	
	// c (회원가입)
	int insertMember(Member member) throws SQLException;
	
	// ReadOne (로그인)
	Member selectMemberByUserIdPassword(Member member) throws SQLException;
}
