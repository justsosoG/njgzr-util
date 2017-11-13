package com.njgzr.util;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/**
* @author Justsoso丶G
 *@description （一句话描述作用）
* @version 创建时间：2017年8月17日 下午9:09:11
*/
public class RanDomUtil {
	
	/**
	 * 获取指定位数的随机数
	 * @param count
	 * @return
	 */
	public static String GetRandomNumber(Integer count) {  
        // 使用SET以此保证写入的数据不重复  
        Set<Integer> set = new HashSet<Integer>();  
        // 随机数  
        Random random = new Random();  
          
        while (set.size() < count) {  
            // nextInt返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）  
            // 和指定值（不包括）之间均匀分布的 int 值。  
        	int randomInt = 0;
        	randomInt = random.nextInt(99);
        	if(randomInt==0)
        		continue;
            set.add(randomInt);  
        }  
        
        Iterator<Integer> iterator = set.iterator();  
        // 临时记录数据  
        String temp = "";  
        while (iterator.hasNext()) {  
            temp += iterator.next();  
        }  
        
        return temp.substring(temp.length()-count,temp.length()); 
    }  
	
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	
	public static String random(){
		Random random = new Random();  
		System.err.println(random.nextInt());
		return null;
	}
	
	public static void main(String[] args) {
		String orderId = String.valueOf(System.currentTimeMillis()).
				concat(RanDomUtil.GetRandomNumber(6));
		random();
		System.err.println(orderId);
		System.err.println(GetRandomNumber(5));
		System.err.println(getUUID());
	}
	
	
}
