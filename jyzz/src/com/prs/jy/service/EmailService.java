package com.prs.jy.service;

import org.springframework.mail.javamail.JavaMailSender;
/**
 * 
 * @author laolu
 *
 */
public interface EmailService {
	public JavaMailSender getMailSender();

}
