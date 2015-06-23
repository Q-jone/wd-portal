package com.wonders.stpt.contractPortal.dao;

import java.util.Collection;
import java.util.List;

public interface ContractPortalDao {
	public List<Object[]> getDatasBySql(String sql);
	
	public void saveOrUpdateAll(Collection cols);
	
	public void updateDatasBySql(String sql);
}
