package com.wonders.stpt.project.action;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkSecurity;
import com.wonders.stpt.project.service.WorkSecurityService;
import com.wondersgroup.framework.security.bo.SecurityUser;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.*;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/25.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/security")
@Controller("workSecurityAction")
@Scope("prototype")
public class WorkSecurityAction extends ActionSupport implements SessionAware,ServletResponseAware{


    @Autowired
    private WorkSecurityService workSecurityService;

    private Integer page = 1;//页码
    private Integer year;
    private WorkSecurity workSecurity=new WorkSecurity();
    private Map<String, Object> session;
    private String workSecurityId;
    private PageResultSet<WorkSecurity> pageResultSet;
    private final Logger logger = Logger.getLogger(WorkSecurityAction.class);
    private HttpServletResponse httpServletResponse;

    @org.apache.struts2.convention.annotation.Action(value = "delete", results = {
            @Result(name = "success", location = "securities.action", type = "redirectAction",params={"page","${page}"})
    })
    public String delete() throws Exception {
        String[] ids = null;
        int p=page;
        if (StringUtils.isNotBlank(workSecurityId)) {
            ids = workSecurityId.split(",");
        } else {
            logger.error("删除安全工作出错,传入的参数不正确:" + workSecurityId);
            throw new Exception("删除安全工作出错,传入的参数不正确" + workSecurityId);
        }
        workSecurityService.delete(ids);
        return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "index")
    public String index() throws Exception{
        String result = workSecurityService.countDataToJson(Calendar.getInstance().get(Calendar.YEAR));
        httpServletResponse.getWriter().print(result);
        return Action.NONE;
    }


    @org.apache.struts2.convention.annotation.Action(value = "security", results = {
            @Result(name = "success", location = "/project/security/security_add.jsp")
    })
    public String security() throws Exception {
        if (StringUtils.isNotBlank(workSecurityId))
            workSecurity = workSecurityService.getWorkSecurity(workSecurityId);
        return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "confirm", results = {
            @Result(name = "success", location = "securities.action",type="redirectAction")
    })
    public String confirm() throws Exception {
    	 WorkSecurity persistentWorkSecurity;
         String loginName = (String) session.get("loginName");
         SecurityUser user = (SecurityUser) session.get("user");
         if (StringUtils.isNotBlank(workSecurityId)) {//编辑
             persistentWorkSecurity = workSecurityService.getWorkSecurity(workSecurityId);
             workSecurity.setWorkSecurityId(workSecurityId);
             workSecurity.setUpdater(loginName);
             workSecurity.setUpdateTime(new Date());
             BeanUtils.copyProperties(workSecurity, persistentWorkSecurity, new String[]{"createTime", "creator", "removed"});

         } else {//新增
         	
         		workSecurity.setConfirm("1");
         	
             workSecurity.setWorkSecurityId(null);
             workSecurity.setCreator(loginName);
             workSecurity.setUpdater(loginName);
             persistentWorkSecurity = workSecurity;
         }
        workSecurityService.save(workSecurity);
        return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "report", results = {
            @Result(name = "success", location = "/project/security/report.jsp")
    })
    public String report() throws Exception {

        if (workSecurity == null) {
            workSecurity = new WorkSecurity();
        }
        workSecurity.setConfirm("1");
        pageResultSet = workSecurityService.getWorkSecurities(workSecurity, year, 1, Integer.MAX_VALUE);
        return Action.SUCCESS;
    }

        @org.apache.struts2.convention.annotation.Action(value = "securities", results = {
                @Result(name = "success", location = "/project/security/work_security_list.jsp")
        })
        public String securities ()throws Exception {
            if (workSecurity == null)
                workSecurity = new WorkSecurity();

            workSecurity.setConfirm(null);
            pageResultSet=workSecurityService.getWorkSecurities(workSecurity, year, page, 10);
            return Action.SUCCESS;
        }

        @org.apache.struts2.convention.annotation.Action(value = "save", results = {
                @Result(name = "success", location = "securities.action", type = "redirectAction"), @Result(name = "input", location = "/project/security/work_security.jsp")
        })
        public String save ()throws Exception {//编辑的时候是新增一条数据？
            try {
                WorkSecurity persistentWorkSecurity;
                String loginName = (String) session.get("loginName");
                SecurityUser user = (SecurityUser) session.get("user");
                if (StringUtils.isNotBlank(workSecurityId)) {//编辑
                    persistentWorkSecurity = workSecurityService.getWorkSecurity(workSecurityId);
                    workSecurity.setWorkSecurityId(workSecurityId);
                    workSecurity.setUpdater(loginName);
                    workSecurity.setUpdateTime(new Date());
                    BeanUtils.copyProperties(workSecurity, persistentWorkSecurity, new String[]{"createTime", "creator", "removed"});

                } else {//新增
                	
                	if("".equals(workSecurity.getConfirm())){
                		workSecurity.setConfirm("0");
                	}
                    workSecurity.setWorkSecurityId(null);
                    workSecurity.setCreator(loginName);
                    workSecurity.setUpdater(loginName);
                    persistentWorkSecurity = workSecurity;
                }
                workSecurityService.save(persistentWorkSecurity);
            } catch (Exception ex) {

                logger.error("保存安全工作基本信息出错,ID:" + workSecurity.getWorkSecurityId() + "," + ex);
                throw ex;
            }

            return Action.SUCCESS;
        }
        @org.apache.struts2.convention.annotation.Action(value = "goAdd", results = {
                @Result(name = "success", location = "/project/security/security_add.jsp")
        })
        public String goAdd(){
        	
        	return Action.SUCCESS;
        }

        @Override
        public void setSession (Map < String, Object > stringObjectMap){
            session = stringObjectMap;
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

    public WorkSecurity getWorkSecurity() {
        return workSecurity;
    }

    public void setWorkSecurity(WorkSecurity workSecurity) {
        this.workSecurity = workSecurity;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public String getWorkSecurityId() {
        return workSecurityId;
    }

    public void setWorkSecurityId(String workSecurityId) {
        this.workSecurityId = workSecurityId;
    }

    public PageResultSet<WorkSecurity> getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet<WorkSecurity> pageResultSet) {
        this.pageResultSet = pageResultSet;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
    }
}
