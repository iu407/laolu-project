package com.laolu.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;

import com.laolu.model.fiveeight.City;
import com.laolu.model.fiveeight.CityProduct;
import com.laolu.model.fiveeight.Product;
import com.laolu.model.fiveeight.ProductData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
/**
 * 58同城api
 *
 * @author laolu
 *
 */
@SessionAttributes("productData")
@Controller
@RequestMapping("/fe")
public class FiveEightForm {
	private String citysurl    = "http://t.58.com/api/citys.xml";
	private String productsurl = "http://t.58.com/api/products.xml";
	
	
	@RequestMapping(value="/pdl")
	public String index(ModelMap model) throws IOException{
//		File file = new File("c:/products.xml");
//		String xml = FileUtils.readFileToString(file);
		RestTemplate template = new RestTemplate();//rest调用
		String xml = template.getForObject(productsurl, String.class);//得到字符串后在处理
		XStream xstream = new XStream(new DomDriver()); 
		xstream.alias("data", ProductData.class);
		xstream.addImplicitCollection(ProductData.class, "citys","city", CityProduct.class);
		xstream.addImplicitCollection(CityProduct.class, "products", "product",Product.class);
		ProductData productData = (ProductData)xstream.fromXML(xml);
		
		model.addAttribute("productData", productData);
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
	
	/**
	 * 显示city中的product
	 * @param model
	 * @return
	 */
	@RequestMapping( value="/scp/{id}" ,method = RequestMethod.GET)
	public String showCityProduct(ModelMap model,@PathVariable("id") Integer cityid) {
		model.put("id", cityid);
		return "/fe/showcityproduct";
	}
	/**
	 * 显示商品详细信息
	 * @param model
	 * @return
	 */
	@RequestMapping( value="/sp/{id}" ,method = RequestMethod.GET)
	public String showProduct(ModelMap model,@PathVariable("id") Integer productid) {
		model.put("id", productid);
		return "/fe/showproduct";
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
