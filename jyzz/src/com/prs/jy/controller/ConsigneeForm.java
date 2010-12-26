package com.prs.jy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prs.jy.model.Area;
import com.prs.jy.model.Consignee;
import com.prs.jy.model.Img;
import com.prs.jy.model.User;
import com.prs.jy.service.AreaService;
import com.prs.jy.service.ConsigneeService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.service.UsersService;

/**
 * 收货人
 * 
 * @author laolu
 * 
 */
@Controller
@RequestMapping("/consignee")
public class ConsigneeForm {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private ConsigneeService consigneeService;
	
	@Autowired
	private AreaService areaService;
	
	public ConsigneeForm(){}


	@RequestMapping("/show")
	public String show( Model model) {
		List<User> users = usersService.findUsers();
		for (User user : users) {
			Date ltime = user.getCreatetime();
		}
		model.addAttribute("users", users);
		return "/user/show";
	}
	
	@RequestMapping("/list")
	public String list( Model model) {
//		User user = SecurityUserHolder.getCurrentUser();
//		List<Consignee>  consignees = consigneeService.findConsignee(user);
//		List<Area>  provinces = areaService.findAreasProvince();
//		List<Area>  citys = areaService.findAreasCity();
//		model.addAttribute("consignees", consignees);
//		model.addAttribute("provinces", provinces);
//		model.addAttribute("citys", citys);
		return "/consignee/list";
	}
	
	@RequestMapping("/consigneelist")
	public String consigneelist( Model model) {
		User user = SecurityUserHolder.getCurrentUser();
		List<Consignee>  consignees = consigneeService.findConsignee(user);
		List<Area>  provinces = areaService.findAreasProvince();
		List<Area>  citys = areaService.findAreasCity();
		model.addAttribute("consignees", consignees);
		model.addAttribute("provinces", provinces);
		model.addAttribute("citys", citys);
		return "/consignee/consigneelist";
	}
	
	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping("/new")
	public String addView(){
		return "/user/new";
	}
	/**
	 * 保存
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST )
	public String save(Consignee consignee, Model model){
		System.out.println(consignee);//保存收货人
		
		User user = SecurityUserHolder.getCurrentUser();
		consignee.setUserid(user.getUserid());
		consignee.setUsername(user.getUsername());
		Integer consigneeid =  consigneeService.saveConsignee(consignee);
		consignee = consigneeService.findConsignee(consigneeid);
		
		model.addAttribute("flag", 0);
		model.addAttribute("consignee", consignee);
		return "/consignee/consigneetip";
		
	}
	
	@RequestMapping(value="/delete/{consigneeid}" )
	public void  delete( @PathVariable("consigneeid") Integer consigneeid ) {
		consigneeService.deleteConsignee(consigneeService.findConsignee(consigneeid));
	}
	
	@RequestMapping(value="/dft/{consigneeid}" )
	public void  setDefault( @PathVariable("consigneeid") Integer consigneeid ) {
		consigneeService.updateToDefault(consigneeService.findConsignee(consigneeid));
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	

}
