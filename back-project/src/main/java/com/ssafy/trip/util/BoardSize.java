package com.ssafy.trip.util;

public enum BoardSize {
	LIST(10),
	NAVIGATION(5);
	
	private int boardSize;
	
	private BoardSize(int boardSize) {
		this.boardSize = boardSize;
	}
	
	public int getBoardSize() {
		return boardSize;
	}
}
