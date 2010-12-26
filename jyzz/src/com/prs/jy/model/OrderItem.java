package com.prs.jy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;


/**
 * 订单项
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_ORDERITEMS")
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 7116543102460887730L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderitemid")
	private Integer orderitemid;
	
	private Integer orderid;//订单id
	
	private Integer goodsid;
	private Double price;//价格
	private Integer quantity;//数量

	private Integer monthnum;//订单项，几个月
	
	private Date createtime;
	
	@Transient 
	private Goods goods;
	
	public OrderItem() {
		super();
	}

	public OrderItem(Integer orderitemid, Integer orderid, Integer goodsid,
			Double price, Integer quantity, Integer monthnum,
			Date createtime) {
		super();
		this.orderitemid = orderitemid;
		this.orderid = orderid;
		this.goodsid = goodsid;
		this.price = price;
		this.quantity = quantity;
		this.monthnum = monthnum;
		this.createtime = createtime;
	}

	public Integer getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(Integer orderitemid) {
		this.orderitemid = orderitemid;
	}

	public Integer getGoodsid() {
		return goodsid;
	}

	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getQuantity() {
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

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	
}
