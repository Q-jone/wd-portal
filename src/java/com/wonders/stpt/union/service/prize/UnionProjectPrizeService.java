package com.wonders.stpt.union.service.prize;

import java.io.File;
import java.util.List;
import java.util.Set;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionProjectPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;

public interface UnionProjectPrizeService {
	
	public UnionProjectPrize find(String id);
	
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize);
	
	public UnionProjectPrize save(UnionProjectPrize bo);
	
	public List<UnionProjectPrize> findByDeptOfMatch(String matchId, String deptId);
	
	public int findByPrizeAndDeptId(String prizeId,String deptId);
	
	public List<UnionProjectPrize> findByMatchId(String matchId);
	
	public int logicDelete(String id);
	
	public List<UnionProjectPrize> findByThemeId(String themeId);
	
	public List<UnionProjectPrize> findByApplyId(String applyId);
	
	public void rejectByIds(UnionFlowInfo params, Set<String> applyIds);
	
	public void updateRejected(String applyId, String rejected);
	
	public List<UnionProjectPrize> findRejectedByApplyId(String applyId);
	
	public String readExcel(List<File> files,List<String> fileuploadFileName,UnionFlowInfo params);
	
	public void eraseModified(String applyId);
}
