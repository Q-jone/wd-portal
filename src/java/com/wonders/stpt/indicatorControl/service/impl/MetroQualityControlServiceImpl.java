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

package com.wonders.stpt.indicatorControl.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.indicatorControl.dao.MetroQualityControlDao;
import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wonders.stpt.indicatorControl.service.MetroQualityControlService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroQualityControlServiceImpl implements
		MetroQualityControlService {
	private MetroQualityControlDao metroQualityControlDao;

	public MetroQualityControlDao getMetroQualityControlDao() {
		return metroQualityControlDao;
	}

	public void setMetroQualityControlDao(
			MetroQualityControlDao metroQualityControlDao) {
		this.metroQualityControlDao = metroQualityControlDao;
	}

	@Override
	public List<MetroQualityControl> findAllQualityControl() {
		return metroQualityControlDao.findAllQualityControl();
	}

	@Override
	public void saveQualityControl(MetroQualityControl mqc) {
		metroQualityControlDao.save(mqc);
	}

	@Override
	public MetroQualityControl findMetroQualityControlById(String id) {
		
		return metroQualityControlDao.findMetroQualityControlById(id);
	}
	
	
	@Override
	public void deleteQualityControlById(String id) {
		metroQualityControlDao.deleteById(id);
	}

	@Override
	public void updateMetroControl(MetroQualityControl mqc) {
		metroQualityControlDao.update(mqc);
	}

	
	@Override
	public MetroQualityControl findQualityControlByLine(String line) {
		return metroQualityControlDao.findQualityControlByLine(line);
	}

	@Override
	public Page findMetroQualityControlByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		return metroQualityControlDao.findMetroQualityControlByPage(filter, pageNo, pageSize);
	}

	@Override
	public MetroQualityControl findMetroQualityControlByLineAndDate(String line, String date) {
		return metroQualityControlDao.findMetroQualityControlByLineAndDate(line, date);
	}

	@Override
	public MetroQualityControl findMQCByLineAndDate(String line, String year,String month) {
		
		return metroQualityControlDao.findMQCByLineAndDate(line, year, month);
	}

	@Override
	public List<MetroQualityControl> findMQCByLineAndDates(String line,List<String> dates) {
		List<MetroQualityControl> mqcList;
		List<String> years;
		List<String> months;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dates!=null && dates.size()>0){
			years = new ArrayList<String>(dates.size());
			months = new ArrayList<String>(dates.size());
			Date tempDate;
			Calendar tempCalendar = Calendar.getInstance();
			for(int i=0; i<dates.size(); i++){
				try {
					tempDate = sdf.parse(dates.get(i));
					tempCalendar.setTime(tempDate);
					years.add(tempCalendar.get(Calendar.YEAR)+"");
					months.add((tempCalendar.get(Calendar.MONTH)+1)+"");
				} catch (ParseException e) {
					years.add(null);
					months.add(null);
				}
			}
			mqcList = metroQualityControlDao.findMQCByLineAndYearsAndMonths(line, years,months);
			return mqcList;
		}else{
			return null;
		}
	}

	@Override
	public List<MetroQualityControl> findMQCByLineAndYear(String line,List<String> dates) {

		List<MetroQualityControl> mqcList;
		List<String> years;
		List<String> months;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(dates!=null && dates.size()>0){
			years = new ArrayList<String>(dates.size());
			months = new ArrayList<String>(dates.size());
			Date tempDate;
			Calendar tempCalendar = Calendar.getInstance();
			for(int i=0; i<dates.size(); i++){
				try {
					tempDate = sdf.parse(dates.get(i));
					tempCalendar.setTime(tempDate);
					years.add(tempCalendar.get(Calendar.YEAR)+"");
					months.add("0");
				} catch (ParseException e) {
					years.add(null);
					months.add("0");
				}
			}
			mqcList = metroQualityControlDao.findMQCByLineAndYearsAndMonths(line, years,months);
			return mqcList;
		}else{
			return null;
		}
	}

	
	
	

	
}
