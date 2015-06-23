package com.wonders.stpt.beforeBuild.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.wonders.stpt.beforeBuild.model.bo.MonomerPlan;
import com.wonders.stpt.beforeBuild.model.bo.MonomerPlanCheck;
import com.wonders.stpt.beforeBuild.model.bo.Report;
import com.wonders.stpt.beforeBuild.model.bo.ShortMsg;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.beforeBuild.service.NewService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/monomerPlan")
@Controller("monomerPlanAction")
@Scope("prototype")
public class MonomerPlanAction implements ModelDriven<MonomerPlan>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	@Autowired
	private NewService newService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private MonomerPlan monomerPlan = new MonomerPlan();

	public MonomerPlan getMonomerPlan() {
		return monomerPlan;
	}

	public void setMonomerPlan(MonomerPlan monomerPlan) {
		this.monomerPlan = monomerPlan;
	}
	
	public MonomerPlan getModel(){
		return monomerPlan;
	}
	
	@Action(value="findMonomerPlanByPage",results={@Result(name="success",location="/beforeBuild/monomerPlan/list.jsp")})
	public String findMonomerPlanByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String planNum = request.getParameter("planNum");
		String planName = request.getParameter("planName");
		String monomerName = request.getParameter("monomerName");
		String linePlanId = request.getParameter("linePlanId");
		String ruleVersionId = request.getParameter("ruleVersionId");
		String paperName = request.getParameter("paperName");
		String ifNode = request.getParameter("ifNode");
		String ifMilestone = request.getParameter("ifMilestone");
		String status = request.getParameter("status");
		String checkRole = request.getParameter("checkRole");
		String checkStatus = request.getParameter("checkStatus");
		
		String id = request.getParameter("id");
		
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
		request.setAttribute("checkRole", checkRole);
		request.setAttribute("checkStatus", checkStatus);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_monomer_plan t,t_before_line_plan t1,t_before_rule_version t2 where t.line_plan_id = t1.id and t.rule_version_id = t2.id and t.removed = '0' and t1.removed = '0' and t2.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(oldDeptId!=null&&oldDeptId.length()>0){
			baseSql += " and t.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		if(planNum!=null&&planNum.length()>0){
			baseSql += " and t.plan_num like ? ";
			src.add("%"+planNum+"%");
		}
		if(planName!=null&&planName.length()>0){
			baseSql += " and t.plan_name like ? ";
			src.add("%"+planName+"%");
		}
		if(monomerName!=null&&monomerName.length()>0){
			baseSql += " and t.monomer_name like ? ";
			src.add("%"+monomerName+"%");
		}
		if(linePlanId!=null&&linePlanId.length()>0){
			baseSql += " and t1.plan_name like ? ";
			src.add("%"+linePlanId+"%");
		}
		if(ruleVersionId!=null&&ruleVersionId.length()>0){
			baseSql += " and t2.version_name like ? ";
			src.add("%"+ruleVersionId+"%");
		}
		if(paperName!=null&&paperName.length()>0){
			baseSql += " and t.paper_name = ? ";
			src.add(paperName);
		}
		if(ifNode!=null&&ifNode.length()>0){
			baseSql += " and t.if_node = ? ";
			src.add(ifNode);
		}
		if(ifMilestone!=null&&ifMilestone.length()>0){
			baseSql += " and t.if_milestone = ? ";
			src.add(ifMilestone);
		}
		if(status!=null&&status.length()>0){
			baseSql += " and t.status = ? ";
			src.add(status);
		}
		if(checkStatus!=null&&checkStatus.length()>0){
			baseSql += " and t.check_status = ? ";
			src.add(checkStatus);
		}
		if(id!=null&&id.length()>0){
			baseSql += " and t.line_plan_id = ? ";
			src.add(id);
		}
//		if(realFinishTime_start!=null&&realFinishTime_start.length()>0){
//			baseSql += " and t.real_finish_time >= ? ";
//			src.add(realFinishTime_start);
//		}
//		if(realFinishTime_end!=null&&realFinishTime_end.length()>0){
//			baseSql += " and t.real_finish_time <= ? ";
//			src.add(realFinishTime_end);
//		}
		
		String orderSql = "";
		String orderParam = request.getParameter("orderParam");
		String orderValue = request.getParameter("orderValue");
		request.setAttribute("orderParam", orderParam);
		request.setAttribute("orderValue", orderValue);
		if(orderParam==null||orderParam.length()==0){
			if("1".equals(checkRole)){
				orderSql = " case check_status when '1' then 1 else 2 end,";
			}else if("2".equals(checkRole)){
				orderSql = " case check_status when '2' then 1 else 2 end,";
			}else{
				orderSql = " case check_status when '4' then 1 when '1' then 2 when '2' then 3 else 4 end,";
			}
			orderSql = " order by " + orderSql + " t.create_time desc ";
		}else{
			if("plan_name".equals(orderParam)||"paper_name".equals(orderParam)||"plan_start_time".equals(orderParam)||"plan_finish_time".equals(orderParam)){
				orderParam = "t."+orderParam;
			}
			orderSql = " order by "+orderParam;
			if(orderValue.length()>0){
				orderSql += " "+ orderValue;
			}
		}
		String listSql = "select t.*,t1.plan_name line_plan_name,t2.version_name rule_version_name " + baseSql + orderSql;
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		
		if(id!=null&&id.length()>0){
			List<Object> src1 = new ArrayList<Object>();
			src1.add(id);
			List<Object[]> list1 = beforeBuildService.findBySql("select plan_name,route_name,route_id from t_before_line_plan where id = ? ", src1);
			if(list1!=null&&list1.size()>0){
				request.setAttribute("linePlanId", list1.get(0)[0]);
				request.setAttribute("selected_route_name", list1.get(0)[1]);
				request.setAttribute("selected_route_id", list1.get(0)[2]);
			}
		}
		
		return "success";
	}
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/monomerPlan/add.jsp")})
	public String toAdd(){
		String name = (String)session.getAttribute("userName");
		String oldDeptId = request.getParameter("oldDeptId");
		List<Object> src = new ArrayList<Object>();
		src.add(name);
		src.add(oldDeptId);
		List<Object[]> userlist = beforeBuildService.findBySql("select phone from t_before_user where name = ? and old_dept_id = ? ", src);
		if(userlist!=null&&userlist.size()>0){
			request.setAttribute("default_phone", userlist.get(0));
		}
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/monomerPlan/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			monomerPlan = (MonomerPlan)beforeBuildService.load(id,MonomerPlan.class);
			if("4".equals(monomerPlan.getCheckStatus())){
				List<Object> src = new ArrayList<Object>();
				src.add(id);
				List<Object[]> list = beforeBuildService.findBySql("select remark,check_user,check_time from t_before_monomer_plan_check t where t.monomer_plan_id = ? order by check_time desc ", src);
				if(list!=null&&list.size()>0){
					request.setAttribute("checkInfo", list);
				}
			}
		}
		return "success";
	}
	
	@Action(value="view",results={@Result(name="success",location="/beforeBuild/monomerPlan/view.jsp")})
	public String view(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			monomerPlan = (MonomerPlan)beforeBuildService.load(id,MonomerPlan.class);
			List<Object> src = new ArrayList<Object>();
			src.add(id);
			List<Object[]> list = beforeBuildService.findBySql("select remark,check_user,check_time,case result when '1' then '通过' when '0' then '不通过' when '2' then '作废' end result from t_before_monomer_plan_check t where t.monomer_plan_id = ? order by check_time desc ", src);
			if(list!=null&&list.size()>0){
				request.setAttribute("checkInfo", list);
			}
		}
		return "success";
	}
	
	@Action(value="save")
	public String save(){
		if(monomerPlan.getId()!=null&&monomerPlan.getId().length()>0){
			monomerPlan.setUpdateTime(df.format(new Date()));
			monomerPlan.setUpdateUser((String)session.getAttribute("loginName"));
			if("4".equals(monomerPlan.getCheckStatus())){
				monomerPlan.setCheckStatus("1");
			}
		}else{
			monomerPlan.setId(null);
			monomerPlan.setCheckStatus("1");
			monomerPlan.setCreateTime(df.format(new Date()));
			monomerPlan.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(monomerPlan);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		String if_have = request.getParameter("if_have");
		if(id!=null&&id.length()>0){
			monomerPlan = (MonomerPlan)beforeBuildService.load(id,MonomerPlan.class);
			monomerPlan.setRemoved("1");
			monomerPlan.setUpdateTime(df.format(new Date()));
			monomerPlan.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(monomerPlan);
			
			if(if_have!=null&&"yes".equals(if_have)){
				List<Object> src = new ArrayList<Object>();
				src.add(id);
				beforeBuildService.updateBySql("update t_before_task set removed = '1' where monomer_plan_id = ?", src);
			}
		}
		return null;
	}
	
	@Action(value="findLines")
	public String findLines(){
		String oldDeptId = request.getParameter("oldDeptId");
		String sql = "select distinct t.p_line,c.content from t_build_project t,t_list_status c "+
         		" where t.p_line = c.sid ";
		List<Object> src = new ArrayList<Object>();
		if(oldDeptId!=null&&oldDeptId.length()>0){
			sql += " and t.p_companyid = ? ";
			src.add(oldDeptId);
		}
		sql += " order by content ";
		ac.writeJson(beforeBuildService.findBySql(sql, src));
		return null;
	}
	
	@Action(value="findLinePlans")
	public String findLinePlans(){
		String sql = "select id,plan_name from t_before_line_plan where removed = '0' order by create_time ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findRuleVersions")
	public String findRuleVersions(){
		String sql = "select * from (select id from t_before_rule_version where removed = '0' order by create_time desc) where rownum < 2 ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="ifHaveTask")
	public String ifHaveTask(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			String sql = "select count(*) count_num from t_before_task where monomer_plan_id = ? and removed = '0' ";
			List<Object> src = new ArrayList<Object>();
			src.add(id);
			int totalRow = beforeBuildService.findPageSize(sql,src);
			if(totalRow==0){
				ac.writeAjax("{\"if_have\":\"no\"}");
			}else{
				ac.writeAjax("{\"if_have\":\"yes\"}");
			}
		}
		return null;
	}
	
	@Action(value="getNextPlanOrder")
	public String getNextPlanOrder(){
		String monomerId = this.request.getParameter("monomerId");
		if(monomerId!=null&&monomerId.length()>0){
			String sql = "select to_char(max(plan_order)+1) from t_before_monomer_plan t where t.removed = '0' and t.monomer_id = ? ";
			List<Object> src = new ArrayList<Object>();
			src.add(monomerId);
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			if(list!=null&&list.size()>0&&list.get(0)!=null){
				ac.writeJson(list.get(0));
			}else{
				ac.writeJson("1");
			}
		}
		return null;
	}
	
	@Action(value="checkFunc")
	public String checkFunc(){
		String checkRole = this.request.getParameter("checkRole");
		String result = this.request.getParameter("result");
		String remark = this.request.getParameter("remark");
		String monomerPlanId = this.request.getParameter("monomerPlanId");
		if(monomerPlanId!=null&&monomerPlanId.length()>0){
			String[] monomerPlanId_split = monomerPlanId.split(",");
			if(monomerPlanId_split!=null&&monomerPlanId_split.length>0){
				for(int i=0;i<monomerPlanId_split.length;i++){
					MonomerPlanCheck bo = new MonomerPlanCheck();
					bo.setMonomerPlanId(monomerPlanId_split[i]);
					bo.setResult(result);
					bo.setRemark(remark);
					bo.setCheckUser((String)session.getAttribute("userName"));
					bo.setCheckTime(df.format(new Date()));
					bo.setRemoved("0");
					beforeBuildService.save(bo);
					
					String sql = "update t_before_monomer_plan t set t.check_status = ? where id = ? ";
					List<Object> src = new ArrayList<Object>();
					if("0".equals(result)){
						src.add("4");
					}else if("1".equals(checkRole)){
						src.add("2");
					}else if("2".equals(checkRole)){
						src.add("3");
					}else{
						src.add("1");
					}
					src.add(monomerPlanId_split[i]);
					beforeBuildService.updateBySql(sql, src);
					
					if("0".equals(result)){
						sql = "select plan_name,old_dept_id from t_before_monomer_plan t where t.id = ?";
						src = new ArrayList<Object>();
						src.add(monomerPlanId_split[i]);
						List<Object[]> nameList = beforeBuildService.findBySql(sql, src);
						String content = "【修改】计划“"+nameList.get(0)[0]+"“审批未通过，请修改！";
						Report report = new Report();
						report.setType("前期办证");
						report.setReceiveTime(df.format(new Date()));
						report.setContent(content);
						report.setStatus("0");
						report.setRemoved("0");
						report.setCreateTime(df.format(new Date()));
						report.setOldDeptId(nameList.get(0)[1]+"");
						report.setRole("1");
						report.setMonomer_plan_id(monomerPlanId_split[i]);
						beforeBuildService.save(report);
						
						String dsql = "select phone from t_before_user where removed = '0' and old_dept_id = '"+nameList.get(0)[1]+"' and role = '1' ";
						List<Object[]> dlist = beforeBuildService.findBySql(dsql, null);
						if(dlist!=null&&dlist.size()>0){
							ShortMsg msg = new ShortMsg();
							msg.setMobile(dlist.get(0)+"");
							msg.setStatus("0");
							msg.setContent(content);
							beforeBuildService.save(msg);
						}
					}
				}
			}
		}
		return null;
	}
	
	@Action(value="cancelCheck")
	public String cancelCheck(){
		String id = this.request.getParameter("id");
		if(id!=null&&id.length()>0){
			MonomerPlanCheck bo = new MonomerPlanCheck();
			bo.setMonomerPlanId(id);
			bo.setResult("2");
			bo.setRemark("");
			bo.setCheckUser((String)session.getAttribute("userName"));
			bo.setCheckTime(df.format(new Date()));
			bo.setRemoved("0");
			beforeBuildService.save(bo);
			
			String sql = "update t_before_monomer_plan t set t.check_status = '1' where id = ? ";
			List<Object> src = new ArrayList<Object>();
			src.add(id);
			beforeBuildService.updateBySql(sql, src);
			
			sql = "update t_before_task t set t.removed = '1' where t.monomer_plan_id = ?";
			src = new ArrayList<Object>();
			src.add(id);
			beforeBuildService.updateBySql(sql, src);
		}
		return null;
	}
	
	@Action(value="findIfExist")
	public String findIfExist(){
		String linePlanId = this.request.getParameter("linePlanId");
		String monomerId = this.request.getParameter("monomerId");
		String paperId = this.request.getParameter("paperId");
		if(linePlanId!=null&&linePlanId.length()>0&&monomerId!=null&&monomerId.length()>0&&paperId!=null&&paperId.length()>0){
			String sql = "select count(*) count_num from t_before_monomer_plan t where t.line_plan_id = ? and t.monomer_id = ? and t.paper_id = ? and t.removed = '0'";
			List<Object> src = new ArrayList<Object>();
			src.add(linePlanId);
			src.add(monomerId);
			src.add(paperId);
			int num = beforeBuildService.findPageSize(sql, src);
			
			sql = "select count(*) count_num from t_before_monomer_plan t,t_before_task t1 where t.id = t1.monomer_plan_id and t.line_plan_id = ? and t.monomer_id = ? and t.paper_id = ? and t.removed = '0' and t1.removed = '0' and (t1.status = '5' or t1.status = '6')";
			src = new ArrayList<Object>();
			src.add(linePlanId);
			src.add(monomerId);
			src.add(paperId);
			int num1 = beforeBuildService.findPageSize(sql, src);
			if(num>0&&num!=num1){
				ac.writeAjax("{\"if_exist\":\"yes\"}");
			}else{
				ac.writeAjax("{\"if_exist\":\"no\"}");
			}
		}
		return null;
	}
}
