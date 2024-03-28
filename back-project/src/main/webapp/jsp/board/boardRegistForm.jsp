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
    <form method="post" action="${root}/board">
    	<input type="hidden" name="action" value="regist">
        <label for="title">제목:</label><br>
        <input type="text" id="board-title" name="title" required><br>
        <label for="content">내용:</label><br>
        <textarea id="board-content" name="content" rows="5" cols="50" required></textarea><br>
        <button id="btn-regist" type="button">등록</button>
    </form>
    <script>
    let btnRegist = document.getElementById("btn-regist");
    btnRegist.addEventListener("click", function (){
  		let title = document.getElementById("board-title").value;
  		let content = document.getElementById("board-content").value;
  		console.log(title);
  	    
  	    let config = {
  	        method: 'POST',
  	        headers: {
  	            "Content-Type": "application/json",
  	        },
  	        body: JSON.stringify({
  	            title : title,
  	            content : content
  	        })
  	    };
  	    
  	    fetch("http://localhost:8080/gng/board?action=regist", config)
  	    .then((response) => response.json())
  	    .then((data) => {
  	    	console.dir(data);
  			if (data.result) {
  				alert("글을 등록했습니다.")
  				window.location.href = "http://localhost:8080/gng/board?action=getList&pageNo=1";	
  			} else {
  				alert("글을 등록할 수 없습니다.");
  			}
  		});
  		
  	});
    </script>
</body>
</html>