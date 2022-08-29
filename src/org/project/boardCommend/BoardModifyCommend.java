package org.project.boardCommend;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;

public class BoardModifyCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url ="";

		String boardTitle = request.getParameter("boardTitle");
		String boardContent = request.getParameter("boardContent");
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		
		BoardDao dao = BoardDao.getInstance();
		int boardModify = dao.boardModify(boardTitle, boardContent, boardSerial);
		
		if(boardModify!=1) {
			url = "boardModifyView.bo?boardSerial="+boardSerial;
			//실패하면 다시 작성할 수 있게 글쓰기 페이지로
		}else {
			url = "boardView.bo?boardSerial="+boardSerial;
			//성공하면 쓴글을 확인할 수 있게 글 상세보기로
		}
		request.setAttribute("url", url);
	}
}
