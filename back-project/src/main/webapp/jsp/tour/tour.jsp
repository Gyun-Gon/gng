<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>관광지 정보</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/tour/tour.css" />
<script src="${pageContext.request.contextPath}/js/tour/tour.js" defer></script>


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
	<main>
		<div class="header_container">
			<h2 class="header_container_header">지역별 관광지 정보</h2>
		</div>
		<div class="search_container">
			<div class="search_dropdown">
				<select id="searchCategory">
					<option value=12>관광지</option>
					<option value=14>문화시설</option>
					<option value=15>축제공연행사</option>
					<option value=25>여행코스</option>
					<option value=28>레포츠</option>
					<option value=32>숙박</option>
					<option value=38>쇼핑</option>
					<option value=39>음식점</option>
				</select>
			</div>

			<div class="search_input">
				<input id="searchText" type="text" class="search_input_box"
					placeholder=" 검색어를 입력하세요">
			</div>

			<div class="search-button">
				<button id="searchbtn" type="button">검색</button>
			</div>
		</div>
		</div>
		<div class="map_container">
			<div id="map" class="map">지도</div>
		</div>
	</main>

	<!-- <script
      type="text/javascript"
      src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04415231ee1856321fd1042a5a42ef5c"
    ></script> -->
	<!-- services와 clusterer, drawing 라이브러리 불러오기 -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=04415231ee1856321fd1042a5a42ef5c&libraries=services,clusterer,drawing"></script>

</body>
</html>