package com.wonders.stpt.project.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jxcell.util.E;
import com.opensymphony.xwork2.Action;
import com.wonders.stpt.project.model.Project;
import com.wonders.stpt.project.model.ProjectForwardGoal;
import com.wonders.stpt.project.service.ProjectForwardGoalService;
import com.wonders.stpt.project.service.ProjectService;
import com.wonders.stpt.util.ActionWriter;
import com.wondersgroup.framework.security.bo.SecurityUser;

@ParentPackage("struts-default")
@Namespace(value = "/projectForwardGoal/sysinfo")
@Controller("projectForwardGoalAction")
@Scope("prototype")
public class ProjectForwardGoalAction implements ServletResponseAware,SessionAware {
	private Map<String, Object> session;
	private HttpServletResponse httpServletResponse;
	private ActionWriter am;
	@Autowired
	private ProjectForwardGoalService forwardService;
	@Autowired
	private ProjectService projectService;
	private String forwardGoalId;
	private ProjectForwardGoal forwardGoal=new ProjectForwardGoal();
	private List<ProjectForwardGoal> forwardGoals=new ArrayList<ProjectForwardGoal>();
	private String projectId;
	private Project project=new Project();
	private String select;
	private Logger logger=Logger.getLogger(ProjectForwardGoal.class);
	private List<Integer> year=new ArrayList<Integer>();
	@Override
	public void setServletResponse(HttpServletResponse httpServletResponse) {
		// TODO Auto-generated method stub
		this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
        am =new ActionWriter(httpServletResponse);
	}
	/**
	 * 数据保存
	 * @return
	 */
	@org.apache.struts2.convention.annotation.Action(value = "saveForwards", results = {
            @Result(name = "success", location = "/project/sysinfo/list.action",type="redirect")
    })
	public String saveForwards(){
		
		String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        
		if(null!=forwardGoals){
			try {
				project=projectService.getProject(projectId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("projectId查找记录失败"+projectId);
				e.printStackTrace();
			}
			if(forwardGoals.size()>0){
				ProjectForwardGoal p=null;
				for(Iterator<ProjectForwardGoal> i=forwardGoals.iterator();i.hasNext();){
					p=i.next();
					if(StringUtils.isNotBlank(p.getForwardGoalId())){
						
						p.setUpdater(loginName);
						p.setUpdateTime(new Date());
					}else{
						p.setForwardGoalId(null);
						p.setCreateTime(new Date());
						p.setCreator(loginName);
						p.setUpdater(loginName);
						p.setUpdateTime(new Date());
					}
					p.setProject(project);
				}
			}
			System.out.println(year);
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

			
			try {
				System.out.println(sdf.parseObject("2014-1-1"));
				ProjectForwardGoal goal;
				//此处将当年的年度目标选出放入项目中
				project.setProjectForwardGoals(((ProjectForwardGoal)forwardGoals.get(forwardGoals.size()-1)).getForwardGoal());
				projectService.save(project);
				for(int i=0;i<year.size();i++){
					if(year.get(i)>0){
						goal=(ProjectForwardGoal)forwardGoals.get(i);
						goal.setBeginDate(sdf.parse(year.get(i)+"-1-1"));
						goal.setEndDate(sdf.parse(year.get(i)+"-11-31"));
					}
				}
				forwardService.saves(forwardGoals);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("年度推进计划保存失败");
				e.printStackTrace();
			}
		}
		
		System.err.println("select="+select);
		System.err.println("as"+forwardGoals);
		return Action.SUCCESS;
	}
	@org.apache.struts2.convention.annotation.Action(value = "forwardGoals", results = {
            @Result(name = "success", location = "/project/sysinfo/projectForwardGoal_add.jsp")
    })
	public String forwardGoals(){
		if(StringUtils.isNotBlank(projectId)){
			try {
				project=projectService.getProject(projectId);
				forwardGoals=forwardService.projectForwardGoal(projectId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("查找年度推进计划目标出错："+projectId);
				e.printStackTrace();
			}
		}
		return Action.SUCCESS;
	}
	
	
	public String getForwardGoalId() {
		return forwardGoalId;
	}
	public void setForwardGoalId(String forwardGoalId) {
		this.forwardGoalId = forwardGoalId;
	}
	public ProjectForwardGoal getForwardGoal() {
		return forwardGoal;
	}
	public void setForwardGoal(ProjectForwardGoal forwardGoal) {
		this.forwardGoal = forwardGoal;
	}
	public List<ProjectForwardGoal> getForwardGoals() {
		return forwardGoals;
	}
	public void setForwardGoals(List<ProjectForwardGoal> forwardGoals) {
		this.forwardGoals = forwardGoals;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getSelect() {
		return select;
	}
	public void setSelect(String select) {
		this.select = select;
	}
	public List<Integer> getYear() {
		return year;
	}
	public void setYear(List<Integer> year) {
		this.year = year;
	}
	
}
