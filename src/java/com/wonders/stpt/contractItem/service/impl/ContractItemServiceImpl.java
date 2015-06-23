package com.wonders.stpt.contractItem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.contractItem.dao.ContractItemDao;
import com.wonders.stpt.contractItem.model.ContractItem;
import com.wonders.stpt.contractItem.service.ContractItemService;
import com.wonders.stpt.core.page.PageResultSet;

@Repository("contractItemService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ContractItemServiceImpl implements ContractItemService{

	@Autowired
	private ContractItemDao contractItemDao;
	
	@Override
	public PageResultSet<ContractItem> getContractItem(
			ContractItem contractItem, int page, int pageSize) throws Exception {
		return contractItemDao.find(contractItem, page, pageSize);
	}

}
