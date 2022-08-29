package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;

public class BoardDeleteCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		
		BoardDao dao = BoardDao.getInstance();
		int boardDelete = dao.boardDelete(boardSerial);
		
		if(boardDelete!=1) {
			url = "boardView.bo?boardSerial="+boardSerial;
		}else {
			url = "boardListView.bo";
		}
		request.setAttribute("url", url);
	}
}
