package com.ssafy.trip.tour.model.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;
import com.ssafy.trip.tour.model.Sector;

public interface AttractionInfoDao {
	List<AttractionInfo> selectAttractionInfo(Search search) throws SQLException;
	List<AttractionInfo> selectRecommenCourse(Search search) throws SQLException;
	Sector selectSectorCoordinate(int sectorCode) throws SQLException;
}