package com.wonders.stpt.project.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
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
import com.wonders.stpt.project.model.WorkInspect;
import com.wonders.stpt.project.service.WorkInspectService;
import com.wondersgroup.framework.security.bo.SecurityUser;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2014/6/26.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/inspect")
@Controller("workInspectAction")
@Scope("prototype")
public class WorkInspectAction implements SessionAware,ServletResponseAware {

    private WorkInspect inspect=new WorkInspect();
    private Map<String, Object> session;
    @Autowired
    private WorkInspectService workInspectService;
    private Integer page=1;
    private Integer year;
    private PageResultSet<WorkInspect> pageResultSet;
    private String workInspectId;
    private HttpServletResponse httpServletResponse;

    @org.apache.struts2.convention.annotation.Action(value = "report", results = {
            @Result(name = "success", location = "/project/inspect/report.jsp")
    })
    public String report() throws Exception {

        if (inspect == null) {
            inspect = new WorkInspect();
        }
        pageResultSet = workInspectService.getWorkInspects(inspect, year, 1, Integer.MAX_VALUE);
        return Action.SUCCESS;
    }
    /**
     * 分页显示
     * @return
     * @throws Exception 
     */
    @org.apache.struts2.convention.annotation.Action(value = "inspects", results = {
            @Result(name = "success", location = "/project/inspect/work_inspect_list.jsp")
    })
    public String inspects() throws Exception{
    	if(inspect==null){
    		inspect=new WorkInspect();
    	}
    	pageResultSet=workInspectService.getWorkInspects(inspect, year, page, 10);
    	return Action.SUCCESS;
    }
    /**
     * 逻辑删除
     * @return
     * @throws Exception 
     */
    @org.apache.struts2.convention.annotation.Action(value = "deletes", results = {
            @Result(name = "success", location = "inspects.action",type="redirectAction",params={"page","%{page}"})
    })
    public String deletes() throws Exception{
    	//WorkInspect workInspect=workInspectService.getInspect(workInspectId);
    	
    	if(StringUtils.isNotBlank(workInspectId)){
    		//workInspect.setRemoved("1");
    		workInspectService.delete(workInspectId);
    	}
    	return Action.SUCCESS;
    }
    /**
     * 根据主键查找记录
     * @return
     * @throws Exception 
     */
    @org.apache.struts2.convention.annotation.Action(value = "find", results = {
            @Result(name = "success", location = "/project/inspect/work_inspect_add.jsp")
    })
    public String find() throws Exception{
    	if(StringUtils.isNotBlank(workInspectId)){
    		inspect=workInspectService.getInspect(workInspectId);
    	}
    	return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "index")
    public String index() throws Exception{
        String result = workInspectService.countDataToJson(Calendar.getInstance().get(Calendar.YEAR));//年度信息
        httpServletResponse.getWriter().print(result);
        return Action.NONE;
    }

    /**
     * 添加页面跳转
     * @return
     */
    @org.apache.struts2.convention.annotation.Action(value = "goAdd", results = {
            @Result(name = "success", location = "/project/inspect/work_inspect_add.jsp")
    })
    public String goAdd(){
    	
    	return Action.SUCCESS;
    }
    @org.apache.struts2.convention.annotation.Action(value = "save", results = {
            @Result(name = "success", location = "/project/inspect/inspects.action",type="redirectAction")
    })
    public  String  save() throws Exception{
    	String loginName = (String) session.get("loginName");//获取用户信息
        SecurityUser user = (SecurityUser) session.get("user");
        WorkInspect  workInspect;
        try {
			if (StringUtils.isNotBlank(workInspectId)) {//修改
				workInspect = workInspectService.getInspect(workInspectId);
				inspect.setWorkInspectId(workInspectId);
				inspect.setUpdater(loginName);
				inspect.setUpdateTime(new Date());
				BeanUtils.copyProperties(inspect, workInspect, new String[] {
						"createTime", "creator", "removed" });
			} else {
				//if("".equals(inspect.getc))//检查是否需要发布
				inspect.setWorkInspectId(null);
				inspect.setCreator(loginName);
				inspect.setUpdater(loginName);
				workInspect = inspect;
			}
			workInspectService.save(workInspect);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("错误");
		}
		System.err.println(inspect);
    	return Action.SUCCESS;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }

    public WorkInspect getInspect() {
        return inspect;
    }

    public void setInspect(WorkInspect inspect) {
        this.inspect = inspect;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public PageResultSet<WorkInspect> getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet<WorkInspect> pageResultSet) {
        this.pageResultSet = pageResultSet;
    }
	public String getWorkInspectId() {
		return workInspectId;
	}
	public void setWorkInspectId(String workInspectId) {
		this.workInspectId = workInspectId;
	}


    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
    }
}
