package com.prs.jy.service.hibernate;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.service.RoleService;
import com.prs.jy.service.SecurityService;
/**
 * provide security service
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("securityService")
public class SecurityServiceImpl implements SecurityService,UserDetailsService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private RoleService  roleService;
	

	/**
	 * 配置事务
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Property.forName("username").eq(username)).add(Property.forName("disable").eq(false));
		List<User> users = criteria.list();
		if (users.isEmpty()) {
			throw new UsernameNotFoundException("User " + username + " has no GrantedAuthority");
		}
		else{
			for (User user : users) {
				Iterator<Role> roles = user.getRoles().iterator();
				while(roles.hasNext())
				{
					Role role = roles.next();
					role = roleService.findRole(role.getRoleid());
					role.setResources(role.getResources());
				}
			}
		}
		return users.get(0);
	}



	/**
	 * 认证资源
	 */
	@Override
	public Map<String, String> loadUrlAuthorities() {
		Map<String, String> urlAuthorities = new HashMap<String, String>();
		
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Resource.class);
		criteria.add(Property.forName("type").eq("URL")  );
		List<Resource> urlResources =  criteria.list();
		for (Resource resource : urlResources) {
			urlAuthorities.put(resource.getValue(), resource.getRoleAuthorities());
		}
		return urlAuthorities;
	}

}
