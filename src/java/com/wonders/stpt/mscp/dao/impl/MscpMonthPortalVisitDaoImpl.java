

package com.wonders.stpt.mscp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpMonthPortalVisitDao;
import com.wonders.stpt.mscp.entity.bo.MscpMonthPortalVisit;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class MscpMonthPortalVisitDaoImpl extends
		AbstractHibernateDaoImpl<MscpMonthPortalVisit> implements
		MscpMonthPortalVisitDao {

	@Override
	public List<MscpMonthPortalVisit> findMscpMonthPortalVisit() {
		String hql="from MscpMonthPortalVisit t where t.removed='0' order by to_date(t.statDate,'yyyy-mm') DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setMaxResults(6).list();
	}

	@Override
	public String findMscpMonthPortalVisitByDate(String date) {
		String sql = "select to_char(sum(to_number(visit_count))) count_sum from mscp_month_portal_visit where removed='0'";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return (String)session.createSQLQuery(sql).uniqueResult();
	}
	
	@Override
	public void findMscpPortalVisitCompare(String now,String last,Map<String,String> map){
		String hql="from MscpMonthPortalVisit t where t.operateTime like '%"+now+"%' or t.operateTime like '%"+last+"%'";
		List<MscpMonthPortalVisit> list = getHibernateTemplate().find(hql);
		if(list !=null && list.size() > 0){
			for(MscpMonthPortalVisit bo : list){
				map.put(bo.getStatDate(), bo.getVisitCount());
			}
		}
		
	}
	
}
