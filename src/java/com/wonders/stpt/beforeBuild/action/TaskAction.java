package com.wonders.stpt.beforeBuild.action;

import java.text.DateFormat;
import java.text.ParseException;
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
import com.wonders.stpt.beforeBuild.model.bo.History;
import com.wonders.stpt.beforeBuild.model.bo.MonomerPlan;
import com.wonders.stpt.beforeBuild.model.bo.Task;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/task")
@Controller("taskAction")
@Scope("prototype")
public class TaskAction implements ModelDriven<Task>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat simpleDf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Task task = new Task();

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}
	
	public Task getModel(){
		return task;
	}
	
	@Action(value="findTaskByPage",results={@Result(name="success",location="/beforeBuild/task/list.jsp")})
	public String findTaskByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String taskNum = request.getParameter("taskNum");
		String taskName = request.getParameter("taskName");
		String planName = request.getParameter("planName");
		String mainPerson = request.getParameter("mainPerson");
		String status = request.getParameter("status");
		String type = request.getParameter("type");
		String monomerName = request.getParameter("monomerName");
		String paperName = request.getParameter("paperName");
		String projectName = request.getParameter("projectName");
		
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			List<Object> src = new ArrayList<Object>();
			src.add(id);
			String sql = "select t.project_id,t.project_name from t_before_line_plan t,t_before_monomer_plan t1 "+
					" where t.removed = '0' and t1.removed = '0' and t.id = t1.line_plan_id	and t1.id = ? ";
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			if(list!=null&&list.size()>0){
				projectName = String.valueOf(list.get(0)[1]);
				request.setAttribute("projectId", list.get(0)[0]);
			}
		}
		
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("planName", planName);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
		request.setAttribute("type", type);
		request.setAttribute("monomerName", monomerName);
		request.setAttribute("paperName", paperName);
		request.setAttribute("projectName", projectName);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_monomer_plan t,t_before_line_plan t1,t_before_rule_version t2,t_before_task t3 where t.line_plan_id = t1.id and t.rule_version_id = t2.id and t.id = t3.monomer_plan_id and t.removed = '0' and t1.removed = '0' and t2.removed = '0' and t3.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(oldDeptId!=null&&oldDeptId.length()>0){
			baseSql += " and t3.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		if(id!=null&&id.length()>0){
			baseSql += " and t.id = ? ";
			src.add(id);
		}
		if(monomerName!=null&&monomerName.length()>0){
			baseSql += " and t.monomer_name like ? ";
			src.add("%"+monomerName+"%");
		}
		if(paperName!=null&&paperName.length()>0){
			baseSql += " and t.paper_name = ? ";
			src.add(paperName);
		}
		if(status!=null&&status.length()>0){
			baseSql += " and t3.status = ? ";
			src.add(status);
		}
		if(projectName!=null&&projectName.length()>0){
			baseSql += " and t1.project_name like ? ";
			src.add("%"+projectName+"%");
		}
		
		String orderSql = "";
		String orderParam = request.getParameter("orderParam");
		String orderValue = request.getParameter("orderValue");
		request.setAttribute("orderParam", orderParam);
		request.setAttribute("orderValue", orderValue);
		if(orderParam==null||orderParam.length()==0){
			orderSql = " order by t.create_time desc ";
		}else{
			if("plan_name".equals(orderParam)||"paper_name".equals(orderParam)||"plan_start_time".equals(orderParam)||"plan_finish_time".equals(orderParam)||"main_person".equals(orderParam)){
				orderParam = "t."+orderParam;
			}
			if("task_status".equals(orderParam)){
				orderParam = "t3.status";
			}
			orderSql = " order by "+orderParam;
			if(orderValue.length()>0){
				orderSql += " "+ orderValue;
			}
		}
		
		String listSql = "select t.*,t1.plan_name line_plan_name,t2.version_name rule_version_name,t3.real_start_time,t3.real_finish_time,t3.status task_status,t3.id task_id " + baseSql + orderSql;
		String countSql = "select count(*) count_num " +baseSql;
		int totalRow = beforeBuildService.findPageSize(countSql,src);
		PageInfo pageInfo = new PageInfo(totalRow, Integer.parseInt(pagesize), Integer.parseInt(page));	
		PageResultSet<Map<String,Object>> pageResultSet = new PageResultSet<Map<String,Object>>();
		pageResultSet.setList(beforeBuildService.findByPage(pageInfo.getBeginIndex(), Integer.parseInt(pagesize), listSql,src));
		pageResultSet.setPageInfo(pageInfo);
		request.setAttribute("pageResultSet", pageResultSet);
		
		/*
		if(id!=null&&id.length()>0){
			List<Object> src1 = new ArrayList<Object>();
			src1.add(id);
			List<Object[]> list1 = beforeBuildService.findBySql("select plan_name from t_before_monomer_plan where id = ? ", src1);
			if(list1!=null&&list1.size()>0){
				request.setAttribute("planName", list1.get(0));
			}
		}
		*/
		
		return "success";
	}
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/task/add.jsp")})
	public String toAdd(){
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/task/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			task = (Task)beforeBuildService.load(id,Task.class);
		}
		return "success";
	}
	
	@Action(value="save")
	public String save() throws ParseException{//将任务发布写进历史数据表中（完成的）
		if(task.getId()!=null&&task.getId().length()>0){
			if(task.getRealFinishTime()!=null&&task.getRealFinishTime().length()>0){
				if(task.getRealFinishTime()!=null&&task.getRealFinishTime().length()>0){
					addToHistory(task);//将记录保存到历史表中
				}
			}
			if(!"5".equals(task.getStatus())){
				if(task.getRealFinishTime()!=null&&task.getRealFinishTime().length()>0){
					task.setStatus("4");
				}else if(task.getPlanFinishTime()!=null&&simpleDf.parse(task.getPlanFinishTime()).getTime()<(new Date()).getTime()){
					task.setStatus("3");
				}else{
					task.setStatus("2");
				}
				if(task.getInvalidStartTime() != null && task.getInvalidStartTime().length() >0 && simpleDf.parse(task.getInvalidStartTime()).getTime()>(new Date()).getTime()){
					task.setStatus("6");
				}
				if(task.getInvalidFinishTime() != null && task.getInvalidFinishTime().length() >0 && simpleDf.parse(task.getInvalidFinishTime()).getTime()<(new Date()).getTime()){
					task.setStatus("6");
				}
			}
		}else{
			task.setId(null);
			task.setCreateTime(df.format(new Date()));
			task.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(task);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	private void addToHistory(Task task){
		History history = new History();
		String status = task.getStatus();
		String sql = "select t.id, t.source_id"+
				" from t_before_history t"+
				" where t.removed = '0' and t.source_id = ? ";
		List<Object> src = new ArrayList<Object>();
		src.add(task.getId());		
		List<Object[]> list = beforeBuildService.findBySql(sql, src);
		if(list!=null&&list.size()>0){
			history.setId((String)list.get(0)[0]);
		}
		
		String sql2 = "select l.project_id, l.project_name, l.route_id, l.route_name, m.monomer_id, m.monomer_name, m.monomer_type_id, m.monomer_type_name, m.paper_id, m.paper_name, m.old_dept_id"+
				" from t_before_monomer_plan m, t_before_line_plan l"+
				" where m.line_plan_id = l.id and m.removed = '0' and l.removed = '0'" +
				" and m.id = ? ";		
		List<Object> src2 = new ArrayList<Object>();
		src2.add(task.getMonomerPlanId());
		List<Object[]> list2 = beforeBuildService.findBySql(sql2, src2);
		if(list2!=null&&list2.size()>0){
			Object[] o = list2.get(0);
			history.setProjectId((String)o[0]);
			history.setProjectName((String)o[1]);
			history.setRouteId((String)o[2]);
			history.setRouteName((String)o[3]);
			history.setMonomerId((String)o[4]);
			history.setMonomerName((String)o[5]);
			history.setTypeId((String)o[6]);
			history.setTypeName((String)o[7]);
			history.setPaperId((String)o[8]);
			history.setPaper((String)o[9]);
			history.setDeptId((String)o[10]);
		}
		
		history.setSource("3");
		history.setSourceId(task.getId());
		history.setRealStartTime(task.getRealStartTime());
		history.setRealFinishTime(task.getRealFinishTime());
		history.setRemoved("0");
		history.setResult(task.getBiaodiwu());
		//history.setStatus(task.getStatus());
		history.setPlanStartTime(task.getPlanStartTime());
		history.setPlanFinishTime(task.getPlanFinishTime());
		if(history.getId() == null){
			history.setCreateTime(df.format(new Date()));	
			history.setCreateUser((String)session.getAttribute("loginName"));
		}
		history.setUpdateTime(df.format(new Date()));
		history.setUpdateUser((String)session.getAttribute("loginName"));
		
		if(!"5".equals(status)){
			status = "4";
			try {
				if (task.getInvalidStartTime() != null
						&& task.getInvalidStartTime().length() > 0
						&& simpleDf.parse(task.getInvalidStartTime()).getTime() > (new Date())
								.getTime()) {
					status = "6";
				}
				if (task.getInvalidFinishTime() != null
						&& task.getInvalidFinishTime().length() > 0
						&& simpleDf.parse(task.getInvalidFinishTime())
								.getTime() < (new Date()).getTime()) {
					status = "6";
				}
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
		history.setStatus(status);
		
		beforeBuildService.save(history);
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		String if_have = request.getParameter("if_have");
		if(id!=null&&id.length()>0){
			task = (Task)beforeBuildService.load(id,Task.class);
			task.setRemoved("1");
			beforeBuildService.save(task);
			
			if(if_have!=null&&"yes".equals(if_have)){
				List<Object> src = new ArrayList<Object>();
				src.add(id);
				beforeBuildService.updateBySql("update t_before_task_follow set removed = '1' where task_id = ?", src);
			}
		}
		return null;
	}
	
	@Action(value="findMonomerPlans")
	public String findMonomerPlans(){
		String oldDeptId = request.getParameter("oldDeptId");
		if(oldDeptId!=null&&oldDeptId.length()>0){
			String sql = "select id,plan_name from t_before_monomer_plan where removed = '0' and old_dept_id = ? and check_status = '3' order by create_time ";
			List<Object> src = new ArrayList<Object>();
			src.add(oldDeptId);
			ac.writeJson(beforeBuildService.findBySql(sql, src));
		}
		return null;
	}
	
	@Action(value="startTask")
	public String startTask(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			String time = simpleDf.format(new Date());
			task = (Task)beforeBuildService.load(id,Task.class);
			task.setRealStartTime(time);
			task.setStatus("2");
			beforeBuildService.save(task);
			ac.writeAjax("{\"time\":\""+time+"\"}");
		}
		return null;
	}
	
	@Action(value="finishTask")
	public String finishTask(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			String time = simpleDf.format(new Date());
			task = (Task)beforeBuildService.load(id,Task.class);
			task.setRealFinishTime(time);
			task.setStatus("4");
			beforeBuildService.save(task);
			ac.writeAjax("{\"time\":\""+time+"\"}");
		}
		return null;
	}
	
	@Action(value="ifHaveTaskFollow")
	public String ifHaveTaskFollow(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			String sql = "select count(*) count_num from t_before_task_follow where task_id = ? and removed = '0' ";
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
	
	@Action(value="addTask")
	public String addTask(){
		String projectId = request.getParameter("projectId");
		String oldDeptId = request.getParameter("oldDeptId");
		String sql = "select t1.id,t1.plan_name,t1.plan_start_time,t1.plan_finish_time,t1.warn_days,t1.main_person,t1.phone "+
				" from t_before_line_plan t,t_before_monomer_plan t1 "+
				" where t.id = t1.line_plan_id and t.removed = '0' and t1.removed = '0' and t.project_id = ? " +
				" and t1.old_dept_id = ? and t1.check_status = '3' ";
		List<Object> src = new ArrayList<Object>();
		src.add(projectId);
		src.add(oldDeptId);
		Date date = new Date();
		String simpleDateStr = simpleDf.format(date);
		String dateStr = df.format(date);
		String username = (String)session.getAttribute("userName");
		String loginname = (String)session.getAttribute("loginName");
		if(projectId!=null&&projectId.length()>0){
			List<Object[]> list = beforeBuildService.findBySql(sql, src);
			List<Task> savelist = new ArrayList<Task>();
			if(list!=null&&list.size()>0){
				sql = "select count(*) count_num from t_before_task t where t.removed = '0' and t.monomer_plan_id = ? and t.old_dept_id = ? ";
				for(int i=0;i<list.size();i++){
					src = new ArrayList<Object>();
					src.add(list.get(i)[0]+"");
					src.add(oldDeptId);
					if(beforeBuildService.findPageSize(sql,src)==0){
						Task bo = new Task();
						bo.setTaskName(list.get(i)[1]+"-任务");
						//bo.setPlanStartTime((list.get(i)[2]+"").replace("null", ""));
						//bo.setPlanFinishTime((list.get(i)[3]+"").replace("null", ""));
						bo.setRealStartTime((list.get(i)[2]+"").replace("null", ""));
						bo.setStatus("2");
						if(!"".equals(list.get(i)[2]+"")&&!"null".equals(list.get(i)[2]+"")&&!"".equals(list.get(i)[4]+"")&&!"null".equals(list.get(i)[4]+"")){
							try {
								Date sdate = simpleDf.parse(list.get(i)[2]+"");
								Date wdate = new Date(sdate.getTime()-Integer.parseInt(list.get(i)[4]+"")*24*60*60*1000);
								bo.setWarnTime(simpleDf.format(wdate));
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						bo.setMainPerson((list.get(i)[5]+"").replace("null", ""));
						bo.setPhone((list.get(i)[6]+"").replace("null", ""));
						bo.setUpdatePerson(username);
						bo.setUpdateTime(simpleDateStr);
						bo.setMonomerPlanId(list.get(i)[0]+"");
						bo.setCreateUser(loginname);
						bo.setCreateTime(dateStr);
						bo.setRemoved("0");
						bo.setOldDeptId(oldDeptId);
						savelist.add(bo);
					}
				}
				beforeBuildService.saveOrUpdateAll(savelist);
			}
		}
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="delayFunc")
	public String delayFunc(){
		String sql = "update t_before_task set status = '3' where id in "+
			" (select t.id from t_before_task t,t_before_monomer_plan t1 where t.removed = '0' and t1.removed = '0' "+
			" and t.monomer_plan_id = t1.id and t1.plan_finish_time < to_char(sysdate,'yyyy-mm-dd') "+
			" and t.real_finish_time is null and (t.status = '1' or t.status = '2' or t.status is null))";
		beforeBuildService.updateBySql(sql, null);
		return null;
	}
}
