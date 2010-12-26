package com.prs.jy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * 购物分类
 * 
 * @author laolu
 * 
 */
@Component()
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_CARTITEMS")
public class CartItem implements Serializable,Comparable<CartItem>{
	private static final long serialVersionUID = 7523082303712290363L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "cartitemid")
	private Integer cartitemid;
	
	private Integer cartid;
	
	private Integer goodsid;
	
	private Integer quantity;//数量
	
	private Double price;//单价
	
	private Integer monthnum = PrsConstants.DEFALUT_MONTHNUM;//几个月,例如5个月，6个月等等
	
	
	@Transient 
	private Goods goods;
	
	@Transient 
	private Cart Cart;
	
	
	public CartItem() {
		super();
	}
	

	public CartItem(Goods goods, int quantity, int monthnum,Double price) {
		super();
		this.goods = goods;
		this.quantity = quantity;
		this.monthnum = monthnum;
		this.price = price;
	}


	/**
	 * 几本书的价格和
	 * @return
	 */
	public Double getTotalPrice(){
		
		return Arith.mul(quantity.doubleValue(),this.goods.getPrice().doubleValue());
	}
	/**
	 * 这个购物栏的价格和。书的单价*书的数量*借阅时间
	 * @return
	 */
	public Double getSum(){
        return Arith.mul(getTotalPrice(), getMonthnum().doubleValue());
	}
	
	public void increaseQuantity(){
		this.quantity++;
	}
	
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	public Integer getMonthnum() {
		return monthnum;
	}


	public void setMonthnum(Integer monthnum) {
		this.monthnum = monthnum;
	}


	public Integer getCartitemid() {
		return cartitemid;
	}


	public void setCartitemid(Integer cartitemid) {
		this.cartitemid = cartitemid;
	}


	public Integer getCartid() {
		return cartid;
	}


	public void setCartid(Integer cartid) {
		this.cartid = cartid;
	}


	public Integer getGoodsid() {
		return goodsid;
	}


	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}


	public Cart getCart() {
		return Cart;
	}


	public void setCart(Cart cart) {
		Cart = cart;
	}


	/**
	 * 
	 * 比较
	 */
	@Override
	public int compareTo(CartItem o) {
		if(this.monthnum > o.getMonthnum()){
			return 1;
		}else if (this.monthnum < o.getMonthnum()){
			return -1;
		}
		
		return 0;
	}
	
	public static void main(String[] args){
		CartItem item1 = new CartItem();
		item1.setMonthnum(12);
		
		CartItem item2 = new CartItem();
		item2.setMonthnum(6);
		
		
		CartItem item3 = new CartItem();
		item3.setMonthnum(7);
		
		List<CartItem> itemList = new ArrayList<CartItem>();
		
		itemList.add(item2);
		itemList.add(item1);
		itemList.add(item3);
		itemList.add(item3);
		
		
		Collections.sort(itemList);
		
		for (CartItem cartItem : itemList) {
			System.out.println(cartItem.getMonthnum());
		}
//		CartItem cartItem = Collections.max(itemList);//求最大值
//		System.out.println(cartItem.getMonthnum());
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}





}
