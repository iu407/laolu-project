package com.prs.jy.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.prs.jy.model.User;
/**
 * 用户保存器
 * @author laolu
 *
 */
public class SecurityUserHolder {
	
	public static User getCurrentUser() {
		//得到当前用户
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(o instanceof User){
			return (User) o;
		}else{
			return null;
		}
	}
}
