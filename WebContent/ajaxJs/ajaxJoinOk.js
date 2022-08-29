

const ajaxJoinOkBtn = document.querySelector("#ajaxJoinOkBtn");
const userId = document.querySelector("#userId");
const userPw1 = document.querySelector("#userPw1");
const userPw2 = document.querySelector("#userPw2");
const p1 = document.querySelector("#p1");
const userName = document.querySelector("#userName");
const jumin = document.querySelector("#jumin");
const userLevel = document.querySelector("#userLevel");
const idCheckedOkBtn = document.querySelector("#idCheckedOkBtn");


userPw2.addEventListener('keyup',pwChecked);


function pwChecked() {
	let rs = false;
	if(userPw1.value==userPw2.value){
		p1.innerText="일치";
		p1.style.color="green";
		rs= true
	
	}else if(userPw1==null||userPw1.value.length<=0){
		return false;
	}else if(userPw2==null||userPw2.value.length<=0){
		return false;
	}else{
		p1.innerText="불일치";
		p1.style.color="red";
		userPw2.focus();
		rs=false;
	}
	return rs;
}

const xhr = new XMLHttpRequest();

idCheckedOkBtn.addEventListener('click',()=>{
	
	const data = "userId="+userId.value;
	const url ="ajaxIdChecked.ax";
	
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded;charset=UTF8");
	xhr.onreadystatechange=function(){
		if(xhr.readyState==4 && xhr.status==200){
			const rs = xhr.responseText;
			
			console.log(rs+"<< rs")
			if(rs!=0){
				//실패
				alert("중복 아이디입니다.");
				userId.classList.add('idError');
				userId.classList.remove('idPass');
				userId.focus();
			}else{
				//성공
				alert("사용가능 아이디입니다.");
				userId.classList.add('idPass');
				userId.classList.remove('idError');
				userPw1.focus();
			}
		}
	}
	xhr.send(data);
});

ajaxJoinOkBtn.addEventListener('click',()=>{
	
	if(userId==null||userId.value.length<=0){
		alert("아이디 오류");
		userId.focus();
		return false;
	}
	if(!userId.classList.contains('idPass')){
		alert("아이디 중복체크 오류")
		userId.focus();
		return false;
	}
	if(!pwChecked()){
		alert("비밀번호일치 오류");
		userPw2.focus();
		return false;
	}
	if(userName==null||userName.value.length<=0){
		alert("이름 오류");
		userName.focus();
		return false;
	}
	if(jumin==null||jumin.value.length<=0){
		alert("주민번호 오류");
		jumin.focus();
		return false;
	}
	if(userLevel=='null'){
		alert("유저등급 오류");
		userLevel.focus();
		return false;
	}
	ajaxJoinOk();
});

 function ajaxJoinOk() {
	
	
	let data = "userId="+userId.value+"&";
		data +="userPw="+userPw2.value+"&";
		data +="userName="+userName.value+"&";
		data +="jumin="+jumin.value+"&";
		data +="userLevel="+userLevel.value;
	const url ="ajaxJoinOk.ax";
	
	xhr.open("POST",url,true);
	xhr.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded;charset=UTF8");

	xhr.onreadystatechange=function(){
		if(xhr.readyState==4&&xhr.status==200){
			const rs = xhr.responseText;
			if(rs==1){
				//성공
				location.herf="ajaxLogin.jsp";
			}else{
				location.herf="ajaxJoin.jsp";
			}
		}
	}
	xhr.send(data);
 }



