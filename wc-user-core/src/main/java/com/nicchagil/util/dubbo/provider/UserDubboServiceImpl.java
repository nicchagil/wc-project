package com.nicchagil.util.dubbo.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;
import com.nicchagil.util.dubbo.common.UserDubboService;

public class UserDubboServiceImpl implements UserDubboService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDubboOutput getByCriteria(UserDubboInput userDubboInput) {
		logger.info("被调用...");
		
		if (userDubboInput == null) {
			throw new DubboRuntimeException("服务入参异常");
		}
		
		return new UserDubboOutput(123, "nk");
	}

	@Override
	public UserDubboOutput insert(UserDubboInput userDubboInput) {
		return new UserDubboOutput(123, "nk");
	}

}
