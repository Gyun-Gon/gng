package com.ssafy.trip.board.model;

public class Badword {
	int id;
	String content;
	
	public Badword() {}
	public Badword(int id, String content) {
		this.id = id;
		this.content = content;
	}	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return "Badword [id=" + id + ", content=" + content + "]";
	}
	
}
