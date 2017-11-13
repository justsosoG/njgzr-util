package com.njgzr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *@author Justsoso丶G
 *@description （一句话描述作用）
 *@version 2017年9月15日下午12:11:17
 */
public class TimeUtil {
	
	public static String getTime(long time) {
        Calendar newCalendar = Calendar.getInstance();
        Calendar oldCalendar = Calendar.getInstance();
        oldCalendar.setTime(new Date(time));
        if (newCalendar.get(Calendar.YEAR) > oldCalendar.get(Calendar.YEAR)) {
            return getFormatTime(time);
        } else if (newCalendar.get(Calendar.MONTH) > oldCalendar.get(Calendar.MONTH)) {
            return (newCalendar.get(Calendar.MONTH) - oldCalendar.get(Calendar.MONTH)) + "月";
        } else if (newCalendar.get(Calendar.DAY_OF_MONTH) > oldCalendar.get(Calendar.DAY_OF_MONTH)) {
            return (newCalendar.get(Calendar.DAY_OF_MONTH) - oldCalendar.get(Calendar.DAY_OF_MONTH)) + "天";
        } else if (newCalendar.get(Calendar.HOUR) > oldCalendar.get(Calendar.HOUR)) {
            return (newCalendar.get(Calendar.HOUR) - oldCalendar.get(Calendar.HOUR)) + "小时";
        } else if (newCalendar.get(Calendar.MINUTE) > oldCalendar.get(Calendar.MINUTE)) {
            return (newCalendar.get(Calendar.MINUTE) - oldCalendar.get(Calendar.MINUTE)) + "分钟";
        } else {
            return "刚刚";
        }
    }
	
	public static long getDays(long time) {
		long now = System.currentTimeMillis();
		if(now > time){
			long nd = 1000 * 24 * 60 * 60;
			long d = now-time;
			long day = d / nd;
			if(day < 1){
				return 1l;
			}else{
				return day;
			}
		}else{
			return 1l;
		}
	}
	
	public static String getDatePoor(long diff) {
		 
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // long ns = 1000;
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    // 计算差多少分钟
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    // long sec = diff % nd % nh % nm / ns;
	    return day + "天" + hour + "小时" + min + "分钟";
	}
	
	private static String getFormatTime(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date(time));
    }
	
	/**
	 * 两个时间间隔多少天
	 * @param oldTime
	 * @param nowTime
	 * @return
	 * @throws ParseException
	 */
	public static int getBetweenDate(String oldTime,String nowTime) throws ParseException{
		//把传入的日期中的"-"符号去掉放入一个字符串数组strdata[]中
		String[] strOldData = oldTime.split("-");
		//循环把数组中的值保存为一个字符串对象
		oldTime = strOldData[0];
		for(int i=1;i<strOldData.length;i++){
			oldTime = oldTime+strOldData[i];
		}
		//把传入的日期中的"-"符号去掉放入一个字符串数组strdata[]中
		String[] strNowData = nowTime.split("-");
		//循环把数组中的值保存为一个字符串对象
		nowTime = strNowData[0];
		for(int i=1;i<strNowData.length;i++){
			nowTime = nowTime+strNowData[i];
		}
		
		//设定日期格式
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		//把之前的字符串对象转为设定好格式的日期对象
		Date date1 =  format.parse(oldTime);
		
		Date date2 = format.parse(nowTime);  
		//把传入的日期跟当前系统日期相减，得出2个日期之间相差天数
		int differ = Math.abs((int)((date1.getTime()-date2.getTime())/(24*60*60*1000)));
		//返回相差天数
		return differ;
	}
	
	
	public static void main(String[] args) throws ParseException {
		String u = "2017-09-13 11:54:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		
		Long r = sdf.parse(u).getTime();
		System.err.println(r);
		System.out.println(getTime(r));
	}
	
}
