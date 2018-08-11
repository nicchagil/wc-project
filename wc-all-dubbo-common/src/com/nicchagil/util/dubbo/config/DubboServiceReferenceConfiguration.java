package com.nicchagil.util.dubbo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.nicchagil.util.dubbo.DubboProperties;

// @Configuration
public class DubboServiceReferenceConfiguration {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private DubboProperties dubboProperties;
	
//	@Bean
//	public ServiceBean<UserDubboService> userDubboProviderService() {
//		ServiceBean<UserDubboService> serviceBean = new ServiceBean<UserDubboService>();
//		serviceBean.setProxy(this.dubboProperties.serviceProviderProxy);
//		serviceBean.setInterface(UserDubboService.class);
//		serviceBean.setRef(ApplicationContextUtils.getBean(UserDubboServiceImpl.class));
//		serviceBean.setVersion(this.dubboProperties.getServiceVersion());
//		serviceBean.setTimeout(5000);
//		
//		/* 集群容错 */
//		/* 故障转移 */
//		serviceBean.setCluster("failover"); // 故障转移
//		serviceBean.setRetries(2); // 除原生调用，重试0次，即不重试
//		/* 快速失败 */
//		serviceBean.setCluster("failfast"); // 快速失败
//		
//		/* 因获取的methodConfigList为空，此段代码暂时注释 */
//		/*
//		List<MethodConfig> methodConfigList = serviceBean.getMethods();
//		this.logger.info("methodConfigList : {}", methodConfigList);
//		for (int i = 0; i < methodConfigList.size(); i++) {
//			MethodConfig methodConfig = methodConfigList.get(i);
//			
//			String methodName = methodConfig.getName();
//			// 幂等的方法可重试
//			if (methodName.startsWith("get") || methodName.startsWith("query") || methodName.startsWith("select")) {
//				methodConfig.setRetries(2);
//			}
//			// 非幂等的方法不重试
//			if (methodName.startsWith("insert") || methodName.startsWith("update") || methodName.startsWith("delete")) {
//				methodConfig.setRetries(0);
//			}
//		}
//		*/
//		
//		/* 是否暴露接口 */
//		serviceBean.setExport(this.dubboProperties.isProviderEnable());
//		
//		/* 负载均衡 */
//		serviceBean.setLoadbalance("random"); // 随机
//		
//		/* 是否TOKEN验证 */
//		serviceBean.setToken(this.dubboProperties.isTokenEnable());
//		
//		/* 是否设置固定Token，不设置的话，如setToken为true，则设置动态的Token */
//		if (this.dubboProperties.isTokenEnable() && StringUtils.isNotBlank(this.dubboProperties.getTokenPassword())) {
//			serviceBean.setToken(this.dubboProperties.getTokenPassword());
//			this.logger.info("set fixed token");
//		} else {
//			this.logger.info("without set fixed token");
//		}
//		
//		return serviceBean;
//	}
//	
//	@Bean
//	public ReferenceBean<UserDubboService> userDubboConsumerService() {
//		ReferenceBean<UserDubboService> referenceBean = new ReferenceBean<>();
//		referenceBean.setInterface(UserDubboService.class);
//		referenceBean.setVersion(this.dubboProperties.getServiceVersion());
//		referenceBean.setCheck(false);
//		
//		/* 直连提供者（绕过注册中心，直连提供者）（只可开发、测试环境使用，生产环境不能用） */
//		/*
//		referenceBean.setUrl("dubbo://127.0.0.1:28801");
//		referenceBean.setTimeout(5000);
//		*/
//		
//		return referenceBean;
//	}

}
