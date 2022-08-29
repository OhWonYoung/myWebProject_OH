package org.project.ajaxMemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.ajaxDao.AjaxMemberDao;

public class AjaxLoginCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		AjaxMemberDao dao = AjaxMemberDao.getInstance();
		int ajaxLogin = dao.ajaxLogin(userId,userPw);
		
		HttpSession session = request.getSession();
		if(ajaxLogin==1) {
			session.setAttribute("sessionId", userId);
			if(userId.equals("admin")) {
				session.setMaxInactiveInterval(60*30);
			}else {
				session.setMaxInactiveInterval(60*10);
			}
			PrintWriter out = response.getWriter();
			out.write(ajaxLogin+"");
			out.close();
		}
		
	}
}
