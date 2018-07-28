package com.nicchagil.util.dubbo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "dubbo")
public class DubboProperties {
	
	/** 是否启用提供者（启动禁用主要用于调试、测试） **/
	private boolean providerEnable;
	
	/** 是否启用消费者（启动禁用主要用于调试、测试） **/
	private boolean consumerEnable;
	
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
