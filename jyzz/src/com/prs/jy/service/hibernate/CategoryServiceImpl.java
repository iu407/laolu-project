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
import com.prs.jy.service.CategoryService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Category> findAllCategory() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Category ");
		List<Category> Categorys = query.list();
		return Categorys;
	}

	@Override
	public Category findCategory(Integer categoryid) {
		Session session = sessionFactory.getCurrentSession();
		Category Category = (Category)session.load(Category.class, categoryid);
		session.flush();
		return Category;
	}

	@Override
	public Integer saveCategory(Category category) {
		Session session=sessionFactory.getCurrentSession();
		Integer categoryid =  (Integer)session.save(category);
		return categoryid;
	}


	@Override
	public void updateCategory(Category category) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}

	@Override
	public List<Category> findCategorys(Integer[] categoryids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Category.class);
		criteria.add(Property.forName("Categoryid").in(categoryids));
		List<Category> Categorys = criteria.list();
		return Categorys;
	}

	@Override
	public void deleteCategory(Category category) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(category);
	}

	@Override
	public List<Category> findCategorys(String cattype) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Category.class);
		criteria.add(Property.forName("cattype").eq(cattype));
		List<Category> Categorys = criteria.list();
		return Categorys;
	}
	
}
