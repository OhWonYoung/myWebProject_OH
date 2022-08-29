package org.project.memberCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.MemberDao;
import org.project.dto.Board_DTO;
import org.project.dto.memberViewPaging_DTO;

public class MemberBoardMyListCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";

		int page =1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		memberViewPaging_DTO paging = new memberViewPaging_DTO();
		
		String userId = request.getParameter("userId");
		MemberDao dao = MemberDao.getInstance();
		ArrayList<Board_DTO> boardList = dao.boardList(userId,page);
		
		int count = dao.userboardMaxCount(userId);
		paging.setPage(page);
		paging.setTotalCount(count);
		
		if(boardList!=null) {
			url = "/memberView.jsp";
			request.setAttribute("boardList", boardList);
			request.setAttribute("paging", paging);
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
