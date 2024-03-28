let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

let options = {
	//지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(36.35537731926109, 127.29847072801634), //지도의 중심좌표.
	level: 5, //지도의 레벨(확대, 축소 정도)
};

let overlays = [];
let markers = [];
let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

// 장소 검색 객체를 생성합니다
let ps = new kakao.maps.services.Places();

let mapCenterX;
let mapCenterY;

// 로고 버튼 - 메인으로 이동
document.getElementById("GnG_logo").addEventListener("click", function() {
	console.log(1);
	window.location.href = "http://localhost:8080/gng/tour";
});
let positions = [];
let contentTypeId;
let searchA;

function clearMarkersAndOveray() {
	for (let i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
		overlays[i].setMap(null);
	}
}

// 버튼 클릭시 키워드 불러오기
document.getElementById("searchbtn").addEventListener("click", function() {
	clearMarkersAndOveray();

	overlays = [];
	markers = [];
	//관광지
	contentTypeId = document.getElementById("searchCategory").value;
	// 검색어
	searchA = document.getElementById("searchText").value;

	// 키워드로 장소를 검색합니다
	ps.keywordSearch(`${searchA}`, placesSearchCB);
});

// 서비스 키
let serviceKey =
	"B%2BMl9I5q1O4TuNDPu%2BowgE6%2FkENWpgp7tDS%2Bu3yZu7VNnPhANtMFm8f9jcvhcoqu2a%2BapKDc5GtJKnTYq94uuQ%3D%3D";

//baseUrl
let baseUrl = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1?serviceKey=";

// 키워드 검색 완료 시 호출되는 콜백함수 입니다
function placesSearchCB(data, status, pagination) {
	if (status === kakao.maps.services.Status.OK) {
		// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
		// LatLngBounds 객체에 좌표를 추가합니다

		console.log(searchA, contentTypeId, data[0].y, data[0].x);

		let nextUrl =
			"&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=" +
			data[0].x +
			"&mapY=" +
			data[0].y +
			"&radius=1000&contentTypeId=" +
			contentTypeId;

		let config = {
			method: 'POST',
			headers: {
				"Content-Type": "application/json",
			},
			body: JSON.stringify({
				contentTypeId: contentTypeId,
				mapxLat: data[0].x,
				mapyLon: data[0].y,
				range: 5,
				maxItems : 20
				
			})
		};

		let url2 = "http://localhost:8080/gng/tour?action=search&contentTypeId="+
				contentTypeId+
				"&mapxLat=" +
				data[0].y +
				"&mapyLon=" +
				data[0].x +
				"&range=" +
				1 +
				"&maxItems=" +
				3; 	

		let url = baseUrl + serviceKey + nextUrl;
		console.log(url2);
		console.log(config);
		fetch(url2)
			.then((response) => response.json())
			.then((d) => {
				console.dir(d);
				let items = d.item;
				makeMarker(items);
			})
			.catch();

		console.log(1);
	}
}

// 주변 마커 세팅
function makeMarker(items) {
	console.dir(items);
	positions = [];
	items.forEach((item) => {
		console.log(item)
		let param = {
			content: item.title,
			latlng: new kakao.maps.LatLng(item.latitude, item.longitude),
		};
		positions.push(param);
	});
	for (let i = 0; i < positions.length; i++) {
		// 마커를 생성합니다
		let marker = new kakao.maps.Marker({
			map: map, // 마커를 표시할 지도
			position: positions[i].latlng,
		});
		markers.push(marker);

		// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다

		// 커스텀 오버레이에 표시할 컨텐츠 입니다
		// 커스텀 오버레이는 아래와 같이 사용자가 자유롭게 컨텐츠를 구성하고 이벤트를 제어할 수 있기 때문에
		// 별도의 이벤트 메소드를 제공하지 않습니다
		let content =
			'<div class="wrap">' +
			'    <div class="info">' +
			'        <div class="title">' +
			`            ${items[i].title}` +
			`            <div class="close" onclick="closeOverlay(${i})" title="닫기"></div>` +
			"        </div>" +
			'        <div class="body">' +
			'            <div class="img">' +
			`                <img src=${items[i].firstimage
				? items[i].firstimage
				: "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumnail.png"
			} width="73" height="70">` +
			"           </div>" +
			'            <div class="desc">' +
			`                <div class="ellipsis">${items[i].addr1}</div>` +
			`                <div class="jibun ellipsis">${items[i].addr2}</div>` +
			`                <div><a href="https://map.kakao.com/link/roadview/${items[i].latitude},${items[i].longitude}" target="_blank" class="link">로드뷰 보기</a></div>` +
			"            </div>" +
			"        </div>" +
			"    </div>" +
			"</div>";

		let overlay = new kakao.maps.CustomOverlay({
			content: content,
			map: null,
			position: positions[i].latlng,
		});

		overlays.push(overlay);
		// 마커를 클릭했을 때 커스텀 오버레이를 표시합니다
		kakao.maps.event.addListener(marker, "click", function() {
			console.log(markers[i].getMap());
			if (overlays[i].getMap() === null) {
				overlays[i].setMap(map);
			} else {
				overlays[i].setMap(null);
			}
		});
	}
	console.log(2);

	// 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
	// LatLngBounds 객체에 좌표를 추가합니다
	var bounds = new kakao.maps.LatLngBounds();

	for (var i = 0; i < items.length; i++) {
		console.log(items[i]);
		bounds.extend(new kakao.maps.LatLng(items[i].latitude, items[i].longitude));
	}

	// 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
	map.setBounds(bounds);
}

// 커스텀 오버레이를 닫기 위해 호출되는 함수입니다
function closeOverlay(idx) {
	overlays[idx].setMap(null);
}