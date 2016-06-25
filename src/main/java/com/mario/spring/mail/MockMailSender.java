package com.mario.spring.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


public class MockMailSender implements MailSender {

	private static Logger logger = LoggerFactory.getLogger(MockMailSender.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mario.MailSender#send(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void send(String to, String subject, String body) {

		logger.info("Enviando email a " + to);
		logger.info("Asunto: " + subject);
		logger.info("Cuerpo: " + body);
	}

}
