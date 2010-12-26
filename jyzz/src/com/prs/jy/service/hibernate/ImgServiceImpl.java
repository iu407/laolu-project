package com.prs.jy.service.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Img;
import com.prs.jy.service.ImgService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("imgService")
public class ImgServiceImpl implements ImgService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Img> findAllImg() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Img ");
		List<Img> Imgs = query.list();
		return Imgs;
	}

	@Override
	public Img findImg(Integer imgid) {
		Session session = sessionFactory.getCurrentSession();
		Img Img = (Img)session.load(Img.class, imgid);
		session.flush();
		return Img;
	}

	@Override
	public Integer saveImg(Img img) {
		Session session=sessionFactory.getCurrentSession();
		Integer imgid =  (Integer)session.save(img);
		return imgid;
	}


	@Override
	public void updateImg(Img img) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(img);
	}

	@Override
	public List<Img> findImgs(Integer[] imgids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Img.class);
		criteria.add(Property.forName("Imgid").in(imgids));
		List<Img> Imgs = criteria.list();
		return Imgs;
	}

	@Override
	public void deleteImg(Img img) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(img);
	}

	@Override
	public List<Img> findImgs(String targetType,Integer targetid) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Img.class);
		criteria.add(Property.forName("targetType").eq(targetType));
		criteria.add(Property.forName("targetid").eq(targetid));
		List<Img> imgs = criteria.list();
		return imgs;
	}

	public void updateImgTargetId(Integer imgid, Integer targetid){
		Session session=sessionFactory.getCurrentSession();
		Img img = findImg(imgid);
		img.setTargetid(targetid);
		session.saveOrUpdate(img);
		
	}

	@Override
	public void updateDftImg(Img img) {
		Img aImg = findImg(img.getImgid());
		List<Img> imgs = findImgs(aImg.getTargettype(), aImg.getTargetid());
		
		for (int i = 0; i < imgs.size(); i++) {
			Img bimg = imgs.get(i);
			bimg.setDftimg(false);
		}
		aImg.setDftimg(true);
	}
	
}
