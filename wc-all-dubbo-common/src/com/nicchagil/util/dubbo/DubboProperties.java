package com.nicchagil.util.dubbo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
	
	/** 应用名 **/
	private String applicationName;
	
	/** 是否启用提供者（启动禁用主要用于调试、测试） **/
	private boolean providerEnable;
	
	/** 是否启用消费者（启动禁用主要用于调试、测试） **/
	private boolean consumerEnable;
	
	/** 是否启用令牌，以避免消费者绕过注册中心直接访问提供者 **/
	private boolean tokenEnable;
	
	/** 令牌密码，如启用令牌情况下此属性为空，则使用动态密码 **/
	private String tokenPassword;
	
	/** 注册中心协议 **/
	private String registryProtocol;
	
	/** 注册中心地址 **/
	private String registryAddress;
	
	/** 注册中心客户端 **/
	private String registryClient;
	
	/** 服务提供者和消费者间的传输协议 **/
	public String rpcProtocol;

	/** 服务提供者和消费者间的端口 **/
	private Integer rpcPort;
	
	/** 服务提供者代理模式 **/
	public String serviceProviderProxy;
	
	/** 服务版本 **/
	public String serviceVersion;
	
	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public boolean isProviderEnable() {
		return providerEnable;
	}

	public void setProviderEnable(boolean providerEnable) {
		this.providerEnable = providerEnable;
	}

	public boolean isConsumerEnable() {
		return consumerEnable;
	}

	public void setConsumerEnable(boolean consumerEnable) {
		this.consumerEnable = consumerEnable;
	}
	
	public boolean isTokenEnable() {
		return tokenEnable;
	}

	public void setTokenEnable(boolean tokenEnable) {
		this.tokenEnable = tokenEnable;
	}

	public String getTokenPassword() {
		return tokenPassword;
	}

	public void setTokenPassword(String tokenPassword) {
		this.tokenPassword = tokenPassword;
	}

	public String getRegistryProtocol() {
		return registryProtocol;
	}

	public void setRegistryProtocol(String registryProtocol) {
		this.registryProtocol = registryProtocol;
	}

	public String getRegistryAddress() {
		return registryAddress;
	}

	public void setRegistryAddress(String registryAddress) {
		this.registryAddress = registryAddress;
	}

	public String getRegistryClient() {
		return registryClient;
	}

	public void setRegistryClient(String registryClient) {
		this.registryClient = registryClient;
	}

	public String getRpcProtocol() {
		return rpcProtocol;
	}

	public void setRpcProtocol(String rpcProtocol) {
		this.rpcProtocol = rpcProtocol;
	}

	public Integer getRpcPort() {
		return rpcPort;
	}

	public void setRpcPort(Integer rpcPort) {
		this.rpcPort = rpcPort;
	}

	public String getServiceProviderProxy() {
		return serviceProviderProxy;
	}

	public void setServiceProviderProxy(String serviceProviderProxy) {
		this.serviceProviderProxy = serviceProviderProxy;
	}

	public String getServiceVersion() {
		return serviceVersion;
	}

	public void setServiceVersion(String serviceVersion) {
		this.serviceVersion = serviceVersion;
	}
	
}
