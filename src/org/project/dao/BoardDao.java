package org.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import org.project.dbConnect.DBConnect;
import org.project.dto.Board_DTO;
import org.project.dto.Comment_DTO;
import org.project.dto.Board_DTO;

public class BoardDao {

	private static final BoardDao INSTANCE = new BoardDao();
	private BoardDao() {}
	public static BoardDao getInstance() {
		return INSTANCE;
	}
	public int maxCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select count(*) from board_oh";
			pstm = conn.prepareStatement(query);
			
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
	public ArrayList<Board_DTO> boardList(int page) {
		ArrayList<Board_DTO> boardList = new ArrayList<Board_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		int startNum = (page-1)*10+1;
		int endNum = page*10;
		
		try {
			conn = DBConnect.getConnection();
			query = "select  * from( select * from (select rownum rnum, board_oh.* from board_oh "
					+ "order by boardLevel desc, boardNo desc, boardWriteDate asc, boardReply asc) "
					+ "where rnum >=?) where rnum <=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, startNum);
			pstm.setInt(2, endNum);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				int rNum = rs.getInt(1);//rnum 
				String userId = rs.getString(2);
				int boardNo = rs.getInt(3);
				String boardTitle = rs.getString(4);
				String boardContent = rs.getString(5);
				String boardWriteDate = rs.getString(6);
				int boardType = rs.getInt(7);
				int boardLevel = rs.getInt(8);
				int boardReply = rs.getInt(9);
				int boardSerial = rs.getInt(10);
				
				boardList.add(new Board_DTO(userId, boardNo, boardTitle, boardContent,
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
	public ArrayList<Board_DTO> searchOk(String search_tag, String search) {
		ArrayList<Board_DTO> boardList = new ArrayList<Board_DTO>();
		//검색은 목록안에서 조회하는것 이기 때문에 목록에 사용한 변수이름을 그대로 사용해야한다.
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from board_oh where "+search_tag+" like ? or "+search_tag+" is null "
							+ "order by boardLevel desc, boardNo desc, boardReply asc";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, "%"+search+"%");
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString(1);
				int boardNo = rs.getInt(2);
				String boardTitle = rs.getString(3);
				String boardContent = rs.getString(4);
				String boardWriteDate = rs.getString(5);
				int boardType = rs.getInt(6);
				int boardLevel = rs.getInt(7);
				int boardReply = rs.getInt(8);
				int boardSerial = rs.getInt(9);
				
				boardList.add(new Board_DTO(userId, boardNo, boardTitle, boardContent,
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
	public int boardWrite(String userId, int boardNo, String boardTitle, String boardContent, String boardWriteDate,
			int boardType, int boardLevel, int boardReply) {
			int boardWrite = 0;
			Connection conn = null;
			PreparedStatement pstm = null;
			String query = "";
			try {
				conn = DBConnect.getConnection();
				query = "insert into board_oh values(?,?,?,?,?,?,?,?,board_serial_sequen.NEXTVAL)";
				pstm = conn.prepareStatement(query);
				
				pstm.setString(1, userId);
				pstm.setInt(2, boardNo);
				pstm.setString(3, boardTitle);
				pstm.setString(4, boardContent);
				pstm.setString(5, boardWriteDate);
				pstm.setInt(6, boardType);
				pstm.setInt(7, boardLevel);
				pstm.setInt(8, boardReply);
				
				boardWrite = pstm.executeUpdate();
				
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
		return boardWrite;
	}
	public int maxBoardNo() {
		int maxBoardNo = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select max(boardno)+1 from board_oh ";
			pstm = conn.prepareStatement(query);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				maxBoardNo = rs.getInt(1);
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
		return maxBoardNo;
	}
	public Board_DTO boardView(int boardSerial) {
		Board_DTO boardView = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from board_oh where boardSerial=? ";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				
				String userId = rs.getString(1);
				int boardNo = rs.getInt(2);
				String boardTitle = rs.getString(3);
				String boardContent = rs.getString(4);
				String boardWriteDate = rs.getString(5);
				int boardType = rs.getInt(6);
				int boardLevel = rs.getInt(7);
				int boardReply = rs.getInt(8);
				int boardSerial2 = rs.getInt(9);

				boardView = new Board_DTO(userId, boardNo, boardTitle, boardContent, boardWriteDate, boardType, boardLevel, boardReply, boardSerial2);
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
		return boardView;
	}
	public int boardDelete(int boardSerial) {
		int boardDelete = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "delete from board_oh where boardSerial=? ";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial);
			
			boardDelete = pstm.executeUpdate();
			
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
		return boardDelete;
	}
	public int boardBlind(int boardSerial, String boardTitle, String boardContent) {
		int boardBlind = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "update board_oh set boardTitle='블라인드처리 되었습니다.', "
					+ "boardContent=(? ||chr(13)||chr(10)||chr(13)||chr(10)|| ? ) "
					+ "where boardSerial=? ";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, boardTitle);
			pstm.setString(2, boardContent);
			pstm.setInt(3, boardSerial);
			
			boardBlind = pstm.executeUpdate();
			
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
		
		return boardBlind;
	}
	public ArrayList<Board_DTO> boardTypeList(int boardType) {
		ArrayList<Board_DTO> boardList = new ArrayList<Board_DTO>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from board_oh where boardType=? or boardLevel=3 order by boardLevel desc, boardNo desc, boardReply asc ";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardType);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString(1);
				int boardNo = rs.getInt(2);
				String boardTitle = rs.getString(3);
				String boardContent = rs.getString(4);
				String boardWriteDate = rs.getString(5);
				int boardType2 = rs.getInt(6);
				int boardLevel = rs.getInt(7);
				int boardReply = rs.getInt(8);
				int boardSerial = rs.getInt(9);
				
				boardList.add(new Board_DTO(userId, boardNo, boardTitle, boardContent,
						boardWriteDate, boardType2, boardLevel, boardReply, boardSerial));
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
	public Board_DTO boardReplyView(int boardSerial) {
		Board_DTO boardReplyView = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from board_oh where boardSerial=? ";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial);
			
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				
				String userId = rs.getString(1);
				int boardNo2 = rs.getInt(2);
				String boardTitle = rs.getString(3);
				String boardContent = rs.getString(4);
				String boardWriteDate = rs.getString(5);
				int boardType = rs.getInt(6);
				int boardLevel = rs.getInt(7);
				int boardReply = rs.getInt(8);
				int boardSerial2 = rs.getInt(9);

				boardReplyView = new Board_DTO(userId, boardNo2, boardTitle, boardContent, boardWriteDate, 
						boardType, boardLevel, boardReply, boardSerial2);
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
			}
		
		}
		return boardReplyView;
	}
	public int boardReplyWrite(String userId, int boardNo, String boardTitle, String boardContent,
			String boardWriteDate, int boardType, int boardLevel, int boardReply) {
		int boardReplyWrite = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "insert into board_oh values(?,?,?,?,?,?,?,?,board_serial_sequen.NEXTVAL)";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, userId);
			pstm.setInt(2, boardNo);
			pstm.setString(3, boardTitle);
			pstm.setString(4, boardContent);
			pstm.setString(5, boardWriteDate);
			pstm.setInt(6, boardType);
			pstm.setInt(7, boardLevel);
			pstm.setInt(8, boardReply);
			
			boardReplyWrite = pstm.executeUpdate();
			
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
		
		return boardReplyWrite;
	}
	public Board_DTO boardModifyView(int boardSerial) {
		Board_DTO boardModifyView = null;
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "select * from board_oh where boardSerial=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setInt(1, boardSerial);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				
				String userId = rs.getString(1);
				int boardNo = rs.getInt(2);
				String boardTitle = rs.getString(3);
				String boardContent = rs.getString(4);
				String boardWriteDate = rs.getString(5);
				int boardType = rs.getInt(6);
				int boardLevel = rs.getInt(7);
				int boardReply = rs.getInt(8);
				int boardSerial2 = rs.getInt(9);

				boardModifyView = new Board_DTO(userId, boardNo, boardTitle, boardContent,
							boardWriteDate, boardType, boardLevel, boardReply, boardSerial2);
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
		return boardModifyView;
	}
	public int boardModify(String boardTitle, String boardContent,int boardSerial) {
		int boardModify = 0;
		Connection conn = null;
		PreparedStatement pstm = null;
		String query = "";
		try {
			conn = DBConnect.getConnection();
			query = "update board_oh set boardTitle=('수정됨 : ' || ?), boardContent=('수정됨 : ' || ?) where boardSerial=?";
			pstm = conn.prepareStatement(query);
			
			pstm.setString(1, boardTitle);
			pstm.setString(2, boardContent);
			pstm.setInt(3, boardSerial);
			
			boardModify = pstm.executeUpdate();
			
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
		
		
		return boardModify;
	}
	
	
}
