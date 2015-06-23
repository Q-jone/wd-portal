

package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpSupplyBid;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpSupplyBidDao extends AbstractHibernateDao<MscpSupplyBid> {

	public List<MscpSupplyBid> findMscpSupplyBid(String date);
	
	public String findMscpSupplyBidCountByDate(String date);
}
