package com.prs.jy.security.interceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Component;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.SecurityService;

@Component
public class SecureResourceFilterInvocationDefinitionSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	public SecurityService securityService;
	
	@Autowired
	public ResourceService resourceService;
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	private static Map<String,Collection<ConfigAttribute>> resourceMap = null;
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestURL = ((FilterInvocation)object).getRequestUrl();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<Resource>  resources = resourceService.findResources();
		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			Resource resource = (Resource) iterator.next();
			Collection<ConfigAttribute> configAttributes = listToCollection(resource.getRoles());
			resourceMap.put(resource.getValue(), configAttributes);
		}
		
		Iterator<String> iter = resourceMap.keySet().iterator();
		while(iter.hasNext()){
			String resURL = iter.next();
            if(urlMatcher.pathMatchesUrl(resURL, requestURL)) {//是否匹配到
            	Collection<ConfigAttribute>  returnColleciton = resourceMap.get(resURL);
            	return returnColleciton;
            }
			
		}
		
		return null;//这里返回说明没有人反对
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	private Collection<ConfigAttribute> listToCollection(Collection<Role> roles){
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		for (Role role : roles) {
			list.add(new SecurityConfig(role.getRolename()));
		}
		return list;
	}

}
