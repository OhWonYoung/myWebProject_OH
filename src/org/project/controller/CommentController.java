package org.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.CommentCommend.CommentDeleteCommend;
import org.project.CommentCommend.CommentWriteCommend;
import org.project.CommentCommend.ExcuteCommend;

@WebServlet("*.co")
public class CommentController extends HttpServlet{

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
		ExcuteCommend commend = null;
		
		if(basicURL.equals("commentWriteOk")){
			commend = new CommentWriteCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("commentDeleteOk")) {
			commend = new CommentDeleteCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
