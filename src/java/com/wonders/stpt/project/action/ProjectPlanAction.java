package com.wonders.stpt.project.action;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.Project;
import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.model.ProjectPlanHistory;
import com.wonders.stpt.project.service.ProjectPlanHistoryService;
import com.wonders.stpt.project.service.ProjectPlanService;
import com.wonders.stpt.project.service.ProjectService;
import com.wonders.stpt.util.ActionWriter;
import com.wondersgroup.framework.security.bo.SecurityUser;

/**
 * 项目计划action
 * @author mengjie
 *
 */
@ParentPackage("struts-default")
@Namespace(value="/projectPlan/sysinfo")
@Controller("projectPlanAction")
@Scope("prototype")
public class ProjectPlanAction implements ServletResponseAware,SessionAware {

	 private final Logger logger = Logger.getLogger(Project.class);
	 private PageResultSet pageResultSet;//页面上的相关信息
	 private String page;//页码
	 private int pageSize;//每页的记录数
	 private ProjectPlan projectPlans=new ProjectPlan();
	 private List<ProjectPlan> projectPlan=new ArrayList<ProjectPlan>();
	 private String projectPlanId;
	 private String projectId;
	 private Project project=new Project();
     private ActionWriter am;
	 @Autowired
	 private ProjectPlanService projectPlanService;
	 @Autowired
	 private ProjectService projectService;
	 @Autowired
	 private ProjectPlanHistoryService projectPlanHistoryService;
     private HttpServletResponse httpServletResponse;
     private Map<String, Object> session;
    @org.apache.struts2.convention.annotation.Action(value = "save", results = {
	            @Result(name = "success", location = "/projectPlan/sysinfo/projectPlan_adds.jsp")
	    })
	 public String save()throws Exception{//单条数据添加成功
		 /**logger.error("jin");
		 String projectId="8a81e6e546b27ec20146b27f1b4d0003";*/
    	 String loginName = (String) session.get("loginName");//
         SecurityUser user = (SecurityUser) session.get("user");//
		 ProjectPlan presentPlan=null;
		 Project project=projectService.getProject(projectId);
		 //logger.error("project.getProjectName()"+project.getProjectName());
		 projectPlans.setProject(project);
		 try{
			 if(StringUtils.isNotBlank(projectPlanId)){//编辑
				 projectPlans.setUpdater(loginName);
				 projectPlans.setUpdateTime(new Date());
				 presentPlan=projectPlanService.getProjectPlan(projectPlanId);
				 BeanUtils.copyProperties(projectPlans, presentPlan, new String[]{"createTime", "creator", "removed",
						 });
				 
			 }else{//新增
				 projectPlans.setProjectPlanId(null);
				 projectPlans.setCreator(loginName);
				 projectPlans.setUpdater(loginName);
				 logger.error("projectPlan.getPlanBeginDate():"+projectPlans.getPlanBeginDate());
				 presentPlan=projectPlans;
			 }
			 
			 presentPlan=projectPlanService.save(presentPlan);
			 
		 }catch(Exception e){
			 logger.error("保存或更新项目计划信息出错："+presentPlan.getProjectPlanId()+",->"+e);
			 throw e;
		 }
		 am.writeAjax("成功");
		 return null;
	 }
	 
	 @org.apache.struts2.convention.annotation.Action(value = "saves", results = {
	            @Result(name = "success", type = "redirect", location = "/project/sysinfo/list.action")
	    })
	 public String saves(){//批量更新添加
		 Project project=null;
		 List<ProjectPlanHistory> historyList=new ArrayList<ProjectPlanHistory>();
		 ProjectPlanHistory history=null;
		 String loginName = (String) session.get("loginName");//
         SecurityUser user = (SecurityUser) session.get("user");//
		 if(projectPlan!=null){
			 try {
				project=projectService.getProject(projectId);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				logger.error("项目查询出错");
				e1.printStackTrace();
			}
			 if(projectPlan.size()>0){
				 logger.error("批量增加数据进入");
				 ProjectPlan p=null;
				 for(Iterator<ProjectPlan> i=projectPlan.iterator();i.hasNext();){
					 p=i.next();
					 p.setProject(project);
					 
					 if(StringUtils.isNotBlank(p.getProjectPlanId())){//修改
						 history=new ProjectPlanHistory();
						 p.setUpdater(loginName);
						 p.setUpdateTime(new Date());
						 BeanUtils.copyProperties(p, history, new String[]{"createTime", "creator", "removed","versions"});
						 history.setProjectId(projectId);
						 historyList.add(history);
					 }else{//新增
						 p.setCreator(loginName);
						 p.setProjectPlanId(null);
						 p.setUpdater(loginName);
//						 BeanUtils.copyProperties(p, history, new String[]{"createTime", "creator", "removed","versions"});
					 }
				 }
				 try {
					 if(historyList.size()>0)
						 projectPlanHistoryService.save(historyList);
					 //logger.error("ss");
					projectPlanService.save(projectPlan);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("项目计划添加或更新出错：："+e);
					e.printStackTrace();
				}
			 }
		 }else{
			 logger.error("前台数据为空");
		 }
		 return Action.SUCCESS;
	 }
	 
	 @org.apache.struts2.convention.annotation.Action(value = "getPageResultSet", results = {
	            @Result(name = "success", type = "redirect", location = "/project/list.action")
	    })
	public PageResultSet getPageResultSet() {
		return pageResultSet;
	}
	 
	 
	 	/**
	     * 根据项目主键id值获取项目计划信息
	     * @return
	     */
	    @org.apache.struts2.convention.annotation.Action(value = "getProjectPlanList", results = {
	            @Result(name = "success",location = "/project/sysinfo/projectPlan_add.jsp")
	    })
	    public String getProjectPlanList(){
	    	logger.error("projectId:"+projectId);
	    	logger.error("进入计管理页面");
	    	if(StringUtils.isNotBlank(projectId)){//传过来的id不为空
	    		try {
					project=projectService.getProject(projectId);
					projectPlan=projectService.getProjectPlans(projectId, 0);
					ProjectPlan p=null;
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					logger.error("查找当前主键项目对象失败："+projectId+"    "+e);
					e.printStackTrace();
				}//获取当前项目信息
	    	}
	    	return Action.SUCCESS;
	    }

	public void setPageResultSet(PageResultSet pageResultSet) {
		this.pageResultSet = pageResultSet;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public ProjectPlan getProjectPlans() {
		return projectPlans;
	}

	public void setProjectPlans(ProjectPlan projectPlans) {
		this.projectPlans = projectPlans;
	}

	public String getProjectPlanId() {
		return projectPlanId;
	}
	public void setProjectPlanId(String projectPlanId) {
		this.projectPlanId = projectPlanId;
	}

	public List<ProjectPlan> getProjectPlan() {
		return projectPlan;
	}

	public void setProjectPlan(List<ProjectPlan> projectPlan) {
		this.projectPlan = projectPlan;
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

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
        am =new ActionWriter(httpServletResponse);
    }

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
