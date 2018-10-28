package com.nicchagil.util.exception;

import org.apache.commons.lang3.StringUtils;
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
		
		StandardResponse<Void> standardResponse = new StandardResponse<>();
		standardResponse.setExceptionCodeEnum(ExceptionCodeEnum.MSG_00002);
		if (StringUtils.isNotBlank(e.getMessage())) {
			standardResponse.setMsg(e.getMessage());
		}
		
		return standardResponse;
    }

}
