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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.indicatorControl.dao.MetroProductionControlDao;
import com.wonders.stpt.indicatorControl.entity.bo.MetroProductionControl;
import com.wonders.stpt.indicatorControl.service.MetroProductionControlService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroProductionControlServiceImpl implements MetroProductionControlService {
	private MetroProductionControlDao metroProductionControlDao;

	public void setMetroProductionControlDao(
			MetroProductionControlDao metroProductionControlDao) {
		this.metroProductionControlDao = metroProductionControlDao;
	}

	@Override
	public List<MetroProductionControl> findAllProductionControl() {
		
		return metroProductionControlDao.findAllProductionControl();
	}

	@Override
	public MetroProductionControl findProductionControlByLine(String line) {
		return metroProductionControlDao.findProductionControlByLine(line);
	}

	@Override
	public void saveProductionControl(MetroProductionControl mpc) {
		metroProductionControlDao.saveProductionControl(mpc);
	}

	@Override
	public MetroProductionControl findMetroProductionControlById(String id) {
		return metroProductionControlDao.findMetroProductionControlById(id);
	}

	@Override
	public void updateMetroProductionControl(MetroProductionControl mpc) {
		metroProductionControlDao.updateProductionControl(mpc);
	}

	@Override
	public void deleteProductionControl(String id) {
		metroProductionControlDao.deleteMPCById(id);
	}

	
	@Override
	public MetroProductionControl findProductionControlByYearAndLine(String year,String month, String line) {
		return metroProductionControlDao.findProductionControlByYearAndLine(year, month,line);
	}

	@Override
	public Page findMetroProductionControlByPage(Map<String, Object> filter,int pageNo, int pageSize) {
		Page page = metroProductionControlDao.findMetroProductionControlByPage(filter,pageNo, pageSize);
		return page;
	}

	@Override
	public MetroProductionControl findProductionControlByYearAndLine( String year, String line) {
		return metroProductionControlDao.findProductionControlByYearAndLine(year, line);
	}

	@Override
	public List<MetroProductionControl> findProductionControlByDatesAndLine(String startDate, String endDate, String line) {
		
		return metroProductionControlDao.findProductionControlByDatesAndLine(startDate, endDate, line);
	}

	@Override
	public List<MetroProductionControl> findMPCByDatesAndLine(String[] dates,String line) {
		Date temp ;
		Calendar calendar ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] years = new String[dates.length];
		String[] months = new String[dates.length];
		
		for(int i=0; i<dates.length; i++){
			try {
				temp = sdf.parse(dates[i]);
				calendar = Calendar.getInstance();
				calendar.setTime(temp);
				years[i] = calendar.get(Calendar.YEAR)+"";
				months[i] = (calendar.get(Calendar.MONTH)+1)+""; 
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return metroProductionControlDao.findMPCByDatesAndLine(years, months, line);
	}

	@Override
	public List<MetroProductionControl> findMPCByYearsAndLine(String[] years,String line) {
		
		return metroProductionControlDao.findMPCByYearsAndLine(years, line);
	}

	
	
}
