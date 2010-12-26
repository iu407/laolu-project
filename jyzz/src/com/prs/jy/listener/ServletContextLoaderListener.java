package com.prs.jy.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.prs.jy.model.Option;
import com.prs.jy.service.OptionService;
import com.prs.jy.utils.PrsConstants;
import com.prs.jy.utils.WebAppUtils;

public class ServletContextLoaderListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		
		OptionService optionService = (OptionService)WebAppUtils.getBean("optionService", servletContext);
		
		List<Option> options = optionService.findAllOption();
		servletContext.setAttribute("options", options);
		PrsConstants.setOptions(options);
		
		for (Option option : options) {
			String optionname = option.getOptionname();
			if(PrsConstants.OPTIONNAME_SITEURL.equals(optionname)){
				PrsConstants.setSITEURL(option.getOptionvalue());
			}
		}
	}

}
