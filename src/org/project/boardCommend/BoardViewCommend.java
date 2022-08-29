package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;
import org.project.dto.Board_DTO;

public class BoardViewCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		BoardDao dao = BoardDao.getInstance();
		Board_DTO boardView = dao.boardView(boardSerial);
		
		if(boardView!=null) {
			url = "/boardView.jsp";
			request.setAttribute("boardView", boardView);
		}else {
			url = "boardListView.bo";
		}
		request.setAttribute("url", url);
	}
}
