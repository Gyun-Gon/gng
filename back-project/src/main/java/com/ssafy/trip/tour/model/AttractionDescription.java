package com.ssafy.trip.tour.model;

import java.sql.Date;

public class AttractionDescription {
	private int contentId;
	private String homepage;
	private String overview;
	private String telname;
	
	public AttractionDescription() {}

	public AttractionDescription(int contentId, String homepage, String overview, String telname) {
		super();
		this.contentId = contentId;
		this.homepage = homepage;
		this.overview = overview;
		this.telname = telname;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getOverview() {
		return overview;
	}

	public void setOverview(String overview) {
		this.overview = overview;
	}

	public String getTelname() {
		return telname;
	}

	public void setTelname(String telname) {
		this.telname = telname;
	}

	@Override
	public String toString() {
		return "AttractionDescription [contentId=" + contentId + ", homepage=" + homepage + ", overview=" + overview
				+ ", telname=" + telname + "]";
	}

}