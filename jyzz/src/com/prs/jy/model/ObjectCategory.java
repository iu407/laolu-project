package com.prs.jy.model;

import java.io.Serializable;
import java.util.Date;

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
 * 对象分类
 * targetid对应的是那个分类
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_OBJECTCATS")
public class ObjectCategory implements Serializable{
	private static final long serialVersionUID = 7523082312212290363L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "objectid" )
	private Integer objectid;
	
	private Integer targetid;
	private Integer categoryid;
	private String cattype;
	
	
	public ObjectCategory() {
		super();
	}


	public ObjectCategory(Integer objectid) {
		super();
		this.objectid = objectid;
	}


	public ObjectCategory(Integer objectid, Integer targetid,
			Integer categoryid, String cattype) {
		super();
		this.objectid = objectid;
		this.targetid = targetid;
		this.categoryid = categoryid;
		this.cattype = cattype;
	}


	public Integer getObjectid() {
		return objectid;
	}


	public void setObjectid(Integer objectid) {
		this.objectid = objectid;
	}


	public Integer getTargetid() {
		return targetid;
	}


	public void setTargetid(Integer targetid) {
		this.targetid = targetid;
	}


	public Integer getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}


	public String getCattype() {
		return cattype;
	}


	public void setCattype(String cattype) {
		this.cattype = cattype;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ObjectCategory) {
			ObjectCategory objectCategory = (ObjectCategory) obj;
			if(        this.categoryid.equals(objectCategory.getCategoryid())
					&& this.targetid.equals(objectCategory.getTargetid()) 
					&& this.cattype.equals(objectCategory.getCattype())){
				return true;
			}
		}
		return super.equals(obj);
	}
	
}
