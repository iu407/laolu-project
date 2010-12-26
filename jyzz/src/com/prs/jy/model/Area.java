package com.prs.jy.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;
/**
 * 商品
 * 
 * @author laolu
 * 
 */

@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_AREA")
public class Area implements Serializable{
	private static final long serialVersionUID = -6528334151259247922L;
	
	public static String AREA_COUNTRY  = "AREA_COUNTRY";
	public static String AREA_PROVINCE = "AREA_PROVINCE";
	public static String AREA_CITY     = "AREA_CITY";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "areaid")
	private Integer areaid;
	
	private String stype;
	private String name;
	private Integer fatherid;
	

	public Area() {
		super();
	}


	public Area(Integer areaid, String stype, String name, Integer fatherid) {
		super();
		this.areaid = areaid;
		this.stype = stype;
		this.name = name;
		this.fatherid = fatherid;
	}


	public Integer getAreaid() {
		return areaid;
	}


	public void setAreaid(Integer areaid) {
		this.areaid = areaid;
	}


	public String getStype() {
		return stype;
	}


	public void setStype(String stype) {
		this.stype = stype;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getFatherid() {
		return fatherid;
	}


	public void setFatherid(Integer fatherid) {
		this.fatherid = fatherid;
	}

}
