package com.ssafy.trip.member.model.service;

import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.member.model.dao.MemberDao;
import com.ssafy.trip.member.model.dao.MemberDaoImpl;

public class MemberServiceImpl implements MemberService {
	
	MemberDao memberDao = MemberDaoImpl.getInstance();
	
	private static MemberService memberService;
	private MemberServiceImpl() {}
	public static MemberService getInstance() {
		if (memberService == null) {
			memberService = new MemberServiceImpl();
		}
		return memberService;
	}

	@Override
	public int joinMember(Member member) throws Exception {
		return memberDao.insertMember(member);
	}

	@Override
	public Member loginMember(Member member) throws Exception {
		return memberDao.selectMemberByUserIdPassword(member);
	}
	@Override
	public boolean loginCheckMember(String userId) throws Exception {
		return memberDao.selectMemberByUserId(userId);
	}
	@Override
	public int editMember(Member member) throws Exception {
		return memberDao.updateMember(member);
	}
	@Override
	public int removeMember(String userId) throws Exception {
		return memberDao.deleteMember(userId);
	}

}
