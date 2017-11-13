package com.njgzr.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.time.DateUtils;

@Slf4j
public class DateUtil {
	
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private static SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final String KEY_PATTERN = "yyyyMMddHHmm";
	private static final SimpleDateFormat hformat = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
	
	public static Date convertESDate(String source) throws ParseException {
		Date d = null;
		SimpleDateFormat FormatSssz1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
		if(source==null||source==""){
			return null;
		}
		d = FormatSssz1.parse(source.replace("Z", " CST"));
		if (null == d) {
			return null;
		}
		return d;
	}
	


	// date类型转换为String类型
	// formatType格式为yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	// data Date类型的时间
	public static String dateToString(Date data) {
		return format.format(data);
	}
	public static String dateToString2(Date data) {
		return format2.format(data);
	}
	public static Date dateformat(Date data) {
		try {
			SimpleDateFormat formatt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return formatt.parse(format2.format(data));
		} catch (ParseException e) {
			log.error("时间格式化出错",e);
			return data;
		}
	}

	// long类型转换为String类型
	// currentTime要转换的long类型的时间
	// formatType要转换的string类型的时间格式
	public static String longToString(long currentTime)
			throws ParseException {
		Date date = longToDate(currentTime); // long类型转成Date类型
		return dateToString(date);
	}
	public static String longToHString(long currentTime)
			throws ParseException {
		Date date = longToDate(currentTime); // long类型转成Date类型
		return hformat.format(date);
	}

	// string类型转换为date类型
	// strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
	// HH时mm分ss秒，
	// strTime的时间格式必须要与formatType的时间格式相同
	public static Date stringToDate(String strTime)
			throws ParseException {
		SimpleDateFormat formatt = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return formatt.parse(strTime);
	}

	// long转换为Date类型
	// currentTime要转换的long类型的时间
	// formatType要转换的时间格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日 HH时mm分ss秒
	public static Date longToDate(long currentTime) throws ParseException {
		Date dateOld = new Date(currentTime); // 根据long类型的毫秒数生命一个date类型的时间
		String sDateTime = dateToString(dateOld); // 把date类型的时间转换为string
		// 把String类型转换为Date类型
		return stringToDate(sDateTime);
	}
	// string类型转换为long类型
	// strTime要转换的String类型的时间
	// formatType时间格式
	// strTime的时间格式和formatType的时间格式必须相同
	public static long stringToLong(String strTime) throws ParseException {
		Date date = stringToDate(strTime); // String类型转成date类型
		if (date == null) {
			return 0;
		} else {
			// date类型转成long类型
			return dateToLong(date);
		}
	}

	// date类型转换为long类型
	// date要转换的date类型的时间
	public static long dateToLong(Date date) {
		return date.getTime();
	}
	
	public static Date convertDateToEsDate(Date date){
		return DateUtils.addHours(date, 8);
	}
	public static String dateFormat(Date date){
		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd   hh:mm:ss");   
		return sDateFormat.format(date); 
	}
	public static String dateLong(Long time){
		DecimalFormat df = new DecimalFormat("#.00");
		Double hour=(double) (time/(60*60*1000));
		Double day=(double) (time/(24*60*60*1000));
		if(hour<1){
			return df.format(time/(60*1000))+"分钟";
		}else if(hour>1 && day<1){
			return df.format(hour)+"小时";
		}else{
			return df.format(day)+"天";
		}
	}
	public static long getTodayBeginTime(){
		Calendar c1 = new GregorianCalendar();
	    c1.set(Calendar.HOUR_OF_DAY, 0);
	    c1.set(Calendar.MINUTE, 0);
	    c1.set(Calendar.SECOND, 0);
		return c1.getTime().getTime();
	}
	public static Date reduceHours(Date date , Integer hour){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);//date 换成已经已知的Date对象
        cal.add(Calendar.HOUR_OF_DAY, hour);// before 8 hour   -8h
		return cal.getTime();
	}
	public static Date reduceYear(Date date , Integer year){
		Calendar cal = Calendar.getInstance();
		//过去一年
		cal.setTime(date);
		cal.add(Calendar.YEAR, year); //-1y
		return cal.getTime();
	}
	public static Date reduceDay(Date date , Integer day){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day); //-1y
		return cal.getTime();
	}
	
}
