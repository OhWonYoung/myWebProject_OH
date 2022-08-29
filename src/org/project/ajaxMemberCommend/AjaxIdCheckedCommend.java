package org.project.ajaxMemberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.ajaxDao.AjaxMemberDao;

public class AjaxIdCheckedCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		AjaxMemberDao dao = AjaxMemberDao.getInstance();
		int idChecked = dao.idChecked(userId);
		
		PrintWriter out = response.getWriter();
		out.write(idChecked+"");
		out.close();
		
	}
}
