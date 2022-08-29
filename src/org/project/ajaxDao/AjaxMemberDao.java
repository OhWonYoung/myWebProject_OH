package org.project.ajaxDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.project.dbConnect.DBConnect;

public class AjaxMemberDao {
	
	private static final AjaxMemberDao INSTANCE = new AjaxMemberDao();
	private AjaxMemberDao () {}
	public static AjaxMemberDao getInstance() {
		return INSTANCE;
	}
	
	
	public int ajaxJoin(String userId, String userPw, 
			String userName, String jumin, int userLevel) {
		int ajaxJoin = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query ="";
		
		try {											
			conn = DBConnect.getConnection();
			query = "insert into member_oh values(?,?,?,?,to_char(sysdate,'yyyyMMdd'),?)";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			pstm.setString(2, userPw);
			pstm.setString(3, userName);
			pstm.setString(4, jumin);
			pstm.setInt(5, userLevel);
			
			
			ajaxJoin = pstm.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn!=null)conn.close();
				if(pstm!=null)pstm.close();
			}catch (Exception e) {
				e.printStackTrace();
			}finally{}
			
		}
		
		return ajaxJoin;
	}
	public int idChecked(String userId) {
		int idChecked = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query ="";
		
		try {
			conn = DBConnect.getConnection();
			query ="select count(*) from member_oh where userId=?";
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
	public int ajaxLogin(String userId, String userPw) {
		int ajaxLogin = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query ="";
		
		try {
			conn = DBConnect.getConnection();
			query ="select count(*) from member_oh where userId=? and userPw=?";
			pstm = conn.prepareStatement(query);
			pstm.setString(1, userId);
			pstm.setString(2, userPw);
			rs = pstm.executeQuery();
			if(rs.next()) {
				ajaxLogin = rs.getInt(1);
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
			
		
		return ajaxLogin;
	}
	
}
