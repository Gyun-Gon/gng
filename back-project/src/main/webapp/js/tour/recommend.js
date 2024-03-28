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

let overlays = [];
let markers = [];
let map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

let exampleModal = document.getElementById('exampleModal')

exampleModal.addEventListener('show.bs.modal', function(event) {
	const gridItems = document.querySelectorAll(".grid-item");
	

    gridItems.forEach(function(item) {
        item.addEventListener("click", function() {
            // 클릭된 요소의 data-id 값을 가져옵니다.
            const itemId = item.getAttribute("data-id");

            // 가져온 data-id 값을 출력합니다.
            console.log("Clicked Item ID:", itemId);

            // 여기서 가져온 값을 다른 곳에 전달하거나 처리할 수 있습니다.
        });
    });
	
	setTimeout(function() {
		map.relayout();
		map.setCenter(new kakao.maps.LatLng(36.35537731926109, 127.29847072801634));
		map.setLevel(5, { animate: true });
	}, 200);
})

function relayout() {
	console.log(11111);
	map.relayout();
}

