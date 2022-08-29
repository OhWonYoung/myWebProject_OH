package org.project.memberCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.MemberDao;
import org.project.dto.Member_DTO;

public class MemberUserLevelListCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int userLevel = Integer.parseInt(request.getParameter("userLevel"));
		MemberDao dao = MemberDao.getInstance();
		ArrayList<Member_DTO> memberList = dao.userLevelList(userLevel);
		
		if(memberList!=null) {
			url = "/memberList.jsp";
			request.setAttribute("memberList", memberList);
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
