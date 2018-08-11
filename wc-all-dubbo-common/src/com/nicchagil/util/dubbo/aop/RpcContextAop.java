package com.nicchagil.util.dubbo.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.dubbo.rpc.RpcContext;
import com.nicchagil.util.dubbo.common.constants.RpcContextConstants;

@Aspect
@Configuration
public class RpcContextAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	/* com.nicchagil.util.dubbo.common.interfaces包及其子包下的所有方法 */
    @Pointcut("execution(* com.nicchagil.util.dubbo.common.interfaces..*.*(..))")
    public void dubboServiceMethodPointcut() {}
    
    @Around("dubboServiceMethodPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		HttpServletRequest request = requestAttributes.getRequest();
    	RpcContext.getContext().setAttachment(RpcContextConstants.SESSION_ID_KEY, request.getSession().getId());
    	
    	Object result = proceedingJoinPoint.proceed();
    	return result;
    }

}