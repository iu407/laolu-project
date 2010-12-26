package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Category;
/**
 * 
 * @author laolu
 *
 */
public interface CategoryService {
	public Category findCategory(Integer categoryid);
	
	public List<Category> findCategorys(Integer[] categoryids);
	
	public List<Category> findAllCategory();
	/**
	 * 上找类型
	 * @param cattype
	 * @return
	 */
	public List<Category> findCategorys(String cattype);
	
	public Integer saveCategory(Category category);
	
	public void updateCategory(Category category);
	
	public void deleteCategory(Category category);

}
