package com.ssafy.trip.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.board.model.Badword;
import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.Comment;
import com.ssafy.trip.util.PageNavigation;

public interface BoardService {
	// 게시글
	int registBoard(Board board) throws Exception;
	
	List<Board> getListBoard(Map<String,Object> map) throws Exception;
	Board getDetailBoard(int boardId) throws Exception;
	
	int editBoard(Board board) throws Exception;
	
	int removeBoard(int boardId) throws Exception;
		
	
	// 댓글
	List<Comment> registComment(Comment comment) throws Exception;
	
	List<Comment> getDetailComment(int boardId) throws Exception;
	
	List<Comment> removeComment(Comment comment) throws Exception;
	
	// navigation
	PageNavigation makePageNavigation(Map<String,Object> map) throws Exception;
}
