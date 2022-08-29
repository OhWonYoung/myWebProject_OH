<%@page import="org.project.dto.Board_DTO"%>
<%@page import="org.project.dto.Member_DTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Board_DTO boardView = (Board_DTO) request.getAttribute("boardView");
	Date date = new Date();
	SimpleDateFormat boardReplyDate = new SimpleDateFormat("yyyyMMddHHmm");
	String BWDate = boardReplyDate.format(date);
%>
<c:set var="sessionId" value="${sessionScope.sessionId }" />
<c:set var="uLevel" value="${sessionScope.uLevel }" />
<c:set var="boardTitle" value="${requestScope.boardView.boardTitle }" />
<c:set var="boardContent"
	value="${requestScope.boardView.boardContent }" />
<c:set var="boardType" value="${requestScope.boardView.boardType }" />
<c:set var="boardLevel" value="${requestScope.boardView.boardLevel }" />
<c:set var="userId" value="${requestScope.boardView.userId}" />
<c:set var="boardReply" value="${requestScope.boardView.boardReply}" />
<c:set var="boardSerial" value="${requestScope.boardView.boardSerial}" />

<div class="container">
	<div class="inner-container">
		<div class="boardReply">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="boardView-con">
							<h1 class="title">게시글 내용</h1>
							<ul>
								<li><span class="boardName">글제목</span> <span
									class="boardTitle">${boardTitle}</span> <span class="boardType">
										<c:choose>
											<c:when test="${boardType eq 1 }">영화</c:when>
											<c:when test="${boardType eq 2 }">등산</c:when>
											<c:when test="${boardType eq 3 }">게임</c:when>
											<c:when test="${boardType eq 4 }">공지</c:when>
											<c:otherwise>오류</c:otherwise>
										</c:choose>

								</span> <span class="boardLevel"> <c:choose>
											<c:when test="${boardLevel eq 1 }">일반</c:when>
											<c:when test="${boardLevel eq 2 }">게시판공지</c:when>
											<c:when test="${boardLevel eq 3 }">전체공지</c:when>
											<c:otherwise>오류</c:otherwise>
										</c:choose>
								</span></li>
								<li><textarea name="boardContent" id="boardContent"
										cols="40" rows="30" wrap="hard" readonly="readonly">${boardContent}</textarea>
								</li>
								<li>
											<a href="boardListView.bo" class="b1">뒤로가기</a>
									</li>
							</ul>
						</div>
						<div class="boardReply-con">
							<form action="boardReplyOk.bo" method="post" id="boardReplyOkForm">
								<h1 class="title">댓글 쓰기</h1>
								<ul>
									<li>
										<label for="boardTitle">글 제목</label>
										<input type="text" name="boardTitle" id="boardTitle" />
										<input type="hidden" name="boardType" id="boardType"  value="${boardType}" />
										<input type="hidden" name="boardLevel" id="boardLevel"  value="${boardLevel}"/>
										<span class="boardType">
											<c:choose>
												<c:when test="${boardType eq 1}">영화</c:when>
												<c:when test="${boardType eq 2}">등산</c:when>
												<c:when test="${boardType eq 3}">게임</c:when>
												<c:when test="${boardType eq 4}">공지</c:when>
												<c:otherwise>오류</c:otherwise>
											</c:choose>
										</span>
										<span class="boardLevel">
										<c:choose>
											<c:when test="${boardLevel eq 1 }">일반</c:when>
											<c:when test="${boardLevel eq 2 }">게시판공지</c:when>
											<c:when test="${boardLevel eq 3 }">전체공지</c:when>
											<c:otherwise>오류</c:otherwise>
										</c:choose>
									</span>
									
									</li>
									<li style="display: none;">
										<input type="hidden" name="boardNo" id="boardNo" value="${requestScope.boardView.boardNo}" />
										<input type="hidden" name="userId" id="userId" value="${sessionId }" />
										<input type="hidden" name="boardReply" id="boardReply" value="${boardReply+1 }" />
										<input type="hidden" name="boardWriteDate" id="boardWriteDate" value="<%=BWDate %>" />
										
									</li>
									<li>
										<textarea name="boardContent" id="boardContent" cols="40" rows="30" 
										style="width: 100%;	height: auto;" wrap="hard"
										placeholder="부적절한 내용의 글은 경고없이 삭제 및 블라인드 처리가 될 수 있습니다."></textarea>
									</li>
									
									<li><a href="boardListView.bo" class="b1">취소</a> 
									<input type="button" value="댓글작성" id="boardReplyOkBtn" class="b1" />
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