package org.project.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.dao.MemberDao;

public class LoginCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		MemberDao dao = MemberDao.getInstance();
		int loginOk = dao.loginOk(userId, userPw);
		HttpSession session = request.getSession();
		if(loginOk!=1) {
			//	loginOk 메서드가 1을 반환하면 로그인 성공 이기 때문에
			//	1이외의 숫자는 전부 로그인 실패로 처리한다.
			url = "loginView.do";
		}else {
			if(userId.equals("admin") && userPw.equals("1111")) {
				//	loginOk이 1인 것과 동시에 admin 계정이라면 따로 처리한다.
				session.setAttribute("sessionId", userId);
				session.setMaxInactiveInterval(60*30);
				//	admin 계정으로 로그인 하면 로그아웃 까지 30분 셋팅
			}else {
				//	일반 계정 로그인 성공
				session.setAttribute("sessionId", userId);
				session.setAttribute("uLevel", "일반");
				session.setMaxInactiveInterval(60*10);
				//일반 계정으로 로그인 하면 로그아웃 까지 10분 셋팅
			}
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
