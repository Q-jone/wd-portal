package com.wonders.stpt.constructionNotice.dao;

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


import java.util.List;
import java.util.Map;

import com.wonders.stpt.constructionNotice.entity.bo.ConstructionNotice;
import com.wonders.stpt.metroLine.entity.bo.MetroLine;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

/**
 * 实体名称
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public interface ConstructionNoticeDao extends
		AbstractHibernateDao<ConstructionNotice> {
	public Page findConstructionNoticeByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	/**
	 * @author ycl
	 * @describe 保存所有数据
	 * @param dataList
	 */
	public void saveAll(List<ConstructionNotice> dataList);
	
	/**
	 * @author ycl
	 * @describe 查询所有线路
	 * @return
	 */
	public List<MetroLine> findAllMetroLine();
	
	/**
	 * @author ycl
	 * @description 根据线路id查询
	 * @return int
	 */
	public int findCountByLineNo(String lineNo,String startDate,String endDate);
}
