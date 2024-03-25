// 서비스 키
let serviceKey =
  "B%2BMl9I5q1O4TuNDPu%2BowgE6%2FkENWpgp7tDS%2Bu3yZu7VNnPhANtMFm8f9jcvhcoqu2a%2BapKDc5GtJKnTYq94uuQ%3D%3D";

let baseUrl = "https://apis.data.go.kr/B551011/KorService1/locationBasedList1?serviceKey=";

let test =
  "&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=AppTest&_type=json&listYN=Y&arrange=A&mapX=126.981611&mapY=37.568477&radius=1000&contentTypeId=15";

let testUrl = baseUrl + serviceKey + test;
console.log(testUrl);
fetch(testUrl)
  .then((response) => response.json())
  .then((data) => {
    console.dir(data);
    let items = data.response.body.items.item;
    makeList(items);
  })
  .catch();
function makeList(items) {
  let here = document.getElementById("here");
  let html;
  items.forEach((item) => {
    html += `
            <div>
                <span>${item.title}</span>
                <span>${item.addr1}</span>
                <span>${item.phone}</span>
            </div>
        `;
  });
  here.innerHTML = html;
}
