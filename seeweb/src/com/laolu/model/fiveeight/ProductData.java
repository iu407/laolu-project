package com.laolu.model.fiveeight;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProductData implements Serializable{
	private static final long serialVersionUID = -4905625307171431907L;
	private List<CityProduct> citys = new ArrayList<CityProduct>();
	
	
	public ProductData() {
		super();
	}


	public ProductData(List<CityProduct> citys) {
		super();
		this.citys = citys;
	}


	public List<CityProduct> getCitys() {
		return citys;
	}


	public void setCitys(List<CityProduct> citys) {
		this.citys = citys;
	}
	

	
}
