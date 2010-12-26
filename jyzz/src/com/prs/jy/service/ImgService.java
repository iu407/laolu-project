package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Img;
/**
 * 
 * @author laolu
 *
 */
public interface ImgService {
	public Img findImg(Integer imgid);
	
	public List<Img> findImgs(Integer[] imgids);
	
	public List<Img> findAllImg();
	/**
	 * 上找类型
	 * @param cattype
	 * @return
	 */
	public List<Img> findImgs(String cattype,Integer targetId);
	
	public Integer saveImg(Img img);
	
	public void updateImg(Img img);
	
	public void deleteImg(Img img);
	/**
	 * 更新成默认图片
	 * @param img
	 */
	public void updateDftImg(Img img);
	
//	public void updateImgTargetId(Integer imgid,Integer targetId);

}
