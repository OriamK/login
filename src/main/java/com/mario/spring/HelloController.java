package com.mario.spring;

import org.springframework.web.bind.annotation.RestController;

import com.mario.spring.mail.MailSender;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
   
	
	private MailSender mailSender;
	
	@Autowired
	public HelloController(@Qualifier("smtpMailSender") MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	
    @RequestMapping("/")
    public String index() throws MessagingException {
    	
    	mailSender.send("to", "s", "b");
    	
        return "abc";
    }
    
}
