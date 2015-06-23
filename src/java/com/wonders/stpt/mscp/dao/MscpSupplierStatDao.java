package com.wonders.stpt.mscp.dao;

import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpSupplierStat;
import com.wondersgroup.framework.core5.dao.AbstractHibernateDao;

public interface MscpSupplierStatDao extends AbstractHibernateDao<MscpSupplierStat>{
	public List<MscpSupplierStat> findMscpSupplierStat();
	public List<Object[]> findMscpSupplierStatNum();
}
