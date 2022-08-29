package org.project.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.dao.MemberDao;

public class MemberModifyCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url ="";
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String jumin = request.getParameter("jumin");
		String joinDate = request.getParameter("joinDate");
		int userLevel = Integer.parseInt(request.getParameter("userLevel"));
		MemberDao dao = MemberDao.getInstance();
		int modifyOk = dao.memberModify(userId, userPw, userName, jumin, joinDate, userLevel);
		HttpSession session = request.getSession();
		if(modifyOk!=1) {
			//회원수정에 실패하면 다시 회원 수정페이지를 보여준다.
			url = "/modifyView.do?userId="+userId;
		}else {
			//회원수정에 성공하면 회원상세조회 페이지로 넘어간다.
			url = "/memberView.do?userId="+userId;
		}
		request.setAttribute("url", url);
	}
}
