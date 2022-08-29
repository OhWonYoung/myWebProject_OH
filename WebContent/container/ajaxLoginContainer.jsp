<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="container">
	<div class="inner-container">
		<div class="ajaxLogin">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="ajaxLogin-con">
							<form action="ajaxLoginOk.do" method="post" id="ajaxLoginOkForm">
								<h1 class="title">로그인</h1>
								<ul>
									<li>
										<label for="userId">아이디</label>
										<input type="text" name="userId" id="userId" />
									</li>
									<li>
										<label for="userPw">비밀번호</label>
										<input type="password" name="userPw" id="userPw" />
									</li>
									
									<li>
										<a href="joinView.do" class="b1">회원가입</a>
										<input type="button" value="로그인" id="ajaxLoginOkBtn" class="b1" />
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