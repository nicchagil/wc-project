package com.nicchagil.util.dubbo.common;

public interface DubboCommonConstants {
	
	/** 服务提供者A **/
	String A_PROVIDER_APPLICATION_NAME = "wc-user-provider-app";
	
	/** 服务消费者A **/
	String A_CONSUMER_APPLICATION_NAME = "wc-user-consumer-app";
	
	/** 注册中心协议 **/
	String MAIN_REGISTRY_PROTOCOL = "zookeeper";
	
	/** 注册中心地址 **/
	String MAIN_REGISTRY_ADDRESS = "127.0.0.1:2181";
	
	/** 注册中心客户端 **/
	String MAIN_REGISTRY_CLIENT = "zkclient";
	
	/** 服务提供者和消费者间的传输协议 **/
	String MAIN_RPC_PROTOCOL = "dubbo";
	
	/** 服务提供者代理模式 **/
	String SERVICE_PROVIDER_PROXY = "jdk";
	
	/** 服务版本 **/
	String MAIN_SERVICE_VERSION = "develop-branch";

}
