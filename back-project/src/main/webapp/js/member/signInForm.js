let loginBtn = document.getElementById("signin_btn");
loginBtn.addEventListener('click', function (){
	let username = document.getElementById('userId').value;
    let password = document.getElementById('userPassword').value;
    
    let config = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            username: username,
            password: password
        })
    };
    
    fetch("http://localhost:8080/gng/member?action=signIn", config)
    .then((response) => response.json())
    .then((data) => {
		if (data.result) {
			alert("로그인 성공!");
			window.location.href = "http://localhost:8080/gng/member";	
		} else {
			alert("로그인 실패했습니다.");
		}
	});
	
});