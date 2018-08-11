package com.nicchagil.user.model;

import java.io.Serializable;

public class UserDubboInput implements Serializable {

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
	
	/* 添加无参数的构造方法才能序列化、反序列化 */
	public UserDubboInput() {
		super();
	}

	public UserDubboInput(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserDubboInput [id=" + id + ", name=" + name + "]";
	}
	
}
