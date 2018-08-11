package com.nicchagil.util.dubbo.common.interfaces;

import com.nicchagil.user.model.UserDubboInput;
import com.nicchagil.user.model.UserDubboOutput;

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
