package com.wonders.stpt.union.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionMatchService {
	
	public UnionMatch find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionMatch save(UnionMatch bo,UnionFlowInfo params);
	
	public List<UnionMatch> listToApply(String deptId);
	
	public List<UnionMatch> findByThemeId(String themeId);
	
	public int updateStatus(String id, int status);
	
	public boolean checkAllDeptsSubmitted(String id);
	
	public int updateMatchStatusOfTheme(String themeId, int matchStatus);
	
	public boolean checkAllPrizeQuantityAssigned(String id);
	
	public boolean checkOtherMatchSubmitted(String themeId, String matchId);
	
	public String flowDeal(UnionFlowInfo params);
	
	public UnionMatch update(UnionMatch match);
	
	public void rejectByIds(UnionFlowInfo params);
	
	public void logicDelete(String id);
}
