package com.ssafy.trip.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.board.model.Badword;
import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.Comment;
import com.ssafy.trip.util.DBUtil;

public class BoardDaoImpl implements BoardDao {
	
	private static BoardDao boardDao;
	private BoardDaoImpl() {}
	public static BoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}

	@Override
	public int insertBoard(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into board\n");
			sql.append("(title, content, user_id)\n");
			sql.append("values(?,?,?)\n");
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setString(++index, board.getTitle());
			pstmt.setString(++index, board.getContent());
			pstmt.setString(++index, board.getUserId());
			
			return pstmt.executeUpdate();			
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public List<Board> selectBoard(Map<String,Object> map) throws SQLException {
		List<Board> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String key = (String)map.get("key");
		String word = (String)map.get("word");
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select board_id, title, content, view_count, user_id \n");
			sql.append("from board \n");
			if (!key.isEmpty() && !word.isEmpty()) {
				if (key.equals("title"))
					sql.append("where title like concat('%', ?, '%') \n");
				else
					sql.append("where ").append(key).append("= ? \n");
			}
			sql.append("order by board_id desc\n");
			sql.append("limit ?, ?");
			pstmt = conn.prepareStatement(sql.toString());	
			
			int index = 0;
			if (!key.isEmpty() && !word.isEmpty()) {
				pstmt.setString(++index, word);
			}
			System.out.println((int)map.get("start"));
			System.out.println((int)map.get("listSize"));
			pstmt.setInt(++index, (int)map.get("start"));
			pstmt.setInt(++index, (int)map.get("listSize"));		
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoardId(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViewCount(rs.getInt("view_count"));
				board.setUserId(rs.getString("user_id"));
				
				list.add(board);
			}
			
			return list;			
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}
	
	
	@Override
	public int getTotalBoardCount(Map<String, Object> map) throws SQLException {
		int cnt = 0;
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String key = (String)map.get("key");
		String word = (String)map.get("word");
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select count(board_id) \n");
			sql.append("from board \n");
			if (!key.isEmpty() && !word.isEmpty()) {
				if (key.equals("title"))
					sql.append("where title like concat('%', ?, '%') \n");
				else
					sql.append("where ").append(key).append("= ? \n");
			}
			
			pstmt = conn.prepareStatement(sql.toString());			
			if (!key.isEmpty() && !word.isEmpty()) {
				pstmt.setString(1, word);
			}
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt("count(board_id)");
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
		
		return cnt;
	}

	@Override
	public Board selectBoardByBoardId(int boardId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = new Board();
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from board \n");
			sql.append("where board_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setInt(++index, boardId);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board.setBoardId(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setViewCount(rs.getInt("view_count"));
				board.setUserId(rs.getString("user_id"));
			}
			
			return board;			
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public int updateBoard(Board board) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set title = ?, content = ? \n");
			sql.append("where board_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setString(++index, board.getTitle());
			pstmt.setString(++index, board.getContent());
			pstmt.setInt(++index, board.getBoardId());
			
			return pstmt.executeUpdate();
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public int updateBoardViewCount(int boardId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("update board \n");
			sql.append("set view_count = view_count + 1\n");
			sql.append("where board_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setInt(++index, boardId);
			
			return pstmt.executeUpdate();
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}
	
	
	@Override
	public int deleteBoard(int boardId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from board \n");
			sql.append("where board_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setInt(++index, boardId);
			
			return pstmt.executeUpdate();
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public int insertComment(Comment comment) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("insert into comment\n");
			sql.append("(content, user_id, board_id)\n");
			sql.append("values(?,?,?)\n");
			
			pstmt = conn.prepareStatement(sql.toString());
			int index = 0;
			pstmt.setString(++index, comment.getContent());
			pstmt.setString(++index, comment.getUserId());
			pstmt.setInt(++index, comment.getBoardId());
			
			return pstmt.executeUpdate();			
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public List<Comment> selectCommentByBoardId(int boardId) throws SQLException {
		List<Comment> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("select * \n");
			sql.append("from comment \n");
			sql.append("order by comment_id desc");
			sql.append("where board_id = ?");
			pstmt = conn.prepareStatement(sql.toString());			
			rs = pstmt.executeQuery();
			
			int index = 0;
			pstmt.setInt(++index, boardId);
			
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setCommentId(rs.getInt("comment_id"));
				comment.setContent(rs.getString("content"));
				comment.setUserId(rs.getString("user_id"));
				comment.setBoardId(rs.getInt("board_id"));
				
				list.add(comment);
			}
			
			return list;			
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

	@Override
	public int deleteComment(int commentId) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("delete from comment \n");
			sql.append("where comment_id = ? \n");
			pstmt = conn.prepareStatement(sql.toString());
			
			int index = 0;
			pstmt.setInt(++index, commentId);
			
			return pstmt.executeUpdate();
			
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}	


}
