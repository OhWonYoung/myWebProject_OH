package org.project.memberCommend;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.MemberDao;

public class MemberIdCheckedCommend implements ExcuteCommend {

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		MemberDao dao = MemberDao.getInstance();
		int idChecked = dao.idChecked(userId);
		
		PrintWriter out = response.getWriter();
		out.write(idChecked+"");
		out.close();
	}

}
