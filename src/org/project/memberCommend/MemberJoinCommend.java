package org.project.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.MemberDao;

public class MemberJoinCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String jumin = request.getParameter("jumin");
		String joinDate = request.getParameter("joinDate");
		int userLevel = 0;
		if(userId.equals("admin")&&userPw.equals("1111")) {
			// 회원가입시 미리 지정해놓은 userId와 userPw를 입력한다면
			// 유저권한을 관리자등급으로 가입되게 해놓는다.
			userLevel = 3;
		}else {
			userLevel = Integer.parseInt(request.getParameter("userLevel"));
			//나머지 회원가입은 미리 처리해놓은 대로 일반유저로 가입한다.
		}
		MemberDao dao = MemberDao.getInstance();
		int joinOk = dao.memberJoin(userId, userPw, userName, jumin, joinDate, userLevel);
		
		if(joinOk!=1) {
			url = "index.do";
		}else {
			url = "loginView.do";
			//회원가입에 성공하면 바로 로그인 할수 있도록 로그인 페이지로 보내준다.
		}
		request.setAttribute("url", url);
	}
}
