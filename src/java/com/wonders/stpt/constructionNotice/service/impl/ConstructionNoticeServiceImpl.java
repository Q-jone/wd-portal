package com.wonders.stpt.constructionNotice.service.impl;

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


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;


import com.wonders.stpt.constructionNotice.dao.ConstructionNoticeDao;
import com.wonders.stpt.constructionNotice.entity.bo.ConstructionNotice;
import com.wonders.stpt.constructionNotice.service.ConstructionNoticeService;
import com.wonders.stpt.metroLine.entity.bo.MetroLine;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class ConstructionNoticeServiceImpl implements
		ConstructionNoticeService {
	private ConstructionNoticeDao constructionNoticeDao;
	
	private static final int BUFFER_SIZE = 20 * 1024; // 20K

	public ConstructionNoticeDao getConstructionNoticeDao() {
		return constructionNoticeDao;
	}

	public void setConstructionNoticeDao(ConstructionNoticeDao constructionNoticeDao) {
		this.constructionNoticeDao = constructionNoticeDao;
	}


	public void addConstructionNotice(ConstructionNotice ConstructionNotice) {
		constructionNoticeDao.save(ConstructionNotice);
	}

	public void deleteConstructionNotice(
			ConstructionNotice ConstructionNotice) {
		constructionNoticeDao.delete(ConstructionNotice);
	}

	public ConstructionNotice findConstructionNoticeById(String id) {
		return constructionNoticeDao.load(id);
	}

	public void updateConstructionNotice(
			ConstructionNotice ConstructionNotice) {
		constructionNoticeDao.update(ConstructionNotice);
	}

	public Page findConstructionNoticeByPage(int pageNo, int pageSize) {
		Page page = constructionNoticeDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findConstructionNoticeByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return constructionNoticeDao.findConstructionNoticeByPage(filter,
				pageNo, pageSize);
	}
	
	/**
	 * @author ycl
	 * @describe 复制文件到本地
	 * @param src 源地址
	 * @param dst 目标地址
	 * 
	 */
	public void copy(File src, File dst) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(src),BUFFER_SIZE);
				out = new BufferedOutputStream(new FileOutputStream(dst),BUFFER_SIZE);
				
				byte[] buffer = new byte[BUFFER_SIZE];
				while (in.read(buffer) > 0) {
					out.write(buffer);
				}
			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * @author ycl
	 * @describe 查询所有地铁线路
	 * @return List<MetroLine>
	 */
	public List<MetroLine> findAllMetroLine(){
		return constructionNoticeDao.findAllMetroLine();
	}
	
	/**
	 * @author ycl
	 * @describe 保存所有数据
	 * @param dataList
	 */
	public void saveAll(List<ConstructionNotice> dataList){
		constructionNoticeDao.saveAll(dataList);
	}
	
	public int findCountByLineNo(String lineNo,String startDate,String endDate){
		return constructionNoticeDao.findCountByLineNo(lineNo,startDate,endDate);
	}
	
}
