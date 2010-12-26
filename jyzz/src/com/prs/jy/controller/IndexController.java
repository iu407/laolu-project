package com.prs.jy.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.FreemarkerEmailService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.ObjectCategoryService;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.UsersService;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;

import freemarker.template.TemplateException;

@Controller
public class IndexController {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private MagazineForm magazineForm;
	

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
	
	
//	//购物车
//	@Autowired
//	private Cart cart;
	
	
	
	
	@RequestMapping(value="/" )
	public String test(Model model){
//		System.out.println("cart hashcode is : " + cart.hashCode());//判断是否一个对象
//		model.addAttribute("msg", new Date());
		return "/index";
	}
	
	@RequestMapping("/commongoods")
	public String commongoods(Model model){
		System.out.println(model);
//		model.addAttribute("msg", new Date());
		return "/common/commongoods";
	}
	
	@RequestMapping("/error")
	public String error(Model model){
		return "/error";
	}
	
	
	/**
	 * 常用服务
	 * 在这里增加常用服务
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/commonservice" )
	public String commonService(Model model){
		return "/common/commonservice";
	}	
	
	
	/**
	 * 常用服务
	 * 在这里增加常用服务
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/commonservice1" )
	public String commonService1(Model model){
		return "/common/commonservice";
	}	
	/**
	 * 万能搜索
	 * @param request
	 * @param pager
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/search" )
	public String commonService1(HttpServletRequest request,Pager pager,Model model) throws Exception{
		String str = request.getParameter("s");
		Map<String,String> demand = new HashMap<String,String>();
		demand.put("goodsname", str);
		demand.put("jyid", str);
		pager.setDemand(demand);
		model.addAttribute("s",str);
		
		ListWithPager<Goods>  goodsPage = goodsService.findGoodsIndex(pager);
		
		model.addAttribute("goodsPage", goodsPage);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,2);
		Date peisongTime = cal.getTime();
		model.addAttribute("peisongTime", peisongTime);
		return "/mgz/list";
	}	
	/**
	 * 导入csv文件
	 * @param request
	 * @param pager
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/importprdcsv" )
	public String importPrdCSV() throws Exception{
		return "/admin/csvview";
	}
	/**
	 * 测试发送邮件
	 * @return
	 * @throws Exception
	 */
	

	@Autowired
	private FreemarkerEmailService freemarkerEmailService;
	
	@RequestMapping(value="/testmail" )
	public String testMail(Model model) throws Exception{
		Map<String, String> emailModel = new HashMap<String, String>();
		emailModel.put(PrsConstants.MAIL_RECEIVE, "manneting@gmail.com");
		emailModel.put(PrsConstants.MAIL_SUBJECT, "杂志吧！测试邮件！");
		emailModel.put(PrsConstants.MAIL_SEND, "manneting@163.com");
//		freemarkerEmailService.sendMail("mail.ftl", emailModel,new HashMap());
		model.addAttribute("message", "邮件发送成功");
		model.addAttribute("emailModel", emailModel);
		return "/testmail";
	}
	


}
