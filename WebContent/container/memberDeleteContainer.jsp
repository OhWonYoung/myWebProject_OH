<%@page import="org.project.dto.Member_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
Member_DTO memberView = (Member_DTO)request.getAttribute("memberView");
 %>
<div class="container">
	<div class="inner-container">
		<div class="memberDelete">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="memberDelete-con">
							<form action="memberDeleteOk.do" method="post" id="memberDeleteOkForm">
								<h1 class="title">회원탈퇴</h1>
								<ul>
									<li>
										<label for="userId">아이디</label>
										<input type="hidden" name="userId" id="userId" value="${requestScope.memberView.userId }"/>
										<span class="mView">${requestScope.memberView.userId }</span>
									</li>
									<c:choose>
										<c:when test="${sessionScope.sessionId eq 'admin' }">
											<li>
												<label for="userPw">비밀번호</label>
												<input type="password" name="userPw" id="userPw1" value="${requestScope.memberView.userPw }"/>
											</li>
											<li>
												<!-- 관리자가 유저의 탈퇴를 보다 편하게 하기위해 비밀번호를 자동입력 되게 해두었다. -->
												<span id="p1"></span>
												<input type="password" name="userPw" id="userPw2" value="${requestScope.memberView.userPw }"/>
											</li>
										</c:when>
										<c:otherwise>
											<li>
												<label for="userPw">비밀번호</label>
												<input type="password" name="userPw" id="userPw1" value="${requestScope.memberView.userPw }" />
											</li>
											<li>
												<!-- 비밀번호를 입력해야 탈퇴할 수 있도록 안전장치를 마련해둔다. -->
												<span id="p1"></span>
												<input type="password" name="userPw" id="userPw2" placeholder="비밀번호 확인"/>
											</li>
										
										</c:otherwise>
									</c:choose>
									<!-- 세션을 deleteCommend에 보내서 관리자와 일반 유저의 회원삭제를 구분 시켜준다. -->
									<li style="display:none;"><input type="text" name="sessionId" value="${sessionScope.sessionId }" /></li>
									<li>
										<a href="deleteView.do?userId=${requestScope.memberView.userId }" class="b1">reset</a>
										<input type="button" value="회원탈퇴" id="memberDeleteOkBtn" class="b1" />
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