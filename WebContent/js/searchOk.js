"use strict";

const searchOkForm = document.querySelector("#searchOkForm");
const searchOkBtn = document.querySelector("#searchOkBtn");
const search_tag = document.querySelector("#search_tag");
const search = document.querySelector("#search");

searchOkBtn.addEventListener('click',()=>{
	
	if(search_tag.value.length<=0||search_tag.value=="null"){
		alert("검색타입을 선택해주세요.")
		search_tag.focus();
		return false;
	}
	if(search.value.length<=0||search.value==""){
		alert("검색내용을 입력해주세요.")
		search.focus();
		return false;
	}
	
	
	searchOkForm.submit();
});





