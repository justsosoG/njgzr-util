package com.njgzr.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.apache.commons.lang3.StringUtils;

import com.njgzr.domain.MailSenderInfo;
import com.njgzr.domain.MyAuthenticator;

/**
 *@author Justsoso丶G
 *@description （一句话描述作用）
 *@version 2017年10月27日下午2:16:41
 */
public class MailSenderUtil {
	
	/**   
	  * 以文本格式发送邮件   
	  * @param mailInfo 待发送的邮件的信息   
	 * @throws UnsupportedEncodingException 
	  */    
	    public boolean sendTextMail(MailSenderInfo mailInfo) throws UnsupportedEncodingException {    
	      // 判断是否需要身份认证    
	      MyAuthenticator authenticator = null;    
	      Properties pro = mailInfo.getProperties();   
	      if (mailInfo.isValidate()) {    
	      // 如果需要身份认证，则创建一个密码验证器    
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());    
	      }   
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress());    
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      // 创建邮件的接收者地址，并设置到邮件消息中    
	      Address to = new InternetAddress(mailInfo.getToAddress());    
	      mailMessage.setRecipient(Message.RecipientType.TO,to);    
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());    
	      // 设置邮件消息的主要内容    
	      String mailContent = mailInfo.getContent();   
	      
	      		/***/
	      if(StringUtils.isNotBlank(mailInfo.getAttachFileNames())){
		      // 创建消息部分 
		      BodyPart messageBodyPart = new MimeBodyPart();
		      // 消息 
		      messageBodyPart.setText(mailContent);
	     
	    	  // 创建多重消息 
	    	  Multipart multipart = new MimeMultipart();
	    	  // 设置文本消息部分 
	    	  multipart.addBodyPart(messageBodyPart);
	    	  // 附件部分 
	    	  messageBodyPart = new MimeBodyPart(); 
	    	  DataSource source = new FileDataSource(mailInfo.getAttachFileNames()); 
	    	  messageBodyPart.setDataHandler(new DataHandler(source));
	    	  //messageBodyPart.setFileName(filename); 
	    	  //处理附件名称中文（附带文件路径）乱码问题 
	    	  messageBodyPart.setFileName(MimeUtility.encodeText(mailInfo.getAttachFileNames())); 
	    	  multipart.addBodyPart(messageBodyPart);
	    	  // 发送完整消息 
	    	  mailMessage.setContent(multipart); 
	      }else{
	    	  mailMessage.setText(mailContent);    
	      }
	      
	      
	      
	      
	      
	      // 发送邮件    
	      Transport.send(mailMessage);   
	      return true;    
	      } catch (MessagingException ex) {    
	          ex.printStackTrace();    
	      }    
	      return false;    
	    }    
	       
	    /**   
	      * 以HTML格式发送邮件   
	      * @param mailInfo 待发送的邮件信息   
	      */    
	    public static boolean sendHtmlMail(MailSenderInfo mailInfo){    
	      // 判断是否需要身份认证    
	      MyAuthenticator authenticator = null;   
	      Properties pro = mailInfo.getProperties();   
	      //如果需要身份认证，则创建一个密码验证器     
	      if (mailInfo.isValidate()) {    
	        authenticator = new MyAuthenticator(mailInfo.getUserName(), mailInfo.getPassword());   
	      }    
	      // 根据邮件会话属性和密码验证器构造一个发送邮件的session    
	      Session sendMailSession = Session.getDefaultInstance(pro,authenticator);    
	      try {    
	      // 根据session创建一个邮件消息    
	      Message mailMessage = new MimeMessage(sendMailSession);    
	      // 创建邮件发送者地址    
	      Address from = new InternetAddress(mailInfo.getFromAddress());    
	      // 设置邮件消息的发送者    
	      mailMessage.setFrom(from);    
	      // 创建邮件的接收者地址，并设置到邮件消息中    
	      Address to = new InternetAddress(mailInfo.getToAddress());    
	      // Message.RecipientType.TO属性表示接收者的类型为TO    
	      mailMessage.setRecipient(Message.RecipientType.TO,to);    
	      // 设置邮件消息的主题    
	      mailMessage.setSubject(mailInfo.getSubject());    
	      // 设置邮件消息发送的时间    
	      mailMessage.setSentDate(new Date());    
	      // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象    
	      Multipart mainPart = new MimeMultipart();    
	      // 创建一个包含HTML内容的MimeBodyPart    
	      BodyPart html = new MimeBodyPart();    
	      // 设置HTML内容    
	      html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");    
	      mainPart.addBodyPart(html);    
	      // 将MiniMultipart对象设置为邮件内容    
	      mailMessage.setContent(mainPart);    
	      // 发送邮件    
	      Transport.send(mailMessage);    
	      return true;    
	      } catch (MessagingException ex) {    
	          ex.printStackTrace();    
	      }    
	      return false;    
	    }    
	
}
