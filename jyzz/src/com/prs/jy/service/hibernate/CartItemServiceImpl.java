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
import com.prs.jy.model.User;
import com.prs.jy.service.CartItemService;
import com.prs.jy.service.GoodsService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private GoodsService goodsService;
	
	
	@Override
	public List<CartItem> findAllCartItem() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM CartItem ");
		List<CartItem> CartItems = query.list();
		return CartItems;
	}

	@Override
	public CartItem findCartItem(Integer cartitemid) {
		Session session = sessionFactory.getCurrentSession();
		CartItem CartItem = (CartItem)session.load(CartItem.class, cartitemid);
		return CartItem;
	}

	@Override
	public void saveCartItem(CartItem cartitem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartitem);
	}


	@Override
	public void updateCartItem(CartItem cartitem) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartitem);
	}

	@Override
	public void deleteCartItem(CartItem cartitem) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(cartitem);
	}

	@Override
	public List<CartItem> findCartItems(Integer cartid) {
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(CartItem.class);
		criteria.add(Property.forName("cartid").eq(cartid));
		List<CartItem> cartItems = criteria.list();
		for (CartItem cartItem : cartItems) {
			Integer goodsid = cartItem.getGoodsid();
			cartItem.setGoods(goodsService.findGoods(goodsid));
		}
		return cartItems;
	}
	
	
}
