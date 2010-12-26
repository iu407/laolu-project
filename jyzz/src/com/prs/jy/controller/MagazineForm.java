package com.prs.jy.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prs.jy.model.Category;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.model.Pojo;
import com.prs.jy.model.Resource;
import com.prs.jy.model.User;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.ObjectCategoryService;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;
import com.prs.jy.service.SecurityUserHolder;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;
import com.prs.jy.utils.PrsFileUtils;

/**
 * 杂志(杂志是商品的一种)
 * 
 * @author laolu
 * 
 */
@Controller
@RequestMapping("/mgz")
public class MagazineForm {
	
	@Autowired
	private GoodsService goodsService;//处理杂志（杂志作为商品处理）
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ObjectCategoryService objectCategoryService;
	
	@Autowired
	private ImgService imgService;
	
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private RoleService roleService;
	
	
	
	public MagazineForm(){}
	
	

	@RequestMapping("/show/{goodsid}")
	public String show(@PathVariable("goodsid") Integer goodsid,Model model) {
		Goods goods = goodsService.findGoods(goodsid);
		model.addAttribute("goods", goods);
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,2);
		Date peisongTime = cal.getTime();
		model.addAttribute("peisongTime", peisongTime);
		return "/mgz/show";
	}
	
	/**
	 * 这里应该分页了
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/list")
	public String list( Model model,Pager pager) throws Exception {
//		List<Goods> goodses = goodsService.findAllGoods();
		
		ListWithPager<Goods>  goodsPage = goodsService.findGoods(pager);
//		model.addAttribute("goodses", goodses);
		model.addAttribute("goodsPage", goodsPage);
		
		//配送时间
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH,2);
		Date peisongTime = cal.getTime();
		model.addAttribute("peisongTime", peisongTime);
		return "/mgz/list";
	}

	/**
	 * 映射到增加页面
	 * @return
	 */
	@RequestMapping("/new")
	public String addView(Model model){
		List<Category> categorys = categoryService.findCategorys(PrsConstants.GOODSCATEGORY);
		model.addAttribute("categorys", categorys);
		return "/mgz/new";
	}
	
	/**
	 * 保存商品
	 * @param goods
	 * @param objectCategory
	 * @param imgids
	 * @return
	 */
	@RequestMapping("/save")
	public String save(Goods goods,@RequestParam(required=false,value="imgid") Integer[] imgids){
//		Category category = categoryService.findCategory(objectCategory.getCategoryid());
//		objectCategory.setCattype(category.getCattype());
		
		User user = SecurityUserHolder.getCurrentUser();//得到用户
		goods.setAuthorid(user.getUserid());
		goods.setAuthorname(user.getUsername());
		
		Integer goodsid = goodsService.saveGoods(goods);
		if(imgids != null){//保存图片
			for (Integer imgid : imgids) {
				Img img = imgService.findImg(imgid);
				img.setTargetid(goodsid);
				imgService.updateImg(img);
			}
		}
		return "redirect:/adm/list";
	}
	
	@RequestMapping(value="/delete/{resourceid}",method=RequestMethod.GET )
	public String delete(@PathVariable("resourceid") Integer resourceid,Model model) throws Exception{
		Resource resource = resourceService.findResource(resourceid);

		if(resource.getRoles().size() > 0 ){
			model.addAttribute("msg", "该资源已经被角色引用不能删除。");
			return list(model,new Pager());
		}
		resourceService.deleteResource(resource);
		model.addAttribute("msg", "删除成功");
		return list(model,new Pager());
	}
	
	@RequestMapping(value="/edit/{goodsid}",method=RequestMethod.GET )
	public String edit(@PathVariable("goodsid") Integer goodsid,Model model){
		Goods goods = goodsService.findGoods(goodsid);
//		List<Img> imgs = imgService.findImgs(PrsConstants.IMGGOODS, goods.getGoodsid());
//		goods.setImgs(imgs);
		List<Category> categorys = categoryService.findCategorys(PrsConstants.GOODSCATEGORY);
		model.addAttribute("categorys", categorys);
		model.addAttribute("goods", goods);
		return "/mgz/edit";
	}


	/**
	 * 上架,这里需要改成ajax的方式
	 */
	@RequestMapping(value="/upsale/{goodsid}",  method=RequestMethod.GET )
	public String upsale(@PathVariable("goodsid") Integer goodsid,
			Model model){
		String siteurl = PrsConstants.getSITEURL();
		Goods goods = goodsService.findGoods(goodsid);
		goods.setIssale(!goods.getIssale());
//		goodsService.saveGoods(goods);
		goodsService.updateUpGoods(goodsid);
		
		model.addAttribute("goods", goods);
		return "/mgz/mgztip";
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("goods", goods);
//		mav.setViewName("/mgz/mgztip");
//		return mav;
//		return "<a href='"+ siteurl +"/mgz/downsale/"+ goodsid +"' onclick=changupdown('mgz_div_"+ goodsid +"',this,event) id='mgz_"+ goodsid +"'>下架</a>";
	}

	
	/**
	 * 下架
	 */
	@RequestMapping(value="/downsale/{goodsid}",method=RequestMethod.GET )
	public String downsale(
			@PathVariable("goodsid") Integer goodsid,
			Model model){
		String siteurl = PrsConstants.getSITEURL();
		
		Goods goods = goodsService.findGoods(goodsid);
		goods.setIssale(!goods.getIssale());
//		goodsService.saveGoods(goods);
		goodsService.updateUpGoods(goodsid);	
		model.addAttribute("goods", goods);
		return "/mgz/mgztip";
//		return "<a href='"+ siteurl +"/mgz/upsale/"+ goodsid +"' onclick=changupdown('mgz_div_"+ goodsid +"',this,event) id='mgz_"+ goodsid +"'>上架</a>";
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("goods", goods);
//		mav.setViewName("/mgz/mgztip");
//		return mav;
	}

	
	@RequestMapping(value="/update",method=RequestMethod.POST )
	public String update(Goods goods,@RequestParam(required=false,value="imgid") Integer[] imgids){
//		User user = SecurityUserHolder.getCurrentUser();//得到用户
//		goods.setAuthorid(user.getUserid());
//		goods.setAuthorname(user.getUsername());
		
		Integer goodsid = goodsService.updateGoods(goods);
		if(imgids != null){//保存图片
			for (Integer imgid : imgids) {
				Img img = imgService.findImg(imgid);
				img.setTargetid(goodsid);
				imgService.updateImg(img);
			}
		}
		return "redirect:/adm/list";
	}
	
	/**
	 * 处理上传.注意swf再上传时一个文件一个文件的处理，因此不需要处理数组
	 * 这个方法中加入了HttpServletRequest，不是纯粹的pojo
	 * upload是个大图片
	 * @param resource
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/upload" )
	@ResponseBody
	public String upload(@RequestParam("Filedata") MultipartFile file ,HttpServletRequest request ) throws IOException{
//		User user = SecurityUserHolder.getCurrentUser();//得到用户

		String webRootDir = PrsFileUtils.getWebRootDir(request);
		File uploadFile   = PrsFileUtils.getUploadFile(request,file.getOriginalFilename());
		file.transferTo(uploadFile);
		
		String absolutePath = uploadFile.getPath();
		String uploadPath = StringUtils.substringAfter(absolutePath, webRootDir);
		
		uploadPath = StringUtils.replaceChars(uploadPath, "\\", "/");
		System.out.println(uploadPath);
		
		Img img = new Img();
		img.setUrl(uploadPath);
		img.setTargettype(PrsConstants.IMGGOODS);
		Integer imgid = imgService.saveImg(img);
		
		//保存到数据库
		//"/jyzz/static/upload/201099优家画报.jpg"
		String contextPath = request.getContextPath();
		String src = contextPath + "/static/" + uploadPath;
		String inputHtml = "<input type='hidden' name='imgid' value='"+ imgid +"'/>";
		String imgHtml   = "<img src='/jyzz/static/" + uploadPath + "' width='80' width='100'/>";
		return inputHtml + imgHtml ;//返回一个图片
	}
	
	//http://localhost:8080/jyzz/mgz/getPerson
	@RequestMapping(value="/getPerson" ,method=RequestMethod.GET)
	@ResponseBody
	public Pojo getPerson() {
		Pojo pojo = new Pojo("qqqqqqqqqqqqqqq","18");
		return pojo;
	}
	
	@RequestMapping(value="/getGoods" ,method=RequestMethod.GET)
	@ResponseBody
	public List<Goods> getGoods() {
		List<Goods> goodses = goodsService.findAllGoods();
		return goodses;
	}
	/**
	 * 浏览最多了的杂志
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/top",method=RequestMethod.GET )
	public String topTenMgz(Model model) throws Exception{
		Pager pager = new Pager();
		pager.setPageSize(6);
		ListWithPager<Goods> listWithPager = goodsService.findGoods(pager);
		model.addAttribute("listWithPager", listWithPager);
		
		return "/mgz/topmgz";
	}

}
