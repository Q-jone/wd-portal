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

import com.wonders.stpt.metroIndicator.entity.bo.MetroScale;
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

public interface MetroScaleService {
	public List<MetroScale> findLatestMetroScaleEvents(String date,String line);
	public List<MetroScale> findLastMetroScaleEvents(String date,String line);
	//查询最近有数据的一天
	public MetroScale findScaleWithData(String endDate,String line);
	
	//分页查询
	public Page findMetroScaleByPage(Integer pageNo, int pageSize,String lineNo);
	
	/**
	 * 根据日期查询数据
	 * @param date 日期
	 * @param line
	 * @return
	 */
	public MetroScale findMetroScaleByDate(String date,String line);
	
	/**
	 * 查询当前月或年的最后一天数据,按日期倒序
	 * @param yearAndMonth	格式:yyyy-MM或yyyy的String类型的日期
	 * @param line	线路编号
	 * @return	MetroScale
	 */
	public MetroScale findMetroScaleByLastDay(String date,String line);
	
	/**
	 * 查询某几个月的同一天的数据
	 * @param monthSpan	月数
	 * @param maxDate	最后一个月的日期
	 * @param line	线路编号
	 * @return	List<MetroScale>
	 */
	public List<MetroScale> findMetroScaleByMonth(int monthSpan,String maxDate,String line);
	
	/**
	 * 查询某几年的同一天的数据
	 * @param monthSpan	年数
	 * @param maxDate	最后一个月的日期
	 * @param line	线路编号
	 * @return	List<MetroScale>
	 */
	public List<MetroScale> findMetroScaleByYear(int yearSpan,String maxDate,String line);

	/**
	 * 查询有数据的第一天和最后一天
	 * @return
	 */
	public List<Object[]> findFirstAndLastDate();
	
	/**
	 * 查询最近有数据的一天
	 * @param date ('yyyy-MM-dd'格式)
	 * @param line 线路
	 * @return MetroScale
	 */
	public MetroScale findRecentlyScale(String date,String line);
}
