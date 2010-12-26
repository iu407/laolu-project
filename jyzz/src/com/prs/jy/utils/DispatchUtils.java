package com.prs.jy.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.prs.jy.model.CartItem;
import com.prs.jy.model.Dispatch;
import com.prs.jy.model.DispatchItem;
import com.prs.jy.model.Goods;
import com.prs.jy.model.User;
import com.prs.jy.service.SecurityUserHolder;




/**
 * 配送单工具
 * @author laolu
 * @version 1.0
 */
@Scope("prototype") 
@Repository("dispatchUtils")
public class DispatchUtils {
	
	public DispatchUtils(){
		
	}
	
	public Dispatch buildDispatch(List<CartItem> cartItems){
		User user = SecurityUserHolder.getCurrentUser();
		
		List<DispatchItem> dispatchItems = new ArrayList<DispatchItem>();
		for(CartItem cartItem :  cartItems){//之一部叫做组合配送项
			Integer monthnum = cartItem.getMonthnum();
			
			for(int i = 0 ; i  < monthnum ; i++){
				DispatchItem dispatchItem = new DispatchItem();
				dispatchItem.setItemNum(i);//配送数量
				dispatchItem.setSequence(i);
				dispatchItem.setUserid(user.getUserid());
				dispatchItem.setUsername(user.getUsername());
				Calendar cal = Calendar.getInstance();//现在时间
				cal.add(Calendar.MONTH,(PrsConstants.ADD_MONTH_NUM + i));//增加2个月
				Date dispatchDate = cal.getTime();
				dispatchItem.setDispatchDate(dispatchDate);
				for(int j = 0 ; j < cartItem.getQuantity() ; j++){
					dispatchItem.addGoods(cartItem.getGoods());//几个goods//这里最好放dispatchItemgoods
				}
				dispatchItems.add(dispatchItem);
			}
		}
		Dispatch dispatch = new Dispatch();//配送单
		dispatch.setCreatetime(new Date());
		dispatch.setUserid(user.getUserid());
		dispatch.setUsername(user.getUsername());
		dispatch.setStatus(Dispatch.DISPATCH_SUMIT_ORDER);
		
		
		for(DispatchItem dispatchItem : dispatchItems){
			dispatch.addDispatchItem(dispatchItem);//合并同类项
		}
		return dispatch;
	}
}
