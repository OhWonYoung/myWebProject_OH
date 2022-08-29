<%@page import="org.project.dto.Member_DTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	int maxBoardNo = (int) request.getAttribute("maxBoardNo");
	Date date = new Date();
	SimpleDateFormat boardWriteDate = new SimpleDateFormat("yyyyMMddHHmm");
	String BWDate = boardWriteDate.format(date);
%>
<c:set var="sessionId" value="${sessionScope.sessionId }" />
<div class="container">
	<div class="inner-container">
		<div class="boardWrite">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="boardWrite-con">
							<form action="boardWriteOk.bo" method="post"
								id="boardWriteOkForm">
										<h1 class="title">새 	글쓰기</h1>
								<ul>
									<li>
										<label for="boardTitle">글 제목</label>
										<input type="text" name="boardTitle" id="boardTitle" />
										<select name="boardType" id="boardType">
											<option value="null">게시판 선택</option>
											<option value="1">영화</option>
											<option value="2">등산</option>
											<option value="3">게임</option>
											<c:if test="${sessionId eq 'admin' }">
											<option value="4"selected>공지</option>
											</c:if>
										</select>
										<c:choose>
											<c:when test="${sessionId eq 'admin' }">
											<select name="boardLevel" id="boardLevel">
												<option value="null">글선택</option>
												<option value="1">일반글</option>
												<option value="2">게시판공지</option>
													<c:if test="${sessionId eq 'admin' }">
												<option value="3" selected>전체공지</option>
											</c:if>
											</select>
											</c:when>
											 <c:otherwise>
											 <input type="hidden" name="boardLevel" id="boardLevel"
											 	value="1" />
											 </c:otherwise>
										</c:choose>
									</li>
									<li style="display: none;">
										<input type="hidden" name="boardNo" id="boardNo" value="<%=maxBoardNo %>" />
										<input type="hidden" name="userId" id="userId" value="${sessionId }" />
										<input type="hidden" name="boardReply" id="boardReply" value="1" />
										<input type="hidden" name="boardWriteDate" id="boardWriteDate" value="<%=BWDate %>" />
										
									</li>
									<li>
									<c:choose>
										<c:when test="${sessionId eq 'admin' }">
										<textarea name="boardContent" id="boardContent" cols="40" rows="30" 
										style="width: 100%;	height: auto;" wrap="hard"
										placeholder="전체공지 : 공지 , 게시판 공지 : 각 게시판별로 작성 꼭 준수 해주세요."></textarea>
										</c:when>
										<c:otherwise>
										<textarea name="boardContent" id="boardContent" cols="40" rows="30" 
										style="width: 100%;	height: auto;" wrap="hard"
										placeholder="부적절한 내용의 글은 경고없이 삭제 및 블라인드 처리가 될 수 있습니다."></textarea>
										</c:otherwise>
									</c:choose>
									</li>
									
									<li><a href="boardListView.bo" class="b1">취소</a> 
									<input type="button" value="글작성" id="boardWriteOkBtn" class="b1" />
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