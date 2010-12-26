package com.prs.jy.service.hibernate;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.SimpleExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prs.jy.model.Category;
import com.prs.jy.model.Goods;
import com.prs.jy.model.Img;
import com.prs.jy.model.ObjectCategory;
import com.prs.jy.service.CategoryService;
import com.prs.jy.service.GoodsService;
import com.prs.jy.service.ImgService;
import com.prs.jy.service.ObjectCategoryService;
import com.prs.jy.utils.Function;
import com.prs.jy.utils.HibernateUtils;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
import com.prs.jy.utils.PrsConstants;
import com.prs.jy.utils.Way;
/**
 * 
 * @author laolu
 *
 */
@Repository
@Transactional
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private ObjectCategoryService objectCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ImgService imgService;
	
	@Override
	public Goods findGoods(Integer goodsid) {
		Session session = sessionFactory.getCurrentSession();
		Goods goods = (Goods)session.load(Goods.class, goodsid);
		List<Img> imgs = imgService.findImgs(PrsConstants.IMGGOODS, goods.getGoodsid());
		goods.setImgs(imgs);
		return goods;
	}

	@Override
	public List<Goods> findGoodses(Integer[] goodsids) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Goods.class);
		criteria.add(Property.forName("goodsid").in(goodsids));
		criteria.addOrder(Order.desc("goodsid"));
		List<Goods> goodses = criteria.list();
		return goodses;

	}

	@Override
	public Integer saveGoods(Goods goods) {
		Session session=sessionFactory.getCurrentSession();
		Integer goodsid =  (Integer)session.save(goods);
		return goodsid;
	}

	@Override
	public Integer saveGoods(Goods goods, ObjectCategory objectCategory) {
		Integer goodsid = saveGoods(goods);
		objectCategory.setTargetid(goodsid);
		objectCategoryService.saveObjectCategory(objectCategory);
		return goodsid;
	}

	@Override
	public List<Goods> findAllGoods() {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Goods.class);
		criteria.addOrder(Order.desc("goodsid"));
		List<Goods> goodses = criteria.list();
		return goodses;
	}
	

	@Override
	public Integer updateDownGoods(Integer goodsid) {
		String setString = "issale = 'F' ";
		return updateGoodsBySetString(goodsid,setString);
	}

	@Override
	public Integer updateUpGoods(Integer goodsid) {
		String setString = "issale = 'T' ";
		return updateGoodsBySetString(goodsid,setString);
	}
	
	
	/**
	 * 传入goodsid和sql中set的字符串
	 * 例如issale=1
	 * 修改若干字段
	 */
	private Integer updateGoodsBySetString(Integer goodsid,String setString){
		String sql = "update PRS_GOODS set " + setString + " where goodsid = "+ goodsid;
		Session session=sessionFactory.getCurrentSession();
		
		
//		Goods goods = findGoods(goodsid);
//		goods.setIssale(!goods.getIssale());
//		return 1;
		
		return session.createSQLQuery(sql).executeUpdate();
		
	}

	@Override
	public ListWithPager<Goods> findGoods(Pager pager) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Goods.class);
		Map<String,String> demand = pager.getDemand();
		
		HibernateUtils.injectWhere(criteria, "goodsname", demand, "goodsname", Way.STRING);
		HibernateUtils.injectWhere(criteria, "jyid", demand, "jyid", Way.STRING);
		HibernateUtils.injectWhere(criteria, "price", demand, "price", Way.DECIMAL);
		HibernateUtils.injectWhere(criteria, "price", demand, "price", Way.DECIMAL);
			
		Integer resultCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		pager.setResultCount(resultCount);
		List<Goods> results = criteria.setProjection(null).setFirstResult(
				pager.getFirstIndex()).setMaxResults(pager.getPageSize())
				.list();
		
		for (Goods goods : results) {
			Integer categoryid = goods.getCategoryid();
			Category category = categoryService.findCategory(categoryid);
			goods.setCategory(category);
			List<Img> imgs = imgService.findImgs(PrsConstants.IMGGOODS, goods.getGoodsid());
			goods.setImgs(imgs);
		}
		
		return new ListWithPager<Goods>(results, pager);
	}

	@Override
	public Integer updateGoods(Goods goods) {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Goods.class);
		session.update(goods);
		return goods.getGoodsid();
	}

	@Override
	public ListWithPager<Goods> findGoodsIndex(Pager pager) throws Exception {
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(Goods.class);
		Map<String,String> demand = pager.getDemand();
		
		String goodsname = demand.get("goodsname");
		String jyid = demand.get("jyid");
		if(goodsname != null || jyid != null){
			if(!"".equals(goodsname) || !"".equals(jyid)){
				criteria.add(Expression.or(
						Expression.like("goodsname", goodsname, MatchMode.ANYWHERE), 
						Expression.like("jyid", jyid, MatchMode.ANYWHERE)));
			}
		}
		Integer resultCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
		pager.setResultCount(resultCount);
		List<Goods> results = criteria.setProjection(null).setFirstResult(
				pager.getFirstIndex()).setMaxResults(pager.getPageSize())
				.list();
		
		for (Goods goods : results) {
			Integer categoryid = goods.getCategoryid();
			Category category = categoryService.findCategory(categoryid);
			goods.setCategory(category);
			List<Img> imgs = imgService.findImgs(PrsConstants.IMGGOODS, goods.getGoodsid());
			goods.setImgs(imgs);
		}
		
		return new ListWithPager<Goods>(results, pager);	}

	@Override
	public List<Goods> findTopGoods() {
		return null;
	}
}
