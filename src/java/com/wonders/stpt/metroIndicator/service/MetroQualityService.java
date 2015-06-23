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

package com.wonders.stpt.metroIndicator.service;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.metroIndicator.entity.bo.MetroQuality;
import com.wonders.stpt.operationIndicator.entity.vo.QualityVo;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroQualityService {
	public List<MetroQuality> findLatestMetroQualityEvents(String date,String line);
	public List<MetroQuality> findLastMetroQualityEvents(String date,String line);
	
	//查询去年同期
	public QualityVo findSamePeriodLastYear(String date,String lineNo);

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
	 * 查询当前月或年的最后一天数据,按日期倒序
	 * @param yearAndMonth	格式:yyyy-MM或yyyy的String类型的日期
	 * @param line	线路编号
	 * @return	MetroQuality
	 */
	public MetroQuality findMetroQualityByLastDay(String date,String line);
	
	/**
	 * 查询某几个月的同一天的数据
	 * @param monthSpan	月数
	 * @param maxDate	最后一个月的日期
	 * @param line	线路编号
	 * @return	List<MetroQuality>
	 */
	public List<MetroQuality> findMetroQualityByMonth(int monthSpan,String maxDate,String line);
	
	
	/**
	 * 查询某几年的同一天的数据
	 * @param monthSpan	年数
	 * @param maxDate	最后一个月的日期
	 * @param line	线路编号
	 * @return	List<MetroQuality>
	 */
	public List<MetroQuality> findMetroQualityByYear(int yearSpan,String maxDate,String line);
	
	
	/**
	 * 查询有数据的第一天和最后一天\
	 * @return List<Object[]>
	 */
	public List<Object[]> findFirstAndLastDate();
	
	
	
}
