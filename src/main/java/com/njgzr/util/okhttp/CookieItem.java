/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-05-15 
 * Date         Author        Version        Discription    
 * 2017年5月15日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.util.okhttp;

import lombok.Data;
import lombok.NoArgsConstructor;
import okhttp3.Cookie;
import okhttp3.internal.http.HttpDate;

@Data
@NoArgsConstructor
public class CookieItem{
	
	public CookieItem(Cookie cookie){
		this.name = cookie.name();
		this.value = cookie.value();
		this.expiresAt = cookie.expiresAt();
		this.domain = cookie.domain();
		this.path = cookie.path();
		this.secure = cookie.secure();
		this.httpOnly = cookie.httpOnly();
		this.persistent = cookie.persistent();
		this.hostOnly = cookie.hostOnly();
	}
	
	public Cookie toOkHttpCookie(){
		Cookie.Builder builder = new Cookie.Builder();
		builder.expiresAt(expiresAt);
		builder.name(name);
		builder.value(value);
		builder.path(path);
		if(httpOnly)
			builder.httpOnly();			
		builder.domain(domain);	
		if(secure)
			builder.secure();
		return builder.build();
	}
	
	public String key(){
		return name+":"+domain;
	}
	
    private String name;
    private String value;
    private long expiresAt = HttpDate.MAX_DATE;
    private String domain;
    private String path = "/";
    private boolean secure;
    private boolean httpOnly;
    private boolean persistent;
    private boolean hostOnly;
}