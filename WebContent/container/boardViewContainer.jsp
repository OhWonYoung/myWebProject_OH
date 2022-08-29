<%@page import="java.util.Date"%>
<%@page import="org.project.dto.Comment_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.project.dto.Board_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	Board_DTO boardView = (Board_DTO) request.getAttribute("boardView");
	ArrayList<Comment_DTO> commentList = (ArrayList<Comment_DTO>) request.getAttribute("commentList");
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
<c:set var="boardNo" value="${requestScope.boardView.boardNo}" />

<div class="container">
	<div class="inner-container">
		<div class="boardView">
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
								<li><c:choose>
										<c:when test="${sessionId eq 'admin'  }">
											<a
												href="boardBlind.bo?boardSerial=${boardSerial}&boardTitle=${boardTitle}&boardContent=${boardContent}"
												class="b1">블라인드</a>
											<a href="boardDelete.bo?boardSerial=${boardSerial}&"
												class="b1">글삭제</a>
												<c:choose>
													<c:when test="${userId eq sessionId }">
													<a href="boardModifyView.bo?boardSerial=${boardSerial}" class="b1">글수정</a>
													</c:when>
													<c:when test="${not (userId eq sessionId)}">
											<a href="boardReplyView.bo?boardSerial=${boardSerial}&boardLevel=${boardLevel}&boardType=${boardType}" 
											class="b1">댓글달기</a>
													</c:when>
												</c:choose>
											<a href="boardListView.bo" class="b1">뒤로가기</a>
										</c:when>
										<c:when
											test="${not (userId eq sessionId)  and (uLevel eq '일반')}">
											<a href="boardListView.bo" class="b1">뒤로가기</a>
											<c:if test="${boardLevel eq 1 }">
												<a href="boardReplyView.bo?boardSerial=${boardSerial}" class="b1">댓글달기</a>
											</c:if>
										</c:when>
										<c:when test="${(userId eq sessionId) and (uLevel eq '일반')  }">
											<a href="boardListView.bo" class="b1">뒤로가기</a>
											<a href="boardDelete.bo?boardSerial=${boardSerial }"
												class="b1">글삭제</a>
													<a href="boardModifyView.bo?boardSerial=${boardSerial }" class="b1">글수정</a>
										</c:when>
										<c:when test="${empty sessionId}">
											<a href="boardListView.bo" class="b1">뒤로가기</a>
											<a href="joinView.do" class="b1">회원가입</a>
											<a href="loginView.do" class="b1">로그인</a>
										</c:when>
									</c:choose></li>
							</ul>
						</div>
						<div class="comment-con">
							<div class="comment-view">
								<h1 class="title">코멘트</h1>
								<div class="view-con">
									<table>
										<thead>
											<tr>
												<th class="t1">작성자</th>
												<th class="t2">내용</th>
												<th class="t3">작성시간</th>
											</tr>
										</thead>
										<tbody>
											<c:if test="${empty requestScope.commentList }">
												<tr>
													<td colspan="3">작성된 코멘트가 없습니다.</td>
												</tr>

											</c:if>
											<c:forEach var="comment" items="${requestScope.commentList }">
												<tr>
													<td>${comment.commentUserId }</td>
													<td>${comment.commentContent }</td>
													<td class="t4"><c:set var="cYear"
															value="${comment.commentDate.substring(0,4) }" /> <c:set
															var="cMonth"
															value="${comment.commentDate.substring(4,6) }" /> <c:set
															var="cDay" value="${comment.commentDate.substring(6,8) }" />
														<c:set var="cHour"
															value="${comment.commentDate.substring(8,10) }" /> <c:set
															var="cMinute"
															value="${comment.commentDate.substring(10,12) }" /> <c:out
															value="${cYear}-${cMonth}-${cDay}" /><br /> <c:choose>
															<c:when test="${sessionId eq comment.commentUserId}">
														${cHour}:${cMinute} 
														<a href="commentDeleteOk.co?boardSerial=${boardSerial}&commentSerial=${comment.commentSerial}">X</a>
															</c:when>
															<c:otherwise>
																<c:out value="${cHour}:${cMinute}" />
															</c:otherwise>
														</c:choose></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<div class="comment-write">
								<form action="commentWriteOk.co" method="post"
									id="commentWriteOkForm">
									<h1 class="title">코멘트 작성</h1>
									<div class="write-con">
										<ul>
											<li><textarea name="commentContent" id="commentContent"
													cols="50" rows="3"
													placeholder="코멘트 작성시 타인에 대한 배려와 책임을 담아주세요"></textarea></li>
											<li style="display: none;">
											<input type="hidden" name="commentUserId" id="commentUserId" value="${sessionId}" />
												<input type="text" name="commentDate" id="commentDate"
												value="<fmt:formatDate value="<%=new Date()%>" type="both" pattern="yyyyMMddhhmmss"/>" />
												<input type="text" name="commentReply" id="commentReply" value="1" />
												<input type="text" name="boardSerial" id="boardSerial"
												value="${boardSerial}" /></li>
											<li><input type="button" value="작성"
												id="commentWriteOkBtn" class="com1" /></li>
										</ul>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>	
		</div>
	</div>
</div>