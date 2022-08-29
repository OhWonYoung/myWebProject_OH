package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.CommentDao;

public class CommentAllDeleteCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		CommentDao dao = CommentDao.getInstance();
		int commentAllDelete = dao.commentAllDelete(boardSerial);
		
		if(commentAllDelete!=1) {
			url = "boardListView.bo";
		}else {
			url = "boardListView.bo";
		}
		request.setAttribute("url", url);
	}
}
