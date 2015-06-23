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

package com.wonders.stpt.metroIndicator.dao;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.metroIndicator.entity.bo.MetroQuality;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroQualityDao extends AbstractHibernateDao<MetroQuality> {
	//按日期 线路 取得对应数据
	public List<MetroQuality> findLatestMetroQualityEvents(String date, String line);
	

	//查询去年同期
	public List<MetroQuality> findSamePeriodLastYear(String date,String lineNo);
	

	
	
	/**
	 * 查询最近有数据的一天
	 * @param date yyyy-MM-dd格式的字符串
	 * @param line 线路
	 * @return MetroQuality
	 */
	public MetroQuality findRecentlyQuality(String date,String line);
	
	/**
	 * 根据日期和线路查询
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param line 线路
	 * @return List<MetroQuality>
	 */
	public List<MetroQuality> findQualityByDays(String beginDate,String endDate,String line);
	
	/**
	 * 根据日期和线路查询
	 * @param date yyyy-MM-dd格式的字符串
	 * @param line 线路
	 * @return MetroQuality
	 */
	public MetroQuality findMetroQualityByDate(String date,String line);
	
	/**
	 * 分页查询
	 * @param pageNo 当前页码
	 * @param pageSize 每天的数量
	 * @param lineNo 线路
	 * @return Page
	 */
	public Page findMetroQualitiesByPage(Integer pageNo, int pageSize,String lineNo);
	
	
	/**
	 * 查询当前月的最后一天数据,按日期倒序
	 * @param yearAndMonth	格式:yyyy-MM的String类型的日期
	 * @param line	线路编号
	 * @return	MetroQuality
	 */
	public MetroQuality findMetroQualityByLastDay(String date,String line);
	
	/**
	 * 根据日期查询多条数据
	 * @param dates String类型的"yyyy-MM-dd"格式的日期
	 * @param line 线路编号
	 * @return List<MetroQuality>
	 */
	public List<MetroQuality> findMetroQualityByDates(String[] dates,String line);
	
	/**
	 * 查询有数据的第一天和最后一天
	 */
	public List<Object[]> findFirstAndLastDate();
}
