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

/**
 * 图片控制器
 * @author laolu
 * 
 */
@Controller
public class ImgController {
	
	@Autowired
	private ImgService imgService;
	
	public ImgController(){}

	@RequestMapping(value="/dftimg/{imgid}" )
	public void makeDftImg( @PathVariable("imgid") Integer imgid ) {
		Img img = imgService.findImg(imgid);
		imgService.updateDftImg(img);
//		return "ok" ;//返回一个图片
	}
	
	@RequestMapping(value="/deleteimg/{imgid}" )
	public void  deleteImg( @PathVariable("imgid") Integer imgid ) {
		Img img = imgService.findImg(imgid);
		imgService.deleteImg(img);
	}

}
