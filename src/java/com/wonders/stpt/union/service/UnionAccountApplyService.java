package com.wonders.stpt.union.service;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionAccountApply;

public interface UnionAccountApplyService {
	
	public UnionAccountApply find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionAccountApply save(UnionAccountApply bo);
	
	public int updateStatus(String id, int status);
	
	public void logicDelete(String id);
}
