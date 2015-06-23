package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpMonthSupplierStatDao;
import com.wonders.stpt.mscp.entity.bo.MscpMonthSupplierStat;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class MscpMonthSupplierStatDaoImpl extends AbstractHibernateDaoImpl<MscpMonthSupplierStat> implements MscpMonthSupplierStatDao{

	@Override
	public List<MscpMonthSupplierStat> findMscpMonthSupplierStat() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> findMscpMonthSupplierStatNumThisMonth(String month){
		String sql = "select count_num from mscp_month_supplier_stat t where removed = '0' and month = ? order by order_num";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).setString(0, month).list();
	}
	
	@Override
	public List<Object[]> findMscpMonthSupplierStatSumByMonth(){
		String sql = "select * from (select month,sum(to_number(count_num)) from mscp_month_supplier_stat t where t.removed = '0' "+
			" group by month order by month desc) where rownum <7";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).list();
	}
}
