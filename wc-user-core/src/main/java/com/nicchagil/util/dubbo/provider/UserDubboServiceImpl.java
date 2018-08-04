package com.nicchagil.util.dubbo.provider;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;
import com.nicchagil.util.dubbo.common.UserDubboService;

@Service
public class UserDubboServiceImpl implements UserDubboService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDubboOutput getByCriteria(UserDubboInput userDubboInput) {
		logger.info("服务提供者被调用：{}", userDubboInput);
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			throw new RuntimeException("模拟睡眠异常", e);
		}
		
		if (userDubboInput == null || userDubboInput.getId() == null || StringUtils.isBlank(userDubboInput.getName())) {
			throw new DubboRuntimeException("服务入参异常");
		}
		
		return new UserDubboOutput(123, "nk");
	}

	@Override
	public UserDubboOutput insert(UserDubboInput userDubboInput) {
		return new UserDubboOutput(123, "nk");
	}

}
