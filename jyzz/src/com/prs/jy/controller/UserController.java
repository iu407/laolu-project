package com.prs.jy.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prs.jy.model.User;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.service.UsersService;

@Controller
public class UserController {
	@Autowired
	private UsersService usersService;
	
	
	@RequestMapping(value="/test/{id}", method = RequestMethod.GET )
	public String test(ModelMap mmap, @PathVariable("id") Integer id){
		User user = usersService.findUser(id);
		
		mmap.addAttribute("user", user);
		return "/test/test";
	}
	
	/**
	 * 登陆
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/login", method = RequestMethod.GET )
	public String login(HttpServletRequest request, HttpServletResponse response){
		User user = SecurityUserHolder.getCurrentUser();
		if(user == null){
			return "/login";
		}
	
//		 Enumeration<String> enu =  request.getSession().getAttributeNames();
//		 while(enu.hasMoreElements())
//		 {
//			 System.out.println(enu.nextElement());
//		 }
//		 request.setAttribute("user", user);
//		 return "/user/myspace";//返回到我的空间
		 
		 return "redirect:/mgz/list";//返回到我的空间
	}
	
	
}
