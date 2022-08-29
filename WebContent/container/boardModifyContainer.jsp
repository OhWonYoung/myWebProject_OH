<%@page import="org.project.dto.Board_DTO"%>
<%@page import="org.project.dto.Member_DTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Board_DTO boardModifyView = (Board_DTO) request.getAttribute("boardModifyView");
%>
<c:set var="sessionId" value="${sessionScope.sessionId }" />
<c:set var="uLevel" value="${sessionScope.uLevel }" />
<c:set var="boardTitle" value="${requestScope.boardModifyView.boardTitle }" />
<c:set var="boardContent"
	value="${requestScope.boardModifyView.boardContent }" />
<c:set var="boardType" value="${requestScope.boardModifyView.boardType }" />
<c:set var="boardLevel" value="${requestScope.boardModifyView.boardLevel }" />
<c:set var="userId" value="${requestScope.boardModifyView.userId}" />
<c:set var="boardReply" value="${requestScope.boardModifyView.boardReply}" />
<c:set var="boardSerial" value="${requestScope.boardModifyView.boardSerial}" />
<c:set var="boardNo" value="${requestScope.boardModifyView.boardNo}" />
<div class="container">
	<div class="inner-container">
		<div class="boardModify">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="boardModify-con">
							<form action="boardModifyOk.bo" method="post"
								id="boardModifyOkForm">
										<h1 class="title">글 수정</h1>
								<ul>
										<li>
										<label for="boardTitle">글제목</label>
										<input type="text" name="boardTitle" id="boardTitle" value="${boardTitle}" />
										 <input type="hidden" name="boardSerial" id="boardSerial" value="${boardSerial}" />
										 <span class="boardType">
										<c:choose>
											<c:when test="${boardType eq 1 }">영화</c:when>
											<c:when test="${boardType eq 2 }">등산</c:when>
											<c:when test="${boardType eq 3 }">게임</c:when>
											<c:when test="${boardType eq 4 }">공지</c:when>
											<c:otherwise>오류</c:otherwise>
										</c:choose>
								</span>
								 <span class="boardLevel"> <c:choose>
											<c:when test="${boardLevel eq 1 }">일반</c:when>
											<c:when test="${boardLevel eq 2 }">게시판공지</c:when>
											<c:when test="${boardLevel eq 3 }">전체공지</c:when>
											<c:otherwise>오류</c:otherwise>
										</c:choose>
								</span></li>
										
									<li>
										<textarea name="boardContent" id="boardContent" cols="40" rows="30" 
										style="width: 100%;	height: auto;" wrap="hard"
										placeholder="부적절한 내용의 글은 경고없이 삭제 및 블라인드 처리가 될 수 있습니다.">${boardContent }</textarea>
									</li>
									
									<li><a href="boardListView.bo" class="b1">취소</a> 
									<input type="button" value="글수정" id="boardModifyOkBtn" class="b1" />
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