package com.nicchagil.util.dubbo.config;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.nicchagil.util.datetime.DateTimeUtils;
import com.nicchagil.util.dubbo.DubboProperties;

@Configuration
@ConditionalOnProperty(name = "dubboEnable", havingValue = "true") // 如果配置项“dubboEnable”的值与“havingValue”的值一致，则为true，Configuration生效；否则为false，Configuration不生效
@ImportResource("classpath:com/nicchagil/util/dubbo/config/dubbo-config.xml")
public class DubboConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DubboProperties dubboProperties;
	
	@Bean
	public ApplicationConfig applicationConfig() {
		ApplicationConfig applicationConfig = new ApplicationConfig();
		applicationConfig.setName(this.dubboProperties.getApplicationName() + "-" + DateTimeUtils.format(new Date(), DateTimeUtils.COHERENT_TIME));
		return applicationConfig;
	}
	
	@Bean
	public RegistryConfig registryConfig() {
		RegistryConfig registryConfig = new RegistryConfig();
		registryConfig.setProtocol(this.dubboProperties.getRegistryProtocol());
		registryConfig.setAddress(this.dubboProperties.getRegistryAddress());
		registryConfig.setClient(this.dubboProperties.getRegistryClient());
		registryConfig.setCheck(false);
		
		/* 是否作为提供者 */
		registryConfig.setRegister(this.dubboProperties.isProviderEnable());
		/* 是否作为消费者 */
		registryConfig.setSubscribe(this.dubboProperties.isConsumerEnable());
		
		return registryConfig;
	}
	
	@Bean
	public ProtocolConfig protocolConfig() {
		ProtocolConfig protocolConfig = new ProtocolConfig();
		protocolConfig.setName(this.dubboProperties.getRpcProtocol());
		protocolConfig.setPort(this.dubboProperties.getRpcPort());
		
		/* 线程模型 */
		protocolConfig.setDispatcher("all"); // 所有消息转发到线程池
		protocolConfig.setThreadpool("fixed"); // 固定线程池大小
		protocolConfig.setThreads(30); // 固定30个线程
		
		return protocolConfig;
	}
	
}
