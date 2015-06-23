package com.wonders.stpt.union.service.prize;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.prize.UnionProjectPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionTeamPrizeService {
	
	public UnionTeamPrize find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionTeamPrize save(UnionTeamPrize bo);
	
	public List<UnionTeamPrize> findByDeptOfMatch(String matchId, String deptId);
	
	public int findByPrizeAndDeptId(String prizeId,String deptId);
	
	public List<UnionTeamPrize> findByMatchId(String matchId);
	
	public int logicDelete(String id);
	
	public List<UnionTeamPrize> findByThemeId(String themeId);
	
	public List<UnionTeamPrize> findByApplyId(String applyId);
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds);
	
	public void updateRejected(String applyId, String rejected);
	
	public List<UnionTeamPrize> findRejectedByApplyId(String applyId);
	
	public String readExcel(List<File> files, List<String> fileuploadFileName, UnionFlowInfo params);
	
	public void eraseModified(String applyId);
}
