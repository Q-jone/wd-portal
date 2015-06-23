package com.wonders.stpt.union.service;

import java.util.List;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionApplyMatchService {
	
	public UnionApplyMatch find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionApplyMatch save(UnionApplyMatch bo);
	
	public void saveOrUpdateAll(List<UnionApplyMatch> applyDepartments);
	
	public int updateStatus(String id, int status);
	
	public List<UnionApplyMatch> findByMatchId(String matchId);
	
	public List<UnionApplyMatch> finSubmittedByMatchId(String matchId);
	
	public String flowDeal(UnionFlowInfo params);
	
	public boolean checkAllSubmitted(String matchId);
	
	public void saveApprovalInfo(UnionApplyMatch applyMatch,UnionFlowInfo params);
	
	public int deleteDiscarded(String matchId);
}
