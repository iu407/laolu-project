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

import com.prs.jy.model.Consignee;
import com.prs.jy.model.User;
import com.prs.jy.service.AreaService;
import com.prs.jy.service.ConsigneeService;
import com.prs.jy.service.UsersService;
/**
 * 收货人地址
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("consigneeService")
public class ConsigneeServiceImpl implements ConsigneeService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UsersService usersService;

	@Override
	public Consignee findConsignee(Integer consigneeid) {
		Session session = sessionFactory.getCurrentSession();
		Consignee consignee = (Consignee)session.load(Consignee.class, consigneeid);
		consignee.setProvince(areaService.findArea(consignee.getProvinceid()));
		consignee.setCity(areaService.findArea(consignee.getCityid()));
		return consignee;
	}

	@Override
	public Integer saveConsignee(Consignee consignee) {
		Session session=sessionFactory.getCurrentSession();
		Integer consigneeid =  (Integer)session.save(consignee);
		updateToDefault(findConsignee(consigneeid));
		
		return consigneeid;
	}


	@Override
	public void updateConsignee(Consignee consignee) {
		Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(consignee);
	}

	@Override
	public List<Consignee> findConsignees(Integer[] consigneeids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Consignee.class);
		criteria.add(Property.forName("Consigneeid").in(consigneeids));
		List<Consignee> Consignees = criteria.list();
		return Consignees;
	}

	@Override
	public void deleteConsignee(Consignee consignee) {
		Session session=sessionFactory.getCurrentSession();
		session.delete(consignee);
	}

	@Override
	public List<Consignee> findConsignee(User user) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Consignee.class);
		criteria.add(Property.forName("userid").eq(user.getUserid()));
		List<Consignee> Consignees = criteria.list();
		
		for (Consignee consignee : Consignees) {
			consignee.setProvince(areaService.findArea(consignee.getProvinceid()));
			consignee.setCity(areaService.findArea(consignee.getCityid()));
		}
		
		return Consignees;
	}

	@Override
	public void updateToDefault(Consignee consignee) {
		List<Consignee> consignees = findConsignee(usersService.findUser(consignee.getUserid()));
		for (Consignee aConsignee : consignees) {
			aConsignee.setDft(false);
			updateConsignee(aConsignee);
		}
		consignee.setDft(true);
		updateConsignee(consignee);
		
	}

}
