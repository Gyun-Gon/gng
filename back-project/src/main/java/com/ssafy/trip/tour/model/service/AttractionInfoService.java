package com.ssafy.trip.tour.model.service;

import java.util.List;

import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;
import com.ssafy.trip.tour.model.Sector;

public interface AttractionInfoService {
	List<AttractionInfo> selectAttractionInfo(Search search) throws Exception;
	List<AttractionInfo> selectRecommenCourse(Search search) throws Exception;
	Sector selectSectorCoordinate(int sectorCode) throws Exception;
}
