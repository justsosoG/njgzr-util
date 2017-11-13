/** 
 * FileName:     Cookies.java 
 * @Description: TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com  
 * Copyright:    Copyright(C) 2016-2021  
 * Company       Jincloud LTD.  
 * @author:      rocky 
 * @version      V1.0  
 * Createdate:   2017-03-29  
 * Modification  History: 
 * Date         Author        Version        Discription    
 * 2017年3月29日       rocky       1.0          Why & What is modified: <修改原因描述>  
 */
package com.njgzr.util.okhttp;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.Cookie;

/**
 * @ClassName:     Cookies
 * @Description:   TODO(这里用一句话描述这个类的作用)  
 * @author         rocky
 * @date:          2017年3月29日 上午10:55:06 
 *
 */
@Data
@NoArgsConstructor
public class Cookies {
	
	public Cookies(List<Cookie> cookies){
		addAll(cookies);
	}

	/**   
	 * @param cookies      
	 */ 
	public synchronized void addAll(List<Cookie> cookies) {
		if(items==null)
			items = Maps.newConcurrentMap();
		for(Cookie cookie:cookies){
			CookieItem item = new CookieItem(cookie);
			items.put(item.key(), item);
		}
	}
	
	/**   
	 * @param newCookies      
	 */ 
	public void add(Cookies newCookies) {
		if(items==null)
			items = newCookies.getItems();
		else{
			if(newCookies.getItems()!=null)
				items.putAll(newCookies.getItems());
		}	
	}
	
	
	
	public List<Cookie> toOkHttpCookies(){
		 List<Cookie> result = Lists.newArrayList();
		 if(items != null)
			 for(CookieItem item:items.values()){
				 result.add(item.toOkHttpCookie());
			 }
		 return result;
	}
	
	private ConcurrentMap<String,CookieItem> items;

	

}
