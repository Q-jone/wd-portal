package com.wonders.stpt.metroLine.service.impl;

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

import com.wonders.stpt.metroLine.dao.MetroLineDao;
import com.wonders.stpt.metroLine.entity.bo.MetroLine;
import com.wonders.stpt.metroLine.service.MetroLineService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroLineServiceImpl implements MetroLineService {
	private MetroLineDao metroLineDao;



	public MetroLineDao getMetroLineDao() {
		return metroLineDao;
	}

	public void setMetroLineDao(MetroLineDao metroLineDao) {
		this.metroLineDao = metroLineDao;
	}

	public void addMetroLine(MetroLine metroLine) {
		metroLineDao.save(metroLine);
	}

	public void deleteMetroLine(MetroLine metroLine) {
		metroLineDao.delete(metroLine);
	}

	public MetroLine findMetroLineById(String id) {
		return metroLineDao.load(id);
	}

	public void updateMetroLine(MetroLine metroLine) {
		metroLineDao.update(metroLine);
	}

}
