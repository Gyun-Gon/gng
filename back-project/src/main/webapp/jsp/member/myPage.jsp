<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/myPage.css" />
  </head>

  <body>
    <header>
      <div id="GnG_logo" class="GnG_logo"><img src="${pageContext.request.contextPath}/image/화이트변경.png" alt="" /></div>
      <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container p-2">
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
              <li class="nav-item m-1">
                <a class="nav-link" aria-current="page" href="tour.html">관광지 정보</a>
              </li>
              <li class="nav-item m-1">
                <a class="nav-link" href="recommend.html">지역별 추천 코스</a>
              </li>
              <li class="nav-item m-1">
                <a class="nav-link" href="review.html">장소 리뷰</a>
              </li>
              <li class="nav-item m-1">
                <a class="nav-link" href="community.html">커뮤니티</a>
              </li>
              <li class="nav-item m-1">
                <a class="nav-link" href="mypage.html">마이 페이지</a>
              </li>
              <li class="nav-item m-1">
                <a class="nav-link" href="signin.html">로그아웃</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <main class="main">
      <form id="mypage_form" action="POST">
        <div class="mypage_form_input">
          <label for="username">이름</label>
          <input type="text" id="username" name="username" value="이경곤" />
          <button id="namechange_btn" type="submit">변경</button>
        </div>
        <div class="mypage_form_input">
          <label for="userid">아이디</label>
          <input type="text" id="userid" name="userid" value="leeggon" />
          <button id="idchange_btn" type="submit">변경</button>
        </div>
        <div class="mypage_form_input">
          <label for="userpwd">비밀번호</label>
          <input type="password" id="userpwd" name="userpwd" value="password" />
          <span></span>
        </div>
        <div class="mypage_form_input">
          <label for="userpwd_check">비밀번호 확인</label>
          <input type="password" id="userpwd_check" name="userpwd_check" />
          <button id="pwdchange_btn" type="submit">변경</button>
        </div>
        <div class="mypage_form_input">
          <label for="userbirth">생년월일</label>
          <input
            type="text"
            id="userbirth"
            name="userbirth"
            value="1999.12.20"
            onfocus="(this.type='date')"
            onblur="(this.type='text')"
          />
          <button id="birthchange_btn" type="submit">변경</button>
        </div>
        <div class="mypage_form_button">
          <button id="signout_btn" type="submit">회원탈퇴</button>
        </div>
      </form>
    </main>
    <footer></footer>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
	<script src="${pageContext.request.contextPath}/js/member/myPage.js"></script>
  </body>
</html>
