package com.nicchagil.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@ConditionalOnProperty(name = "logAopEnable", havingValue = "true") // 如果配置项“dubboEnable”的值与“havingValue”的值一致，则为true，Configuration生效；否则为false，Configuration不生效
public class LogAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/* com.nicchagil包及其子包下的所有方法 */
    @Pointcut("execution(* com.nicchagil..*.*(..))")
    public void allMethodPointcut() {}
    
    @Around("allMethodPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	
    	this.logger.info("方法（{}）被触发", AopUtils.getMethodName(proceedingJoinPoint));
    	
    	Object result = null;
    	try {
    		result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			this.logger.info("方法（{}）发生异常（{}）", AopUtils.getMethodName(proceedingJoinPoint), e.getClass());
			throw e;
		}
    	
    	this.logger.info("方法（{}）执行结束", AopUtils.getMethodName(proceedingJoinPoint));
    	return result;
    }

}
