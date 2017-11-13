/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-06-19 
 * Date         Author        Version        Discription    
 * 2017年6月19日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.util;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @ClassName     Dates
 * @Description   TODO(这里用一句话描述这个类的作用)  
 * @author        rocky
 * @date          2017年6月19日 下午3:32:09 
 *
 */
public class Dates {

	/**
	 * 格式: yyyyMMdd
	 * @return
	 */
	public static String stringToday(){
		return DateFormatUtils.format(new Date(), "yyyyMMdd");
	}
	
	
	
}
