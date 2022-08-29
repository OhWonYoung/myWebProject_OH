<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="org.project.dto.Member_DTO"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	ArrayList<Member_DTO> memberList = (ArrayList<Member_DTO>)request.getAttribute("memberList");
	Date date = new Date();
	SimpleDateFormat a1 = new SimpleDateFormat("yyyy");
	//date를 사용하기위해 SimpleDateFormat로 변환해준다.
	int nowYear = Integer.parseInt(a1.format(date));
	//만나이를 계산하기위해 현재 날짜를 가져오며 사용하기 편하게 int형으로 형변환 해준다,
	int nowMonth = date.getMonth()+1;
	//만나이를 계산하기 위해 현재 월을 넣어준다. -> 0부터 시작하기 때문에 +1을 해주어야 정확하다.
	int nowDay = date.getDay();			//만나이를 계산하기 위해 오늘 날짜를 넣어준다.
	int nowDate = nowMonth*100 + nowDay;//nowMonthnowDay ex) 0214
	int amaricaAge = 0;// 만나이를 넣어줄 변수
	String userLevel = ""; // 유저권한을 문자열로 바꾸어서 넣어줄 변수
%>
<div class="container">
	<div class="inner-container">
		<div class="memberList">
			<div class="sections">
				<div class="section sec1">
					<div class="sec-con">
						<div class="memberList-con">
						<h1 class="title">회원목록 조회</h1>
							<table>
								<thead>
								 <tr>
								 	<th colspan="6">
								 		<a href="memberListView.do" class="bt">전체 회원</a>
										<a href="memberUserLevelList.do?userLevel=1" class="bt">일반</a>
										<a href="memberUserLevelList.do?userLevel=2" class="bt">매니저</a>
										<a href="memberUserLevelList.do?userLevel=3" class="bt">관리자</a>
								 	</th>
								 	<th colspan="3">
								 	<!-- 검색은  memberList로 변수명을 같게 해야한다. -->
								 		<form action="searchOk.do" method="post" id="searchOkForm">
								 		<select name="search_tag" id="search_tag" class="bs1">
								 		<option value="">::검색선택::</option>
								 		<option value="userId">아이디</option>
								 		<option value="userName">이름</option>
								 	</select>
								 	<input type="text" name="search" id="search" class="bs2"/>
								 	<input type="button" value="검색" id="searchOkBtn" class="bs3" />
								 	</form>
								 	</th>
								 </tr>
									<tr>
										<th>아이디</th>
										<th>비밀번호</th>
										<th>이름</th>
										<th>성별</th>
										<th>생일</th>
										<th>만 나이</th>
										<th>가입날짜</th>
										<th>유저등급</th>
										<th>상세조회</th>
									</tr>
								</thead>
								<tbody>
									<c:if test="${empty memberList}">
										<tr>
											<th colspan="9">조건에 만족하는 회원이 없습니다.</th>
										</tr>
									</c:if>
									<%for(Member_DTO list : memberList){%>
										<tr>
											<td><%=list.getUserId() %></td>										
											<td><%=list.getUserPw() %></td>										
											<td><%=list.getUserName() %></td>
											<%String juminGen = list.getJumin().substring(6,7); 
											//주민등록번호의 7번째 자리수를 가져온다.(0번지 부터 시작) -> 원하는 index , 원하는 index+1
												String gen = ""; // 성별을 넣기 위한 변수
												%>										
											<%if(juminGen.equals("1")||juminGen.equals("3")){
															// 남자는 1999년까지는 1 2000부터는 3
																	gen="남자";
														}else if(juminGen.equals("2")||juminGen.equals("4")){
															// 여자는 1999년까지는 2 2000부터는 4
																	gen = "여자";
														}else{
															// 그이외의 숫자들은 입력오류
																	gen = "성별오류";
														}
												%>
											<td><%=gen %></td>
											<%int juminYear = Integer.parseInt(list.getJumin().substring(0,2));	//년
											int juminMonth = Integer.parseInt(list.getJumin().substring(2,4));	//월
											int juminDay = Integer.parseInt(list.getJumin().substring(4,6));		//일
											String bath = ""; //가입자의 생일을 담아줄 변수
											if(juminGen.equals("1")||juminGen.equals("2")){
												// 7번째 index가 1이나 2는 1999년까지 이기 때문에 주민등록번호 앞 두자리에 1900를 더해준다.
												juminYear = 1900+juminYear;	//ex) 1900 + 92 = 1992
											}else if(juminGen.equals("3")||juminGen.equals("4")){
												// 7번째 index가 3이나 4는 2000년이후 이기 때문에 주민등록번호 앞 두자리에 2000를 더해준다.
												juminYear = 2000+juminYear;	//ex) 2000+ 04 = 2004
											}else{
												bath = "생년월일 오류입니다.";
											}
												bath =juminYear+"년"+juminMonth+"월"+juminDay+"일"; 
												// YYYY년MM월DD일로 출력해준다.
											%>
											<td><%=bath %></td>
											<%int juminDate = juminMonth*100+juminDay;// -> juminMonthjuminDay ex)0812
											//nowDate -> 현재 월일 ex) 0802
											//juminDate -> 생년 월일 ex) 0214
											//생년월일이 현재보다 크면 나이를 한살 먹는다. -> 2022 - 1992 = 30(만나이)
											//현재월일이 생년보다 크면 나이를 먹지 않는다. -> 2022 - 1992 - 1 = 29(만나이)
											//현재의 월일과 주민등록상의 월일을 정수로 변환하여 계산해준다.
												if(juminDate<nowDate){
													//주민등록번호의 월일이 현재의 날짜보다 작다면 생일이 지난것이기 때문에 그대로 계산한다.
													amaricaAge= nowYear-juminYear;
												}else{
													//주민등록번호의 월일이 현재의 날짜보다 크다면 생일이 지나기전이기 때문에 나이를 아직 먹지 않음으로 -1해준다.
													amaricaAge= nowYear-juminYear-1;
												}
												%>
												<td><%=amaricaAge%>살</td>
												<%SimpleDateFormat joinDate = new SimpleDateFormat("yyyy년MM월dd일");
													String jDate = joinDate.format(list.getJoinDate());
													//가입일을 보기 편하게 변환해 준다.
												%>
											<td><%=jDate %></td>
											<%int level = list.getUserLevel();
											if(level==1)userLevel = "일반 유저"; 										
											if(level==2)userLevel = "매니저"; 										
											if(level==3)userLevel = "관리자"; 
											// 유저 권한을 직관적으로 판단할 수 있게 대응되는 문자열로 출력해준다.
											%>										
											<td><%=userLevel %></td>										
											<c:set var="userId" value="<%=list.getUserId() %>" />
											<td><a href="memberView.do?userId=${userId}&commentUserId=${userId}">조회</a></td>										
										</tr>
									<% } %>
								</tbody>
								<tfoot>
									<tr>
									<td colspan="9">tfoot</td>
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