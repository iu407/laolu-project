package com.prs.jy.model;

import java.io.Serializable;
import java.math.BigDecimal;

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
 * 
 * 配送的商品相同的商品拼成一个
 * @author laolu
 * 
 */

@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_DISPATCHITEMGOODSES")
public class DispatchItemGoods implements Serializable{
	private static final long serialVersionUID = -2752317880583469160L;

	//主键
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dispatchitemgoodsid")
	private Integer dispatchitemgoodsid;
	
	private Integer dispatchItemId;//配送项id
	
	private Integer goodsid;
	
	private Integer num = 0;
	private BigDecimal price;//单价这个配送项所表示的价格。是真实的价格
	
	@Transient
	private Goods goods;
	
	public DispatchItemGoods() {
		super();
	}
	

	public Integer getDispatchitemgoodsid() {
		return dispatchitemgoodsid;
	}


	public void setDispatchitemgoodsid(Integer dispatchitemgoodsid) {
		this.dispatchitemgoodsid = dispatchitemgoodsid;
	}
	
	
	public Integer getGoodsid() {
		return goodsid;
	}
	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getDispatchItemId() {
		return dispatchItemId;
	}


	public void setDispatchItemId(Integer dispatchItemId) {
		this.dispatchItemId = dispatchItemId;
	}
	
	public void increaseNum(){
		this.num++;
	}
}
