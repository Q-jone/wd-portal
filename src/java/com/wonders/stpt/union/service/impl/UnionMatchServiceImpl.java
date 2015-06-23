package com.wonders.stpt.union.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionApplyDepartment;
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.entity.bo.UnionDealUser;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionPrize;
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyDepartmentService;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionDealUserService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionOrganService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.UnionThemeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.DateUtil;
import com.wondersgroup.framework.util.DateUtils;

@Repository("unionMatchService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionMatchServiceImpl implements UnionMatchService{
	 
	private UnionTDao<UnionMatch> dao;
	public UnionTDao<UnionMatch> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionMatch> dao) {
		this.dao = dao;
	}

	@Override
	public UnionMatch find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionMatch.class);
	}
	
	public String flowDeal(UnionFlowInfo params){

		String id = params.getId();
		UnionMatch match = this.find(id);
		String choice = params.getChoice();
		
		int status = params.getStatus();
		int stage = UnionApproval.ORG_STAGE;

		String result = "1";
		int updateStatus = -1;
		if("0".equals(choice)){
			switch(status){
				case UnionMatch.MATCH_REVIEW_STATUS:
					updateStatus = UnionMatch.PRIZE_SET_STATUS;
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());
					break;
				case UnionMatch.PRIZE_SET_STATUS:
					if(this.prizeService.countPrizeOfMatch(id) == 0){
						result = "2";
						break;						
					}
					updateStatus = UnionMatch.PRIZE_SET_DEPT_REVIEW_STATUS;
					break;	
				case UnionMatch.PRIZE_SET_DEPT_REVIEW_STATUS:
					updateStatus = UnionMatch.PRIZE_SET_FIRST_REVIEW_STATUS;
					break;
				case UnionMatch.PRIZE_SET_FIRST_REVIEW_STATUS:
					updateStatus = UnionMatch.PRIZE_SET_APPROVE_STATUS;
					break;
				case UnionMatch.PRIZE_SET_APPROVE_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());								
					updateStatus = UnionMatch.PRIZE_SET_SECOND_REVIEW_STATUS;
					break;
				case UnionMatch.PRIZE_SET_SECOND_REVIEW_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());
					updateStatus = UnionMatch.PRIZE_ASSIGN_STATUS;
					break;																		
				case UnionMatch.PRIZE_ASSIGN_STATUS:
					if(!checkAllPrizeQuantityAssigned(id)){
						result = "2";
						break;						
					}
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());					
					updateStatus = UnionMatch.PRIZE_ASSIGN_OPERATOR_STATUS;
					break;
				case UnionMatch.PRIZE_ASSIGN_OPERATOR_STATUS:
					initApplyMatchData(params);
					updateStatus = UnionMatch.PRIZE_ASSIGN_DEPT_REVIEW_STATUS;
					break;					
				case UnionMatch.PRIZE_ASSIGN_DEPT_REVIEW_STATUS:
					updateStatus = UnionMatch.PRIZE_ASSIGN_FIRST_REVIEW_STATUS;
					break;
				case UnionMatch.PRIZE_ASSIGN_FIRST_REVIEW_STATUS:
					updateStatus = UnionMatch.PRIZE_ASSIGN_APPROVE_STATUS;
					break;																			
				case UnionMatch.PRIZE_ASSIGN_APPROVE_STATUS:
					updateStatus = UnionMatch.APPLY_STATUS;
					this.dispatchMatchApply(match,params); //下发参赛单位
					break;
				case UnionMatch.ASSESS_SUM_STATUS:
					updateStatus = UnionMatch.ASSESS_SUM_REVIEW_STATUS;
					break;											
				case UnionMatch.ASSESS_SUM_REVIEW_STATUS:
					if(checkOtherMatchSubmitted(match.getThemeId(),match.getId())){
						startThemeApprove(match,params);
					}
					updateStatus = UnionMatch.LEAD_SUM_STATUS;
					break;								
			}
		}else if("1".equals(choice)){
			switch(status){
				case UnionMatch.MATCH_REVIEW_STATUS:
					params.setHandlerId(match.getcUser());
					updateStatus = UnionMatch.MODIFY_STATUS;
					break;
				case UnionMatch.PRIZE_SET_DEPT_REVIEW_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());
					updateStatus = UnionMatch.PRIZE_SET_STATUS;
					break;				
				case UnionMatch.PRIZE_SET_FIRST_REVIEW_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());					
					updateStatus = UnionMatch.PRIZE_SET_STATUS;
					break;							
				case UnionMatch.PRIZE_SET_APPROVE_STATUS:
					updateStatus = UnionMatch.PRIZE_SET_FIRST_REVIEW_STATUS;
					break;
				case UnionMatch.PRIZE_SET_SECOND_REVIEW_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());
					updateStatus = UnionMatch.PRIZE_SET_STATUS;
					break;
				case UnionMatch.PRIZE_ASSIGN_OPERATOR_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());
					updateStatus = UnionMatch.PRIZE_ASSIGN_STATUS;				
					break;
				case UnionMatch.PRIZE_ASSIGN_DEPT_REVIEW_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());					
					updateStatus = UnionMatch.PRIZE_ASSIGN_STATUS;
					break;
				case UnionMatch.PRIZE_ASSIGN_FIRST_REVIEW_STATUS:
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());					
					updateStatus = UnionMatch.PRIZE_ASSIGN_STATUS;
					break;
				case UnionMatch.PRIZE_ASSIGN_APPROVE_STATUS:
					updateStatus = UnionMatch.PRIZE_ASSIGN_FIRST_REVIEW_STATUS;
					break;										
				case UnionMatch.ASSESS_SUM_STATUS:		
					updateStatus = UnionMatch.APPLY_STATUS;
					break;				
				case UnionMatch.ASSESS_SUM_REVIEW_STATUS:		
					params.setHandlerId(match.getOperatorId());
					params.setHandlerName(match.getOperator());		
					updateStatus = UnionMatch.ASSESS_SUM_STATUS;
					break;						
			}
		}
		
		if(updateStatus >= 0 ){
			this.saveApprovalInfo(match, params);

			if(!StringUtils.isEmpty(match.getTodoId())){
				UnionProcessUtil.clearTodo(match.getTodoId());
				match.setTodoId(null);
			}
			
			List<UnionDealUser> dealUsers = this.dealUserService.findByStatusAndStage(updateStatus, stage);
			for(UnionDealUser dealUser : dealUsers){
				if("1".equals(dealUser.getIfAddTodo())){
					if(StringUtils.isEmpty(params.getHandlerId())){
						params.setHandlerId(dealUser.getLoginName());
						params.setHandlerName(dealUser.getName());
					}
					String todoId = UnionProcessUtil.addTodo(params.getHandlerId(), params.getBasePath()+dealUser.getDealUrl()+id, "工会立功竞赛-"+match.getMatchName(), UnionProcessUtil.getTodoStep(updateStatus,UnionApproval.ORG_STAGE));
					match.setTodoId(todoId);
				}
			}

			match.setuTime(DateUtil.getNowTime());
			match.setStatus(updateStatus);
			match.setHandlerId(params.getHandlerId());
			match.setHandlerName(params.getHandlerName());
			this.dao.update(match);		
		}
		
		return result;
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionMatch save(UnionMatch match,UnionFlowInfo params) {

		if(StringUtils.isEmpty(match.getId())){
			match = dao.save(match);
		}
		
		this.saveApprovalInfo(match, params);
		int updateStatus = UnionMatch.MATCH_REVIEW_STATUS;
		
		if("1".equals(params.getChoice())){
			updateStatus = UnionMatch.MATCH_NEW_STATUS;
			match.setRemoved(1);
		}
		
		if(!StringUtils.isEmpty(match.getTodoId())){
			UnionProcessUtil.clearTodo(match.getTodoId());
			match.setTodoId(null);
		}
		
		List<UnionDealUser> dealUsers = this.dealUserService.findByStatusAndStage(updateStatus, UnionApproval.ORG_STAGE);
		for(UnionDealUser dealUser : dealUsers){
			if(StringUtils.isEmpty(params.getHandlerId())){
				params.setHandlerId(dealUser.getLoginName());
				params.setHandlerName(dealUser.getName());
			}
			String todoId = UnionProcessUtil.addTodo(params.getHandlerId(), params.getBasePath()+dealUser.getDealUrl()+match.getId(), "工会立功竞赛-"+match.getMatchName(), UnionProcessUtil.getTodoStep(updateStatus,UnionApproval.ORG_STAGE));
			match.setTodoId(todoId);
			match.setHandlerId(params.getHandlerId());
			match.setHandlerName(params.getHandlerName());
		}

		match.setStatus(updateStatus);
		match = dao.update(match);
		return match;
	}
	
	private void saveApprovalInfo(UnionMatch match,UnionFlowInfo params){
		if(match.getStatus() == UnionMatch.PRIZE_ASSIGN_STATUS)
			return;
		
		UnionApproval bo = new UnionApproval();
		bo.setMatchId(match.getId());
		bo.setChoice(params.getChoice());
		bo.setRemark(params.getRemark());
		bo.setCheckUser(params.getUserName());
		bo.setCheckUserId(params.getLoginName());
		bo.setCheckTime(DateUtil.getNowTime());
		bo.setRemoved("0");
		bo.setStage(UnionApproval.ORG_STAGE);
		bo.setStep(match.getStatus());
		bo.setNodeName(UnionProcessUtil.getStep(match.getStatus(),UnionApproval.ORG_STAGE));
		approvalService.save(bo);
	}
	
	@Override
	public List<UnionMatch> listToApply(String deptId){
		String hql = "from UnionMatch m where exists(select a.id from UnionApplyDepartment a,UnionPrize p where a.prizeId = p.id and p.matchId = m.id and a.deptId = ?) and m.status = 5";
		return this.dao.findByHql(hql,new Object[]{deptId});
	}
	
	public List<UnionMatch> findByThemeId(String themeId){
		return dao.findByHql("from UnionMatch where removed = 0 and themeId = ?",new Object[]{themeId});
	}
	
	public int updateStatus(String id, int status){
		return this.dao.excuteHQLUpdate("update UnionMatch set status = ? where id = ?", new Object[]{status, id});
	}
	
	public int updateMatchStatusOfTheme(String themeId, int matchStatus){
		return this.dao.excuteHQLUpdate("update UnionMatch set status = ? where themeId = ?", new Object[]{matchStatus, themeId});
	}
	
	private void initApplyMatchData(UnionFlowInfo params){
		String[] applyDeptsIdArray = params.getApplyDeptsId().split(",");
		String[] applyDeptsNameArray = params.getApplyDeptsName().split(",");
		String[] applyUsersNameArray = params.getApplyUsersName().split(",");
		String[] applyLoginsNameArray = params.getApplyLoginsName().split(",");
		String now = DateUtil.getNowTime();
		
		List<UnionApplyMatch> toSaveList = new ArrayList<UnionApplyMatch>();
		for(int i = 0;i < applyDeptsIdArray.length;i++){
			UnionApplyMatch applyMatch = new UnionApplyMatch();
			applyMatch.setDeptId(applyDeptsIdArray[i]);
			applyMatch.setDeptName(applyDeptsNameArray[i]);
			applyMatch.setUserName(applyUsersNameArray[i]);
			applyMatch.setLoginName(applyLoginsNameArray[i]);
			applyMatch.setMatchId(params.getId());
			applyMatch.setStatus(UnionApplyMatch.NEW_STATUS);
			applyMatch.setcUser(params.getLoginName());
			applyMatch.setcTime(now);
			applyMatch.setuUser(params.getLoginName());
			applyMatch.setuTime(now);			
			toSaveList.add(applyMatch);			
		}
		
		applyMatchService.deleteDiscarded(params.getId());
		applyMatchService.saveOrUpdateAll(toSaveList);
	}
	
	private void dispatchMatchApply(UnionMatch match, UnionFlowInfo params){
		List<UnionApplyMatch> toUpdateList = this.applyMatchService.findByMatchId(match.getId());
		for(UnionApplyMatch applyMatch : toUpdateList){
			String todoId = UnionProcessUtil.addTodo(applyMatch.getLoginName(), params.getBasePath()+"/unionPrize/prizeApplyList.action?applyMatchId="+applyMatch.getId(), "工会立功竞赛-"+match.getMatchName(), "竞赛申报");
			applyMatch.setHandlerId(applyMatch.getLoginName());
			applyMatch.setHandlerName(applyMatch.getUserName());
			applyMatch.setStatus(UnionApplyMatch.APPLY_STATUS);
			applyMatch.setTodoId(todoId);
		}
		applyMatchService.saveOrUpdateAll(toUpdateList);
	}
	
	private void startThemeApprove(UnionMatch match, UnionFlowInfo params){
		UnionTheme theme = this.themeService.find(match.getThemeId());
		theme.setStatus(UnionTheme.SUM_STATUS);
		
		List<UnionDealUser> dealUsers = this.dealUserService.findByStatusAndStage(theme.getStatus(), UnionApproval.APPROVE_STAGE);
		for(UnionDealUser dealUser : dealUsers){
			if(StringUtils.isEmpty(theme.getTodoId()) && "1".equals(dealUser.getIfAddTodo())){
				String todoId = UnionProcessUtil.addTodo(dealUser.getLoginName(), params.getBasePath()+dealUser.getDealUrl()+theme.getId(), "工会立功竞赛-"+theme.getThemeName(), UnionProcessUtil.getTodoStep(theme.getStatus(),UnionApproval.APPROVE_STAGE));
				theme.setTodoId(todoId);
				theme.setHandlerId(dealUser.getLoginName());
				theme.setHandlerName(dealUser.getName());
			}
		}
		
		this.themeService.save(theme);
	}
	
	public boolean checkAllDeptsSubmitted(String id){
		List<UnionApplyMatch> applyMatches = applyMatchService.findByMatchId(id);
		for(UnionApplyMatch applyMatch : applyMatches){
			if(applyMatch.getStatus() != 1){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkAllPrizeQuantityAssigned(String id){
		List<UnionPrize> prizes = this.prizeService.findByMatchId(id);
		for(UnionPrize prize : prizes){
			if(prize.getQuantity() > applyDepartmentService.getQuantityAssignedSumOfPrize(prize.getId())){
				return false;
			}
		}
		return true;
	}
	
	public boolean checkOtherMatchSubmitted(String themeId, String matchId){
		List<UnionMatch> matchList = this.findByThemeId(themeId);
		for(UnionMatch match : matchList){
			if(match.getStatus() != UnionMatch.LEAD_SUM_STATUS && !matchId.equals(match.getId())){
				return false;
			}
		}
		return true;
	}
	
	public void rejectByIds(UnionFlowInfo params){
		String id = params.getId();
		UnionMatch match = this.find(id);
		this.saveApprovalInfo(match, params);
		
		match.setStatus(UnionMatch.ASSESS_SUM_STATUS);

		String todoId = UnionProcessUtil.addTodo(match.getOperatorId(), params.getBasePath()+"/unionPrize/prizeApplySum.action?matchId="+params.getId(), "工会立功竞赛-"+match.getMatchName(), "返回修改");
		match.setTodoId(todoId);
		match.setHandlerId(match.getOperatorId());
		match.setHandlerName(match.getOperator());
		
		this.dao.save(match);

	}
	
	@Override
	public void logicDelete(String id){
		UnionMatch match = this.find(id);
		if(StringUtils.isNotEmpty(match.getTodoId())){
			UnionProcessUtil.clearTodo(match.getTodoId());
			match.setTodoId(null);
			match.setHandlerId(null);
			match.setHandlerName(null);
		}
		match.setRemoved(1);
		this.update(match);
	}
	
	public UnionMatch update(UnionMatch match){
		return this.dao.update(match);
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
	
	private UnionThemeService themeService;
	public UnionThemeService getThemeService() {
		return themeService;
	}
	@Autowired(required=false)
	public void setThemeService(@Qualifier("unionThemeService")UnionThemeService themeService) {
		this.themeService = themeService;
	}
	
	private UnionDealUserService dealUserService;
	public UnionDealUserService getDealUserService() {
		return dealUserService;
	}
	@Autowired(required=false)
	public void setDealUserService(@Qualifier("unionDealUserService")UnionDealUserService dealUserService) {
		this.dealUserService = dealUserService;
	}
	
	private UnionOrganService organService;
	public UnionOrganService getOrganService() {
		return organService;
	}
	@Autowired(required=false)
	public void setOrganService(@Qualifier("unionOrganService")UnionOrganService organService) {
		this.organService = organService;
	}
}
