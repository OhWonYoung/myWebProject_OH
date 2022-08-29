package org.project.memberCommend;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.dao.CommentDao;
import org.project.dao.MemberDao;
import org.project.dto.Comment_DTO;
import org.project.dto.memberCommentPaging_DTO;
import org.project.dto.memberViewPaging_DTO;

public class MemberCommentMyListCommend implements ExcuteCommend{
	
	@Override
	public void excuteQueryCommend(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = "";
		int page =1;
		if(request.getParameter("page")!=null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		memberCommentPaging_DTO paging2 = new memberCommentPaging_DTO();
		
		String commentUserId  = request.getParameter("commentUserId");
		MemberDao dao = MemberDao.getInstance();
		ArrayList<Comment_DTO> commentList = dao.commentList(commentUserId,page);
		
		int count = dao.userCommentMaxCount(commentUserId);
		paging2.setPage(page);
		paging2.setTotalCount(count);
		
		if(commentList!=null ){
			url ="/memberView.jsp";
			request.setAttribute("commentList", commentList);
			request.setAttribute("paging2", paging2);
		}else {
			url = "index.do";
		}
		request.setAttribute("url", url);
	}

}
