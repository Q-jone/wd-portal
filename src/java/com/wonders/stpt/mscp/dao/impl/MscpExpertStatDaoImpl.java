package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpExpertStatDao;
import com.wonders.stpt.mscp.entity.bo.MscpExpertStat;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class MscpExpertStatDaoImpl extends AbstractHibernateDaoImpl<MscpExpertStat> implements MscpExpertStatDao{

	@Override
	public List<MscpExpertStat> findMscpExpertStat() {
		String hql ="from MscpExpertStat t where t.removed='0' order by to_number(t.orderNum) ";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return this.getHibernateTemplate().find(hql);
	}

}
