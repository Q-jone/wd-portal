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

package com.wonders.stpt.core.login.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.stpt.core.login.dao.TuserTokenDao;
import com.wonders.stpt.core.login.entity.bo.TuserToken;
import com.wonders.stpt.core.login.service.TuserTokenService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-28
 * @author modify by $Author$
 * @since 1.0
 */

public class TuserTokenServiceImpl implements TuserTokenService {
	private TuserTokenDao tuserTokenDao;

	public void setTuserTokenDao(TuserTokenDao tuserTokenDao) {
		this.tuserTokenDao = tuserTokenDao;
	}

	public void addTuserToken(TuserToken tuserToken) {
		tuserTokenDao.save(tuserToken);
	}

	public void deleteTuserToken(TuserToken tuserToken) {
		tuserTokenDao.delete(tuserToken);
	}

	public TuserToken findTuserTokenById(String id) {
		return tuserTokenDao.load(id);
	}

	public void updateTuserToken(TuserToken tuserToken) {
		tuserTokenDao.update(tuserToken);
	}

	public List<TuserToken> findTuserTokenByIdRemoved(String id){
		return tuserTokenDao.findTuserTokenByIdRemoved(id);
	}

}
