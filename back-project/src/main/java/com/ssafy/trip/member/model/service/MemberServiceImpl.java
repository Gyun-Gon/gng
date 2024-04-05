package com.ssafy.trip.member.model.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		String password = member.getUserPassword();
		String hashedPassword = hashPassword(password);
		member.setUserPassword(hashedPassword);
		
		return memberDao.insertMember(member);		
	}

	@Override
	public Member loginMember(Member member) throws Exception {
		String password = member.getUserPassword();
		String hashedPassword = hashPassword(password);
		member.setUserPassword(hashedPassword);
		
		return memberDao.selectMemberByUserIdPassword(member);
	}
	@Override
	public boolean loginCheckMember(String userId) throws Exception {
		return memberDao.selectMemberByUserId(userId);
	}
	@Override
	public int editMember(Member member) throws Exception {
		String password = member.getUserPassword();
		String hashedPassword = hashPassword(password);
		member.setUserPassword(hashedPassword);
		
		return memberDao.updateMember(member);
	}
	@Override
	public int removeMember(String userId) throws Exception {
		return memberDao.deleteMember(userId);
	}
	
	public String hashPassword(String password) {
		
		String hashedPassword = "";
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");			
			byte[] hash = digest.digest(password.getBytes());
			StringBuilder hexString = new StringBuilder();
			
			for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            hashedPassword = hexString.toString();            
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		 		
		return hashedPassword;
	}

}
