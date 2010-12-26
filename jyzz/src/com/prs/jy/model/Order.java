package com.prs.jy.model;

import java.io.Serializable;
import java.math.BigDecimal;
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


/**
 * 订单
 * 
 * @author laolu
 * 
				
	订单状态		用户显示	管理员显示
	0	用户建立订单，还没有结算。	在我的订单中显示，提交订单。	等待用户提交
	1	用户提交了订单，等待发货	等待发货	
	2	已经发货		
	3	等待付款		
	4	已经付款		
	5	收到货款		
	6	完成		
	7	继续发货		
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_ORDERS")
public class Order implements Serializable{
	private static final long serialVersionUID = -8533226742365854352L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderid")
	private Integer orderid;
	
	private Date createtime;
	
	private Integer userid;
	
	private String username;
	
	private String ordersn;
	
	private String status;
	
	private BigDecimal totalPrice;
	
	private Integer consigneeid;//收货人id
	
	@Transient 
	private Cart cart;//购物车
	
	@Transient 
	private Consignee consignee;//购物车
	
	@Transient 
	private Map<Integer, OrderItem> orderItemMap = new HashMap<Integer, OrderItem>();//购物车放在map中
	
	public List<OrderItem> getOrderItems(){
		return new ArrayList<OrderItem>(orderItemMap.values());
	}
	
	public void addOrderItem(OrderItem orderItem ){
		orderItemMap.put(orderItem.getOrderitemid(), orderItem);
	}
	
	public Order() {
		super();
	}



	public Order(Integer orderid, Date createtime, Integer userid,
			String username, String ordersn, String status,
			BigDecimal totalPrice, Integer consigneeid) {
		super();
		this.orderid = orderid;
		this.createtime = createtime;
		this.userid = userid;
		this.username = username;
		this.ordersn = ordersn;
		this.status = status;
		this.totalPrice = totalPrice;
		this.consigneeid = consigneeid;
	}

	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public BigDecimal getTotalPrice() {
		return totalPrice;
	}


	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Integer getConsigneeid() {
		return consigneeid;
	}

	public void setConsigneeid(Integer consigneeid) {
		this.consigneeid = consigneeid;
	}

	public Consignee getConsignee() {
		return consignee;
	}

	public void setConsignee(Consignee consignee) {
		this.consignee = consignee;
	}

}
