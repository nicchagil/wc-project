package com.nicchagil.util.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateTimeUtils {
	
	public static Logger logger = LoggerFactory.getLogger(DateTimeUtils.class);
	
	/** 常用的标准时间 **/
	public static String STANDARD_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	/** 常用的标准日期 **/
	public static String STANDARD_DATE = "yyyy-MM-dd";
	
	/** 连贯的时间 **/
	public static String COHERENT_TIME = "yyyy-MM-dd-HH-mm-ss";
	
	/**
	 * 日期格式化成字符串
	 */
	public static String format(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 日期格式化成字符串
	 */
	public static Date parse(String source, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void formatTest() {
		logger.info("result : {}", DateTimeUtils.format(new Date(), DateTimeUtils.COHERENT_TIME));
	}

	@Test
	public void parseTest() {
		logger.info("result : {}", DateTimeUtils.parse("2000-01-01-01-01-01", DateTimeUtils.COHERENT_TIME));
	}

}
