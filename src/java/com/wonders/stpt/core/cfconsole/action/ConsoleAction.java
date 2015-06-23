package com.wonders.stpt.core.cfconsole.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.core.cfconsole.entity.vo.UserVo;
import com.wonders.stpt.core.cfconsole.service.ConsoleService;
import com.wonders.stpt.core.cfconsole.util.ConsoleCheckUtil;
import com.wonders.stpt.core.login.entity.bo.TuserRelation;
import com.wondersgroup.framework.organization.bo.OrganNode;
import com.wondersgroup.framework.organization.service.OrganNodeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import com.wondersgroup.framework.security.service.UserService;


@SuppressWarnings("serial")
@ParentPackage("cuteframework-default")
@Namespace(value="/cfconsole")
@Controller("consoleAction")
@Scope("prototype")
public class ConsoleAction extends AbstractParamAction implements ModelDriven<UserVo>
{
	private ConsoleService consoleService;
	public ConsoleService getConsoleService() {
		return consoleService;
	}
	@Autowired(required=false)
	public void setConsoleService(@Qualifier("consoleService")ConsoleService consoleService) {
		this.consoleService = consoleService;
	}
	private List<TuserRelation> list;
	
	public List<TuserRelation> getList() {
		return list;
	}

	public void setList(List<TuserRelation> list) {
		this.list = list;
	}


	private UserVo vo = new UserVo();
	@Override
	public UserVo getModel() {
		return vo;
	}
	
	public UserVo getVo() {
		return vo;
	}

	public void setVo(UserVo vo) {
		this.vo = vo;
	}

	
	
	//Cuser 新增 并插入所属部门，关联群组
	
	
	@Action(value="addUser",results={
			@Result(name="success",location="/cfconsole/manage.jsp",type="redirect")
			})	
	public String addUser(){
		if(list!=null&&list.size()>0){
			this.consoleService.relationAddPatch(list);
		}
		return "success";
	}
	
	
	
	
	 	

}
