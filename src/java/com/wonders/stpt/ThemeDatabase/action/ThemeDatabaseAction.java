package com.wonders.stpt.ThemeDatabase.action;

import java.text.DateFormat;
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
import com.wonders.stpt.ThemeDatabase.model.bo.GreataProject;
import com.wonders.stpt.ThemeDatabase.model.bo.GreataProjectCompany;
import com.wonders.stpt.ThemeDatabase.service.ThemeDatabaseService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/themeDatabase")
@Controller("themeDatabaseAction")
@Scope("prototype")
public class ThemeDatabaseAction {
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private ThemeDatabaseService themeDatabaseService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat dfy = new SimpleDateFormat("yyyy");
	
	@Action(value="showIndex",results={@Result(name="success",location="/themeDatabase/index.jsp")})
	public String showIndex(){
		String sql = "select t.id,t.project_id,t.line_id,t.project_name from greata_project t where t.removed = '0' order by t.project_name";
		List<Object[]> projectList = themeDatabaseService.findBySql(sql, null);
		List<Object[]> assetList = findEstimateList();
		request.setAttribute("projectList", projectList);
		request.setAttribute("assetList", assetList);
		return "success";
	}
	
	@Action(value="showPlanTask")
	public String showPlanTask(){
		String sql = "select t.project_id,t1.plan_type_name,sum(t.total_completion) "+
				" from greata_plan_task t,greata_plan_type t1 "+
				" where t.plan_id = t1.plan_id "+
				" group by t.project_id,t1.plan_type_name "+
				" order by t.project_id,t1.plan_type_name";
		List<Object[]> list = themeDatabaseService.findBySql(sql, null);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="showQuality")
	public String showQuality(){
		String sql = "select t.project_id,t.status,count(*) from greata_quality t " +
			" where to_date(submit_time,'yyyy-mm-dd')>sysdate-30 "+
			" group by t.project_id,t.status order by t.project_id,t.status";
		List<Object[]> list = themeDatabaseService.findBySql(sql, null);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="toIntroduction",results={@Result(name="introduction",location="/themeDatabase/introduction.jsp")})
	public String introduction(){
		String id=request.getParameter("id");
		if(id!=null&&id.length()>0){
			GreataProject go=(GreataProject)themeDatabaseService.load(id, GreataProject.class);
			if(go.getProjectCompanyId()!=null&&go.getProjectCompanyId().length()>0){
				GreataProjectCompany co=(GreataProjectCompany)themeDatabaseService.load(go.getProjectCompanyId(), GreataProjectCompany.class);
				request.setAttribute("co", co);
			}
			request.setAttribute("go", go);
		}
		return "introduction";
	}
	
	@Action(value="toView",results={@Result(name="view",location="/themeDatabase/view.jsp")})
	public String view(){
		String projectId=request.getParameter("projectId");
		String sql="";
		List<Object> list=new ArrayList<Object>();
		if(projectId!=null){
			sql+="select t.plan_name,t.plan_begin_time,t.plan_finish_time,t.real_begin_time, t.real_finish_time  "+
					" from greata_plan_task t  "+
					" where t.removed='0' and t.plan_type='9' and t.project_id = ? order by t.plan_name desc";
			list.add(projectId);
			List<Object[]> pbt=themeDatabaseService.findBySql(sql, list);
			request.setAttribute("list", pbt);
			return "view";
		}
		
		return "view";
	}
	
	@Action(value="toEstimate",results={@Result(name="estimate",location="/themeDatabase/estimate.jsp")})
	public String estimate(){
		String projectId=request.getParameter("projectId");
		String sql="";
		List<Object> list=new ArrayList<Object>();
		if(projectId!=null){
			sql+="select t.estimates_name,t.final_base_amount "+
				"from greata_estimates t where t.project_id = ?";
			list.add(projectId);	
			List<Object[]> ge=themeDatabaseService.findBySql(sql, list);
			request.setAttribute("list", ge);
			return "estimate";
		}
		return "estimate";
	}
	
	@Action(value="countPlanTask")
	public String countPlanTask(){
		String year = dfy.format(new Date());
		
		String sql = "select distinct t.plan_type_name from greata_plan_type t where t.removed = '0' order by t.plan_type_name";
		List<Object[]> list = themeDatabaseService.findBySql(sql, null);
		
		sql = "select t1.plan_type_name,count(*) from greata_plan_task t,greata_plan_type t1 "+
				" where t.plan_id = t1.plan_id and t.removed = '0' and t1.removed = '0' "+
				" group by t1.plan_type_name order by t1.plan_type_name";
		List<Object[]> list1 = themeDatabaseService.findBySql(sql, null);
		
		sql = "select t1.plan_type_name,count(*) from greata_plan_task t,greata_plan_type t1 "+
				" where t.plan_id = t1.plan_id and t.removed = '0' and t1.removed = '0' "+
				" and t.real_finish_time is not null "+
				" group by t1.plan_type_name order by t1.plan_type_name";
		List<Object[]> list2 = themeDatabaseService.findBySql(sql, null);
		
		sql = "select t1.plan_type_name,count(*) from greata_plan_task t,greata_plan_type t1 "+
				" where t.plan_id = t1.plan_id and t.removed = '0' and t1.removed = '0' "+
				" and t.plan_finish_time like '"+year+"%' "+
				" group by t1.plan_type_name order by t1.plan_type_name";
		List<Object[]> list3 = themeDatabaseService.findBySql(sql, null);
		
		sql = "select t1.plan_type_name,count(*) from greata_plan_task t,greata_plan_type t1 "+
				" where t.plan_id = t1.plan_id and t.removed = '0' and t1.removed = '0' "+
				" and t.real_finish_time like '"+year+"%' "+
				" group by t1.plan_type_name order by t1.plan_type_name";
		List<Object[]> list4 = themeDatabaseService.findBySql(sql, null);
		
		List<Integer[]> outList = new ArrayList<Integer[]>();
		if(list!=null&&list.size()>0){
			Map<String,Integer> map1 = new HashMap<String,Integer>();
			if(list1!=null&&list1.size()>0){
				for(int i=0;i<list1.size();i++){
					map1.put(list1.get(i)[0]+"", Integer.parseInt(list1.get(i)[1]+""));
				}
			}
			
			Map<String,Integer> map2 = new HashMap<String,Integer>();
			if(list2!=null&&list2.size()>0){
				for(int i=0;i<list2.size();i++){
					map2.put(list2.get(i)[0]+"", Integer.parseInt(list2.get(i)[1]+""));
				}
			}
			
			Map<String,Integer> map3 = new HashMap<String,Integer>();
			if(list3!=null&&list3.size()>0){
				for(int i=0;i<list3.size();i++){
					map3.put(list3.get(i)[0]+"", Integer.parseInt(list3.get(i)[1]+""));
				}
			}
			
			Map<String,Integer> map4 = new HashMap<String,Integer>();
			if(list4!=null&&list4.size()>0){
				for(int i=0;i<list4.size();i++){
					map4.put(list4.get(i)[0]+"", Integer.parseInt(list4.get(i)[1]+""));
				}
			}
			
			
			Integer[] datas1 = new Integer[list.size()];
			Integer[] datas2 = new Integer[list.size()];
			Integer[] datas3 = new Integer[list.size()];
			Integer[] datas4 = new Integer[list.size()];
			for(int i=0;i<list.size();i++){
				if(map1.containsKey(list.get(i))){
					datas1[i] = map1.get(list.get(i));
				}else{
					datas1[i] = 0;
				}
				
				if(map2.containsKey(list.get(i))){
					datas2[i] = map2.get(list.get(i));
				}else{
					datas2[i] = 0;
				}
				
				if(map3.containsKey(list.get(i))){
					datas3[i] = map3.get(list.get(i));
				}else{
					datas3[i] = 0;
				}
				
				if(map4.containsKey(list.get(i))){
					datas4[i] = map4.get(list.get(i));
				}else{
					datas4[i] = 0;
				}
			}
			
			outList.add(datas1);
			outList.add(datas2);
			outList.add(datas3);
			outList.add(datas4);
		}
		ac.writeJson(outList);
		return null;
	}
	
	@Action(value="findAllTypes")
	public String findAllTypes(){
		String sql = "select distinct t.plan_type_name from greata_plan_type t where t.removed = '0' order by t.plan_type_name";
		List<Object[]> list = themeDatabaseService.findBySql(sql, null);
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="toCountPlanTaskPage",results={@Result(name="success",location="/themeDatabase/countPlanTask.jsp")})
	public String tocountPlanTaskPage(){
		
		return "success";
	}
	
	public List<Object[]> findEstimateList(){
		String sql = "select v.*,sum(nvl(t2.final_base_amount,0)) sum6 from ( "+
				" select t1.project_name,t1.project_id,sum(nvl(t.done_contract_amount,0)) sum1,sum(nvl(t.approved_change_amount,0)) sum2,sum(nvl(t.todo_change_amount,0)) sum3,"+
				" sum(nvl(t.total_payment,0)) sum4,sum(nvl(t.this_month_payment,0)) sum5 from greata_asset_execute t right join greata_project t1 "+
		        " on t.project_id = t1.project_id group by t1.project_name,t1.project_id) v "+
		        " left join greata_estimates t2 on t2.project_id = v.project_id "+
		        " group by v.project_name,v.project_id,v.sum1,v.sum2,v.sum3,v.sum4,v.sum5 order by v.project_name";
		return themeDatabaseService.findBySql(sql, null);
	}
	
	@Action(value="showEstimateView",results={@Result(name="success",location="/themeDatabase/estimateView.jsp")})
	public String showEstimateView(){
		List<Object[]> list = findEstimateList();
		List<String[]> showList = new ArrayList<String[]>();
		String[] sum = {"合计：","0","0","0","0","0","0"};
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				String[] strs = new String[7];
				strs[0] = list.get(i)[0]+"";
				strs[1] = list.get(i)[7]+"";
				strs[2] = list.get(i)[2]+"";
				strs[3] = list.get(i)[3]+"";
				strs[4] = list.get(i)[4]+"";
				strs[5] = String.valueOf(Double.parseDouble(list.get(i)[2]+"")+Double.parseDouble(list.get(i)[3]+""));
				strs[6] = String.valueOf(Double.parseDouble(strs[5])-Double.parseDouble(list.get(i)[7]+"")<0?0:Double.parseDouble(strs[5])-Double.parseDouble(list.get(i)[7]+""));
				showList.add(strs);
				sum[1] = String.valueOf(Double.parseDouble(strs[1])+Double.parseDouble(sum[1]));
				sum[2] = String.valueOf(Double.parseDouble(strs[2])+Double.parseDouble(sum[2]));
				sum[3] = String.valueOf(Double.parseDouble(strs[3])+Double.parseDouble(sum[3]));
				sum[4] = String.valueOf(Double.parseDouble(strs[4])+Double.parseDouble(sum[4]));
				sum[5] = String.valueOf(Double.parseDouble(strs[5])+Double.parseDouble(sum[5]));
				sum[6] = String.valueOf(Double.parseDouble(strs[6])+Double.parseDouble(sum[6]));
			}
			showList.add(sum);
		}
		request.setAttribute("showList", showList);
		return "success";
	}
}
