"use strict";

const joinOkForm = document.querySelector("#joinOkForm");
const joinOkBtn = document.querySelector("#joinOkBtn");
const userId = document.querySelector("#userId");
const userPw1 = document.querySelector("#userPw1");
const userPw2 = document.querySelector("#userPw2");
const userName = document.querySelector("#userName");
const jumin = document.querySelector("#jumin");
const joinDate = document.querySelector("#joinDate");
const userLevel = document.querySelector("#userLevel");
const idCh = document.querySelector("#idCh");
const idChM = document.querySelector("#idChM");

const idCheckedOkBtn = document.querySelector("#idCheckedOkBtn");

const xhr = new XMLHttpRequest();
	
idCheckedOkBtn.addEventListener('click',()=>{

	if(userId.value==null||userId.value.length<=0){
		idChM.innerText="아이디를 입력해주세요";
		idChM.style.color="red";
		userId.focus();
		return false;
	}else{
	
	const date = "userId="+userId.value;
	const url = "idCheckedOk.do";
	
	xhr.open("post",url,true);
	
	xhr.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded;charset=utf8");

	xhr.onreadystatechange=function(){
		
		if(xhr.readyState==4 && xhr.status==200){
			const idChecked = xhr.responseText;
			
			idCh.value=idChecked;
			if(idChecked==1){
				console.log("seccess");
				idChM.innerText="중복 아이디입니다.";
				idChM.style.color="red";
				userId.focus();
			}else{
				idChM.innerText="사용가능한 아이디입니다.";
				idChM.style.color="green";
				userPw1.focus();
			}
			
		}else{
			console.log("Fail");
		}
	}
	xhr.send(date);
	}
});

const p1 = document.querySelector("#p1");
				// keyup -> 키보드를 누를때마다 이벤트가 실행됨
userPw2.addEventListener('keyup',passFun);
function passFun() {
	let rs = false;
	if(userPw1.value!=userPw2.value){
		p1.innerText="불일치";
		p1.classList.remove('p2');
		p1.style.color="red";
		//불일치 하면 붉은 색 글씨로 "불일치" 표기
		rs = false;
	// 비밀번호를 입력하지 않으면 false을 반환하게 한다.
	}else if(userPw1==null||userPw1.value.length<=0){
		return false;
	}else if(userPw2==null||userPw2.value.length<=0){
		return false;
	}else{
		p1.innerText="일치";
		p1.classList.add('p2');
		p1.style.color="green";	
		//일치 하면 녹색 글씨로 "일치" 표기
		rs = true;
	}
	return rs;
}
//	유효성 검사
joinOkBtn.addEventListener('click',()=>{
	if(userId==null||userId.value.length<=0){
		alert("아이디를 입력해주세요");
		userId.focus();
		return false;
	}
	if(idCh==null||idCh.value.length<=0){
		idChM.innerText = "아이디 체크를 해주세요";
		idChM.style.color="red";
		userId.focus();
		return false;
	}
	if(idCh.value!=0){
		idChM.innerText = "중복아이디 입니다.";
		idChM.style.color="red";
		userId.focus();
		return false;
	}
	if(!passFun()){
		//비밀번호와 비밀번호 확인에 같은 값이 들어왔는지 체크하고
		//값이 다르다면 회원가입을 하지 못하게 한다.
		alert("비밀번호가 일치하지 않습니다");
		userPw2.focus();
		return false;
	}
	if(userName==null||userName.value.length<=0){
		alert("이름을 입력해주세요");
		userName.focus();
		return false;
	}
	if(jumin==null||jumin.value.length<=0){
		alert("주민등록번호를 입력해주세요");
		jumin.focus();
		return false;
	}
	if(joinDate==null||joinDate.value.length<=0){
		alert("가입일을 입력해주세요");
		joinDate.focus();
		return false;
	}
	if(userLevel=="null"){
		alert("유저권한을 선택해주세요");
		userLevel.focus();
		return false;
	}
	
	alert("회원가입 실행");
	joinOkForm.submit();
});