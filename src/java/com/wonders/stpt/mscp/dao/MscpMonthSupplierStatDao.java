package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpMonthSupplierStat;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface MscpMonthSupplierStatDao extends AbstractHibernateDao<MscpMonthSupplierStat>{
	public List<MscpMonthSupplierStat> findMscpMonthSupplierStat();
	public List<Object[]> findMscpMonthSupplierStatNumThisMonth(String month);
	public List<Object[]> findMscpMonthSupplierStatSumByMonth();
}
