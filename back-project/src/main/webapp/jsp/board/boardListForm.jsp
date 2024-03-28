<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous" />
<link href="${root}/css/board/boardListForm.css" rel="stylesheet" />
<title></title>
</head>
<body>
	<div id="big-container" class="container">
		<%@ include file="/jsp/nav.jsp"%>
		<div class="row justify-content-center">
<!-- 			<div class="col-lg-8 col-md-10 col-sm-12">
				<h2 class="my-3 py-3 shadow-sm bg-light text-center">
					<mark class="sky">글목록</mark>
				</h2>
			</div> -->
			<div id="board-list" class="col-lg-8 col-md-10 col-sm-12">
				<div class="row align-self-center mb-2">
					<div class="col-md-2 text-start">
						<button type="button" id="btn-mv-register"
							class="btn btn-outline-primary btn-sm">글쓰기</button>
					</div>
					<div class="col-md-7 offset-3">
						<form id="form-search" class="d-flex">
							<input type="hidden" name="action" value="getList">
							<input type="hidden" name="pageNo" value="1">
							<select id="key" name="key"
								class="form-select form-select-sm ms-5 me-1 w-50"
								aria-label="검색조건">
								<option selected>검색조건</option>
								<option value="board_id">글번호</option>
								<option value="title">제목</option>
								<option value="user_id">작성자</option>
							</select>
							<div class="input-group input-group-sm">
								<input id="word" name="word" type="text" class="form-control"
									placeholder="검색어..." />
								<button id="btn-search" class="btn btn-dark" type="button">검색</button>
							</div>
						</form>
					</div>
				</div>
				<table class="table table-hover">
					<thead>
						<tr class="text-center">
							<th scope="col">글번호</th>
							<th scope="col">제목</th>
							<th scope="col">작성자</th>
							<th scope="col">조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${list}"> 
							<tr class="text-center">
								<th scope="row">${board.boardId}</th>
								<td class="text-start"><a href="#"
									class="board-title link-dark" board-id="${board.boardId}"
									style="text-decoration: none">
										${board.title} </a></td>
								<td>${board.userId}</td>
								<td>${board.viewCount}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="row">
				${navigation.navigator}
			</div>
		</div>
	</div>

	<form id="form-param" method="get" action="">
		<input type="hidden" id="p-action" name="action" value=""> <input
			type="hidden" id="p-pgno" name="pageNo" value=""> <input
			type="hidden" id="p-key" name="key" value=""> <input
			type="hidden" id="p-word" name="word" value="">
	</form>
	<script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
      crossorigin="anonymous"
    ></script>
    <script>
     // 제목 눌렀을 때 상세 페이지 이동
      let titles = document.querySelectorAll(".board-title");
      titles.forEach(function (title) {
        title.addEventListener("click", function () {
          console.log(this.getAttribute("page-no"));
          location.href = "${root}/board?action=getDetail&boardId=" + this.getAttribute("board-id");
        });
      });
      
      // 검색 버튼
      document.querySelector("#btn-search").addEventListener("click", function () {
          let form = document.querySelector("#form-search");
          form.setAttribute("action", "${root}/board");
          form.submit();
      });
      
      // 페이지 이동
      let pages = document.querySelectorAll(".page-link");
      pages.forEach(function (page) {
        page.addEventListener("click", function () {
        	console.log("hi");
          console.log(this.parentNode.getAttribute("data-pg"));
          document.querySelector("#p-action").value = "getList";
       	  document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
       	  document.querySelector("#p-key").value = "${param.key}";
       	  document.querySelector("#p-word").value = "${param.word}";
       	  // form-param form 서버로 전송. get 방식이고 button도 없기 때문에 submit()으로 제출.
          document.querySelector("#form-param").submit();
        });
      });

      // 등록버튼 눌렀을 때
      document.querySelector("#btn-mv-register").addEventListener("click", function () {
        location.href = "${root}/board?action=registForm";
      });
    </script>
</body>
</html>