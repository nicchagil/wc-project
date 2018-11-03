package com.nicchagil.util.exception;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import com.nicchagil.util.spring.ApplicationContextUtils;

public class StandardResponse <T> {
	
	/** 提示代码 **/
	private String code;
	
	/** 提示信息 **/
	private String msg;
	
	/** 数据 **/
	private T data;

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}
	
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public StandardResponse<T> withData(T data) {
		this.data = data;
		return this;
	}

	public StandardResponse<T> withCode(String code) {
		this.code = code;
		return this;
	}
	
	public StandardResponse<T> withMsg(String msg) {
		this.msg = msg;
		return this;
	}

	/**
	 * 通过指定枚举设置代码和提示信息
	 */
	public StandardResponse<T> setExceptionCodeEnum(ExceptionCodeEnum exceptionCodeEnum) {
		this.code = exceptionCodeEnum.name();
		
		/* 设置系统语言的提示信息 */
		Locale locale = LocaleContextHolder.getLocale();
		if (locale != null) {
			MessageSource messageSource = ApplicationContextUtils.getBean(MessageSource.class);
			this.msg = messageSource.getMessage(this.code, null, locale);
		}
		
		return this;
	}
	
	/**
	 * 生成成功的标准响应对象
	 */
	public static <T> StandardResponse<T> getSuccessResponse(T t) {
		StandardResponse<T> standardResponse = new StandardResponse<>();
		standardResponse.setExceptionCodeEnum(ExceptionCodeEnum.SUCCESS_00001);
		standardResponse.setData(t);
		return standardResponse;
	}
	
	/**
	 * 生成失败的标准响应对象
	 */
	public static <T> StandardResponse<T> getErrorResponse(ExceptionCodeEnum exceptionCodeEnum, T t) {
		StandardResponse<T> standardResponse = new StandardResponse<>();
		standardResponse.setExceptionCodeEnum(exceptionCodeEnum);
		standardResponse.setData(t);
		return standardResponse;
	}
	
	/**
	 * 生成失败的标准响应对象
	 */
	public static <T> StandardResponse<T> getDiyResponse(String code, String msg, T t) {
		StandardResponse<T> standardResponse = new StandardResponse<>();
		standardResponse.setCode(code);
		standardResponse.setMsg(msg);
		standardResponse.setData(t);
		return standardResponse;
	}

}
