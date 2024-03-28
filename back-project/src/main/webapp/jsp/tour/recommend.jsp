<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tour/recommend.css" />

</head>
<body>
	<header>
		<div id="GnG_logo" class="GnG_logo">
			<img src="${pageContext.request.contextPath}/image/화이트변경.png" alt="" />
		</div>
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container p-2">
				<!-- <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button> -->
				<div class="collapse navbar-collapse" id="navbarNav">
					<ul class="navbar-nav ms-auto">
						<li class="nav-item m-1"><a class="nav-link"
							aria-current="page" href="tour.html">관광지 정보</a></li>
						<li class="nav-item m-1"><a class="nav-link"
							href="recommend.html">지역별 추천 코스</a></li>
						<li class="nav-item m-1"><a class="nav-link"
							href="review.html">장소 리뷰</a></li>
						<li class="nav-item m-1"><a class="nav-link"
							href="community.html">커뮤니티</a></li>
						<li class="nav-item m-1"><a class="nav-link"
							href="mypage.html">마이 페이지</a></li>
						<li class="nav-item m-1"><a class="nav-link"
							href="signin.html">로그아웃</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<main class="main">
		<div class="header_container">
			<h2 class="header_container_header">지역별 추천 코스</h2>
		</div>
		<div class="grid-container" id="">
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="4">
				<img src="${pageContext.request.contextPath}/image/비빔밥.jpg" alt="" />
				<div class="region_div">
					<span class="region_dialect">거시기 하네ㅋ</span><span class="region">전라도</span>
				</div>
			</div>
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="2">
				<img src="${pageContext.request.contextPath}/image/국밥.jpeg" alt="" />
				<div class="region_div">
					<span class="region_dialect">니 머라카노!</span><span class="region">경상도</span>
				</div>
			</div>
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="6">
				<img src="${pageContext.request.contextPath}/image/닭갈비.jpg" alt="" />
				<div class="region_div">
					<span class="region_dialect">감자 무봤나?</span><span class="region">강원도</span>
				</div>
			</div>
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="5">
				<img src="${pageContext.request.contextPath}/image/성심당.jpeg" alt=""/ >
				<div class="region_div">
					<span class="region_dialect">돌 굴러가유~~</span><span class="region">충청도</span>
				</div>
			</div>
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="7">
				<img src="${pageContext.request.contextPath}/image/흑돼지.jpeg" alt=""/ >
				<div class="region_div">
					<span class="region_dialect">혼저옵서예..?</span><span class="region">제주도</span>
				</div>
			</div>
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="1">
				<img src="${pageContext.request.contextPath}/image/설렁탕.jpg" alt="" />
				<div class="region_div">
					<span class="region_dialect">서울특별시.</span><span class="region">서울시</span>
				</div>
			</div>
			<div class="grid-item" data-bs-toggle="modal"
				data-bs-target="#exampleModal" data-id="3">
				<img src="${pageContext.request.contextPath}/image/부대찌개.jpg" alt=""/ >
				<div class="region_div">
					<span class="region_dialect">강남역말고^^</span><span class="region">경기도</span>
				</div>
			</div>
		</div>
	</main>
	<footer></footer>


	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-xl">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">추천 코스</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<div class="map_container">
						<div id="map" class="map">지도</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary" onclick="relayout()">다른 경로 추천</button>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04415231ee1856321fd1042a5a42ef5c&libraries=services,clusterer,drawing"></script>

	<script src="${pageContext.request.contextPath}/js/tour/recommend.js"></script>
</body>
</html>