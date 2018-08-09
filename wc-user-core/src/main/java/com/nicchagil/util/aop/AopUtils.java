package com.nicchagil.util.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;

public class AopUtils {

	/**
	 * 根据ProceedingJoinPoint获取AOP拦截的方法
	 */
	public static Method getMethod(ProceedingJoinPoint proceedingJoinPoint) {
		Signature signature = proceedingJoinPoint.getSignature();
		
		if (signature instanceof MethodSignature) {
			return null;
			// throw new RuntimeException("此AOP只能用于方法");
		}
		
		MethodSignature methodSignature = (MethodSignature)signature;
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

}
