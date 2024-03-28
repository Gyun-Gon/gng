package com.ssafy.trip.board.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.Comment;

public interface BoardDao {
	// 게시글
	
	// 등록
	int insertBoard(Board board) throws SQLException;
	
	// 전제조회
	List<Board> selectBoard(Map<String,Object> map) throws SQLException;
	// 상세조회
	Board selectBoardByBoardId(int boardId) throws SQLException;
	
	// 수정
	int updateBoard(Board board) throws SQLException;
	
	// 삭제
	int deleteBoard(int boardId) throws SQLException;
	
	// board_id에 대한 상세 조회수 증가
	int updateBoardViewCount(int boardId) throws SQLException;
	
	// 전체 글 수 가져오기
	int getTotalBoardCount(Map<String,Object> map) throws SQLException;
	
	// 내가 쓴 글 조회(userId) 상세 목록조회 -> 나중에
	
	
	// 댓글
	
	// 등록
	int insertComment(Comment comment) throws SQLException;
	
	// 상세(board_id에 대한) 전체목록조회
	List<Comment> selectCommentByBoardId(int boardId) throws SQLException;
	
	// 삭제
	int deleteComment(int commentId) throws SQLException;
	
}
