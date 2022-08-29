package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;

public class BoardMaxNoCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		BoardDao dao = BoardDao.getInstance();
		int maxBoardNo= dao.maxBoardNo();
		
		if(maxBoardNo>=0) {
			url = "/boardWrite.jsp";
			request.setAttribute("maxBoardNo", maxBoardNo);
		}else {
			url = "boardListView.do";
		}
		request.setAttribute("url", url);
	}
}
