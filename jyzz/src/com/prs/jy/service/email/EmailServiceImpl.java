package com.prs.jy.service.email;

import java.util.Properties;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.service.EmailService;

/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("emailService")
public class EmailServiceImpl implements EmailService{
	private @Value("${email.host}") String emailHost;
    private @Value("${email.username}") String userName;
    private @Value("${email.password}") String password;
    private @Value("${mail.smtp.auth}") String mailAuth;
	
    
	@Override
	public JavaMailSender getMailSender() {
		 JavaMailSenderImpl ms = new JavaMailSenderImpl();
	       ms.setHost(emailHost);
	       ms.setUsername(userName);
	       ms.setPassword(password);
	       Properties pp = new Properties();
	       pp.setProperty("mail.smtp.auth", mailAuth);
	       ms.setJavaMailProperties(pp);
	       return ms;   
	}

}
