package com.prs.jy.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Proxy;
import org.hibernate.annotations.Type;
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
@Table(name = "PRS_GOODS")
public class Goods implements Serializable{
	private static final long serialVersionUID = 7523082303212290363L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "goodsid")
	private Integer goodsid;
	
	private String goodsname;
	private String detail;
	
	@Type(type="true_false")
	private Boolean issale = false;
	private Date createtime;
	private String sn;
	private BigDecimal price;
	
	private Integer packageid;
	private Integer publishid;
	private Integer typeid;
	
	private String jyid;
	
	private Integer authorid;
	private String authorname;
	
	private Integer categoryid;
	
	
	@Transient 
	private List<Img> imgs;
	
	@Transient 
	private Category category;
	

	public Goods() {
		super();
	}


	public Goods(Integer goodsid) {
		super();
		this.goodsid = goodsid;
	}

	public Goods(Integer goodsid, String goodsname, String detail,
			Boolean issale, Date createtime, String sn, BigDecimal price,
			Integer packageid, Integer publishid, Integer typeid, String jyid,
			Integer authorid, String authorname, Integer categoryid) {
		super();
		this.goodsid = goodsid;
		this.goodsname = goodsname;
		this.detail = detail;
		this.issale = issale;
		this.createtime = createtime;
		this.sn = sn;
		this.price = price;
		this.packageid = packageid;
		this.publishid = publishid;
		this.typeid = typeid;
		this.jyid = jyid;
		this.authorid = authorid;
		this.authorname = authorname;
		this.categoryid = categoryid;
	}


	public Integer getGoodsid() {
		return goodsid;
	}


	public void setGoodsid(Integer goodsid) {
		this.goodsid = goodsid;
	}


	public String getGoodsname() {
		return goodsname;
	}


	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}


	public String getDetail() {
		return detail;
	}


	public void setDetail(String detail) {
		this.detail = detail;
	}


	/**
	 * 是否销售
	 * @return
	 */
//	public Boolean isIssale() {
//		return issale;
//	}



	public Boolean getIssale() {
		return issale;
	}


	public void setIssale(Boolean issale) {
		this.issale = issale;
	}


	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public String getSn() {
		return sn;
	}


	public void setSn(String sn) {
		this.sn = sn;
	}


	public BigDecimal getPrice() {
		return price;
	}


	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public Integer getPackageid() {
		return packageid;
	}


	public void setPackageid(Integer packageid) {
		this.packageid = packageid;
	}


	public Integer getPublishid() {
		return publishid;
	}


	public void setPublishid(Integer publishid) {
		this.publishid = publishid;
	}


	public Integer getTypeid() {
		return typeid;
	}


	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}


	public String getJyid() {
		return jyid;
	}


	public void setJyid(String jyid) {
		this.jyid = jyid;
	}


	public Integer getAuthorid() {
		return authorid;
	}


	public void setAuthorid(Integer authorid) {
		this.authorid = authorid;
	}


	public String getAuthorname() {
		return authorname;
	}


	public void setAuthorname(String authorname) {
		this.authorname = authorname;
	}


	public List<Img> getImgs() {
		return imgs;
	}


	public void setImgs(List<Img> imgs) {
		this.imgs = imgs;
	}


	public Integer getCategoryid() {
		return categoryid;
	}


	public void setCategoryid(Integer categoryid) {
		this.categoryid = categoryid;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

}
