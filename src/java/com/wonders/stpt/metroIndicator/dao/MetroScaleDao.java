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
import com.wonders.stpt.metroIndicator.entity.bo.MetroScale;
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

public interface MetroScaleDao extends AbstractHibernateDao<MetroScale> {
	//按日期 线路 取得对应数据
	public List<MetroScale> findLatestMetroScaleEvents(String date, String line);

	//查询最近有数据的一天
	public List<MetroScale> findScaleWithData(String endDate,String line);
	
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
	 * 查询当前月的最后一天数据,按日期倒序
	 * @param yearAndMonth	格式:yyyy-MM的String类型的日期
	 * @param line	线路编号
	 * @return	MetroScale
	 */
	public MetroScale findMetroScaleByLastDay(String date,String line);
	
	/**
	 * 根据日期查询多条数据
	 * @param dates String类型的"yyyy-MM-dd"格式的日期
	 * @param line 线路编号
	 * @return List<MetroScale>
	 */
	public List<MetroScale> findMetroScaleByDates(String[] dates,String line);
	
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
