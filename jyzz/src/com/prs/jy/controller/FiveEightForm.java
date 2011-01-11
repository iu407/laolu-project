package com.prs.jy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.prs.jy.model.FiveEightCity.City;
import com.prs.jy.utils.Pager;
/**
 * 58同城调用controller
 *
 * @author laolu
 *
 */
@Controller
@RequestMapping("/fe")
public class FiveEightForm {
	private String citysurl    = "http://t.58.com/api/citys.xml";
	private String productsurl = "http://t.58.com/api/products.xmll";
	
	
	@RequestMapping(value="/s/{goodsid}" ,method = RequestMethod.GET)
	public String index(Model model,@PathVariable("goodsid") Integer goodsid){//购物车
		return "/android/show";
	}
	
	@RequestMapping("/l")
	public String list( Model model,Pager pager) throws Exception {
		RestTemplate template = new RestTemplate();//rest调用
		Map<String, City> params =  new HashMap<String, City>();
		City city = template.getForObject(citysurl, City.class);
		
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
	public String showImg( Model model) throws Exception {
		return "/android/showimg";
	}
	
}
