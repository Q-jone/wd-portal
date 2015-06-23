package com.wonders.stpt.union.service;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionThemeService {
	
	public UnionTheme find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionTheme save(UnionTheme bo);
	
	public int updateStatus(String id, int status);
	
	public String flowDeal(UnionFlowInfo params);
}
