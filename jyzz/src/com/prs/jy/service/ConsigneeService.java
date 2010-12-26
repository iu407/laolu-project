package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Consignee;
import com.prs.jy.model.User;
/**
 * 
 * @author laolu
 *
 */
public interface ConsigneeService {
	public Consignee findConsignee(Integer consigneeid);
	
	public List<Consignee> findConsignees(Integer[] consigneeids);
	
	
	public Integer saveConsignee(Consignee consignee);
	
	public void updateConsignee(Consignee consignee);
	
	public void deleteConsignee(Consignee consignee);

	public List<Consignee>  findConsignee(User user);
	
	public void updateToDefault(Consignee consignee);

}
