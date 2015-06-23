package com.wonders.stpt.project.action;

import com.opensymphony.xwork2.Action;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkSeclude;
import com.wonders.stpt.project.service.WorkSecludeService;
import com.wondersgroup.framework.security.bo.SecurityUser;
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

import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2014/6/23.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/work")
@Controller("workSecludeAction")
@Scope("prototype")
public class WorkSecludeAction implements SessionAware,ServletResponseAware {
    @Autowired
    private WorkSecludeService workSecludeService;

    private final Logger logger = Logger.getLogger(WorkSecludeAction.class);
    private PageResultSet<WorkSeclude> pageResultSet;//页面上的相关信息
    private Integer page = 1;//页码
    private Integer pageSize = 10;//每页的记录数
    private String operatorAuth;
    private WorkSeclude workSeclude=new WorkSeclude();
    private String workSecludeId;
    private Integer year;
    private Map<String, Object> session;
    private HttpServletResponse httpServletResponse;


    /**
     * 删除推进计划
     *
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "delete", results = {
            @Result(name = "success", location = "workSecludes.action", type = "redirectAction")
    })
    public String delete() throws Exception {
        String[] ids = null;
        if (StringUtils.isNotBlank(workSecludeId)) {//workSecludeId为字符串
            ids = workSecludeId.split(",");
        } else {
            throw new Exception("删除工作出错,传入的参数不正确" + workSecludeId);
        }
        workSecludeService.delete(ids);
        return Action.SUCCESS;
    }

    /**
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "goAdd", results = {
            @Result(name = "success", location = "/project/work/work_seclude.jsp")
    })
    public String goAdd() {

        return Action.SUCCESS;
    }

    /**
     * 查找一条记录
     *
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "workSeclude", results = {
            @Result(name = "success", location = "/project/work/work_seclude.jsp")
    })
    public String workSeclude() throws Exception {
        if (StringUtils.isNotBlank(workSecludeId))
            workSeclude = workSecludeService.getWorkSeclude(workSecludeId);
        return Action.SUCCESS;
    }

    /**
     * 可操作的显示界面推进计划
     *
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "workSecludes", results = {
            @Result(name = "success", location = "/project/work/work_seclude_list.jsp")
    })
    public String workSecludes() throws Exception {
        operatorAuth = "1";
        if (workSeclude == null) {
            workSeclude = new WorkSeclude();
            workSeclude.setConfirm(null);
            //后台管理，只显示自己的工作

            workSeclude.setCreator((String) session.get("loginName"));//获取创建人姓名
        }
        workSeclude.setConfirm(null);
        pageResultSet = workSecludeService.getWorkSecludes(workSeclude, page, pageSize);

        return Action.SUCCESS;
    }

    /**
     * 不可操作的推进计划显示
     *
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "report", results = {
            @Result(name = "success", location = "/project/work/report.jsp")
    })
    public String report() throws Exception {
        if(workSeclude.getYear() == null)
            workSeclude.setYear(2014);
        workSeclude.setConfirm("1");
        pageResultSet = workSecludeService.getWorkSecludes(workSeclude, page, Integer.MAX_VALUE);
        return Action.SUCCESS;
    }

    @org.apache.struts2.convention.annotation.Action(value = "index")
    public String index() throws Exception{
        String result = workSecludeService.countDataToJson(Calendar.getInstance().get(Calendar.YEAR));
        httpServletResponse.getWriter().print(result);
        return Action.NONE;
    }


    /**
     * 保存或更新推进计划
     *
     * @return
     * @throws Exception
     */
    @org.apache.struts2.convention.annotation.Action(value = "save", results = {
            @Result(name = "success", location = "workSecludes.action", type = "redirectAction"), @Result(name = "input", location = "/project/work/work_seclude.jsp")
    })
    public String save() throws Exception {//编辑的时候是新增一条数据？
        try {
            WorkSeclude persistentWorkSeclude;
            String loginName = (String) session.get("loginName");//
            SecurityUser user = (SecurityUser) session.get("user");//
            if (StringUtils.isNotBlank(workSecludeId)) {//编辑
                persistentWorkSeclude = workSecludeService.getWorkSeclude(workSecludeId);
                workSeclude.setWorkSecludeId(workSecludeId);
                workSeclude.setUpdater(loginName);
                workSeclude.setUpdateTime(new Date());
                workSeclude.setContactName(user.getName());
                BeanUtils.copyProperties(workSeclude, persistentWorkSeclude, new String[]{"createTime", "creator", "removed"});

            } else {//新增
                if (!workSeclude.getConfirm().equals("1")) {
                    workSeclude.setConfirm("0");
                }
                workSeclude.setWorkSecludeId(null);
                workSeclude.setCreator(loginName);
                workSeclude.setUpdater(loginName);
                workSeclude.setContactName(user.getName());
                persistentWorkSeclude = workSeclude;
            }

            workSecludeService.save(persistentWorkSeclude);
        } catch (Exception ex) {

            logger.error("保存工作基本信息出错,ID:" + workSeclude.getWorkSecludeId() + "," + ex);
            throw ex;
        }

        return Action.SUCCESS;
    }


    public WorkSecludeService getWorkSecludeService() {
        return workSecludeService;
    }

    public void setWorkSecludeService(WorkSecludeService workSecludeService) {
        this.workSecludeService = workSecludeService;
    }

    public PageResultSet<WorkSeclude> getPageResultSet() {
        return pageResultSet;
    }

    public void setPageResultSet(PageResultSet<WorkSeclude> pageResultSet) {
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

    public WorkSeclude getWorkSeclude() {
        return workSeclude;
    }

    public void setWorkSeclude(WorkSeclude workSeclude) {
        this.workSeclude = workSeclude;
    }

    public String getWorkSecludeId() {
        return workSecludeId;
    }

    public void setWorkSecludeId(String workSecludeId) {
        this.workSecludeId = workSecludeId;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getOperatorAuth() {
        return operatorAuth;
    }

    public void setOperatorAuth(String operatorAuth) {
        this.operatorAuth = operatorAuth;
    }

    @Override
    public void setSession(Map<String, Object> stringObjectMap) {
        session = stringObjectMap;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
    }
}
