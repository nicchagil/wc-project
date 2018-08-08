package com.nicchagil.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = BusinessException.class)  
	@ResponseBody
	public StandardResponse<Void> handle(BusinessException e) {
		this.logger.error("全部异常处理器打印异常（BusinessException）：{}", e);
		return StandardResponse.getErrorResponse(e.getExceptionCodeEnum(), null);
	}
	
	@ExceptionHandler(value = Exception.class)  
    @ResponseBody
    public StandardResponse<Void> handle(Exception e) {
		this.logger.error("全部异常处理器打印异常（Exception）：{}", e);
		return StandardResponse.getErrorResponse(ExceptionCodeEnum.MSG_00002, null);
    }

}
