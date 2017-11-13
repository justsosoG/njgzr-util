/* 
 * Description TODO(用一句话描述该文件做什么) 
 * All rights Reserved, Designed By Jincloud.com Copyright(C) 2017
 * author      rocky 
 * version      V1.0  
 * Createdate:   2017-07-13 
 * Date         Author        Version        Discription    
 * 2017年7月13日       rocky       1.0           <修改原因描述>  
 */
package com.njgzr.base;

import java.nio.charset.Charset;

/**
 * @ClassName     Charsets
 * @Description   TODO(这里用一句话描述这个类的作用)  
 * @author        rocky
 * @date          2017年7月13日 下午4:55:25 
 *
 */
public final class Charsets {
	 public static final Charset ASCII = Charset.forName(Strings.Charsets.ASCII);
	 public static final Charset ISO_8859_1 = Charset.forName(Strings.Charsets.ISO_8859_1);
	 public static final Charset UTF8 = Charset.forName(Strings.Charsets.UTF_8);
	 private Charsets(){}
}
