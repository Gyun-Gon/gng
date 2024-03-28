package com.ssafy.trip.tour.model;

public class Search {
	private int contenTypeId;
	private double mapxLat;
	private double mapyLon;
	private int range;
	private int maxItems;
	public Search() {
		super();
	}
	public Search(int contenTypeId, double mapxLat, double mapyLon, int range, int maxItems) {
		super();
		this.contenTypeId = contenTypeId;
		this.mapxLat = mapxLat;
		this.mapyLon = mapyLon;
		this.range = range;
		this.maxItems = maxItems;
	}
	public int getContenTypeId() {
		return contenTypeId;
	}
	public void setContenTypeId(int contenTypeId) {
		this.contenTypeId = contenTypeId;
	}
	public double getMapxLat() {
		return mapxLat;
	}
	public void setMapxLat(double mapxLat) {
		this.mapxLat = mapxLat;
	}
	public double getMapyLon() {
		return mapyLon;
	}
	public void setMapyLon(double mapyLon) {
		this.mapyLon = mapyLon;
	}
	public int getRange() {
		return range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public int getMaxItems() {
		return maxItems;
	}
	public void setMaxItems(int maxItems) {
		this.maxItems = maxItems;
	}
	@Override
	public String toString() {
		return "Search [contenTypeId=" + contenTypeId + ", mapxLat=" + mapxLat + ", mapyLon=" + mapyLon + ", range="
				+ range + ", maxItems=" + maxItems + "]";
	}

	

}
