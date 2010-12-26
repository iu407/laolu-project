package com.prs.jy.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.prs.jy.model.Cart;
import com.prs.jy.model.CartItem;
import com.prs.jy.model.Dispatch;
import com.prs.jy.model.Order;
import com.prs.jy.model.User;
import com.prs.jy.service.CartService;
import com.prs.jy.service.DispatchService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.OrderItemService;
import com.prs.jy.service.OrderService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.utils.DispatchUtils;
import com.prs.jy.utils.PrsConstants;
/**
 * 订单
 * @author laolu
 *
 */
@Controller
@RequestMapping("/order")
@SessionAttributes("cart")
public class OrderForm {
	
	@Autowired
	private GoodsService goodsService;
	
	@Autowired
	private CartService  cartService;
	
	@Autowired
	private OrderService  orderService;
	@Autowired
	private DispatchService  dispatchService;
	
	@Autowired
	private DispatchUtils  dispatchUtils;
	/**
	 * 购物车后->结算
	 * 购物车中的物品结算
	 * @return
	 * 
	 */
	//@RequestMapping(value="/check", method = RequestMethod.POST )
	@RequestMapping(value="/check", method = RequestMethod.GET )
	public String addToCart(@ModelAttribute Cart cart,
			Model model,
			HttpServletRequest request ){
		
		HttpSession httpSession = request.getSession(true);//得到httpsession
		Object objectCart = httpSession.getAttribute(PrsConstants.CART_SESSION);
		if(objectCart == null ){//如何session中有购物车，说明这个购物车是性建立的购物车。
			return "redirect:/cart/mycart";
		}
		
		User user = SecurityUserHolder.getCurrentUser();
		
		
		//1:订单出入数据库，2：清空购物车
		cart.setUserid(user.getUserid());
		cart.setUsername(user.getUsername());
		
		Integer cartid = cartService.saveCart(cart);//购物车历史
		
		List<CartItem>  cartItems = cart.getItems();
		
		Dispatch dispatch = dispatchUtils.buildDispatch(cartItems);
		cart.setDispatch(dispatch);
		
		model.addAttribute("cart", cart);
		model.addAttribute("dispatch", dispatch);

		
		return "/order/check";
	}
	
	/**
	 * 提交订单
	 * consigneeid：收货人
	 * @return
	 */
	@RequestMapping(value="/submit/{consigneeid}" )
	public String submitOrder(@ModelAttribute Cart cart,
			@PathVariable("consigneeid") Integer consigneeid,
			Model model,
			HttpServletRequest request){
		Order order = orderService.saveOrder(cart,consigneeid);//保存订单的的同时保存配送信息
		
		HttpSession httpSession = request.getSession(true);//得到httpsession
		Object objectCart = httpSession.getAttribute(PrsConstants.CART_SESSION);
		if(objectCart != null ){//如何session中有购物车，说明这个购物车是性建立的购物车。
			cart.clearCart();
			cartService.deleteCart(cart);//三处购物车
			httpSession.setAttribute(PrsConstants.CART_SESSION,null);
		}
		
		
		model.addAttribute("order", order);//购物车中的物品转换成订单
		
		return "redirect:/order/show/"+order.getOrderid()+"";//返回到我的订单，注意这时候的订单状态
	}
	
	@RequestMapping(value="/show/{orderid}", method = RequestMethod.GET )
	public String showorder(@PathVariable("orderid") Integer orderid,
			Model model){
		Order order = orderService.findOrder(orderid);
		Dispatch dispatch = dispatchService.findDispatch(order);
		
		model.addAttribute("order", order);
		model.addAttribute("dispatch", dispatch);
		return "/order/show";
	}
	
}
