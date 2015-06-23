/**
 * 
 */
package com.wonders.stpt.contractReview.action;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.contractReview.model.bo.WorkflowActivity;
import com.wonders.stpt.contractReview.model.vo.ContractReviewVo;
import com.wonders.stpt.contractReview.service.ContractReviewService;
import com.wonders.stpt.contractReview.util.ContractReviewUtil;
import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;


/** 
 * @ClassName: ContractReviewAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:37:12 
 *  
 */
@ParentPackage("custom-default")
@Namespace(value="/contractReview")
@Controller("contractReviewAction")
@Scope("prototype")
public class ContractReviewAction extends AbstractParamAction implements ModelDriven<ContractReviewVo>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1631323544896056619L;
	private ActionWriter aw = new ActionWriter(response);
	private ContractReviewVo vo = new ContractReviewVo();
	private ContractReviewService service;
	public ContractReviewService getService() {
		return service;
	}
	@Autowired(required=false)
	public void setService(@Qualifier("contractReviewService")ContractReviewService service) {
		this.service = service;
	}
	
	
	@Action(value="batchFinish")
	public String batchFinish(){
		String ids = StringUtil.getNotNullValueString(request.getParameter("ids"));
		String operators = StringUtil.getNotNullValueString(request.getParameter("operators"));
		String steps = StringUtil.getNotNullValueString(request.getParameter("steps"));
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		String[] a = ids.split(",");
		String[] b = operators.split(",");
		String[] c = steps.split(",");
		
		for(int i=0;i<a.length;i++){
			List<String> list = new ArrayList<String>();
			list.add(b[i]);list.add(c[i]);
			map.put(a[i], list);
		}
		
		if(this.service.saveBatch(ids, map)){
			aw.writeAjax("1");
		}else{
			aw.writeAjax("0");
		}
		
		return null;
	}
	

	@Action(value="getTypeByCode")
	public String getTypeByCode(){
		String code = StringUtil.getNotNullValueString(request.getParameter("code"));
		List<Map<String,Object>> list = ContractReviewUtil.getTypeByCode(code);
		aw.writeJson(list);
		return null;
	}
	
	@Action(value="getTypeByPid")
	public String getTypeByPid(){
		String pid = StringUtil.getNotNullValueString(request.getParameter("pid"));
		List<Map<String,Object>> list = ContractReviewUtil.getTypeByPid(pid);
		aw.writeJson(list);
		return null;
	}
	
	@Action(value="getKPI")
	public String getKPI(){
		List<Map<String,Object>> list = ContractReviewUtil.getKPI();
		aw.writeJson(list);
		return null;
	}
	

	@Action(value="list",results={
			@Result(name="success",location="/contractReview/list.jsp")
			})
	public String findHtspOaByPage(){
		String contract_num = this.request.getParameter("contract_num");
		String contract_name = this.request.getParameter("contract_name");
		String contract_money = this.request.getParameter("contract_money");
		String opposite_company = this.request.getParameter("opposite_company");
		String project_charge = this.request.getParameter("project_charge");
		String sign_time_start = this.request.getParameter("sign_time_start");
		String sign_time_end = this.request.getParameter("sign_time_end");
		String pstatus = this.request.getParameter("pstatus");
		String projectChargeDept = this.request.getParameter("projectChargeDept");
		
		this.request.setAttribute("contract_num", contract_num);
		this.request.setAttribute("contract_name", contract_name);
		this.request.setAttribute("contract_money", contract_money);
		this.request.setAttribute("opposite_company", opposite_company);
		this.request.setAttribute("project_charge", project_charge);
		this.request.setAttribute("sign_time_start",sign_time_start);
		this.request.setAttribute("sign_time_end", sign_time_end);
		this.request.setAttribute("pstatus", pstatus);
		this.request.setAttribute("projectChargeDept", projectChargeDept);
		
		int totalRows = this.service.countHtspOa(contract_num,
				 contract_name, contract_money, opposite_company, project_charge,
				 sign_time_start,sign_time_end, pstatus,projectChargeDept);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<Object[]> list = this.service.findHtspOaByPage(pageinfo.getBeginIndex(), vo.pageSize,
				contract_num,
				 contract_name, contract_money, opposite_company, project_charge,
				 sign_time_start,sign_time_end, pstatus,projectChargeDept);
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		return SUCCESS;
	}
	
	
	@SuppressWarnings({ "unchecked"})
	@Action(value="todoList",results={
			@Result(name="success",location="/contractReview/todoList.jsp")
			},
			interceptorRefs = {@InterceptorRef(value = "mydefault")}  )
	// 拦截器  
	
	public String todoItemList(){
		String loginNames = "";
		Map<String, TaskUserVo> userMap = 
				(Map<String, TaskUserVo>)this.request.getSession().getAttribute("deptUsers");
		for(Map.Entry<String, TaskUserVo> entry : userMap.entrySet()){
			loginNames += "'"+StringUtil.getNotNullValueString(entry.getKey()) +"'"+",";
		}
		if(loginNames.length() > 0){
			loginNames = loginNames.substring(0, loginNames.length()-1);
		}
		String contract_name = this.request.getParameter("contract_name");
		String contract_identifier = this.request.getParameter("contract_identifier");
		String contract_money_type_id = this.request.getParameter("contract_money_type_id");
		String contract_type1_id = this.request.getParameter("contract_type1_id");
		String contract_type2_id = this.request.getParameter("contract_type2_id");
		String contract_moneyLt = this.request.getParameter("contract_moneyLt");
		String contract_moneyGt = this.request.getParameter("contract_moneyGt");
		String project_charge_dept = this.request.getParameter("project_charge_dept");
		String contract_group_id = this.request.getParameter("contract_group_id");
		String purchase_type_id = this.request.getParameter("purchase_type_id");
		String[] kpi_controls = this.request.getParameterValues("kpi_control");
		String kpi_control = "";
		if(kpi_controls != null && kpi_controls.length > 0){
			for(String temp : kpi_controls){
				kpi_control += temp + ",";
			}
			kpi_control = kpi_control.substring(0, kpi_control.length()-1);
		}
		
		this.request.setAttribute("contract_name", contract_name);
		this.request.setAttribute("contract_identifier", contract_identifier);
		this.request.setAttribute("contract_money_type_id", contract_money_type_id);
		this.request.setAttribute("contract_type1_id", contract_type1_id);
		this.request.setAttribute("contract_type2_id", contract_type2_id);
		this.request.setAttribute("contract_moneyLt",contract_moneyLt);
		this.request.setAttribute("contract_moneyGt", contract_moneyGt);
		this.request.setAttribute("project_charge_dept", project_charge_dept);
		this.request.setAttribute("contract_group_id", contract_group_id);
		this.request.setAttribute("purchase_type_id", purchase_type_id);
		this.request.setAttribute("kpi_control", kpi_control);

		int totalRows = this.service.countTodo(contract_name,contract_identifier, 
				contract_money_type_id, contract_type1_id, 
				contract_type2_id, contract_moneyLt, 
				contract_moneyGt, project_charge_dept, 
				contract_group_id, purchase_type_id, 
				kpi_control,loginNames);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<Object[]> list = this.service.findTodoByPage(pageinfo.getBeginIndex(), vo.pageSize,
				contract_name,
				contract_identifier, 
				contract_money_type_id, contract_type1_id, 
				contract_type2_id, contract_moneyLt, 
				contract_moneyGt, project_charge_dept, 
				contract_group_id, purchase_type_id, 
				kpi_control,loginNames);
		//System.out.println("list.size======"+list.size());
		PageResultSet<Object[]> result  = new PageResultSet<Object[]>();
		result.setList(list);
		result.setPageInfo(pageinfo);
		this.request.setAttribute("result", result);
		
		return "success";
	}
	
	/** 
	* @Title: getModel 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @throws 
	*/
	@Override
	public ContractReviewVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	@Action(value="findContractReviewByType",results={
			@Result(name="success",location="/contractReview/contractReviewCover.jsp")
			})
	public String findContractReviewByType(){
		String type = request.getParameter("type");
		if(type==null || "".equals(type)){
			type = "1";
		}
		List result = service.getContractReviewDataByType(type);
		request.setAttribute("result", result);
		request.setAttribute("type", type);
		return "success";
	}
	
}
