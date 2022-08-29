package org.project.boardCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;
import org.project.dto.Board_DTO;
import org.project.dto.Paging_DTO;

public class BoardListCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		BoardDao dao = BoardDao.getInstance();
		int page =1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int count = dao.maxCount();
		Paging_DTO paging = new Paging_DTO();
		paging.setPage(page);
		paging.setTotalCount(count);
		ArrayList<Board_DTO> boardList = dao.boardList(page);
		
		if(boardList!=null) {
			url = "/boardList.jsp";
			request.setAttribute("boardList", boardList);
			request.setAttribute("paging", paging);
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
