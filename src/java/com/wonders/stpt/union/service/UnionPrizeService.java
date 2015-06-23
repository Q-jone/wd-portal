package com.wonders.stpt.union.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionPrizeService {
	
	public UnionPrize find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionPrize findById(String id);
	
	public UnionPrize save(UnionPrize bo);
	
	public boolean hasQuantityLeft(String prizeId, String deptId);
	
	public int quantityLeft(String prizeId, String deptId);
	
	public List<UnionPrize> findByMatchId(String matchId);
	
	public List<Map> getDepts();
	
	public int countPrizeOfMatch(String matchId);
	
	public int logicDelete(String id);
	
	public void rejectByIds(UnionFlowInfo params);
	
	public void submitRejectedPrizes(String applyId);
	
	public boolean hasApplied(String applyId);
	
	public void eraseModifiedPrizes(String applyId);
}
