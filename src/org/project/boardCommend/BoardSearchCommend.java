package org.project.boardCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;
import org.project.dto.Board_DTO;

public class BoardSearchCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		String search_tag = request.getParameter("search_tag");
		String search = request.getParameter("search");
		
		BoardDao dao = BoardDao.getInstance();
		ArrayList<Board_DTO> boardList = dao.searchOk(search_tag, search);
		// BoardListCommend와 변수가 같아야 출력이 된다.
		if(boardList!=null) {
			url = "/boardList.jsp";
			request.setAttribute("boardList", boardList);
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
