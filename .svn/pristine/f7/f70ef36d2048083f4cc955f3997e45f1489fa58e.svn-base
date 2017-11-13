/** 
 * FileName:     RequsetBuilder.java 
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

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * @ClassName:     RequsetBuilder
 * @Description:   TODO(这里用一句话描述这个类的作用)  
 * @author         rocky
 * @date:          2017年4月10日 下午1:44:22 
 *
 */
public class RequestBuilder {
	
	private String url;
	
	private String referer;
	
	private RequestBody jsonBody;
	
	private Map<String,String> formValues;
	
	private Map<String,String> headers;
	
	private boolean isPost=false;
	
	public RequestBuilder(){		
	}
	public RequestBuilder url(String url){
		this.url = url;
		return this;
	}

	public RequestBuilder referer(String referer){
		this.referer = referer;
		return this;
	}
	public RequestBuilder postJson(String json){
		if(this.formValues!=null)
			throw new IllegalArgumentException("Can not post json and formValue at the same time.");
		if(StringUtils.isNotBlank(json))
			this.jsonBody = RequestBody.create(Constants.JSON, json);
		isPost=true;
		return this;
	}
	public RequestBuilder postFormValue(String key,String value){
		if(this.jsonBody!=null)
			throw new IllegalArgumentException("Can not post json and formValue at the same time.");
		if(this.formValues==null)
			this.formValues = new HashMap<>();
		this.formValues.put(key, value);
		isPost=true;
		return this;
	}
	public RequestBuilder postFormValue(Map<String,String> values){
		if(this.jsonBody!=null)
			throw new IllegalArgumentException("Can not post json and formValue at the same time.");
		if(this.formValues==null)
			this.formValues = new HashMap<>();
		if(values!=null)
			this.formValues.putAll(values);
		isPost=true;
		return this;
	}
	
	public RequestBuilder addHeader(String key,String val){
		if(headers==null)
			headers = Maps.newHashMap();
		headers.put(key, val);
		return this;
	}
	
	
	public Request build(){
		Request.Builder builder = new Request.Builder();
		builder.url(url);
		if(StringUtils.isNotBlank(this.referer)){
			builder.addHeader("Referer", this.referer);
			if(isPost)
				builder.addHeader("Origin", this.getOrigin());
		}
		
		if(headers!=null){
			for(String key:headers.keySet()){
			
				builder.addHeader(key, headers.get(key));
			}
		}
		
		if(this.jsonBody!=null){
			builder.post(jsonBody);
		}else if(this.formValues!=null){
			if(this.formValues.get("charset")==null||this.formValues.get("charset").isEmpty()){
				builder.post(formBody());
			}else{
				builder.post(requestBody());
			}
		}	
		return builder.build();
	}
	public Request.Builder builder(){
		Request.Builder builder = new Request.Builder().url(url);
		return builder;
	}
	private RequestBody requestBody(){
		String params="";
		int count=0;
		for(String key:this.formValues.keySet()){
			++count;
			if(key.equals("charset"))
				continue;
			if(this.formValues.size()>count){
				params+=key+"="+this.formValues.get(key)+"&";
			}else{
				params+=key+"="+this.formValues.get(key);
			}
			
		}
		return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded;charset="+this.formValues.get("charset")),
		        params);
		
	}
	private FormBody formBody(){
		FormBody.Builder builder = new FormBody.Builder(); 
		for(String key:this.formValues.keySet()){
			builder.add(key, this.formValues.get(key));
		}
		return builder.build();
	}
	private String getOrigin() {
		if(referer==null)
			return null;
		int schemaIndex = this.referer.indexOf("://");
		int endIndex = -1;
		if(schemaIndex>0)
			endIndex = StringUtils.indexOf(referer, "/", schemaIndex+3);
		else
			endIndex = StringUtils.indexOf(referer, "/");
		if(endIndex>0)
			return this.referer.substring(0, endIndex);
		else
			return referer;
    }
}

