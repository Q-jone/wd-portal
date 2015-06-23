package com.wonders.stpt.project.action;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.*;
import com.wonders.stpt.project.service.*;
import com.wonders.stpt.project.service.impl.RiskManageServiceImpl;
import com.wonders.stpt.util.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/7/29.
 */
@ParentPackage("struts-default")
@Namespace(value = "/project/index")
@Controller("sysIndexAction")
@Scope("prototype")
public class IndexAction implements ServletResponseAware {
    @Autowired
    private RiskManageService riskManageService;
    @Autowired
    private WorkInspectService workInspectService;
    @Autowired
    private WorkSecurityService workSecurityService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private IWorkEventService workEventService;
    private HttpServletResponse httpServletResponse;

    @org.apache.struts2.convention.annotation.Action(value = "marquee")
    public void index() throws Exception{
        List<RiskManage> riskList =riskManageService.getDepartmentData(Calendar.getInstance().get(Calendar.YEAR));
        List<WorkInspect> inspectList =workInspectService.getDepartmentData(Calendar.getInstance().get(Calendar.YEAR));
        List<WorkSecurity> workSecuritiesNotBegin =workSecurityService.getNotBegin(Calendar.getInstance().get(Calendar.YEAR));
        List<WorkSecurity> workSecuritiesNotEnd =workSecurityService.getNotEnd(Calendar.getInstance().get(Calendar.YEAR));
        List<Project> projectList =projectService. getProjectPlanReport(Calendar.getInstance().get(Calendar.YEAR));
        ArrayList<Project> planExceptionObjs = new ArrayList<Project>();
        ArrayList<Project> periodExceptionObjs = new ArrayList<Project>();
        List eventList=workEventService.monthTotal(Calendar.getInstance().get(Calendar.YEAR));
        for (Project project : projectList) {
            if ("1".equals(project.getCurrentPlan().getPlanStatus())) {
                Date endDate = new Date();
                if(project.getCurrentPlan().getRealEndDate()!= null)
                    endDate = project.getCurrentPlan().getRealEndDate();
                project.setFinish((endDate.getTime() - project.getCurrentPlan().getPlanEndDate().getTime())/(1000*60*60*24)+"");
                project.setCreator(project.getCurrentPlan().getSubPlanName());
                planExceptionObjs.add(project);
            } else if ("2".equals(project.getCurrentPlan().getPlanStatus())) {
                Date endDate = new Date();
                if(project.getCurrentPlan().getRealEndDate()!= null)
                    endDate = project.getCurrentPlan().getRealEndDate();
                project.setFinish((endDate.getTime() - project.getCurrentPlan().getPlanEndDate().getTime())/(1000*60*60*24)+"");
                if("1".equals(project.getCurrentPlan().getPlanName()))
                project.setCreator("前期策划");if("2".equals(project.getCurrentPlan().getPlanName()))
                    project.setCreator("立项采购");if("3".equals(project.getCurrentPlan().getPlanName()))
                    project.setCreator("实施推进");
                periodExceptionObjs.add(project);
            }
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("riskList", JSONArray.fromObject(riskList));
        jsonObject.put("inspectList", JSONArray.fromObject(inspectList));
        jsonObject.put("eventList", JSONArray.fromObject(eventList));
        JsonConfig config  = new JsonConfig();
        config.setExcludes(new String[]{"createTime","updateTime","delay"});
        jsonObject.put("notBegin", JSONArray.fromObject(workSecuritiesNotBegin,config));
        jsonObject.put("notEnd", JSONArray.fromObject(workSecuritiesNotEnd,config));

        JsonConfig config2 = new JsonConfig();
        config2.setExcludes(new String[]{"createTime", "updateTime", "currentPlan", "projectPlans", "projectForwardGoals", "projectGoal", "projectDiscription", "projectStatus"});

        jsonObject.put("planEx", JSONArray.fromObject(planExceptionObjs,config2));
        jsonObject.put("periodEx", JSONArray.fromObject(periodExceptionObjs,config2));
        httpServletResponse.getWriter().print(jsonObject.toString());
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.httpServletResponse = httpServletResponse;
        httpServletResponse.setContentType("text/html;charset=utf-8");
    }
}
