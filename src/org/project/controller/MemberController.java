package org.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.boardCommend.BoardListCommend;
import org.project.boardCommend.CommentListCommend;
import org.project.memberCommend.ExcuteCommend;
import org.project.memberCommend.LoginCommend;
import org.project.memberCommend.LogoutCommend;
import org.project.memberCommend.MemberBoardMyListCommend;
import org.project.memberCommend.MemberCommentMyListCommend;
import org.project.memberCommend.MemberDeleteCommend;
import org.project.memberCommend.MemberIdCheckedCommend;
import org.project.memberCommend.MemberJoinCommend;
import org.project.memberCommend.MemberListCommend;
import org.project.memberCommend.MemberModifyCommend;
import org.project.memberCommend.MemberSearchCommend;
import org.project.memberCommend.MemberUserLevelListCommend;
import org.project.memberCommend.MemberViewCommend;

@WebServlet("*.do")
public class MemberController extends HttpServlet{

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
		
		String path = request.getContextPath();
		String uri = request.getRequestURI();
		String basicURL = uri.substring(path.length()+1,uri.length()-3);
		String url = "";
		ExcuteCommend commend = null;
		ExcuteCommend commend2 = null;
		ExcuteCommend commend3 = null;
		
		if(basicURL.equals("index")) {
			url = "/index.jsp";
		}else if(basicURL.equals("joinView")) {
			url = "/join.jsp";
		}else if(basicURL.equals("joinOk")) {
			commend = new MemberJoinCommend();
			commend.excuteQueryCommend(request, response);
			url = (String) request.getAttribute("url");
		}else if(basicURL.equals("idCheckedOk")) {
			commend = new MemberIdCheckedCommend();
			commend.excuteQueryCommend(request, response);
			return;
		}else if(basicURL.equals("loginView")) {
			url = "/login.jsp";
		}else if(basicURL.equals("loginOk")) {
			commend = new LoginCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("logoutOk")) {
			commend = new LogoutCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("logoutOk")) {
			commend = new LogoutCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("memberListView")) {
			commend = new MemberListCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("memberView")) {
			commend = new MemberViewCommend();
			commend.excuteQueryCommend(request, response);
			commend2 = new MemberBoardMyListCommend();
			commend2.excuteQueryCommend(request, response);
			commend3 = new MemberCommentMyListCommend();
			commend3.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("modifyView")) {
			commend = new MemberViewCommend();
			commend.excuteQueryCommend(request, response);
			url = "/modify.jsp";
		}else if(basicURL.equals("modifyOk")) {
			commend = new MemberModifyCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("deleteView")) {
			commend = new MemberViewCommend();
			commend.excuteQueryCommend(request, response);
			url = "/memberDelete.jsp";
		}else if(basicURL.equals("memberDeleteOk")) {
			commend = new MemberDeleteCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("memberUserLevelList")) {
			commend = new MemberUserLevelListCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}else if(basicURL.equals("searchOk")) {
			commend = new MemberSearchCommend();
			commend.excuteQueryCommend(request, response);
			url = (String)request.getAttribute("url");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
