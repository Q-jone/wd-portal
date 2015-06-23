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

import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public interface MetroQualityControlService {
	
	/**
	 * 查询所有
	 */
	public List<MetroQualityControl> findAllQualityControl();
	
	/**
	 * 保存
	 * @param mqc
	 */
	public void saveQualityControl(MetroQualityControl mqc);
	
	/**
	 * 根据id查询
	 * @param id 主键 
	 * @return MetroQualityControl
	 */
	public MetroQualityControl findMetroQualityControlById(String id);
	
	/**
	 * 更新
	 * @param mqc
	 */
	public void updateMetroControl(MetroQualityControl mqc);
	
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteQualityControlById(String id);

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
	 * 根据线路和日期查询
	 * @param line 线路
	 * @param date 日期数组
	 * @return 管控list
	 */
	public List<MetroQualityControl> findMQCByLineAndDates(String line,List<String> date);
	
	/**
	 * 年视图(查询数据)
	 * @param line 线路
	 * @param date 日期数组
	 * @return 管控list
	 */
	public List<MetroQualityControl> findMQCByLineAndYear(String line,List<String> date);
	
}
