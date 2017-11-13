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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.njgzr.base.Strings.Charsets;


/**
 * @ClassName     Serializables
 * @Description   TODO(这里用一句话描述这个类的作用)  
 * @author        rocky
 * @date          2017年6月19日 下午5:45:24 
 *
 */
public class Serializables {
	
	private static final Logger logger = LoggerFactory.getLogger(Serializables.class);
	
	public static String toBase64String(Serializable object) throws IOException{
		
		if (!(object instanceof Serializable)) {
			throw new IllegalArgumentException("Serializables requires a Serializable payload " +
					"but received an object of type [" + object.getClass().getName() + "]");
		}
		try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream(1024);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)){;
			objectOutputStream.writeObject(object);
			objectOutputStream.flush();
			return Base64Util.encodeStr(outputStream.toString(Charsets.UTF_8));
		} catch (IOException e) {
			logger.error("",e);
			throw e;
		}
		
	}
	

}
