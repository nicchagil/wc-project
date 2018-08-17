package com.nicchagil.util.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "logAopEnable", havingValue = "true") // 如果配置项“dubboEnable”的值与“havingValue”的值一致，则为true，Configuration生效；否则为false，Configuration不生效
public class LogAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/* com.nicchagil包及其子包下的所有方法 */
    @Pointcut("execution(* com.nicchagil..*.*(..))")
    public void allMethodPointcut() {}
    
    @Around("allMethodPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	
    	String methodName = proceedingJoinPoint.getTarget().getClass().getName() + "." + AopUtils.getMethodName(proceedingJoinPoint);
    	
    	this.logger.info("方法（{}）被触发", methodName);
    	
    	Object result = null;
    	try {
    		result = proceedingJoinPoint.proceed();
		} catch (Throwable e) {
			this.logger.info("方法（{}）发生异常（{}）", methodName, e.getClass());
			throw e;
		}
    	
    	this.logger.info("方法（{}）执行结束", methodName);
    	return result;
    }

}
