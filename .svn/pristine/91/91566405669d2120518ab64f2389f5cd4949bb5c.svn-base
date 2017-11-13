/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-05-26 
 * Date         Author        Version        Discription    
 * 2017年5月26日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.util.okhttp;

import java.util.List;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

import okhttp3.Cookie;

/**
 * @ClassName     DefaultCookieService
 * @Description   TODO(这里用一句话描述这个类的作用)  
 * @author        rocky
 * @date          2017年5月26日 下午1:49:55 
 *
 */
public class DefaultCookieService implements CookieService {

	private ConcurrentMap<String,Cookies> cookiesCache = Maps.newConcurrentMap(); 
	
	/** 
	 * @param cookie
	 * @param key 
	 * @see com.jincloud.base.util.okhttp.CookieService#saveCookie(java.util.List, java.lang.String)  
	 */
	@Override
	public void saveCookie(List<Cookie> cookies, String key) {
		
		getCookies(key).addAll(cookies);

	}

	/** 
	 * @param key
	 * @return 
	 * @see com.jincloud.base.util.okhttp.CookieService#reloadCookie(java.lang.String)  
	 */
	@Override
	public List<Cookie> reloadCookie(String key) {
		return getCookies(key).toOkHttpCookies();
	}

	private Cookies getCookies(String key){
		cookiesCache.putIfAbsent(key, new Cookies());
		return cookiesCache.get(key);
	}
	
	
	
}
