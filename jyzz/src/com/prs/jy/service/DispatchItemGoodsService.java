package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.DispatchItem;
import com.prs.jy.model.DispatchItemGoods;
import com.prs.jy.model.User;
/**
 * 配送商品
 * @author laolu
 *
 */
public interface DispatchItemGoodsService {
	public DispatchItemGoods findDispatchItemGoods(Integer dispatchItemGoodsid);
	public List<DispatchItemGoods> findDispatchItemGoods(User user);
	
	public List<DispatchItemGoods> findDispatchItemGoods(DispatchItem dispatchItem);
	
	
	public DispatchItemGoods findADispatchItemGoods(User user);
	
	
	public void deleteDispatchItemGoods(User user);
	
	public List<DispatchItemGoods> findAllDispatchItemGoods();
	public Integer saveDispatchItemGoods(DispatchItemGoods dispatchItemGoods);
	
	public void updateDispatchItemGoods(DispatchItemGoods dispatchItemGoodsid);
	
	public void deleteDispatchItemGoods(DispatchItemGoods dispatchItemGoodsid);

}
