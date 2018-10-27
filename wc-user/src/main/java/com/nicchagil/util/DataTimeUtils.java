package com.nicchagil.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataTimeUtils {
	
	private static Logger logger = LoggerFactory.getLogger(DataTimeUtils.class);
	
	public static String STANDARD_DATE = "yyyy-MM-dd";
	public static String STANDARD_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
	
	public static Long SECOND = 1000L;
	public static Long MINUTE = 60000L;
	public static Long HOUR = 3600000L;
	public static Long DAY = 86400000L;

	/**
	 * 添加时间，填入负数可减去时间
	 */
	public static Date add(Date date, Long addTime) {
		Date newDate = new Date();
		newDate.setTime(date.getTime() + addTime);
		return newDate;
	}
	
	/**
	 * 格式化为字符串
	 */
	public static String format(Date date, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	/**
	 * 字符串转义为日期
	 */
	public static Date format(String dateStr, String dateFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		logger.info("date : {}", DataTimeUtils.format(date, DataTimeUtils.STANDARD_DATE_TIME));
		logger.info("add second date : {}", DataTimeUtils.format(DataTimeUtils.add(date, DataTimeUtils.SECOND), DataTimeUtils.STANDARD_DATE_TIME));
		logger.info("add minute date : {}", DataTimeUtils.format(DataTimeUtils.add(date, DataTimeUtils.MINUTE), DataTimeUtils.STANDARD_DATE_TIME));
		logger.info("add hour date : {}", DataTimeUtils.format(DataTimeUtils.add(date, DataTimeUtils.HOUR), DataTimeUtils.STANDARD_DATE_TIME));
		logger.info("add day date : {}", DataTimeUtils.format(DataTimeUtils.add(date, DataTimeUtils.DAY), DataTimeUtils.STANDARD_DATE_TIME));
	}

}
