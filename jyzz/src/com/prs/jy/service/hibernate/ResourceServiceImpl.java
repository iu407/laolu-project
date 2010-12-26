package com.prs.jy.service.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.SecurityService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private SecurityService securityService;
	
	private UrlMatcher urlMatcher = new AntUrlPathMatcher();
	
	
	private static Map<String,Collection<ConfigAttribute>> resourceMap = null;
	
	

	@Override
	public Resource findResource(Integer id) {
		Session session = sessionFactory.getCurrentSession();
		Resource resource = (Resource)session.load(Resource.class, id);
		return resource;
	}
	
	@Override
	public List<Resource> findResources(Integer[] id) {
			Session session=sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(Resource.class);
			criteria.add(Property.forName("resourceid").in(id));
			List<Resource> resources = criteria.list();
			return resources;
	}

	@Override
	public List<Resource> findResources() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Resource ");
		List<Resource> resources = query.list();
		return resources;
	}

	@Override
	public Integer saveResources(Resource resource) {
		Session session=sessionFactory.getCurrentSession();
		Integer id =  (Integer)session.save(resource);
		return id;
	}

	@Override
	public void deleteResource(Resource resource) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(resource);
		String sql = "delete from prs_roleresources where resourceid = "+ resource.getResourceid();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.executeUpdate();
		session.flush();
	}
	
	public List<Resource> findResource(Role role){
		String sql = "select prs_resources.* " +
				"from prs_resources ,`prs_roleresources` ,prs_roles " +
				"where prs_resources.`resourceid`=`prs_roleresources`.`resourceid` " +
				"and prs_roleresources.`roleid` = `prs_roles`.`roleid` " +
				"and `prs_roles`.`roleid` = " + role.getRoleid();
		Session session=sessionFactory.getCurrentSession();
		SQLQuery sqlquery =  session.createSQLQuery(sql);
		sqlquery.addEntity(Resource.class);
		List<Resource> list = (List<Resource>)sqlquery.list();
		return list;
	}
	

	@Override
	public void updateResource(Resource resource) {
		Session session=sessionFactory.getCurrentSession();
		session.update(resource);
	}

	
	
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		String requestURL = ((FilterInvocation)object).getRequestUrl();
		
//		Map<String, String> resourceMap = securityService.loadUrlAuthorities();
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<Resource>  resources = findResources();
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
		
		return null;
	}

	public boolean supports(Class<?> clazz) {
		return true;
	}
	
	
	public void init() throws Exception{
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();
		List<Resource>  resources = findResources();
		for (Iterator iterator = resources.iterator(); iterator.hasNext();) {
			Resource resource = (Resource) iterator.next();
			Collection<ConfigAttribute> configAttributes = listToCollection(resource.getRoles());
			resourceMap.put(resource.getValue(), configAttributes);
		}
	}
	
	private Collection<ConfigAttribute> listToCollection(Collection<Role> roles){
		List<ConfigAttribute> list = new ArrayList<ConfigAttribute>();
		for (Role role : roles) {
			list.add(new SecurityConfig(role.getRolename()));
		}
		return list;
	}

}
