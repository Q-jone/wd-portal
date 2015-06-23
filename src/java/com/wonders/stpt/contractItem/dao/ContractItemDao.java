package com.wonders.stpt.contractItem.dao;

import com.wonders.stpt.contractItem.model.ContractItem;
import com.wonders.stpt.core.page.PageResultSet;

public interface ContractItemDao {
	PageResultSet<ContractItem> find(ContractItem contractItem,int page,int pageSize) throws Exception;
}
