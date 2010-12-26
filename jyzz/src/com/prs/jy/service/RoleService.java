package com.prs.jy.service;

import java.util.Collection;
import java.util.List;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
/**
 * 
 * @author laolu
 *
 */
public interface RoleService {
	/**
	 * 根据roleid 查找一个role
	 * @param roleid
	 * @return role
	 */
	public Role findRole(Integer roleid);
	
	public List<Role> findRoles(Integer[] roleids);
	/**
	 * 查找所有的role
	 * @return List<Role>
	 */
	public List<Role> findAllRole();
	/**
	 * 保存role
	 * @param role
	 * @return roleid
	 */
	public Integer saveRole(Role role);
	
	
	public void updateRole(Role role);

	public void addResource(Role role, Collection<Resource> resources);

	public void deleteResource(Role role, Collection<Resource> resources);
	

}
