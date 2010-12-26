package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Dispatch;
import com.prs.jy.model.Order;
import com.prs.jy.model.User;
/**
 * 购物车
 * @author laolu
 *
 */
public interface DispatchService {
	public Dispatch findDispatch(Integer dispatchid);
	
	public Dispatch findDispatch(Order order);
	
	public List<Dispatch> findDispatch(User user);
	
	public Dispatch findADispatch(User user);
	
	public void deleteDispatch(User user);
	
	public List<Dispatch> findAllDispatch();
	
	public Integer saveDispatch(Dispatch dispatch);
	
	public void updateDispatch(Dispatch dispatchid);
	
	public void deleteDispatch(Dispatch dispatchid);

}
