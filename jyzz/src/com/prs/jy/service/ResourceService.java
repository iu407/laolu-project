package com.prs.jy.service;

import java.util.List;

import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
/**
 * 资源
 * @author laolu
 *
 */
public interface ResourceService{
	/**
	 * 
	 * @param resourceid
	 * @return
	 */
	public Resource findResource(Integer resourceid);
	/**
	 * 
	 * @param Resource
	 * @return userid
	 */
	public Integer saveResources(Resource resource);
	
	
	public List<Resource> findResources();
	
	public List<Resource> findResources(Integer[] resourceid);
	
	public void deleteResource(Resource resource);
	
	public void updateResource(Resource resource);
	
	
	public List<Resource> findResource(Role role);
	
}
