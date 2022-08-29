<%@page import="org.project.dto.Member_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String sessionId =(String)session.getAttribute("sessionId");
	String uLevel = (String)session.getAttribute("uLevel");
//헤더로 세션을 받아서 처리해준다.
%>
<div class="ajaxHeader">
	<div class="inner-ajaxHeader">
		<div class="nav">
			<div class="inner-nav">
				<h1 class="logo"><a href="index.do">LOGO</a></h1>
				<div class="gnb">
					<ul>
						<li><a href="index.ax">HOME</a></li>
						<li><a href="index.do">NORMAL</a></li>
						<li><a href="boardListView.ax">게시판</a></li>
						<c:set var="userId" value="${sessionId }"/>
						<c:choose>
							<c:when test="${userId eq 'admin' }">
								<li><a href="memberListView.ax">회원목록조회</a></li>								
								<li><a href="memberView.ax?userId=${userId}&commentUserId=${userId}">마이프로필</a></li>								
								<li><a href="logoutOk.do">로그아웃</a></li>
								<li><a href="#">${sessionScope.sessionId}</a></li>	
							</c:when>
							<c:when test="${not empty userId }">
								<li><a href="memberView.ax?userId=${userId}&commentUserId=${userId}">마이프로필</a></li>								
								<li><a href="logoutOk.do">로그아웃</a></li>
								<li><a href="#">${sessionScope.sessionId}</a></li>	
							</c:when>
							<c:otherwise>
								<li><a href="joinView.ax">회원가입</a></li>
								<li><a href="loginView.ax">로그인</a></li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>