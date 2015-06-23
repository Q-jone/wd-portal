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
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;


import com.wonders.stpt.metroIndicator.dao.MetroScaleDao;
import com.wonders.stpt.metroIndicator.entity.bo.MetroScale;
import com.wonders.stpt.metroIndicator.service.MetroScaleService;
import com.wonders.stpt.util.DateUtil;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroScaleServiceImpl implements MetroScaleService {
	private MetroScaleDao metroScaleDao;

	public MetroScaleDao getMetroScaleDao() {
		return metroScaleDao;
	}

	public void setMetroScaleDao(MetroScaleDao metroScaleDao) {
		this.metroScaleDao = metroScaleDao;
	}
	

	public List<MetroScale> findLatestMetroScaleEvents(String date,String line){
		return this.metroScaleDao.findLatestMetroScaleEvents(date, line);
	}
	
	public List<MetroScale> findLastMetroScaleEvents(String date,String line){
		String lastDate = "";
		if("".equals(date)){
			return null;
		}
		lastDate = DateUtil.getLastYearDay(date);
		return this.metroScaleDao.findLatestMetroScaleEvents(lastDate, line);
	}
	
	public MetroScale findScaleWithData(String endDate,String line){
		List<MetroScale> list = metroScaleDao.findScaleWithData(endDate, line);
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null; 
	}
	
	public Page findMetroScaleByPage(Integer pageNo, int pageSize,String lineNo){
		return metroScaleDao.findMetroScaleByPage(pageNo, pageSize, lineNo);
	}

	@Override
	public MetroScale findMetroScaleByLastDay(String date, String line) {
		return metroScaleDao.findMetroScaleByLastDay(date, line);
	}

	@Override
	public MetroScale findMetroScaleByDate(String date, String line) {
		return metroScaleDao.findMetroScaleByDate(date, line);
	}
	
	@Override
	public List<MetroScale> findMetroScaleByMonth(int monthSpan,String maxDate, String line) {
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
				return metroScaleDao.findMetroScaleByDates(dates, line);
			}else{
				return null;
			}
		} catch (ParseException e) {
			System.out.println("---------------日期转换错误-------------");
			return null;
		}
	}

	@Override
	public List<MetroScale> findMetroScaleByYear(int yearSpan, String maxDate,String line) {
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
				return metroScaleDao.findMetroScaleByDates(dates, line);
			}else {
				return null;
			}
		} catch (ParseException e) {
			System.out.println("----------------日期转换错误----------------");
			return null;
		}
	}

	@Override
	public List<Object[]> findFirstAndLastDate() {
		return metroScaleDao.findFirstAndLastDate();
	}

	@Override
	public MetroScale findRecentlyScale(String date, String line) {
		return metroScaleDao.findRecentlyScale(date, line);
	}
	
}
