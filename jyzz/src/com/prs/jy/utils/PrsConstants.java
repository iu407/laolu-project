package com.prs.jy.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

import com.prs.jy.model.Cart;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Option;


/**
 * PRS项目中用的常数
 * @author laolu
 *
 */
public class PrsConstants {
	public static String CART_SESSION = "CART_SESSION";//0代表商品
	
//	public static String CTX = "http://localhost:8080";
	/**
	 * UTF-16
	 */
	public static String UTF16 = "UTF-16";//0代表商品
	
	public static String OPTIONNAME_SITEURL = "siteurl";
	
	public static String SITEURL = "http://localhost:8080";
	
	public static Integer ADD_MONTH_NUM = 2;
	
	private static List<Option> options = new ArrayList<Option>();
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddSSSS");
	/**
	 * 默认增加的月份是2个月
	 */
	
	/**
	 * 商品类
	 * GOODSCATEGORY = "0"
	 */
	public static String GOODSCATEGORY = "0";//0代表商品
	public static String ARTICALCATEGORY = "1";//1代表文章
	/**
	 * 商品图片类型
	 */
	public static String IMGGOODS = "goods";
	/**
	 * 用户图片类型
	 */
	public static String IMGUSER = "user";
	
	public static String GOODSTYPE = "0";
	
	public static String USERTYPE = "1";
	
	/**
	 * 普通用户的id
	 */
	public static Integer GENERALUSERID = 5;
	
	public static Integer DEFALUT_MONTHNUM = 6;
	/**
	 * 接收方的email地址
	 */
	public static String MAIL_RECEIVE = "mail_receive" ;
	/**
	 * 邮件主题
	 */
	public static String MAIL_SUBJECT = "mail_subject" ;
	/**
	 * 发送方地址
	 */
	public static String MAIL_SEND = "mail_subject" ;
	
	/**
	 * 图片类型
	 */
	public static Map<String,String> IMGTYPEMAP = new HashMap<String,String>();
	static {
		IMGTYPEMAP.put(GOODSTYPE, IMGGOODS);
		IMGTYPEMAP.put(USERTYPE, IMGUSER);
	}
	
	
	
	
	public static List<Option> getOptions() {
		return PrsConstants.options;
	}
	public static void setOptions(List<Option> options) {
		PrsConstants.options = options;
	}
	public static String getSITEURL() {
		return SITEURL;
	}
	public static void setSITEURL(String siteurl) {
		SITEURL = siteurl;
	}
	
	
	
	
	
	
	
	
	
	public static String getGoodsSn(Goods goods) {
		String d = sdf.format(new Date())+ "" +goods.getGoodsid();
		return d;
	}
	
	public static String getOrderSn(Cart cart) {
		String d = sdf.format(new Date())+ "" +cart.getCartid();
		return d;
	}
	
	/**
	 * 	订单状态		用户显示	管理员显示
	0	用户建立订单，还没有结算。	在我的订单中显示，提交订单。	等待用户提交
	1	用户提交了订单，等待发货	等待发货	
	2	已经发货		
	3	等待付款		
	4	已经付款		
	5	收到货款		
	6	完成		
	7	继续发货		
	 */
	/**
	 * 0:用户建立订单，还没有结算。
	 */
	public static String ORDER_PRE = "0";//
	/**
	 * 1:用户提交了订单，等待发货	等待发货
	 */
	public static String ORDER_SUB = "1";//
	/**
	 * 2:已经发货	allready send goods
	 */
	public static String ORDER_ASD = "2";//
	/**
	 * 3:等待付款   wait for paid
	 */
	public static String ORDER_WPD = "3";//等待付款   wait for paid
	/**
	 * 4:已经付款	   allready for paid
	 */
	public static String ORDER_APD = "4";//已经付款   allready for paid
	/**
	 * 5:收到货款	   recieve for paid
	 */
	public static String ORDER_RPD = "5";//已经付款   recieve for paid
	/**
	 * 6:收到货款   recieve for paid
	 */
	public static String ORDER_CMP = "6";//完成	   complete
	/**
	 * 7:继续发货   recieve for paid
	 */
	public static String ORDER_CTN = "7";//继续发货   recieve for paid
	
}
