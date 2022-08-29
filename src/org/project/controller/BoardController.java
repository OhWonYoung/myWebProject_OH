package org.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.boardCommend.BoardBlindCommend;
import org.project.boardCommend.BoardDeleteCommend;
import org.project.boardCommend.BoardListCommend;
import org.project.boardCommend.BoardMaxNoCommend;
import org.project.boardCommend.BoardModifyCommend;
import org.project.boardCommend.BoardModifyViewCommend;
import org.project.boardCommend.BoardReplyViewCommend;
import org.project.boardCommend.BoardReplyWriteCommend;
import org.project.boardCommend.BoardSearchCommend;
import org.project.boardCommend.BoardTypeListCommend;
import org.project.boardCommend.BoardViewCommend;
import org.project.boardCommend.BoardWriteCommend;
import org.project.boardCommend.CommentAllDeleteCommend;
import org.project.boardCommend.CommentListCommend;
import org.project.boardCommend.ExcuteCommend;

@WebServlet("*.bo")
public class BoardController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF8");
		
		String url = "";
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String basicURL = uri.substring(path.length()+1, uri.length()-3);
		ExcuteCommend commend= null;
		ExcuteCommend commend2= null;
		
		if(basicURL.equals("boardListView")) {
			commend = new BoardListCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("searchOk")) {
			commend = new BoardSearchCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardWriteView")) {
			commend = new BoardMaxNoCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardWriteOk")) {
			commend = new BoardWriteCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardView")) {
			commend = new BoardViewCommend();
			commend.excuteQueryCommend(request, response);
			//boardView에 같이 출력하기 위해 Controller는 board를 연결 하였다.
			commend2 = new CommentListCommend();
			commend2.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardDelete")) {
			commend = new BoardDeleteCommend();
			commend.excuteQueryCommend(request, response);
			commend2 = new CommentAllDeleteCommend();
			commend2.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardBlind")) {
			commend = new BoardBlindCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardTypeList")) {
			commend = new BoardTypeListCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardReplyView")) {
			commend = new BoardReplyViewCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardReplyOk")) {
			commend = new BoardReplyWriteCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardModifyOk")) {
			commend = new BoardModifyCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("boardModifyView")) {
			commend = new BoardModifyViewCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
