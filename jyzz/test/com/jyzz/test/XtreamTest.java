package com.jyzz.test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.prs.jy.model.FiveEightCity.City;
import com.prs.jy.model.FiveEightCity.CityProduct;
import com.prs.jy.model.FiveEightCity.Product;
import com.prs.jy.model.FiveEightCity.ProductData;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class XtreamTest {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		File file = new File("c:/products.xml");
		String xml = FileUtils.readFileToString(file);
		
		XStream xstream = new XStream(new DomDriver()); 
		xstream.alias("data", ProductData.class);
		//
		xstream.addImplicitCollection(ProductData.class, "citys","city", CityProduct.class);
		xstream.addImplicitCollection(CityProduct.class, "products", "product",Product.class);
		Object cityProducts = xstream.fromXML(xml);
		//定义xml中的属性data对应java ProductData；ProductData类中有属性citys，在xml文件中city属性，对应的java CityProduct
		System.out.println(cityProducts);
	}


}

