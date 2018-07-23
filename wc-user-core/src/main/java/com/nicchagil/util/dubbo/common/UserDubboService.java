package com.nicchagil.util.dubbo.common;

public interface UserDubboService {
	
	/**
	 * 根据条件查询记录
	 */
	UserDubboOutput getByCriteria(UserDubboInput userDubboInput);

}
