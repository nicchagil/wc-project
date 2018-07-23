package com.nicchagil.util.dubbo.consumer;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.util.dubbo.common.UserDubboInput;
import com.nicchagil.util.dubbo.common.UserDubboOutput;
import com.nicchagil.util.dubbo.common.UserDubboService;

public class ConsumerTest {
	
	private static Logger logger = LoggerFactory.getLogger(ConsumerTest.class);
	
	private static ClassPathXmlApplicationContext applicationContext = null;
	
	@Before
	public void before() {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "com/nicchagil/util/dubbo/consumer/dubbo-consumer.xml" });
		applicationContext.start();
	}
	
	@Test
	public void normalTest() {
		UserDubboService userDubboService = (UserDubboService)applicationContext.getBean("userDubboService");
		UserDubboOutput userDubboOutput = userDubboService.getByCriteria(new UserDubboInput(1, "n"));
		
		logger.info("dubbo service result : {}", userDubboOutput);
	}

	@Test
	public void wrongTest() {
		UserDubboService userDubboService = (UserDubboService)applicationContext.getBean("userDubboService");
		UserDubboOutput userDubboOutput = userDubboService.getByCriteria(null);
		
		logger.info("dubbo service result : {}", userDubboOutput);
	}

}
