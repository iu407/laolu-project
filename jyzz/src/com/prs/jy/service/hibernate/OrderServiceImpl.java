package com.prs.jy.service.hibernate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Cart;
import com.prs.jy.model.CartItem;
import com.prs.jy.model.Dispatch;
import com.prs.jy.model.Order;
import com.prs.jy.model.OrderItem;
import com.prs.jy.model.User;
import com.prs.jy.service.DispatchService;
import com.prs.jy.service.OrderItemService;
import com.prs.jy.service.OrderService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private OrderItemService  orderItemService;
	
	@Autowired
	private DispatchService dispatchService;

	@Override
	public void deleteOrder(Order order) {
		Session session=sessionFactory.getCurrentSession();		
		session.delete(order);
	}

	@Override
	public Order findOrder(Integer orderid) {
		Session session = sessionFactory.getCurrentSession();
		Order order = (Order)session.load(Order.class, orderid);
		List<OrderItem>  orderItems = orderItemService.findOrderItems(orderid);
		for (OrderItem orderItem : orderItems) {
			order.addOrderItem(orderItem);//放入到
		}
		return order;
	}

	@Override
	public List<Order> findOrders(User user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.addOrder(org.hibernate.criterion.Order.desc("createtime"));
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		List<Order> orders = criteria.list();
		return orders;
	}

	@Override
	public Integer saveOrder(Order order) {
		Session session=sessionFactory.getCurrentSession();
		Integer orderid =  (Integer)session.save(order);
		return orderid;
	}

	@Override
	public void updateOrder(Order order) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(order);
		
	}

	@Override
	public Order saveOrder(Cart cart,Integer consigneeid) {
		User user = SecurityUserHolder.getCurrentUser();
		Date createtime = new Date();
		Order order = new Order();
		order.setCreatetime(createtime);
		order.setUserid(user.getUserid());
		order.setUsername(user.getUsername());
		order.setStatus(PrsConstants.ORDER_PRE);
		order.setOrdersn(PrsConstants.getOrderSn(cart));//订单序列号
		order.setTotalPrice(new BigDecimal(cart.getTotalPrice()));
		order.setConsigneeid(consigneeid);
		Integer orderid = saveOrder(order);
		List<CartItem> cartItems = cart.getItems();
		for (CartItem cartItem : cartItems) {
			OrderItem orderItem = new OrderItem();
			orderItem.setGoods(cartItem.getGoods());
			orderItem.setGoodsid(cartItem.getGoods().getGoodsid());
			orderItem.setPrice(cartItem.getTotalPrice());
			orderItem.setQuantity(cartItem.getQuantity());
			orderItem.setMonthnum(cartItem.getMonthnum());
			orderItem.setOrderid(orderid);
			orderItem.setCreatetime(createtime);
			orderItem.setGoods(cartItem.getGoods());
			orderItemService.saveOrderItem(orderItem);//保存订单商品
			order.addOrderItem(orderItem);
		}
		Dispatch dispatch = cart.getDispatch();
		dispatch.setOrderid(orderid);
		dispatchService.saveDispatch(dispatch);
		
		
		return order;
	}

	@Override
	public ListWithPager<Order> findOrders(Pager pager) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.addOrder(org.hibernate.criterion.Order.desc("createtime"));
		Map<String,String> demand = pager.getDemand();
		
		Integer resultCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		pager.setResultCount(resultCount);
		
		List<Order> results = criteria.setProjection(null).setFirstResult(
				pager.getFirstIndex()).setMaxResults(pager.getPageSize())
				.list();
		
		for (Order order : results) {
			findOrder(order.getOrderid());
		}
		
		
		
		return new ListWithPager<Order>(results, pager);	
	}

	@Override
	public ListWithPager<Order> findOrders(Pager pager, User user)
			throws Exception {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Order.class);
		criteria.addOrder(org.hibernate.criterion.Order.desc("createtime"));
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		
		Map<String,String> demand = pager.getDemand();
		
		Integer resultCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		pager.setResultCount(resultCount);
		
		List<Order> results = criteria.setProjection(null).setFirstResult(
				pager.getFirstIndex()).setMaxResults(pager.getPageSize())
				.list();
		
		for (Order order : results) {
			findOrder(order.getOrderid());
		}
		return new ListWithPager<Order>(results, pager);	
		
	}
	
}
