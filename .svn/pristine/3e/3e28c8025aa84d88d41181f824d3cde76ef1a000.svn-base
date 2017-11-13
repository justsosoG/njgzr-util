/** 
 * FileName:     CharsetDetector.java 
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
package com.njgzr.util.okhttp.commen;

import org.mozilla.universalchardet.UniversalDetector;

/**
 * @ClassName:     CharsetDetector
 * @Description:   TODO(这里用一句话描述这个类的作用)  
 * @author         rocky
 * @date:          2017年4月10日 下午2:55:33 
 *
 */
public class CharsetDetector {
	
	private static  UniversalDetector detector = new UniversalDetector(null);
	private static String DEFAULT_ENCODING = "UTF-8";
	 /**
     * 根据字节数组，猜测可能的字符集，如果检测失败，返回utf-8
     * @param bytes 待检测的字节数组
     * @return 可能的字符集，如果检测失败，返回utf-8
     */
    public static synchronized String guessEncoding(byte[] bytes) {  
        detector.handleData(bytes, 0, bytes.length);
        detector.dataEnd();
        String encoding = detector.getDetectedCharset();
        detector.reset();
        if (encoding == null) {
            encoding = DEFAULT_ENCODING;
        }
        return encoding;
    }
}
