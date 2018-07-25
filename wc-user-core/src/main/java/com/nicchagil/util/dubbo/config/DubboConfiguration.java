package com.nicchagil.util.dubbo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.nicchagil.util.dubbo.common.DubboCommonConstants;
import com.nicchagil.util.dubbo.common.UserDubboService;
import com.nicchagil.util.dubbo.provider.UserDubboServiceImpl;
import com.nicchagil.util.spring.ApplicationContextUtils;

@Configuration
public class DubboConfiguration {
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(DubboCommonConstants.A_PROVIDER_APPLICATION_NAME);
		return applicationConfig;
	}
	
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol(DubboCommonConstants.MAIN_REGISTRY_PROTOCOL);
		registryConfig.setAddress(DubboCommonConstants.MAIN_REGISTRY_ADDRESS);
		registryConfig.setClient(DubboCommonConstants.MAIN_REGISTRY_CLIENT);
		return registryConfig;
	}
	
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(DubboCommonConstants.MAIN_RPC_PROTOCOL);
		protocolConfig.setPort(DubboCommonConstants.MAIN_RPC_SERVICE_PROVIDER_PORT);
		return protocolConfig;
	}
	
	@Bean
	public ServiceBean userDubboService() {
		ServiceBean serviceBean = new ServiceBean();
		serviceBean.setProxy(DubboCommonConstants.SERVICE_PROVIDER_PROXY);
		serviceBean.setInterface(UserDubboService.class);
		serviceBean.setRef(ApplicationContextUtils.getBean(UserDubboServiceImpl.class));
		serviceBean.setTimeout(1000);
		serviceBean.setRetries(2);
		return serviceBean;
	}

}
