package com.nicchagil.util.session.springsession;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@ConditionalOnProperty(name = "springSessionEnable", havingValue = "true") // RedisSessionConfiguration中会自动加载Spring Session，此注解可能无效
@EnableRedisHttpSession
@Configuration
public class SpringSessionConfiguration {

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new CookieHttpSessionStrategy(); // 默认策略是CookieHttpSessionStrategy
	}

}
