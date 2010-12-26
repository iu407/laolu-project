package com.prs.jy.service.hibernate;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.service.RoleService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("roleService")
public class RoleServiceImpl implements RoleService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Role> findAllRole() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Role ");
		List<Role> roles = query.list();
		return roles;
	}

	@Override
	public Role findRole(Integer roleid) {
		Session session = sessionFactory.getCurrentSession();
		Role role = (Role)session.load(Role.class, roleid);
		session.flush();
		return role;
	}

	@Override
	public Integer saveRole(Role role) {
		Session session=sessionFactory.getCurrentSession();
		Integer roleid =  (Integer)session.save(role);
		return roleid;
	}


	@Override
	public void updateRole(Role role) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(role);
	}

	@Override
	public List<Role> findRoles(Integer[] roleids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Role.class);
		criteria.add(Property.forName("roleid").in(roleids));
		List<Role> roles = criteria.list();
		return roles;
	}
	
	
	@Override
	public void addResource(Role role ,Collection<Resource> resources) {
		Session session=sessionFactory.getCurrentSession();
		Collection<Resource>  resourcesAl = role.getResources();
		resourcesAl.addAll(resources);
		role.setResources(resourcesAl);
		session.save(role);
		session.flush();
	}
	
	@Override
	public void deleteResource(Role role ,Collection<Resource> resources) {
		Session session=sessionFactory.getCurrentSession();
		Collection<Resource>  resourcesAl = role.getResources();
		resourcesAl.removeAll(resources);
		role.setResources(resourcesAl);
		session.save(role);
		session.flush();
	}
}
