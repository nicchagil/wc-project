package com.nicchagil.util.desctranslator;

public class User {

	private String userId;

	private String userIdDesc;
	
	private String creatorId;
	
	private String creatorIdDesc;

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
	
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorIdDesc() {
		return creatorIdDesc;
	}

	public void setCreatorIdDesc(String creatorIdDesc) {
		this.creatorIdDesc = creatorIdDesc;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userIdDesc=" + userIdDesc + "]";
	}
	
}
