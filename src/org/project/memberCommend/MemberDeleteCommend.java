package org.project.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.dao.MemberDao;

public class MemberDeleteCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String sessionId = request.getParameter("sessionId");
		
		MemberDao dao = MemberDao.getInstance();
		int memberDelete = dao.memberDelete(userId, userPw);
		//회원 탈퇴를 하면 자동으로 로그아웃을 시켜준다.
		HttpSession session = request.getSession();
		if(memberDelete!=1) {
			url = "deleteView.do?userId="+userId;
		}else {
			if((sessionId.equals("admin"))) {
				//관리자가 회원탈퇴를 시키면 맴버조회페이지로 이동
				url = "memberListView.do";
			}else {
				url = "index.do";
				session.invalidate();
				//일반유저가 회원탈퇴를 하면 메인페이지로 이동
			}
		}
		request.setAttribute("url", url);
	}
}
