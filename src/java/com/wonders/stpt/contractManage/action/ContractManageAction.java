/**
 * 
 */
package com.wonders.stpt.contractManage.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.contractManage.model.bo.HtxxStop;
import com.wonders.stpt.contractManage.model.vo.ContractManageVo;
import com.wonders.stpt.contractManage.service.ContractManageService;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;


/** 
 * @ClassName: ContractManageAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:37:12 
 *  
 */
@ParentPackage("struts-default")
@Namespace(value="/contractManage")
@Controller("contractManageAction")
@Scope("prototype")
public class ContractManageAction extends AbstractParamAction implements ModelDriven<ContractManageVo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1631323544896056619L;
	private ActionWriter aw = new ActionWriter(response);
	private ContractManageVo vo = new ContractManageVo();
	private ContractManageService service;
	public ContractManageService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("contractManageService")ContractManageService service) {
		this.service = service;
	}

	@Action(value="newList",results={
			@Result(name="success",location="/contractManage/newList.jsp")
	})
	public String findHtNewByPage(){

		String plan_num = this.request.getParameter("plan_num");
		String contract_num = this.request.getParameter("contract_num");
		String self_num = this.request.getParameter("self_num");
		String contract_name = this.request.getParameter("contract_name");
		String project_num = this.request.getParameter("project_num");
		String sign_cop = this.request.getParameter("sign_cop");
		String add_time_start = this.request.getParameter("add_time_start");
		String add_time_end = this.request.getParameter("add_time_end");
		String add_person = this.request.getParameter("add_person");
		String contract_money = this.request.getParameter("contract_money");
		String contract_money_end = this.request.getParameter("contract_money_end");
		String pstatus = this.request.getParameter("pstatus");

		this.request.setAttribute("plan_num", plan_num);
		this.request.setAttribute("contract_num", contract_num);
		this.request.setAttribute("self_num", self_num);
		this.request.setAttribute("contract_name", contract_name);
		this.request.setAttribute("project_num", project_num);
		this.request.setAttribute("sign_cop", sign_cop);
		this.request.setAttribute("add_time_start", add_time_start);
		this.request.setAttribute("add_time_end", add_time_end);
		this.request.setAttribute("add_person", add_person);
		this.request.setAttribute("contract_money", contract_money);
		this.request.setAttribute("contract_money_end", contract_money_end);
		this.request.setAttribute("pstatus", pstatus);

		int totalRows = this.service.countHtNew(plan_num, contract_num, self_num,
				contract_name, project_num, sign_cop, add_time_start, add_time_end,
				add_person, contract_money,contract_money_end, pstatus);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);
		List<Object[]> list = this.service.findHtNewByPage(pageinfo.getBeginIndex(), vo.pageSize,
				plan_num, contract_num, self_num,
				contract_name, project_num, sign_cop, add_time_start, add_time_end,
				add_person, contract_money,contract_money_end, pstatus);
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		return SUCCESS;
	}
	@Action(value="list",results={
			@Result(name="success",location="/contractManage/list.jsp")
			})
	public String findHtspOaByPage(){
		String plan_num = this.request.getParameter("plan_num");
		String contract_num = this.request.getParameter("contract_num");
		String self_num = this.request.getParameter("self_num");
		String contract_name = this.request.getParameter("contract_name");
		String project_num = this.request.getParameter("project_num");
		String sign_cop = this.request.getParameter("sign_cop");
		String add_time_start = this.request.getParameter("add_time_start");
		String add_time_end = this.request.getParameter("add_time_end");
		String add_person = this.request.getParameter("add_person");
		String contract_money = this.request.getParameter("contract_money");
		String pstatus = this.request.getParameter("pstatus");
		
		this.request.setAttribute("plan_num", plan_num);
		this.request.setAttribute("contract_num", contract_num);
		this.request.setAttribute("self_num", self_num);
		this.request.setAttribute("contract_name", contract_name);
		this.request.setAttribute("project_num", project_num);
		this.request.setAttribute("sign_cop", sign_cop);
		this.request.setAttribute("add_time_start", add_time_start);
		this.request.setAttribute("add_time_end", add_time_end);
		this.request.setAttribute("add_person", add_person);
		this.request.setAttribute("contract_money", contract_money);
		this.request.setAttribute("pstatus", pstatus);
		
		int totalRows = this.service.countHtspOa(plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<Object[]> list = this.service.findHtspOaByPage(pageinfo.getBeginIndex(), vo.pageSize,
				plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus);
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		return SUCCESS;
	}
	
	@Action(value="listView",results={
			@Result(name="success",location="/contractManage/listView.jsp")
			})
	public String findHtspOaViewByPage(){
		String plan_num = this.request.getParameter("plan_num");
		String contract_num = this.request.getParameter("contract_num");
		String self_num = this.request.getParameter("self_num");
		String contract_name = this.request.getParameter("contract_name");
		String project_num = this.request.getParameter("project_num");
		String sign_cop = this.request.getParameter("sign_cop");
		String add_time_start = this.request.getParameter("add_time_start");
		String add_time_end = this.request.getParameter("add_time_end");
		String add_person = this.request.getParameter("add_person");
		String contract_money = this.request.getParameter("contract_money");
		String pstatus = this.request.getParameter("pstatus");
		
		this.request.setAttribute("plan_num", plan_num);
		this.request.setAttribute("contract_num", contract_num);
		this.request.setAttribute("self_num", self_num);
		this.request.setAttribute("contract_name", contract_name);
		this.request.setAttribute("project_num", project_num);
		this.request.setAttribute("sign_cop", sign_cop);
		this.request.setAttribute("add_time_start", add_time_start);
		this.request.setAttribute("add_time_end", add_time_end);
		this.request.setAttribute("add_person", add_person);
		this.request.setAttribute("contract_money", contract_money);
		this.request.setAttribute("pstatus", pstatus);
		
		int totalRows = this.service.countHtspOaView(plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus,"ST/"+StringUtil.getNotNullValueString(this.request.getSession().getAttribute("cs_login_name")));
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<Object[]> list = this.service.findHtspOaViewByPage(pageinfo.getBeginIndex(), vo.pageSize,
				plan_num, contract_num, self_num,
				 contract_name, project_num, sign_cop, add_time_start, add_time_end,
				 add_person, contract_money, pstatus,"ST/"+StringUtil.getNotNullValueString(this.request.getSession().getAttribute("cs_login_name")));
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		return SUCCESS;
	}
	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public ContractManageVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	@Action(value="addReason")
	public String saveReason(){
		String reason = StringUtil.getNotNullTime(this.request.getParameter("reason"));
		String attach = StringUtil.getNotNullTime(this.request.getParameter("attach"));
		String process = StringUtil.getNotNullTime(this.request.getParameter("process"));
		String incident = StringUtil.getNotNullTime(this.request.getParameter("incident"));
		String operator = StringUtil.getNotNullTime(this.request.getParameter("operator"));
		String mainId = StringUtil.getNotNullTime(this.request.getParameter("mainId"));
		boolean flag = this.service.save(reason, attach, operator, mainId, process, incident);
		if(flag){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		return null;
	}

	@Action(value="getReason")
	public String getReason(){
		String mainId = StringUtil.getNotNullTime(this.request.getParameter("mainId"));
		List<HtxxStop> list = this.service.find(mainId);
		aw.writeJson(list);
		return null;
	}
}
