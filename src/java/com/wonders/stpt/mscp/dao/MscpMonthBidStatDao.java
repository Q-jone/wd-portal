package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpMonthBidStat;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface MscpMonthBidStatDao extends AbstractHibernateDao<MscpMonthBidStat>{
	public List<MscpMonthBidStat> findMscpMonthBidStat();
	public String findMscpMonthBidStatSum();
	public List<Object[]> findMscpMonthBidStatByMonth();
}
