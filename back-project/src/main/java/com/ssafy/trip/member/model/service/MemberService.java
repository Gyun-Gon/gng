package com.ssafy.trip.member.model.service;

import java.sql.SQLException;

import com.ssafy.trip.member.model.Member;

public interface MemberService {
	
	int joinMember(Member member) throws Exception;
	
	Member loginMember (Member member) throws Exception;
	
	boolean loginCheckMember(String userId) throws Exception;
	
	int editMember(Member member) throws Exception;
	
	int removeMember(String userId) throws Exception;
}
