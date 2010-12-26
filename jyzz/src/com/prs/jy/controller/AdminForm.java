package com.prs.jy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.prs.jy.model.Cart;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;
/**
 * 后台管理
 *
 * @author laolu
 *
 */
@Controller
@RequestMapping("/adm")
public class AdminForm {
	
	@Autowired
	private GoodsService goodsService;//处理杂志（杂志作为商品处理）
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ImgService imgService;
	
	
	@RequestMapping(value="/admin")
	public String index(Model model){//购物车
		//这里增加应用
		return "/admin/admin";
	}
	
	/**
	 * list mgz
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String listMgz( Model model,Pager pager) throws Exception {
		ListWithPager<Goods>  goodsPage = goodsService.findGoods(pager);
		
//		model.addAttribute("goodses", goodses);
		model.addAttribute("goodsPage", goodsPage);
		return "/admin/mgzlist";
	}
	
}
