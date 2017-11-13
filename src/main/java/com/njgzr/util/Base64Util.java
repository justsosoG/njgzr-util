package com.njgzr.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.codec.binary.Base64;







/**
 *@author Justsoso丶G
 *@description （base64位编码加解密）
 *@version 2017年11月8日上午9:38:30
 */
public class Base64Util {
	
	/**
	 * 
	 * 创建日期2011-4-25上午10:12:38
	 * 修改日期
	 * 作者：dh *TODO 使用Base64加密算法加密字符串
	 *return
	 */
	public static String encodeStr(String plainText){
		byte[] b=plainText.getBytes();
		Base64 base64=new Base64();
		b=base64.encode(b);
		String s=new String(b);
		return s;
	}
	
	/**
	 * 
	 * 创建日期2011-4-25上午10:15:11
	 * 修改日期
	 * 作者：dh	 *TODO 使用Base64解密
	 *return
	 */
	public static String decodeStr(String encodeStr){
		byte[] b=encodeStr.getBytes();
		Base64 base64=new Base64();
		b=base64.decode(b);
		String s=new String(b);
		return s;
	}
    
    
    
    /**
     * 
     * @param imageBase64(图片的base64位编码)
     * @param filePath(欲存储的路径)
     * @return
     */
    public static Boolean Base64ToImg(String imageBase64,String filePath){
    	byte[] buffer;
		try {
			buffer = Base64.decodeBase64(imageBase64.replace("data:image/png;base64,","").replace("data:image/jpeg;base64,","").replace("data:image/gif;base64,","").replace("data:image/bmp;base64,",""));
			String path = filePath+"/"+System.currentTimeMillis()+".png";
			FileOutputStream out = new FileOutputStream(path);  
			out.write(buffer);  
			out.close();
			return Boolean.TRUE;
		} catch (IOException e) {
			e.printStackTrace();
			return Boolean.FALSE;
		}  
    }
	
	
	
	
}
