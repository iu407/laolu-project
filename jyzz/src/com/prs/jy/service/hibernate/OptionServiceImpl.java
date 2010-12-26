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

import com.prs.jy.model.Option;
import com.prs.jy.service.OptionService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("optionService")
public class OptionServiceImpl implements OptionService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Option> findAllOption() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Option ");
		List<Option> Options = query.list();
		return Options;
	}

	@Override
	public Option findOption(Integer optionid) {
		Session session = sessionFactory.getCurrentSession();
		Option Option = (Option)session.load(Option.class, optionid);
		session.flush();
		return Option;
	}

	@Override
	public Integer saveOption(Option option) {
		Session session=sessionFactory.getCurrentSession();
		Integer optionid =  (Integer)session.save(option);
		return optionid;
	}


	@Override
	public void updateOption(Option option) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(option);
	}

	@Override
	public List<Option> findOptions(Integer[] optionids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Option.class);
		criteria.add(Property.forName("optionid").in(optionids));
		List<Option> Options = criteria.list();
		return Options;
	}

	@Override
	public void deleteOption(Option option) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(option);
	}
}
