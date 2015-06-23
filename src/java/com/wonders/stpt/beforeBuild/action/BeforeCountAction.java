package com.wonders.stpt.beforeBuild.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/beforeCount")
@Controller("beforeCountAction")
@Scope("prototype")
public class BeforeCountAction {
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat yDf = new SimpleDateFormat("yyyy");
	
	@Action(value="toCountList",results={@Result(name="success",location="/beforeBuild/beforeCount/countList.jsp")})
	public String toCountList(){
		String listType = request.getParameter("listType");
		String project_id = request.getParameter("project_id");
		String project_id_2 = request.getParameter("project_id_2");
		String routeId = request.getParameter("routeId");
		String monomer_type = request.getParameter("monomer_type");
		String monomerId = request.getParameter("monomerId");
		String paper_id = request.getParameter("paper_id");
		
		request.setAttribute("listType", listType);
		request.setAttribute("project_id", project_id);
		request.setAttribute("project_id_2", project_id_2);
		request.setAttribute("routeId", routeId);
		request.setAttribute("monomer_type", monomer_type);
		request.setAttribute("monomerId", monomerId);
		request.setAttribute("paper_id", paper_id);
		return "success";
	}
	
	@Action(value="countDatas")
	public String countDatas() throws NumberFormatException{
		String listType = request.getParameter("listType");
		String project_id = request.getParameter("project_id");
		String monomer_id = request.getParameter("monomer_id");
		String paper_id = request.getParameter("paper_id");
		String oldDeptId = request.getParameter("oldDeptId");
		String sql = "";
		List<Object> src = new ArrayList<Object>();
		if("project".equals(listType)){
			sql = "select t.paper_name,t.paper_id,t2.status,count(*) count_num " +
					" from t_before_monomer_plan t,t_before_line_plan t1,t_before_task t2 "+
					" where t.line_plan_id = t1.id and t.id = t2.monomer_plan_id ";
			if(project_id!=null&&project_id.length()>0){
				src.add(project_id);
				sql += " and t1.project_id = ? ";
			}
			sql += " and t.removed = '0' and t1.removed = '0' and t2.removed = '0'";
			if(oldDeptId!=null&&oldDeptId.length()>0){
				src.add(oldDeptId);
				sql += " and t.old_dept_id = ? ";
			}
			sql +=  " group by t.paper_name,t.paper_id,t2.status "+
					" order by to_number(t.paper_id),to_number(t2.status)";
		}else if("monomer".equals(listType)){
			sql = "select t.paper_name,t.paper_id,t1.status,count(*) count_num "+
					" from t_before_monomer_plan t,t_before_task t1 where t.removed = '0' and t1.removed = '0' and t.id = t1.monomer_plan_id ";
			if(monomer_id!=null&&monomer_id.length()>0){
				src.add(monomer_id);
				sql += " and t.monomer_id = ? ";
			}
			if(oldDeptId!=null&&oldDeptId.length()>0){
				src.add(oldDeptId);
				sql += " and t.old_dept_id = ? ";
			}
			sql += " group by t.paper_name,t.paper_id,t1.status "+
					" order by to_number(t.paper_id),to_number(t1.status)";
		}else if("dept".equals(listType)){
			sql = "select t1.deal_unit,t1.deal_unit,t2.status,count(*) from t_before_monomer_plan t,t_before_paper t1,t_before_task t2 "+
					" where t.paper_id = t1.id and t.id = t2.monomer_plan_id and t.removed = '0' and t1.removed = '0' and t2.removed = '0' ";
			if(oldDeptId!=null&&oldDeptId.length()>0){
				src.add(oldDeptId);
				sql += " and t.old_dept_id = ? ";
			}
			sql += " group by t1.deal_unit,t1.deal_unit,t2.status order by t1.deal_unit,t1.deal_unit,t2.status";
			//System.out.println(sql);
		}else{
			sql = "select t1.project_name,t1.project_id,t2.status,count(*) count_num " +
					" from t_before_monomer_plan t,t_before_line_plan t1,t_before_task t2 "+
					" where t.line_plan_id = t1.id and t.id = t2.monomer_plan_id";
			if(paper_id!=null&&paper_id.length()>0){
				src.add(paper_id);
				sql += " and t.paper_id = ? ";
			}
			if(oldDeptId!=null&&oldDeptId.length()>0){
				src.add(oldDeptId);
				sql += " and t.old_dept_id = ? ";
			}
			sql += " and t.removed = '0' and t1.removed = '0' and t2.removed = '0' "+
					" group by t1.project_name,t1.project_id,t2.status "+
					" order by t1.project_name,to_number(t2.status)";
		}
		
		//System.out.println(sql);
		
		List<Object[]> resultList = beforeBuildService.findBySql(sql, src);
		if("project".equals(listType)||"monomer".equals(listType)){
			sql = "select id,name from t_before_paper where removed = '0' order by orders ";
		}else if("dept".equals(listType)){
			sql = "select distinct t.deal_unit,'' from t_before_paper t where t.removed = '0' order by t.deal_unit ";
		}else{
			sql = "select p_id,p_name from t_build_project order by p_operatedate desc ";
		}
		List<Object[]> xList = beforeBuildService.findBySql(sql, null);
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(resultList!=null&&resultList.size()>0){
			for(int i=0;i<resultList.size();i++){
				map.put(resultList.get(i)[1]+","+resultList.get(i)[2], Integer.parseInt(String.valueOf(resultList.get(i)[3])));
			}
		}
		List<Integer[]> outList = new ArrayList<Integer[]>();
		String[] status = {"1","2","3","4","5","6"};
		if(xList!=null&&xList.size()>0){
			for(int i=0;i<status.length;i++){
				Integer[] outStrs = new Integer[xList.size()];
				for(int j=0;j<xList.size();j++){
					if(map.containsKey(xList.get(j)[0]+","+status[i])){
						outStrs[j] = map.get(xList.get(j)[0]+","+status[i]);
					}else{
						outStrs[j] = 0;
					}
				}
				outList.add(outStrs);
			}
		}
		ac.writeJson(outList);
		return null;
	}
	
	@Action(value="findAllPapers")
	public String findAllPapers(){
		String sql = "select name from t_before_paper where removed = '0' order by orders ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findAllProjects")
	public String findAllProjects(){
		String sql = "select (case when p_short_name is null then p_name else p_short_name end) p_name from t_build_project order by p_operatedate desc ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="findAllDepts")
	public String findAllDepts(){
		String sql = "select distinct t.deal_unit from t_before_paper t where t.removed = '0' order by t.deal_unit ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
	
	@Action(value="toInfoList",results={@Result(name="success",location="/beforeBuild/beforeCount/infoList.jsp")})
	public String toInfoList(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String projectName = request.getParameter("projectName");
		String monomerName = request.getParameter("monomerName");
		String paperName = request.getParameter("paperName");
		String ifNode = request.getParameter("ifNode");
		String ifMilestone = request.getParameter("ifMilestone");
		String status = request.getParameter("status");
		String oldDeptId = request.getParameter("oldDeptId");
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		
		String baseSql = " from t_before_line_plan t,t_before_monomer_plan t1,t_before_task t2 "+
				" where t.id = t1.line_plan_id and t1.id = t2.monomer_plan_id and t.removed = '0' and t1.removed = '0' and t2.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(projectName!=null&&projectName.length()>0){
			baseSql += " and t.project_name like ? ";
			src.add("%"+projectName+"%");
		}
		if(monomerName!=null&&monomerName.length()>0){
			baseSql += " and t1.monomer_name like ? ";
			src.add("%"+monomerName+"%");
		}
		if(paperName!=null&&paperName.length()>0){
			baseSql += " and t1.paper_name = ? ";
			src.add(paperName);
		}
		if(ifNode!=null&&ifNode.length()>0){
			baseSql += " and t1.if_node = ? ";
			src.add(ifNode);
		}
		if(ifMilestone!=null&&ifMilestone.length()>0){
			baseSql += " and t1.if_milestone = ? ";
			src.add(ifMilestone);
		}
		if(status!=null&&status.length()>0){
			baseSql += " and t2.status = ? ";
			src.add(status);
		}
		if(oldDeptId!=null&&oldDeptId.length()>0){
			baseSql += " and t1.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		
		String orderSql = "";
		String orderParam = request.getParameter("orderParam");
		String orderValue = request.getParameter("orderValue");
		request.setAttribute("orderParam", orderParam);
		request.setAttribute("orderValue", orderValue);
		if(orderParam==null||orderParam.length()==0){
			orderSql = " order by t.project_name,t1.monomer_name,to_number(t1.paper_id) ";
		}else{
			if("project_name".equals(orderParam)){
				orderParam = "t."+orderParam;
			}else if("monomer_name".equals(orderParam)||"paper_name".equals(orderParam)||"plan_start_time".equals(orderParam)||"plan_finish_time".equals(orderParam)){
				orderParam = "t1."+orderParam;
			}else if("real_start_time".equals(orderParam)||"real_finish_time".equals(orderParam)){
				orderParam = "t2."+orderParam;
			}
			orderSql = " order by "+orderParam;
			if(orderValue.length()>0){
				orderSql += " "+ orderValue;
			}
		}
		
		String listSql = "select t.project_name,t.project_id,t1.monomer_name,t1.monomer_id,t1.paper_name,t1.paper_id,"+
				" t2.status,t1.if_node,t1.if_milestone,t1.main_person,t1.remark," +
				" t1.plan_start_time,t1.plan_finish_time,t2.real_start_time,t2.real_finish_time,t2.id" + baseSql + orderSql;
		String countSql = "select count(*) count_num " +baseSql;
		
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		
		request.setAttribute("projectName", projectName);
		request.setAttribute("monomerName", monomerName);
		request.setAttribute("paperName", paperName);
		request.setAttribute("ifNode", ifNode);
		request.setAttribute("ifMilestone", ifMilestone);
		request.setAttribute("status", status);
		request.setAttribute("oldDeptId", oldDeptId);
		return "success";
	}
	
	@Action(value="view",results={@Result(name="success",location="/beforeBuild/beforeCount/view.jsp")})
	public String view(){
		String id = request.getParameter("id");
		String sql = "select t.project_name,t.project_id,t1.monomer_name,t1.monomer_id,t1.paper_name,t1.paper_id,"+
			" t2.status,t1.if_node,t1.if_milestone,t1.main_person,t1.remark," +
			" t1.plan_start_time,t1.plan_finish_time,t2.real_start_time,t2.real_finish_time,t2.biaodiwu "+
			" from t_before_line_plan t,t_before_monomer_plan t1,t_before_task t2 "+
			" where t.id = t1.line_plan_id and t1.id = t2.monomer_plan_id and t.removed = '0' and t1.removed = '0' and t2.removed = '0' " +
			" and t2.id = ? ";
		List<Object> src = new ArrayList<Object>();
		src.add(id);
		List<Object[]> list = beforeBuildService.findBySql(sql, src);
		this.request.setAttribute("info", list.get(0));
		return "success";
	}
	
	@Action(value="showPlanReport",results={@Result(name="success",location="/beforeBuild/beforeCount/planReport.jsp")})
	public String showPlanReport() throws ParseException{
		Date date = new Date();
		List<Object> src = new ArrayList<Object>();
		String projectId = request.getParameter("projectId");
		String year = request.getParameter("year");
		String oldDeptId = request.getParameter("oldDeptId");
		request.setAttribute("projectId", projectId);
		request.setAttribute("year", year);
		request.setAttribute("oldDeptId", oldDeptId);
		String sql = "select t.project_name,t.project_id,t1.monomer_name,t1.monomer_id,t1.paper_name,t1.paper_id,t1.plan_finish_time,t2.real_finish_time "+
			" from t_before_line_plan t,t_before_monomer_plan t1,t_before_task t2 "+
			" where t.removed = '0' and t1.removed = '0' and t2.removed = '0' "+
			" and t.id = t1.line_plan_id and t1.id = t2.monomer_plan_id and t1.plan_finish_time like ? ";
		if(year==null){
			year = yDf.format(date);
		}
		src.add(year+"%");
		if(projectId!=null&&projectId.length()>0){
			sql += " and t.project_id = ? ";
			src.add(projectId);
		}
		if(oldDeptId!=null&&oldDeptId.length()>0){
			sql += " and t.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		sql += " order by project_name,monomer_name ";
		List<Object[]> list = beforeBuildService.findBySql(sql, src);
		
		sql = "select distinct t.project_name,t.project_id,t1.monomer_name,t1.monomer_id "+
			" from t_before_line_plan t,t_before_monomer_plan t1 "+
			" where t.removed = '0' and t1.removed = '0' "+
			" and t.id = t1.line_plan_id and t1.plan_finish_time like ? ";
		if(projectId!=null&&projectId.length()>0){
			sql += " and t.project_id = ? ";
		}
		if(oldDeptId!=null&&oldDeptId.length()>0){
			sql += " and t.old_dept_id = ? ";
		}
		sql += " order by project_name,monomer_name ";
		List<Object[]> monomerList = beforeBuildService.findBySql(sql, src);
		
		sql = "select t.id,t.name from t_before_paper t where t.removed = '0' order by t.orders";
		List<Object[]> paperList = beforeBuildService.findBySql(sql, null);
		
		Map<String,String> map = new HashMap<String,String>();
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				if(list.get(i)[7]!=null&&!"null".equals(list.get(i)[7]+"")&&(list.get(i)[7]+"").length()>0){
					map.put(list.get(i)[1]+"&&"+list.get(i)[3]+"&&"+list.get(i)[5], "完成");
				}else if(simpleDf.parse(list.get(i)[6]+"").getTime()<date.getTime()){
					map.put(list.get(i)[1]+"&&"+list.get(i)[3]+"&&"+list.get(i)[5], list.get(i)[6]+"red");
				}else{
					map.put(list.get(i)[1]+"&&"+list.get(i)[3]+"&&"+list.get(i)[5], list.get(i)[6]+"");
				}
			}
		}
		List<String[]> showList = new ArrayList<String[]>();
		if(monomerList!=null&&monomerList.size()>0){
			for(int i=0;i<monomerList.size();i++){
				if(paperList!=null&&paperList.size()>0){
					String[] strs = new String[paperList.size()];
					for(int j=0;j<paperList.size();j++){
						strs[j] = map.get(monomerList.get(i)[1]+"&&"+monomerList.get(i)[3]+"&&"+paperList.get(j)[0]);
					}
					showList.add(strs);
				}
			}
		}
		request.setAttribute("monomerList", monomerList);
		request.setAttribute("showList", showList);
		request.setAttribute("paperList", paperList);
		return "success";
	}
}
