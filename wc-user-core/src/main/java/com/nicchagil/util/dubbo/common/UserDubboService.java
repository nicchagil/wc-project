package com.nicchagil.util.dubbo.common;

public interface UserDubboService {
	
	/**
	 * 根据条件查询记录
	 */
	UserDubboOutput getByCriteria(UserDubboInput userDubboInput);
	
	/**
	 * 插入记录
	 */
	UserDubboOutput insert(UserDubboInput userDubboInput);

}
