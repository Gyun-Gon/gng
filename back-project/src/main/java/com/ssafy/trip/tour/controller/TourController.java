package com.ssafy.trip.tour.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;
import com.ssafy.trip.tour.model.Sector;
import com.ssafy.trip.tour.model.service.AttractionInfoService;
import com.ssafy.trip.tour.model.service.AttractionInfoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/tour")
public class TourController extends HttpServlet {

	private static AttractionInfoService attractionInfoService = AttractionInfoServiceImpl
			.getAttractionInfoServiceImpl();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String action = req.getParameter("action");

		try {

			if (action == null) {
				index(req, resp);
			}

			switch (action) {
			case "tour":
				locateTour(req,resp);
				break;
			case "recommend":
				locateRecommend(req,resp);
				break;
			case "search":
				search(req, resp);
				break;
			case "searchRecommenCourse":
				searchRecommenCourse(req,resp);
				break;
			case "searchSector":
				searchSector(req, resp);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

	}

	private void locateRecommend(HttpServletRequest req, HttpServletResponse resp)throws Exception {
		req.getRequestDispatcher("/jsp/tour/recommend.jsp").forward(req, resp);
		
	}

	private void locateTour(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		req.getRequestDispatcher("/jsp/tour/tour.jsp").forward(req, resp);
	}

	private void searchRecommenCourse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();
		
		double mapxLat = Double.parseDouble(req.getParameter("mapxLat"));
		double mapyLon = Double.parseDouble(req.getParameter("mapyLon"));
		Search search = new Search();
		search.setMapxLat(mapxLat);
		search.setMapyLon(mapyLon);
		try {
			List<AttractionInfo> list = attractionInfoService.selectRecommenCourse(search);

			List<AttractionInfo> resList = new ArrayList<>();

			if (list.size() > 0) {
				for (AttractionInfo info : list) {
					System.out.println(info);
				}

				AttractionInfo currentInfo = list.get(0);
				resList.add(currentInfo);

				while (resList.size() < list.size()) {
					double minDistance = Double.MAX_VALUE;
					AttractionInfo nextInfo = null;

					for (AttractionInfo info : list) {
						if (!resList.contains(info)) {
							double distance = distance(currentInfo, info);
							if (distance < minDistance) {
								minDistance = distance;
								nextInfo = info;
							}
						}
					}

					resList.add(nextInfo);
					currentInfo = nextInfo;
				}
				System.out.println();
				for (AttractionInfo info : resList) {
					System.out.println(info);
				}
			} else {
				AttractionInfo tmp = new AttractionInfo();
				tmp.setLatitude(mapxLat);
				tmp.setLongitude(mapyLon);
				resList.add(tmp);
			}
			
			
			JSONArray recommendList = new JSONArray();
			for (AttractionInfo info : resList) {
				JSONObject attractionInfo = new JSONObject();
				attractionInfo.put("contentId", info.getContentId());
				attractionInfo.put("contentTypeId", info.getContentTypeId());
				attractionInfo.put("title", info.getTitle());
				attractionInfo.put("addr1", info.getAddr1());
				attractionInfo.put("addr2", info.getAddr2());
				attractionInfo.put("zipcode", info.getZipcode());
				attractionInfo.put("tel", info.getTel());
				attractionInfo.put("firstImage", info.getFirstImage());
				attractionInfo.put("firstImage2", info.getFirstImage2());
				attractionInfo.put("readcount", info.getReadcount());
				attractionInfo.put("sidoCode", info.getSidoCode());
				attractionInfo.put("gugunCode", info.getGugunCode());
				attractionInfo.put("latitude", info.getLatitude());
				attractionInfo.put("longitude", info.getLongitude());
				attractionInfo.put("mlevel", info.getMlevel());

				recommendList.put(attractionInfo);
			}

			result.put("result", true);
			result.put("item", recommendList);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("message", "지도 목록 조회 중 오류가 발생");
		}
		// 응답으로 JSON 형식의 결과 전송
		resp.setContentType("application/json; charset=UTF-8");
		out.print(result.toString());
	}

	private void searchSector(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();

		int sectorCode = Integer.parseInt(req.getParameter("sectorCode"));

		try {
			Sector sector = attractionInfoService.selectSectorCoordinate(sectorCode);
			JSONObject sectorInfo = new JSONObject();
			sectorInfo.put("id", sector.getId());
			sectorInfo.put("sectorCode", sector.getSectorCode());
			sectorInfo.put("latitude", sector.getLatitude());
			sectorInfo.put("longitude", sector.getLongitude());
			result.put("result", true);
			result.put("item", sectorInfo);
		} catch (Exception e) {
			result.put("result", false);
			result.put("message", "구역 목록 조회 중 오류 발생");
		}
		// 응답으로 JSON 형식의 결과 전송
		resp.setContentType("application/json; charset=UTF-8");
		out.print(result.toString());
	}

	private void search(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = resp.getWriter();
		JSONObject result = new JSONObject();

		// 클라이언트에서 전송된 검색 조건 파라미터를 받음
		int contentTypeId = Integer.parseInt(req.getParameter("contentTypeId"));
		double mapxLat = Double.parseDouble(req.getParameter("mapxLat"));
		double mapyLon = Double.parseDouble(req.getParameter("mapyLon"));
		int range = Integer.parseInt(req.getParameter("range"));
		int maxItems = Integer.parseInt(req.getParameter("maxItems"));
		Search search = new Search(contentTypeId, mapxLat, mapyLon, range, maxItems);
		System.out.println(search);

		try {
			List<AttractionInfo> list = attractionInfoService.selectAttractionInfo(search);

			JSONArray attractionList = new JSONArray();
			for (AttractionInfo info : list) {
				JSONObject attractionInfo = new JSONObject();
				attractionInfo.put("contentId", info.getContentId());
				attractionInfo.put("contentTypeId", info.getContentTypeId());
				attractionInfo.put("title", info.getTitle());
				attractionInfo.put("addr1", info.getAddr1());
				attractionInfo.put("addr2", info.getAddr2());
				attractionInfo.put("zipcode", info.getZipcode());
				attractionInfo.put("tel", info.getTel());
				attractionInfo.put("firstImage", info.getFirstImage());
				attractionInfo.put("firstImage2", info.getFirstImage2());
				attractionInfo.put("readcount", info.getReadcount());
				attractionInfo.put("sidoCode", info.getSidoCode());
				attractionInfo.put("gugunCode", info.getGugunCode());
				attractionInfo.put("latitude", info.getLatitude());
				attractionInfo.put("longitude", info.getLongitude());
				attractionInfo.put("mlevel", info.getMlevel());

				attractionList.put(attractionInfo);
			}

			result.put("result", true);
			result.put("item", attractionList);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("message", "지도 목록 조회 중 오류가 발생");
		}
		// 응답으로 JSON 형식의 결과 전송
		resp.setContentType("application/json; charset=UTF-8");
		out.print(result.toString());
	}

	private void index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

	private double distance(AttractionInfo a, AttractionInfo b) {
		double latDiff = a.getLatitude() - b.getLatitude();
		double lonDiff = a.getLongitude() - b.getLongitude();
		return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
	}

}
