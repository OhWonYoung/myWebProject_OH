

const ajaxLoginOkBtn = document.querySelector("#ajaxLoginOkBtn");
const userId = document.querySelector("#userId");
const userPw = document.querySelector("#userPw");

const xhr = new XMLHttpRequest();

ajaxLoginOkBtn.addEventListener('click',()=>{
	
	if(userId==null||userId.value.length<=0){
		alert("아이디 오류");
		userId.focus();
		return false;
	}
	if(userPw==null||userPw.value.length<=0){
		alert("비밀번호 오류");
		userPw.focus();
		return false;
	}
	loginOk();
});

function loginOk(){
	
	let data = "userId="+userId.value+"&";
		data +="userPw="+userPw.value+"&";
	const url = "ajaxLoginOk.ax";
	
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded;charset=UTF8");
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4&&xhr.status==200){
			const rs = xhr.responseText;
			if(rs==1){
			// 성공
				location.href="index.ax";
			}else{
			// 실패
			location.href="loginView.ax";
			}
		}
	}
	xhr.send(data);
}