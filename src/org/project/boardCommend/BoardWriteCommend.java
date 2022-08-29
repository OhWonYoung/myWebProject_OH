package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;

public class BoardWriteCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url ="";

		String userId = request.getParameter("userId");
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		String boardWriteDate = request.getParameter("boardWriteDate");
		int boardType = Integer.parseInt(request.getParameter("boardType"));
		int boardLevel = Integer.parseInt(request.getParameter("boardLevel"));
		int boardReply = Integer.parseInt(request.getParameter("boardReply"));
		
		BoardDao dao = BoardDao.getInstance();
		int boardWrite = dao.boardWrite(userId, boardNo, boardTitle, boardContent, 
				boardWriteDate, boardType, boardLevel, boardReply);
		
		if(boardWrite!=1) {
			url = "boardWriteView.bo?userId="+userId;
			//실패하면 다시 작성할 수 있게 글쓰기 페이지로
		}else {
			url = "boardListView.bo";
			//성공하면 쓴글을 확인할 수 있게 글 상세보기로
		}
		request.setAttribute("url", url);
	}
}
