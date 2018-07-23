package com.nicchagil.util.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProviderBoostrap {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				new String[] { "com/nicchagil/util/dubbo/provider/dubbo-provider.xml" });
		applicationContext.start();
		System.in.read(); // 按任意键退出
	}

}
