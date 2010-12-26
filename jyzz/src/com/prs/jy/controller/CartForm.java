package com.prs.jy.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.prs.jy.model.Cart;
import com.prs.jy.model.CartItem;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.model.User;
import com.prs.jy.service.CartItemService;
import com.prs.jy.service.CartService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.utils.PrsConstants;

@Controller
@RequestMapping("/cart")
//@Scope("session")
@SessionAttributes("cart")
public class CartForm {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private ImgService imgService;
	
	
	
	//http://localhost:8080/jyzz/cart/addtocart/1
	@RequestMapping(value="/addtocart/{goodsid}", method = RequestMethod.GET )
	public String addToCart(@PathVariable("goodsid") Integer goodsid,
			Model model,
			HttpServletRequest request ){//购物车
		User user = SecurityUserHolder.getCurrentUser();
		Cart cart = null;
		
		HttpSession httpSession = request.getSession(true);//得到httpsession
		Object objectCart = httpSession.getAttribute(PrsConstants.CART_SESSION);
		if(objectCart == null ){
			cart = new Cart();
			httpSession.setAttribute(PrsConstants.CART_SESSION, cart );
		}else{
			if (objectCart instanceof Cart) {
				cart = (Cart) objectCart;
			}
		}
		Goods goods = goodsService.findGoods(goodsid);
		cart.addItem(goods);//在购物车中增加商品
		model.addAttribute("cart", cart);
		return "redirect:/cart/mycart";
	}
	
	//我的购物车，判断用户是否登录如果登录显示自己的购物车，如果没有登录显示当前session的用户
	@RequestMapping(value="/mycart" )
	public String myCart(Model model,
			HttpServletRequest request ){//购物车
//		User user = SecurityUserHolder.getCurrentUser();
		Cart cart = null;
		HttpSession httpSession = request.getSession(true);//得到httpsession
		Object objectCart = httpSession.getAttribute(PrsConstants.CART_SESSION);
		if(objectCart == null ){
			cart = new Cart();
			httpSession.setAttribute(PrsConstants.CART_SESSION, cart );
		}else{
			if (objectCart instanceof Cart) {
				cart = (Cart) objectCart;
				//处理商品
				List<CartItem> items = cart.getItems();
				for (CartItem cartItem : items) {
					Goods goods = cartItem.getGoods();
					List<Img> imgs = imgService.findImgs(PrsConstants.IMGGOODS, goods.getGoodsid());
					goods.setImgs(imgs);
				}
			}
		}
		model.addAttribute("cart", cart);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,2);
		Date peisongTime = cal.getTime();
		model.addAttribute("peisongTime", peisongTime);
		
		return "/cart/mycart";
	}
	
	@RequestMapping(value="/delete/{goodsid}", method = RequestMethod.GET )
	public String deleteGoodsFormCart(
			@PathVariable("goodsid") Integer goodsid,
			Model model,
			HttpServletRequest request ){
		HttpSession httpSession = request.getSession(true);//得到httpsession
		Object objectCart = httpSession.getAttribute(PrsConstants.CART_SESSION);
		Cart cart = null;
//		cartItemService.deleteCartItem(cartItemService.findCartItem(cartitemid));
		Goods goods = goodsService.findGoods(goodsid);
		
		if (objectCart instanceof Cart) {
			cart = (Cart) objectCart;
			cart.deleteItem(goods);
		}
		model.addAttribute("cart", cart);
		model.addAttribute("flag", "0");
		return "/cart/tip";
	}
}
