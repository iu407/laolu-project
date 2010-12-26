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
import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * 对应基础数据
 * 
 * @author laolu
 * 
 */
@Component
@Scope("globalSession")
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_OPTIONS")
public class Option implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "optionid")
	private Integer optionid;
	
	private String optionname;
	private String optionvalue;

	public Option() {
		super();
	}
	
	public Option(Integer optionid, String optionname, String optionvalue) {
		super();
		this.optionid = optionid;
		this.optionname = optionname;
		this.optionvalue = optionvalue;
	}


	public Integer getOptionid() {
		return optionid;
	}

	public void setOptionid(Integer optionid) {
		this.optionid = optionid;
	}

	public String getOptionname() {
		return optionname;
	}

	public void setOptionname(String optionname) {
		this.optionname = optionname;
	}

	public String getOptionvalue() {
		return optionvalue;
	}

	public void setOptionvalue(String optionvalue) {
		this.optionvalue = optionvalue;
	}



}
