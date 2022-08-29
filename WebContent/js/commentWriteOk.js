"use strict";

const commentWriteOkForm = document.querySelector("#commentWriteOkForm");
const commentWriteOkBtn = document.querySelector("#commentWriteOkBtn");
const boardSerial = document.querySelector("#boardSerial");
const commentUserId = document.querySelector("#commentUserId");
const commentContent = document.querySelector("#commentContent");
const commentDate = document.querySelector("#commentDate");
const commentReply = document.querySelector("#commentReply");
	
//	유효성 검사
commentWriteOkBtn.addEventListener('click',()=>{
	if(boardSerial==null||boardSerial.value.length<=0){
		alert("게시글번호 입력해주세요");
		boardSerial.focus();
		return false;
	}
	if(commentUserId==null||commentUserId.value.length<=0){
		alert("작성자를 입력해주세요");
		commentUserId.focus();
		return false;
	}
	if(commentContent==null||commentContent.value.length<=0){
		alert("코멘트을 입력해주세요");
		commentContent.focus();
		return false;
	}
	if(commentDate==null||commentDate.value.length<=0){
		alert("작성일을 입력해주세요");
		commentDate.focus();
		return false;
	}
	
	if(commentReply==null||commentReply.value.length<=0){
		alert("리플번호 오류");
		commentReply.focus();
		return false;
	}
	
	alert("코멘트 작성 실행");
	commentWriteOkForm.submit();
});