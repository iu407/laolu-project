package com.prs.jy.utils;

import java.math.BigDecimal;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;



/**
 * Discription: Datetime: 2007-8-6 13:21:02
 * @author Hellfire
 * @version 1.0
 */
public abstract class HibernateUtils
{

	public static void injectWhere(Criteria criteria, String property,
			Map<String,String> demand, String key, Way way) throws Exception
	{
		if(way.equals(Way.LONG))
		{
			String value = demand.get(key);
			if(!Function.isBlank(value))
			{
				criteria
						.add(Property.forName(property).eq(Long.valueOf(value)));
			}
		}
		else if(way.equals(Way.STRING))
		{
			String value = demand.get(key);
			if(!Function.isBlank(value))
			{
				criteria.add(Property.forName(property).like(value.trim(),
						MatchMode.ANYWHERE));
			}
		}
		else if(way.equals(Way.BOOLEAN))
		{
			String value = demand.get(key);
			if(!Function.isBlank(value))
			{
				criteria.add(Property.forName(property).eq(
						Boolean.valueOf(value)));
			}
		}
		else if(way.equals(Way.NUMERIC))
		{
			String valueMin = demand.get(key + "Min");
			if(!Function.isBlank(valueMin))
			{
				criteria.add(Property.forName(property).ge(
						Long.valueOf(valueMin)));
			}
			String valueMax = demand.get(key + "Max");
			if(!Function.isBlank(valueMax))
			{
				criteria.add(Property.forName(property).lt(
						Long.valueOf(valueMax)));
			}
		}
		else if(way.equals(Way.DECIMAL))
		{
			String valueMin = demand.get(key + "Min");
			if(!Function.isBlank(valueMin))
			{
				criteria.add(Property.forName(property).ge(
						new BigDecimal(valueMin)));
			}
			String valueMax = demand.get(key + "Max");
			if(!Function.isBlank(valueMax))
			{
				criteria.add(Property.forName(property).lt(
						new BigDecimal(valueMax)));
			}
		}
		else if(way.equals(Way.DECIMAL))
		{
			String valueMin = demand.get(key + "Min");
			if(!Function.isBlank(valueMin))
			{
				criteria.add(Property.forName(property).ge(
						new BigDecimal(valueMin)));
			}
			String valueMax = demand.get(key + "Max");
			if(!Function.isBlank(valueMax))
			{
				criteria.add(Property.forName(property).lt(
						new BigDecimal(valueMax)));
			}
		}
		else if(way.equals(Way.DATE))
		{
			String valueBegin = demand.get(key + "Begin");
			if(!Function.isBlank(valueBegin))
			{
				valueBegin += " 00:00:00";
				criteria.add(Property.forName(property).ge(
						Function.parse(valueBegin, "yyyy-MM-dd HH:mm:ss")));
			}

			String valueEnd = demand.get(key + "End");
			if(!Function.isBlank(valueEnd))
			{
				valueEnd += " 23:59:59";
				criteria.add(Property.forName(property).le(
						Function.parse(valueEnd, "yyyy-MM-dd HH:mm:ss")));
			}
		}

	}

	public static void injectOrders(Criteria criteria, Map<String,String> orders)
	{
		for(Map.Entry<String,String> order:orders.entrySet())
		{
			if("asc".equalsIgnoreCase(order.getValue()))
			{
				criteria.addOrder(Order.asc(order.getKey()));
			}
			else
			{
				criteria.addOrder(Order.desc(order.getKey()));
			}
		}
	}

}
