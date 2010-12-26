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
import org.hibernate.annotations.Type;
import org.springframework.stereotype.Component;


/**
 * 
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_USERMETAOPTION")
public class UserMetaOption implements Serializable{
	private static final long serialVersionUID = -4399047877724710168L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "usermetaoption")
	private Integer userMetaOption;
	
	@Column(name = "usermetaname")
	private String userMetaName;
	
	@Column(name = "usermetavalue")
	private String userMetaValue;
	
	@Type(type="true_false")
	private Boolean disable = false;
	
	public UserMetaOption() {
		super();
	}

	public UserMetaOption(Integer userMetaOption, String userMetaName,
			String userMetaValue, Boolean disable) {
		super();
		this.userMetaOption = userMetaOption;
		this.userMetaName = userMetaName;
		this.userMetaValue = userMetaValue;
		this.disable = disable;
	}

	public Integer getUserMetaOption() {
		return userMetaOption;
	}

	public void setUserMetaOption(Integer userMetaOption) {
		this.userMetaOption = userMetaOption;
	}

	public String getUserMetaName() {
		return userMetaName;
	}

	public void setUserMetaName(String userMetaName) {
		this.userMetaName = userMetaName;
	}

	public String getUserMetaValue() {
		return userMetaValue;
	}

	public void setUserMetaValue(String userMetaValue) {
		this.userMetaValue = userMetaValue;
	}

	public Boolean getDisable() {
		return disable;
	}

	public void setDisable(Boolean disable) {
		this.disable = disable;
	}


}
