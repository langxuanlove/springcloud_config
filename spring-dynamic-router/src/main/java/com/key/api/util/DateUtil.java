package com.key.api.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getCurrentLongDateTime() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(dt);
	}
	public static String formatDate(String date) {
		return date.replaceAll("T", " ");
	}
	//获取一个月的天数
	public static int getDayNum(int year,int month) {
		//获取当前时间  
        Calendar cal = Calendar.getInstance();  
        //设置年份
        cal.set(Calendar.YEAR,year);
        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推  
        cal.set(Calendar.MONTH, month-1);    
        //得到一个月最最后一天日期(31/30/29/28)  
        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        return MaxDay;
	}
}
