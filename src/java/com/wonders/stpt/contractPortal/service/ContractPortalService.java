package com.wonders.stpt.contractPortal.service;

import java.util.Collection;
import java.util.List;

public interface ContractPortalService {
	public List<Object[]> getDatasBySql(String sql);
	
	public void saveOrUpdateAll(Collection cols);
	
	public void updateDatasBySql(String sql);
}
