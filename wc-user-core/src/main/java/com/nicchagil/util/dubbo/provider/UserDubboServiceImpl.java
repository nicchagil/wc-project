package com.nicchagil.util.dubbo.provider;

import com.nicchagil.util.dubbo.common.UserDubboService;
import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;

public class UserDubboServiceImpl implements UserDubboService {

	@Override
	public UserDubboOutput getByCriteria(UserDubboInput userDubboInput) {
		if (userDubboInput == null) {
			throw new DubboRuntimeException("服务入参异常");
		}
		
		return new UserDubboOutput(123, "nk");
	}

}
