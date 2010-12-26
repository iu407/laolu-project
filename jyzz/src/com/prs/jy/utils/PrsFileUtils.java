package com.prs.jy.utils;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import com.prs.jy.model.User;
import com.prs.jy.service.SecurityUserHolder;

/**
 * 处理文件
 * @author laolu
 *
 */
public class PrsFileUtils {
	public static String SEPARATOR = System.getProperty("file.separator");
	public static String UPLOADDIR = "upload";
	
	public static String getWebRootDir(HttpServletRequest request){
//		User user = SecurityUserHolder.getCurrentUser();//得到用户
		String webRootDir = request.getSession().getServletContext().getRealPath(SEPARATOR);
		return webRootDir;
	}
	/**
	 * 得到将要被上传的文件
	 * @param request
	 * @return
	 */
	public static File getUploadFile(HttpServletRequest request,String filename){
		String webRootDir = getWebRootDir( request) ;
		String uploadDir = webRootDir + UPLOADDIR;
		File filePath = new File(uploadDir);
		if (!filePath.exists()) {
			filePath.mkdirs();
		}
		File newFile = new File(uploadDir +  SEPARATOR + filename);
		return newFile;
	}
	
}
