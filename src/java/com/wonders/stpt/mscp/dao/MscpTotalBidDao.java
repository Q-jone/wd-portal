
package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpTotalBid;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpTotalBidDao extends AbstractHibernateDao<MscpTotalBid> {

	public List<MscpTotalBid> findMscpTotalBid();
}
