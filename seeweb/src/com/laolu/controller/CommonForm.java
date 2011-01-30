package com.laolu.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author laolu
 *
 */
@Controller
public class CommonForm {
	
	@RequestMapping("/nodata")
	public String showImg( Model model) throws Exception {
		return "/common/nodata";
	}
	
}
