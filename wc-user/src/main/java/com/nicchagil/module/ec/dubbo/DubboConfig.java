package com.nicchagil.module.ec.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ConditionalOnProperty(name = "dubboEnable", havingValue = "true") // 如果配置项“dubboEnable”的值与“havingValue”的值一致，则为true，Configuration生效；否则为false，Configuration不生效
@ImportResource("classpath*:com/nicchagil/module/ec/dubbo/dubbo-*-config.xml")
public class DubboConfig {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
}
