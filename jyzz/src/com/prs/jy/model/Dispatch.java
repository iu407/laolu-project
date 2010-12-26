package com.prs.jy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
/**
 * 配送单
 * 一个配送单有多个配送项，一个配送项有多个配送的商品
 * 理论上一个配送单对应一个订单
 * @author laolu
 * 
 */

@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_DISPATCHES")
public class Dispatch implements Serializable{
	private static final long serialVersionUID = 571912684564316001L;
	
	public static final String DISPATCH_SUMIT_ORDER = "0";//确认订单;状态
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "dispatchId")
	private Integer dispatchId;//配送单id
	
	private Date createtime;//创建时间
	private Integer userid;//用户id.表示那个人的配送单，例如：张三的订单，自然是张三的配送单，要送给张三
	private String username;//用户名
	
	private String status;//配送单状态，需要一个状态变化表。
	
	private Integer orderid;
	
	@Transient 
	private List<DispatchItem> dispatchItemList = new ArrayList<DispatchItem>();
	@Transient 
	private List<Integer> seqList = new ArrayList<Integer>();
	
	/**
	 * 增加配送项
	 * @param dispatchItem
	 */
	public void addDispatchItem(DispatchItem dispatchItem){
		dispatchItemList.add(dispatchItem);
		Integer seq = dispatchItem.getSequence();
		
		if(seqList.contains(seq)){
			dispatchItemList.remove(dispatchItem);
			//合并
			for(DispatchItem item : dispatchItemList){
				Integer seq1 = item.getSequence();
				if(seq1.equals(dispatchItem.getSequence())){
					List<Goods> goodses = dispatchItem.getGoodsList();
					for(Goods goods :goodses){
						item.addGoods(goods);
					}
					break;
				}
		}
		}else{
			seqList.add(seq);
		}
		

	}
	
	public Dispatch() {
		super();
	}

	public Dispatch(Integer dispatchId, List<DispatchItem> dispatchItemList) {
		super();
		this.dispatchId = dispatchId;
		this.dispatchItemList = dispatchItemList;
	}

	public Integer getDispatchId() {
		return dispatchId;
	}

	public void setDispatchId(Integer dispatchId) {
		this.dispatchId = dispatchId;
	}

	public List<DispatchItem> getDispatchItemList() {
		return dispatchItemList;
	}

	public void setDispatchItemList(List<DispatchItem> dispatchItemList) {
		this.dispatchItemList = dispatchItemList;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getOrderid() {
		return orderid;
	}

	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	
	
}
