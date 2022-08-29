package org.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.project.dbConnect.DBConnect;
import org.project.dto.Board_DTO;
import org.project.dto.Comment_DTO;
import org.project.dto.Member_DTO;

public class MemberDao {

	private static final MemberDao INSTANCE = new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
		return INSTANCE;
	}
	public int memberJoin(String userId, String userPw, String userName, String jumin, String joinDate, int userLevel) {
		int joinOk = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		
		try {
			conn = DBConnect.getConnection();
			query = "insert into member_oh values(?,?,?,?,?,?)";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			pstm.setString(2, userPw);
			pstm.setString(3, userName);
			pstm.setString(4, jumin);
			pstm.setString(5, joinDate);
			pstm.setInt(6, userLevel);
			
			joinOk = pstm.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return joinOk;
	}
	
	public int loginOk(String userId, String userPw) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		int loginOk = 0;
		try {
			conn = DBConnect.getConnection();
			query = "select count(*) from member_oh where userId=? and userPw=?";
			// 입력한 userId의 값과 userPw의 값을 동시에 가지고 있는 레코드의 숫자를 체크해준다.
			// userId는 primary key이기 때문에 중복이 없기 때문에 반드시 하나만 존재 할수있다.
			// 따라서 count(*)가 1이면 로그인 성공
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			pstm.setString(2, userPw);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				loginOk = rs.getInt(1);
				// count(*)의 값을 loginOk변수에 담아서 커맨드로 보낸다.
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return loginOk;
	}
	// MemberView에 같이 나올 내가 쓴글 리스트
	public ArrayList<Member_DTO> memberList() {
		ArrayList<Member_DTO> memberList = new ArrayList<Member_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from member_oh order by userLevel desc, joinDate desc";
			pstm = conn.prepareStatement(query);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString(1);
				String userPw = rs.getString(2);
				String userName = rs.getString(3);
				String jumin = rs.getString(4);
				Date joinDate = rs.getDate(5);
				int userLevel = rs.getInt(6);
				
				memberList.add(new Member_DTO(userId, userPw, userName, jumin, joinDate, userLevel));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return memberList;
	}
	public Member_DTO memberView(String userId) {
		Member_DTO memberView = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from member_oh where userId=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				String userId1 = rs.getString(1);
				String userPw = rs.getString(2);
				String userName = rs.getString(3);
				String jumin = rs.getString(4);
				Date joinDate = rs.getDate(5);
				int userLevel = rs.getInt(6);
				
				memberView = new Member_DTO(userId1, userPw, userName, jumin, joinDate, userLevel);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return memberView;
	}
	public int memberModify(String userId, String userPw, String userName, String jumin, String joinDate, int userLevel) {
		int modifyOk = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		
		try {
			conn = DBConnect.getConnection();
			query = "update member_oh set userPw=?, userName=?, jumin=?, joinDate=?, userLevel=? where userId=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userPw);
			pstm.setString(2, userName);
			pstm.setString(3, jumin);
			pstm.setString(4, joinDate);
			pstm.setInt(5, userLevel);
			pstm.setString(6, userId);
			
			modifyOk = pstm.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		
		return modifyOk;
	}
	public int memberDelete(String userId, String userPw) {
		int memberDelete = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		
		try {
			conn = DBConnect.getConnection();
			query = "delete member_oh where userId=? and userPw=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			pstm.setString(2, userPw);
			
			memberDelete = pstm.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		
		return memberDelete;
	}
	public ArrayList<Board_DTO> boardList(String userId, int page) {
		ArrayList<Board_DTO> boardList = new ArrayList<Board_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		int startNum = (page-1)*5+1;
		int endNum = page*5;
		try {
			conn = DBConnect.getConnection();
			query = "select  * from( select * from (select rownum rnum, board_oh.* from board_oh where userId=? "
					+ "order by boardLevel desc, boardNo desc, boardWriteDate asc, boardReply asc) "
					+ "where rnum >=?) where rnum <=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			pstm.setInt(2, startNum);
			pstm.setInt(3, endNum);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				int rNum = rs.getInt(1);//rnum 
				String userId2 = rs.getString(2);
				int boardNo = rs.getInt(3);
				String boardTitle = rs.getString(4);
				String boardContent = rs.getString(5);
				String boardWriteDate = rs.getString(6);
				int boardType = rs.getInt(7);
				int boardLevel = rs.getInt(8);
				int boardReply = rs.getInt(9);
				int boardSerial = rs.getInt(10);
				
				boardList.add(new Board_DTO(userId2, boardNo, boardTitle, boardContent,
						boardWriteDate, boardType, boardLevel, boardReply, boardSerial));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return boardList;
	}
	public ArrayList<Comment_DTO> commentList(String commentUserId, int page) {
		ArrayList<Comment_DTO> commentList = new ArrayList<Comment_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		int startNum = (page-1)*3+1;
		int endNum = page*3;
		
		try {
			conn = DBConnect.getConnection();
			query = "select  * from( select * from (select rownum rnum, comment_oh.* from comment_oh where commentUserId=? "
					+ "order by commentSerial asc) where rnum >=?) where rnum <=?";
			
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, commentUserId);
			pstm.setInt(2, startNum);
			pstm.setInt(3, endNum);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				int rNum = rs.getInt(1);
				int boardSerial = rs.getInt(2);
				String commentUserId2 = rs.getString(3);
				String commentContent = rs.getString(4);
				String commentDate = rs.getString(5);
				int commentReply = rs.getInt(6);
				int commentSerial = rs.getInt(7);

				commentList.add(new Comment_DTO(boardSerial, commentUserId2,
						commentContent, commentDate, commentReply, commentSerial));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return commentList;
	}
	public ArrayList<Member_DTO> userLevelList(int userLevel) {
		ArrayList<Member_DTO> memberList = new ArrayList<Member_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from member_oh where userLevel=? order by userLevel desc, joinDate desc";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, userLevel);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString(1);
				String userPw = rs.getString(2);
				String userName = rs.getString(3);
				String jumin = rs.getString(4);
				Date joinDate = rs.getDate(5);
				int userLevel2 = rs.getInt(6);
				
				memberList.add(new Member_DTO(userId, userPw, userName, jumin, joinDate, userLevel2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		
		return memberList;
	}
	public ArrayList<Member_DTO> searchOk(String search_tag, String search) {
		ArrayList<Member_DTO> memberList = new ArrayList<Member_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from member_oh where "+search_tag+" like ? or "+search_tag+" is null "
					+ "order by userLevel desc, joinDate desc";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, "%"+search+"%");
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString(1);
				String userPw = rs.getString(2);
				String userName = rs.getString(3);
				String jumin = rs.getString(4);
				Date joinDate = rs.getDate(5);
				int userLevel2 = rs.getInt(6);
				
				memberList.add(new Member_DTO(userId, userPw, userName, jumin, joinDate, userLevel2));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		
		
		return memberList;
	}
	public int idChecked(String userId) {
		int idChecked = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		
		try {
			conn = DBConnect.getConnection();
			query = "select count(*) from member_oh where userId=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			
			rs = pstm.executeQuery();
			if(rs.next()) {
				idChecked = rs.getInt(1);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		
		
		
		return idChecked;
	}
	public int userboardMaxCount(String userId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select count(*) from board_oh where userId=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return count;
	}
	public int userCommentMaxCount(String commentUserId) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select count(*) from comment_oh where commentUserId=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, commentUserId);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
				if(rs!=null)rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally {}
		}
		return count;
	}
}
