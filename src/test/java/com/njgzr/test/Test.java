package com.njgzr.test;

import java.io.UnsupportedEncodingException;

import com.njgzr.domain.MailSenderInfo;
import com.njgzr.util.MailSenderUtil;

/**
 *@author Justsoso丶G
 *@description （一句话描述作用）
 *@version 2017年10月27日下午2:19:21
 */
public class Test {
	
	public static void main(String[] args) throws UnsupportedEncodingException{   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.163.com");    
     mailInfo.setMailServerPort("25");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("nj_gzr@163.com");    
     mailInfo.setPassword("gupeng1004");//您的邮箱密码    
     mailInfo.setFromAddress("nj_gzr@163.com");    
     mailInfo.setToAddress("980061784@qq.com");    
     mailInfo.setSubject("关于图片压缩");    
     mailInfo.setContent(" 前段时间在使用对图片加水印后，由于需加水印的图片的宽度和高度都非常的大，加了水印后图片从几百KB，变成了几MB，严重影响了图片在页面的加载速度！"); 
     
     mailInfo.setAttachFileNames("c:\\add.txt");
     //这个类主要来发送邮件   
     MailSenderUtil sms = new MailSenderUtil();   
 	 boolean res = sms.sendTextMail(mailInfo);//发送文体格式    
     
     System.out.println(res);
     
//   sms.sendHtmlMail(mailInfo);//发送html格式   
     
     
//     UploadUtil.upload(FilePath, "hello");
     
     
   }  
	
}
