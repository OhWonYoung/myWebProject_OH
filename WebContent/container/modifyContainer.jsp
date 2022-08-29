<%@page import="org.project.dto.Member_DTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
Member_DTO memberView = (Member_DTO)request.getAttribute("memberView");
SimpleDateFormat joinDate = new SimpleDateFormat("yyyy년 MM월 dd일");
String jDate = joinDate.format(memberView.getJoinDate());
//현재시간을 SimpleDateFormat으로 원하는 형식으로 만들어 사용한다. 
%>
<div class="container">
	<div class="inner-container">
		<div class="modify">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="modify-con">
							<form action="modifyOk.do" method="post" id="modifyOkForm">
								<h1 class="title">회원수정</h1>
								<ul>
									<li>
										<label for="userId">아이디</label>
										<input type="hidden" name="userId" id="userId" value="${requestScope.memberView.userId }"/>
										<span class="mView">${requestScope.memberView.userId }</span>
									</li>
									<!--  비밀번호는 본인과 관리자만 수정할 수 있게 한다.  -->
									<c:choose>
												<%-- 세션아이디(로그인된)와 memberView로 전달받은 아이디가 같거나(본인) 세션아이디가 'admin'인사람(관리자) --%>
										<c:when test="${(sessionScope.sessionId eq requestScope.memberView.userId) or (sessionScope.sessionId eq 'admin') }">
											<li>
												<label for="userPw">비밀번호</label>
												<input type="password" name="userPw" id="userPw1" value="${requestScope.memberView.userPw }"/>
											</li>
											<li>
												<!-- 일치하지 않으면 회원가입을 할 수 없다. -->
												<span id="p1"></span>
												<input type="password" name="userPw" id="userPw2" value="${requestScope.memberView.userPw }"
													placeholder="비밀번호 확인"/>
											</li>
										</c:when>
									</c:choose>
									<li>
										<label for="userName">이름</label>
										<input type="text" name="userName" id="userName" value="${requestScope.memberView.userName }"/>
									</li>
									<!--  주민등록번호는 일반적으로 수정할 일이 없기 때문에 관리자만 수정할수 있게 한다.  -->
									<c:choose>
										<c:when test="${sessionScope.sessionId eq 'admin' }">
										<li>
											<label for="jumin">주민등록번호</label>
											<input type="text" name="jumin" id="jumin" maxlength="13"
												placeholder="하이픈(-) 없이  13자리" value="${requestScope.memberView.jumin }"/>
										</li>
										</c:when>
										<c:otherwise>
											<input type="hidden" name="jumin" id="jumin" maxlength="13"
												placeholder="하이픈(-) 없이  13자리" value="${requestScope.memberView.jumin }"/>
										</c:otherwise>
									</c:choose>
							<%-- 		<c:set var="userLevel" value="${requestScope.memberView.userLevel }"/> --%>
									<c:choose>
										<c:when test="${sessionScope.sessionId eq 'admin' }">
									<li>
										<label for="joinDate">가입일</label>
										<input type="text" name="joinDate" id="joinDate" value="${requestScope.memberView.joinDate }"
										placeholder="yyyyMMdd형식으로 입력"/>
									</li>
										<!-- 관리자 등급(UserLevel=3)만 볼수 있는 메뉴 -->
											<li>
												<label for="userLevel">유저권한</label>
												<select name="userLevel" id="userLevel">
													<option value="null">::선택::</option>
													<option value="1"<%if(memberView.getUserLevel()==1){%>selected<%}%> >일반유저</option>
													<%--<option value="2"<%if(memberView.getUserLevel()==2){%>selected<%}%> >매니저</option> --%>
													<option value="3"<%if(memberView.getUserLevel()==3){%>selected<%}%> >관리자</option>
												</select>
											</li>
											</c:when>
											<c:otherwise>
											<input type="hidden" name="joinDate" id="joinDate" value="${requestScope.memberView.joinDate }"/>
												<input type="hidden" name="userLevel" value="${requestScope.memberView.userLevel }" />												
											</c:otherwise>
										</c:choose>
									<li>
										<a href="modifyView.do?userId=${requestScope.memberView.userId }" class="b1">reset</a>
										<input type="button" value="수정실행" id="modifyOkBtn" class="b1" />
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