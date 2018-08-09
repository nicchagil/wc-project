package com.nicchagil.util.dubbo.common.interfaces;

import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;

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
