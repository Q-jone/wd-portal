package com.wonders.stpt.union.action;

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
import com.wonders.stpt.union.entity.bo.UnionAccountApply;
import com.wonders.stpt.union.entity.vo.UnionFlowInfo;
import com.wonders.stpt.union.service.UnionAccountApplyService;
import com.wonders.stpt.union.service.UnionOrganService;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.StringUtil;

/**
 * 工会立功竞赛
 *
 */
@SuppressWarnings("serial")
@ParentPackage("struts-default")
@Namespace(value="/unionAccountApply")
@Controller("unionAccountApplyAction")
@Scope("prototype")
public class UnionAccountApplyAction extends AbstractParamAction implements ModelDriven<UnionAccountApply> {

	private UnionAccountApply unionAccountApply = new UnionAccountApply();
	
	private PageResultSet<UnionAccountApply> pageResultSet;
	
	private ActionWriter aw = new ActionWriter(response);
	
	public UnionFlowInfo params = new UnionFlowInfo();

	@Override
	public UnionAccountApply getModel() {
		return unionAccountApply;
	}

	public void setUnionAccountApply(UnionAccountApply unionAccountApply) {
		this.unionAccountApply = unionAccountApply;
	}

	public PageResultSet<UnionAccountApply> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<UnionAccountApply> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	@Action(value="list",results={
			@Result(name="success",location="/union/account/accountApplyList.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String findPage(){
		String tloginName = (String)this.request.getSession().getAttribute(LoginConstant.STPT_SECURITY_LOGIN_NAME);
		String deptId = (String)this.request.getSession().getAttribute(LoginConstant.USER_DEPT_ID);
		
		String g = this.request.getParameter("g");
		String pageNoString = this.request.getParameter("page");
		
		String name = this.request.getParameter("name");
		String loginName = this.request.getParameter("loginName");
		
		if(StringUtils.isEmpty(pageNoString) || "0".equals(pageNoString)){
			pageNoString = "1";
		}
		int page = Integer.valueOf(pageNoString);		//页码
		int pageSize = 10;								//每页显示

		String queryHql = "select t.id,t.LOGIN_NAME,t.NAME,t.DEPT_ID,t.DEPT_NAME,t.STATUS"
				+" from U_ACCOUNT_APPLY t where t.removed=0";
		String countHql = "select count(t.id) from U_ACCOUNT_APPLY t where t.removed=0";
		
		String filterPart = "";
		String orderPart = "";
		
		if(StringUtils.isNotEmpty(name)){
			filterPart += " and t.NAME like '%"+name+"%'";
		}
		if(StringUtils.isNotEmpty(loginName)){
			filterPart += " and t.LOGIN_NAME like '%"+loginName+"%'";
		}
		
		if("admin".equals(g)){
			filterPart += "";
			orderPart += " order by t.STATUS, t.U_TIME desc";
		}else{
			filterPart += " and t.C_USER like '"+tloginName+"%'";
			orderPart += " order by t.U_TIME desc";			
		}
		
		PageResultSet result = this.service.findPageResult(queryHql+filterPart+orderPart,countHql+filterPart,page, pageSize);
		
		this.request.setAttribute("pageResultSet", result);
		this.request.setAttribute("g", g);
		this.request.setAttribute("name", name);
		this.request.setAttribute("loginName", loginName);
		
		return SUCCESS;
	}
	
	@Action(value="showAdd",results={
			@Result(name="success",location="/union/account/accountApplyAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String add(){
		List<Map> depts = this.organService.getDirectDepts();
		
		this.request.setAttribute("depts", depts);
		return SUCCESS;
	}
	
	@Action(value="showEdit",results={
			@Result(name="success",location="/union/account/accountApplyAdd.jsp"),
			@Result(name="error",location="/404.jsp")
			})
	public String showEdit(){
		String id = this.request.getParameter("id");
		UnionAccountApply accountApply = service.find(id);
		
		List<Map> depts = this.organService.getDirectDepts();
		
		this.request.setAttribute("depts", depts);
		this.request.setAttribute("accountApply", accountApply);
		return SUCCESS;
	}
	
	@Action(value="save")
	public String save(){
		String loginName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_LOGIN_NAME);
		String userName = (String)this.request.getSession().getAttribute(LoginConstant.SECURITY_USER_NAME);		
		
		String now = DateUtil.getNowTime();
		if(StringUtils.isEmpty(unionAccountApply.getId())){
			this.unionAccountApply.setcUser(loginName);
			this.unionAccountApply.setcTime(now);			
		}
		
		this.unionAccountApply.setuUser(loginName);
		this.unionAccountApply.setuTime(now);
		
		unionAccountApply = this.service.save(unionAccountApply);
		
		this.aw.writeAjax("1");
		return null;
	}
	
	@Action(value="doDel")
	public String doDel(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		
		this.service.logicDelete(id);
		return null;
	}
	
	@Action(value="changeStatus")
	public String changeStatus(){
		String id = StringUtil.getNotNullValueString(request.getParameter("id"));
		
		this.service.updateStatus(id, 1);
		return null;
	}
	
	private UnionAccountApplyService service;
	public UnionAccountApplyService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("unionAccountApplyService")UnionAccountApplyService service) {
		this.service = service;
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
