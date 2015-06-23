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

package com.wonders.stpt.organTree.dao.impl;

import java.util.List;


import com.wonders.stpt.organTree.dao.TorganRelationDao;
import com.wonders.stpt.organTree.entity.bo.TorganRelation;

import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

/**
 * TorganRelation实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-5-7
 * @author modify by $Author$
 * @since 1.0
 */

public class TorganRelationDaoImpl extends
		AbstractHibernateDaoImpl<TorganRelation> implements TorganRelationDao {
	@SuppressWarnings("unchecked")
	public List<TorganRelation> findOrganRelation(){
		String hql = "from TorganRelation t where t.removed = '0'";
		List<TorganRelation> list =  getHibernateTemplate().find(hql);
		return list;
	}
}
