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
import org.springframework.stereotype.Component;


/**
 * 图片
 * 
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_IMGS")
public class Img implements Serializable{
	private static final long serialVersionUID = 7523082304512290363L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "imgid")
	private Integer imgid;
	
	private Date createtime;
	private Integer targetid;
	
	@Column(name = "targettype")
	private String targetType;
	private String url;
	/*
	 * 默认的图片
	 */
	@Type(type="true_false")
	private Boolean dftimg = false;
	
	public Img() {
		super();
	}


	public Img(Integer imgid, Date createtime, Integer targetid,
			String targetType, String url, Boolean dftimg) {
		super();
		this.imgid = imgid;
		this.createtime = createtime;
		this.targetid = targetid;
		this.targetType = targetType;
		this.url = url;
		this.dftimg = dftimg;
	}


	public Integer getImgid() {
		return imgid;
	}


	public void setImgid(Integer imgid) {
		this.imgid = imgid;
	}


	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public Integer getTargetid() {
		return targetid;
	}


	public void setTargetid(Integer targetid) {
		this.targetid = targetid;
	}


	public String getTargettype() {
		return targetType;
	}


	public void setTargettype(String targetType) {
		this.targetType = targetType;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Boolean getDftimg() {
		return dftimg;
	}


	public void setDftimg(Boolean dftimg) {
		this.dftimg = dftimg;
	}


}
