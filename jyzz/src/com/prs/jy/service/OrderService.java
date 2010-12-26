package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Cart;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Order;
import com.prs.jy.model.User;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
/**
 * 
 * @author laolu
 *
 */
public interface OrderService {
	public Order findOrder(Integer orderid);
	
	public List<Order> findOrders(User user);
	
	
	public Integer saveOrder(Order order);
	
	public void updateOrder(Order order);
	
	public void deleteOrder(Order order);
	
	/**
	 * 把购物车中的物品保存到订单中
	 * @param order
	 * @return
	 */
	public Order saveOrder(Cart cart,Integer consigneeid);
	/**
	 * 找出一页数据
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public ListWithPager<Order> findOrders(Pager pager) throws Exception;
	
	public ListWithPager<Order> findOrders(Pager pager,User user) throws Exception;

}
