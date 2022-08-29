package org.project.dto;

public class Comment_DTO {

	private int boardSerial;		//글고유번호
	private	String commentUserId;	//작성자 아이디 == userId
	private	String commentContent;	//코멘트 본문
	private	String commentDate;		//코멘트 작성일
	private int commentReply;		//코멘트 댓글(구현 X)
	private int commentSerial;		//코멘트 고유번호
	
	public Comment_DTO(int boardSerial, String commentUserId, String commentContent, String commentDate,
			int commentReply, int commentSerial) {
		super();
		this.boardSerial = boardSerial;
		this.commentUserId = commentUserId;
		this.commentContent = commentContent;
		this.commentDate = commentDate;
		this.commentReply = commentReply;
		this.commentSerial = commentSerial;
	}
	public int getBoardSerial() {
		return boardSerial;
	}
	public void setBoardSerial(int boardSerial) {
		this.boardSerial = boardSerial;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(String commentDate) {
		this.commentDate = commentDate;
	}
	public int getCommentReply() {
		return commentReply;
	}
	public void setCommentReply(int commentReply) {
		this.commentReply = commentReply;
	}
	public int getCommentSerial() {
		return commentSerial;
	}
	public void setCommentSerial(int commentSerial) {
		this.commentSerial = commentSerial;
	}
	
	
}
