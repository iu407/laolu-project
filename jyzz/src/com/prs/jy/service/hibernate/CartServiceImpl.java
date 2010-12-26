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

import com.prs.jy.model.Cart;
import com.prs.jy.model.CartItem;
import com.prs.jy.model.User;
import com.prs.jy.service.CartItemService;
import com.prs.jy.service.CartService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("cartService")
public class CartServiceImpl implements CartService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Override
	public List<Cart> findAllCart() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Cart ");
		List<Cart> Carts = query.list();
		return Carts;
	}

	@Override
	public Cart findCart(Integer cartid) {
		Session session = sessionFactory.getCurrentSession();
		Cart Cart = (Cart)session.load(Cart.class, cartid);
		return Cart;
	}

	@Override
	public Integer saveCart(Cart cart) {
		Session session=sessionFactory.getCurrentSession();
		
//		Cart aCart = findCart(cart.getCartid());
		Integer cartid =null;
//		if(aCart==null){
			cartid =  (Integer)session.save(cart);
			List<CartItem> cartItems = cart.getItems();
			for (CartItem cartItem : cartItems) {
				cartItem.setCartid(cartid);
				cartItem.setGoodsid(cartItem.getGoods().getGoodsid());
				cartItemService.saveCartItem(cartItem);
			}
//		}else{
//			session.saveOrUpdate(cart);
//			List<CartItem> cartItems = cart.getItems();
//			for (CartItem cartItem : cartItems) {
//				cartItem.setCartid(cartid);
//				cartItem.setGoodsid(cartItem.getGoods().getGoodsid());
//				cartItemService.saveCartItem(cartItem);
//			}
//		}
		
		
		return cartid;
	}


	@Override
	public void updateCart(Cart cart) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cart);
	}

	@Override
	public void deleteCart(Cart cart) {
		Session session=sessionFactory.getCurrentSession();
		
		List<CartItem> cartItems = cart.getItems();
		for (CartItem cartItem : cartItems) {
			cartItemService.deleteCartItem(cartItem);
		}
		session.delete(cart);
	}

	@Override
	public List<Cart> findCart(User user) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Cart.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		criteria.addOrder(Order.desc("cartid"));
		
		List<Cart> carts = criteria.list();
		for (Cart cart : carts) {
			Integer cartid = cart.getCartid();
			List<CartItem> cartItems = cartItemService.findCartItems(cartid);
		}
		
		return criteria.list();
	}

	@Override
	public void deleteCart(User user) {
		List<Cart> carts = findCart(user);
		for (Cart cart : carts) {
			deleteCart(cart);
		}
	}

	@Override
	public Cart findACart(User user) {
		if(user==null){
			return null;
		}
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Cart.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		criteria.addOrder(Order.desc("cartid"));
		
		Cart cart = (Cart)criteria.uniqueResult();
		if(cart != null)
		{
			Integer cartid = cart.getCartid();
			List<CartItem> cartItems = cartItemService.findCartItems(cartid);
			for(CartItem cartItem : cartItems){
				Map<Integer,CartItem> cartItemMap = new HashMap<Integer,CartItem>();
				cartItemMap.put(cartItem.getGoodsid(), cartItem);
				cart.setMap(cartItemMap);
			}
		}
		
		
		return cart;
	}
	
	
}
