/** 
 * FileName:     ResponseParser.java 
 * @Description: TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com  
 * Copyright:    Copyright(C) 2016-2021  
 * Company       Jincloud LTD.  
 * @author:      rocky 
 * @version      V1.0  
 * Createdate:   2017-04-10  
 * Modification  History: 
 * Date         Author        Version        Discription    
 * 2017年4月10日       rocky       1.0          Why & What is modified: <修改原因描述>  
 */
package com.njgzr.util.okhttp;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;






import com.njgzr.util.okhttp.commen.CharsetDetector;
import com.njgzr.util.okhttp.commen.FileResponse;
import com.njgzr.util.okhttp.commen.HttpResponse;
import com.njgzr.util.okhttp.commen.NetworkException;

import okhttp3.Response;

/**
 * @ClassName:     ResponseParser
 * @Description:   TODO(这里用一句话描述这个类的作用)  
 * @author         rocky
 * @date:          2017年4月10日 下午2:31:46 
 *
 */
public class ResponseReader {

	private Response response;

	public ResponseReader(Response response){
		this.response = response;
	}
	
	
	public boolean isHtml(){
		String contentType = response.header("Content-Type");
		return StringUtils.contains(contentType, "text/html");
	}
	
	public boolean isJson(){
		String contentType = response.header("Content-Type");
		return StringUtils.contains(contentType, "application/json");
	}
	
	public boolean isGzip(){
		String contentType = response.header("Content-Encoding");
		return StringUtils.contains(contentType, "gzip");
	}

    protected String getCharsetByHeader(){
    	String contentType = response.header("Content-Type");
    	String charset = "";
    	if(StringUtils.isNotBlank(contentType)){
    		for(String term:StringUtils.split(contentType,";")){
    			term = StringUtils.trimToEmpty(term);
    			if(StringUtils.startsWith(term, "charset")){
    				charset= StringUtils.substringAfter(term, "=");
    				break;
    			}
    		}
    	}
    	return charset;
    }
    
    
    public String getContent()throws NetworkException{
    	try{
	    	byte[] content = response.body().bytes();
	
	    	if(content==null)
	    		return null;
	    	if(isGzip()){
	    	  
	    	}
	    		
	    	String charset = getCharsetByHeader();
	    	if(StringUtils.isBlank(charset))
	    		charset = CharsetDetector.guessEncoding(content);
	        return new String(content,charset);
    	}catch (IOException e) {
			throw new NetworkException(e.getMessage(),e);
		}

    } 
	
    public HttpResponse getResult() throws NetworkException{
    	return new HttpResponse(response.code(), getContent());
    }
    
	public FileResponse getFile(File target) throws IOException{

		FileResponse result = new FileResponse(response.code());
		if(result.isSuccessful())
			try(InputStream is = response.body().byteStream()){
				FileUtils.copyToFile(is, target);
				result.setFile(target);
			}
		return result;	
	}
	
}
