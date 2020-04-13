package com.book.pojo;

public class User {

	private String userId; //用户ID
	private String userPsw; //用户密码
	private String userName;//用户名
	private int role;// 角色
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPsw() {
		return userPsw;
	}
	public void setUserPsw(String userPsw) {
		this.userPsw = userPsw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public User(String userId, String userPsw, String userName, int role) {
		super();
		this.userId = userId;
		this.userPsw = userPsw;
		this.userName = userName;
		this.role = role;
	}
	public User() {
		super();
	}
	
	
	
}
