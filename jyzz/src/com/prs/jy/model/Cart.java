package com.prs.jy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import com.prs.jy.utils.Arith;
import com.prs.jy.utils.PrsConstants;


/**
 * 购物车<br/>
 * 一个用户只有一个购物车。要么在session中要么在数据库中
 * @author laolu
 * 
 */

@Component()
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_CARTS")
public class Cart implements Serializable{
	private static final long serialVersionUID = 7523082303712290363L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cartid")
	private Integer cartid;
	
	private Integer userid;
	
	private String  username;
	
	private Date createtime;
	

	@Transient 
	private Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();//购物车放在map中
	
	@Transient 
	private Dispatch dispatch;
	
	public Cart() {
		super();
	}
	
	public List<CartItem> getItems(){
		return new ArrayList<CartItem>(map.values());
	}
	
	public void clearCart(){
		List<CartItem> items = getItems();
		items = null;
	}
	
	/**
	 * 增加一个商品
	 * @param goods
	 */
	public void addItem(Goods goods) {
		int goodsid = goods.getGoodsid();
		CartItem cartItem = map.get(goodsid);
		if (cartItem != null){
			cartItem.increaseQuantity();
//			map.put(goodsid, new CartItem(goods, cartItem.getQuantity()));//
		}else{
			map.put(goodsid, new CartItem(goods, 1,PrsConstants.DEFALUT_MONTHNUM,goods.getPrice().doubleValue()) );
		}
	}
	/**
	 * 减少一个商品
	 * @param goods
	 */
	public void deleteItem(Goods goods) {
		int goodsid = goods.getGoodsid();
		map.remove(goodsid);
	}
	/**
	 *	得到购物车的总价
	 * @return
	 */
	public Double getTotalPrice() {
		Double total = 0.00;
		for (CartItem item : map.values())
		{
			total = Arith.add(total, item.getSum());
		}
		return total;
	}

	public Integer getCartid() {
		return cartid;
	}

	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Map<Integer, CartItem> getMap() {
		return map;
	}

	public void setMap(Map<Integer, CartItem> map) {
		this.map = map;
	}

	public Dispatch getDispatch() {
		return dispatch;
	}

	public void setDispatch(Dispatch dispatch) {
		this.dispatch = dispatch;
	}
}
