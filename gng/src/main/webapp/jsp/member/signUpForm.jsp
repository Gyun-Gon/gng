<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/signUpForm.css"  />
  </head>

  <body>
    <main>
      <div id="GnG_logo" class="GnG_logo"><a href="${pageContext.request.contextPath}/member"><img src="${pageContext.request.contextPath}/image/화이트변경.png" alt="" /></a></div>
      <div class="sign_up">
        <div class="sign_up_header">
          <h2 class="sign_up_header_title">Sign up</h2>
        </div>
        <div class="sign_up_content">
          <form id="signup_form" action="POST">
            <div class="signup_form_input">
              <label for="username">이름</label>
              <input type="text" id="username" name="username" placeholder="  name" />
            </div>
            <div class="signup_form_input">
              <label for="userid">아이디</label>
              <input type="text" id="userid" name="userid" placeholder="  id" />
            </div>
            <div class="signup_form_input">
              <label for="userpwd">비밀번호</label>
              <input type="password" id="userpwd" name="userpwd" placeholder="  password" />
            </div>
            <div class="signup_form_input">
              <label for="userpwd_check">비밀번호 확인</label>
              <input
                type="password"
                id="userpwd_check"
                name="userpwd_check"
                placeholder="  password check"
              />
            </div>
            <div class="signup_form_input">
              <label for="userbirth">생년월일</label>
              <input
                type="text"
                id="userbirth"
                name="userbirth"
                placeholder="  birthday"
                onfocus="(this.type='date')"
                onblur="(this.type='text')"
              />
            </div>
            <div class="signup_form_button">
              <button id="cancel_btn" type="button">취소</button>
              <button id="signup_btn" type="button">회원가입</button>
            </div>
          </form>
        </div>
      </div>
    </main>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
    <script src="${pageContext.request.contextPath}/js/member/signUpForm.js"></script>
  </body>
</html>
