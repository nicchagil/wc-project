package com.nicchagil.util.desctranslator;

public class User {

	private String userId;

	private String userIdDesc;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserIdDesc() {
		return userIdDesc;
	}

	public void setUserIdDesc(String userIdDesc) {
		this.userIdDesc = userIdDesc;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userIdDesc=" + userIdDesc + "]";
	}
	
}
