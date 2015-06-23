package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpAlarmStatDao;
import com.wonders.stpt.mscp.entity.bo.MscpAlarmStat;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class MscpAlarmStatDaoImpl extends AbstractHibernateDaoImpl<MscpAlarmStat> implements MscpAlarmStatDao{

	@Override
	public List<MscpAlarmStat> findMscpAlarmStat() {
		String hql ="from MscpAlarmStat t where t.removed='0' order by to_number(t.orderNum) ";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return this.getHibernateTemplate().find(hql);
	}
}
