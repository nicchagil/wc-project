package com.nicchagil.util.dubbo.provider.service;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.RpcContext;
import com.nicchagil.dubbo.interfaces.user.UserDubboService;
import com.nicchagil.dubbo.model.user.UserDubboInput;
import com.nicchagil.dubbo.model.user.UserDubboOutput;
import com.nicchagil.util.dubbo.common.constants.RpcContextConstants;
import com.nicchagil.util.dubbo.provider.DubboRuntimeException;
import com.nicchagil.util.random.RandomUtils;

@Service
public class UserDubboServiceImpl implements UserDubboService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDubboOutput getByCriteria(UserDubboInput userDubboInput) {
		logger.info("服务提供者被调用：{}", userDubboInput);
		logger.info("隐式参数{}：{}", RpcContextConstants.SESSION_ID_KEY, RpcContext.getContext().getAttachment(RpcContextConstants.SESSION_ID_KEY));
		
		if (userDubboInput == null || userDubboInput.getId() == null || StringUtils.isBlank(userDubboInput.getName())) {
			throw new DubboRuntimeException("服务入参异常");
		}
		
		return new UserDubboOutput(123, "nk");
	}

	@Override
	public UserDubboOutput insert(UserDubboInput userDubboInput) {
		return new UserDubboOutput(123, "nk");
	}
	
	@Override
	public UserDubboOutput sleep20Second(UserDubboInput userDubboInput) {
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			throw new RuntimeException("模拟睡眠异常", e);
		}
		
		return new UserDubboOutput(123, "nk");
	}
	
	@Override
	public UserDubboOutput occasionally(UserDubboInput userDubboInput) {
		int randonNum = RandomUtils.getRandomNumber(2);
		
		if (randonNum == 0) {
			throw new RuntimeException("模拟偶发运行时异常");
		}
		
		return new UserDubboOutput(123, "nk");
	}

}
