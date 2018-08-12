package com.nicchagil.util.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AopUtils {
	
	private static Logger logger = LoggerFactory.getLogger(AopUtils.class);

	/**
	 * 根据ProceedingJoinPoint获取AOP拦截的MethodSignature
	 * @throws RuntimeException 不是MethodSignature则抛出此异常
	 */
	public static MethodSignature getMethodSignature(ProceedingJoinPoint proceedingJoinPoint) throws RuntimeException {
		Signature signature = proceedingJoinPoint.getSignature();
		logger.info("type of signature : {}", signature.getClass().getName());
		
		if (!(signature instanceof MethodSignature)) {
			throw new RuntimeException("此AOP只能用于方法");
		}
		
		MethodSignature methodSignature = (MethodSignature)signature;
		return methodSignature;
	}

	/**
	 * 根据ProceedingJoinPoint获取AOP拦截的方法
	 */
	public static Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
		MethodSignature methodSignature = AopUtils.getMethodSignature(proceedingJoinPoint);
		return methodSignature.getMethod();
	}
	
	/**
	 * 根据ProceedingJoinPoint获取AOP拦截的方法名
	 */
	public static String getMethodName(ProceedingJoinPoint proceedingJoinPoint) {
		Method method = AopUtils.getMethod(proceedingJoinPoint);
		
		if (method == null) {
			return null;
		}
		
		return method.getName();
	}
	
	/**
	 * 根据ProceedingJoinPoint获取拦截的对象
	 */
	public static Object getTargetObject(ProceedingJoinPoint proceedingJoinPoint) {
		Object target = proceedingJoinPoint.getTarget();
		return target;
	}

}
