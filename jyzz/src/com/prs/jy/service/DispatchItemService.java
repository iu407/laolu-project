package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Dispatch;
import com.prs.jy.model.DispatchItem;
import com.prs.jy.model.User;
/**
 * 购物车
 * @author laolu
 *
 */
public interface DispatchItemService {
	public DispatchItem findDispatchItem(Integer dispatchItemid);
	public List<DispatchItem> findDispatchItem(User user);
	
	public List<DispatchItem> findDispatchItem(Dispatch dispatch);
	
	public DispatchItem findADispatchItem(User user);
	public void deleteDispatchItem(User user);
	
	public List<DispatchItem> findAllDispatchItem();
	public Integer saveDispatchItem(DispatchItem dispatchItem);
	public void updateDispatchItem(DispatchItem dispatchItemid);
	public void deleteDispatchItem(DispatchItem dispatchItemid);

}
