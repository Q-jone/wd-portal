/**
 * 
 */
package com.wonders.stpt.htxx.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.htxx.model.vo.HtxxVo;
import com.wonders.stpt.htxx.service.HtxxService;
import com.wonders.stpt.page.model.PageInfo;
import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.pageNew.service.PageNewService;
import com.wonders.stpt.userMsg.action.AbstractParamAction;

/** 
 * @ClassName: HtxxAction 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-5-15 下午2:12:27 
 *  
 */

@ParentPackage("struts-default")
@Namespace(value="/htxx")
@Controller("htxxAction")
@Scope("prototype")
public class HtxxAction extends AbstractParamAction implements ModelDriven<HtxxVo>{

	private static final long serialVersionUID = -661190421108027253L;
	private PageNewService pageNewService;
	private HtxxService htxxService;
	private HtxxVo vo = new HtxxVo();
	
	@Override
	public HtxxVo getModel() {
		// TODO Auto-generated method stub
		return vo;
	}
	
	
	public void setHtxxService(@Qualifier(value="htxxService")HtxxService htxxService) {
		this.htxxService = htxxService;
	}

	public PageNewService getPageNewService() {
		return pageNewService;
	}

	@Autowired(required = false)
	public void setPageNewService(@Qualifier(value = "pageNewService")PageNewService pageNewService) {
		this.pageNewService = pageNewService;
	}

	private PageResultSet<Map<String,String>> pageResultSet;

	public PageResultSet<Map<String, String>> getPageResultSet() {
		return pageResultSet;
	}

	public void setPageResultSet(PageResultSet<Map<String, String>> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	
	
	@Action(value="list",results={
			@Result(name="success",location="/htxx/list.jsp")
			})
	public String list(){
		String showOrHide = servletRequest.getParameter("showOrHide");
		servletRequest.setAttribute("showOrHide", showOrHide);
		
		String projectNo = servletRequest.getParameter("projectNo");
		if(isSQLInject(projectNo)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectNo", projectNo);
			return "success";
		}
		String projectName = servletRequest.getParameter("projectName");
		if(isSQLInject(projectName)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectName", projectName);
			return "success";
		}
		String contract_owner_execute_name = servletRequest.getParameter("contract_owner_execute_name");
		if(isSQLInject(contract_owner_execute_name)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contract_owner_execute_name", contract_owner_execute_name);
			return "success";
		}
		String contractNo = servletRequest.getParameter("contractNo");
		if(isSQLInject(contractNo)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractNo", contractNo);
			return "success";
		}
		String contractName = servletRequest.getParameter("contractName");
		if(isSQLInject(contractName)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractName", contractName);
			return "success";
		}
		String contractType = servletRequest.getParameter("contractType");
		if(isSQLInject(contractType)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractType", contractType);
			return "success";
		}
		String contractSignedDate = servletRequest.getParameter("contractSignedDate");
		if(isSQLInject(contractSignedDate)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractSignedDate",contractSignedDate );
			return "success";
		}
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		if(isSQLInject(contractSignedEndDate)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
			return "success";
		}
		String stat_date = servletRequest.getParameter("stat_date");
		if(isSQLInject(stat_date)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("stat_date", stat_date);
			return "success";
		}
		String stat_status = servletRequest.getParameter("stat_status");		//1:数据不为0，2:数据为0
		if(isSQLInject(stat_status)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("stat_status", stat_status);
			return "success";
		}
		String projectApproveStart = servletRequest.getParameter("projectApproveStart");		//项目批文开始时间
		if(isSQLInject(projectApproveStart)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectApproveStart", projectApproveStart);
			return "success";
		}
		String projectApproveEnd = servletRequest.getParameter("projectApproveEnd");			//项目批文结束时间
		if(isSQLInject(projectApproveEnd)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectApproveEnd", projectApproveEnd);
			return "success";
		}
		
		/*String sql = "select c.contract_no,c.contract_name,c.contract_type," +
				" c.contract_owner_execute_name,c.build_supplier_name," +
				" c.contract_signed_date,c.contract_price," +
				" t.stat_date," +
				" t.process_count_month,t.process_count_year,t.process_count_total," +
				" t.change_count_month,t.change_count_year,t.change_count_total," +
				" t.change_money_month,t.change_money_year,t.change_money_total," +
				" t.change_percent_month,t.change_percent_year,t.change_percent_total," +
				" t.pay_plan_money_month,t.pay_plan_money_year,t.pay_plan_money_total," +
				" t.pay_plan_percent_month,t.pay_plan_percent_year,t.pay_plan_percent_total," +
				" t.pay_actual_money_month,t.pay_actual_money_year,t.pay_actual_money_total," +
				" t.pay_actual_percent_month,t.pay_actual_percent_year,t.pay_actual_percent_total," +
				" c.project_no,c.project_name,c.invite_bid_type,c.final_price" +
				" from dw_contract_info  t , c_contract c where c.removed=0 and t.contract_id=c.id";*/
		String sql = "select c.contract_no,c.contract_name,c.contract_type," +
				" c.contract_owner_execute_name,c.build_supplier_name," +
				" c.contract_signed_date,c.contract_price," +
				" t.stat_date," +
				" t.process_count_month,t.process_count_year,t.process_count_total," +
				" t.change_count_month,t.change_count_year,t.change_count_total," +
				" t.change_money_month,t.change_money_year,t.change_money_total," +
				" t.change_percent_month,t.change_percent_year,t.change_percent_total," +
				" t.pay_plan_money_month,t.pay_plan_money_year,t.pay_plan_money_total," +
				" t.pay_plan_percent_month,t.pay_plan_percent_year,t.pay_plan_percent_total," +
				" t.pay_actual_money_month,t.pay_actual_money_year,t.pay_actual_money_total," +
				" t.pay_actual_percent_month,t.pay_actual_percent_year,t.pay_actual_percent_total," +
				" c.project_no,c.project_name,c.invite_bid_type,c.final_price" +
				" from dw_contract_info  t , c_contract c,c_project p where c.removed=0 and t.contract_id=c.id and p.removed=0 and c.project_id=p.id";
		
		if(projectApproveStart!=null && !"".equals(projectApproveStart)){
			sql += " and p.approval_date >='"+projectApproveStart+"'";
			servletRequest.setAttribute("projectApproveStart", projectApproveStart);
		}
		if(projectApproveEnd!=null && !"".equals(projectApproveEnd)){
			sql += " and p.approval_date <='"+projectApproveEnd+"'";
			servletRequest.setAttribute("projectApproveEnd", projectApproveEnd);
		}
		if(projectNo!=null && !"".equals(projectNo)){
			sql += " and c.project_no like '%"+projectNo+"%'";
			servletRequest.setAttribute("projectNo",projectNo);
		}
		if(projectName!=null && !"".equals(projectName)){
			sql += " and c.project_name like '%"+projectName+"%'";
			servletRequest.setAttribute("projectName",projectName);
		}
		
		if(contract_owner_execute_name!=null && !"".equals(contract_owner_execute_name)){
			sql += " and c.contract_owner_execute_name like '%"+contract_owner_execute_name.trim()+"%'";
			servletRequest.setAttribute("contract_owner_execute_name", contract_owner_execute_name);
		}
		if(contractNo!=null && !"".equals(contractNo)){
			sql += " and c.contract_no like '%"+contractNo.trim()+"%'";
			servletRequest.setAttribute("contractNo", contractNo);
		}
		if(contractName!=null && !"".equals(contractName)){
			sql += " and c.contract_name like '%"+contractName.trim()+"%'";
			servletRequest.setAttribute("contractName", contractName);
		}
		if(contractType!=null && !"".equals(contractType)){
			sql += " and c.contract_type like '"+contractType.trim()+"%'";
			servletRequest.setAttribute("contractType", contractType);
		}
		if(contractSignedDate!=null && !"".equals(contractSignedDate)){
			sql += " and c.contract_signed_date >= '"+contractSignedDate.trim()+"'";
			servletRequest.setAttribute("contractSignedDate", contractSignedDate);
		}
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			sql += " and c.contract_signed_date <= '"+contractSignedEndDate.trim()+"'";
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
		}
		
		if(stat_status!=null && !"".equals(stat_status)){
			if("1".equals(stat_status)){
				sql += " and (t.change_count_total!='0' or t.change_money_total!='0' or t.change_percent_total!='0'" +
				//" or t.pay_plan_money_month!='0' " +
				" or t.pay_plan_money_year!='0' or t.pay_plan_money_total!='0'" +
				" or t.pay_plan_percent_total!='0' " +
				//" or t.pay_actual_money_month!='0' " +
				" or t.pay_actual_money_year!='0'" +
				" or t.pay_actual_money_total!='0' or t.pay_actual_percent_total!='0')";
			}else{
				sql += " and t.change_count_total='0' and t.change_money_total='0' and t.change_percent_total='0'" +
				//" and t.pay_plan_money_month='0' " +
				" and t.pay_plan_money_year='0' and t.pay_plan_money_total='0'" +
				" and t.pay_plan_percent_total='0' " +
				//"and t.pay_actual_money_month='0' " +
				" and t.pay_actual_money_year='0'" +
				" and t.pay_actual_money_total='0' and t.pay_actual_percent_total='0'";
			}
			servletRequest.setAttribute("stat_status", stat_status);
		}
		if(stat_date!=null && !"".equals(stat_date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(sdf.parse(stat_date));
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
				String maxDate = sdf.format(calendar.getTime());
				String tempSql = sql +" and t.stat_date<='"+maxDate+"' order by t.stat_date DESC";

				List<String[]> list = this.pageNewService.findPaginationInfo(tempSql, 0, 1);
				if(list!=null && list.size()>0 && list.get(0)[7]!=null){
					sql += " and t.stat_date='"+list.get(0)[7]+"'";
				}else{
					sql += " and t.stat_date='"+maxDate+"'";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			servletRequest.setAttribute("stat_date", stat_date);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
			String maxDate = sdf.format(calendar.getTime());
			String tempSql = sql +" and t.stat_date<='"+maxDate+"' order by t.stat_date DESC";

			List<String[]> list = this.pageNewService.findPaginationInfo(tempSql, 0, 1);
			if(list!=null && list.size()>0 && list.get(0)[7]!=null){
				sql += " and t.stat_date='"+list.get(0)[7]+"'";
			}else{
				sql += " and t.stat_date='"+maxDate+"'";
			}
			servletRequest.setAttribute("stat_date", stat_date);
		}
//		sql += " order by c.contract_signed_date DESC";
		sql += " order by c.project_no DESC";
		int totalRows = (int) this.pageNewService.countBySql(sql);
//System.out.println(totalRows);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<String[]> list = this.pageNewService.findPaginationInfo(sql, pageinfo.getBeginIndex(), vo.pageSize);
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		List<List<Map<String,Object>>> projectList = new ArrayList<List<Map<String,Object>>>();
		Map<String, String> inviteBidMap = new HashMap<String, String>();
		inviteBidMap.put("1", "公开招标");
		inviteBidMap.put("2", "采购平台");
		inviteBidMap.put("3", "直接委托 ");
		Map<String, String > moneySourceTypeMap = new HashMap<String,String>();
		moneySourceTypeMap.put("1", "建设资金");
		moneySourceTypeMap.put("2", "自有资金");
		moneySourceTypeMap.put("3", "财政资金");
		Map<String, String> projectTypeMap = new HashMap<String,String>();
		projectTypeMap.put("1", "外部项目");
		projectTypeMap.put("2", "内部项目");
		projectTypeMap.put("3", "内部项目");
		projectTypeMap.put("4", "内部项目");
		
		for(String[] s : list){
			Map<String,String> map = new HashMap<String,String>();
			map.put("contract_no", s[0]);map.put("contract_name", s[1]);map.put("contract_type", s[2]);
			map.put("contract_owner_execute_name", s[3]);map.put("build_supplier_name", s[4]);map.put("contract_signed_date", s[5]);
			map.put("contract_price", s[6]);map.put("stat_date", s[7]);
			map.put("process_count_month", s[8]);map.put("process_count_year", s[9]);map.put("process_count_total", s[10]);
			map.put("change_count_month", s[11]);map.put("change_count_year", s[12]);map.put("change_count_total", s[13]);
			map.put("change_money_month", s[14]);map.put("change_money_year", s[15]);map.put("change_money_total", s[16]);
			map.put("change_percent_month", s[17]);map.put("change_percent_year", s[18]);map.put("change_percent_total", s[19]);
			map.put("pay_plan_money_month", s[20]);map.put("pay_plan_money_year", s[21]);map.put("pay_plan_money_total", s[22]);
			map.put("pay_plan_percent_month", s[23]);map.put("pay_plan_percent_year", s[24]);map.put("pay_plan_percent_total", s[25]);
			map.put("pay_actual_money_month", s[26]);map.put("pay_actual_money_year", s[27]);map.put("pay_actual_money_total", s[28]);
			map.put("pay_actual_percent_month", s[29]);map.put("pay_actual_percent_year", s[30]);map.put("pay_actual_percent_total", s[31]);
			map.put("project_no", s[32]);map.put("project_name", s[33]);
			if(s[34]==null || "".equals(s[34])){
				map.put("invite_bid_type", "");
			}else{
				map.put("invite_bid_type", inviteBidMap.get(s[34]));
			}
			map.put("final_price", s[35]);
			String sql2 ="select t.project_type,t.money_source_type,t.project_budget_all from c_project t where t.removed='0' and t.project_no='"+s[32]+"'";
			List<Map<String, Object>> tempList = htxxService.executeSQl(sql2);
			if(tempList!=null && tempList.size()>0){
				if(tempList.get(0).get("MONEY_SOURCE_TYPE")!=null && !"".equals(tempList.get(0).get("MONEY_SOURCE_TYPE"))){
					tempList.get(0).put("MONEY_SOURCE_TYPE", moneySourceTypeMap.get(tempList.get(0).get("MONEY_SOURCE_TYPE")));
				}
				if(tempList.get(0).get("PROJECT_TYPE")!=null && !"".equals(tempList.get(0).get("PROJECT_TYPE"))){
					tempList.get(0).put("PROJECT_TYPE", projectTypeMap.get(tempList.get(0).get("PROJECT_TYPE")));
				}
			}
			projectList.add(tempList);
			result.add(map);
		}
		servletRequest.setAttribute("projectList", projectList);
		//sql2。project_type,1:外部项目，(2,3,4)内部项目,5:其他项目
		//money_source_type,1:建设资金，2：自有资金，3:财政资金
		this.pageResultSet = new PageResultSet<Map<String, String>>();
		pageResultSet.setList(result);
		pageResultSet.setPageInfo(pageinfo);
		return "success";
	}
	
	
	@Action(value="projectList",results={
			@Result(name="success",location="/htxx/projectList.jsp")
			})
	public String projectList(){
		
		String showOrHide = servletRequest.getParameter("showOrHide");
		servletRequest.setAttribute("showOrHide", showOrHide);
		
		String projectNo = servletRequest.getParameter("projectNo");
		if(isSQLInject(projectNo)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectNo", projectNo);
			return "success";
		}
		String projectName = servletRequest.getParameter("projectName");
		if(isSQLInject(projectName)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectName", projectName);
			return "success";
		}
		String contract_owner_execute_name = servletRequest.getParameter("contract_owner_execute_name");
		if(isSQLInject(contract_owner_execute_name)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contract_owner_execute_name", contract_owner_execute_name);
			return "success";
		}
		String contractNo = servletRequest.getParameter("contractNo");
		if(isSQLInject(contractNo)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractNo", contractNo);
			return "success";
		}
		String contractName = servletRequest.getParameter("contractName");
		if(isSQLInject(contractName)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractName", contractName);
			return "success";
		}
		String contractType = servletRequest.getParameter("contractType");
		if(isSQLInject(contractType)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractType", contractType);
			return "success";
		}
		
		String contractSignedDate = servletRequest.getParameter("contractSignedDate");
		if(isSQLInject(contractSignedDate)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractSignedDate", contractSignedDate);
			return "success";
		}
		String contractSignedEndDate = servletRequest.getParameter("contractSignedEndDate");
		if(isSQLInject(contractSignedEndDate)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
			return "success";
		}
		String stat_date = servletRequest.getParameter("stat_date");
		if(isSQLInject(stat_date)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("stat_date", stat_date);
			return "success";
		}
		String stat_status = servletRequest.getParameter("stat_status");		//1:数据不为0，2:数据为0
		if(isSQLInject(stat_status)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("stat_status", stat_status);
			return "success";
		}
		String projectApproveStart = servletRequest.getParameter("projectApproveStart");		//项目批文开始时间
		if(isSQLInject(projectApproveStart)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectApproveStart", projectApproveStart);
			return "success";
		}
		String projectApproveEnd = servletRequest.getParameter("projectApproveEnd");			//项目批文结束时间
		if(isSQLInject(projectApproveEnd)){
			servletRequest.setAttribute("errorInfo", "参数错误");
			servletRequest.setAttribute("projectApproveEnd", projectApproveEnd);
			return "success";
		}
		
		String sql ="select t.project_no ,sum(c.contract_price)  ,sum(t.change_count_total) ,"+
		"sum(t.change_money_total) ,sum(c.final_price) ,sum(t.pay_plan_money_total) ,"+
		"sum(t.pay_actual_money_total) "+
		" from dw_contract_info t ,c_contract c ,c_project p where t.contract_id = c.id and c.removed ='0' and p.removed='0' and c.project_id = p.id";
		
		if(projectApproveStart!=null && !"".equals(projectApproveStart)){
			sql += " and p.approval_date >='"+projectApproveStart+"'";
			servletRequest.setAttribute("projectApproveStart", projectApproveStart);
		}
		if(projectApproveEnd!=null && !"".equals(projectApproveEnd)){
			sql += " and p.approval_date <='"+projectApproveEnd+"'";
			servletRequest.setAttribute("projectApproveEnd", projectApproveEnd);
		}
		if(projectNo!=null && !"".equals(projectNo)){
			sql += " and c.project_no like '%"+projectNo+"%'";
			servletRequest.setAttribute("projectNo",projectNo);
		}
		if(projectName!=null && !"".equals(projectName)){
			sql += " and c.project_name like '%"+projectName+"%'";
			servletRequest.setAttribute("projectName",projectName);
		}
		
		if(contract_owner_execute_name!=null && !"".equals(contract_owner_execute_name)){
			sql += " and c.contract_owner_execute_name like '%"+contract_owner_execute_name.trim()+"%'";
			servletRequest.setAttribute("contract_owner_execute_name", contract_owner_execute_name);
		}
		if(contractNo!=null && !"".equals(contractNo)){
			sql += " and c.contract_no like '%"+contractNo.trim()+"%'";
			servletRequest.setAttribute("contractNo", contractNo);
		}
		if(contractName!=null && !"".equals(contractName)){
			sql += " and c.contract_name like '%"+contractName.trim()+"%'";
			servletRequest.setAttribute("contractName", contractName);
		}
		if(contractType!=null && !"".equals(contractType)){
			sql += " and c.contract_type like '"+contractType.trim()+"%'";
			servletRequest.setAttribute("contractType", contractType);
		}
		if(contractSignedDate!=null && !"".equals(contractSignedDate)){
			sql += " and c.contract_signed_date >= '"+contractSignedDate.trim()+"'";
			servletRequest.setAttribute("contractSignedDate", contractSignedDate);
		}
		if(contractSignedEndDate!=null && !"".equals(contractSignedEndDate)){
			sql += " and c.contract_signed_date <= '"+contractSignedEndDate.trim()+"'";
			servletRequest.setAttribute("contractSignedEndDate", contractSignedEndDate);
		}
		
		if(stat_status!=null && !"".equals(stat_status)){
			if("1".equals(stat_status)){
				sql += " and (t.change_count_total!='0' or t.change_money_total!='0' or t.change_percent_total!='0'" +
				" or t.pay_plan_money_year!='0' or t.pay_plan_money_total!='0'" +
				" or t.pay_plan_percent_total!='0' " +
				" or t.pay_actual_money_year!='0'" +
				" or t.pay_actual_money_total!='0' or t.pay_actual_percent_total!='0')";
			}else{
				sql += " and t.change_count_total='0' and t.change_money_total='0' and t.change_percent_total='0'" +
				" and t.pay_plan_money_year='0' and t.pay_plan_money_total='0'" +
				" and t.pay_plan_percent_total='0' " +
				" and t.pay_actual_money_year='0'" +
				" and t.pay_actual_money_total='0' and t.pay_actual_percent_total='0'";
			}
			servletRequest.setAttribute("stat_status", stat_status);
		}
		if(stat_date!=null && !"".equals(stat_date)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			try {
				calendar.setTime(sdf.parse(stat_date));
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
				String maxDate = sdf.format(calendar.getTime());
				String tempSql = "select t.stat_date from dw_contract_info t where t.stat_date<='"+maxDate+"' order by t.stat_date DESC";
				List<String[]> list = this.pageNewService.findPaginationInfo(tempSql, 0, 1);
				if(list!=null && list.size()>0 && list.get(0)[0]!=null){
					sql += " and t.stat_date='"+list.get(0)[0]+"'";
				}else{
					sql += " and t.stat_date='"+maxDate+"'";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			servletRequest.setAttribute("stat_date", stat_date);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getMaximum(Calendar.DAY_OF_MONTH));
			String maxDate = sdf.format(calendar.getTime());
			String tempSql = "select t.stat_date from dw_contract_info t where t.stat_date<='"+maxDate+"'  order by t.stat_date DESC";
			List<String[]> list = this.pageNewService.findPaginationInfo(tempSql, 0, 1);
			if(list!=null && list.size()>0 && list.get(0)[0]!=null){
				sql += " and t.stat_date='"+list.get(0)[0]+"'";
			}else{
				sql += " and t.stat_date='"+maxDate+"'";
			}
			servletRequest.setAttribute("stat_date", stat_date);
		}
		
		sql += " group by t.project_no order by t.project_no DESC";
		int totalRows = (int) this.pageNewService.countBySql(sql);
		PageInfo pageinfo = new PageInfo(totalRows, vo.pageSize, vo.page);	
		List<String[]> list = this.pageNewService.findPaginationInfo(sql, pageinfo.getBeginIndex(), vo.pageSize);	//结果集
		
		Map<String, String> projectTypeMap = new HashMap<String,String>();
		projectTypeMap.put("1", "外部项目");
		projectTypeMap.put("2", "内部项目");
		projectTypeMap.put("3", "内部项目");
		projectTypeMap.put("4", "内部项目");
		Map<String, String > moneySourceTypeMap = new HashMap<String,String>();
		moneySourceTypeMap.put("1", "建设资金");
		moneySourceTypeMap.put("2", "自有资金");
		moneySourceTypeMap.put("3", "财政资金");
		List<Map<String,String>> result = new ArrayList<Map<String,String>>();
		if(list!=null && list.size()>0){
			for(int i=0; i<list.size(); i++){
				Map<String,String> map = new HashMap<String,String>();
				map.put("project_no", list.get(i)[0]);
				map.put("contract_price_total", list.get(i)[1]);
				map.put("change_total_count", list.get(i)[2]);
				map.put("change_total_price", list.get(i)[3]);
				if(list.get(i)[4]==null || "".equals(list.get(i)[4])){
					map.put("final_price_total", "0");
				}else{
					map.put("final_price_total", list.get(i)[4]);
				}
				map.put("plan_pay_total",list.get(i)[5]);
				map.put("actual_pay_total", list.get(i)[6]);
				String pSql = "select t.project_type,t.project_name,t.money_source_type,project_company,project_budget_all from c_project t where t.removed='0' and t.project_no='"+list.get(i)[0]+"'";
				List<Map<String, Object>> projectInfoList = htxxService.executeSQl(pSql);
				if(projectInfoList!=null && projectInfoList.size()>0){
					map.put("project_type", projectTypeMap.get(projectInfoList.get(0).get("PROJECT_TYPE")));
					map.put("project_name", projectInfoList.get(0).get("PROJECT_NAME")+"");
					map.put("money_source_type", moneySourceTypeMap.get(projectInfoList.get(0).get("MONEY_SOURCE_TYPE")));
					map.put("project_company",projectInfoList.get(0).get("PROJECT_COMPANY")+"");
					map.put("project_budget_all",projectInfoList.get(0).get("PROJECT_BUDGET_ALL")+"");
					
				}
				result.add(map);
			}
		}
		
		this.pageResultSet = new PageResultSet<Map<String,String>>();
		pageResultSet.setList(result);
		pageResultSet.setPageInfo(pageinfo);
		return "success";
	}
	
	
	public boolean isSQLInject(String param) {
		if(param==null || "".equals(param)) return false;
		String[] array = {"select","delete","from","update","create","destory","drop","alter","and","like","exec","count","chr","master","truncate","char","declare","'","%"};
		for(int i=0,len = array.length; i<len; i++){
			if(param.contains(array[i])){
				return true;
			}
			if(param.contains(array[i].toUpperCase())){
				return true;
			}
		}
		return false;
	} 
	
	
	
	
	
	
	
}
