/** 
 * FileName:     NetworkException.java 
 * @Description: 导控网络异常
 * All rights Reserved, Designed By Jincloud.com  
 * Copyright:    Copyright(C) 2016-2021  
 * Company       Jincloud LTD.  
 * @author:      rocky 
 * @version      V1.0  
 * Createdate:   2017-04-11  
 * Modification  History: 
 * Date         Author        Version        Discription    
 * 2017年4月11日       rocky       1.0          Why & What is modified: <修改原因描述>  
 */
package com.njgzr.util.okhttp.commen;

/**
 * @ClassName:     NetworkException
 * @Description:   导控网络异常
 * @author         rocky
 * @date:          2017年4月11日 下午1:42:14 
 *
 */
@SuppressWarnings("serial")
public class NetworkException extends Exception{

	/** 
	 * @Title:        NetworkException 
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @param:          
	 */
	public NetworkException() {
		super();
	}

	/** 
	 * @Title:        NetworkException 
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @param:        @param message
	 * @param:        @param cause
	 * @param:        @param enableSuppression
	 * @param:        @param writableStackTrace  
	 */
	public NetworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/** 
	 * @Title:        NetworkException 
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @param:        @param message
	 * @param:        @param cause  
	 */
	public NetworkException(String message, Throwable cause) {
		super(message, cause);
	}

	/** 
	 * @Title:        NetworkException 
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @param:        @param message  
	 */
	public NetworkException(String message) {
		super(message);
	}

	/** 
	 * @Title:        NetworkException 
	 * @Description:  TODO(这里用一句话描述这个方法的作用)  
	 * @param:        @param cause  
	 */
	public NetworkException(Throwable cause) {
		super(cause);
	}

	
}
