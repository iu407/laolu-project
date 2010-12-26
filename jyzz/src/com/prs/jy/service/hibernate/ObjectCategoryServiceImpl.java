package com.prs.jy.service.hibernate;

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

import com.prs.jy.model.Category;
import com.prs.jy.model.ObjectCategory;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.ObjectCategoryService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("objectCategoryService")
public class ObjectCategoryServiceImpl implements ObjectCategoryService{
	@Autowired
	private SessionFactory sessionFactory;
//	
//	@Override
//	public List<Category> findAllCategory() {
//		Session session=sessionFactory.getCurrentSession();
//		Query query = session.createQuery("FROM Category ");
//		List<Category> Categorys = query.list();
//		return Categorys;
//	}
//
//	@Override
//	public Category findCategory(Integer categoryid) {
//		Session session = sessionFactory.getCurrentSession();
//		Category Category = (Category)session.load(Category.class, categoryid);
//		session.flush();
//		return Category;
//	}
//
//	@Override
//	public Integer saveCategory(Category category) {
//		Session session=sessionFactory.getCurrentSession();
//		Integer categoryid =  (Integer)session.save(category);
//		return categoryid;
//	}
//
//
//	@Override
//	public void updateCategory(Category category) {
//		Session session=sessionFactory.getCurrentSession();
//		session.saveOrUpdate(category);
//	}
//
//	@Override
//	public List<Category> findCategorys(Integer[] categoryids) {
//		Session session=sessionFactory.getCurrentSession();
//		Criteria criteria = session.createCriteria(Category.class);
//		criteria.add(Property.forName("Categoryid").in(categoryids));
//		List<Category> Categorys = criteria.list();
//		return Categorys;
//	}

	@Override
	public void deleteObjectCategory(ObjectCategory objectCategory) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(objectCategory);
	}

	@Override
	public List<ObjectCategory> findAllObjectCategorys() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM ObjectCategory ");
		List<ObjectCategory> objectCategorys = query.list();
		return objectCategorys;
	}

	@Override
	public ObjectCategory findObjectCategory(Integer objectid) {
		Session session = sessionFactory.getCurrentSession();
		ObjectCategory objectCategory = (ObjectCategory)session.load(ObjectCategory.class, objectid);
		return objectCategory;
	}

	@Override
	public List<ObjectCategory> findObjectCategory(Integer categoryid, String cattype) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ObjectCategory.class);
		criteria.add(Property.forName("categoryid").eq(categoryid));
		criteria.add(Property.forName("cattype").eq(cattype));
		List<ObjectCategory> objectCategorys = criteria.list();
		return objectCategorys;
	}

	@Override
	public List<ObjectCategory> findObjectCategoryBytargetid(Integer targetid,String cattype) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ObjectCategory.class);
		criteria.add(Property.forName("targetid").eq(targetid));
		criteria.add(Property.forName("cattype").eq(cattype));
		List<ObjectCategory> objectCategorys = criteria.list();
		return objectCategorys;
	}

	@Override
	public List<ObjectCategory> findObjectCategorys(Integer[] objectids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ObjectCategory.class);
		criteria.add(Property.forName("targetid").in(objectids));
		List<ObjectCategory> objectCategorys = criteria.list();
		return objectCategorys;
	}

	@Override
	public List<ObjectCategory> findObjectCategorys(String cattype) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(ObjectCategory.class);
		criteria.add(Property.forName("cattype").eq(cattype));
		List<ObjectCategory> objectCategorys = criteria.list();
		return objectCategorys;
	}

	@Override
	public Integer saveObjectCategory(ObjectCategory objectCategory) {
		Session session=sessionFactory.getCurrentSession();
		Integer objectid = (Integer)session.save(objectCategory);
		return objectid;
	}

	@Override
	public void updateObjectCategory(ObjectCategory objectCategory) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(objectCategory);
	}
	
}
