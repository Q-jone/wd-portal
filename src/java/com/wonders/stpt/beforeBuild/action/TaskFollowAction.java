package com.wonders.stpt.beforeBuild.action;

import java.io.IOException;
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
import com.wonders.stpt.beforeBuild.model.bo.TaskFollow;
import com.wonders.stpt.beforeBuild.service.BeforeBuildService;
import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.util.ActionWriter;
import com.wonders.stpt.util.StringUtil;

@ParentPackage("struts-default")
@Namespace(value="/taskFollow")
@Controller("taskFollowAction")
@Scope("prototype")
public class TaskFollowAction implements ModelDriven<TaskFollow>{
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request = (HttpServletRequest) actionContext.get(ServletActionContext.HTTP_REQUEST);
	public HttpServletResponse response = (HttpServletResponse) actionContext.get(ServletActionContext.HTTP_RESPONSE);
	public HttpSession session = request.getSession();
	
	@Autowired
	private BeforeBuildService beforeBuildService;
	
	ActionWriter ac = new ActionWriter(response);
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
	DateFormat df2 = new SimpleDateFormat("HH:mm:ss");
	
	private TaskFollow taskFollow = new TaskFollow();

	public TaskFollow getTaskFollow() {
		return taskFollow;
	}

	public void setTaskFollow(TaskFollow taskFollow) {
		this.taskFollow = taskFollow;
	}
	
	public TaskFollow getModel(){
		return taskFollow;
	}
	
	@Action(value="findTaskFollowByPage",results={@Result(name="success",location="/beforeBuild/taskFollow/list.jsp")})
	public String findTaskFollowByPage(){
		String pagesize = StringUtil.getNotNullValueString(request.getParameter("pagesize"));
		String page = StringUtil.getNotNullValueString(request.getParameter("page"));
		String oldDeptId = StringUtil.getNotNullValueString(request.getParameter("oldDeptId"));
		String taskName = request.getParameter("taskName");
		
		String id = request.getParameter("id");
		
		request.setAttribute("oldDeptId", oldDeptId);
		request.setAttribute("page", page);
		request.setAttribute("id", id);
		request.setAttribute("taskName", taskName);
		
		if(pagesize==null||"".equals(pagesize)){
			pagesize = "10";
		}
		if(page==null||"".equals(page)){
			page = "1";
		}
		String baseSql = " from t_before_task_follow t,t_before_task t1 where t.task_id = t1.id and t.removed = '0' and t1.removed = '0' ";
		List<Object> src = new ArrayList<Object>();
		if(oldDeptId!=null&&oldDeptId.length()>0){
			baseSql += " and t.old_dept_id = ? ";
			src.add(oldDeptId);
		}
		if(taskName!=null&&taskName.length()>0){
			baseSql += " and t1.task_name like ? ";
			src.add("%"+taskName+"%");
		}
		if(id!=null&&id.length()>0){
			baseSql += " and t.task_id = ? ";
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
		
		String listSql = "select t.*,t1.task_name " + baseSql +" order by t.create_time desc ";
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
			List<Object[]> list1 = beforeBuildService.findBySql("select task_name from t_before_task where id = ? ", src1);
			if(list1!=null&&list1.size()>0){
				request.setAttribute("taskName", list1.get(0));
			}
		}
		
		return "success";
	}
	
	@Action(value="toAdd",results={@Result(name="success",location="/beforeBuild/taskFollow/add.jsp")})
	public String toAdd(){
		String nowDate = df1.format(new Date());
		String nowTime = df2.format(new Date());
		request.setAttribute("nowDate", nowDate);
		request.setAttribute("nowTime", nowTime);
		return "success";
	}
	
	@Action(value="toEdit",results={@Result(name="success",location="/beforeBuild/taskFollow/add.jsp")})
	public String toEdit(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			taskFollow = (TaskFollow)beforeBuildService.load(id,TaskFollow.class);
		}
		return "success";
	}
	
	@Action(value="save")
	public String save(){
		if(taskFollow.getId()!=null&&taskFollow.getId().length()>0){
			taskFollow.setUpdateTime(df.format(new Date()));
			taskFollow.setUpdateUser((String)session.getAttribute("loginName"));
		}else{
			taskFollow.setId(null);
			taskFollow.setCreateTime(df.format(new Date()));
			taskFollow.setCreateUser((String)session.getAttribute("loginName"));
		}
		beforeBuildService.save(taskFollow);
		ac.writeAjax("{\"if_success\":\"yes\"}");
		return null;
	}
	
	@Action(value="delete")
	public String delete(){
		String id = request.getParameter("id");
		if(id!=null&&id.length()>0){
			taskFollow = (TaskFollow)beforeBuildService.load(id,TaskFollow.class);
			taskFollow.setRemoved("1");
			taskFollow.setUpdateTime(df.format(new Date()));
			taskFollow.setUpdateUser((String)session.getAttribute("loginName"));
			beforeBuildService.save(taskFollow);
		}
		return null;
	}
	
	@Action(value="findTasks")
	public String findTasks(){
		String sql = "select id,task_name from t_before_task where removed = '0' order by create_time ";
		ac.writeJson(beforeBuildService.findBySql(sql, null));
		return null;
	}
}
