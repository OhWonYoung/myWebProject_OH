package org.project.dto;

import java.util.Date;

public class Member_DTO {
	
	private String userId;		//회원 아이디
	private String userPw;		//회원 비밀번호
	private String userName;	//회원 이름
	private String jumin;		//회원 주민번호
	private Date joinDate;		//회원 가입일자
	private int userLevel;		//유저 권한 등급 일반 1 매니저 2 관리자 3
	
	
	public Member_DTO(String userId, String userPw, String userName, String jumin, Date joinDate, int userLevel) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.jumin = jumin;
		this.joinDate = joinDate;
		this.userLevel = userLevel;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getJumin() {
		return jumin;
	}
	public void setJumin(String jumin) {
		this.jumin = jumin;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(int userLevel) {
		this.userLevel = userLevel;
	}

	
	
}
