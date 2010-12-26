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
/**
 * 配送项
 * @author laolu
 * 
 */

@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_DISPATCHITEMS")
public class DispatchItem implements Serializable{
	private static final long serialVersionUID = 665170775988439008L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dispatchItemId")
	private Integer dispatchItemId;//配送项id
	
	private Integer dispatchId;//配送单id
	private Date dispatchDate;//配送时间
	
	private Integer userid;
	private String username;
	
	private Integer itemNum;//配送数量
	//序号
	private Integer sequence;//例如0，表示第一个月，1：表示第二个月
	
	@Transient
	private List<Goods> goodsList = new ArrayList<Goods>();//配送商品列表
	
	@Transient
	private Map<Integer,DispatchItemGoods> dispatchItemGoodsMap = new HashMap<Integer,DispatchItemGoods>();//配送商品列表
	

	public DispatchItem() {
		super();
	}

	public void addGoods(Goods goods){
		goodsList.add(goods);
		
		int goodsid = goods.getGoodsid();
		DispatchItemGoods dispatchItemGoods = dispatchItemGoodsMap.get(goodsid);
		if(dispatchItemGoods != null){
			dispatchItemGoods.increaseNum();
		}else{
			DispatchItemGoods aDispatchItemGoods = new DispatchItemGoods();
			aDispatchItemGoods.setNum(1);
			aDispatchItemGoods.setGoods(goods);
			aDispatchItemGoods.setGoodsid(goods.getGoodsid());
			aDispatchItemGoods.setPrice(goods.getPrice());
			dispatchItemGoodsMap.put(goodsid, aDispatchItemGoods);
		}
		
	}
	/**
	 * 返回dispathcitemgoodslist
	 * @return
	 */
	public List<DispatchItemGoods> getDispatchItemGoodsList(){
		return new ArrayList<DispatchItemGoods>(dispatchItemGoodsMap.values());
	}
	public void addDispatchItemGoodsMap(DispatchItemGoods dispatchItemGoods ){
		dispatchItemGoodsMap.put(dispatchItemGoods.getGoodsid(), dispatchItemGoods);
		
	}

	public Integer getDispatchItemId() {
		return dispatchItemId;
	}

	public void setDispatchItemId(Integer dispatchItemId) {
		this.dispatchItemId = dispatchItemId;
	}

	public Date getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(Date dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
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

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}


	public Integer getSequence() {
		return sequence;
	}


	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

}
