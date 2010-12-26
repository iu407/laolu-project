package com.prs.jy.service.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Dispatch;
import com.prs.jy.model.DispatchItem;
import com.prs.jy.model.User;
import com.prs.jy.service.DispatchItemService;
import com.prs.jy.service.DispatchService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("dispatchService")
public class DispatchServiceImpl implements DispatchService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private DispatchItemService dispatchItemService;
	
	@Override
	public List<Dispatch> findAllDispatch() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Dispatch ");
		List<Dispatch> Dispatchs = query.list();
		return Dispatchs;
	}

	@Override
	public Dispatch findDispatch(Integer dispatchid) {
		Session session = sessionFactory.getCurrentSession();
		Dispatch Dispatch = (Dispatch)session.load(Dispatch.class, dispatchid);
		return Dispatch;
	}

	@Override
	public Integer saveDispatch(Dispatch dispatch) {
		Session session=sessionFactory.getCurrentSession();
		Integer dispatchid = (Integer)session.save(dispatch);
		
		List<DispatchItem> dispatchItemList = dispatch.getDispatchItemList();
		for (DispatchItem dispatchItem : dispatchItemList) {
			dispatchItem.setDispatchId(dispatchid);
			dispatchItemService.saveDispatchItem(dispatchItem);
		}
		
		return dispatchid;
	}


	@Override
	public void updateDispatch(Dispatch dispatch) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(dispatch);
	}

	@Override
	public void deleteDispatch(Dispatch dispatch) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(dispatch);
	}

	@Override
	public List<Dispatch> findDispatch(User user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dispatch.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		criteria.addOrder(Order.desc("dispatchid"));
		return criteria.list();
	}

	@Override
	public void deleteDispatch(User user) {
		List<Dispatch> dispatchs = findDispatch(user);
		for (Dispatch dispatch : dispatchs) {
			deleteDispatch(dispatch);
		}
	}

	@Override
	public Dispatch findADispatch(User user) {
		if(user==null){
			return null;
		}
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dispatch.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		criteria.addOrder(Order.desc("dispatchid"));
		Dispatch dispatch = (Dispatch)criteria.uniqueResult();
		return dispatch;
	}

	@Override
	public Dispatch findDispatch(com.prs.jy.model.Order order) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Dispatch.class);
		criteria.add(Property.forName("orderid").eq(order.getOrderid()));
		Dispatch dispatch = (Dispatch)criteria.uniqueResult();
		List<DispatchItem> dispatchItemList = dispatchItemService.findDispatchItem(dispatch);
		dispatch.setDispatchItemList(dispatchItemList);
		return dispatch;
	}
	
}
