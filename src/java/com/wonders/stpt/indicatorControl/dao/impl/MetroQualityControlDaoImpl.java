/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.indicatorControl.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.stpt.indicatorControl.dao.MetroQualityControlDao;
import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroQualityControlDaoImpl extends AbstractHibernateDaoImpl<MetroQualityControl> implements MetroQualityControlDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MetroQualityControl> findAllQualityControl() {
		String hql = "from MetroQualityControl t order by cast(t.indicatorLine as int) ASC";
		return getHibernateTemplate().find(hql);
	}

	@Override
	public MetroQualityControl findMetroQualityControlById(String id) {
		String hql = "from MetroQualityControl t where t.id='"+id+"'";
		return (MetroQualityControl) getSession().createQuery(hql).uniqueResult();
	}

	@Override
	public MetroQualityControl findQualityControlByLine(String line) {
		String hql = "from MetroQualityControl t where t.indicatorLine='"+line+"'";
		return (MetroQualityControl) getSession().createQuery(hql).uniqueResult();
	}
	
	@Override
	public Page findMetroQualityControlByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroQualityControl t ";
		String countHql = "select count(*) from MetroQualityControl t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				if(paramName.equals("indicatorLine") && filter.get(paramName).equals("-1")){
					filterPart += "t." + paramName + " <> :" + paramName;
				}else if(paramName.equals("ext1") && filter.get(paramName).equals("-1")){
					filterPart += "t." + paramName + " <> :" + paramName;
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
				}
				args.add(new HqlParameter(paramName, filter.get(paramName)));
				filterCounter++;
			}
		}
		filterPart +=" order by cast(t.indicatorLine as int) ASC,t.ext1 DESC,cast(t.ext2 as int) ASC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}

	@SuppressWarnings("unchecked")
	@Override
	public MetroQualityControl findMetroQualityControlByLineAndDate(String line, String date) {
		String hql = "from MetroQualityControl t where t.indicatorLine='"+line+"' and t.ext1='"+date+"'";
		Query query = getSession().createQuery(hql);
		List<MetroQualityControl> boList = query.setMaxResults(1).list();
		if(boList!=null && boList.size()>0) return boList.get(0);
		return null;
	}

	@Override
	public MetroQualityControl findMQCByLineAndDate(String line, String year,String month) {
		String hql = "from MetroQualityControl t where t.indicatorLine='"+line+"' and t.ext1='"+year+"' and t.ext2='"+month+"'";
		Query query = getSession().createQuery(hql);
		List<MetroQualityControl> mqcList = query.setMaxResults(1).list();
		if(mqcList!=null && mqcList.size()>0) 
			return mqcList.get(0);
		return null;
	}

	@Override
	public List<MetroQualityControl> findMQCByLineAndYearsAndMonths(String line, List<String> years, List<String> months) {
		List<MetroQualityControl> mqcList ;
		String hql = "from MetroQualityControl t where t.indicatorLine='"+line+"'";
		if(years!=null && years.size()>0 ){
			mqcList = new ArrayList<MetroQualityControl>();
			for(int i=0; i<years.size(); i++){
				String hql2 = hql + " and t.ext1='"+years.get(i)+"' and t.ext2 ='"+months.get(i)+"'";
				List<MetroQualityControl> tempList = getHibernateTemplate().find(hql2);
				if(tempList!=null && tempList.size()>0){
					mqcList.add(tempList.get(0));
				}else{
					mqcList.add(null);
				}
			}
		}else{
			return null;
		}
		return mqcList;
	}

	
	
}
