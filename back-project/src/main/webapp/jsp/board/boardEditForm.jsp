<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> --%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 등록</title>
    <link href="${root}/css/board/boardRegistForm.css" rel="stylesheet">
</head>
<body>
    <h2>게시글 등록</h2>
    <form method="post" action="${root}/board">
    	<input type="hidden" name="action" value="regist">
    	<label for="boardId">글 번호:</label><br>
        <input type="text" id="board-id" name="boardId" value="${board.boardId}" readonly><br>
        <label for="title">제목:</label><br>
        <input type="text" id="board-title" name="title" value="${board.title}" required><br>
        <label for="content">내용:</label><br>
        <textarea id="board-content" name="content" rows="5" cols="50" required>${board.content}</textarea><br>
        <button id="btn-edit" type="button">수정</button>
    </form>
    <script>
    let btnEdit = document.getElementById("btn-edit");
    btnEdit.addEventListener("click", function (){
    	console.log("hi");
    	let boardId = document.getElementById("board-id").value;
  		let title = document.getElementById("board-title").value;
  		let content = document.getElementById("board-content").value;
  	    
  	    let config = {
  	        method: 'POST',
  	        headers: {
  	            "Content-Type": "application/json",
  	        },
  	        body: JSON.stringify({
  	        	boardId : boardId,
  	            title : title,
  	            content : content
  	        })
  	    };
  	    
  	    fetch("http://localhost:8080/gng/board?action=edit", config)
  	    .then((response) => response.json())
  	    .then((data) => {
  	    	console.dir(data);
  			if (data.result) {
  				alert("글을 수정했습니다.")
  				window.location.href = "http://localhost:8080/gng/board?action=getList&pageNo=1";	
  			} else {
  				alert("글을 수정할 수 없습니다.");
  			}
  		});
  		
  	});
    </script>
</body>
</html>