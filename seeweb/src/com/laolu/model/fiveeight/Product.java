package com.laolu.model.fiveeight;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = 869162897351471416L;
	private Integer id;
	private String url;
	private String name;
	private String short_name;
	private String vendor_name;
	private String vendor_website_url;
	private String vendor_address;
	private String vendor_map_address;
	private String vendor_bus_line;
	private String vendor_phone;
	private String market_price;
	private String group_price;
	private String discount;
	private String quantity_sold;
	private String begin_date;
	private String end_date;
	private String expired_date;
	private String image;
	private String small_image;
	private String introduction;
	private String comment;
	private String region;
	
	
	
	public Product() {
		super();
	}
	public Product(Integer id, String url, String name, String short_name,
			String vendor_name, String vendor_website_url,
			String vendor_address, String vendor_map_address,
			String vendor_bus_line, String vendor_phone, String market_price,
			String group_price, String discount, String quantity_sold,
			String begin_date, String end_date, String expired_date,
			String image, String small_image, String introduction,
			String comment, String region) {
		super();
		this.id = id;
		this.url = url;
		this.name = name;
		this.short_name = short_name;
		this.vendor_name = vendor_name;
		this.vendor_website_url = vendor_website_url;
		this.vendor_address = vendor_address;
		this.vendor_map_address = vendor_map_address;
		this.vendor_bus_line = vendor_bus_line;
		this.vendor_phone = vendor_phone;
		this.market_price = market_price;
		this.group_price = group_price;
		this.discount = discount;
		this.quantity_sold = quantity_sold;
		this.begin_date = begin_date;
		this.end_date = end_date;
		this.expired_date = expired_date;
		this.image = image;
		this.small_image = small_image;
		this.introduction = introduction;
		this.comment = comment;
		this.region = region;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShort_name() {
		return short_name;
	}
	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}
	public String getVendor_name() {
		return vendor_name;
	}
	public void setVendor_name(String vendor_name) {
		this.vendor_name = vendor_name;
	}
	public String getVendor_website_url() {
		return vendor_website_url;
	}
	public void setVendor_website_url(String vendor_website_url) {
		this.vendor_website_url = vendor_website_url;
	}
	public String getVendor_address() {
		return vendor_address;
	}
	public void setVendor_address(String vendor_address) {
		this.vendor_address = vendor_address;
	}
	public String getVendor_map_address() {
		return vendor_map_address;
	}
	public void setVendor_map_address(String vendor_map_address) {
		this.vendor_map_address = vendor_map_address;
	}
	public String getVendor_bus_line() {
		return vendor_bus_line;
	}
	public void setVendor_bus_line(String vendor_bus_line) {
		this.vendor_bus_line = vendor_bus_line;
	}
	public String getVendor_phone() {
		return vendor_phone;
	}
	public void setVendor_phone(String vendor_phone) {
		this.vendor_phone = vendor_phone;
	}
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public String getGroup_price() {
		return group_price;
	}
	public void setGroup_price(String group_price) {
		this.group_price = group_price;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getQuantity_sold() {
		return quantity_sold;
	}
	public void setQuantity_sold(String quantity_sold) {
		this.quantity_sold = quantity_sold;
	}
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(String expired_date) {
		this.expired_date = expired_date;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSmall_image() {
		return small_image;
	}
	public void setSmall_image(String small_image) {
		this.small_image = small_image;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	
	
}
