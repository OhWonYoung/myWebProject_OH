package org.project.CommentCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.CommentDao;

public class CommentDeleteCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		int commentSerial = Integer.parseInt(request.getParameter("commentSerial"));
		CommentDao dao = CommentDao.getInstance();
		int commentDelete = dao.commentDelete(boardSerial, commentSerial);
		
		if(commentDelete!=1) {
			url = "boardView.bo?boardSerial="+boardSerial;
		}else {
			url = "boardView.bo?boardSerial="+boardSerial;
		}
		request.setAttribute("url", url);
	}
}
