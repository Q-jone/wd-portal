package com.wonders.stpt.union.action;

import java.util.Calendar;

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
import com.wonders.stpt.union.entity.bo.UnionTheme;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionApprovalService;
import com.wonders.stpt.union.service.UnionMatchService;
import com.wonders.stpt.union.service.UnionThemeService;
import com.wonders.stpt.union.util.UnionProcessUtil;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;

/**
 * 工会立功竞赛
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionTheme")
@Controller("unionThemeAction")
@Scope("prototype")
public class UnionThemeAction extends AbstractParamAction implements ModelDriven<UnionTheme> {

	private UnionTheme unionTheme = new UnionTheme();
	
	private PageResultSet<UnionTheme> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);
	
	public UnionFlowInfo params = new UnionFlowInfo();

	@Override
	public UnionTheme getModel() {
		return unionTheme;
	}

	public void setUnionTheme(UnionTheme unionTheme) {
		this.unionTheme = unionTheme;
	}

	public PageResultSet<UnionTheme> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<UnionTheme> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="list",results={
			@Result(name="success",location="/union/themeList.jsp"),
			@Result(name="leader",location="/union/themeList_leader.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String g = this.request.getParameter("g");
		String pageNoString = this.request.getParameter("page");
		
		if(StringUtils.isEmpty(pageNoString) || "0".equals(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.id,t.YEAR,t.THEME_NAME,t.STATUS,t.HANDLER_ID"
				+" from U_THEME t where t.removed=0";
		String countHql = "select count(t.id) from U_THEME t where t.removed=0";
		
		String filterPart = "";
		String orderPart = " order by t.YEAR desc,t.U_TIME desc";
		
		PageResultSet result = this.service.findPageResult(queryHql+filterPart+orderPart,countHql+filterPart,page, pageSize);
		
		this.request.setAttribute("g", g);
		this.request.setAttribute("pageResultSet", result);
		
		if(StringUtils.isNotEmpty(g) && g.endsWith("Leader")){
			return "leader";
		}
		
		return SUCCESS;
	}
	
	@Action(value="showAdd",results={
			@Result(name="success",location="/union/themeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		request.setAttribute("thisYear", Calendar.getInstance().get(Calendar.YEAR));
		return SUCCESS;
	}
	
	@Action(value="showEdit",results={
			@Result(name="success",location="/union/themeAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showEdit(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
				
		String id = this.request.getParameter("id");
		UnionTheme theme = service.find(id);
		
		request.setAttribute("thisYear", Calendar.getInstance().get(Calendar.YEAR));
		this.request.setAttribute("theme", theme);
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);		
		
		String now = DateUtil.getNowTime();
		if(StringUtils.isEmpty(unionTheme.getId())){
			this.unionTheme.setcUser(loginName);
			this.unionTheme.setcTime(now);
			this.unionTheme.setuUser(loginName);
			this.unionTheme.setuTime(now);
			
			this.service.save(unionTheme);
		}else{
			UnionTheme theme = this.service.find(unionTheme.getId());
			theme.setThemeName(unionTheme.getThemeName());
			theme.setYear(unionTheme.getYear());
			theme.setuUser(loginName);
			theme.setuTime(now);
			
			this.service.save(theme);
		}
		
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
	
	private UnionThemeService service;
	public UnionThemeService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionThemeService")UnionThemeService service) {
		this.service = service;
	}
	
	public UnionTheme getUnionTheme() {
		return unionTheme;
	}

	private UnionMatchService matchService;
	public UnionMatchService getMatchService() {
		return matchService;
	}
	@Autowired(required=false)
	public void setMatchService(@Qualifier("unionMatchService")UnionMatchService matchService) {
		this.matchService = matchService;
	}
	
	private UnionApprovalService approvalService;
	public UnionApprovalService getApprovalService() {
		return approvalService;
	}
	@Autowired(required=false)
	public void setApprovalService(@Qualifier("unionApprovalService")UnionApprovalService approvalService) {
		this.approvalService = approvalService;
	}
}
