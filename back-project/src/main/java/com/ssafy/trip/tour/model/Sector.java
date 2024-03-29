package com.ssafy.trip.tour.model;

public class Sector {
	private int id;
	private int sectorCode;
	private double latitude;
	private double longitude;
	public Sector() {
		super();
	}
	public Sector(int id, int sectorCode, double latitude, double longitude) {
		super();
		this.id = id;
		this.sectorCode = sectorCode;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSectorCode() {
		return sectorCode;
	}
	public void setSectorCode(int sectorCode) {
		this.sectorCode = sectorCode;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	@Override
	public String toString() {
		return "Sector [id=" + id + ", sectorCode=" + sectorCode + ", latitude=" + latitude + ", longitude=" + longitude
				+ "]";
	}
	
}
