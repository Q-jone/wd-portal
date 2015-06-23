package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpExpertStat;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface MscpExpertStatDao extends AbstractHibernateDao<MscpExpertStat>{
	public List<MscpExpertStat> findMscpExpertStat();
}
