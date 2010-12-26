package com.prs.jy.service;

import java.util.List;

import com.prs.jy.model.Goods;
import com.prs.jy.model.ObjectCategory;
import com.prs.jy.utils.ListWithPager;
import com.prs.jy.utils.Pager;
/**
 * 商品服务
 * @author laolu
 *
 */
public interface GoodsService {
	/*
	 * 查找一个商品
	 */
	public Goods findGoods(Integer goodsid);
	
	public List<Goods> findAllGoods();
	
	public ListWithPager<Goods> findGoods(Pager pager) throws Exception ;
	/**
	 * 万能搜索
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	public ListWithPager<Goods> findGoodsIndex(Pager pager) throws Exception ;

	public List<Goods> findGoodses(Integer[] goodsids);
	/**
	 * 保存商品
	 * @param goods
	 * @return
	 */
	public Integer saveGoods(Goods goods);
	
	public Integer updateGoods(Goods goods);
	
	public Integer saveGoods(Goods goods,ObjectCategory objectCategory);
	/**
	 * 上架
	 * @param goodsid
	 * @return
	 */
	public Integer updateUpGoods(Integer goodsid);
	/**
	 * 下架
	 * 
	 */
	public Integer updateDownGoods(Integer goodsid);
	/**
	 * 最受欢迎的杂志6本杂志
	 * @return
	 */
	public List<Goods> findTopGoods();
	
	
}
