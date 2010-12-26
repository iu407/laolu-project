package com.prs.jy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * 分类
 * 
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_CATS")
public class Category implements Serializable{
	private static final long serialVersionUID = 7523082303712290363L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "categoryid")
	private Integer categoryid;
	
	private String cattype;//类别：例如文章类，商品类
	private String catname;//名称
	private Integer fatherid;
	
	
	public Category() {
		super();
	}


	public Category(Integer categoryid) {
		super();
		this.categoryid = categoryid;
	}


	public Category(Integer categoryid, String cattype, String catname,
			Integer fatherid) {
		super();
		this.categoryid = categoryid;
		this.cattype = cattype;
		this.catname = catname;
		this.fatherid = fatherid;
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


	public String getCatname() {
		return catname;
	}


	public void setCatname(String catname) {
		this.catname = catname;
	}


	public Integer getFatherid() {
		return fatherid;
	}


	public void setFatherid(Integer fatherid) {
		this.fatherid = fatherid;
	}


}
