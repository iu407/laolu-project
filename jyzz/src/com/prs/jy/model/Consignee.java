package com.prs.jy.model;

import java.io.Serializable;
import java.util.Date;

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
 *  收货人
 * 
 * @author laolu
 * 
 */
@Component
@Entity
@Proxy(lazy = false)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "PRS_CONSIGNEES")
public class Consignee implements Serializable{
	private static final long serialVersionUID = 2355758602782663466L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "consigneeid")
	private Integer consigneeid;
	
	private Integer userid;
	
	private String username;
	
	private String consigneename;
	
	private Integer provinceid;
	
	private Integer cityid;
	
	private String street;
	
	private String postcode;
	
	private String tele;
	
	private Date createtime;
	
	@Type(type="true_false")
	private Boolean dft = false;
	
	@Transient
	private Area province;
	@Transient
	private Area city;
	
	
	public Consignee() {
		super();
	}


	public Consignee(Integer consigneeid, Integer userid, String username,
			String consigneename, Integer provinceid, Integer cityid,
			String street, String postcode, String tele, Date createtime,
			Boolean dft, Area province, Area city) {
		super();
		this.consigneeid = consigneeid;
		this.userid = userid;
		this.username = username;
		this.consigneename = consigneename;
		this.provinceid = provinceid;
		this.cityid = cityid;
		this.street = street;
		this.postcode = postcode;
		this.tele = tele;
		this.createtime = createtime;
		this.dft = dft;
		this.province = province;
		this.city = city;
	}


	public Integer getConsigneeid() {
		return consigneeid;
	}


	public void setConsigneeid(Integer consigneeid) {
		this.consigneeid = consigneeid;
	}


	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getConsigneename() {
		return consigneename;
	}


	public void setConsigneename(String consigneename) {
		this.consigneename = consigneename;
	}


	public Integer getProvinceid() {
		return provinceid;
	}


	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}


	public Integer getCityid() {
		return cityid;
	}


	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}


	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public String getPostcode() {
		return postcode;
	}


	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}


	public String getTele() {
		return tele;
	}


	public void setTele(String tele) {
		this.tele = tele;
	}


	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public Boolean getDft() {
		return dft;
	}


	public void setDft(Boolean dft) {
		this.dft = dft;
	}


	public Area getProvince() {
		return province;
	}


	public void setProvince(Area province) {
		this.province = province;
	}


	public Area getCity() {
		return city;
	}


	public void setCity(Area city) {
		this.city = city;
	}


}
