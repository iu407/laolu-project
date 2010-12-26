package com.prs.jy.utils;

import java.util.HashMap;
import java.util.List;

import com.prs.jy.model.DispatchItem;
import com.prs.jy.model.Goods;


/**
 * @author laolu
 * @version 1.0
 */
public class DispatchItemMap extends HashMap<Integer, DispatchItem>{
	private static final long serialVersionUID = -3351732179611896471L;
	
	
	private Integer seq;
	private DispatchItem dispatchItem;
	
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public DispatchItem getDispatchItem() {
		return dispatchItem;
	}
	public void setDispatchItem(DispatchItem dispatchItem) {
		this.dispatchItem = dispatchItem;
	}
	@Override
	public DispatchItem put(Integer key, DispatchItem value) {
		if(containsKey(key)){
			List<Goods>  goodses = value.getGoodsList();
			for(Goods goods : goodses){
				this.get(key).addGoods(goods);
			}
			return super.put(key, this.get(key));
		}
		return super.put(key, value);
	}
	
	
	
}
