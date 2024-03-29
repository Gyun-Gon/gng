package com.ssafy.trip.tour.model.service;

import java.util.List;

import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;
import com.ssafy.trip.tour.model.Sector;
import com.ssafy.trip.tour.model.dao.AttractionInfoDao;
import com.ssafy.trip.tour.model.dao.AttractionInfoDaoImpl;

public class AttractionInfoServiceImpl implements AttractionInfoService {
	
	public static AttractionInfoDao attractionInfoDao = AttractionInfoDaoImpl.getAttractionInfoDaoImpl();
	
	private static AttractionInfoService attractionInfoService;
	
	private AttractionInfoServiceImpl() {};
	
	public static AttractionInfoService getAttractionInfoServiceImpl() {
		if(attractionInfoService == null) {
			attractionInfoService = new AttractionInfoServiceImpl();
		}
		return attractionInfoService;
	}

	@Override
	public List<AttractionInfo> selectAttractionInfo(Search search) throws Exception {
		return attractionInfoDao.selectAttractionInfo(search);
	}

	@Override
	public Sector selectSectorCoordinate(int sectorCode) throws Exception {
		return attractionInfoDao.selectSectorCoordinate(sectorCode);
	}

	@Override
	public List<AttractionInfo> selectRecommenCourse(Search search) throws Exception {
		return attractionInfoDao.selectRecommenCourse(search);
	}

}
