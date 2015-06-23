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

package com.wonders.stpt.metroIndicator.dao.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.stpt.metroIndicator.dao.MetroProductionDao;
import com.wonders.stpt.metroIndicator.entity.bo.MetroProduction;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TMetroProduction实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroProductionDaoImpl extends
		AbstractHibernateDaoImpl<MetroProduction> implements
		MetroProductionDao {
	
	//按日期 线路 取得对应数据
	public List<MetroProduction> findLatestMetroProductionEvents(String date, String line){
		String hql = "from MetroProduction m where m.removed = '0'";
		if(!"".equals(date)){
			hql += " and m.indicatorDate = '"+date+"'";
		}
		if(!"".equals(line)){
			hql += " and m.indicatorLine = '"+line+"'";
		}
		
		if("".equals(date)){
			hql += " order by m.indicatorDate desc";
		}
		//hql += " order by m.accidentDate desc ,m.accidentTime desc ";
		//System.out.println(hql+"--------------------------------");
		Query query = getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery(hql);
		query.setMaxResults(1);
		List<MetroProduction> list = query.list();
		//System.out.println(list.size()+"--------------------------------");
		return list;
	}
	
	//查询7天的线路数据
	@SuppressWarnings("unchecked")
	@Override
	public List<MetroProduction> findSevenDaysProduction(String startDate,String endDate,String line){
		
		String hql ="from MetroProduction t where t.removed ='0' and t.indicatorDate>'"
						+startDate+"' and t.indicatorDate <='"+endDate+"' and t.indicatorLine='"+line+"' order by t.indicatorDate ASC";
		
		return getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	public List<MetroProduction> findProductionWithData(String date,String line){
		String hql = "from MetroProduction t where t.removed ='0' and t.indicatorDate <='"
			+date+"' and t.indicatorLine='"+line+"' order by t.indicatorDate DESC";
		Query query = this.getSession().createQuery(hql);
		query.setMaxResults(1);
		return query.list();
	}
	
	//查询去年同期数据
	@SuppressWarnings("unchecked")
	@Override
	public List<MetroProduction> findSamePeriodLastYear(String date,String line){
		String hql = "from MetroProduction t where t.removed ='0' and t.indicatorDate='"+date+"' and t.indicatorLine='"+line+"'";
		
		return getHibernateTemplate().find(hql);
	}
	
	
	//查询总数
	public Integer findTotalMetroProductionByLineNo(String lineNo){
		String sql = "SELECT COUNT(*) from DW_METRO_PRODUCTION t where t.INDICATOR_LINE='"+lineNo+"'";
		Query query = this.getSession().createSQLQuery(sql);
		
		BigDecimal bd = (BigDecimal) query.uniqueResult();
		
		return bd.intValue();
	}
	
	//分页查询
	/*public List<MetroProduction> findMetroProductionByPage(String lineNo,int first,int size){
		String hql = "from MetroProduction t where t.indicatorLine='"+lineNo+"' order by t.indicatorDate DESC";
		Query query = getSession().createQuery(hql);
		query.setFirstResult(first).setMaxResults(size);
		return query.list();
	}*/
	
	
	@Override
	public Page findMetroProductionByPage(Integer pageNo, int pageSize,String lineNo) {
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroProduction t where t.indicatorLine='"+lineNo+"' ";
		String countHql = "select count(*) from MetroProduction t where t.indicatorLine='"+lineNo+"' ";
		String filterPart = "";
		filterPart += " order by t.indicatorDate DESC";
		return findByHQLWithPage(hql + filterPart, args, pageNo, pageSize,countHql + filterPart);
	}

	
	
	@SuppressWarnings("unchecked")
	@Override
	public MetroProduction findRecentlyProduction(String date,String line){
		String hql = "from MetroProduction t where t.indicatorDate <='"+date+"' order by t.indicatorDate DESC";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1);
		List<MetroProduction> list = query.list();
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MetroProduction> findProductionByDays(String startDate,String endDate,String line){
		String hql ="from MetroProduction t where t.removed ='0' and t.indicatorDate >='"
			+startDate+"' and t.indicatorDate <='"+endDate+"' and t.indicatorLine='"+line+"' order by t.indicatorDate ASC";

		return getHibernateTemplate().find(hql);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MetroProduction findMetroProductionByDate(String date,String line){
		String hql = "from MetroProduction t where t.removed ='0' and t.indicatorDate='"+date+"' and t.indicatorLine='"+line+"'";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1);
		List<MetroProduction> list = query.list();
		if(list!=null &&list.size()>0) return list.get(0);
		return null ;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public MetroProduction findMetroProductionByLastDay(String date,String line){
		String hql = "from MetroProduction t where t.removed ='0' and t.indicatorDate like '%"+date+"%' and t.indicatorLine='"+line+"' order by t.indicatorDate DESC";
		Query query = getSession().createQuery(hql);
		query.setMaxResults(1);
		List<MetroProduction> list = query.list();
		if(list!=null && list.size()>0) return list.get(0);
		return null;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MetroProduction> findMetroProductionByDates(String[] dates,String line) {
		String hql = "from MetroProduction t where t.removed ='0' and t.indicatorDate in (";
		if(dates.length>0){
			for(int i=0; i<dates.length; i++){
				if(i!=dates.length-1) hql +="'"+dates[i]+"',";
				else hql += "'"+dates[i]+"'";
			}
			hql += ") and t.indicatorLine='"+line+"' order by t.indicatorDate ASC";
			return getHibernateTemplate().find(hql);
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findFirstAndLastDate(){
		String sql = "SELECT MIN(t.INDICATOR_DATE),MAX(t.INDICATOR_DATE) from DW_METRO_PRODUCTION t";
		Query query = getSession().createSQLQuery(sql);
		return query.list();
	}

}
