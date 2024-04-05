package com.ssafy.trip.member.model.service;

import com.ssafy.trip.member.model.Member;

public interface MemberService {
	
	int joinMember(Member member) throws Exception;
	
	Member loginMember (Member member) throws Exception;
}
