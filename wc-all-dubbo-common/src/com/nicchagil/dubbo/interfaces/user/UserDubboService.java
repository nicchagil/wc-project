package com.nicchagil.dubbo.interfaces.user;

import com.nicchagil.dubbo.model.user.UserDubboInput;
import com.nicchagil.dubbo.model.user.UserDubboOutput;

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
