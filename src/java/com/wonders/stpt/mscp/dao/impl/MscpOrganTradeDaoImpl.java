
package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpOrganTradeDao;
import com.wonders.stpt.mscp.entity.bo.MscpOrganTrade;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;



public class MscpOrganTradeDaoImpl extends AbstractHibernateDaoImpl<MscpOrganTrade> implements MscpOrganTradeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MscpOrganTrade> findMscpOrganTrade(String date) {
		String hql = "from MscpOrganTrade t where t.statDate='"+date+"' and t.removed='0' order by to_number(t.tradeCount) DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setMaxResults(10).list();
	}
	
}
