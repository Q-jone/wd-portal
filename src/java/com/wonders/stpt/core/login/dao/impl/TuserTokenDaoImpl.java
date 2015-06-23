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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.login.dao.TuserTokenDao;
import com.wonders.stpt.core.login.entity.bo.Tuser;
import com.wonders.stpt.core.login.entity.bo.TuserToken;
import com.wonders.stpt.core.login.util.SHAEncode;
import com.wondersgroup.framework.core.bo.Page;
import com.wondersgroup.framework.core.bo.hibernate.HqlParameter;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TuserToken实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-28
 * @author modify by $Author$
 * @since 1.0
 */

public class TuserTokenDaoImpl extends AbstractHibernateDaoImpl<TuserToken>
		implements TuserTokenDao {
	public List<TuserToken> findTuserTokenByIdRemoved(String id){
		String hql = "from TuserToken t where t.removed = '0' and t.id=?";
		List<TuserToken> list =  getHibernateTemplate().find(hql, new Object[] {id});
		return list;
	}
}
