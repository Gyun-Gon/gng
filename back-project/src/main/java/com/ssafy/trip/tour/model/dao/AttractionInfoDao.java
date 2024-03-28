package com.ssafy.trip.tour.model.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;

public interface AttractionInfoDao {
	List<AttractionInfo> selectAttractionInfo(Search search) throws SQLException;
	
}