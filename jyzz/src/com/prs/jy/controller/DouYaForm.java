package com.prs.jy.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.prs.jy.converter.CityProductConverter;
import com.prs.jy.model.FiveEightCity.City;
import com.prs.jy.model.FiveEightCity.CityProduct;
import com.prs.jy.model.FiveEightCity.Product;
import com.prs.jy.model.FiveEightCity.ProductData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * 豆芽api
 *
 * @author laolu
 *
 */
@SessionAttributes("productData")
@Controller
@RequestMapping("/dy")
public class DouYaForm {
	@Value("0d2228864d9c8e5227667992e8d5aaf6")
	private String api_key ;
	
	@Value("http://t.58.com/api/citys.xml")
	private String citysurl  ;
	
	@Value("http://t.58.com/api/products.xml")
	private String productsurl ;
	
	
	@RequestMapping(value="/t")
	public String index(ModelMap model) throws IOException{//购物车
		RestTemplate template = new RestTemplate();//rest调用
		String xml = template.getForObject("http://api.douban.com/book/subject/1220562?apikey="+api_key+"", String.class);//得到字符串后在处理
		System.out.println(xml);
		XStream xstream = new XStream(new DomDriver()); 
		
		
		return "/fe/prdlist";
	}
	
	@RequestMapping("/l")
	public String list( Model model) throws Exception {
		RestTemplate template = new RestTemplate();//rest调用
//		Map<String, City> params =  new HashMap<String, City>();
		String xml = template.getForObject(citysurl, String.class);//得到字符串后在处理
		XStream xstream = new XStream(new DomDriver()); 
		xstream.alias("citys", List.class); 
		xstream.alias("city",  City.class); 
		List<City> cityList = (List<City>)xstream.fromXML(xml);
		
		model.addAttribute("citys", cityList);
		
		return "/fe/citylist";
	}
}
