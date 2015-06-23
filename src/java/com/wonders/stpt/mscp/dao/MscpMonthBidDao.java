
package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpMonthBid;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;


public interface MscpMonthBidDao extends AbstractHibernateDao<MscpMonthBid> {

	public List<MscpMonthBid> findMscpMonthBidByDate(String date);
	
	public List<Object[]> findMscpMonthBidMoneySumByMonth();
	
	public List<Object[]> findMscpMonth();
}
