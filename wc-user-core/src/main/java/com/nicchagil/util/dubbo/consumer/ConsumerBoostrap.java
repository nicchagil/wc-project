package com.nicchagil.util.dubbo.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.nicchagil.user.model.UserDubboInput;
import com.nicchagil.user.model.UserDubboOutput;
import com.nicchagil.util.dubbo.common.interfaces.UserDubboService;

public class ConsumerBoostrap {
	
	private static Logger logger = LoggerFactory.getLogger(ConsumerBoostrap.class);

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "com/nicchagil/util/dubbo/consumer/dubbo-consumer.xml" });
		applicationContext.start();

		UserDubboService userDubboService = (UserDubboService)applicationContext.getBean("userDubboService");
		UserDubboOutput userDubboOutput = userDubboService.getByCriteria(new UserDubboInput(1, "n"));
		
		logger.info("dubbo service result : {}", userDubboOutput);
	}

}
