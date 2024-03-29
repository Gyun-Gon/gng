package com.ssafy.trip.tour.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.util.DBUtil;
import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;
import com.ssafy.trip.tour.model.Sector;

public class AttractionInfoDaoImpl implements AttractionInfoDao {

	private static AttractionInfoDao attractionInfoDao;

	private AttractionInfoDaoImpl() {
	};

	public static AttractionInfoDao getAttractionInfoDaoImpl() {
		if (attractionInfoDao == null) {
			attractionInfoDao = new AttractionInfoDaoImpl();
		}
		return attractionInfoDao;
	}

	@Override
	public List<AttractionInfo> selectAttractionInfo(Search search) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttractionInfo> list = new ArrayList<>();
		/*
		 * SELECT
		 *
		 * FROM attraction_info WHERE
		 * (6371*acos(cos(radians(36.318852))*cos(radians(latitude))*cos(radians(
		 * longitude) -
		 * radians(127.428056))+sin(radians(36.318852))*sin(radians(latitude)))) < 10
		 * and content_type_id =39 order by rand() limit 0, 30;
		 * 
		 */

		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select * ");
			sql.append(" from attraction_info ");
			sql.append(" where (6371*acos(cos(radians( ? ))*cos(radians(latitude))*cos(radians(longitude) ");
			sql.append(" - radians( ? ))+sin(radians( ? ))*sin(radians(latitude)))) < ?  ");
			sql.append(" and content_type_id = ? ");
			sql.append(" order by rand() ");
			sql.append(" limit 0, ? ");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setDouble(++idx, search.getMapxLat());
			pstmt.setDouble(++idx, search.getMapyLon());
			pstmt.setDouble(++idx, search.getMapxLat());
			pstmt.setInt(++idx, search.getRange());
			pstmt.setInt(++idx, search.getContenTypeId());
			pstmt.setInt(++idx, search.getMaxItems());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AttractionInfo attractionInfo = new AttractionInfo();
				list.add(new AttractionInfo(rs.getInt("content_id"), rs.getInt("content_type_id"),
						rs.getString("title"), rs.getString("addr1"), rs.getString("addr2"), rs.getString("zipcode"),
						rs.getString("tel"), rs.getString("first_image"), rs.getString("first_image2"),
						rs.getInt("readcount"), rs.getInt("sido_code"), rs.getInt("gugun_code"),
						rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("mlevel")));
			}
			return list;
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}
	
	@Override
	public List<AttractionInfo> selectRecommenCourse(Search search) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AttractionInfo> list = new ArrayList<>();
		/*
		
		SELECT *
		FROM (
    	SELECT *, ROW_NUMBER() OVER(PARTITION BY content_type_id ORDER BY RAND()) as row_num
    	FROM attraction_info
    	WHERE (6371*acos(cos(radians(36.3188))*cos(radians(latitude))*cos(radians(longitude) 
    	- radians(127.428056))+sin(radians(36.318852))*sin(radians(latitude)))) < 1
    	AND content_type_id IN (12,14,15,38,39)
		) AS ranked_attractions
		WHERE row_num = 1
		order by content_type_id;
		
		 */
		
		
		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select * ");
			sql.append(" FROM ( ");
			sql.append(" SELECT *, ROW_NUMBER() OVER(PARTITION BY content_type_id ORDER BY RAND()) as row_num ");
			sql.append(" FROM attraction_info  ");
			sql.append(" WHERE (6371*acos(cos(radians(?))*cos(radians(latitude))*cos(radians(longitude)  ");
			sql.append(" - radians(?))+sin(radians(?))*sin(radians(latitude)))) < 1  ");
			sql.append(" AND content_type_id IN (12,14,15,38,39)  ");
			sql.append(" ) AS ranked_attractions  ");
			sql.append(" WHERE row_num = 1  ");
			sql.append(" order by content_type_id  ");
	
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setDouble(++idx, search.getMapxLat());
			pstmt.setDouble(++idx, search.getMapyLon());
			pstmt.setDouble(++idx, search.getMapxLat());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				AttractionInfo attractionInfo = new AttractionInfo();
				list.add(new AttractionInfo(rs.getInt("content_id"), rs.getInt("content_type_id"),
						rs.getString("title"), rs.getString("addr1"), rs.getString("addr2"), rs.getString("zipcode"),
						rs.getString("tel"), rs.getString("first_image"), rs.getString("first_image2"),
						rs.getInt("readcount"), rs.getInt("sido_code"), rs.getInt("gugun_code"),
						rs.getDouble("latitude"), rs.getDouble("longitude"), rs.getString("mlevel")));
			}
			return list;
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}


	@Override
	public Sector selectSectorCoordinate(int sectorCode) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DBUtil.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" select * ");
			sql.append(" from recommend ");
			sql.append(" where sector_code = ? ");
			sql.append(" order by rand() ");
			sql.append(" limit 1 ");
			pstmt = conn.prepareStatement(sql.toString());
			int idx = 0;
			pstmt.setInt(++idx, sectorCode);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Sector(rs.getInt("id"), rs.getInt("sector_code"), rs.getDouble("latitude"),
						rs.getDouble("longitude"));
			}
			return null;
		} finally {
			DBUtil.getInstance().close(pstmt, conn);
		}
	}

}