package com.prs.jy.controller;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.prs.jy.model.Img;
import com.prs.jy.model.Order;
import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.OrderService;
import com.prs.jy.service.RoleService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.service.UsersService;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;

/**
 * 所有的映射/user
 * 
 * @author laolu
 * 
 */
@Controller
@RequestMapping("/user")
public class UserForm {
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ImgService imgService;
	
	@Autowired
	private OrderService orderService;//订单服务
	
	
	@Autowired
	private User user;
	
	public UserForm(){}

	public UserForm(User user) {
		this.user = user;
	}

	@RequestMapping("/show")
	public String show( Model model) {
		List<User> users = usersService.findUsers();
		for (User user : users) {
			Date ltime = user.getCreatetime();
		}
		model.addAttribute("users", users);
		return "/user/show";
	}
	
	@RequestMapping("/list")
	public String list( Model model,Pager pager) throws Exception {
		ListWithPager<User> userspage = usersService.findUsers(pager);
		model.addAttribute("userspage", userspage);
		return "/user/list";
	}

	
	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping("/new")
	public String addView(){
		return "/user/new";
	}
	/**
	 * 保存user
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	public ModelAndView save(User user){
		user.setCreatetime(new Date());
		ModelAndView mav = new ModelAndView();
		String username = user.getUsername();
		if(usersService.findUser(username) != null && usersService.findUser(username).size() > 0){
			
			mav.addObject("msg", "用户名已经被其他人占用!请选用其他用户名");
			mav.setViewName("/user/list");
		}else{
			Integer userid = usersService.saveUsers(user);
//			user = usersService.findUser(userid);
//			mav.addObject("user", user);
			mav.setViewName("redirect:/user/list");
		
		}
		return mav;
		
	}
	
	@RequestMapping(value="/delete/{userid}",method=RequestMethod.GET )
	public @ResponseBody String delete(@PathVariable("userid") Integer userid){
		usersService.deleteUser(userid);
		return "删除成功"+userid;
	}
	
	/**
	 * 分配角色
	 * @param userid
	 * @return
	 */
	@RequestMapping(value="/torole/{userid}",method=RequestMethod.GET )
	public String toRole(@PathVariable("userid") Integer userid,Model model){
		List<Role> roles = roleService.findAllRole();
		
		User user = usersService.findUser(userid);
		Collection<Role>  rolesAlready = user.getRoles();//已经分配的角色

		roles.removeAll(rolesAlready);//去掉已经分配的角色
		
		
		model.addAttribute("user", user);
		model.addAttribute("userid", userid);
		model.addAttribute("roles", roles);
		return "/user/torole";
	}
	
	@RequestMapping(value="/addrole",method=RequestMethod.POST )
	public String addRole(@RequestParam("userid") Integer userid,@RequestParam("roleid") Integer[] roleid,Model model){
		List<Role> roles = roleService.findRoles(roleid);
		User user = usersService.findUser(userid);
		usersService.addRoles(user, roles);
		
		return "redirect:/user/list";
	}
	
	@RequestMapping(value="/deleterole",method=RequestMethod.POST )
	public String deleteRole(@RequestParam("userid") Integer userid,@RequestParam("roleid") Integer[] roleid,Model model){
		List<Role> roles = roleService.findRoles(roleid);
		User user = usersService.findUser(userid);
		usersService.deleteRoles(user, roles);
		
		return "redirect:/user/list";
	}
	
	/**
	 * 注册用户
	 * @param userid
	 * @param roleid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/reg",method=RequestMethod.POST )
	public String regiest(@RequestParam("userid") Integer userid,@RequestParam("roleid") Integer[] roleid,Model model){
		List<Role> roles = roleService.findRoles(roleid);
		User user = usersService.findUser(userid);
		usersService.deleteRoles(user, roles);
		
		return "redirect:/user/list";
	}
	/**
	 * 注册用户
	 * @param userid
	 * @param roleid
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/register",method=RequestMethod.POST )
	public String registerView(User user,Model model){
		String username = user.getUsername();
		if(usersService.findUser(username) != null && usersService.findUser(username).size() > 0){
			
			model.addAttribute("msg", "用户名已经被其他人占用!请选用其他用户名");
			return "/register";
		}else{
			Integer userid = usersService.saveUsers(user);
			List<Role> roles = roleService.findRoles(new Integer[]{PrsConstants.GENERALUSERID});
			user = usersService.findUser(userid);
			usersService.addRoles(user, roles);	
			model.addAttribute("msg", "注册成功请登录!");
			return "redirect:/login";
		
		}
	}
	
	@RequestMapping(value="/openidlogin",method=RequestMethod.POST )
	public String openidLogin(){
		return null;
	}
	
	
	//我的信息
	@RequestMapping(value="/myinfo",method=RequestMethod.GET )
	public String myinfo(Model model){
		User user = SecurityUserHolder.getCurrentUser();
		user = usersService.findUser(user.getUserid());
		model.addAttribute("user",user);
		List<Img> imgs = imgService.findImgs(PrsConstants.IMGUSER, user.getUserid());
		model.addAttribute("imgs",imgs);
		return "/user/myinfo";
	}

	
	//我的信息
	@RequestMapping(value="/_myinfo",method=RequestMethod.GET )
	public String _myinfo(Model model){
		User user = SecurityUserHolder.getCurrentUser();
		user = usersService.findUser(user.getUserid());
		model.addAttribute("user",user);
		List<Img> imgs = imgService.findImgs(PrsConstants.IMGUSER, user.getUserid());
		model.addAttribute("imgs",imgs);
		return "/user/_myinfo";
	}
	
	//我的空间
	@RequestMapping(value="/myspace",method=RequestMethod.GET )
	public String myspace(Model model){
		User user = SecurityUserHolder.getCurrentUser();
		user = usersService.findUser(user.getUserid());
		model.addAttribute("user",user);
		List<Img> imgs = imgService.findImgs(PrsConstants.IMGUSER, user.getUserid());
		model.addAttribute("imgs",imgs);
		return "/user/myspace";
	}
	
	/**
	 * 保存用户个人信息
	 * @param user
	 * @param imgids
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/savemyinfo",method=RequestMethod.POST )
	public String saveMyinfo(User user,
			HttpServletRequest request,
			Model model){
		usersService.updateUserInfo(user);
		
		String[] imgids = request.getParameterValues("imgid");
		if(imgids != null){//保存图片
			for (String imgid : imgids) {
				Img img = imgService.findImg(new Integer(imgid));
				img.setTargetid(user.getUserid());
				imgService.updateImg(img);
			}
		}
		user = usersService.findUser(user.getUserid());
		model.addAttribute("user",user);
//		return myinfo( model);
		
		return "redirect:/user/myinfo";
	}
	/**
	 * 通过<jsp:include page="/user/usermenu"/>
	 * 可以实现页面有几个部分构成的功能
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/usermenu")
	public String usermenu(Model model){
		User user = SecurityUserHolder.getCurrentUser();
		user = usersService.findUser(user.getUserid());
		model.addAttribute("user",user);
		List<Img> imgs = imgService.findImgs(PrsConstants.IMGUSER, user.getUserid());
		model.addAttribute("imgs",imgs);
		return "/user/usermenu";
	}
	
	/**
	 * 我的订单
	 * @throws Exception 
	 */
	@RequestMapping(value="/myorder")
	public String myOrder(Model model,Pager pager ) throws Exception{
		User user = SecurityUserHolder.getCurrentUser();
		ListWithPager<Order> orderpage = orderService.findOrders(pager,user);
		
		model.addAttribute("orderpage",orderpage);
		return "/user/myorder";
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	

}
