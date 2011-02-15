package com.prs.jy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prs.jy.model.Category;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.model.Pojo;
import com.prs.jy.model.Resource;
import com.prs.jy.model.User;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.ObjectCategoryService;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;
import com.prs.jy.utils.PrsFileUtils;
/**
 * 后台管理
 *
 * @author laolu
 *
 */
@Controller
@RequestMapping("/lling")
public class LinklingForm {
	/*
	 * 返回页面前缀
	 */
	@Value(value="/linking/")
	private String PRE;
	
	
	@RequestMapping(value="/llist")
	public String llist(Model model){//购物车
		//这里增加应用
		return "/linklnig/index";
	}
	/**
	 * 返回页面;如果page是index，就返回index.jsp页面
	 * @param page
	 * @return
	 */
	private String getReStr(String page){
		return PRE+page;
	}
	
	@Autowired
	private GoodsService goodsService;//处理杂志（杂志作为商品处理）
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ObjectCategoryService objectCategoryService;
	
	@Autowired
	private ImgService imgService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;
	
	
	@RequestMapping("/list")
	public String list( Model model,Pager pager) throws Exception {
		return "/linkling/index";
	}

	
}
