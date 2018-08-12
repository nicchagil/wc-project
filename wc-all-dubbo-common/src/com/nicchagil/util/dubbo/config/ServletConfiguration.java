package com.nicchagil.util.dubbo.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

@Configuration
public class ServletConfiguration {

	@Bean
	public ServletRegistrationBean dubboDispatcherServlet() {
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setName("dubboDispatcherServlet"); // 注意，不要和Spring MVC默认的dispatcherServlet重名
		servletRegistrationBean.setServlet(new com.alibaba.dubbo.remoting.http.servlet.DispatcherServlet());
		servletRegistrationBean.setUrlMappings(Lists.newArrayList("/dubbo/http/service/*"));
		servletRegistrationBean.setOrder(10);
		return servletRegistrationBean;
	}

}
