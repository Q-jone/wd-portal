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
import java.util.List;
import java.util.Map;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.wonders.stpt.metroIndicator.dao.MetroQualityDao;
import com.wonders.stpt.metroIndicator.entity.bo.MetroQuality;
import com.wonders.stpt.metroIndicator.service.MetroQualityService;
import com.wonders.stpt.operationIndicator.entity.vo.QualityVo;
import com.wonders.stpt.util.DateUtil;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroQualityServiceImpl implements MetroQualityService {
	private MetroQualityDao metroQualityDao;

	public MetroQualityDao getMetroQualityDao() {
		return metroQualityDao;
	}

	public void setMetroQualityDao(MetroQualityDao metroQualityDao) {
		this.metroQualityDao = metroQualityDao;
	}
	
	public List<MetroQuality> findLatestMetroQualityEvents(String date,String line){
		return this.metroQualityDao.findLatestMetroQualityEvents(date, line);
	}
	
	public List<MetroQuality> findLastMetroQualityEvents(String date,String line){
		String lastDate = "";
		if("".equals(date)){
			return null;
		}
		lastDate = DateUtil.getLastYearDay(date);
		return this.metroQualityDao.findLatestMetroQualityEvents(lastDate, line);
	}
	
	public QualityVo findSamePeriodLastYear(String date,String lineNo){
		String lastYearDate = DateUtil.getLastYearDay(date);
		QualityVo vo = new QualityVo();
		List<MetroQuality> list = metroQualityDao.findSamePeriodLastYear(lastYearDate, lineNo);
		if(list!=null && list.size()!=0){
			MetroQuality mq = list.get(0);
			vo = new QualityVo(mq);
		}
		return vo;
	}
	
	




	//查询最近有数据的一天
	@Override
	public MetroQuality findRecentlyQuality(String date,String line){
		return metroQualityDao.findRecentlyQuality(date, line);
	}
	
	//根据开始时间和结束时间查询天数
	@Override
	public List<MetroQuality> findQualityByDays(String beginDate,String endDate,String line){
		return metroQualityDao.findQualityByDays(beginDate, endDate, line);
	}
	
	//根据日期查询MetroProduction
	@Override
	public MetroQuality findMetroQualityByDate(String date,String line){
		return metroQualityDao.findMetroQualityByDate(date, line);
	}

	//分页查询
	@Override
	public Page findMetroQualitiesByPage(Integer pageNo, int pageSize,String lineNo){
		return metroQualityDao.findMetroQualitiesByPage(pageNo, pageSize, lineNo);
	}

	
	
	@Override
	public MetroQuality findMetroQualityByLastDay(String date, String line) {
		return metroQualityDao.findMetroQualityByLastDay(date, line);
	}

	@Override
	public List<MetroQuality> findMetroQualityByMonth(int monthSpan,String maxDate, String line) {
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
				return metroQualityDao.findMetroQualityByDates(dates, line);
			}else{
				return null;
			}
		} catch (ParseException e) {
			System.out.println("---------------日期转换错误-------------");
			return null;
		}
	}

	@Override
	public List<MetroQuality> findMetroQualityByYear(int yearSpan,String maxDate, String line) {
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
				return metroQualityDao.findMetroQualityByDates(dates, line);
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
		return metroQualityDao.findFirstAndLastDate();
	}

}
