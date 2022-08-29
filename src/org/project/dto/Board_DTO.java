package org.project.dto;


public class Board_DTO {

	private String userId;			//작성자 아이디
	private int boardNo;			//글번호 
	private String boardTitle;		//글제목
	private String boardContent;	//글본문
	private String boardWriteDate;	//작성시간
	private int boardType;			//게시판타입 1 -> 영화 2 -> 등산 3 -> 게임
	private int boardLevel;			//공지 구분 1 -> 일반글 2-> 게시판공지 3->전체공지
	private int boardReply;			//게시글단계 1 -> 일반글  2 -> 댓글  3 -> 대댓글
	private int boardSerial;		//글 고유번호 (시퀀스)
	
	public Board_DTO(String userId, int boardNo, String boardTitle, String boardContent, String boardWriteDate,
			int boardType, int boardLevel, int boardReply, int boardSerial) {
		super();
		this.userId = userId;
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriteDate = boardWriteDate;
		this.boardType = boardType;
		this.boardLevel = boardLevel;
		this.boardReply = boardReply;
		this.boardSerial = boardSerial;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriteDate() {
		return boardWriteDate;
	}
	public void setBoardWriteDate(String boardWriteDate) {
		this.boardWriteDate = boardWriteDate;
	}
	public int getBoardType() {
		return boardType;
	}
	public void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public int getBoardLevel() {
		return boardLevel;
	}
	public void setBoardLevel(int boardLevel) {
		this.boardLevel = boardLevel;
	}
	public int getBoardReply() {
		return boardReply;
	}
	public void setBoardReply(int boardReply) {
		this.boardReply = boardReply;
	}
	public int getBoardSerial() {
		return boardSerial;
	}
	public void setBoardSerial(int boardSerial) {
		this.boardSerial = boardSerial;
	}
	
	
}
