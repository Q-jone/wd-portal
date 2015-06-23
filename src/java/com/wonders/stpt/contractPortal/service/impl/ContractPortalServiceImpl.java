package com.wonders.stpt.contractPortal.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.contractPortal.dao.ContractPortalDao;
import com.wonders.stpt.contractPortal.service.ContractPortalService;

@Repository("contractPortalService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ContractPortalServiceImpl implements ContractPortalService{
	@Autowired
	private ContractPortalDao contractPortalDao;

	public List<Object[]> getDatasBySql(String sql){
		return this.contractPortalDao.getDatasBySql(sql);
	}
	
	public void saveOrUpdateAll(Collection cols){
		contractPortalDao.saveOrUpdateAll(cols);
	}
	
	public void updateDatasBySql(String sql){
		contractPortalDao.updateDatasBySql(sql);
	}
}
