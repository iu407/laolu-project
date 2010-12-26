package com.prs.jy.service.hibernate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Goods;
import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.service.UsersService;
import com.prs.jy.utils.HibernateUtils;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.Way;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("usersService")
public class UsersServiceImpl implements UsersService{
	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public User findUser(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User)session.load(User.class, id);
		return user;
	}


	@Override
	public Integer saveUsers(User user) {
		Session session=sessionFactory.getCurrentSession();
//		user.setPassword(new Md5PasswordEncoder().encodePassword(user.getPassword(), null));
		Integer userid =  (Integer)session.save(user);
//		session.flush();
		return userid;
	}


	@Override
	public List<User> findUsers() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("from User where disable = 'F'");
		List<User> users = query.list();
		return users;
	}


	@Override
	public void deleteUser(Integer id) {
		Session session=sessionFactory.getCurrentSession();
//		User user = (User)session.load(User.class, id);
		User user = findUser(id);
		user.setDisable(true);
		updateUser(user);
		
//		session.delete(user);
	}


	@Override
	public void addRoles(User user ,List<Role> roles) {
		Session session=sessionFactory.getCurrentSession();
		Collection<Role> rolesAl = user.getRoles();
		rolesAl.addAll(roles);//增加角色
		
		user.setRoles(rolesAl);
		session.save(user);
		session.flush();
	}
	
	@Override
	public void deleteRoles(User user ,List<Role> roles) {
		Session session=sessionFactory.getCurrentSession();

		Collection<Role> rolesAl = user.getRoles();
		rolesAl.removeAll(roles);//删除角色
		user.setRoles(rolesAl);
		session.saveOrUpdate(user);
		session.flush();
	}


	@Override
	public List<User> findUser(String username) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Property.forName("username").eq(username)).add(Property.forName("disable").eq(false));
		List<User> users = criteria.list();
		return users;
	}


	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
//		String sql = "PRS_USERS set createtime=?, disable=?, email=?, openid=?, password=?, realname=?, username=? where userid=?";
		
		session.saveOrUpdate(user);
	}


	@Override
	public void updateUserInfo(User user) {
		User rUser = findUser(user.getUserid());
		rUser.setEmail(user.getEmail());
		rUser.setRealname(user.getRealname());
		
	}


	@Override
	public ListWithPager<User> findUsers(Pager pager) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(User.class);
		criteria.add(Property.forName("disable").eq(false));
		Map<String,String> demand = pager.getDemand();
		HibernateUtils.injectWhere(criteria, "username", demand, "username", Way.STRING);
		HibernateUtils.injectWhere(criteria, "realname", demand, "realname", Way.STRING);
		HibernateUtils.injectWhere(criteria, "email", demand, "email", Way.STRING);
		
		Integer resultCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		pager.setResultCount(resultCount);

		List<User> results = criteria.setProjection(null).setFirstResult(
				pager.getFirstIndex()).setMaxResults(pager.getPageSize())
				.list();
		return new ListWithPager<User>(results,pager);
	}

}
