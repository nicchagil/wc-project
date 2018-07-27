package com.nicchagil.util.dubbo.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.nicchagil.util.datetime.DateTimeUtils;
import com.nicchagil.util.dubbo.DubboProperties;
import com.nicchagil.util.dubbo.common.UserDubboService;
import com.nicchagil.util.dubbo.provider.UserDubboServiceImpl;
import com.nicchagil.util.spring.ApplicationContextUtils;

@Configuration
public class DubboConfiguration {
	
	@Autowired
	private DubboProperties dubboProperties;
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName("wc-user-" + DateTimeUtils.format(new Date(), DateTimeUtils.COHERENT_TIME));
		return applicationConfig;
	}
	
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol(this.dubboProperties.getRegistryProtocol());
		registryConfig.setAddress(this.dubboProperties.getRegistryAddress());
		registryConfig.setClient(this.dubboProperties.getRegistryClient());
		return registryConfig;
	}
	
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(this.dubboProperties.getRpcProtocol());
		protocolConfig.setPort(this.dubboProperties.getRpcPort());
		return protocolConfig;
	}
	
	@Bean
	public ServiceBean userDubboService() {
		ServiceBean serviceBean = new ServiceBean();
		serviceBean.setProxy(this.dubboProperties.serviceProviderProxy);
		serviceBean.setInterface(UserDubboService.class);
		serviceBean.setRef(ApplicationContextUtils.getBean(UserDubboServiceImpl.class));
		serviceBean.setTimeout(1000);
		serviceBean.setRetries(2);
		return serviceBean;
	}

}
