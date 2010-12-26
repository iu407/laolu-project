package com.jyzz.test;

import java.math.BigDecimal;


public class SendMail {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		ApplicationContext ctx = new FileSystemXmlApplicationContext(
//				"src/applicationContext.xml");
//		JavaMailSender sender = (JavaMailSender) ctx.getBean("mailSender");
//		SpringMail springMail = new SpringMail();
		String temp = "8.000000";
		double d = Double.valueOf(temp);
		BigDecimal bigDecimal = new BigDecimal(d);
		System.out.println(bigDecimal);
	}
	
	

}
