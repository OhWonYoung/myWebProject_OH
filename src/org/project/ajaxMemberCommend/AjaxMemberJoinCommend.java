package org.project.ajaxMemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.ajaxDao.AjaxMemberDao;

public class AjaxMemberJoinCommend implements ExcuteCommend{
	
	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String userId = request.getParameter("userId");
		String userPw = request.getParameter("userPw");
		String userName = request.getParameter("userName");
		String jumin = request.getParameter("jumin");
		int userLevel = Integer.parseInt(request.getParameter("userLevel"));
		
		AjaxMemberDao dao = AjaxMemberDao.getInstance();
		int ajaxJoin = dao.ajaxJoin(userId, userPw, userName, jumin, userLevel);
		
		PrintWriter out = response.getWriter();
		out.print(ajaxJoin+"");
		out.close();
	}
}
