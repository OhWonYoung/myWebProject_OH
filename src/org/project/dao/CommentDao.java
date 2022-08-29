package org.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.project.dbConnect.DBConnect;
import org.project.dto.Comment_DTO;

public class CommentDao {
	private static final CommentDao INSTANCE = new CommentDao();
	private CommentDao() {}
	public static CommentDao getInstance() {
		return INSTANCE;
	}
	// boardView에 같이 출력하기 위해 Controller는 board를 연결 하였다.
	public ArrayList<Comment_DTO> commentList(int boardSerial) {
		ArrayList<Comment_DTO> commentList = new ArrayList<Comment_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from comment_oh where boardSerial=? order by commentSerial asc";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				int boardSerial2 = rs.getInt(1);
				String commentUserId = rs.getString(2);
				String commentContent = rs.getString(3);
				String commentDate = rs.getString(4);
				int commentReply = rs.getInt(5);
				int commentSerial = rs.getInt(6);

				commentList.add(new Comment_DTO(boardSerial2, commentUserId, 
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
	public int commentWrite(int boardSerial, String commentUserId, String commentContent,
			String commentDate, int commentReply) {
		int commentWrite = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "insert into comment_oh values(?,?,?,?,?,comment_serial_sequen.NEXTVAL)";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial );
			pstm.setString(2, commentUserId );
			pstm.setString(3, commentContent );
			pstm.setString(4, commentDate );
			pstm.setInt(5, commentReply );
			
			commentWrite = pstm.executeUpdate();
			
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
		return commentWrite;
	}
	public int commentDelete(int boardSerial, int commentSerial) {
		int commentDelete = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "delete comment_oh where boardSerial=? and commentSerial=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial );
			pstm.setInt(2, commentSerial );
			
			commentDelete = pstm.executeUpdate();
			
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
		return commentDelete;
	}
	public int commentAllDelete(int boardSerial) {
		int commentAllDelete = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "delete comment_oh where boardSerial=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial );
			
			commentAllDelete = pstm.executeUpdate();
			
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
		
		return commentAllDelete;
	}
}
