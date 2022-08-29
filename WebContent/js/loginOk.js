"use strict";

const loginOkForm = document.querySelector("#loginOkForm");
const loginOkBtn = document.querySelector("#loginOkBtn");
const userId = document.querySelector("#userId");
const userPw = document.querySelector("#userPw");

loginOkBtn.addEventListener('click',()=>{
	
	if(userId==null||userId.value.length<=0){
		alert("아이디를 입력해주세요");
		userId.focus();
		return false;
	}


	if(userPw==null||userPw.value.length<=0){
		alert("비밀번호를 입력해주세요");
		userPw.focus();
		return false;
	}
	
	alert("로그인 실행");
	loginOkForm.submit();
});