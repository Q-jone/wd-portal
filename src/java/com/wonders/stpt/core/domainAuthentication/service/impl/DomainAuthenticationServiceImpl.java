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

package com.wonders.stpt.core.domainAuthentication.service.impl;

import java.util.Map;
import java.util.Date;


import com.wonders.stpt.core.domainAuthentication.dao.DomainAuthenticationDao;
import com.wonders.stpt.core.domainAuthentication.entity.bo.DomainAuthentication;
import com.wonders.stpt.core.domainAuthentication.service.DomainAuthenticationService;
import com.wondersgroup.framework.core.bo.Page;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-26
 * @author modify by $Author$
 * @since 1.0
 */

public class DomainAuthenticationServiceImpl implements
		DomainAuthenticationService {
	private DomainAuthenticationDao domainAuthenticationDao;

	public void setDomainAuthenticationDao(
			DomainAuthenticationDao domainAuthenticationDao) {
		this.domainAuthenticationDao = domainAuthenticationDao;
	}

	public void addDomainAuthentication(
			DomainAuthentication domainAuthentication) {
		domainAuthenticationDao.save(domainAuthentication);
	}

	public void deleteDomainAuthentication(
			DomainAuthentication domainAuthentication) {
		domainAuthenticationDao.delete(domainAuthentication);
	}

	public DomainAuthentication findDomainAuthenticationById(String id) {
		return domainAuthenticationDao.load(id);
	}

	public void updateDomainAuthentication(
			DomainAuthentication domainAuthentication) {
		domainAuthenticationDao.update(domainAuthentication);
	}

	public Page findDomainAuthenticationByPage(int pageNo, int pageSize) {
		Page page = domainAuthenticationDao.findAllWithPage(pageNo, pageSize);
		return page;
	}

	public Page findDomainAuthenticationByPage(Map<String, Object> filter,
			int pageNo, int pageSize) {
		return domainAuthenticationDao.findDomainAuthenticationByPage(filter,
				pageNo, pageSize);
	}
}
