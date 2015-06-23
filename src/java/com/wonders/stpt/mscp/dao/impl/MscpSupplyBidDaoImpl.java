

package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpSupplyBidDao;
import com.wonders.stpt.mscp.entity.bo.MscpSupplyBid;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class MscpSupplyBidDaoImpl extends AbstractHibernateDaoImpl<MscpSupplyBid> implements MscpSupplyBidDao {

	@Override
	public List<MscpSupplyBid> findMscpSupplyBid(String date) {
		String hql ="from MscpSupplyBid t where t.statDate ='"+date+"' and removed='0' order by to_number(t.bidSum) DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setMaxResults(10).list();
	}

	@Override
	public String findMscpSupplyBidCountByDate(String date) {
		List<Object> list;
		Session session = getHibernateTemplate().getSessionFactory().openSession();
		try {
			String sql = "select sum(t.bid_sum)/10000 from mscp_supply_bid t where t.removed='0' ";
			Query query = session.createSQLQuery(sql);
			list = query.list();
			if(list!=null && list.size()==1 && list.get(0)!=null){
				return list.get(0).toString();
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			session.close();
		}
		return null; 
	}

	
	
}
