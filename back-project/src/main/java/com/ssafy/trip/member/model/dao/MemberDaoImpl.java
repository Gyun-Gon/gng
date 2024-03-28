package com.ssafy.trip.member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.util.DBUtil;

public class MemberDaoImpl implements MemberDao {
	
	private static MemberDao memberDao;
	private MemberDaoImpl() {}
	public static MemberDao getInstance() {
		if (memberDao == null) {
			memberDao = new MemberDaoImpl();
		}
		return memberDao;
	}

	@Override
	public int insertMember(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into members\n");
			sql.append("(user_id, user_name, user_password, user_email)\n");
			sql.append("values(?,?,?,?)\n");
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setString(++index, member.getUserId());
			pstmt.setString(++index, member.getUserName());
			pstmt.setString(++index, member.getUserPassword());
			pstmt.setString(++index, member.getUserEmail());
			
			return pstmt.executeUpdate();			
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public Member selectMemberByUserIdPassword(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select *\n");
			sql.append("from members\n");
			sql.append("where user_id = ? and user_password = ?\n");
			
			pstmt = conn.prepareStatement(sql.toString());			
			int index = 0;
			pstmt.setString(++index, member.getUserId());
			pstmt.setString(++index, member.getUserPassword());
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Member resultMember = new Member();
				resultMember.setUserId(rs.getString("user_id"));
				resultMember.setUserName(rs.getString("user_name"));
				resultMember.setUserPassword(rs.getString("user_password"));
				resultMember.setUserEmail(rs.getString("user_email"));
				resultMember.setJoinDate(rs.getDate("join_date"));
				return resultMember;
			}
			
			return null;
			
		} finally {
			DBUtil.getInstance().close(pstmt,conn);
		}
	}
	
	@Override
	public boolean selectMemberByUserId(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select *\n");
			sql.append("from members\n");
			sql.append("where user_id = ?\n");
			
			pstmt = conn.prepareStatement(sql.toString());			
			int index = 0;
			pstmt.setString(++index, userId);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			
			return false;
			
		} finally {
			DBUtil.getInstance().close(pstmt,conn);
		}
	}
	@Override
	public int updateMember(Member member) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update members \n");
			sql.append("set user_name = ?, user_password = ?, user_email = ? \n");
			sql.append("where user_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setString(++index, member.getUserName());
			pstmt.setString(++index, member.getUserPassword());
			pstmt.setString(++index, member.getUserEmail());
			pstmt.setString(++index, member.getUserId());
			
			return pstmt.executeUpdate();
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}
	@Override
	public int deleteMember(String userId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from members \n");
			sql.append("where user_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setString(++index, userId);
			
			return pstmt.executeUpdate();
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

}
