package com.ssafy.trip.tour.model.service;

import java.util.List;

import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;

public interface AttractionInfoService {
	List<AttractionInfo> selectAttractionInfo(Search search) throws Exception;
}
