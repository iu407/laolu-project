package com.jyzz.test;

import java.math.BigDecimal;

import com.aviyehuda.easyimage.Image;


public class ImageTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String filePath = "C:/img/201011GQ智族.jpg";
//		String extension = FilenameUtils.getExtension(filePath);
//		String baseName = FilenameUtils.getBaseName(filePath);
//		String fullPath = FilenameUtils.getFullPath(filePath);
//		System.out.println(extension);
//		System.out.println(baseName);
//		System.out.println(fullPath);
//		
//		String targetName = "C:/img/Sunset_thumb.jpg";
////		String targetName = fullPath + baseName + "_thumb" + "." + extension;
//		System.out.println(targetName);
//		
		Image image  = new Image(filePath);
		int h = 200;
		double width = image.getWidth();
		double height = image.getHeight();
		double r = width/height;
		
		Double d = r*h;
		System.out.println("比例："+r);
		int w = d.intValue();
		System.out.println("w："+w+"h："+h);
		image.resize(w, h);
		
//		Image image2  = new Image(targetName);
//		image.combineWithPicture(image2,1,Color.white);
//		
//		
		image.saveAs("C:/img/201011GQ智族1.jpg");//
		
		Double hr = height/ h;
		image.resize(100);
		image.saveAs("C:/img/201011GQ智族2.jpg");//
//		String str1 = "D:/apache-tomcat-6.0.29/webapps/jyzz/upload/201099优家画报.jpg";
//		String str2 = "D:/apache-tomcat-6.0.29/webapps/jyzz/";
//		String str3 = StringUtils.substringAfter(str1, str2);
//		System.out.println(str3);
	}
	

}
