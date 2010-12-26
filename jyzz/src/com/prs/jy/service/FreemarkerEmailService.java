package com.prs.jy.service;

import java.io.IOException;
import java.util.Map;

import freemarker.template.TemplateException;

/**
 * 使用freemarker 发送电子邮件
 * @author laolu
 *
 */
public interface FreemarkerEmailService {
	/**
	 * templateName:模板的名称
	 * emailModel:
	 *  message.setTo("manneting@gmail.com");//设置接收方的email地址
		message.setSubject("jyzz 修改主题");//设置邮件主题
		message.setFrom("manneting@163.com");//设置发送方地址
	 * model:对象
	 * 发送电子邮件
	 */
	public void sendMail(String templateName,  Map<String, String> emailModel, Map<String, Object> model) throws IOException,TemplateException;

}
