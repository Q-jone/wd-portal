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

import com.wonders.stpt.core.login.dao.TuserDao;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.service.TuserService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */

public class TuserServiceImpl implements TuserService {
	private TuserDao tuserDao;

	public TuserDao getTuserDao() {
		return tuserDao;
	}

	public void setTuserDao(TuserDao tuserDao) {
		this.tuserDao = tuserDao;
	}

	public void addTuser(Tuser tuser) {
		tuserDao.save(tuser);
	}

	public void deleteTuser(Tuser tuser) {
		tuserDao.delete(tuser);
	}

	public Tuser findTuserById(Long id) {
		return tuserDao.load(id);
	}

	public void updateTuser(Tuser tuser) {
		tuserDao.update(tuser);
	}

	public List<Tuser> authenticationTuser(String loginName, String password){
		return tuserDao.authenticationTuser(loginName, password);
	}
	
	public List<Tuser> authenticationTuserByLoginName(String loginName){
		return tuserDao.authenticationTuserByLoginName(loginName);
	}
}
