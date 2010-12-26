package com.prs.jy.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.model.Pojo;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.ObjectCategoryService;
import com.prs.jy.service.ResourceService;
import com.prs.jy.service.RoleService;
import com.prs.jy.utils.PrsConstants;
import com.prs.jy.utils.PrsFileUtils;
import com.prs.jy.utils.PrsUtils;

/**
 * 上传控制器
 * 用来上传图片，文件等资源
 * @author laolu
 * 
 */
@Controller
public class UploadController {
	
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
	
	
	public UploadController(){}


	/**
	 * 
	 * @param file
	 * @param imgtype 图片类型
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/uploadimg/{imgtype}" )
	@ResponseBody
	public String uploadimg(
			@RequestParam("Filedata") MultipartFile file ,
			@PathVariable("imgtype") String imgtype,
			HttpServletRequest request ) throws IOException{
//		User user = SecurityUserHolder.getCurrentUser();//得到用户

		String webRootDir = PrsFileUtils.getWebRootDir(request);
		File uploadFile   = PrsFileUtils.getUploadFile(request,file.getOriginalFilename());
		file.transferTo(uploadFile);//保存文件
		
		String absolutePath = uploadFile.getPath();
		String uploadPath = StringUtils.substringAfter(absolutePath, webRootDir);
		
		uploadPath = StringUtils.replaceChars(uploadPath, "\\", "/");
		System.out.println(uploadPath);
		
		Img img = new Img();
		img.setUrl(uploadPath);
		img.setTargettype(PrsConstants.IMGTYPEMAP.get(imgtype));
		Integer imgid = imgService.saveImg(img);
		
		//保存到数据库
		String contextPath = request.getContextPath();
		String src = contextPath + "/static/" + uploadPath;
		String inputHtml = "<input type='hidden' name='imgid' value='"+ imgid +"'/>";
		String imgHtml   = "<img src='/jyzz/static/" + uploadPath + "' width='80' width='100'/>";
		return inputHtml + imgHtml ;//返回一个图片
	}
	
	@RequestMapping(value="/uploadcsv" )
	public String uploadCSV(@RequestParam("Filedata") MultipartFile file ) throws Exception{
		List<Goods> goodses = PrsUtils.getGoodsFromCSVInputStream(file.getInputStream());
		for (Goods goods : goodses) {
			goodsService.saveGoods(goods);
//			System.out.println(goods.getGoodsname()+" /"+goods.getJyid()+ " /" + goods.getPrice());
		}
		return "/admin/csvtip";
	}
//	
//	@RequestMapping(value="/getPerson" ,method=RequestMethod.GET)
//	@ResponseBody
//	public Pojo getPerson() {
//		Pojo pojo = new Pojo("qqqqqqqqqqqqqqq","18");
//		return pojo;
//	}
//	
//	@RequestMapping(value="/getGoods" ,method=RequestMethod.GET)
//	@ResponseBody
//	public List<Goods> getGoods() {
//		List<Goods> goodses = goodsService.findAllGoods();
//		return goodses;
//	}

}
