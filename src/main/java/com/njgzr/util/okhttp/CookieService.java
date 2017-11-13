/** 
 * FileName:     CookieService.java 
 * @Description: Cookie管理服务接口
 * All rights Reserved, Designed By Jincloud.com  
 * Copyright:    Copyright(C) 2016-2021  
 * Company       Jincloud LTD.  
 * @author:      rocky 
 * @version      V1.0  
 * Createdate:   2017-03-08  
 * Modification  History: 
 * Date         Author        Version        Discription    
 * 2017年3月8日       rocky       1.0          Why & What is modified: <修改原因描述>  
 */
package com.njgzr.util.okhttp;

import java.util.List;

import okhttp3.Cookie;

/**
 * @ClassName:     CookieService
 * @Description:   Cookie管理服务，定义基于Key的Cookie的持久化服务
 * @author         rocky
 * @date:          2017年3月8日 下午1:13:31 
 */
public interface CookieService {

	/**
	 * @Title:       saveCookie   
	 * @Description: 根据Key保存Cookie   
	 * @param cookie
	 * @param key
	 */
	void saveCookie(List<Cookie> cookie,String key);

	/**
	 * 
	 * @Title:       reloadCookie   
	 * @Description: 根据Key读取Cookie 
	 * @param key
	 * @return List<Cookie>
	 * @throws Exception 
	 */
	List<Cookie> reloadCookie(String key);
}
