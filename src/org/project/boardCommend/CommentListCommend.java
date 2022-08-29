package org.project.boardCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.CommentDao;
import org.project.dto.Comment_DTO;

public class CommentListCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//boardView에 같이 출력하기 위해 Controller는 board를 연결 하였다.
		String url = "";
		int boardSerial = Integer.parseInt(request.getParameter("boardSerial"));
		CommentDao dao = CommentDao.getInstance();
		ArrayList<Comment_DTO> commentList = dao.commentList(boardSerial);
		
		if(commentList!=null ){
			url ="/boardView.jsp";
			request.setAttribute("commentList", commentList);
		}else {
			url = "boardListView.bo";
		}
		request.setAttribute("url", url);
	}
}
