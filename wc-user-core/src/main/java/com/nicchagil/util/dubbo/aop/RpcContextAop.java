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
    
	/* com.nicchagil.dubbo.interfaces包及其子包下的所有方法 */
    @Pointcut("execution(* com.nicchagil.dubbo.interfaces..*.*(..))")
    public void dubboServiceMethodPointcut() {}
    
    @Around("dubboServiceMethodPointcut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    	
    	/* TODO 不稳定，偶尔出现获取的requestAttributes为null的情况（貌似初次启动常获取为null，热部署常获取成功） */
		ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes();
		this.logger.info("+++++ requestAttributes : {}", proceedingJoinPoint.getTarget().getClass().getName() + "." + requestAttributes);
		
		HttpServletRequest request = requestAttributes.getRequest();
    	RpcContext.getContext().setAttachment(RpcContextConstants.SESSION_ID_KEY, request.getSession().getId());
    	
    	Object result = proceedingJoinPoint.proceed();
    	return result;
    }

}
