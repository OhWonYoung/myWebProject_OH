<%@page import="org.project.dto.Paging_DTO"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="org.project.dto.Board_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	ArrayList<Board_DTO> boardList = (ArrayList<Board_DTO>) request.getAttribute("boardList");
	Paging_DTO paging = (Paging_DTO)request.getAttribute("paging");
	SimpleDateFormat joinDate = new SimpleDateFormat("yyyyMMdd");
	Date date = new Date();
	int nYear = Integer.parseInt(joinDate.format(date));
%>
<div class="container">
	<div class="inner-container">
		<div class="boardList">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="boardList-con">
							<h1 class="title">글목록 조회</h1>
							<table>
								<thead>
									<tr>
										<th colspan="3">
										<%-- 
										<a href="boardListView.bo" class="bt">전체글</a>
										<a href="boardTypeList.bo?boardType=1" class="bt">영화</a>
										<a href="boardTypeList.bo?boardType=2" class="bt">등산</a>
										<a href="boardTypeList.bo?boardType=3" class="bt">게임</a>
										 --%>
										<a href="boardWriteView.bo?userId=${sessionScope.sessionId }" class="bt">글쓰기</a>
											</th>
											<th colspan="2">
											<form action="searchOk.bo" method="post" id="searchOkForm">
												<select name="search_tag" id="search_tag" class="bs1">
													<option value="null">::검색선택::</option>
													<option value="boardTitle">제목</option>
													<option value="boardContent">글내용</option>
													<option value="userId">작성자</option>
												</select>
												<input type="text" name="search" id="search" class="bs2" 
													placeholder="검색어를 입력해주세요"/>
												<input type="button" value="검색" id="searchOkBtn" class="bs3"/>
											</form>
											</th>	
									</tr>
									<tr>
										<th class="h1">비고</th>
										<th class="h1">게시판</th>
										<th class="h3">제목</th>
										<th class="h2">작성자</th>
										<th class="h2">작성일</th>
									</tr>
								</thead>
								<tbody>
										<c:if test="${empty boardList }">
										<tr>
											<th colspan="5">게시글이 없습니다.</th>
										</tr>
										</c:if>
									<c:forEach var="list" items="${boardList }">
										<c:set var="nYear" value="<%=nYear%>" />
										<c:set var="bYear"
											value="${list.boardWriteDate.substring(0,8) }" />
										<c:set var="wYear"
											value="${list.boardWriteDate.substring(0,4) }" />
										<c:set var="wMonth"
											value="${list.boardWriteDate.substring(4,6) }" />
										<c:set var="wDay"
											value="${list.boardWriteDate.substring(6,8) }" />
										<c:set var="wHour"
											value="${list.boardWriteDate.substring(8,10) }" />
										<c:set var="wMinute"
											value="${list.boardWriteDate.substring(10,12) }" />
										<c:set var="bLevel" value="${list.boardLevel }" />
										<c:set var="bType" value="${list.boardType}" />
										<%-- 글 종류			     --%>
											<c:choose>
												<c:when test="${(bLevel > 2) and (requestScope.paging.page eq 1)}">
													<tr class="c1"><%-- 전체공지 --%>
												</c:when>											
												<c:when test="${bLevel eq 2 and (requestScope.paging.page eq 1)}">
													<tr class="c2"><%--게시판공지 --%>
												</c:when>
												<c:otherwise>
													<tr><%-- 일반글 --%>
												</c:otherwise>
											</c:choose>
												<td><c:choose>
														<c:when test="${bLevel eq 1}">일반</c:when>
														<c:when test="${bLevel eq 2}">게시판공지</c:when>
														<c:when test="${bLevel eq 3}">전체공지</c:when>
													</c:choose></td>
												<td>
												<c:choose>
													<c:when test="${bType eq 1}">영화</c:when>
													<c:when test="${bType eq 2}">등산</c:when>
													<c:when test="${bType eq 3}">게임</c:when>
													<c:when test="${bType eq 4}">공지</c:when>
												</c:choose>
												</td>
														<c:choose>
													<c:when test="${(list.boardReply eq 2) and (bLevel eq 1)}">
													<%-- 댓글 --%>
													<td class="a1"><a href="boardView.bo?boardSerial=${list.boardSerial }">&nbsp;&nbsp;ㄴ
														${list.boardTitle }</a></td>
													</c:when>
													<%-- 대댓글 --%>
													<c:when test="${(list.boardReply > 2) and (bLevel eq 1)}">
														<td class="a1">
														<a href="boardView.bo?boardSerial=${list.boardSerial }">&nbsp;&nbsp;&nbsp;&nbsp;ㄴ${list.boardTitle }</a></td>
													</c:when>
													<c:otherwise>
													<%-- 일반글 --%>
												<td class="a1"><a href="boardView.bo?boardSerial=${list.boardSerial }">${list.boardTitle }</a></td>
													</c:otherwise>
												</c:choose>
												<td>${list.userId }</td>
												<td><c:choose>
														<c:when test="${nYear-bYear < 1 }">
															${wHour}:${wMinute}
														</c:when>
														<c:when test="${nYear-bYear >= 1 }">
															${wYear}-${wMonth}-${wDay}
														</c:when>
													</c:choose></td>
											</tr>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th colspan="5" id="paging">
										<c:set var="paging" value="${requestScope.paging}" />
									 	<c:url var="action" value="boardListView.bo" />
										<c:if test="${paging.prev}">
											<a href="${action}?page=${paging.beginPage-1}">prev</a>
										</c:if>
										<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
											<c:choose>
												<c:when test="${paging.page==index}">${index}</c:when>
												<c:otherwise>
													<a href="${action}?page=${index}">${index}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${paging.next}">
											<a href="${action}?page=${paging.endPage+1}">next</a>
										</c:if>
										</th>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>