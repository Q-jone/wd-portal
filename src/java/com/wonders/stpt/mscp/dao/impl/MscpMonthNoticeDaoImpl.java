

package com.wonders.stpt.mscp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpMonthNoticeDao;
import com.wonders.stpt.mscp.entity.bo.MscpMonthNotice;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;


public class MscpMonthNoticeDaoImpl extends AbstractHibernateDaoImpl<MscpMonthNotice> implements MscpMonthNoticeDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<MscpMonthNotice> findMscpMonthNotice() {
		String hql="from MscpMonthNotice t where t.removed='0' order by to_date(t.statDate,'yyyy-mm') DESC";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createQuery(hql).setMaxResults(6).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findMscpMonthNoticeByDate(String date) {
		String sql = "select to_char(sum(to_number(t.source_notice_num))) sum1,to_char(sum(to_number(t.ifb_notice_num))) sum2,"+
				" to_char(sum(to_number(t.wab_notice_num))) sum3,to_char(sum(to_number(t.bid_notice_num))) sum4,"+
				" to_char(sum(to_number(t.bid_result_num))) sum5 from mscp_month_notice t where t.removed ='0'";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).list();
	}
	
	@Override
	public void findMscpMonthNoticeCompare(String now,String last,
			Map<String,String> map1,Map<String,String> map2,
			Map<String,String> map3,
			Map<String,String> map4,
			Map<String,String> map5){
		String hql="from MscpMonthNotice t where t.operateTime like '%"+now+"%' or t.operateTime like '%"+last+"%'";
		List<MscpMonthNotice> list = getHibernateTemplate().find(hql);
		if(list !=null && list.size() > 0){
			for(MscpMonthNotice bo : list){
				map1.put(bo.getStatDate(), bo.getSourceNoticeNum());
				map2.put(bo.getStatDate(), bo.getIfbNoticeNum());
				map3.put(bo.getStatDate(), bo.getWabNoticeNum());
				map4.put(bo.getStatDate(), bo.getBidNoticeNum());
				map5.put(bo.getStatDate(), bo.getBidResultNum());
				
			}
		}
		
	}
}
