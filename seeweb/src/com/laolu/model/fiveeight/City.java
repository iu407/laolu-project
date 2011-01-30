package com.laolu.model.fiveeight;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = -4905625307171431907L;
	
	private Integer id;
	private String name;
	private String enname;
	
	public City() {
		super();
	}
	public City(Integer id, String name, String enname) {
		super();
		this.id = id;
		this.name = name;
		this.enname = enname;
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
	public String getEnname() {
		return enname;
	}
	public void setEnname(String enname) {
		this.enname = enname;
	}

}
