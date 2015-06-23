
package com.wonders.stpt.mscp.dao;

import com.wonders.stpt.mscp.entity.bo.MscpProfile;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpProfileDao extends AbstractHibernateDao<MscpProfile> {
	
	/**
	 * 查询当前月
	 */
	public MscpProfile findMscpProfileByDate(String date);
}
