package com.nicchagil.util.dubbo.ext.args.signature;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.aspectj.lang.ProceedingJoinPoint;

import com.nicchagil.util.encode.Sha1Utils;

public class ArgsSignatureUtils {
	
	/**
	 * 获取参数的签名
	 */
	public static String getArgsSignature(ProceedingJoinPoint proceedingJoinPoint, String salt) {
		String argsString = ArgsSignatureUtils.getArgsString(proceedingJoinPoint);
		String sha1Result = Sha1Utils.encode(argsString, salt);
		return sha1Result;
	}
	
	/**
	 * 将参数以一定的形式拼接成字符串
	 */
	public static String getArgsString(ProceedingJoinPoint proceedingJoinPoint) {
		final String SPLIT = ";";
		Object[] argsObject = proceedingJoinPoint.getArgs();
		
		StringBuffer sb = new StringBuffer();
		if (argsObject == null) {
			sb.append("the args is null.");
		}
		
		/* 将参数以一定的形式拼接成字符串 */
		for (int i = 0; i < argsObject.length; i++) {
			Object arg = argsObject[i];
			
			if (arg == null) {
				sb.append("the #" + i + " arg is null.");
			} else {
				sb.append(ToStringBuilder.reflectionToString(arg, ToStringStyle.SHORT_PREFIX_STYLE));
			}
			
			sb.append(SPLIT);
		}
		
		return sb.toString();
	}

}
