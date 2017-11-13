package com.njgzr.domain;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 *@author Justsoso丶G
 *@description （一句话描述作用）
 *@version 2017年10月27日下午2:17:39
 */
public class MyAuthenticator extends Authenticator{   
    String userName=null;   
    String password=null;   
        
    public MyAuthenticator(){   
    }   
    public MyAuthenticator(String username, String password) {    
        this.userName = username;    
        this.password = password;    
    }    
    protected PasswordAuthentication getPasswordAuthentication(){   
        return new PasswordAuthentication(userName, password);   
    }   
}   
