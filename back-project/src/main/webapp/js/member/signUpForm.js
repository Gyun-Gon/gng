let userid = document.getElementById("userid");
userid.addEventListener('keyup', function (){
	let useridvalue = userid.value;
    
    let config = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userId: useridvalue,
        })
    };
    
    fetch("http://localhost:8080/gng/member?action=signInCheck", config)
    .then((response) => response.json())
    .then((data) => {
		let userIdSpan = document.getElementById("userid_check_span");
		if (data.memberExist) {
			userIdSpan.innerHTML = "사용 불가능합니다.";
			userIdSpan.classList.remove("available");
            userIdSpan.classList.add("not-available");
		} else {
			userIdSpan.innerHTML = "사용 가능합니다!!";
			userIdSpan.classList.remove("not-available");
            userIdSpan.classList.add("available");
		}
	});
	
});


let userPwdCheck = document.getElementById("userpwd_check");
let userPwd = document.getElementById("userpwd");

userPwdCheck.addEventListener('keyup', function (){	
	let userPwdSpan = document.getElementById("userpwd_check_span");
	if (userPwdCheck.value === userPwd.value) {
		userPwdSpan.innerHTML = "일치합니다!!";
		userPwdSpan.classList.remove("not-available");
        userPwdSpan.classList.add("available");
	} else {
		userPwdSpan.innerHTML = "비밀번호가 틀립니다.";
		userPwdSpan.classList.remove("available");
        userPwdSpan.classList.add("not-available");
	}
	
});

let signupBtn = document.getElementById("signup_btn");
signupBtn.addEventListener('click', function() {
	let useridvalue = document.getElementById("userid").value;
	let usernamevalue = document.getElementById("username").value;
	let userpwdvalue = document.getElementById("userpwd").value;
	let useremailvalue = document.getElementById("useremail").value;
	
	let config = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            userId: useridvalue,
            userName: usernamevalue,
            userPassword: userpwdvalue,
            userEmail: useremailvalue
        })
    };
    
    fetch("http://localhost:8080/gng/member?action=signUp", config)
    .then((response) => response.json())
    .then((data) => {
		if (data.result) {
			alert("회원가입 성공!");
			window.location.href = "http://localhost:8080/gng/member";	
		} else {
			alert("회원가입 할 수 없습니다.");
		}
	});
	    
})
