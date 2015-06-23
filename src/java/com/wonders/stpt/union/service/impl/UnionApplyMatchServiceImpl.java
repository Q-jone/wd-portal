package com.wonders.stpt.union.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.entity.bo.UnionDealUser;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionDealUserService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.DateUtil;

@Repository("unionApplyMatchService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionApplyMatchServiceImpl implements UnionApplyMatchService{
	
	private UnionTDao<UnionApplyMatch> dao;
	public UnionTDao<UnionApplyMatch> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionApplyMatch> dao) {
		this.dao = dao;
	}

	@Override
	public UnionApplyMatch find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionApplyMatch.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionApplyMatch save(UnionApplyMatch bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	@Override
	public List<UnionApplyMatch> findByMatchId(String matchId) {
		// TODO Auto-generated method stub
		return dao.findByHql("from UnionApplyMatch where matchId = ?",new Object[]{matchId});
	}
	
	@Override
	public String flowDeal(UnionFlowInfo params){

		String matchId = params.getId();
		String applyId = params.getApplyId();
		String choice = params.getChoice();
		
		UnionMatch match = this.matchService.find(matchId);
		UnionApplyMatch applyMatch = this.find(applyId);
		String deptId = applyMatch.getDeptId();
		int status = params.getStatus();

		int stage = UnionApproval.APPLY_STAGE;
		String result = "1";
		int updateStatus = -1;
		boolean pushTodo = false;
		if("0".equals(choice)){
			switch(status){
				case UnionApplyMatch.APPLY_STATUS:
					if(!existApplying(matchId,deptId,applyId)){
						if(!checkAllQuantityApplied(matchId,deptId)){
							result = "2";
							break;
						}						
					}
					if(!this.prizeService.hasApplied(applyId)){
						result = "4";
						break;						
					}
					pushTodo = true;
					updateStatus = UnionApplyMatch.DEPT_REVIEW_STATUS;
					break;				
				case UnionApplyMatch.DEPT_REVIEW_STATUS:
					if(existUnsubmitted(matchId,deptId,applyId)){
						updateStatus = UnionApplyMatch.SUBMITTED_STATUS;						
					}else{
						updateStatus = UnionApplyMatch.ASSESS_STATUS;	
						this.updateSameDeptStatus(applyId ,matchId, deptId, UnionApplyMatch.ASSESS_STATUS);
						
						if(this.checkOtherDeptsSubmitted(matchId, deptId)){
							if(StringUtils.isEmpty(match.getTodoId())){
								pushTodo = true;
								stage = UnionApproval.ORG_STAGE;
								params.setHandlerId(match.getOperatorId());
								params.setHandlerName(match.getOperator());
								match.setStatus(UnionMatch.ASSESS_SUM_STATUS);//如其他部门都已提交，可把专项提交给考评部门								
							}
						}
					}
					break;				
				case UnionApplyMatch.MODIFY_STATUS:
					if(!existApplying(matchId,deptId,applyId)){
						if(!checkAllQuantityApplied(matchId,deptId)){
							result = "2";
							break;
						}						
					}
					this.prizeService.submitRejectedPrizes(applyId);
					
					pushTodo = true;
					updateStatus = UnionApplyMatch.DEPT_REVIEW_STATUS;
					break;						
			}
		}else if("1".equals(choice)){
			switch(status){
				case UnionApplyMatch.APPLY_STATUS:
					if(!existApplying(matchId,deptId,applyId)){
						if(!checkAllQuantityApplied(matchId,deptId)){
							result = "2";
							break;
						}						
					}
					if(this.prizeService.hasApplied(applyId)){
						result = "3";
						break;						
					}
					
					if(existUnsubmitted(matchId,deptId,applyId)){
						updateStatus = UnionApplyMatch.SUBMITTED_STATUS;						
					}else{
						updateStatus = UnionApplyMatch.ASSESS_STATUS;	
						this.updateSameDeptStatus(applyId ,matchId, deptId, UnionApplyMatch.ASSESS_STATUS);
						
						if(this.checkOtherDeptsSubmitted(matchId, deptId)){
							if(StringUtils.isEmpty(match.getTodoId())){
								pushTodo = true;
								stage = UnionApproval.ORG_STAGE;
								params.setHandlerId(match.getOperatorId());
								params.setHandlerName(match.getOperator());
								match.setStatus(UnionMatch.ASSESS_SUM_STATUS);//如其他部门都已提交，可把专项提交给考评部门								
							}
						}
					}
					break;					
				case UnionApplyMatch.DEPT_REVIEW_STATUS:
					pushTodo = true;
					updateStatus = UnionApplyMatch.MODIFY_STATUS;
					params.setHandlerId(applyMatch.getLoginName());
					params.setHandlerName(applyMatch.getUserName());
					this.prizeService.eraseModifiedPrizes(applyId);
					break;			
				case UnionApplyMatch.ASSESS_STATUS:
					updateStatus = UnionApplyMatch.MODIFY_STATUS;
					break;
			}
		}
		
		if(updateStatus > 0){
			this.saveApprovalInfo(applyMatch, params);
			
			if(!StringUtils.isEmpty(applyMatch.getTodoId())){
				UnionProcessUtil.clearTodo(applyMatch.getTodoId());
				applyMatch.setTodoId(null);
				applyMatch.setHandlerId(null);
				applyMatch.setHandlerName(null);				
			}
			
			if(pushTodo){
				if(stage == UnionApproval.APPLY_STAGE){
					List<UnionDealUser> dealUsers = this.dealUserService.findByStatusAndStage(updateStatus, stage);
					for(UnionDealUser dealUser : dealUsers){
						if(StringUtils.isEmpty(params.getHandlerId())){
							params.setHandlerId(dealUser.getLoginName());
							params.setHandlerName(dealUser.getName());
						}
						String todoId = UnionProcessUtil.addTodo(params.getHandlerId(), params.getBasePath()+dealUser.getDealUrl()+applyId, "工会立功竞赛-"+match.getMatchName(), UnionProcessUtil.getTodoStep(updateStatus,stage));
						applyMatch.setTodoId(todoId);
						applyMatch.setHandlerId(params.getHandlerId());
						applyMatch.setHandlerName(params.getHandlerName());						
					}					
				}else if(stage == UnionApproval.ORG_STAGE){
					List<UnionDealUser> dealUsers = this.dealUserService.findByStatusAndStage(match.getStatus(), stage);
					for(UnionDealUser dealUser : dealUsers){
						if(StringUtils.isEmpty(params.getHandlerId())){
							params.setHandlerId(dealUser.getLoginName());
							params.setHandlerName(dealUser.getName());
						}
						String todoId = UnionProcessUtil.addTodo(params.getHandlerId(), params.getBasePath()+dealUser.getDealUrl()+matchId, "工会立功竞赛-"+match.getMatchName(), UnionProcessUtil.getTodoStep(match.getStatus(),stage));
						match.setHandlerId(params.getHandlerId());
						match.setHandlerName(params.getHandlerName());
						match.setTodoId(todoId);
						this.matchService.update(match);

					}					
				}			
			}
			
			applyMatch.setuTime(DateUtil.getNowTime());
			applyMatch.setStatus(updateStatus);
			this.dao.update(applyMatch);		
		}
		
		return result;
	}
	
	private boolean checkAllQuantityApplied(String matchId,String deptId){
		
		List<UnionPrize> prizes = this.prizeService.findByMatchId(matchId);
		for(UnionPrize prize : prizes){
			if(this.prizeService.hasQuantityLeft(prize.getId(), deptId)){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * 其他部门是否都已提交
	 * @param id
	 * @param deptId
	 * @return
	 */
	private boolean checkOtherDeptsSubmitted(String id, String deptId){
		List<UnionApplyMatch> applyMatches = this.findByMatchId(id);
		for(UnionApplyMatch applyMatch : applyMatches){
			if(applyMatch.getStatus() != UnionApplyMatch.ASSESS_STATUS && !deptId.equals(applyMatch.getDeptId())){
				return false;
			}
		}
		return true;
	}

	/**
	 * 其他部门是否都已提交
	 * @param id
	 * @param deptId
	 * @return
	 */
	public boolean checkAllSubmitted(String matchId){
		int cnt = this.dao.countByHql("select count(id) from UnionApplyMatch where status != "+UnionApplyMatch.ASSESS_STATUS+" and matchId = '"+matchId+"'");
		return cnt==0?true:false;
	}
	
	public void saveApprovalInfo(UnionApplyMatch applyMatch,UnionFlowInfo params){
		UnionApproval bo = new UnionApproval();
		bo.setMatchId(applyMatch.getMatchId());
		bo.setApplyId(applyMatch.getId());
		bo.setChoice(params.getChoice());
		bo.setRemark(params.getRemark());
		bo.setCheckUser(params.getUserName());
		bo.setCheckTime(DateUtil.getNowTime());
		bo.setCheckUserId(params.getLoginName());
		bo.setRemoved("0");
		bo.setStage(UnionApproval.APPLY_STAGE);
		bo.setStep(applyMatch.getStatus());
		bo.setNodeName(UnionProcessUtil.getStep(applyMatch.getStatus(),UnionApproval.APPLY_STAGE));
		approvalService.save(bo);
	}
	
	@Override
	public List<UnionApplyMatch> finSubmittedByMatchId(String matchId) {
		List<UnionApplyMatch> all = dao.findByHql("from UnionApplyMatch t where not exists(select id from UnionApplyMatch where status != "+UnionApplyMatch.ASSESS_STATUS+" and deptId = t.deptId and matchId = t.matchId) and t.status = "+UnionApplyMatch.ASSESS_STATUS+" and t.matchId = ?",new Object[]{matchId});
		List<UnionApplyMatch> distinctList = new ArrayList<UnionApplyMatch>();
		Map<String,String> tmp = new HashMap<String,String>();
		for(UnionApplyMatch applyMatch : all){
			if(!tmp.containsKey(applyMatch.getDeptId())){
				tmp.put(applyMatch.getDeptId(), null);
				distinctList.add(applyMatch);
			}
		}
		// TODO Auto-generated method stub
		return distinctList;
	}
	
	private boolean existApplying(String matchId, String deptId, String applyId) {
		// TODO Auto-generated method stub
		int unsubmitted =  dao.countByHql("select count(id) from UnionApplyMatch where (status = "+UnionApplyMatch.APPLY_STATUS+" or status = "+UnionApplyMatch.MODIFY_STATUS+") and id != '"+applyId+"' and matchId = '"+matchId+"' and deptId = '"+deptId+"'");
		return unsubmitted>0 ? true:false;
	}
	
	private boolean existUnsubmitted(String matchId, String deptId, String applyId) {
		// TODO Auto-generated method stub
		int unsubmitted =  dao.countByHql("select count(id) from UnionApplyMatch where (status != "+UnionApplyMatch.SUBMITTED_STATUS+" and status != "+UnionApplyMatch.ASSESS_STATUS+") and id != '"+applyId+"' and matchId = '"+matchId+"' and deptId = '"+deptId+"'");
		return unsubmitted>0 ? true:false;
	}
	
	@Override
	public void saveOrUpdateAll(List<UnionApplyMatch> applyDepartments){
		this.dao.saveOrUpdateAll(applyDepartments);
	}
	
	public int updateStatus(String id, int status){
		return this.dao.excuteHQLUpdate("update UnionApplyMatch set status = ? where id = ?", new Object[]{status, id});
	}
	
	public int updateSameDeptStatus(String applyId ,String matchId, String deptId, int status){
		return this.dao.excuteHQLUpdate("update UnionApplyMatch set status = ? where id != ? and deptId = ? and matchId = ?", new Object[]{status, applyId ,deptId, matchId});
	}
	
	public int deleteDiscarded(String matchId){
		return this.dao.excuteHQLUpdate("delete from UnionApplyMatch where matchId = ?", new Object[]{matchId});
	}
	
	private UnionMatchService matchService;
	public UnionMatchService getMatchService() {
		return matchService;
	}
	@Autowired(required=false)
	public void setMatchService(@Qualifier("unionMatchService")UnionMatchService matchService) {
		this.matchService = matchService;
	}
	
	private UnionPrizeService prizeService;
	public UnionPrizeService getPrizeService() {
		return prizeService;
	}
	@Autowired(required=false)
	public void setPrizeService(@Qualifier("unionPrizeService")UnionPrizeService prizeService) {
		this.prizeService = prizeService;
	}
	
	private UnionApprovalService approvalService;
	public UnionApprovalService getApprovalService() {
		return approvalService;
	}
	@Autowired(required=false)
	public void setApprovalService(@Qualifier("unionApprovalService")UnionApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	private UnionDealUserService dealUserService;
	public UnionDealUserService getDealUserService() {
		return dealUserService;
	}
	@Autowired(required=false)
	public void setDealUserService(@Qualifier("unionDealUserService")UnionDealUserService dealUserService) {
		this.dealUserService = dealUserService;
	}
}
