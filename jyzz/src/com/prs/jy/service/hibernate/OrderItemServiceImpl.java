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

import com.prs.jy.model.OrderItem;
import com.prs.jy.model.User;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.OrderItemService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private GoodsService goodsService;

	@Override
	public void deleteOrderItem(OrderItem orderitem) {
		Session session=sessionFactory.getCurrentSession();		
		session.delete(orderitem);
	}

	@Override
	public List<OrderItem> findAllOrderItem() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM OrderItem ");
		List<OrderItem> orderItems = query.list();
		
		for (OrderItem orderItem : orderItems) {
			orderItem.setGoods( goodsService.findGoods(orderItem.getGoodsid()));
		}
		return orderItems;
	}

	@Override
	public OrderItem findOrderItem(Integer orderitemid) {
		Session session = sessionFactory.getCurrentSession();
		OrderItem orderItem = (OrderItem)session.load(OrderItem.class, orderitemid);
		
		orderItem.setGoods( goodsService.findGoods(orderItem.getGoodsid()));
		
		return orderItem;
	}

	@Override
	public Integer saveOrderItem(OrderItem orderitem) {
		Session session=sessionFactory.getCurrentSession();
		Integer orderitemid = (Integer)session.save(orderitem);
		return orderitemid;	
	}

	@Override
	public void updateOrderItem(OrderItem orderitem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(orderitem);		
	}

	@Override
	public List<OrderItem> findOrderItems(Integer orderid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(OrderItem.class);
		criteria.add(Property.forName("orderid").eq(orderid));
		List<OrderItem> orderItems = criteria.list();
		for (OrderItem orderItem : orderItems) {
			orderItem.setGoods( goodsService.findGoods(orderItem.getGoodsid()));
		}
		return orderItems;
	}

	
}
