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

package com.wonders.stpt.core.login.dao.impl;

import java.util.List;

import com.wonders.stpt.core.login.dao.TuserDao;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.util.SHAEncode;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * Tuser实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */

public class TuserDaoImpl extends AbstractHibernateDaoImpl<Tuser> implements
		TuserDao {
	//
	@SuppressWarnings("unchecked")
	public List<Tuser> authenticationTuser(String loginName, String password){
		String hql = "from Tuser t where t.removed = '0' and t.loginName=? and t.password=?";
		List<Tuser> list =  getHibernateTemplate().find(hql, new Object[] {
				loginName, SHAEncode.encodeInternal(password)
            });
		//System.out.println("--------------------loginName"+loginName);
		//System.out.println("--------------------password"+SHAEncode.encodeInternal(password));
		//System.out.println("--------------------list"+list.size());
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Tuser> authenticationTuserByLoginName(String loginName){
		String hql = "from Tuser t where t.removed = '0' and t.loginName=?";
		List<Tuser> list =  getHibernateTemplate().find(hql, new Object[] {
				loginName
            });
		//System.out.println("--------------------loginName"+loginName);
		//System.out.println("--------------------list"+list.size());
		return list;
	}

}
