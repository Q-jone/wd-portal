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

package com.wonders.stpt.indicatorControl.service.impl;

import java.util.List;

import com.wonders.stpt.indicatorControl.dao.MetroScaleControlDao;
import com.wonders.stpt.indicatorControl.entity.bo.MetroScaleControl;
import com.wonders.stpt.indicatorControl.service.MetroScaleControlService;

/**
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroScaleControlServiceImpl implements MetroScaleControlService {
	private MetroScaleControlDao metroScaleControlDao;

	public void setMetroScaleControlDao(MetroScaleControlDao metroScaleControlDao) {
		this.metroScaleControlDao = metroScaleControlDao;
	}

	@Override
	public List<MetroScaleControl> findAllScaleControl() {
		return metroScaleControlDao.findAllScaleControl();
	}

	
}
