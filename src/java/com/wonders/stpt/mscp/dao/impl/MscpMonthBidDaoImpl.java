package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpMonthBidDao;
import com.wonders.stpt.mscp.entity.bo.MscpMonthBid;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class MscpMonthBidDaoImpl extends AbstractHibernateDaoImpl<MscpMonthBid> implements MscpMonthBidDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MscpMonthBid> findMscpMonthBidByDate(String date) {
		String hql ="from MscpMonthBid t where t.statDate='"+date+"' and removed='0'" ;
		return getHibernateTemplate().find(hql);
	}
	
	@Override
	public List<Object[]> findMscpMonthBidMoneySumByMonth(){
		String sql = "select * from (select sum(to_number(bid_sum)) from mscp_month_bid t where removed = '0' "+
			" group by stat_date order by stat_date desc) where rownum < 7";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).list();
	}
	
	@Override
	public List<Object[]> findMscpMonth(){
		String sql = "select * from (select distinct t.stat_date from mscp_month_bid t order by stat_date desc) where rownum < 7";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).list();
	}
}
