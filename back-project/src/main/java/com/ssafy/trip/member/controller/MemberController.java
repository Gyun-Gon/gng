package com.ssafy.trip.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import org.json.JSONObject;

import com.ssafy.trip.board.model.Board;
import com.ssafy.trip.member.model.Member;
import com.ssafy.trip.member.model.service.MemberService;
import com.ssafy.trip.member.model.service.MemberServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/member")
public class MemberController extends HttpServlet {
	
	MemberService memberService = MemberServiceImpl.getInstance();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		try {
		
			if (action == null) {
				index(req,resp);
			}
			
			switch(action) {
			case "signUp" :
				signUp(req,resp);
				break;
			case "signUpForm" :
				signUpForm(req,resp);
				break;
			case "signIn" :
				signIn(req,resp);
				break;
			case "signInForm" :
				signInForm(req,resp);
				break;
			case "signOut" :
				signOut(req,resp);
				break;
			case "signInCheck" :
				signInCheck(req,resp);
				break;
			case "myPage" :
				myPage(req,resp);
				break;
			case "edit" :
				edit(req,resp);
				break;
			case "remove" :
				remove(req,resp);
				break;				
			}		
		
		} catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
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
	        String userId = jsonObject.getString("userId");
	        System.out.println(userId);
	        
	        int removeResult = memberService.removeMember(userId);
	        if (removeResult > 0) {
	        	session.invalidate();
	        	result.put("result", true); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 
	        } else {
	        	result.put("result", false); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 	        	
	        }			
	        out.print(result.toString()); // JSON 문자열로 보내야하므로 Result JSON객체 -> JSON 문자열
			
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
	        String userId = jsonObject.getString("userId");
	        String userName = jsonObject.getString("userName");
	        String userPassword = jsonObject.getString("userPassword");
	        String userEmail = jsonObject.getString("userEmail");
	        Member myMember = new Member();
	        myMember.setUserId(userId);
	        myMember.setUserName(userName);
	        myMember.setUserPassword(userPassword);
	        myMember.setUserEmail(userEmail);
	        
	        int editResult = memberService.editMember(myMember);
	        System.out.println("editResult : " + editResult);
	        if (editResult > 0) {
	        	session.setAttribute("resultMember", myMember);
	        	result.put("result", true); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 
	        } else {
	        	result.put("result", false); // 삭제 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 	        	
	        }			
	        out.print(result.toString()); // JSON 문자열로 보내야하므로 Result JSON객체 -> JSON 문자열
			
		} else
			req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);
	}

	private void signInCheck(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		PrintWriter out = null;
	    JSONObject result = null;
	    String jsonString = null;
	    StringBuilder jsonBuilder = null;
	    JSONObject jsonObject = null;
	    BufferedReader reader = null;
	    
	    resp.setContentType("application/x-json; charset=UTF-8");
	    out = resp.getWriter();
	    result = new JSONObject();
		
	    reader = req.getReader();
	    jsonBuilder = new StringBuilder();
	    String line;
	    while ((line= reader.readLine()) != null) {
	    	jsonBuilder.append(line);
	    }
	    jsonString = jsonBuilder.toString();
	    jsonObject = new JSONObject(jsonString);
	    
	    // 사용자가 입력한 id값 가져오기
	    String userId = jsonObject.getString("userId");	    
	    boolean memberExist = false;
	    try {
			memberExist = memberService.loginCheckMember(userId);
		} catch (Exception e) {
			e.printStackTrace();
			memberExist = false;
		}
	    
	    result.put("memberExist", memberExist);
	    out.print(result.toString());
	    
	}

	private void signOut(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		HttpSession httpsession = req.getSession();
		httpsession.invalidate();
		
		resp.sendRedirect(req.getContextPath() + "/member");		
	}

	private void signIn(HttpServletRequest req, HttpServletResponse resp) throws Exception {
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

        // 내가 필요한 id, password값 JSON 객체에서 가져오기
        String userId = jsonObject.getString("username");
        String userPassword = jsonObject.getString("password");		
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPassword(userPassword);
		
		Member resultMember = memberService.loginMember(member); // 로그인 결과 확인
		if (resultMember != null) {
			// 내 세션 방 가져오기
			HttpSession httpsession = req.getSession();
			// 세션 방에다가 내 회원정보 저장
			httpsession.setAttribute("resultMember", resultMember);
			
			result.put("result", true); // 로그인 결과를 JSON 객체에 "result" 키값으로 담아서 보낼거임. 
            out.print(result.toString()); // JSON 문자열로 보내야하므로 Result JSON객체 -> JSON 문자열
		} else {			
			result.put("result", false);
            out.print(result.toString());
		}
		
	}

	private void signInForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/member/signInForm.jsp").forward(req, resp);		
	}

	private void signUpForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/member/signUpForm.jsp").forward(req, resp);
	}

	private void signUp(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//		String userId = req.getParameter("userId");
//		String userName = req.getParameter("userName");
//		String userPassword = req.getParameter("userPassword");
//		String userEmail = req.getParameter("userEmail");
//		Date joinDate = new Date(System.currentTimeMillis());
//		Member member = new Member(userId,userName,userPassword,"",userEmail,joinDate);
//		int joinResult = memberService.joinMember(member);
//		
//		resp.sendRedirect(req.getContextPath() + "/member?action=signInForm");
		
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
        jsonString = jsonBuilder.toString().trim();
        // JSON 문자열에 있는 값들을 써야하므로 다시 JSON 객체로 변환
        System.out.println(jsonString);
        jsonObject = new JSONObject(jsonString);

        // 내가 필요한 id, password값 JSON 객체에서 가져오기
        String userId = jsonObject.getString("userId");
        String userName = jsonObject.getString("userName");
        String userPassword = jsonObject.getString("userPassword");
        String userEmail = jsonObject.getString("userEmail");	
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserName(userName);
		member.setUserPassword(userPassword);
		member.setUserEmail(userEmail);
		
		try {
			memberService.joinMember(member);
			result.put("result", true);
		} catch(Exception e) {
			e.printStackTrace();
			result.put("result", false);			
		}
		out.print(result.toString());
	}
	
	private void myPage(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/member/myPage.jsp").forward(req, resp);
	}

	private void index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);		
	}
	
	
	
}
