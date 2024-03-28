package com.ssafy.trip.tour.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.member.model.service.MemberService;
import com.ssafy.trip.member.model.service.MemberServiceImpl;
import com.ssafy.trip.tour.model.AttractionInfo;
import com.ssafy.trip.tour.model.Search;
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
			case "search":
				search(req, resp);
				break;
			case "signInForm":
				signInForm(req, resp);
				break;
			case "signOut":
				signOut(req, resp);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}

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

	private void signOut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession httpsession = req.getSession();
		httpsession.invalidate();

		resp.sendRedirect(req.getContextPath() + "/member");
	}

	private void signInForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
	}

	private void index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);
	}

}
