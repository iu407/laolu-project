package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.ObjectCategory;
/**
 * 对象类型
 * @author laolu
 *
 */
public interface ObjectCategoryService {
	/**
	 * 通过主键
	 * @param objectid
	 * @return
	 */
	public ObjectCategory findObjectCategory(Integer objectid);
	
	/**
	 * 通过targetid
	 * @param targetid
	 * @return
	 */
	public List<ObjectCategory> findObjectCategoryBytargetid(Integer targetid,String cattype);
	
	public List<ObjectCategory> findObjectCategory(Integer categoryid,String cattype);
	
	public List<ObjectCategory> findObjectCategorys(Integer[] objectids);
	
	public List<ObjectCategory> findObjectCategorys(String cattype);
	
	public List<ObjectCategory> findAllObjectCategorys();
	
	public Integer saveObjectCategory(ObjectCategory objectCategory);
	
	public void updateObjectCategory(ObjectCategory objectCategory);
	
	public void deleteObjectCategory(ObjectCategory objectCategory);

}
