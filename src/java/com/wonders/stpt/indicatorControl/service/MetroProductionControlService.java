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

package com.wonders.stpt.indicatorControl.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.indicatorControl.entity.bo.MetroProductionControl;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroProductionControlService {
	
	/**
	 * 查询所有
	 * @return List<MetroProductionControl>
	 */
	public List<MetroProductionControl> findAllProductionControl();
	
	/**
	 * 根据线路查询
	 * @param 线路编号
	 * @return MetroProductionControl
	 */
	public MetroProductionControl findProductionControlByLine(String line);
	
	/**
	 * 保存
	 * @param mpc
	 */
	public void saveProductionControl(MetroProductionControl mpc);
	
	/**
	 * 根据主键查询
	 * @param id 主键
	 * @return MetroProductionControl
	 */
	public MetroProductionControl findMetroProductionControlById(String id);
	
	/**
	 * 保存更新
	 * @param mpc
	 */
	public void updateMetroProductionControl(MetroProductionControl mpc);
	
	/**
	 * 根据主键删除
	 * @param id 主键
	 */
	public void deleteProductionControl(String id);

	/**
	 * 根据日期和线路查询
	 * @param year 年份
	 * @param month 月份
	 * @param line 线路
	 */
	public MetroProductionControl findProductionControlByYearAndLine(String year,String month, String line);
	
	/**
	 * 根据日期和线路查询
	 * @param year 年份
	 * @param line 线路
	 */
	public MetroProductionControl findProductionControlByYearAndLine(String year,String line);
	
	/**
	 * 根据日期和线路查询
	 * @param year 年份
	 * @param month 月份
	 * @param line 线路
	 */
	public List<MetroProductionControl> findProductionControlByDatesAndLine(String startDate,String endDate, String line);
	
	/**
	 * 分页查询
	 * @param pageNo 页码
	 * @param pageSize 大小
	 * @return Page
	 */
	public Page findMetroProductionControlByPage(Map<String, Object> filter, int pageNo, int pageSize);
	
	/**
	 * 根据给定的日期和线路查询管控值
	 * @param dates	日期数组(yyyy-MM-dd)
	 * @param line	线路
	 * @return	管控值list
	 */
	public List<MetroProductionControl> findMPCByDatesAndLine(String[] dates,String line);
	
	/**
	 * 根据给定的年份和线路查询管控值
	 * @param years	年份(yyyy)
	 * @param line	线路
	 * @return	管控值list
	 */
	public List<MetroProductionControl> findMPCByYearsAndLine(String[] years,String line);
	
}
