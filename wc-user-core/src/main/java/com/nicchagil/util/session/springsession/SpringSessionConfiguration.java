package com.nicchagil.util.session.springsession;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@EnableRedisHttpSession
@Configuration
public class SpringSessionConfiguration {

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new CookieHttpSessionStrategy(); // 默认策略是CookieHttpSessionStrategy
	}

}
