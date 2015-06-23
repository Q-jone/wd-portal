package com.wonders.stpt.lawScan.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.lawScan.model.bo.LawScan;
import com.wonders.stpt.lawScan.service.LawScanService;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.processInfo.model.TodoItemVo;
import com.wondersgroup.framework.core.web.struts2.action.BaseAjaxAction;

@ParentPackage("struts-default")
@Namespace(value="/lawScan")
@Controller("lawScanAction")
@Scope("prototype")
public class LawScanAction extends BaseAjaxAction{
	private static final long serialVersionUID = 1L;
	private TodoItemVo vo = new TodoItemVo();

	  public TodoItemVo getModel()
	  {
	    return this.vo;
	  }
	  
	@Autowired
	private LawScanService lawScanService;
	
	@Action(value="/findLawScanContractByPage",results={@Result(name="success",location="/lawScan/list.jsp")})
	public String findLawScanContractByPage(){
		String ifRead = this.servletRequest.getParameter("ifRead");
		String plan_num = this.servletRequest.getParameter("plan_num");
	    String contract_num = this.servletRequest.getParameter("contract_num");
	    String self_num = this.servletRequest.getParameter("self_num");
	    String contract_name = this.servletRequest.getParameter("contract_name");
	    String project_num = this.servletRequest.getParameter("project_num");
	    String sign_cop = this.servletRequest.getParameter("sign_cop");
	    String add_time_start = this.servletRequest.getParameter("add_time_start");
	    String add_time_end = this.servletRequest.getParameter("add_time_end");
	    String add_person = this.servletRequest.getParameter("add_person");
	    String contract_money = this.servletRequest.getParameter("contract_money");
	    String pstatus = this.servletRequest.getParameter("pstatus");

	    this.servletRequest.setAttribute("ifRead", ifRead);
	    this.servletRequest.setAttribute("plan_num", plan_num);
	    this.servletRequest.setAttribute("contract_num", contract_num);
	    this.servletRequest.setAttribute("self_num", self_num);
	    this.servletRequest.setAttribute("contract_name", contract_name);
	    this.servletRequest.setAttribute("project_num", project_num);
	    this.servletRequest.setAttribute("sign_cop", sign_cop);
	    this.servletRequest.setAttribute("add_time_start", add_time_start);
	    this.servletRequest.setAttribute("add_time_end", add_time_end);
	    this.servletRequest.setAttribute("add_person", add_person);
	    this.servletRequest.setAttribute("contract_money", contract_money);
	    this.servletRequest.setAttribute("pstatus", pstatus);

	    int totalRows = this.lawScanService.countLawScanContract(plan_num, contract_num, self_num, 
	      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
	      add_person, contract_money, pstatus,ifRead);
	    PageInfo pageinfo = new PageInfo(totalRows, this.vo.pageSize, this.vo.page);
	    List list = this.lawScanService.findLawScanContractList(pageinfo.getBeginIndex(), this.vo.pageSize, 
	      plan_num, contract_num, self_num, 
	      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
	      add_person, contract_money, pstatus,ifRead);

	    PageResultSet result = new PageResultSet();
	    result.setList(list);
	    result.setPageInfo(pageinfo);
	    this.servletRequest.setAttribute("result", result);
		return "success";
	}
	
	@Action(value="/read")
	public String read(){
		String cname = this.servletRequest.getParameter("cname");
		String cid = this.servletRequest.getParameter("cid");
		String pname = this.servletRequest.getParameter("pname");
		String pid = this.servletRequest.getParameter("pid");
		if(!this.lawScanService.findIfExists(cname, cid)){
			LawScan bo = new LawScan();
			bo.setCname(cname);
			bo.setCid(cid);
			bo.setPname(pname);
			bo.setPid(pid);
			bo.setReadDate(new Date());
			this.lawScanService.save(bo);
		}
		return null;
	}
}
