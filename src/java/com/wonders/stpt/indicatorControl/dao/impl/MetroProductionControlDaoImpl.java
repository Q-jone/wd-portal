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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.wonders.stpt.indicatorControl.dao.MetroProductionControlDao;
import com.wonders.stpt.indicatorControl.entity.bo.MetroProductionControl;
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

public class MetroProductionControlDaoImpl extends AbstractHibernateDaoImpl<MetroProductionControl> implements MetroProductionControlDao {

	
	@SuppressWarnings("unchecked")
	@Override
	public List<MetroProductionControl> findAllProductionControl(){
		String hql = "from MetroProductionControl t order by t.indicatorLine ASC,t.ext1 DESC";
		
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public MetroProductionControl findProductionControlByLine(String line) {
		String hql = "from MetroProductionControl t where t.indicatorLine='"+line+"' order by t.indicatorLine ASC";
		Query query = getSession().createQuery(hql);
		return (MetroProductionControl) query.uniqueResult();
	}
	
	@Override
	public void saveProductionControl(MetroProductionControl mpc) {
		getHibernateTemplate().save(mpc);
	}

	@Override
	public MetroProductionControl findMetroProductionControlById(String id) {
		String hql = "from MetroProductionControl t where t.id ='"+id+"'";
		Query query = getSession().createQuery(hql);
		return (MetroProductionControl) query.uniqueResult();
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public MetroProductionControl findProductionControlByYearAndLine(String year,String month, String line) {
		String hql = "from MetroProductionControl t where t.indicatorLine ='"+line+"' and t.ext1='"+year+"' and t.ext2='"+month+"'";
		Query query = getSession().createQuery(hql);
		List<MetroProductionControl> mpcList = query.setMaxResults(1).list();
		if(mpcList!=null && mpcList.size()!=0) return mpcList.get(0);
		return null;
	}
	
	@Override
	public Page findMetroProductionControlByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		if (filter == null)
			filter = new HashMap<String, Object>();
		List<HqlParameter> args = new ArrayList<HqlParameter>();
		String hql = "select t from MetroProductionControl t ";
		String countHql = "select count(*) from MetroProductionControl t ";
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

	@Override
	public MetroProductionControl findProductionControlByYearAndLine(String year, String line) {
		String hql = "from MetroProductionControl t where t.indicatorLine ='"+line+"' and t.ext1='"+year+"'";
		Query query = getSession().createQuery(hql);
		List<MetroProductionControl> mpcList = query.setMaxResults(1).list();
		if(mpcList!=null && mpcList.size()!=0) return mpcList.get(0);
		return null;
	}

	@Override
	public void updateProductionControl(MetroProductionControl mpc) {
		super.getHibernateTemplate().saveOrUpdate(mpc);
	}

	//根据日期和线路查询管控值
	@Override
	public List<MetroProductionControl> findProductionControlByDatesAndLine(String startDate, String endDate, String line) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<MetroProductionControl> list = new ArrayList<MetroProductionControl>();
		
		try {
			Date s = sdf.parse(startDate);
			Date e = sdf.parse(endDate);
			
			Calendar c1 = Calendar.getInstance();
				c1.setTime(s);
			Calendar c2 = Calendar.getInstance();
				c2.setTime(e);
				
			if(c1.getTimeInMillis()>c2.getTimeInMillis()){	//如果起始时间>结束时间，对换值
				Calendar temp = c1;
				c1 = c2; 
				c2 = temp;
			}
			
			if(c1.get(Calendar.YEAR)==c2.get(Calendar.YEAR)){		//年份相等
				list.addAll(findListBySameYear(c1,c2,line));
				
			}else{				//年份不等
				Calendar maxDayOfCurrentYear = Calendar.getInstance();		//当前年的月份、日期最大值
				maxDayOfCurrentYear.set(Calendar.YEAR, c1.get(Calendar.YEAR));
				maxDayOfCurrentYear.set(Calendar.MONTH, c1.getMaximum(Calendar.MONTH));
				maxDayOfCurrentYear.set(Calendar.DAY_OF_MONTH, maxDayOfCurrentYear.getMaximum(Calendar.DAY_OF_MONTH));
				
//				maxDayOfCurrentYear.set(Calendar.MONTH, maxDayOfCurrentYear.getMaximum(Calendar.MONTH));		//月份最大
//				maxDayOfCurrentYear.set(Calendar.DAY_OF_MONTH, maxDayOfCurrentYear.getMaximum(Calendar.DAY_OF_MONTH));	//日期最大
				
				list.addAll(findListBySameYear(c1,maxDayOfCurrentYear,line));
				
				boolean yearStatus = true;
				Calendar minDayOfCurrentYear = null;
				Calendar tempCalendar = Calendar.getInstance();
				tempCalendar.set(Calendar.YEAR, c1.get(Calendar.YEAR));
				tempCalendar.set(Calendar.MONTH, c1.get(Calendar.MONTH));
				tempCalendar.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH));
				
				
				while(yearStatus){
					
					tempCalendar.set(Calendar.YEAR, tempCalendar.get(Calendar.YEAR)+1);		//年份+1
					
					minDayOfCurrentYear = Calendar.getInstance();
					minDayOfCurrentYear.set(Calendar.YEAR, tempCalendar.get(Calendar.YEAR));
					minDayOfCurrentYear.set(Calendar.MONTH, tempCalendar.get(Calendar.MONTH));
					minDayOfCurrentYear.set(Calendar.DAY_OF_MONTH, tempCalendar.get(Calendar.DAY_OF_MONTH));
					
					minDayOfCurrentYear.set(Calendar.MONTH, minDayOfCurrentYear.getMinimum(Calendar.MONTH));	//月份最小
					minDayOfCurrentYear.set(Calendar.DAY_OF_MONTH, minDayOfCurrentYear.getMinimum(Calendar.DAY_OF_MONTH));	//日期最小
					if(tempCalendar.get(Calendar.YEAR)==c2.get(Calendar.YEAR)){		//年份相等
						list.addAll(findListBySameYear(minDayOfCurrentYear,c2,line));
						yearStatus = false;
					}else{		//年份不等
						maxDayOfCurrentYear = tempCalendar;
						maxDayOfCurrentYear.set(Calendar.YEAR, tempCalendar.get(Calendar.YEAR));
						maxDayOfCurrentYear.set(Calendar.MONTH, tempCalendar.get(Calendar.MONTH));
						maxDayOfCurrentYear.set(Calendar.DAY_OF_MONTH, tempCalendar.get(Calendar.DAY_OF_MONTH));
						maxDayOfCurrentYear.set(Calendar.YEAR, maxDayOfCurrentYear.getMaximum(Calendar.MONTH));	//月份最大
						maxDayOfCurrentYear.set(Calendar.DAY_OF_MONTH,maxDayOfCurrentYear.get(Calendar.DAY_OF_MONTH));	//日期最大
						list.addAll(findListBySameYear(minDayOfCurrentYear,maxDayOfCurrentYear,line));
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	
	
	//相同年份
	public List<MetroProductionControl> findListBySameYear(Calendar c1,Calendar c2,String line){
		List<MetroProductionControl> list = new ArrayList<MetroProductionControl>();
		if(c1.get(Calendar.MONTH)==c2.get(Calendar.MONTH)){		//月份相等
			list.addAll(findListBySameMonth(c1,c2,line));		
			
		}else{			//月份不等
			
			Calendar tempCalendar = Calendar.getInstance();
			tempCalendar.set(Calendar.YEAR, c1.get(Calendar.YEAR));
			tempCalendar.set(Calendar.MONTH,c1.get(Calendar.MONTH));
			tempCalendar.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH));
			
			Calendar maxDayOfCurrentMonth = Calendar.getInstance();	//当前月 日期最大
			maxDayOfCurrentMonth.set(Calendar.YEAR, c1.get(Calendar.YEAR));
			maxDayOfCurrentMonth.set(Calendar.MONTH,c1.get(Calendar.MONTH));
			maxDayOfCurrentMonth.set(Calendar.DAY_OF_MONTH, c1.getMaximum(Calendar.DAY_OF_MONTH));
			
			
			//maxDayOfCurrentMonth.set(Calendar.DAY_OF_MONTH, maxDayOfCurrentMonth.getMaximum(Calendar.DAY_OF_MONTH));	//设置日期最大
			
			list.addAll(findListBySameMonth(c1, maxDayOfCurrentMonth, line));		//当前月份的管控值
			
			boolean status = true;
			while(status){
				
				Calendar minDayOfCurrentMonth = null ;		//当前月份的日期最小值
				int currentMonth = tempCalendar.get(Calendar.MONTH);			
				tempCalendar.add(Calendar.MONTH, 1);
				if(tempCalendar.get(Calendar.MONTH) != c2.get(Calendar.MONTH)){			//+1后的月份和结束月份不等
					maxDayOfCurrentMonth = Calendar.getInstance();
					maxDayOfCurrentMonth.set(Calendar.YEAR, tempCalendar.get(Calendar.YEAR));
					maxDayOfCurrentMonth.set(Calendar.MONTH, tempCalendar.get(Calendar.MONTH));
					maxDayOfCurrentMonth.set(Calendar.DAY_OF_MONTH, tempCalendar.getMaximum(Calendar.DAY_OF_MONTH));
					
					
					minDayOfCurrentMonth = Calendar.getInstance();
					
					minDayOfCurrentMonth.set(Calendar.YEAR, tempCalendar.get(Calendar.YEAR));
					minDayOfCurrentMonth.set(Calendar.MONTH, tempCalendar.get(Calendar.MONTH));
					minDayOfCurrentMonth.set(Calendar.DAY_OF_MONTH, tempCalendar.getMinimum(Calendar.DAY_OF_MONTH));
					
					list.addAll(findListBySameMonth(minDayOfCurrentMonth, maxDayOfCurrentMonth, line));
					
				}else{									//+1后的月份和结束月份相等
					minDayOfCurrentMonth = Calendar.getInstance();				//当前月份的最小值
					minDayOfCurrentMonth.set(Calendar.YEAR, c2.get(Calendar.YEAR));
					minDayOfCurrentMonth.set(Calendar.MONTH,c2.get(Calendar.MONTH));
					minDayOfCurrentMonth.set(Calendar.DAY_OF_MONTH, c2.getMinimum(Calendar.DAY_OF_MONTH));

					list.addAll(findListBySameMonth(minDayOfCurrentMonth,c2,line));
					status = false;
				}
			}
		}
				

		
		return list;
	}
	
	
	//相同月份
	public List<MetroProductionControl> findListBySameMonth(Calendar c1,Calendar c2,String line){
		
		List<MetroProductionControl> list = new ArrayList<MetroProductionControl>();
		String hql = "from MetroProductionControl t where t.indicatorLine ='"+line+"'";
		int dayDiff = c2.get(Calendar.DAY_OF_MONTH)-c1.get(Calendar.DAY_OF_MONTH);	//日期差
		hql += " and t.ext1='"+c1.get(Calendar.YEAR)+"' and t.ext2 = '"+(c1.get(Calendar.MONTH)+1)+"'";
		List<MetroProductionControl> tempList = getHibernateTemplate().find(hql);
		
		if(tempList!=null && tempList.size()>0){		//有管控值
			if(dayDiff>0){			//日期不同
				for(int i=0; i<=dayDiff; i++){
					list.add(tempList.get(0));
				}
			}else{					//日期相同
				list.add(tempList.get(0));
			}
		}else{											//没有管控值
			if(dayDiff>0){			//日期不同
				for(int i=0; i<=dayDiff; i++){
					list.add(null);						
				}
			}else{					//日期相同
				list.add(null);
			}
		}
		return list;
	}

	@Override
	public List<MetroProductionControl> findMPCByDatesAndLine(String[] years,String[] months, String line) {
		String hql = "from MetroProductionControl t where t.indicatorLine ='"+line+"'";
		
		List<MetroProductionControl> mpcList = new ArrayList<MetroProductionControl>();
		List<MetroProductionControl> tempList;
		if(years!=null && months!=null && years.length==months.length && years.length>0){
			hql +=  " and ";
			for(int i=0; i<years.length; i++){
				String hql2 = hql + "t.ext1='"+years[i]+"' and t.ext2='"+months[i]+"'";
				tempList = getHibernateTemplate().find(hql2);
				if(tempList!=null && tempList.size()>0){
					mpcList.add(tempList.get(0));
				}else{
					mpcList.add(null);
				}
			}
		}
		
		return mpcList;
	}

	@Override
	public List<MetroProductionControl> findMPCByYearsAndLine(String[] years,String line) {
		List<MetroProductionControl> mpcList = null;
		String hql = "from MetroProductionControl t where t.indicatorLine ='"+line+"'";
		if(years!=null && years.length>0){
			mpcList = new ArrayList<MetroProductionControl>();
			for(int i=0; i<years.length; i++){
				if(years[i]!=null && !"".equals(years[i])){
					String hql2 = hql + " and t.ext1='"+years[i]+"' and t.ext2='0' order by cast(t.ext2 as int) DESC";
					List<MetroProductionControl> tempList = getHibernateTemplate().find(hql2);
					if(tempList!=null && tempList.size()>0){
						mpcList.add(tempList.get(0));
					}else{
						mpcList.add(null);
					}
				}else{
					mpcList.add(null);
				}
			}
		}
		return mpcList;
	}

	@Override
	public void deleteMPCById(String id) {
		String hql = "delete MetroProductionControl t where t.id='"+id+"'";
		Query query = getSession().createQuery(hql);
		query.executeUpdate();
	}
	
	
	
	
	
	
	
	
	
	
}
