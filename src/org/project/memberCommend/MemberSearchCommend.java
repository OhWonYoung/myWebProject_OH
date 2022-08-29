package org.project.memberCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.MemberDao;
import org.project.dto.Member_DTO;

public class MemberSearchCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String search_tag = request.getParameter("search_tag");
		String search = request.getParameter("search");
		
		MemberDao dao = MemberDao.getInstance();
		ArrayList<Member_DTO> memberList = dao.searchOk(search_tag, search);
		// BoardListCommend와 변수가 같아야 출력이 된다.
		if(memberList!=null) {
			url = "/memberList.jsp";
			request.setAttribute("memberList", memberList);
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
