"use strict";

const boardModifyOkForm = document.querySelector("#boardModifyOkForm");
const boardModifyOkBtn = document.querySelector("#boardModifyOkBtn");
const boardTitle = document.querySelector("#boardTitle");
const boardContent = document.querySelector("#boardContent");
	
//	유효성 검사
boardModifyOkBtn.addEventListener('click',()=>{

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
	alert("글작성 실행");
	boardModifyOkForm.submit();
});