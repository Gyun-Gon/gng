package com.ssafy.trip.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import org.json.JSONObject;

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
			}		
		
		} catch(Exception e) {
			e.printStackTrace();
			req.getRequestDispatcher("/error.jsp").forward(req, resp);
		}
		
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
		
		resp.setContentType("application/x-json; charset=UTF-8");
        out = resp.getWriter();
        result = new JSONObject();

        reader = req.getReader();
        jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        jsonString = jsonBuilder.toString();
        jsonObject = new JSONObject(jsonString);

        String userId = jsonObject.getString("username");
        String userPassword = jsonObject.getString("password");		
		
		Member member = new Member();
		member.setUserId(userId);
		member.setUserPassword(userPassword);
		
		Member resultMember = memberService.loginMember(member);
		if (resultMember != null) {
			// 내 세션 방 가져오기
			HttpSession httpsession = req.getSession();
			// 세션 방에다가 내 회원정보 저장
			httpsession.setAttribute("resultMember", resultMember);
			
			result.put("result", true);
            out.print(result.toString());
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
		String userId = req.getParameter("userId");
		String userName = req.getParameter("userName");
		String userPassword = req.getParameter("userPassword");
		Date joinDate = new Date(System.currentTimeMillis());
		Member member = new Member(userId,userName,userPassword,"","",joinDate);
		int joinResult = memberService.joinMember(member);
		
		resp.sendRedirect(req.getContextPath() + "/member?action=signInForm");		
	}

	private void index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		req.getRequestDispatcher("/jsp/index.jsp").forward(req, resp);		
	}
	
	
	
}
