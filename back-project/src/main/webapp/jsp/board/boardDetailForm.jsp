<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<c:if test="${board ne null}">
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
<link href="${root}/css/board/boardDetailForm.css" rel="stylesheet" />
<title></title>
</head>
<body>
	<div class="container">
		<%@ include file="/jsp/nav.jsp"%>
		<div class="row justify-content-center">
			<div id="board-content-container"
				class="col-lg-8 col-md-10 col-sm-12">
				<div class="row my-2">
					<h2 id="boardIdTitle" class="text-secondary px-5"
						board-id="${board.boardId}">${board.boardId}.${board.title}</h2>
				</div>
				<div class="row">
					<div class="col-md-8">
						<div class="clearfix align-content-center">
							<img class="avatar me-2 float-md-start bg-light p-2"
								src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
							<p>
								<span class="fw-bold">${board.userId}</span> <br />
							</p>
						</div>
					</div>
					<div class="col-md-4 align-self-center text-end">댓글 : 0</div>
					<div class="divider mb-3"></div>
					<div class="text-secondary">${board.content}</div>
					<div class="divider mt-3 mb-3"></div>
					<div class="d-flex justify-content-end">
						<button type="button" id="btn-list"
							class="btn btn-outline-primary mb-3">글목록</button>
						<button type="button" id="btn-mv-modify"
							class="btn btn-outline-success mb-3 ms-1">글수정</button>
						<button type="button" id="btn-delete"
							class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
					</div>
				</div>
			</div>
		</div>
		<div class="comment_register_container">
			<div class="comment_register">
				<h3>댓글</h3>
				<form>
					<input id="comment_regist_content" type="text" name="comment_regist_content"></input>
					<button type="button">등록</button>
				</form>
			</div>
		</div>

	</div>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
	<script>
      document.querySelector("#btn-list").addEventListener("click", function () {
        location.href = "${root}/board?action=getList&pageNo=1";
      });
      document.querySelector("#btn-mv-modify").addEventListener("click", function () {
    	  let toEditBoardId = document.getElementById("boardIdTitle").getAttribute("board-id");
          location.href = "${root}/board?action=editForm&boardId=" + toEditBoardId;
      });
/*       document.querySelector("#btn-delete").addEventListener("click", function () {
        alert("글삭제하자!!!");
        location.href = "${root}";
      }); */
      
      let btnDelete = document.getElementById("btn-delete");
      btnDelete.addEventListener("click", function (){
    		let toDeleteBoardId = document.getElementById("boardIdTitle").getAttribute("board-id");
    		console.log(toDeleteBoardId);
    	    
    	    let config = {
    	        method: 'POST',
    	        headers: {
    	            "Content-Type": "application/json",
    	        },
    	        body: JSON.stringify({
    	            boardId : toDeleteBoardId
    	        })
    	    };
    	    
    	    fetch("http://localhost:8080/gng/board?action=remove", config)
    	    .then((response) => response.json())
    	    .then((data) => {
    			if (data.result) {
    				alert("글을 삭제했습니다.")
    				window.location.href = "http://localhost:8080/gng/board?action=getList&pageNo=1";	
    			} else {
    				alert("글을 삭제할 수 없습니다.");
    			}
    		});
    		
    	});
    </script>
</body>
	</html>
</c:if>
<c:if test="${board eq null}">
	<script>
	alert("글이 삭제되었거나 부적절한 URL 접근입니다.");
	location.href = "${root}/board?action=getList";
	</script>
</c:if>
