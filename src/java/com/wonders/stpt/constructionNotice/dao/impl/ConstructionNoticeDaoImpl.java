package com.wonders.stpt.constructionNotice.dao.impl;

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


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;


import com.wonders.stpt.constructionNotice.dao.ConstructionNoticeDao;
import com.wonders.stpt.constructionNotice.entity.bo.ConstructionNotice;
import com.wonders.stpt.metroLine.entity.bo.MetroLine;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * ConstructionNotice实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class ConstructionNoticeDaoImpl extends
		AbstractHibernateDaoImpl<ConstructionNotice> implements
		ConstructionNoticeDao {
	public Page findConstructionNoticeByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from ConstructionNotice t ";
		String countHql = "select count(*) from ConstructionNotice t ";
		String filterPart = "";
		int filterCounter = 0;
		if (!filter.isEmpty()) {
			filterPart += " where ";
			for (Iterator<String> i = filter.keySet().iterator(); i.hasNext();) {
				if (filterCounter > 0) {
					filterPart += " and ";
				}
				String paramName = i.next();
				
				if(paramName.equals("applyUnit") || paramName.equals("constructionUnit") || paramName.equals("constructionDetail")){
					filterPart += "t." + paramName + " like :" + paramName;
					args.add(new HqlParameter(paramName, "%"+filter.get(paramName)+"%"));
				}else if(paramName.equals("constructionStartDate")){
					filterPart += "t." + paramName + " > :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else if(paramName.equals("constructionEndDate")){
					filterPart += "t." + paramName + " < :" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}else{
					filterPart += "t." + paramName + "=:" + paramName;
					args.add(new HqlParameter(paramName, filter.get(paramName)));
				}
				
				filterCounter++;
			}
		}
		filterPart += " order by t.id DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,
				countHql + filterPart);
	}
	
	/**
	 * @author ycl
	 * @describe 保存所有数据
	 * @param dataList
	 */
	public void saveAll(List<ConstructionNotice> dataList){
		getHibernateTemplate().saveOrUpdateAll(dataList);
	}
	
	/**
	 * @author ycl
	 * @describe 查询所有线路
	 * @return
	 */
	public List<MetroLine> findAllMetroLine(){
		String hql = "from MetroLine t order by t.sortingOrder";
		return getHibernateTemplate().find(hql);
	}
	
	
	public int findCountByLineNo(String lineNo,String startDate,String endDate){
		String sql ="SELECT COUNT(*) from T_CONSTRUCTION_NOTICE t where t.LINE_NO='"+lineNo+"'" +
				" and t.construction_date >= to_date('"+startDate+"','yyyy/mm/dd')" +
				" and t.construction_date <= to_date('"+endDate+"','yyyy/mm/dd')" ;
		Query query = getSession().createSQLQuery(sql);
		query.setMaxResults(1);
		List<?> list = query.list();
		return Integer.valueOf(list.get(0).toString());
	}
}
