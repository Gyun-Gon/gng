package com.ssafy.trip.board.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.Comment;
import com.ssafy.trip.board.model.dao.BoardDao;
import com.ssafy.trip.board.model.dao.BoardDaoImpl;
import com.ssafy.trip.util.BoardSize;
import com.ssafy.trip.util.PageNavigation;

public class BoardServiceImpl implements BoardService {
	
	BoardDao boardDao = BoardDaoImpl.getInstance();
	
	private static BoardService boardService;
	private BoardServiceImpl() {}
	public static BoardService getInstance() {
		if (boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}

	@Override
	public int registBoard(Board board) throws Exception {
		return boardDao.insertBoard(board);
	}

	// 원래는 이렇게 db와 관련없는 data들은 dto 새로 만들어서 전송하는게 일반적이지만,
	// 지금은 간단하게 map으로 구현해봄.
	@Override
	public List<Board> getListBoard(Map<String,Object> map) throws Exception {
		int pageNo = (int) map.get("pageNo");
		int listSize = BoardSize.LIST.getBoardSize();
		int start = (pageNo - 1) * listSize;
		
		map.put("start", start);
		map.put("listSize", listSize);
		
		return boardDao.selectBoard(map);
	}
	
	@Override
	public PageNavigation makePageNavigation(Map<String, Object> map) throws Exception {
		PageNavigation pageNavigation = new PageNavigation();
		int listSize = BoardSize.LIST.getBoardSize();
		
		int currentPage = (int)map.get("pageNo");
		int naviSize = BoardSize.NAVIGATION.getBoardSize();
		pageNavigation.setCurrentPage(currentPage);
		pageNavigation.setNaviSize(naviSize);
		int totalCount = boardDao.getTotalBoardCount(map);
		pageNavigation.setTotalCount(totalCount);
		int totalPageCount = (totalCount-1) / listSize + 1;
		pageNavigation.setTotalPageCount(totalPageCount);
		boolean startRange = currentPage <= naviSize;
		boolean endRange = currentPage > (totalPageCount - 1) / naviSize * naviSize;
		pageNavigation.setStartRange(false);
		pageNavigation.setEndRange(false);
		
		pageNavigation.makeNavigator();		
		return pageNavigation;
	}

	@Override
	public Board getDetailBoard(int boardId) throws Exception {
		boardDao.updateBoardViewCount(boardId);
		return boardDao.selectBoardByBoardId(boardId);
	}

	@Override
	public int editBoard(Board board) throws Exception {
		return boardDao.updateBoard(board);
	}

	@Override
	public int removeBoard(int boardId) throws Exception {
		return boardDao.deleteBoard(boardId);
	}

	@Override
	public List<Comment> registComment(Comment comment) throws Exception {
		int insertResult = boardDao.insertComment(comment);
		return getDetailComment(comment.getBoardId());
	}

	@Override
	public List<Comment> getDetailComment(int boardId) throws Exception {
		return boardDao.selectCommentByBoardId(boardId);
	}

	@Override
	public List<Comment> removeComment(Comment comment) throws Exception {
		int removeResult = boardDao.deleteComment(comment.getCommentId());
		return getDetailComment(comment.getBoardId());
	}

}
