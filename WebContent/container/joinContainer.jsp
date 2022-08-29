<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
Date date = new Date();
SimpleDateFormat joinDate = new SimpleDateFormat("yyyyMMdd");
//현재시간을 SimpleDateFormat으로 원하는 형식으로 만들어 사용한다. 
%>
<div class="container">
	<div class="inner-container">
		<div class="join">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="join-con">
							<form action="joinOk.do" method="post" id="joinOkForm">
								<h1 class="title">회원가입</h1>
								<ul>
									<li>
										<label for="userId">아이디</label>
										<input type="text" name="userId" id="userId" />
										<input type="button" name="idChecked" id="idCheckedOkBtn" class="idBtn" value="중복확인"/>
										<input type="hidden" name="idCh" id="idCh" />
									</li>
									<li>
										<span id="idChM"></span>
									</li>
									<li>
										<label for="userPw">비밀번호</label>
										<input type="password" name="userPw" id="userPw1" />
									</li>
									<li>
										<!-- 일치하지 않으면 회원가입을 할 수 없다. -->
										<span id="p1"></span>
										<input type="password" name="userPw" id="userPw2" placeholder="비밀번호 확인"/>
									</li>
									<li>
										<label for="userName">이름</label>
										<input type="text" name="userName" id="userName" />
									</li>
									<li>
										<label for="jumin">주민등록번호</label>
										<input type="text" name="jumin" id="jumin" maxlength="7"
											placeholder="하이픈(-) 없이  7자리"/>
											<!-- 입력을 13자리만 받을수 있게 만들어 혹시 모를 오류를 줄여준다. -->
									</li>
									<li style="display: none;">
										<!-- 가입일을 현재시간을 String으로 형변환을 해서 넣어주며, 임의로 기제가 불가능 하도록 아에 보이지 않게 처리한다. -->
										<label for="joinDate">가입일</label>
										<input type="hidden" name="joinDate" id="joinDate" value="<%=joinDate.format(date).toString() %>"/>
									</li>
									
									<li style="display: none;">
										<!-- 유저권한은 보이지 않게 해서 기본적으로 일반 유저로 가입하게하고 추후 admin계정이 수정할 수 있게 해준다.-->
										<label for="userLevel">유저권한</label>
										<select name="userLevel" id="userLevel">
											<option value="null">::선택::</option>
											<option value="1" selected>일반유저</option>
											<option value="2">매니저</option>
											<option value="3">관리자</option>
										</select>
									</li>
									<li>
										<a href="index.do" class="b1">HOME</a>
										<input type="button" value="회원가입" id="joinOkBtn" class="b1" />
									</li>
								</ul>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>