/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-05-26 
 * Date         Author        Version        Discription    
 * 2017年5月26日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.util.okhttp.commen;

import java.io.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName     FileResponse
 * @Description   TODO(这里用一句话描述这个类的作用)  
 * @author        rocky
 * @date          2017年5月26日 下午5:41:58 
 *
 */
@Getter @Setter @ToString
@NoArgsConstructor
public class FileResponse  extends BaseHttpResponse{

	private File file;
	
	public FileResponse(int code){
		super(code);
	}
	
	public FileResponse(int code,File file){
		this(code);
		this.file = file;
	}
	
}
