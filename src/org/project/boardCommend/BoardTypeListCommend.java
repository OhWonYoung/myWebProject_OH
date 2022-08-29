package org.project.boardCommend;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.BoardDao;
import org.project.dto.Board_DTO;

public class BoardTypeListCommend implements ExcuteCommend{

	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int boardType = Integer.parseInt(request.getParameter("boardType"));
		BoardDao dao = BoardDao.getInstance();
		ArrayList<Board_DTO> boardList = dao.boardTypeList(boardType);
		
		if(boardList!=null) {
		request.setAttribute("boardList", boardList);
			url = "/boardList.jsp";
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}
}
