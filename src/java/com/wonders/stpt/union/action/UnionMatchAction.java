package com.wonders.stpt.union.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.login.constant.LoginConstant;
import com.wonders.stpt.exam.action.AbstractParamAction;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.bo.prize.UnionAchivementPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionProjectPrize;
import com.wonders.stpt.union.entity.bo.prize.UnionTeamPrize;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.service.UnionThemeService;
import com.wonders.stpt.union.service.prize.UnionAchivementPrizeService;
import com.wonders.stpt.union.service.prize.UnionPersonalPrizeService;
import com.wonders.stpt.union.service.prize.UnionProjectPrizeService;
import com.wonders.stpt.union.service.prize.UnionTeamPrizeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/**
 * 专项
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionMatch")
@Controller("unionMatchAction")
@Scope("prototype")
public class UnionMatchAction extends AbstractParamAction implements ModelDriven<UnionMatch> {

	
	private UnionMatch unionMatch = new UnionMatch();
	
	private PageResultSet<UnionMatch> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);
	
	public UnionFlowInfo params = new UnionFlowInfo();

	@Override
	public UnionMatch getModel() {
		return unionMatch;
	}

	public void setUnionMatch(UnionMatch unionMatch) {
		this.unionMatch = unionMatch;
	}

	public PageResultSet<UnionMatch> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<UnionMatch> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	/**
	 * 专项列表(工会经办人、工会领导、牵头部门经办人)
	 * @return
	 */
	@Action(value="list",results={
			@Result(name="success",location="/union/matchList.jsp"),
			@Result(name="deptLeader",location="/union/matchList_deptLeader.jsp"),
			@Result(name="assessTeam",location="/union/matchList_assessTeam.jsp"),
			@Result(name="assessSum",location="/union/matchList_assessSum.jsp"),
			@Result(name="approver",location="/union/matchList_approver.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String themeId = this.request.getParameter("themeId");
		String g = this.request.getParameter("g");
		
		String matchName = this.request.getParameter("matchName");
		String status = this.request.getParameter("status");
		
		String pageNoString = this.request.getParameter("page");
		if(StringUtils.isEmpty(pageNoString) || "0".equals(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.ID,t.MATCH_NAME,t.MATCH_TYPE,t.BEGIN_DATE,t.END_DATE,t.DEPT_ID,t.DEPT_NAME,t.OPERATOR_ID,t.OPERATOR,t.HANDLER_ID,t.STATUS"
				+" from U_MATCH t where t.removed=0";
		String countHql = "select count(t.id) from U_MATCH t where t.removed=0";
		
		String filterPart = "";
		
		if(StringUtils.isNotEmpty(matchName)){
			filterPart += " and t.MATCH_NAME like '%"+matchName+"%'";
		}
		if(StringUtils.isNotEmpty(status)){
			filterPart += " and t.STATUS = "+status;
		}
		
		if("assessTeam".equals(g)){
			filterPart += " and t.OPERATOR_ID like '"+tloginName+"%' and (t.STATUS >= "+UnionMatch.PRIZE_SET_STATUS+" AND t.STATUS <= "+UnionMatch.PASS_STATUS+")";
		}else if("assessSum".equals(g)){
			filterPart += " and t.OPERATOR_ID like '"+tloginName+"%' and (t.STATUS >= "+UnionMatch.APPLY_STATUS+" AND t.STATUS <= "+UnionMatch.PASS_STATUS+")";
		}else if("deptLeader".equals(g)){
			filterPart += " and t.STATUS >= "+UnionMatch.MATCH_REVIEW_STATUS;
		}else if("approver".equals(g)){
			filterPart += " and (exists(select id from U_APPROVAL where MATCH_ID = t.ID and CHECK_USER_ID like '"+tloginName+"%') or exists(select id from U_APPROVAL where MATCH_ID = t.THEME_ID and CHECK_USER_ID like '"+tloginName+"%'))";
		}
		
		if(StringUtils.isNotEmpty(themeId)){
			filterPart += " and t.THEME_ID = '"+themeId+"'";
			UnionTheme theme = this.themeService.find(themeId);
			this.request.setAttribute("theme", theme);
		}
		
		String orderPart = " order by t.U_TIME desc";
		
		PageResultSet result = this.service.findPageResult(queryHql+filterPart+orderPart,countHql+filterPart,page, pageSize);
		
		this.request.setAttribute("pageResultSet", result);
		this.request.setAttribute("themeId", themeId);
		this.request.setAttribute("g", g);
		this.request.setAttribute("matchName", matchName);
		this.request.setAttribute("status", status);
		
		if(!StringUtils.isEmpty(g)){
			return g;
		}
		return SUCCESS;
	}
	
	@Action(value="showEdit",results={
			@Result(name="success",location="/union/matchEdit.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showEdit(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String matchId = this.request.getParameter("matchId");
		UnionMatch match = service.find(matchId);
		
		List<UnionApproval> approvalInfos = approvalService.findByMatchId(matchId, UnionApproval.ORG_STAGE);
		this.request.setAttribute("approvalInfos", approvalInfos);
		
		this.request.setAttribute("match", match);
		return SUCCESS;
	}
	
	@Action(value="showAdd",results={
			@Result(name="success",location="/union/matchAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String themeId = this.request.getParameter("themeId");
		UnionTheme theme = themeService.find(themeId);
		
		this.request.setAttribute("theme", theme);
		return SUCCESS;
	}
	
	@Action(value="showView",results={
			@Result(name="success",location="/union/matchView.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showView(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String checkRole = this.request.getParameter("checkRole");
		String matchId = this.request.getParameter("matchId");
		UnionMatch match = service.find(matchId);
		
		List<UnionApproval> approvalInfos = approvalService.findByMatchId(matchId, UnionApproval.ORG_STAGE);
		this.request.setAttribute("approvalInfos", approvalInfos);
		
		this.request.setAttribute("checkRole", checkRole);
		this.request.setAttribute("match", match);
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);

		String now = DateUtil.getNowTime();
		if(StringUtils.isEmpty(unionMatch.getId())){
			this.unionMatch.setcUser(loginName);
			this.unionMatch.setcTime(now);		
		}

		this.unionMatch.setuUser(loginName);
		this.unionMatch.setuTime(now);	
		
		UnionProcessUtil.prepareFlowInfo(request, params);
		unionMatch = this.service.save(unionMatch,params);
		
		this.aw.writeAjax("1");
		return null;
	}
	
	@Action(value="rejectById")
	public String rejectByIds(){
		UnionProcessUtil.prepareFlowInfo(request, params);
		
		this.service.rejectByIds(params);
		
		this.aw.writeAjax("1");
		return null;
	}
	
	@Action(value="matchApplySum",results={
			@Result(name="success",location="/union/matchApplySum.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String matchApplySum(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String themeId = this.request.getParameter("themeId");
		UnionTheme theme = themeService.find(themeId);
		
		List<UnionMatch> allMatches = this.service.findByThemeId(themeId);
		
		List<UnionApproval> approvalInfos = approvalService.findByMatchId(themeId, UnionApproval.APPROVE_STAGE);
		
		List<UnionMatch> matchList = new ArrayList<UnionMatch>(); 
		for(UnionMatch match : allMatches){
			if(match.getStatus() == UnionMatch.LEAD_SUM_STATUS || match.getStatus() == UnionMatch.PASS_STATUS){
				matchList.add(match);
			}
		}
		
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("matchList", matchList);
		this.request.setAttribute("approvalInfos", approvalInfos);
		this.request.setAttribute("allSubmitted", matchList.size()==allMatches.size());
		
		return SUCCESS;
	}
	
	@Action(value="matchApplySumForLeader",results={
			@Result(name="success",location="/union/matchApplySumForLeader.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String matchApplySumForLeader(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String themeId = this.request.getParameter("themeId");
		UnionTheme theme = themeService.find(themeId);
		
		List<UnionMatch> matchList = this.service.findByThemeId(themeId);
		Map<String,Map<String,List>> prizeMap = new HashMap<String,Map<String,List>>();
		
		for(UnionMatch match : matchList){
			String matchId = match.getId();
			List<UnionPersonalPrize> appliedPersonalPrizes = this.personalPrizeService.findByMatchId(matchId);
			List<UnionTeamPrize> appliedTeamPrizes = this.teamPrizeService.findByMatchId(matchId);
			List<UnionProjectPrize> appliedProjectPrizes = this.unionProjectPrizeService.findByMatchId(matchId);
			List<UnionAchivementPrize> appliedAchivementPrizes = this.unionAchivementPrizeService.findByMatchId(matchId);
			
			Map<String,List> matchPrizeMap = new HashMap<String,List>();
			matchPrizeMap.put("personal", appliedPersonalPrizes);
			matchPrizeMap.put("team", appliedTeamPrizes);
			matchPrizeMap.put("project", appliedProjectPrizes);
			matchPrizeMap.put("achivement", appliedAchivementPrizes);
			
			prizeMap.put(matchId, matchPrizeMap);
		}
		
		List<UnionApproval> approvalInfos = approvalService.findByMatchId(themeId, UnionApproval.APPROVE_STAGE);
		
		this.request.setAttribute("theme", theme);
		this.request.setAttribute("matchList", matchList);
		this.request.setAttribute("prizeMap", prizeMap);
		this.request.setAttribute("approvalInfos", approvalInfos);
		
		return SUCCESS;
	}
	
	/**
	 * 流程处理
	 * @return
	 */
	@Action(value="flowDeal")
	public String flowDeal(){
		
		UnionProcessUtil.prepareFlowInfo(request, params);
		String processResult = this.service.flowDeal(params);
		
		this.aw.writeAjax(processResult);
		return null;
	}
	
	@Action(value="doDel")
	public String doDel(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		
		this.service.logicDelete(id);
		return null;
	}
	
	private boolean checkAllDeptsSubmitted(String id){
		return this.service.checkAllDeptsSubmitted(id);
	}
	
	private boolean checkAllPrizeQuantityAssigned(String id){
		return this.service.checkAllPrizeQuantityAssigned(id);
	}
	
	private boolean checkOtherMatchSubmitted(String themeId, String matchId){
		return this.service.checkOtherMatchSubmitted(themeId, matchId);
	}
	
	private UnionMatchService service;
	public UnionMatchService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionMatchService")UnionMatchService service) {
		this.service = service;
	}
	
	private UnionThemeService themeService;
	public UnionThemeService getThemeService() {
		return themeService;
	}
	@Autowired(required=false)
	public void setThemeService(@Qualifier("unionThemeService")UnionThemeService themeService) {
		this.themeService = themeService;
	}

	public UnionMatch getUnionMatch() {
		return unionMatch;
	}
	
	private UnionApplyMatchService applyMatchService;
	public UnionApplyMatchService getApplyMatchService() {
		return applyMatchService;
	}
	@Autowired(required=false)
	public void setApplyMatchService(@Qualifier("unionApplyMatchService")UnionApplyMatchService applyMatchService) {
		this.applyMatchService = applyMatchService;
	}
	
	private UnionApprovalService approvalService;
	public UnionApprovalService getApprovalService() {
		return approvalService;
	}
	@Autowired(required=false)
	public void setApprovalService(@Qualifier("unionApprovalService")UnionApprovalService approvalService) {
		this.approvalService = approvalService;
	}
	
	private UnionPersonalPrizeService personalPrizeService;
	public UnionPersonalPrizeService getPersonalPrizeService() {
		return personalPrizeService;
	}
	@Autowired(required=false)
	public void setPersonalPrizeService(@Qualifier("unionPersonalPrizeService")UnionPersonalPrizeService personalPrizeService) {
		this.personalPrizeService = personalPrizeService;
	}
	
	private UnionTeamPrizeService teamPrizeService;
	public UnionTeamPrizeService getTeamPrizeService() {
		return teamPrizeService;
	}
	@Autowired(required=false)
	public void setTeamPrizeService(@Qualifier("unionTeamPrizeService")UnionTeamPrizeService teamPrizeService) {
		this.teamPrizeService = teamPrizeService;
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

	private UnionPrizeService prizeService;
	public UnionPrizeService getPrizeService() {
		return prizeService;
	}
	@Autowired(required=false)
	public void setPrizeService(@Qualifier("unionPrizeService")UnionPrizeService prizeService) {
		this.prizeService = prizeService;
	}
}
