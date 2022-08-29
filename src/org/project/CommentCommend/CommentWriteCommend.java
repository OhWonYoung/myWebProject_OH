package org.project.CommentCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.CommentDao;

public class CommentWriteCommend implements ExcuteCommend{
	
	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		String commentUserId = request.getParameter("commentUserId");
		String commentContent = request.getParameter("commentContent");
		String commentDate = request.getParameter("commentDate");
		int commentReply = Integer.parseInt(request.getParameter("commentReply"));
	
		CommentDao dao = CommentDao.getInstance();
		int commentWrite = dao.commentWrite(boardSerial, commentUserId, commentContent, commentDate, commentReply);
		
		if(commentWrite!=1) {
			url = "boardView.bo?boardSerial="+boardSerial;
		}else {
			url = "boardView.bo?boardSerial="+boardSerial;
		}
		request.setAttribute("url", url);
	}

}
