package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpAlarmStat;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface MscpAlarmStatDao extends AbstractHibernateDao<MscpAlarmStat>{
	public List<MscpAlarmStat> findMscpAlarmStat();
}
