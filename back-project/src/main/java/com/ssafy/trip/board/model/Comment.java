package com.ssafy.trip.board.model;

public class Comment {
	int commentId;
	String content;
	String userId;
	int boardId;
	
	public Comment() {}
	public Comment(int commentId, String content, String userId, int boardId) {
		this.commentId = commentId;
		this.content = content;
		this.userId = userId;
		this.boardId = boardId;
	}
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", content=" + content + ", userId=" + userId + ", boardId="
				+ boardId + "]";
	}
	
}
