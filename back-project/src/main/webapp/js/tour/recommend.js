// 로고 버튼 - 메인으로 이동
document.getElementById("GnG_logo").addEventListener("click",
	function() {
		window.location.href = "http://localhost:8080/gng/tour";
	});


let container = document.getElementById("map"); //지도를 담을 영역의 DOM 레퍼런스

let options = {
	//지도를 생성할 때 필요한 기본 옵션
	center: new kakao.maps.LatLng(36.35537731926109, 127.29847072801634), //지도의 중심좌표.
	level: 5, //지도의 레벨(확대, 축소 정도)
};
let positions = [];
let overlays = [];
let markers = [];
let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴
let exampleModal = document.getElementById('exampleModal')

exampleModal.addEventListener('show.bs.modal', function(event) {
	const gridItems = document.querySelectorAll(".grid-item");
	clearMarkersAndOveray();
	// 기존에 등록된 클릭 이벤트 리스너 제거
	gridItems.forEach(function(item) {
		item.removeEventListener("click", handleGridItemClick);
	});

	// 새로운 클릭 이벤트 리스너 등록
	gridItems.forEach(function(item) {
		item.addEventListener("click", handleGridItemClick);
	});
});

// 클릭 이벤트 핸들러 함수
function handleGridItemClick(event) {
	const item = event.currentTarget;
	const itemId = item.getAttribute("data-id");
	console.log("Clicked Item ID:", itemId);

	findSector(itemId);
}

function findSector(itemId) {
	bounds = new kakao.maps.LatLngBounds();
	distanceOverlay = new kakao.maps.CustomOverlay();
	console.log(itemId)
	let url = "http://localhost:8080/gng/tour?action=searchSector&sectorCode=" + itemId;

	fetch(url)
		.then((response) => response.json())
		.then((d) => {
			console.log(d);

			let findUrl = "http://localhost:8080/gng/tour?action=searchRecommenCourse&mapxLat=" +
				d.item.latitude +
				"&mapyLon=" +
				d.item.longitude;

			fetch(findUrl)
				.then((response) => response.json())
				.then((d) => {
					console.log(d);
					let items = d.item;
					makeWay(items);
				})
				.catch();

			setTimeout(function() {
				map.setCenter(new kakao.maps.LatLng(d.item.latitude, d.item.longitude));
				map.setLevel(4);
				map.relayout();
				relayout();

			}, 300);
		})
}

var drawLine;
var distanceOverlay;
var dots = {};

function makeWay(items) {
	clearMarkersAndOveray();

	positions = [];
	let linePath;
	let lineLine = new kakao.maps.Polyline();
	let distance;

	items.forEach((item) => {
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

		// 마커를 클릭했을 때 이벤
		kakao.maps.event.addListener(marker, "click", function() {

		});

		if (i != 0) {
			linePath = [positions[i - 1].latlng, positions[i].latlng]
		}
		lineLine.setPath(linePath);

		drawLine = new kakao.maps.Polyline({
			map: map,
			path: linePath,
			strokeWeight: 2,
			strokeColor: 'red',
			strokeStyle: 'solid'
		});
		distance = Math.round(lineLine.getLength());
		displayCircleDot(positions[i].latlng, distance, positions[i].content);
	}

	// 모든 마커의 위치를 LatLngBounds 객체에 추가
	items.forEach(function(item) {
		bounds.extend(new kakao.maps.LatLng(item.latitude, item.longitude));
	});

	// 지도의 중심점과 축척을 설정합니다.
	//map.setCenter(center);
	//map.setLevel(6);
}

var bounds = new kakao.maps.LatLngBounds(); // LatLngBounds 객체 생성


function displayCircleDot(position, distance, title) {

	if (distance > 0) {
		console.log(title);
		distanceOverlay = new kakao.maps.CustomOverlay(
			{
				content: title,
				position: position,
				yAnchor: 1,
				zInget: 2
			})
		distanceOverlay.setMap(map);
	}
	dots.push({ line: drawLine, distance: distanceOverlay });
}

function clearMarkersAndOveray() {
	for (let i = 0; i < markers.length; i++) {
		markers[i].setMap(null);
	}
	var i;

	for (i = 0; i < dots.length; i++) {
		if (dots[i].line) {
			dots[i].line.setMap(null);
		}

		if (dots[i].distance) {
			dots[i].distance.setMap(null);
		}
	}

	dots = [];
}

function relayout() {
	setBounds()
	map.relayout();
}

function setBounds() {
	map.setBounds(bounds)
}
