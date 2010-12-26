package com.prs.jy.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Category;
import com.prs.jy.model.User;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.CategoryService;

/**
 * 分类 /catg
 * 
 * @author laolu
 * 
 */
@Controller
@RequestMapping("/category")
public class CategoryForm {
	@Autowired
	private CategoryService categoryService;
	public CategoryForm(){}


	@RequestMapping(value={"/list"})
	public String show( Model model ) {
		List<Category> categorys = categoryService.findAllCategory();
		model.addAttribute("categorys", categorys);
		return "/category/list";
	}
	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping("/new")
	public String addView(Model model ){
		List<Category> categorys = categoryService.findAllCategory();
		model.addAttribute("categorys", categorys);
		return "/category/new";
	}
	
	@RequestMapping(value="/edit/{categoryid}",method=RequestMethod.GET )
	public String editView(@PathVariable("categoryid") Integer Categoryid,  Model model){
		Category category = categoryService.findCategory(Categoryid);
		model.addAttribute("category", category);
		return "/category/edit";
	}	
	/**
	 * 保存user
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Category category){
		categoryService.saveCategory(category);
		return "redirect:/category/list";
	}

	@RequestMapping("/update")
	public String update(Category Category){
		categoryService.updateCategory(Category);
		return "redirect:/category/list";
	}
	
	@RequestMapping(value="/delete/{categoryid}",method=RequestMethod.GET )
	public String delete(@PathVariable("categoryid") Integer Categoryid,  Model model){
		Category category = categoryService.findCategory(Categoryid);
		categoryService.deleteCategory(category);
		return "redirect:/category/list";
	}

	/**
	 * 列出商品类型
	 * @param cattype
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listcat/{cattype}",method=RequestMethod.GET )
	public String listCat(@PathVariable("String cattype") String cattype,  Model model){
		List<Category> categorys = categoryService.findCategorys(cattype);
		model.addAttribute("categorys", categorys);
		return "/category/_listcat";
	}
	
	
}
