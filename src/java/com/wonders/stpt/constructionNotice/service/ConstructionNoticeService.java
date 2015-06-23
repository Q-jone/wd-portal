package com.wonders.stpt.constructionNotice.service;

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

import java.io.File;

import java.util.List;
import java.util.Map;


import com.wonders.stpt.constructionNotice.entity.bo.ConstructionNotice;
import com.wonders.stpt.metroLine.entity.bo.MetroLine;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public interface ConstructionNoticeService {
	/**
	 * 删除实体对象
	 * 
	 * @param ConstructionNotice
	 */
	public void deleteConstructionNotice(
			ConstructionNotice ConstructionNotice);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public ConstructionNotice findConstructionNoticeById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param ConstructionNotice
	 */
	public void addConstructionNotice(ConstructionNotice ConstructionNotice);

	/**
	 * 更新数据到数据库
	 * 
	 * @param ConstructionNotice
	 *            实体
	 */
	public void updateConstructionNotice(
			ConstructionNotice ConstructionNotice);

	/**
	 * 根据分页参数进行分页查询.
	 * 
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findConstructionNoticeByPage(int pageNo, int pageSize);

	/**
	 * 根据Map中过滤条件和分页参数进行分页查询.
	 * 
	 * @param filter
	 *            过滤条件<propertyName,properyValue>
	 * @param pageNo
	 *            当前页码
	 * @param pageSize
	 *            每页显示记录数.
	 * @return
	 */
	public Page findConstructionNoticeByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	/**
	 * @author ycl
	 * @describe 复制文件到本地
	 * @param src 源地址
	 * @param dst 目标地址
	 * 
	 */
	public void copy(File src, File dst);

	
	/**
	 * @author ycl
	 * @describe 查询所有地铁线路
	 * @return List<MetroLine>
	 */
	public List<MetroLine> findAllMetroLine(); 
	
	/**
	 * @author ycl
	 * @describe 保存所有数据
	 * @param dataList
	 */
	public void saveAll(List<ConstructionNotice> dataList);
	
	/**
	 * @author ycl
	 * @description 根据线路id查询
	 * @return int
	 */
	public int findCountByLineNo(String lineNo,String startDate,String endDate);
	
}
