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
import com.prs.jy.model.DispatchItemGoods;
import com.prs.jy.model.User;
import com.prs.jy.service.DispatchItemGoodsService;
import com.prs.jy.service.DispatchItemService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("dispatchItemService")
public class DispatchItemServiceImpl implements DispatchItemService{
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private DispatchItemGoodsService dispatchItemGoodsService;
	
	@Override
	public List<DispatchItem> findAllDispatchItem() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM DispatchItem ");
		List<DispatchItem> DispatchItems = query.list();
		return DispatchItems;
	}

	@Override
	public DispatchItem findDispatchItem(Integer dispatchItemid) {
		Session session = sessionFactory.getCurrentSession();
		DispatchItem DispatchItem = (DispatchItem)session.load(DispatchItem.class, dispatchItemid);
		return DispatchItem;
	}

	@Override
	public Integer saveDispatchItem(DispatchItem dispatchItem) {
		Session session=sessionFactory.getCurrentSession();
		Integer dispatchItemid = (Integer)session.save(dispatchItem);
		
		List<DispatchItemGoods> dispatchItemGoodsList = dispatchItem.getDispatchItemGoodsList();
		for (DispatchItemGoods dispatchItemGoods : dispatchItemGoodsList) {
			dispatchItemGoods.setDispatchItemId(dispatchItemid);
			dispatchItemGoodsService.saveDispatchItemGoods(dispatchItemGoods);
			
		}
		return dispatchItemid;
	}


	@Override
	public void updateDispatchItem(DispatchItem dispatchItem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(dispatchItem);
	}

	@Override
	public void deleteDispatchItem(DispatchItem dispatchItem) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(dispatchItem);
	}

	@Override
	public List<DispatchItem> findDispatchItem(User user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DispatchItem.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		criteria.addOrder(Order.desc("dispatchItemid"));
		
		List<DispatchItem> dispatchItems = criteria.list();
		
		return dispatchItems;
	}

	@Override
	public void deleteDispatchItem(User user) {
		List<DispatchItem> dispatchItems = findDispatchItem(user);
		for (DispatchItem dispatchItem : dispatchItems) {
			deleteDispatchItem(dispatchItem);
		}
	}

	@Override
	public DispatchItem findADispatchItem(User user) {
		if(user==null){
			return null;
		}
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DispatchItem.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		criteria.addOrder(Order.desc("dispatchItemid"));
		
		DispatchItem dispatchItem = (DispatchItem)criteria.uniqueResult();
		return dispatchItem;
	}

	@Override
	public List<DispatchItem> findDispatchItem(Dispatch dispatch) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DispatchItem.class);
		criteria.add(Property.forName("dispatchId").eq(dispatch.getDispatchId()));
		List<DispatchItem> dispatchItems = criteria.list();
		
		for (DispatchItem dispatchItem : dispatchItems) {
			List<DispatchItemGoods>  dispatchItemGoodsList = dispatchItemGoodsService.findDispatchItemGoods(dispatchItem);
			for (DispatchItemGoods dispatchItemGoods : dispatchItemGoodsList) {
				dispatchItem.addDispatchItemGoodsMap(dispatchItemGoods);
			}
			
		}
		
		return dispatchItems;
	}
	
	
}
