package com.prs.jy.model.FiveEightCity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class CityProduct implements Serializable{
	private static final long serialVersionUID = 661968542125862071L;
	
	private Integer id;
	private String name;
	private String listname;
	
	private List<Product> products = new ArrayList<Product>();
	
	public CityProduct() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getListname() {
		return listname;
	}

	public void setListname(String listname) {
		this.listname = listname;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
