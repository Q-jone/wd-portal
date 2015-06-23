package com.wonders.stpt.contractItem.service;

import com.wonders.stpt.contractItem.model.ContractItem;
import com.wonders.stpt.core.page.PageResultSet;

public interface ContractItemService {
	PageResultSet<ContractItem> getContractItem(ContractItem contractItem,int page,int pageSize) throws Exception;
}
