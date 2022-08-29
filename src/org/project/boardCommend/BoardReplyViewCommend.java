package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;
import org.project.dto.Board_DTO;

public class BoardReplyViewCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		BoardDao dao = BoardDao.getInstance();
		Board_DTO boardReplyView = dao.boardReplyView(boardSerial);

		if(boardReplyView!=null) {
			url = "/boardReply.jsp";
			request.setAttribute("boardView", boardReplyView);
		}else {
			url = "boardView.bo?boardSerial="+boardSerial;
		}
		request.setAttribute("url", url);
	}
}
