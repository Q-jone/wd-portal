package com.wonders.stpt.union.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.entity.bo.UnionDealUser;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionDealUserService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionThemeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.DateUtil;

@Repository("unionThemeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionThemeServiceImpl implements UnionThemeService{
	
	private UnionTDao<UnionTheme> dao;
	public UnionTDao<UnionTheme> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionTheme> dao) {
		this.dao = dao;
	}

	@Override
	public UnionTheme find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionTheme.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionTheme save(UnionTheme bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	public String flowDeal(UnionFlowInfo params){

		String themeId = params.getThemeId();
		UnionTheme theme = this.find(themeId);
		String choice = params.getChoice();
		
		int status = params.getStatus();
		int stage = UnionApproval.APPROVE_STAGE;

		String result = "1";
		int updateStatus = -1;
		if("0".equals(choice)){
			switch(status){
				case UnionTheme.SUM_STATUS:
					updateStatus = UnionTheme.DEPT_LEADER_APPROVE_STATUS;
					break;
				case UnionTheme.DEPT_LEADER_APPROVE_STATUS:
					updateStatus = UnionTheme.GROUP_LEADER_APPROVE_STATUS;
					break;	
				case UnionTheme.GROUP_LEADER_APPROVE_STATUS:
					updateStatus = UnionTheme.PASS_STATUS;
					this.matchService.updateMatchStatusOfTheme(themeId, UnionMatch.PASS_STATUS);
					break;						
			}
		}else if("1".equals(choice)){
			switch(status){
				case UnionTheme.SUM_STATUS:
					updateStatus = UnionTheme.NEW_STATUS;
					break;
				case UnionTheme.DEPT_LEADER_APPROVE_STATUS:
					updateStatus = UnionTheme.SUM_STATUS;
					break;				
				case UnionTheme.GROUP_LEADER_APPROVE_STATUS:		
					updateStatus = UnionTheme.SUM_STATUS;
					break;						
			}
		}
		
		if(updateStatus >= 0 ){
			saveApprovalInfo(theme,params);

			if(!StringUtils.isEmpty(theme.getTodoId())){
				UnionProcessUtil.clearTodo(theme.getTodoId());
				theme.setTodoId(null);
			}
			
			List<UnionDealUser> dealUsers = this.dealUserService.findByStatusAndStage(updateStatus, stage);
			for(UnionDealUser dealUser : dealUsers){
				if("1".equals(dealUser.getIfAddTodo())){
					if(StringUtils.isEmpty(params.getHandlerId())){
						params.setHandlerId(dealUser.getLoginName());
						params.setHandlerName(dealUser.getName());
					}
					String todoId = UnionProcessUtil.addTodo(params.getHandlerId(), params.getBasePath()+dealUser.getDealUrl()+themeId, "工会立功竞赛-"+theme.getThemeName(), UnionProcessUtil.getTodoStep(updateStatus,stage));
					theme.setTodoId(todoId);
				}
			}

			theme.setuTime(DateUtil.getNowTime());
			theme.setStatus(updateStatus);
			theme.setHandlerId(params.getHandlerId());
			theme.setHandlerName(params.getHandlerName());
			this.dao.update(theme);		
		}
		
		return result;
	}
	
	private void saveApprovalInfo(UnionTheme theme,UnionFlowInfo params){
		UnionApproval bo = new UnionApproval();
		bo.setMatchId(theme.getId());
		bo.setChoice(params.getChoice());
		bo.setRemark(params.getRemark());
		bo.setCheckUser(params.getUserName());
		bo.setCheckUserId(params.getLoginName());
		bo.setCheckTime(DateUtil.getNowTime());
		bo.setRemoved("0");
		bo.setStage(UnionApproval.APPROVE_STAGE);
		bo.setStep(theme.getStatus());
		bo.setNodeName(UnionProcessUtil.getStep(theme.getStatus(),UnionApproval.APPROVE_STAGE));
		approvalService.save(bo);
	}
	
	public int updateStatus(String id, int status){
		return this.dao.excuteHQLUpdate("update UnionTheme set status = ? where id = ?", new Object[]{status, id});
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
	
	private UnionMatchService matchService;
	public UnionMatchService getMatchService() {
		return matchService;
	}
	@Autowired(required=false)
	public void setMatchService(@Qualifier("unionMatchService")UnionMatchService matchService) {
		this.matchService = matchService;
	}
}
