package com.wonders.stpt.project.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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
import com.wonders.stpt.project.model.WorkProgress;
import com.wonders.stpt.project.service.WorkProgressService;

/**
 * Created by Administrator on 2014/6/24.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/progress")
@Controller("workProgressAction")
@Scope("prototype")
public class WorkProgressAction implements SessionAware,ServletResponseAware{
    private final Logger logger = Logger.getLogger(WorkProgressAction.class);
    @Autowired
    private WorkProgressService workProgressService;
    private List<WorkProgress> workProgresses=new ArrayList<WorkProgress>();
    private String workSecludeId;
    private String workProgressId;
    private WorkProgress workProgress=new WorkProgress();
    private Map<String, Object> session;

    private PageResultSet<WorkProgress> pageResultSet;//页面上的相关信息
    private Integer page = 1;//页码
    private Integer pageSize=10;//每页的记录数

    private String format;
    private HttpServletResponse response;

    @org.apache.struts2.convention.annotation.Action(value = "progresses", results = {
            @Result(name = "success", location = "/project/work/work_history_progress.jsp")
    })
    public String progresses() throws Exception{
        workProgresses = workProgressService.getWorkProgresses(workSecludeId);
        if("json".equals(format)){
            JSONArray jsonArray = JSONArray.fromObject(workProgresses);
            response.getWriter().print(jsonArray.toString());
            return null;
        }
        return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "progress", results = {
            @Result(name = "success", location = "/project/work/work_history_progress.jsp")
    })
    public String progress(){
    	try {
			workProgress=workProgressService.getWorkProgress(workProgressId);
			if("json".equals(format)){
	            JSONArray jsonArray = JSONArray.fromObject(workProgress);
	            response.getWriter().print(jsonArray.toString());
	            return null;
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("查找历史出错");
			e.printStackTrace();
		}
    	
    	return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "workProgresses", results = {
            @Result(name = "success", location = "/project/work/work_history_progress.jsp")
    })
    public String workProgresses() throws Exception{
    	WorkProgress w=new WorkProgress();
    	w.setWorkSecludeId(workSecludeId);
    	pageResultSet=workProgressService.getWorkProgresses(w, page, pageSize);
    	return Action.SUCCESS;
    }
    
    
    @org.apache.struts2.convention.annotation.Action(value = "delete", results = {
            @Result(name = "success", location = "workProgresses.action",type = "redirectAction",params = {"workSecludeId","%{workSecludeId}"})
    })
    public String delete() throws Exception{
        String[] ids = null;
        if(StringUtils.isNotBlank(workProgressId)){
            ids = workProgressId.split(",");
        }else{
            logger.error("删除推进情况出错,传入的参数不正确:"+workProgressId);
            throw new Exception("删除推进情况出错,传入的参数不正确"+workProgressId);
        }
        
        workProgressService.delete(ids);
        return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "save", results = {
            @Result(name = "success", location = "progresses.action",type = "redirectAction",params = {"workSecludeId","%{workSecludeId}"})
    })
    public String save() throws Exception{
        WorkProgress persistentWorkProgress;
        String loginName = (String)session.get("loginName");
        if (StringUtils.isNotBlank(workProgressId)) {//编辑
            persistentWorkProgress = workProgressService.getWorkProgress(workProgressId);
            workProgress.setWorkProgressId(workProgressId);
            workProgress.setUpdater(loginName);
            workProgress.setUpdateTime(new Date());
            BeanUtils.copyProperties(workProgress, persistentWorkProgress, new String[]{"createTime", "creator", "removed"});
        }else{
        	workProgress.setWorkSecludeId(null);
        	workProgress.setCreator(loginName);
        	workProgress.setUpdater(loginName);
            persistentWorkProgress = workProgress;
        }
        workProgressService.save(persistentWorkProgress);
        workProgressId = persistentWorkProgress.getWorkProgressId();
        return Action.SUCCESS;
    }
    
    

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }

    public List<WorkProgress> getWorkProgresses() {
        return workProgresses;
    }

    public void setWorkProgresses(List<WorkProgress> workProgresses) {
        this.workProgresses = workProgresses;
    }

    public String getWorkSecludeId() {
        return workSecludeId;
    }

    public void setWorkSecludeId(String workSecludeId) {
        this.workSecludeId = workSecludeId;
    }

    public String getWorkProgressId() {
        return workProgressId;
    }

    public void setWorkProgressId(String workProgressId) {
        this.workProgressId = workProgressId;
    }

    public WorkProgress getWorkProgress() {
        return workProgress;
    }

    public void setWorkProgress(WorkProgress workProgress) {
        this.workProgress = workProgress;
    }

	public PageResultSet<WorkProgress> getPageResultSet() {
		return pageResultSet;
	}
	public void setPageResultSet(PageResultSet<WorkProgress> pageResultSet) {
		this.pageResultSet = pageResultSet;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        response = httpServletResponse;
        response.setContentType("text/html;charset=utf-8");
    }
}
