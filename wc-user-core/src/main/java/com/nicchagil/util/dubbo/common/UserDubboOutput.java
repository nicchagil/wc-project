package com.nicchagil.util.dubbo.common;

import java.io.Serializable;

public class UserDubboOutput implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String name;

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

	public UserDubboOutput(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDubboOutput [id=" + id + ", name=" + name + "]";
	}
	
}
