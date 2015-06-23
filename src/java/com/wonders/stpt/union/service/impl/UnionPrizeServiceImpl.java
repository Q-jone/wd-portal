package com.wonders.stpt.union.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionApplyDepartment;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionProjectPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyDepartmentService;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.prize.UnionAchivementPrizeService;
import com.wonders.stpt.union.service.prize.UnionPersonalPrizeService;
import com.wonders.stpt.union.service.prize.UnionProjectPrizeService;
import com.wonders.stpt.union.service.prize.UnionTeamPrizeService;
import com.wonders.stpt.union.util.UnionProcessUtil;

@Repository("unionPrizeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionPrizeServiceImpl implements UnionPrizeService{
	
	private UnionTDao<UnionPrize> dao;
	public UnionTDao<UnionPrize> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionPrize> dao) {
		this.dao = dao;
	}

	@Override
	public UnionPrize find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionPrize.class);
	}
	
	@Override
	public List<UnionPrize> findByMatchId(String matchId) {
		String hql = "from UnionPrize where removed = 0 and matchId = ? order by prizeType";
		return dao.findByHql(hql, new Object[]{matchId});
	}
	
	@Override
	public UnionPrize findById(String id) {
		String hql = "from UnionPrize p left join fetch p.match m left join fetch m.theme t where p.removed = 0 and p.id = ?";
		List<UnionPrize> list = dao.findByHql(hql, new Object[]{id});
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public int countPrizeOfMatch(String matchId) {
		String countHql = "select count(t.id) from UnionPrize t where t.removed = 0 and t.matchId = '"+matchId+"'";
		return dao.countByHql(countHql);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionPrize save(UnionPrize bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			bo = dao.update(bo);
		}else{
			bo = dao.save(bo);
		}
		
/*        if(bo.getApplyDepartmentList()!=null){

            for (UnionApplyDepartment applyDepartment : bo.getApplyDepartmentList()) {
            	applyDepartment.setPrizeId(bo.getId());
            }
            applyDepartmentService.saveOrUpdateAll(bo.getApplyDepartmentList());
        }*/
		
		return bo;
	}
	
	public boolean hasQuantityLeft(String prizeId, String deptId) {
		UnionPrize prize = this.find(prizeId);
		
		List<UnionApplyDepartment> allowed = applyDepartmentService.findByPrizeAndDeptId(prizeId, deptId);
		
		if(allowed.size() == 0){
			return false;
		}
		
		if(UnionPrize.PRIZE_TYPE_PERSON.equals(prize.getPrizeType())){
			int applied = unionPersonalPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity()>applied;
		}else if(UnionPrize.PRIZE_TYPE_TEAM.equals(prize.getPrizeType())){
			int applied = unionTeamPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity()>applied;
		}else if(UnionPrize.PRIZE_TYPE_PROJECT.equals(prize.getPrizeType())){
			int applied = unionProjectPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity()>applied;
		}else if(UnionPrize.PRIZE_TYPE_PROJECT_ACHIEVEMENT.equals(prize.getPrizeType())){
			int applied = unionAchivementPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity()>applied;
		}
		
		return true;
	}
	
	public int quantityLeft(String prizeId, String deptId) {
		UnionPrize prize = this.find(prizeId);
		
		List<UnionApplyDepartment> allowed = applyDepartmentService.findByPrizeAndDeptId(prizeId, deptId);
		
		if(allowed.size() == 0){
			return 0;
		}
		
		if(UnionPrize.PRIZE_TYPE_PERSON.equals(prize.getPrizeType())){
			int applied = unionPersonalPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity() - applied;
		}else if(UnionPrize.PRIZE_TYPE_TEAM.equals(prize.getPrizeType())){
			int applied = unionTeamPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity() - applied;
		}else if(UnionPrize.PRIZE_TYPE_PROJECT.equals(prize.getPrizeType())){
			int applied = unionProjectPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity() - applied;
		}else if(UnionPrize.PRIZE_TYPE_PROJECT_ACHIEVEMENT.equals(prize.getPrizeType())){
			int applied = unionAchivementPrizeService.findByPrizeAndDeptId(prizeId, deptId);
			return allowed.get(0).getQuantity() - applied;
		}
		
		return 0;
	}
	
	/**
	 * 考评小组退回奖项
	 */
	public void rejectByIds(UnionFlowInfo params){
		Set<String> applyIds = new HashSet<String>();
		
		unionPersonalPrizeService.rejectByIds(params, applyIds);
		unionTeamPrizeService.rejectByIds(params, applyIds);
		unionProjectPrizeService.rejectByIds(params, applyIds);
		unionAchivementPrizeService.rejectByIds(params, applyIds);
		
		List<UnionApplyMatch> toUpdateApplyList = new ArrayList<UnionApplyMatch>();
		for(String applyId : applyIds){
			UnionApplyMatch applyMatch = this.applyMatchService.find(applyId);
			UnionMatch match = this.matchService.find(applyMatch.getMatchId());
			applyMatchService.saveApprovalInfo(applyMatch, params);
			
			this.eraseModifiedPrizes(applyId);
			applyMatch.setStatus(UnionApplyMatch.MODIFY_STATUS);
			String todoId = UnionProcessUtil.addTodo(applyMatch.getLoginName(), params.getBasePath()+"/unionPrize/prizeApplyList.action?applyMatchId="+applyId, "工会立功竞赛申报-"+match.getMatchName(), "返回修改");
			applyMatch.setTodoId(todoId);
			applyMatch.setHandlerId(applyMatch.getLoginName());
			applyMatch.setHandlerName(applyMatch.getUserName());
			toUpdateApplyList.add(applyMatch);
		}

		this.applyMatchService.saveOrUpdateAll(toUpdateApplyList);

	}
	
	public void submitRejectedPrizes(String applyId) {
		
		unionPersonalPrizeService.updateRejected(applyId,"0");
		unionTeamPrizeService.updateRejected(applyId,"0");
		unionProjectPrizeService.updateRejected(applyId,"0");
		unionAchivementPrizeService.updateRejected(applyId,"0");
		
	}
	
	public void eraseModifiedPrizes(String applyId) {
		unionPersonalPrizeService.eraseModified(applyId);
		unionTeamPrizeService.eraseModified(applyId);
		unionProjectPrizeService.eraseModified(applyId);
		unionAchivementPrizeService.eraseModified(applyId);
	}
	
	public boolean hasApplied(String applyId) {
		List<UnionPersonalPrize> unionPersonalPrizes = unionPersonalPrizeService.findByApplyId(applyId);
		if(unionPersonalPrizes.size() > 0){
			return true;
		}
		List<UnionTeamPrize> unionTeamPrizes = unionTeamPrizeService.findByApplyId(applyId);
		if(unionTeamPrizes.size() > 0){
			return true;
		}		
		List<UnionProjectPrize> unionProjectPrizes = unionProjectPrizeService.findByApplyId(applyId);
		if(unionProjectPrizes.size() > 0){
			return true;
		}		
		List<UnionAchivementPrize> unionAchivementPrizes = unionAchivementPrizeService.findByApplyId(applyId);
		if(unionAchivementPrizes.size() > 0){
			return true;
		}		
		return false;
	}
	
	@Override
	public int logicDelete(String id){
		return this.dao.excuteHQLUpdate("update UnionPrize set removed = 1 where id = ?", new Object[]{id});
	}
	
	@Override
	public List<Map> getDepts(){
		return this.dao.findBySql("SELECT id as id,PID,NAME name,DESCRIPTION,ORDERS,LEVELS,DECODE(LEVELS,'0','true','1','false') ISPARENT FROM  V_ORGAN_TREE WHERE LEVELS = 1 and TYPE_ID = 2 and ID != 2501");
	}
	
	private UnionApplyDepartmentService applyDepartmentService;
	public UnionApplyDepartmentService getApplyDepartmentService() {
		return applyDepartmentService;
	}
	@Autowired(required=false)
	public void setApplyDepartmentService(@Qualifier("unionApplyDepartmentService")UnionApplyDepartmentService applyDepartmentService) {
		this.applyDepartmentService = applyDepartmentService;
	}
	
	private UnionApplyMatchService applyMatchService;
	public UnionApplyMatchService getApplyMatchService() {
		return applyMatchService;
	}
	@Autowired(required=false)
	public void setApplyMatchService(@Qualifier("unionApplyMatchService")UnionApplyMatchService applyMatchService) {
		this.applyMatchService = applyMatchService;
	}
	
	private UnionMatchService matchService;
	public UnionMatchService getMatchService() {
		return matchService;
	}
	@Autowired(required=false)
	public void setMatchService(@Qualifier("unionMatchService")UnionMatchService matchService) {
		this.matchService = matchService;
	}
	
	private UnionPersonalPrizeService unionPersonalPrizeService;
	public UnionPersonalPrizeService getUnionPersonalPrizeService() {
		return unionPersonalPrizeService;
	}
	@Autowired(required=false)
	public void setUnionPersonalPrizeService(@Qualifier("unionPersonalPrizeService")UnionPersonalPrizeService unionPersonalPrizeService) {
		this.unionPersonalPrizeService = unionPersonalPrizeService;
	}
	
	private UnionTeamPrizeService unionTeamPrizeService;
	public UnionTeamPrizeService getUnionTeamPrizeService() {
		return unionTeamPrizeService;
	}
	@Autowired(required=false)
	public void setUnionTeamPrizeService(@Qualifier("unionTeamPrizeService")UnionTeamPrizeService unionTeamPrizeService) {
		this.unionTeamPrizeService = unionTeamPrizeService;
	}
	
	private UnionProjectPrizeService unionProjectPrizeService;
	public UnionProjectPrizeService getUnionProjectPrizeService() {
		return unionProjectPrizeService;
	}
	@Autowired(required=false)
	public void setUnionProjectPrizeService(@Qualifier("unionProjectPrizeService")UnionProjectPrizeService unionProjectPrizeService) {
		this.unionProjectPrizeService = unionProjectPrizeService;
	}
	
	private UnionAchivementPrizeService unionAchivementPrizeService;
	public UnionAchivementPrizeService getUnionAchivementPrizeService() {
		return unionAchivementPrizeService;
	}
	@Autowired(required=false)
	public void setUnionAchivementPrizeService(@Qualifier("unionAchivementPrizeService")UnionAchivementPrizeService unionAchivementPrizeService) {
		this.unionAchivementPrizeService = unionAchivementPrizeService;
	}
	
}
