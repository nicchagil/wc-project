package com.nicchagil.util.objectcan;

import com.nicchagil.util.JsonUtils;

public class User {

	private Integer id;
	private String name;

	public User(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
	
	

}
