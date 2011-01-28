package com.prs.jy.model.douya;

import java.io.Serializable;

public class Author implements Serializable{
	private static final long serialVersionUID = -682855887763896269L;
	private String name;

	public Author() {
		super();
	}

	public Author(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
