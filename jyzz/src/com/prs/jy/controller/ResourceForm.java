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
import org.springframework.web.bind.annotation.ResponseBody;

import com.prs.jy.model.Resource;
import com.prs.jy.model.Role;
import com.prs.jy.model.User;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;

/**
 * 所有的映射/resource
 * 
 * @author laolu
 * 
 */
@Controller
@RequestMapping("/resource")
public class ResourceForm {
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;
	
	
	public ResourceForm(){}

	@RequestMapping("/show")
	public String show() {
//		List<User> users = usersService.findUsers();
//		model.addAttribute("users", users);
		return "/resource/show";
	}
	
	@RequestMapping("/list")
	public String list( Model model) {
		List<Resource> resources = resourceService.findResources();
		model.addAttribute("resources", resources);
		return "/resource/list";
	}
	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping("/new")
	public String addView(){
		return "/resource/new";
	}
	/**
	 * 保存user
	 * @param user
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Resource resource){
		resourceService.saveResources(resource);
		return "redirect:/resource/list";
	}
	
	@RequestMapping(value="/delete/{resourceid}",method=RequestMethod.GET )
	public String delete(@PathVariable("resourceid") Integer resourceid,Model model){
		Resource resource = resourceService.findResource(resourceid);

		if(resource.getRoles().size() > 0 ){
			model.addAttribute("msg", "该资源已经被角色引用不能删除。");
//			return "redirect:/resource/list";
			return list(model);
		}
		resourceService.deleteResource(resource);
		model.addAttribute("msg", "删除成功");
		return list(model);
	}
	
	@RequestMapping(value="/edit/{resourceid}",method=RequestMethod.GET )
	public String edit(@PathVariable("resourceid") Integer resourceid,Model model){
		Resource resource = resourceService.findResource(resourceid);
		model.addAttribute("resource", resource);
		model.addAttribute("title", "编辑资源");
		return "/resource/edit";
	}
		
	
	@RequestMapping(value="/update",method=RequestMethod.POST )
	public String update(Resource resource){
		resourceService.updateResource(resource);
		return "redirect:/resource/list";
	}

}
