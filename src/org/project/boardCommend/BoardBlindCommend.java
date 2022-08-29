package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;

public class BoardBlindCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent= request.getParameter("boardContent");
		BoardDao dao = BoardDao.getInstance();
		int boardBlind = dao.boardBlind(boardSerial, boardTitle, boardContent);
		
		if(boardBlind!=1) {
			url = "boardView.bo?boardSerial="+boardSerial;
		}else {
			url = "boardListView.bo";
		}
		request.setAttribute("url", url);
	}
}
