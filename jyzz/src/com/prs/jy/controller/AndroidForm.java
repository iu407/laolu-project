package com.prs.jy.controller;

import java.util.Calendar;
import java.util.Date;
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
import com.prs.jy.service.ObjectCategoryService;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;
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
@RequestMapping("/adr")
public class AndroidForm {

	@Autowired
	private GoodsService goodsService;//处理杂志（杂志作为商品处理）
	
	
	@RequestMapping(value="/s/{goodsid}" ,method = RequestMethod.GET)
	public String index(Model model,@PathVariable("goodsid") Integer goodsid){//购物车
		//这里增加应用
		Goods goods = goodsService.findGoods(goodsid);
		model.addAttribute("goods", goods);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,2);
		Date peisongTime = cal.getTime();
		model.addAttribute("peisongTime", peisongTime);
		return "/android/show";
	}
	
	@RequestMapping("/l")
	public String list( Model model,Pager pager) throws Exception {
		ListWithPager<Goods>  goodsPage = goodsService.findGoods(pager);
		model.addAttribute("goodsPage", goodsPage);
		//配送时间
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,2);
		Date peisongTime = cal.getTime();
		model.addAttribute("peisongTime", peisongTime);
		return "/android/list";
	}
	/**
	 * 显示照片
	 * @param model
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/img")
	public String showImg( Model model,Pager pager) throws Exception {
		return "/android/showimg";
	}
	
}
