package com.wonders.stpt.mscp.dao.impl;

import java.util.List;

import org.hibernate.Session;

import com.wonders.stpt.mscp.dao.MscpSupplierStatDao;
import com.wonders.stpt.mscp.entity.bo.MscpSupplierStat;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

public class MscpSupplierStatDaoImpl extends AbstractHibernateDaoImpl<MscpSupplierStat> implements MscpSupplierStatDao{

	@Override
	public List<MscpSupplierStat> findMscpSupplierStat() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Object[]> findMscpSupplierStatNum(){
		String sql = "select type_name,count_num from mscp_supplier_stat t where removed = '0' order by order_num";
		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
		return session.createSQLQuery(sql).list();
	}

}
