package com.prs.jy.service.hibernate;

import java.util.List;

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

import com.prs.jy.model.CartItem;
import com.prs.jy.model.Cart;
import com.prs.jy.model.DispatchItem;
import com.prs.jy.model.DispatchItemGoods;
import com.prs.jy.model.Goods;
import com.prs.jy.model.User;
import com.prs.jy.service.CartItemService;
import com.prs.jy.service.DispatchItemGoodsService;
import com.prs.jy.service.GoodsService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("dispatchItemGoodsService")
public class DispatchItemGoodsServiceImpl implements DispatchItemGoodsService{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void deleteDispatchItemGoods(User user) {
		
	}

	@Override
	public void deleteDispatchItemGoods(DispatchItemGoods dispatchItemGoodsid) {
		
	}

	@Override
	public DispatchItemGoods findADispatchItemGoods(User user) {
		return null;
	}

	@Override
	public List<DispatchItemGoods> findAllDispatchItemGoods() {
		return null;
	}

	@Override
	public DispatchItemGoods findDispatchItemGoods(Integer dispatchItemGoodsid) {
		Session session = sessionFactory.getCurrentSession();
		DispatchItemGoods dispatchItemGoods = (DispatchItemGoods)session.load(DispatchItemGoods.class, dispatchItemGoodsid);
		return dispatchItemGoods;
	}

	@Override
	public List<DispatchItemGoods> findDispatchItemGoods(User user) {
		return null;
	}

	@Override
	public Integer saveDispatchItemGoods(DispatchItemGoods dispatchItemGoods) {
		Session session=sessionFactory.getCurrentSession();
		Integer dispatchitemgoodsid = (Integer)session.save(dispatchItemGoods);
		return dispatchitemgoodsid;
	}

	@Override
	public void updateDispatchItemGoods(DispatchItemGoods dispatchItemGoodsid) {
		
	}

	@Override
	public List<DispatchItemGoods> findDispatchItemGoods( DispatchItem dispatchItem) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(DispatchItemGoods.class);
		criteria.add(Property.forName("dispatchItemId").eq(dispatchItem.getDispatchItemId()));
		List<DispatchItemGoods> dispatchItemGoodsList = criteria.list();
		return dispatchItemGoodsList;
	}
	
	
}
