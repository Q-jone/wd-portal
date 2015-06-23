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

import com.wonders.stpt.metroIndicator.entity.bo.MetroProduction;
import com.wonders.stpt.operationIndicator.entity.vo.ProductionVo;
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

public interface MetroProductionService {
	public List<MetroProduction> findLatestMetroProductionEvents(String date,String line);
	public List<MetroProduction> findLastMetroProductionEvents(String date,String line);
	//查询去年同期
	public MetroProduction findSamePeriodLastYear(String date,String line);
	
	//查询总数
	public Integer findTotalMetroProductionByLineNo(String lineNo);
	
	/**
	 * 分页查询数据
	 * @param pageNo 当前页数
	 * @param pageSize	每页的数量
	 * @param lineNo	线路
	 * @return	Page
	 */
	public Page findMetroProductionByPage(Integer pageNo, int pageSize,String lineNo);
	
	
	
	/**
	 * 查询最近有数据的一天
	 * @param date ('yyyy-MM-dd'格式)
	 * @param line 线路
	 * @return MetroProduction
	 */
	public MetroProduction findRecentlyProduction(String date,String line);
	
	/**
	 * 根据时间和线路查询总天数
	 * @param beginDate 开始时间
	 * @param endDate 结束时间
	 * @param line	线路
	 * @return List<MetroProduction>
	 */
	public List<MetroProduction> findProductionByDays(String beginDate,String endDate,String line);
	
	/**
	 * 根据日期和线路查询
	 * @param date yyyy-MM-dd格式
	 * @param line 线路
	 * @return MetroProduction
	 */
	public MetroProduction findMetroProductionByDate(String date,String line);
	
	
	/**
	 * 查询当前月或年的最后一天数据,按日期倒序
	 * @param yearAndMonth	格式:yyyy-MM或yyyy的String类型的日期
	 * @param line	线路编号
	 * @return	MetroProduction
	 */
	public MetroProduction findMetroProductionByLastDay(String date,String line);
	
	/**
	 * 查询某几个月的同一天的数据
	 * @param monthSpan	月数
	 * @param maxDate	最后一个月的日期
	 * @param line	线路编号
	 * @return	List<MetroProduction>
	 */
	public List<MetroProduction> findMetroProductionByMonth(int monthSpan,String maxDate,String line);
	
	
	/**
	 * 查询某几年的同一天的数据
	 * @param monthSpan	年数
	 * @param maxDate	最后一个月的日期
	 * @param line	线路编号
	 * @return	List<MetroProduction>
	 */
	public List<MetroProduction> findMetroProductionByYear(int yearSpan,String maxDate,String line);
	
	/**
	 * 查询production表中最小天数和最大天数
	 * @return 最小天数，最大天数
	 */
	public List<Object[]> findFirstAndLastDate();
	
	
	
}
