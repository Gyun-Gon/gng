package com.ssafy.trip.board.model;

public class Board {
	int boardId;
	String title;
	String content;
	int viewCount;
	String userId;
	
	public Board() {}
	public Board(int boardId, String title, String content, int viewCount, String userId) {
		super();
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.viewCount = viewCount;
		this.userId = userId;
	}
	
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Override
	public String toString() {
		return "Board [boardId=" + boardId + ", title=" + title + ", content=" + content + ", viewCount=" + viewCount
				+ ", userId=" + userId + "]";
	}
	
}
