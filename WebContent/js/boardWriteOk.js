"use strict";

const boardWriteOkForm = document.querySelector("#boardWriteOkForm");
const boardWriteOkBtn = document.querySelector("#boardWriteOkBtn");
const userId = document.querySelector("#userId");
const boardNo = document.querySelector("#boardNo");
const boardTitle = document.querySelector("#boardTitle");
const boardContent = document.querySelector("#boardContent");
const boardWriteDate = document.querySelector("#boardWriteDate");
const boardType = document.querySelector("#boardType");
const boardLevel = document.querySelector("#boardLevel");
const boardReply = document.querySelector("#boardReply");
	
//	유효성 검사
boardWriteOkBtn.addEventListener('click',()=>{
	if(userId==null||userId.value.length<=0){
		alert("아이디를 입력해주세요");
		userId.focus();
		return false;
	}
	if(boardNo==null||boardNo.value.length<=0){
		alert("글 번호를 입력해주세요");
		boardNo.focus();
		return false;
	}
	if(boardTitle==null||boardTitle.value.length<=0){
		alert("글 제목을 입력해주세요");
		boardTitle.focus();
		return false;
	}
	if(boardContent==null||boardContent.value.length<=0){
		alert("글 내용을 입력해주세요");
		boardContent.focus();
		return false;
	}
	if(boardWriteDate==null||boardWriteDate.value.length<=0){
		alert("작성일 오류");
		
		return false;
	}
	if(boardType.value=="null"||boardType.value.length<=0){
		alert("게시판오류");
		boardType.focus();
		return false;
	}
	if(boardLevel.value=="null"||boardLevel.value.length<=0){
		alert("게시글 레벨오류");
		boardLevel.focus();
		return false;
	}
	if(boardReply==null||boardReply.value.length<=0){
		alert("리플오류");
		boardReply.focus();
		return false;
	}
	
	alert("글작성 실행");
	boardWriteOkForm.submit();
});