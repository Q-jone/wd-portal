package com.wonders.stpt.union.service.prize;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionAchivementPrizeService {
	
	public UnionAchivementPrize find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionAchivementPrize save(UnionAchivementPrize bo);
	
	public List<UnionAchivementPrize> findByDeptOfMatch(String matchId, String deptId);
	
	public int findByPrizeAndDeptId(String prizeId,String deptId);
	
	public List<UnionAchivementPrize> findByMatchId(String matchId);
	
	public int logicDelete(String id);
	
	public List<UnionAchivementPrize> findByThemeId(String themeId);
	
	public List<UnionAchivementPrize> findByApplyId(String applyId);
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds);
	
	public void updateRejected(String applyId, String rejected);
	
	public List<UnionAchivementPrize> findRejectedByApplyId(String applyId);
	
	public String readExcel(List<File> files,List<String> fileuploadFileName,UnionFlowInfo params);
	
	public void eraseModified(String applyId);
}
