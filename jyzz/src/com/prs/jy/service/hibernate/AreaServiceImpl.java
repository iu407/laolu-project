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

import com.prs.jy.model.Area;
import com.prs.jy.service.AreaService;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("areaService")
public class AreaServiceImpl implements AreaService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Area> findAllArea() {
		Session session=sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Area ");
		List<Area> Areas = query.list();
		return Areas;
	}

	@Override
	public Area findArea(Integer areaid) {
		Session session = sessionFactory.getCurrentSession();
		Area Area = (Area)session.load(Area.class, areaid);
		return Area;
	}

	@Override
	public Integer saveArea(Area area) {
		Session session=sessionFactory.getCurrentSession();
		Integer areaid =  (Integer)session.save(area);
		return areaid;
	}


	@Override
	public void updateArea(Area area) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(area);
	}

	@Override
	public List<Area> findAreas(Integer[] areaids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Area.class);
		criteria.add(Property.forName("Areaid").in(areaids));
		List<Area> Areas = criteria.list();
		return Areas;
	}

	@Override
	public void deleteArea(Area area) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(area);
	}

	@Override
	public List<Area> findAreas(String stype) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Area.class);
		criteria.add(Property.forName("stype").eq(stype));
		List<Area> Areas = criteria.list();
		return Areas;
	}

	@Override
	public List<Area> findAreasCountry() {
		return findAreas(Area.AREA_COUNTRY);
	}

	@Override
	public List<Area> findAreasProvince() {
		return findAreas(Area.AREA_PROVINCE);
	}

	@Override
	public List<Area> findAreasCity() {
		return findAreas(Area.AREA_CITY);
	}
	
}
