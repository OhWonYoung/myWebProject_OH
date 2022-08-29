package org.project.memberCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.MemberDao;
import org.project.dto.Member_DTO;

public class MemberViewCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String userId = request.getParameter("userId");
		MemberDao dao = MemberDao.getInstance();
		Member_DTO memberView = dao.memberView(userId);
		
		if(memberView!=null) {
			url = "/memberView.jsp";
			request.setAttribute("memberView", memberView);
		}else {
			url = "index.do";
//관리자 페이지(memberList.jsp)를 노출 시키지 않기위해 index로 보낸다.
		}
		request.setAttribute("url", url);
	}
}
