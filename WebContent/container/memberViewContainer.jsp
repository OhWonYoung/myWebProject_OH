<%@page import="org.project.dto.memberCommentPaging_DTO"%>
<%@page import="org.project.dto.memberViewPaging_DTO"%>
<%@page import="org.project.dto.Board_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.project.dto.Member_DTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	memberViewPaging_DTO paging =(memberViewPaging_DTO) request.getAttribute("paging");
	memberCommentPaging_DTO paging2 =(memberCommentPaging_DTO) request.getAttribute("paging2");
ArrayList<Board_DTO> boardList = (ArrayList<Board_DTO>) request.getAttribute("boardList");
	Member_DTO memberView = (Member_DTO) request.getAttribute("memberView");
	SimpleDateFormat joinDate = new SimpleDateFormat("yyyyMMdd");
	String jDate = joinDate.format(memberView.getJoinDate());
	Date date = new Date();
	int nYear = Integer.parseInt(joinDate.format(date));
	int nMonth = date.getMonth() + 1;
	int nDay = date.getDay();
	int nDate = nMonth * 100 + nDay;
%>
<c:set var="userId" value="${requestScope.memberView.userId }" />
<c:set var="sessionId" value="${sessionScope.sessionId }" />
<div class="container">
	<div class="inner-container">
		<div class="memberView">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="memberView-con">
							<form action="memberViewOk.do" method="post"
								id="memberViewOkForm">
								<c:choose>
									<c:when test="${userId eq sessionId}">
										<h1 class="title">회원 정보</h1>
									</c:when>
									<c:otherwise>
								<h1 class="title">${userId}님의 회원 정보</h1>
									</c:otherwise>
								</c:choose>
								<ul>
									<li><label for="userId">아이디</label> <span class="mView">${requestScope.memberView.userId }</span>
									</li>
									<li><label for="userName">이름</label> <span class="mView">${requestScope.memberView.userName }</span>
									</li>
									<!-- 생일 및 성별은 유저 본인만 확인 할 수 있게 했다. -->
									<c:if
										test="${sessionScope.sessionId eq requestScope.memberView.userId }">
										<li><label for="jumin">생일</label> <span class="mView">
												<!-- gen 성별 (주민번호 7번째 index(1,2,3,4,)  --> <c:set var="gen"
													value="${requestScope.memberView.jumin.substring(6,7) }" />
												<!-- 태어난 bYear 년, bMonth 월, bDay 일 --> <c:set var="bYear"
													value="${requestScope.memberView.jumin.substring(0,2) }" />
												<c:set var="bMonth"
													value="${requestScope.memberView.jumin.substring(2,4) }" />
												<c:set var="bDay"
													value="${requestScope.memberView.jumin.substring(4,6) }" />
												<c:choose>
													<c:when test="${gen eq 1 or gen eq 2	}">
															${1900 + bYear}년
															${bMonth} 월
															${bDay} 일
															<%-- bathYear 생년 --%>
														<c:set var="bathYear" value="${1900 + bYear }" />
													</c:when>
													<c:when test="${gen eq 2 or gen eq 4	}">
															${2000 + bYear}년
															${bMonth} 월
															${bDay} 일
															<%-- bathYear 생년 --%>
														<c:set var="bathYear" value="${2000 + bYear }" />
													</c:when>
													<c:otherwise>
												출력오류
											</c:otherwise>
												</c:choose>
										</span></li>
										<%-- 띠 계산  --%>
										<c:set var="animalNum" value="${bathYear%12 }" />
										<c:if test="${animalNum==0 }"><c:set var="animal" value="원숭이" /></c:if>
										<c:if test="${animalNum==1 }"><c:set var="animal" value="닭" /></c:if>
										<c:if test="${animalNum==2 }"><c:set var="animal" value="개" /></c:if>
										<c:if test="${animalNum==3 }"><c:set var="animal" value="돼지" /></c:if>
										<c:if test="${animalNum==4 }"><c:set var="animal" value="쥐" /></c:if>
										<c:if test="${animalNum==5 }"><c:set var="animal" value="소" /></c:if>
										<c:if test="${animalNum==6 }"><c:set var="animal" value="호랑이" /></c:if>
										<c:if test="${animalNum==7 }"><c:set var="animal" value="토끼" /></c:if>
										<c:if test="${animalNum==8 }"><c:set var="animal" value="용" /></c:if>
										<c:if test="${animalNum==9 }"><c:set var="animal" value="뱀" /></c:if>
										<c:if test="${animalNum==10 }"><c:set var="animal" value="말" /></c:if>
										<c:if test="${animalNum==11 }"><c:set var="animal" value="양" /></c:if>
										<li class="age_gen"><c:set var="nowYear"
												value="<%=(nYear - nDate) / 10000%>" /> <%-- nowDate 오늘날짜 bathDate 생일날짜  --%>
											<c:set var="nowDate" value="<%=nMonth * 100 + nDay%>" /> <c:set
												var="bathDate" value="${bMonth*100+bDay }" /> <label
											for="americaAge">나이</label> <span class="mView"> <c:choose>
													<c:when test="${bathDate < nowDate}">
											만	${nowYear-bathYear}살, ${animal }띠
											</c:when>
													<c:when test="${bathDate > nowDate}">
											만	${nowYear-bathYear-1}살, ${animal }띠
											</c:when>
													<c:otherwise>
												출력오류
											</c:otherwise>
												</c:choose>
										</span> <label for="gen">성별</label> <span class="mView"> <c:choose>
													<c:when test="${gen eq 1 or gen eq 3 }">
													남자
												</c:when>
													<c:when test="${gen eq 2 or gen eq 4 }">
													여자
												</c:when>
													<c:otherwise>
												출력오류
											</c:otherwise>
												</c:choose>
										</span></li>
									</c:if>
									<li><label for="joinDate">가입일</label> <c:set var="jDate"
											value="<%=jDate%>" /> <span class="mView">
											${jDate.substring(0,4)}년 ${jDate.substring(4,6)}월
											${jDate.substring(6)}일 </span></li>
									<li><label for="userLevel">유저권한</label> <c:set
											var="userLevel" value="${requestScope.memberView.userLevel }" />
										<c:choose>
											<c:when test="${userLevel eq '1'}">
												<span class="mView">일반유저</span>
											</c:when>
											<c:when test="${userLevel eq '2'}">
												<span class="mView">매니저</span>
											</c:when>
											<c:when test="${userLevel eq '3'}">
												<span class="mView">관리자</span>
											</c:when>
											<c:otherwise>
												<span class="mView">권한설정 오류</span>
											</c:otherwise>
										</c:choose></li>
									<li><a href="index.do" class="b1">HOME</a> <a
										href="deleteView.do?userId=${requestScope.memberView.userId }"
										class="b1">회원탈퇴</a> <a
										href="modifyView.do?userId=${requestScope.memberView.userId }"
										class="b1">회원수정</a></li>
								</ul>
							</form>
						</div>
						<div class="contentView-con">
							<div class="boardView-con">
							<c:choose>
									<c:when test="${userId eq sessionId}">
								<h1 class="title">내가 쓴 글</h1>
									</c:when>
									<c:otherwise>
								<h1 class="title">${userId}님이 쓴 글</h1>
									</c:otherwise>
									</c:choose>
								
								<table>
								<thead>
									<tr>
										<th class="h1">비고</th>
										<th class="h1">게시판</th>
										<th class="h3">제목</th>
										<th class="h2">작성일</th>
									</tr>
								</thead>
								<tbody>
										<c:if test="${empty boardList }">
										<tr>
											<th colspan="4">게시글이 없습니다.</th>
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
										<%--   						 전체공지  													     --%>
										<c:if test="${bLevel > 2 }">
											<tr class="c1">
												<th><c:choose>
														<c:when test="${bLevel eq 1}">일반</c:when>
														<c:when test="${bLevel eq 2}">게시판공지</c:when>
														<c:when test="${bLevel eq 3}">전체공지</c:when>
													</c:choose></th>
												<th>
												<c:choose>
													<c:when test="${bType eq 1}">영화</c:when>
													<c:when test="${bType eq 2}">등산</c:when>
													<c:when test="${bType eq 3}">게임</c:when>
													<c:when test="${bType eq 4}">공지</c:when>
												</c:choose>
												</th>
												<th><a href="boardView.bo?boardSerial=${list.boardSerial }">${list.boardTitle }</a></th>
												<th><c:choose>
														<c:when test="${nYear-bYear < 1 }">
															${wHour}:${wMinute}
														</c:when>
														<c:when test="${nYear-bYear >= 1 }">
															${wYear}-${wMonth}-${wDay}
														</c:when>
													</c:choose></th>
											</tr>
									</c:if>
									<%--   						 게시판공지  													     --%>
										<c:if test="${bLevel eq 2 }">
											<tr class="c2">
												<th><c:choose>
														<c:when test="${bLevel eq 1}">일반</c:when>
														<c:when test="${bLevel eq 2}">게시판공지</c:when>
														<c:when test="${bLevel eq 3}">전체공지</c:when>
													</c:choose></th>
													
												<th>
												<c:choose>
													<c:when test="${bType eq 1}">영화</c:when>
													<c:when test="${bType eq 2}">등산</c:when>
													<c:when test="${bType eq 3}">게임</c:when>
													<c:when test="${bType eq 4}">공지</c:when>
												</c:choose>
												</th>
												<th><a href="boardView.bo?boardSerial=${list.boardSerial }">${list.boardTitle }</a></th>
												<th><c:choose>
														<c:when test="${nYear-bYear < 1 }">
															${wHour}:${wMinute}
														</c:when>
														<c:when test="${nYear-bYear >= 1 }">
															${wYear}-${wMonth}-${wDay}
														</c:when>
													</c:choose></th>
											</tr>
										</c:if>
										<%--   						 일반게시글  													     --%>
										<c:if test="${(list.boardReply eq 1) and (bLevel eq 1) }">
											<tr class="c3">
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
												<td><a href="boardView.bo?boardSerial=${list.boardSerial }">${list.boardTitle }</a></td>
												<td><c:choose>
														<c:when test="${nYear-bYear < 1 }">
															${wHour}:${wMinute}
														</c:when>
														<c:when test="${nYear-bYear >= 1 }">
															${wYear}-${wMonth}-${wDay}
														</c:when>
													</c:choose></td>
											</tr>
										</c:if>
										<c:if test="${(list.boardReply eq 2) and (bLevel eq 1)}">
											<tr class="c3">
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
												<td><a href="boardView.bo?boardSerial=${list.boardSerial }">ㄴ
														${list.boardTitle }</a></td>
												<td><c:choose>
														<c:when test="${nYear-bYear < 1 }">
															${wHour}:${wMinute}
														</c:when>
														<c:otherwise>
															${wYear}-${wMonth}-${wDay}
														</c:otherwise>
													</c:choose></td>
											</tr>
										</c:if>
										
										<c:if test="${(list.boardReply > 2) and (bLevel eq 1)}">
											<tr class="c3">
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
												<td><a href="boardView.bo?boardSerial=${list.boardSerial }">ㄴㄴ
														${list.boardTitle }</a></td>
												<td>
												<c:choose>
													<c:when test="${nYear-bYear < 1 }">
														${wHour}:${wMinute}
													</c:when>
													<c:otherwise>
														${wYear}-${wMonth}-${wDay}
													</c:otherwise>
												</c:choose>
												</td>
											</tr>
										</c:if>
										</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th colspan="4">
											<c:set var="paging" value="${requestScope.paging}" />
									 	<c:url var="action" value="memberView.do?userId=${requestScope.memberView.userId}" />
										<c:if test="${paging.prev}">
											<a href="${action}&page=${paging.beginPage-1}">prev</a>
										</c:if>
										<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
											<c:choose>
												<c:when test="${paging.page==index}">${index}</c:when>
												<c:otherwise>
													<a href="${action}&page=${index}">${index}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${paging.next}">
											<a href="${action}&page=${paging.endPage+1}">next</a>
										</c:if>
										
										
										</th>	
									</tr>
								</tfoot>
							</table>
							</div>
							<div class="commentView-con">
								<c:choose>
									<c:when test="${userId eq sessionId}">
										<h1 class="title">내 코멘트 </h1>
									</c:when>
									<c:otherwise>
										<h1 class="title">${userId}님이 쓴 코멘트</h1>
									</c:otherwise>
									</c:choose>
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
												<tr class="t4">
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
										<tfoot>
									<tr>
										<th colspan="3">
											<c:set var="paging2" value="${requestScope.paging2}" />
									 	<c:url var="action" value="memberView.do?commentUserId=${requestScope.memberView.userId}" />
										<c:if test="${paging2.prev}">
											<a href="${action}&page=${paging2.beginPage-1}">prev</a>
										</c:if>
										<c:forEach begin="${paging2.beginPage}" end="${paging2.endPage}" step="1" var="index">
											<c:choose>
												<c:when test="${paging2.page==index}">${index}</c:when>
												<c:otherwise>
													<a href="${action}&page=${index}">${index}</a>
												</c:otherwise>
											</c:choose>
										</c:forEach>
										<c:if test="${paging2.next}">
											<a href="${action}&page=${paging2.endPage+1}">next</a>
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
</div>