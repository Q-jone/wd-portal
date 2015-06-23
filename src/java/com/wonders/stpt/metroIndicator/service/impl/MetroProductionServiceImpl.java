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

package com.wonders.stpt.metroIndicator.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.wonders.stpt.metroIndicator.dao.MetroProductionDao;
import com.wonders.stpt.metroIndicator.entity.bo.MetroProduction;
import com.wonders.stpt.metroIndicator.service.MetroProductionService;
import com.wonders.stpt.operationIndicator.entity.vo.ProductionVo;
import com.wonders.stpt.util.DateUtil;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroProductionServiceImpl implements MetroProductionService {
	private MetroProductionDao metroProductionDao;

	public MetroProductionDao getMetroProductionDao() {
		return metroProductionDao;
	}

	public void setMetroProductionDao(MetroProductionDao metroProductionDao) {
		this.metroProductionDao = metroProductionDao;
	}

	public List<MetroProduction> findLatestMetroProductionEvents(String date,String line){
		return this.metroProductionDao.findLatestMetroProductionEvents(date, line);
	}
	
	public List<MetroProduction> findLastMetroProductionEvents(String date,String line){
		//取得去年数据
		String lastDate = "";
		if("".equals(date)){
			return null;
		}
		lastDate = DateUtil.getLastYearDay(date);
		
		return this.metroProductionDao.findLatestMetroProductionEvents(lastDate, line);
	}
	

	//查询7天的线路数据
	@SuppressWarnings("deprecation")
	public List<ProductionVo> findSevenDaysProduction(String endDate,String line){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<ProductionVo> voList = new ArrayList<ProductionVo>();
		
		if(endDate==null || "".equals(endDate)){
			endDate = sdf.format(new Date());
		}
		try {
			
			//查询最近有数据的一天
			List<MetroProduction> dataList = metroProductionDao.findProductionWithData(endDate, line); 
			if(dataList!=null && dataList.size()!=0){
				endDate = dataList.get(0).getIndicatorDate();
			}
			Date start = sdf.parse(endDate);	//开始日期	
			start.setDate(start.getDate()-5);
			List<MetroProduction> productList = metroProductionDao.findSevenDaysProduction(sdf.format(start), endDate, line);
			if(productList!=null && productList.size()!=0){
				for(int i=0; i<productList.size(); i++){
					ProductionVo vo = new ProductionVo(productList.get(i));
					voList.add(vo);
				}
			}
			return voList;
		} catch (ParseException e) {
			return null;
		}
	}
	
	//查询去年同期
	@SuppressWarnings("deprecation")
	public MetroProduction findSamePeriodLastYear(String now,String line){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<MetroProduction> dataList = metroProductionDao.findProductionWithData(now, line); 
		if(dataList!=null && dataList.size()!=0){
			now = dataList.get(0).getIndicatorDate();
		}
		try {
			Date lastyear = sdf.parse(now);		//去年的今天
			lastyear.setYear(lastyear.getYear()-1);
			List<MetroProduction> list = metroProductionDao.findSamePeriodLastYear(sdf.format(lastyear),line);
			if(list!=null && list.size()>0) return list.get(0); 
			return null;
		} catch (ParseException e) {
			return null;
		}
		
	}
	
	//查询总数
	public Integer findTotalMetroProductionByLineNo(String lineNo){
		return metroProductionDao.findTotalMetroProductionByLineNo(lineNo);
	}
	
	@Override
	public Page findMetroProductionByPage(Integer pageNo, int pageSize,String lineNo){
		return metroProductionDao.findMetroProductionByPage(pageNo, pageSize,lineNo);
	}
	
	@Override
	public MetroProduction findRecentlyProduction(String date,String line){
		return metroProductionDao.findRecentlyProduction(date, line);
	}
	
	@Override
	public List<MetroProduction> findProductionByDays(String beginDate,String endDate,String line){
		return metroProductionDao.findProductionByDays(beginDate, endDate, line);
	}
	
	@Override
	public MetroProduction findMetroProductionByDate(String date,String line){
		return metroProductionDao.findMetroProductionByDate(date, line);
	}

	@Override
	public MetroProduction findMetroProductionByLastDay(String date,String line) {
		return metroProductionDao.findMetroProductionByLastDay(date, line);
	}

	@Override
	public List<MetroProduction> findMetroProductionByMonth(int monthSpan,String maxDate, String line) {
		String[] dates;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(maxDate);
			if(monthSpan>0){
				dates = new String[monthSpan];
				for(int i=0; i<monthSpan; i++){
					Date newdate = DateUtils.addMonths(date, -i);
					if(i!=0)
						newdate = DateUtil.getLastDayOfTheMonth(newdate);
					dates[i] = sdf.format(newdate); 
				}
				return metroProductionDao.findMetroProductionByDates(dates, line);
			}else{
				return null;
			}
		} catch (ParseException e) {
			System.out.println("---------------日期转换错误-------------");
			return null;
		}
	}

	@Override
	public List<MetroProduction> findMetroProductionByYear(int yearSpan,String maxDate, String line) {
		String[] dates;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(maxDate);
			if(yearSpan>0){
				dates = new String[yearSpan];
				for(int i=0; i<yearSpan; i++){
					Date newDate = DateUtils.addYears(date, -i);
					dates[i] = sdf.format(newDate);
				}
				return metroProductionDao.findMetroProductionByDates(dates, line);
			}else {
				return null;
			}
		} catch (ParseException e) {
			System.out.println("----------------日期转换错误----------------");
			return null;
		}
	}
	
	@Override
	public List<Object[]> findFirstAndLastDate(){
		return metroProductionDao.findFirstAndLastDate();
	}

	
}
