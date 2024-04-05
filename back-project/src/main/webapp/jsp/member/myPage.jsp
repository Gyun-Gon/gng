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
    <%@ include file="/jsp/nav.jsp"%>
    <main class="main">
      <form id="mypage_form" action="POST">
        <div class="mypage_form_input">
          <label for="userName">이름</label>
          <input type="text" id="userName" name="userName" value="${sessionScope.resultMember.userName}" />
        </div>
        <div class="mypage_form_input">
          <label for="userId">아이디</label>
          <input type="text" id="userId" name="userid" value="${sessionScope.resultMember.userId}" readonly/>
        </div>
        <div class="mypage_form_input">
          <label for="userPassword">비밀번호</label>
          <input type="password" id="userPassword" name="userPassword" />
        </div>
        <div class="mypage_form_input">
          <label for="userEmail">이메일</label>
          <input type="email" id="userEmail" name="userEmail" value="${sessionScope.resultMember.userEmail}" />
        </div>
        <div class="mypage_form_button">
        <button id="edit_btn" type="button">변경하기</button>
          <button id="remove_btn" type="button">회원탈퇴</button>
        </div>
      </form>
    </main>
    <footer></footer>

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
      crossorigin="anonymous"
    ></script>
	<%-- <script src="${pageContext.request.contextPath}/js/member/myPage.js"></script> --%>
	<script>
	let editBtn = document.getElementById("edit_btn");
	editBtn.addEventListener('click', function (){
		let userId = document.getElementById('userId').value;
		let userName = document.getElementById('userName').value;
	    let userPassword = document.getElementById('userPassword').value;
	    let userEmail = document.getElementById('userEmail').value;
	    
	    let config = {
	        method: 'POST',
	        headers: {
	            "Content-Type": "application/json",
	        },
	        body: JSON.stringify({
	        	userId: userId,
	            userName: userName,
	            userPassword: userPassword,
	            userEmail: userEmail
	        })
	    };
	    
	    fetch("http://localhost:8080/gng/member?action=edit", config)
	    .then((response) => response.json())
	    .then((data) => {
			if (data.result) {
				alert("회원정보 수정이 완료되었습니다.");
				window.location.href = "http://localhost:8080/gng/member";	
			} else {
				alert("수정할 수 없습니다.");
			}
		});
		
	});
	
	let removeBtn = document.getElementById("remove_btn");
	removeBtn.addEventListener('click', function (){
		let userId = document.getElementById('userId').value;
	    
	    let config = {
	        method: 'POST',
	        headers: {
	            "Content-Type": "application/json",
	        },
	        body: JSON.stringify({
	        	userId: userId
	        })
	    };
	    
	    fetch("http://localhost:8080/gng/member?action=remove", config)
	    .then((response) => response.json())
	    .then((data) => {
			if (data.result) {
				alert("회원정보가 삭제되었습니다.");
				window.location.href = "http://localhost:8080/gng/member";	
			} else {
				alert("삭제할 수 없습니다.");
			}
		});
		
	});
	</script>
  </body>
</html>
