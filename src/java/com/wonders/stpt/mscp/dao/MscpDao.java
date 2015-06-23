
package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpOrganTrade;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpDao extends AbstractHibernateDao {
	
	public List<Object> findMscpInfo();
}
