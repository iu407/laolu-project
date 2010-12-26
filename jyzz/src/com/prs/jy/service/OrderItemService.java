package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.OrderItem;
/**
 * 
 * @author laolu
 *
 */
public interface OrderItemService {
	public OrderItem findOrderItem(Integer orderitemid);
	
	public List<OrderItem> findOrderItems(Integer orderid);
	
//	public List<OrderItem> findOrderItems(Integer[] orderitemids);
	
	public List<OrderItem> findAllOrderItem();

//	public List<OrderItem> findOrderItems(String cattype);
	
	public Integer saveOrderItem(OrderItem orderitem);
	
	public void updateOrderItem(OrderItem orderitem);
	
	public void deleteOrderItem(OrderItem orderitem);

}
