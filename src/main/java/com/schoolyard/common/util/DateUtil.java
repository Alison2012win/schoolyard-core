package com.schoolyard.common.util;



import java.util.Date;

public class DateUtil {
	/**
	 * 获取今天00:00:00的时间
	 * @return
	 */
	public static Date getTodayZero(){
		Date date = new Date();
		long dateTime = date.getTime();
		return new Date(dateTime-(dateTime%86400000)-115200000);		
	}
}
