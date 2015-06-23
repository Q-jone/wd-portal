package com.wonders.stpt.union.service.prize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApplyDepartment;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionPersonalPrizeService {
	
	public UnionPersonalPrize find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionPersonalPrize save(UnionPersonalPrize bo);
	
	public List<UnionPersonalPrize> findByDeptOfMatch(String matchId, String deptId);
	
	public int findByPrizeAndDeptId(String prizeId,String deptId);
	
	public List<UnionPersonalPrize> findByMatchId(String matchId);
	
	public int logicDelete(String id);
	
	public List<UnionPersonalPrize> findByThemeId(String themeId);
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds);
	
	public void updateRejected(String applyId, String rejected);
	
	public List<UnionPersonalPrize> findByApplyId(String applyId);
	
	public List<UnionPersonalPrize> findRejectedByApplyId(String applyId);
	
	public String readExcel(List<File> files,List<String> fileuploadFileName,UnionFlowInfo params);
	
	public void eraseModified(String applyId);
}
