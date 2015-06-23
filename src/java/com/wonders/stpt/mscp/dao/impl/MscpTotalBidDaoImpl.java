

package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import com.wonders.stpt.mscp.dao.MscpTotalBidDao;
import com.wonders.stpt.mscp.entity.bo.MscpTotalBid;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class MscpTotalBidDaoImpl extends AbstractHibernateDaoImpl<MscpTotalBid>
		implements MscpTotalBidDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<MscpTotalBid> findMscpTotalBid() {
		String hql="from MscpTotalBid t where t.removed='0'";
		return getHibernateTemplate().find(hql);
	}
	
	
}
