package com.ssafy.trip.board.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.board.model.service.BoardService;
import com.ssafy.trip.board.model.service.BoardServiceImpl;
import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.util.PageNavigation;
import com.ssafy.trip.util.ParameterCheck;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board")
public class BoardController extends HttpServlet{
	// 조회 2개 paging 처리(일단 난 1개)
	// 댓글 3개 ajax
	
	private BoardService boardService = BoardServiceImpl.getInstance();
	private int pageNo;
	private String key;
	private String word;
	private String queryString;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			String action = req.getParameter("action");
			
			pageNo = ParameterCheck.notNumberToOne(req.getParameter("pageNo"));
			key = ParameterCheck.nullToBlank(req.getParameter("key"));
			word = ParameterCheck.nullToBlank(req.getParameter("word"));
			
			queryString = "pageNo=" + pageNo + "&key=" + key + "&word=" + word;

			if (action == null) {
				index(req,resp);
			}
			
			switch(action) {
			case "getList" :
				getList(req,resp);
				break;
			case "getDetail" :
				getDetail(req,resp);
				break;
			case "remove" :
				remove(req,resp);
				break;
			case "registForm" :
				registForm(req,resp);
				break;
			case "regist" :
				regist(req,resp);
				break;
			case "edit" :
				edit(req,resp);
				break;
			case "editForm" :
				editForm(req,resp);
				break;
				
			}		
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
	}

	private void editForm(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("resultMember");
		if (member != null) {
			int boardId = Integer.parseInt(req.getParameter("boardId"));
			Board board = boardService.getDetailBoard(boardId);
			req.setAttribute("board", board);
			
			req.getRequestDispatcher("/jsp/board/boardEditForm.jsp").forward(req, resp);
			
		} else
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);

	}

	private void edit(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("resultMember");
		if (member != null) {
			PrintWriter out = null;
		    JSONObject result = null;
		    String jsonString = null;
		    StringBuilder jsonBuilder = null;
		    JSONObject jsonObject = null;
		    BufferedReader reader = null;
			
		    // 처리해서 front에 JSON형식으로 보내주겠다. 이전처럼 jsp로 페이지 이동하지 않고.
			resp.setContentType("application/x-json; charset=UTF-8");
	        out = resp.getWriter();
	        result = new JSONObject(); // Result라는 JSON객체로 보내준다.

	        // front에서 온 요청 (fetch) 처리 작업
	        // JSON 문자열로 왔으니까 JSON 문자열을 읽어들이는 작업
	        reader = req.getReader();
	        jsonBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonBuilder.append(line);
	        }
	        jsonString = jsonBuilder.toString();
	        // JSON 문자열에 있는 값들을 써야하므로 다시 JSON 객체로 변환
	        System.out.println(jsonString);
	        jsonObject = new JSONObject(jsonString);

	        // 내가 필요한 boardId값 JSON 객체에서 가져오기
	        int boardId = jsonObject.getInt("boardId");
	        String title = jsonObject.getString("title");
	        String content = jsonObject.getString("content");
	        Board board = new Board();
	        board.setBoardId(boardId);
	        board.setTitle(title);
	        board.setContent(content);
	        
	        int editResult = boardService.editBoard(board);
	        System.out.println("editResult : " + editResult);
	        if (editResult > 0) {
	        	result.put("result", true); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 
	        } else {
	        	result.put("result", false); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 	        	
	        }			
	        out.print(result.toString()); // JSON 문자열로 보내야하므로 Result JSON객체 -> JSON 문자열
			
		} else
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
		
	}

	private void regist(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("resultMember");
		if (member != null) {
			PrintWriter out = null;
		    JSONObject result = null;
		    String jsonString = null;
		    StringBuilder jsonBuilder = null;
		    JSONObject jsonObject = null;
		    BufferedReader reader = null;
			
		    // 처리해서 front에 JSON형식으로 보내주겠다. 이전처럼 jsp로 페이지 이동하지 않고.
			resp.setContentType("application/x-json; charset=UTF-8");
	        out = resp.getWriter();
	        result = new JSONObject(); // Result라는 JSON객체로 보내준다.

	        // front에서 온 요청 (fetch) 처리 작업
	        // JSON 문자열로 왔으니까 JSON 문자열을 읽어들이는 작업
	        reader = req.getReader();
	        jsonBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonBuilder.append(line);
	        }
	        jsonString = jsonBuilder.toString();
	        // JSON 문자열에 있는 값들을 써야하므로 다시 JSON 객체로 변환
	        System.out.println(jsonString);
	        jsonObject = new JSONObject(jsonString);

	        // 내가 필요한 boardId값 JSON 객체에서 가져오기
	        String title = jsonObject.getString("title");
	        String content = jsonObject.getString("content");
	        String userId = member.getUserId();
	        Board board = new Board();
	        board.setTitle(title);
	        board.setContent(content);
	        board.setUserId(userId);
	        
	        int insertResult = boardService.registBoard(board);
	        System.out.println("insertResult : " + insertResult);
	        if (insertResult > 0) {
	        	result.put("result", true); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 
	        } else {
	        	result.put("result", false); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 	        	
	        }			
	        out.print(result.toString()); // JSON 문자열로 보내야하므로 Result JSON객체 -> JSON 문자열
			
		} else
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
	}

	private void registForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/board/boardRegistForm.jsp").forward(req, resp);
	}

	private void remove(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("resultMember");
		if (member != null) {
			PrintWriter out = null;
		    JSONObject result = null;
		    String jsonString = null;
		    StringBuilder jsonBuilder = null;
		    JSONObject jsonObject = null;
		    BufferedReader reader = null;
			
		    // 처리해서 front에 JSON형식으로 보내주겠다. 이전처럼 jsp로 페이지 이동하지 않고.
			resp.setContentType("application/x-json; charset=UTF-8");
	        out = resp.getWriter();
	        result = new JSONObject(); // Result라는 JSON객체로 보내준다.

	        // front에서 온 요청 (fetch) 처리 작업
	        // JSON 문자열로 왔으니까 JSON 문자열을 읽어들이는 작업
	        reader = req.getReader();
	        jsonBuilder = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonBuilder.append(line);
	        }
	        jsonString = jsonBuilder.toString();
	        // JSON 문자열에 있는 값들을 써야하므로 다시 JSON 객체로 변환
	        System.out.println(jsonString);
	        jsonObject = new JSONObject(jsonString);

	        // 내가 필요한 boardId값 JSON 객체에서 가져오기
	        int boardId = jsonObject.getInt("boardId");
	        System.out.println(boardId);
	        
	        int removeResult = boardService.removeBoard(boardId);
	        if (removeResult > 0) {
	        	result.put("result", true); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 
	        } else {
	        	result.put("result", false); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 	        	
	        }			
	        out.print(result.toString()); // JSON 문자열로 보내야하므로 Result JSON객체 -> JSON 문자열
			
		} else
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
	}

	private void getDetail(HttpServletRequest req, HttpServletResponse resp) throws Exception {	
		
		HttpSession session = req.getSession();
		Member member = (Member) session.getAttribute("resultMember");
		if (member != null) {
			int boardId = Integer.parseInt(req.getParameter("boardId"));
			try {
				Board board = boardService.getDetailBoard(boardId);
				req.setAttribute("board", board);
				
				System.out.println("queryString : " + queryString);
				req.getRequestDispatcher("/jsp/board/boardDetailForm.jsp?" + queryString).forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
				index(req,resp);
			}
		} else
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
	}

	private void index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);		
	}

	private void getList(HttpServletRequest req, HttpServletResponse resp) throws Exception{
		HttpSession httpSession = req.getSession();
		Member resultMember = (Member) httpSession.getAttribute("resultMember");
		if (resultMember != null) {
			try {
				Map<String,Object> map = new HashMap<>();
				map.put("pageNo", pageNo); // 현재 page
				map.put("key", key);
				map.put("word", word);
				System.out.println(map.get("pageNo"));
				System.out.println(map.get("key"));
				System.out.println(map.get("word"));
				
				req.setAttribute("pageNo", pageNo);
//				req.setAttribute("key", key);
//				req.setAttribute("word", word);
				
				// 글 목록 처리(리스트에 뭐 띄울지)
				List<Board> list = boardService.getListBoard(map);
				req.setAttribute("list", list);
				
				// 페이징 처리(밑에 이전,다음 처리랑 페이지 숫자들 data)
				PageNavigation pageNavigation = boardService.makePageNavigation(map);
				req.setAttribute("navigation", pageNavigation);
				
				// 최종 출력 jsp 파일(여기로 넘겨줌)
				req.getRequestDispatcher("/jsp/board/boardListForm.jsp").forward(req, resp);
				
			} catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("/error.jsp").forward(req, resp);
			}
		} else {
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
		}
		
		
		
		
		
	}
	
	
	
	
	
}
