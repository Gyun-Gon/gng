package com.ssafy.trip.tour.model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.trip.member.util.DBUtil;
import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;

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

}