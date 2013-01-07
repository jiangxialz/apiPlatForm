package com.sharehome.platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具
 * 
 * @author Administrator
 * @date 2012-01-05
 */
public class DateUtil {

	/** 默认的日期格式 */
	public static final String defaultDateFormat = "yyyy-MM-dd HH:mm:ss";

//	public static void main(String[] args) {
//		System.out.println(parseDateToStr(parseStrToDate("2012-10-12 12:12:00")));
//	}

	public static Date parseStrToDate(String dateStr) {
		return parseStrToDate(dateStr, null);
	}

	/**
	 * 将日期由String格式转为Date格式
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseStrToDate(String dateStr, String format) {
		SimpleDateFormat sdf;
		if (format == null) {
			// 默认的日期格式
			sdf = new SimpleDateFormat(defaultDateFormat);
		} else {
			// 指定的日期格式
			sdf = new SimpleDateFormat(format);
		}
		Date resultDate = null;
		try {
			resultDate = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return resultDate;
	}

	public static String parseDateToStr(Date date) {
		return parseDateToStr(date, null);
	}

	/**
	 * 将日期由Date格式转为String格式
	 * @param date
	 * @param format
	 * @return
	 */
	public static String parseDateToStr(Date date, String format) {
		String resultStr = null;
		try {
			if (format == null) {
				// 默认的日期格式
				resultStr = new SimpleDateFormat(defaultDateFormat).format(date);
			} else {
				// 指定的日期格式
				resultStr = new SimpleDateFormat(format).format(date);
			}
		} catch (Exception e) {
		}
		return resultStr;
	}
}
