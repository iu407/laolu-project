package com.prs.jy.utils;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;

/**
 * 
 * @author luweihua
 * @version 1.0
 */
public class WebAppUtils {

	/**
	 * �õ�bean
	 * @param beanName
	 * @param ServletContext
	 * @return
	 */
	public static Object getBean(String name,ServletContext application){
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(application);
		Object object = wac.getBean(name);
		return object;
	}


}
