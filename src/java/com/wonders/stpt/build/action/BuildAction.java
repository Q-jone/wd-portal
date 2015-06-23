package com.wonders.stpt.build.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.wonders.stpt.build.service.BuildService;
import com.wonders.stpt.util.ActionWriter;

@ParentPackage("struts-default")
@Namespace(value="/build")
@Controller("buildAction")
@Scope("prototype")
public class BuildAction {
	@Autowired
	private BuildService buildService;
	
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	ActionWriter ac = new ActionWriter(response);
	
	DateFormat df = new SimpleDateFormat("yyyy");
	
	@Action(value="/getProcessData")
	public String getProcessData(){
		List<Object[]> list = buildService.getProcessData();
		ac.writeJson(list);
		return null;
	}
	
	@Action(value="/getProcessList",results={@Result(name="success",location="/build/processList.jsp")})
	public String getProcessList(){
		String processType = request.getParameter("processType");
		request.setAttribute("processType", processType);
		List<Object[]> list = buildService.getProcessList(processType);
		List<String[]> list1 = new ArrayList<String[]>();
		
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				long plan_persent = 0;
				long milestone_persent = 0;
				double abnormal_sum = Double.valueOf(String.valueOf(list.get(i)[4]));
				double plan_sum = Double.valueOf(String.valueOf(list.get(i)[5]));
				double milestone_delay_sum = Double.valueOf(String.valueOf(list.get(i)[2]));
				double milestone_sum = Double.valueOf(String.valueOf(list.get(i)[3]));
				if(plan_sum>0){
					plan_persent = (long) (abnormal_sum*100/plan_sum);
				}
				if(milestone_sum>0){
					milestone_persent = (long) (milestone_delay_sum*100/milestone_sum);
				}
				String[] strs = new String[4];
				strs[0] = (String)list.get(i)[0];
				strs[1] = (String)list.get(i)[1];
				strs[2] = String.valueOf(milestone_persent)+"%";
				strs[3] = String.valueOf(plan_persent)+"%";
				list1.add(strs);
			}
		}
		request.setAttribute("processList", list1);
		return "success";
	}
	
	@Action(value="/getProcessDetailList")
	public String getProcessDetailList(){
		String processType = request.getParameter("processType");
		String project_id = request.getParameter("project_id");
		String detailType = request.getParameter("detailType");
		List<Object[]> detailList = buildService.getProcessDetailList(processType,project_id,detailType);
		List<String[]> detailList1 = new ArrayList<String[]>();
		
		if(detailList!=null&&detailList.size()>0){
			for(int i=0;i<detailList.size();i++){
				long begin_delay_persent = 0;
				long finish_delay_persent = 0;
				double plan_sum = Double.valueOf(String.valueOf(detailList.get(i)[2]));
				double begin_delay_sum = Double.valueOf(String.valueOf(detailList.get(i)[3]));
				double finish_delay_sum = Double.valueOf(String.valueOf(detailList.get(i)[4]));
				if(plan_sum>0){
					begin_delay_persent = (long) (begin_delay_sum*100/plan_sum);
					finish_delay_persent = (long) (finish_delay_sum*100/plan_sum);
				}
				
				String[] strs = new String[10];
				strs[0] = (String)detailList.get(i)[0];
				strs[1] = String.valueOf(detailList.get(i)[2]);
				strs[2] = String.valueOf(detailList.get(i)[3]);
				strs[3] = String.valueOf(begin_delay_persent)+"%";
				strs[4] = String.valueOf(detailList.get(i)[4]);
				strs[5] = String.valueOf(finish_delay_persent)+"%";
				strs[6] = String.valueOf(detailList.get(i)[5]);
				strs[7] = String.valueOf(detailList.get(i)[6]);
				strs[8] = String.valueOf(detailList.get(i)[7]);
				strs[9] = (String)detailList.get(i)[1];
				detailList1.add(strs);
			}
		}
		ac.writeJson(detailList1);
		return null;
	}
	
	@Action(value="/getMonthDetailData")
	public String getMonthDetailData(){
		String processType = request.getParameter("processType");
		String project_id = request.getParameter("project_id");
		String detailType = request.getParameter("detailType");
		List<Object[]> detailList = buildService.getProcessDetailList(processType,project_id,detailType);
		List<String[]> detailList1 = new ArrayList<String[]>();
		
		String if_abnormal = "";
		if(detailList!=null&&detailList.size()>0){
			for(int i=0;i<detailList.size();i++){
				long begin_persent = 0;
				long finish_persent = 0;
				double begin_plan_sum = Double.valueOf(String.valueOf(detailList.get(i)[2]));
				double begin_delay_sum = Double.valueOf(String.valueOf(detailList.get(i)[4]));
				if(begin_plan_sum>0){
					begin_persent = (long) (begin_delay_sum*100/begin_plan_sum);
				}
				double finish_plan_sum = Double.valueOf(String.valueOf(detailList.get(i)[5]));
				double finish_delay_sum = Double.valueOf(String.valueOf(detailList.get(i)[7]));
				if(finish_plan_sum>0){
					finish_persent = (long) (finish_delay_sum*100/finish_plan_sum);
				}
				if(begin_delay_sum>0||finish_delay_sum>0){
					if_abnormal = "异常";
				}else{
					if_abnormal = "正常";
				}
				
				String[] strs = new String[12];
				strs[0] = (String)detailList.get(i)[0];
				strs[1] = (String)detailList.get(i)[1];
				strs[2] = if_abnormal;
				strs[3] = String.valueOf(detailList.get(i)[2]);
				strs[4] = String.valueOf(detailList.get(i)[3]);
				strs[5] = String.valueOf(detailList.get(i)[4]);
				strs[6] = String.valueOf(begin_persent)+"%";
				strs[7] = String.valueOf(detailList.get(i)[5]);
				strs[8] = String.valueOf(detailList.get(i)[6]);
				strs[9] = String.valueOf(detailList.get(i)[7]);
				strs[10] = String.valueOf(finish_persent)+"%";
				strs[11] = "";
				detailList1.add(strs);
			}
		}
		ac.writeJson(detailList1);
		return null;
	}
	
	@Action(value="/getTasksList",results={@Result(name="success",location="/build/tasksList.jsp")})
	public String getTasksList(){
		String project_id = this.request.getParameter("project_id");
		String plan_id = this.request.getParameter("plan_id");
		String processType = this.request.getParameter("processType");
		String detailType = this.request.getParameter("detailType");
		String type = this.request.getParameter("type");
		List<Object[]> list = buildService.getTasksList(project_id,plan_id,processType,detailType,type);
		this.request.setAttribute("list", list);
		this.request.setAttribute("processType", processType);
		this.request.setAttribute("detailType", detailType);
		return "success";
	}

	@Action(value="/getProcessYearCompareList",results={@Result(name="success",location="/build/processYearCompareList.jsp")})
	public String getProcessYearCompareList(){
		String year = request.getParameter("stat_year");
		if(year==null){
			Date date = new Date();
			year = df.format(date);
		}
		String project_name = request.getParameter("project_name");
		String type_name = request.getParameter("type_name");
		List<Object[]> list = this.buildService.getProcessYearCompareList(year,project_name,type_name);
		this.request.setAttribute("list", list);
		this.request.setAttribute("year", year);
		this.request.setAttribute("project_name", project_name);
		this.request.setAttribute("type_name", type_name);
		return "success";
	}
	
	@Action(value="/getProcessMonthCompareList",results={@Result(name="success",location="/build/processMonthCompareList.jsp")})
	public String getProcessMonthCompareList(){
		String year = request.getParameter("stat_year");
		if(year==null){
			Date date = new Date();
			year = df.format(date);
		}
		String stat_month = request.getParameter("stat_month");
		String type_name = request.getParameter("type_name");
		String project_name = request.getParameter("project_name");
		List<Object[]> list = this.buildService.getProcessMonthCompareList(year,stat_month,type_name,project_name);
		this.request.setAttribute("list", list);
		this.request.setAttribute("year", year);
		this.request.setAttribute("stat_month", stat_month);
		this.request.setAttribute("type_name", type_name);
		this.request.setAttribute("project_name", project_name);
		return "success";
	}
	
	@Action(value="/getListBySql")
	public String getListBySql(){
		String sql = this.request.getParameter("sql");
		List list = buildService.getListBySql(sql);
		if(list!=null&&list.size()>0){
			ac.writeJson(list);
		}
		return null;
	}
	
	@Action(value="/findProcessPlanComparision",results={@Result(name="success",location="/build/processPlanComparisionList.jsp")})
	public String findProcessPlanComparision(){
		String projectId = this.request.getParameter("projectId");
		String dateType = this.request.getParameter("dateType");
		if(projectId==null){
			projectId = "";
		}
		if(dateType==null){
			dateType = "month";
		}
		List<Object[]> list = this.buildService.findProcessPlanComparision(projectId,dateType);
		this.request.setAttribute("list", list);
		this.request.setAttribute("projectId", projectId);
		this.request.setAttribute("dateType", dateType);
		return "success";
	}
}
