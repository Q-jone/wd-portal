package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpMonthBidStatDao;
import com.wonders.stpt.mscp.entity.bo.MscpMonthBidStat;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class MscpMonthBidStatDaoImpl extends AbstractHibernateDaoImpl<MscpMonthBidStat> implements MscpMonthBidStatDao{

	@Override
	public List<MscpMonthBidStat> findMscpMonthBidStat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String findMscpMonthBidStatSum() {
		String sql = "select to_char(sum(to_number(amount))/10000) money_sum from mscp_month_bid_stat where removed='0'";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (String)session.createSQLQuery(sql).uniqueResult();
	}
	
	@Override
	public List<Object[]> findMscpMonthBidStatByMonth(){
		String sql = "select * from (select to_number(amount) from mscp_month_bid_stat where removed = '0' order by month desc) where rownum < 7";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).list();
	}
}
