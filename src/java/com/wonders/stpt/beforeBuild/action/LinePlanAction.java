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
import com.wonders.stpt.beforeBuild.model.bo.LinePlan;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;


@ParentPackage("struts-default")
@Namespace(value="/linePlan")
@Controller("linePlanAction")
@Scope("prototype")
public class LinePlanAction implements ModelDriven<LinePlan>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private LinePlan linePlan = new LinePlan();

	public LinePlan getLinePlan() {
		return linePlan;
	}

	public void setLinePlan(LinePlan linePlan) {
		this.linePlan = linePlan;
	}

	public LinePlan getModel(){
		return linePlan;
	}
	
	@Action(value="findLinePlanByPage",results={@Result(name="success",location="/beforeBuild/linePlan/list.jsp")})
	public String findLinePlanByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String planNum = request.getParameter("planNum");
		String planName = request.getParameter("planName");
		String projectName = request.getParameter("projectName");
		String routeName = request.getParameter("routeName");
		String planTypeName = request.getParameter("planTypeName");
		String paperName = request.getParameter("paperName");
		String ifNode = request.getParameter("ifNode");
		String ifMilestone = request.getParameter("ifMilestone");
		String status = request.getParameter("status");
		String checkRole = request.getParameter("checkRole");
		
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		request.setAttribute("checkRole", checkRole);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_line_plan t where t.removed = '0'  ";
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
		if(projectName!=null&&projectName.length()>0){
			baseSql += " and t.project_name like ? ";
			src.add("%"+projectName+"%");
		}
		if(routeName!=null&&routeName.length()>0){
			baseSql += " and t.route_name like ? ";
			src.add("%"+routeName+"%");
		}
		if(planTypeName!=null&&planTypeName.length()>0){
			baseSql += " and t.plan_type_name = ? ";
			src.add(planTypeName);
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
			orderSql = " order by create_time desc ";
		}else{
			orderSql = " order by "+orderParam;
			if(orderValue.length()>0){
				orderSql += " "+ orderValue;
			}
		}
		String listSql = "select t.* " + baseSql + orderSql;
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		
		return "success";
	}
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/linePlan/add.jsp")})
	public String toAdd(){
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/linePlan/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			linePlan = (LinePlan)beforeBuildService.load(id,LinePlan.class);
		}
		return "success";
	}
	
	@Action(value="save")
	public String save(){
		if(linePlan.getId()!=null&&linePlan.getId().length()>0){
			linePlan.setUpdateTime(df.format(new Date()));
			linePlan.setUpdateUser((String)session.getAttribute("loginName"));
		}else{
			linePlan.setId(null);
			linePlan.setCreateTime(df.format(new Date()));
			linePlan.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(linePlan);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="findAllPlanTypes")
	public String findAllPlanTypes(){
		String sql = "select id,name from t_before_plan_type where removed = '0' ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findAllPapers")
	public String findAllPapers(){
		String sql = "select id,name from t_before_paper where removed = '0' order by orders ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		String if_have = request.getParameter("if_have");
		if(id!=null&&id.length()>0){
			linePlan = (LinePlan)beforeBuildService.load(id,LinePlan.class);
			linePlan.setRemoved("1");
			linePlan.setUpdateTime(df.format(new Date()));
			linePlan.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(linePlan);
			
			if(if_have!=null&&"yes".equals(if_have)){
				List<Object> src = new ArrayList<Object>();
				src.add(id);
				beforeBuildService.updateBySql("update t_before_monomer_plan set removed = '1' where line_plan_id = ?", src);
			}
		}
		return null;
	}
	
	@Action(value="ifHaveMonomerPlan")
	public String ifHaveMonomerPlan(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			String sql = "select count(*) count_num from t_before_monomer_plan where line_plan_id = ? and removed = '0' ";
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
	
	@Action(value="getPaperByLine")
	public String getPaperByLine(){
		String linePlanId = this.request.getParameter("linePlanId");
		if(linePlanId!=null&&linePlanId.length()>0){
			String sql = "select paper_id,paper_name from t_before_line_plan where removed = '0' and id = ? ";
			List<Object> src = new ArrayList<Object>();
			src.add(linePlanId);
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			if(list!=null&&list.size()>0){
				ac.writeJson(list.get(0));
			}
		}
		return null;
	}
	
	@Action(value="getNextPlanOrder")
	public String getNextPlanOrder(){
		String projectId = this.request.getParameter("projectId");
		if(projectId!=null&&projectId.length()>0){
			String sql = "select to_char(max(plan_order)+1) from t_before_line_plan t where t.removed = '0' and t.project_id = ? ";
			List<Object> src = new ArrayList<Object>();
			src.add(projectId);
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			if(list!=null&&list.size()>0&&list.get(0)!=null){
				ac.writeJson(list.get(0));
			}else{
				ac.writeJson("1");
			}
		}
		return null;
	}
}
