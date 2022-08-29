package org.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.ajaxMemberCommend.AjaxIdCheckedCommend;
import org.project.ajaxMemberCommend.AjaxLoginCommend;
import org.project.ajaxMemberCommend.AjaxMemberJoinCommend;
import org.project.ajaxMemberCommend.ExcuteCommend;

@WebServlet("*.ax")
public class AjaxControllr extends HttpServlet{

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
		response.setContentType("txet/html;charset=UTF8");
		
		String url ="";
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String basicURL = uri.substring(path.length()+1, uri.length()-3);
		ExcuteCommend commend = null;
		
		if(basicURL.equals("index")) {
			url = "ajaxIndex.jsp";
		}else if(basicURL.equals("joinView")) {
			url = "ajaxJoin.jsp";
		}else if(basicURL.equals("ajaxJoinOk")) {
			commend = new AjaxMemberJoinCommend();
			commend.excuteQueryCommend(request, response);
			return;
		}else if(basicURL.equals("ajaxIdChecked")) {
			commend = new AjaxIdCheckedCommend();
			commend.excuteQueryCommend(request, response);
			return;
		}else if(basicURL.equals("loginView")) {
			url = "ajaxLogin.jsp";
		}else if(basicURL.equals("ajaxLoginOk")) {
			commend = new AjaxLoginCommend();
			commend.excuteQueryCommend(request, response);
			return;
		}
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
