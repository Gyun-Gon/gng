package com.ssafy.trip.member.model;

import java.sql.Date;

public class Member {
	private String userId;
	private String userName;
	private String userPassword;
	private String emailId;
	private String emailDomain;
	private Date joinDate;
	
	public Member() {}
	public Member(String userId, String userName, String userPassword, String emailId, String emailDomain,
			Date joinDate) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.emailId = emailId;
		this.emailDomain = emailDomain;
		this.joinDate = joinDate;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getEmailDomain() {
		return emailDomain;
	}
	public void setEmailDomain(String emailDomain) {
		this.emailDomain = emailDomain;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", emailId="
				+ emailId + ", emailDomain=" + emailDomain + ", joinDate=" + joinDate + "]";
	}
	
}