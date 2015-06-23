package com.wonders.stpt.project.action;

import java.util.*;

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
import com.wonders.stpt.project.model.ProjectForwardGoal;
import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.service.ProjectForwardGoalService;
import com.wonders.stpt.project.service.ProjectService;
import com.wonders.stpt.util.ActionWriter;
import com.wondersgroup.framework.security.bo.SecurityUser;

/**
 * Created by Administrator on 2014/6/16.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/sysinfo")
@Controller("projectAction")
@Scope("prototype")
public class ProjectAction implements ServletResponseAware,SessionAware{
    private final Logger logger = Logger.getLogger(Project.class);
    private PageResultSet pageResultSet;//页面上的相关信息
    private int page=1;//页码
    private int pageSize;//每页的记录数
    private Project project = new Project();
    private String projectId;
    private List<ProjectPlan> projectPlan = new ArrayList<ProjectPlan>();
    private List<Project> projects=new ArrayList<Project>();
    private Integer year;
    private ActionWriter am;
    private Map<String, Object> session;
    @Autowired
    private ProjectForwardGoalService forwardService;
	@Autowired
    private ProjectService projectService;
    private HttpServletResponse httpServletResponse;

    @org.apache.struts2.convention.annotation.Action(value = "delete", results = {
            @Result(name = "success", location = "/project/sysinfo/project_list.jsp")
    })
    public String delete() throws Exception{
        String[] ids = null;
        if(StringUtils.isNotBlank(projectId)){
            ids = projectId.split(",");
        }else{
            logger.error("删除项目出错,传入的参数不正确:"+projectId);
            throw new Exception("删除项目出错,传入的参数不正确"+projectId);
        }
        projectService.deleteProject(ids);
        return projects();
    }

    @org.apache.struts2.convention.annotation.Action(value = "save", results = {
            @Result(name = "success", location = "/project/sysinfo/project_list.jsp"), @Result(name = "input", location = "/project/sysinfo/project_list.jsp")
    })
    /**
     * 保存更新
     */
    public String save() throws Exception{//编辑的时候是新增一条数据？
    	
    	boolean flag=false;
    	
    	String loginName = (String) session.get("loginName");//
        SecurityUser user = (SecurityUser) session.get("user");//
        try {
            Project persistentProject = null;
            if (StringUtils.isNotBlank(projectId)) {//编辑
                persistentProject = projectService.getProject(projectId);
                flag=true;
                project.setProjectId(projectId);
                project.setUpdater(loginName);
                project.setUpdateTime(new Date());
                
                BeanUtils.copyProperties(project, persistentProject, new String[]{"createTime", "creator", "removed"
                        });
                persistentProject.setProjectId(projectId);
                
            } else {//新增
            	project.setProjectId(null);
            	project.setCreator(loginName);
            	project.setUpdater(loginName);
                persistentProject = project;
            }
            //logger.error(project.getDepartment()+"          "+project.getPlanActivateTime());
            //logger.error("persistentProject.toString():"+persistentProject.toString());
            persistentProject=projectService.save(persistentProject);
            if(StringUtils.isNotBlank(persistentProject.getProjectId())){
            	ProjectForwardGoal p=new ProjectForwardGoal();
                p.setProject(persistentProject);
                forwardService.saves(p);
            }
            
            //将修改前记录的removed字段改为1
        } catch (Exception ex) {
        	
            logger.error("保存项目基本信息出错,ID:"+project.getProjectId()+","+ex);
            throw ex;
        }
       // return projects();
        //logger.error("project.getProjectId():"+project.getProjectId());

        if(!flag)
            am.writeAjax("添加成功！！！");
        else
            am.writeAjax("修改成功！！！");
        
        return null;
    }

    @org.apache.struts2.convention.annotation.Action(value = "project", results = {
            @Result(name = "success", location = "/project/sysinfo/project_add.jsp")
    })
    /**
     * 读取项目信息
     */
    public String project() throws Exception{
        if (StringUtils.isNotBlank(projectId))
            project = projectService.getProject(projectId);
      //把修改前的记录保存到历史数据表中
        
        return Action.SUCCESS;
    }


    /**
     * 项目列表
     */
    @org.apache.struts2.convention.annotation.Action(value = "projects", results = {
            @Result(name = "success", location = "/project/sysinfo/project_list.jsp")
    })
    public String projects() throws Exception{
    	//int pages=Integer.parseInt(page);
        pageResultSet = projectService.getProjects(project, page, 10);
        return Action.SUCCESS;
    }

    /**
     * 项目列表
     */
    @org.apache.struts2.convention.annotation.Action(value = "list", results = {
            @Result(name = "success", location = "/project/sysinfo/project_list.jsp")
    })
    public String list() throws Exception{
    	/**int pages=0;
    	if(StringUtils.isNotBlank(page)){//如果page不为空
    		pages=Integer.parseInt(page);
    	}*/
    	if(null==project){
    		project=new Project();
    	}
    	pageResultSet = projectService.getProjects(project, page, pageSize);

    	
    	return Action.SUCCESS;
    }
    
    public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@org.apache.struts2.convention.annotation.Action(value = "view", results = {
            @Result(name = "success", location = "/project/sysinfo/project_view.jsp")
    })
    public String view(){
    	
    	try {
			this.project=projectService.getProject(projectId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("错误信息："+projectId+"       "+e);
			e.printStackTrace();
		}
    	return Action.SUCCESS;
    }

    /**
     * 根据项目主键id值获取项目计划信息
     * @return
     */
    @org.apache.struts2.convention.annotation.Action(value = "getProjectPlans", results = {
            @Result(name = "success",type="redirect", location = "/project/sysinfo/projectPlan_add.jsp")
    })
    public String getProjectPlans(){
    	if(StringUtils.isNotBlank(projectId)){//传过来的id不为空
    		try {
				project=projectService.getProject(projectId);
				projectPlan=projectService.getProjectPlans(projectId, 0);
				/**ProjectPlan p=null;
				for(Iterator<ProjectPlan> i=projectPlan.iterator();i.hasNext();){
					p=i.next();
					logger.error("projectPlan："+p.getProjectPlanId()
							+"      p.getPlanBeginDate"+p.getPlanBeginDate()
							+"      p.getPlanEndDate"+p.getPlanEndDate());
				}*/
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("查找当前主键项目对象失败："+projectId+"    "+e);
				e.printStackTrace();
			}//获取当前项目信息
    	}
    	return Action.SUCCESS;
    }

    public String getProjectId() {
        return projectId;
    }
    /**
     * 跳转到查询项目计划的Action
     * @return
     */
    @org.apache.struts2.convention.annotation.Action(value = "returnPlanList", results = {
            @Result(name = "success",type="redirect", location = "/getProjectPlanList.action?projectId=${projectId}")
    })
    public String returnPlanList(){
    	logger.error("returnPlanList");
    	return Action.SUCCESS;
    }
    
    @org.apache.struts2.convention.annotation.Action(value = "deletes", results = {
            @Result(name = "success", location = "list.action",type="redirectAction")
    })
    public String deletes(){//BeanUtils.copyProperties除了指定的属性不复制外其他的属性都复制，在这删除有问题   很多属性值都变为null（不行）
    	
    	Project presentProject=null;
    	/**int pages=0;
    	 if(StringUtils.isNotBlank(page)){//如果page不为空
    	 
    		pages=Integer.parseInt(page);
    	}
    	
    	try {
    		presentProject = projectService.getProject(project.getProjectId());
             BeanUtils.copyProperties(project, presentProject, new String[]{"createTime", "creator"
                     });
             presentProject.setProjectId(project.getProjectId());
     		presentProject=projectService.save(presentProject);
 			pageResultSet = projectService.getProjects(project, page, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	
    	if(StringUtils.isNotBlank(projectId)){
    		try {
				projectService.delete(projectId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("记录删除出错");
				e.printStackTrace();
			}
    	}else{
    		System.err.println("id为空");
    	}
    	
    	return Action.SUCCESS;
    }
    
    
    @org.apache.struts2.convention.annotation.Action(value = "finished", results = {
            @Result(name = "success", location = "list.action",type="redirectAction")
    })
    public String finished(){
    	
    	Project presentProject=null;
    	/**int pages=0;
    	if(StringUtils.isNotBlank(page)){//如果page不为空
    		pages=Integer.parseInt(page);
    	}*/
    	
    	/**try {
    		presentProject = projectService.getProject(project.getProjectId());
           /* BeanUtils.copyProperties(project, presentProject, new String[]{"createTime", "creator"
                    });
            presentProject.setProjectId(project.getProjectId());
            presentProject.setFinish("1");
           // presentProject.setUpdateTime(new Date());
    		presentProject=projectService.save(presentProject);
    		project=new Project();
			pageResultSet = projectService.getProjects(project, page, pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
    	if(StringUtils.isNotBlank(projectId)){
    		try {
				projectService.finished(projectId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("项目标记完成出错："+projectId);
				e.printStackTrace();
			}
    	}else{
    		logger.debug("未将要标记为完成的项目主键传参");
    	}
    	return Action.SUCCESS;
    }
    
   
    /**
     * 报表信息
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "report", results = {
            @Result(name = "success", location = "/project/sysinfo/report.jsp")
    })
    public String projectPlanReport() throws Exception {
        projects = projectService.getProjectPlanReport(year);
        return Action.SUCCESS;
    }
    
    
    /**
     * 台账信息
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "ledger", results = {
            @Result(name = "success", location = "/project/sysinfo/ledger_list.jsp")
    })
    public String ledger() throws Exception {
        projects = projectService.getProjectPlanReport(year);
        return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "index")
    public String index() throws Exception {
        httpServletResponse.getWriter().print(projectService.countDataToJson(Calendar.getInstance().get(Calendar.YEAR)));
        return Action.NONE;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PageResultSet getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet pageResultSet) {
        this.pageResultSet = pageResultSet;
    }

	public List<ProjectPlan> getProjectPlan() {
		return projectPlan;
	}

	public void setProjectPlan(List<ProjectPlan> projectPlan) {
		this.projectPlan = projectPlan;
	}

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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
