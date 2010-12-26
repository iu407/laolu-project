package com.prs.jy.service.freemarker;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.prs.jy.service.EmailService;
import com.prs.jy.service.FreemarkerEmailService;
import com.prs.jy.utils.PrsConstants;

import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("freemarkerEmailService")
public class FreemarkerEmailServiceImpl implements FreemarkerEmailService{

    @Autowired
    private EmailService  emailService;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Override
	public void sendMail(String templateName, final Map<String, String> emailModel, Map<String, Object> model) throws IOException, TemplateException {
		
		Template t = freeMarkerConfigurer.getConfiguration().getTemplate(templateName);
		model.put("ctx", PrsConstants.SITEURL);
		final String text = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
//		new MyMimeMessagePreparator();
		 MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
				 MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true,"GBK");
				 message.setTo(emailModel.get(PrsConstants.MAIL_RECEIVE));//设置接收方的email地址
		         message.setSubject(emailModel.get(PrsConstants.MAIL_SUBJECT));//设置邮件主题
		         message.setFrom(emailModel.get(PrsConstants.MAIL_SEND));//设置发送方地址
		         message.setText(text, true);
			}
		 };
		 emailService.getMailSender().send(preparator);//发送邮件
	}
	/**
	 * 内部类。没有使用它
	 * @author 老卢
	 *
	 */
	private class MyMimeMessagePreparator implements MimeMessagePreparator{
		String receive;
		String subject;
		String send;
		
		public MyMimeMessagePreparator(String receive, String subject,
				String send) {
			super();
			this.receive = receive;
			this.subject = subject;
			this.send = send;
		}

		@Override
		public void prepare(MimeMessage arg0) throws Exception {
			
		}
		public String getReceive() {
			return receive;
		}
		public void setReceive(String receive) {
			this.receive = receive;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getSend() {
			return send;
		}
		public void setSend(String send) {
			this.send = send;
		}
		
	}
}