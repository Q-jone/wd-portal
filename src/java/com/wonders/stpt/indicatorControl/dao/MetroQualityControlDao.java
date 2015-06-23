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

package com.wonders.stpt.indicatorControl.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroQualityControlDao extends AbstractHibernateDao<MetroQualityControl> {

	/**
	 * 查询所有
	 */
	public List<MetroQualityControl> findAllQualityControl();
	
	/**
	 * 根据id查询
	 * @param id 主键
	 * @return MetroQualityControl
	 */
	public MetroQualityControl findMetroQualityControlById(String id);
	
	/**
	 * 根据line查询
	 * @param line 线路
	 * @return MetroQualityControl
	 */
	public MetroQualityControl findQualityControlByLine(String line);
	
	/**
	 * 分页查询
	 * @param filter 过滤条件
	 * @param pageNo 页码
	 * @param pageSize 每页的大小
	 * @return
	 */
	public Page findMetroQualityControlByPage(Map<String, Object> filter,int pageNo, int pageSize);
	
	/**
	 * 根据线路和日期查询
	 * @param line 线路
	 * @param date 日期
	 * @return MetroQualityControl
	 */
	public MetroQualityControl findMetroQualityControlByLineAndDate(String line,String date);
	
	/**
	 * 根据时间和路线查询
	 * @param line	线路
	 * @param year	年份
	 * @param month	月份
	 * @return 管控list
	 */
	public MetroQualityControl findMQCByLineAndDate(String line,String year,String month);
	
	/**
	 * 根据线路、年份、月份查询
	 * @param line	线路
	 * @param years	年份
	 * @param months	月份
	 * @return	管控list
	 */
	public List<MetroQualityControl> findMQCByLineAndYearsAndMonths(String line,List<String> years,List<String> months);
	
}
