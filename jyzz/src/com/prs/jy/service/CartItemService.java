package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.CartItem;
import com.prs.jy.model.User;
/**
 * 购物车元素
 * @author laolu
 *
 */
public interface CartItemService {
	public CartItem findCartItem(Integer cartitemid);
	
	public List<CartItem> findCartItems(Integer cartid);
	
	public List<CartItem> findAllCartItem();
	/**
	 * save or update
	 * @param cartitem
	 */
	public void saveCartItem(CartItem cartitem);
	
	public void updateCartItem(CartItem cartitemid);
	
	public void deleteCartItem(CartItem cartitem);

}
