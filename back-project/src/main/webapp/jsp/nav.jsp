<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header>
	<div id="GnG_logo" class="GnG_logo">
		<a href="${pageContext.request.contextPath}/member"><img
			src="${pageContext.request.contextPath}/image/화이트변경.png" alt="" /></a>
	</div>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container p-2">
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ms-auto">
					<li class="nav-item m-1"><a class="nav-link"
						aria-current="page" href="${pageContext.request.contextPath}/tour">관광지 정보</a></li>
					<li class="nav-item m-1"><a class="nav-link"
						href="${pageContext.request.contextPath}/tour">지역별 추천 코스</a></li>
					<li class="nav-item m-1"><a class="nav-link"
						href="./html/review.html">장소 리뷰</a></li>
					<li class="nav-item m-1"><a class="nav-link"
						href="${pageContext.request.contextPath}/board?action=getList&pageNo=1">커뮤니티</a></li>
					
					<c:choose>
						<c:when test="${not empty sessionScope.resultMember}">
							<li class="nav-item m-1"><a class="nav-link"
								href="${pageContext.request.contextPath}/member?action=myPage">마이 페이지</a></li>
							<li class="nav-item m-1"  id="login_user_name"><span>${sessionScope.resultMember.userName}님</span></li>
							<li class="nav-item m-1">
								<a class="nav-link"	href="${pageContext.request.contextPath}/member?action=signOut">로그아웃</a>
							</li>
						</c:when>
						<c:otherwise>
							<li class="nav-item m-1"><a class="nav-link"
								href="${pageContext.request.contextPath}/member?action=signInForm">로그인</a></li>
							<li class="nav-item m-1">
								<a class="nav-link"	href="${pageContext.request.contextPath}/member?action=signUpForm">회원가입</a>
							</li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
		</div>
	</nav>
</header>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
	crossorigin="anonymous"></script>