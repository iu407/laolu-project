package com.prs.jy.service;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;

import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;

public interface UsersService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public User findUser(Integer id);
	
	public void updateUser(User user);
	
	public void updateUserInfo(User user);
	
	public List<User> findUser(String username);
	/**
	 * 
	 * @param user
	 * @return userid
	 */
	public Integer saveUsers(User user);
	
	
	public List<User> findUsers();
	
	public ListWithPager<User> findUsers(Pager pager) throws Exception;
	
	public void deleteUser(Integer id);
	/**
	 * 增加角色
	 * @param user
	 * @param roles
	 */
	public void addRoles(User user ,List<Role> roles);

	/**
	 * 删除角色
	 * @param user
	 * @param roles
	 */
	public void deleteRoles(User user, List<Role> roles);

}
