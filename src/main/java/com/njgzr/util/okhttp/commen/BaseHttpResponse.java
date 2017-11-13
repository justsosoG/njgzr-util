/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-06-01 
 * Date         Author        Version        Discription    
 * 2017年6月1日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.util.okhttp.commen;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName     BaseHttpResponse
 * @Description   TODO(这里用一句话描述这个类的作用)  
 * @author        rocky
 * @date          2017年6月1日 下午4:10:21 
 *
 */
@Getter @ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseHttpResponse {
	
	private int code;
	
	public boolean isSuccessful(){
		return code >= 200 && code < 300;
	}
}
