package com.prs.jy.tag;

import java.util.Iterator;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;
import com.prs.jy.utils.WebAppUtils;

public class AuthorizeTag extends TagSupport{
	 private String url;
	 
	 private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	 

	public int doStartTag() throws JspException {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		if (currentUser == null) {
			return SKIP_BODY;
		}
		
		return authorize(currentUser);
	}
	
	private int authorize(Authentication currentUser){
		if(currentUser instanceof AnonymousAuthenticationToken)
		{
			return SKIP_BODY;
		}
		
		User user =(User)currentUser.getPrincipal();
		Iterator<Role> roles = user.getRoles().iterator();
		while(roles.hasNext())
		{
			Role role = roles.next();
			RoleService  roleService = (RoleService)WebAppUtils.getBean("roleService", this.pageContext.getServletContext());
			role = roleService.findRole(role.getRoleid());
			
			ResourceService resourceService = (ResourceService)WebAppUtils.getBean("resourceService", this.pageContext.getServletContext());
			List<Resource> resources = resourceService.findResource(role);
			
			role.setResources(resources);
			Iterator<Resource> resources_it =  role.getResources().iterator();
			while(resources_it.hasNext())
			{
				Resource resource = resources_it.next();
				String value = resource.getValue();
				if(urlMatcher.pathMatchesUrl(value, url)) {
					return EVAL_PAGE;
				}
			}
		}
		return SKIP_BODY;
	}
	
	 public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

}
