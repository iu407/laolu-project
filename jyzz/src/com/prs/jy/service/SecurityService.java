package com.prs.jy.service;

import java.util.Map;

public interface SecurityService {
	/*
	 * 得到 url 权限
	 */
	 public Map<String, String> loadUrlAuthorities();

}
