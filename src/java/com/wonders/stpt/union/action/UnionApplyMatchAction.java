package com.wonders.stpt.union.action;

import java.util.List;

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
import com.wonders.stpt.union.entity.bo.UnionApplyMatch;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApplyMatchService;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionPrizeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;

/**
 * 运营发文 
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionApplyMatch")
@Controller("unionApplyMatchAction")
@Scope("prototype")
public class UnionApplyMatchAction extends AbstractParamAction implements ModelDriven<UnionApplyMatch> {

	
	private UnionApplyMatch unionApplyMatch = new UnionApplyMatch();
	
	private PageResultSet<UnionApplyMatch> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);
	
	public UnionFlowInfo params = new UnionFlowInfo();

	@Override
	public UnionApplyMatch getModel() {
		return unionApplyMatch;
	}

	public void setUnionApplyMatch(UnionApplyMatch unionApplyMatch) {
		this.unionApplyMatch = unionApplyMatch;
	}

	public PageResultSet<UnionApplyMatch> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<UnionApplyMatch> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="list",results={
			@Result(name="success",location="/union/matchApplyList.jsp"),
			@Result(name="approver",location="/union/matchApplyList_approver.jsp"),
			@Result(name="applicant",location="/union/matchApplyList_applicant.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		String g = this.request.getParameter("g");
		String matchId = this.request.getParameter("matchId");
		String matchName = this.request.getParameter("matchName");
		String applyStatus = this.request.getParameter("applyStatus");
		
		String pageNoString = this.request.getParameter("page");
		if(StringUtils.isEmpty(pageNoString) || "0".equals(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select am.ID,am.STATUS,am.USER_NAME,am.LOGIN_NAME,am.DEPT_NAME as APPLY_DEPT_NAME,m.ID as MATCH_ID,m.MATCH_NAME,m.MATCH_TYPE,m.BEGIN_DATE,m.END_DATE,m.DEPT_ID,m.DEPT_NAME,m.OPERATOR_ID,m.OPERATOR"
				+" from U_APPLICANT_MATCH am LEFT JOIN U_MATCH m on am.MATCH_ID = m.ID"
				+" where am.removed=0 and am.status!=0 ";
		String countHql = "select count(am.id) from U_APPLICANT_MATCH am LEFT JOIN U_MATCH m on am.MATCH_ID = m.ID where am.removed=0 and am.status!=0 ";
		
		String filterPart = "";
		if(StringUtils.isNotEmpty(matchName)){
			filterPart += " and m.MATCH_NAME like '%"+matchName+"%'";
		}
		if(StringUtils.isNotEmpty(applyStatus)){
			filterPart += " and am.STATUS = "+applyStatus;
		}
		
		if("approver".equals(g)){
			filterPart += " and exists(select id from U_APPROVAL where APPLY_ID = am.ID and STAGE = 2 and CHECK_USER_ID like '"+tloginName+"%')";
		}else if("applicant".equals(g)){
			filterPart += " and am.MATCH_ID = '"+matchId+"'";
		}else{
			filterPart += " and am.LOGIN_NAME like '"+tloginName+"%'";
		}
		
		String orderPart = " order by am.DEPT_ID, am.U_TIME desc";
		
		PageResultSet result = this.service.findPageResult(queryHql+filterPart+orderPart,countHql+filterPart,page, pageSize);
		
		this.request.setAttribute("pageResultSet", result);
		this.request.setAttribute("g", g);
		this.request.setAttribute("matchName", matchName);
		this.request.setAttribute("applyStatus", applyStatus);
		
		if(!StringUtils.isEmpty(g)){
			return g;
		}
		return SUCCESS;
	}
	
	@Action(value="listAllOfMatch",results={
			@Result(name="success",location="/union/applyMatchList.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String listAllOfMatch(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String matchId = this.request.getParameter("matchId");
		
		List<UnionApplyMatch> list = this.service.findByMatchId(matchId);
		return SUCCESS;
	}
	
	@Action(value="showAdd",results={
			@Result(name="success",location="/union/themeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);		
		
		String now = DateUtil.getNowTime();
		if(StringUtils.isEmpty(unionApplyMatch.getId())){
			this.unionApplyMatch.setcUser(loginName);
			this.unionApplyMatch.setcTime(now);			
		}
		
		this.unionApplyMatch.setuUser(loginName);
		this.unionApplyMatch.setuTime(now);
		
		unionApplyMatch = this.service.save(unionApplyMatch);
		
		this.aw.writeAjax("1");
		return null;
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
	
	private UnionApplyMatchService service;
	public UnionApplyMatchService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionApplyMatchService")UnionApplyMatchService service) {
		this.service = service;
	}
	
	public UnionApplyMatch getUnionApplyMatch() {
		return unionApplyMatch;
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
	
	private UnionMatchService matchService;
	public UnionMatchService getMatchService() {
		return matchService;
	}
	@Autowired(required=false)
	public void setMatchService(@Qualifier("unionMatchService")UnionMatchService matchService) {
		this.matchService = matchService;
	}
}
