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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/member/signInForm.css" />
    <script src="${pageContext.request.contextPath}/js/member/signInForm.js" defer></script>
  </head>
  <body>
    <main>
      <div id="GnG_logo" class="GnG_logo"><img src="${pageContext.request.contextPath}/image/화이트변경.png" alt="" /></div>
      <div class="sign_in">
        <div class="title">
          <h2 class="title_header">Sign In</h2>
          <div class="title_create">
            <div class="title_create_text">or</div>
            <a class="title_create_ref" href="${pageContext.request.contextPath}/member?action=signUpForm">create an account</a>
          </div>
          
<%--           <form id="signin_form" method="post" action="${pageContext.request.contextPath}/member">
          	<input type="hidden" name="action" value="signIn">
            <input type="text" id="userId" name="userId" placeholder="  id" />
            <input type="password" id="userPassword" name="userPassword" placeholder="  password" />
            <button id="signin_btn" type="submit">Log in</button>
          </form> --%>
          
          <form id="signin_form">
          	<input type="hidden" name="action" value="signIn">
            <input type="text" id="userId" name="userId" placeholder="  id" />
            <input type="password" id="userPassword" name="userPassword" placeholder="  password" />
          </form>
          
          <button id="signin_btn" type="button">Log in</button>
        </div>
      </div>
    </main>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
 
  </body>
</html>
