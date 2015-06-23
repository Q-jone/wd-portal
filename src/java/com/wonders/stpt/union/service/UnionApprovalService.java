package com.wonders.stpt.union.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApproval;

public interface UnionApprovalService {
	
	public UnionApproval find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionApproval save(UnionApproval bo);
	
	public List<UnionApproval> findByMatchId(String matchId, int stage);
	
	public List<UnionApproval> findByApplyId(String applyId);
	
	public List<UnionApproval> findByMatchAndDeptId(String matchId, String deptId);
	
	public Map<String,String> findFinalApprovalInfo(String applyId, String matchId, String themeId);
}
