package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Cart;
import com.prs.jy.model.User;
/**
 * 购物车
 * @author laolu
 *
 */
public interface CartService {
	public Cart findCart(Integer cartid);
	/**
	 * 得到用户的购物车
	 * @param user
	 * @return
	 */
	public List<Cart> findCart(User user);
	/**
	 * 找到这个用户下的购物车
	 * 一般认为一个用户只能有一个购物车
	 * @param user
	 * @return
	 */
	public Cart findACart(User user);
	/**
	 * 删除用户的购物车
	 * @param user
	 * @return
	 */
	public void deleteCart(User user);
	
	public List<Cart> findAllCart();
	/**
	 * 保存购物车
	 * 并且同时保存购物项
	 * @param cart
	 * @return
	 */
	public Integer saveCart(Cart cart);
	
	public void updateCart(Cart cartid);
	
	public void deleteCart(Cart cartid);

}
