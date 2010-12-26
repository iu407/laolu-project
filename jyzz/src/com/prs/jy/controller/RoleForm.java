package com.prs.jy.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;

/**
 * 所有的映射 /role
 * 
 * @author laolu
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleForm {
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private ResourceService resourceService;
	public RoleForm(){}


	@RequestMapping("/list")
	public String show( Model model ) {
		List<Role> roles = roleService.findAllRole();
		model.addAttribute("roles", roles);
		return "/role/list";
	}
	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping("/new")
	public String addView(){
		return "/role/roleform";
	}
	
	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping(value="/edit/{roleid}",method=RequestMethod.GET )
	public String editView(@PathVariable("roleid") Integer roleid,  Model model){
		Role role = roleService.findRole(roleid);
		model.addAttribute("role", role);
		return "/role/editview";
	}	
	/**
	 * 保存user
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Role role){
		role.setUpdatetime(new Date());
		roleService.saveRole(role);
		return "redirect:/role/list";
	}

	@RequestMapping("/update")
	public String update(Role role){
		role.setUpdatetime(new Date());
		roleService.updateRole(role);
		return "/role/list";
	}
	
	
	@RequestMapping(value="/toresource/{roleid}",method=RequestMethod.GET )
	public String toRole(@PathVariable("roleid") Integer roleid,Model model){
		Role role = roleService.findRole(roleid);
		Collection<Resource> resourcesAl = role.getResources();//已经分配的资源
		List<Resource>  resources = resourceService.findResources();
		resources.removeAll(resourcesAl);//没有分配的资源
		
		
		model.addAttribute("role", role);
		model.addAttribute("resources", resources);
		return "/role/toresource";
	}

	@RequestMapping(value="/addresource",method=RequestMethod.POST )
	public String addResource(@RequestParam("roleid") Integer roleid,@RequestParam("resourceid") Integer[] resourceid,Model model){
		Role role = roleService.findRole(roleid);
		List<Resource> resources = resourceService.findResources(resourceid);
		roleService.addResource(role, resources);
		return "redirect:/role/list";
	}
	
	@RequestMapping(value="/deleteresource",method=RequestMethod.POST )
	public String deleteResource(@RequestParam("roleid") Integer roleid,@RequestParam("resourceid") Integer[] resourceid,Model model){
		Role role = roleService.findRole(roleid);
		List<Resource> resources = resourceService.findResources(resourceid);
		roleService.deleteResource(role, resources);
		
		return "redirect:/role/list";
//		return "redirect:/role/toresource/"+role.getRoleid()+"";
	}
	
	
	
}
