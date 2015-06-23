package com.wonders.stpt.union.service;

import java.util.List;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApplyDepartment;
import com.wonders.stpt.union.entity.bo.UnionPrize;

public interface UnionApplyDepartmentService {
	
	public UnionApplyDepartment find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionApplyDepartment save(UnionApplyDepartment bo);
	
	public List<UnionApplyDepartment> findByPrizeId(String prizeId);
	
	public void saveOrUpdateAll(List<UnionApplyDepartment> applyDepartments);
	
	public void updateQuantity(List<UnionApplyDepartment> applyDepartments);
	
	public List<UnionApplyDepartment> listToApply(String matchId, String deptId);
	
	public List<UnionApplyDepartment> applyDeptsOfMatch(String matchId);
	
	public List<UnionApplyDepartment> findByPrizeAndDeptId(String prizeId,String deptId);
	
	public int getQuantityAssignedSumOfPrize(String prizeId);
	
	public int logicDelete(String id);
}
