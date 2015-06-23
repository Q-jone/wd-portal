package com.wonders.stpt.project.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.Action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.model.ProjectPlanHistory;
import com.wonders.stpt.project.service.ProjectPlanHistoryService;
import com.wonders.stpt.project.service.ProjectPlanService;
/**
 * 历史计划信息Action
 * @author mengjie
 *
 */
@ParentPackage("struts-default")
@Namespace(value="/projectPlanHistory/sysinfo")
@Controller("projectPlanHistoryAction")
@Scope("prototype")
public class ProjectPlanHistoryAction {

	private final Logger logger=Logger.getLogger(ProjectPlanHistory.class);
	private String projectPlanId;
	private List<ProjectPlanHistory> projectPlanHistorys=new ArrayList<ProjectPlanHistory>();
	private ProjectPlan projectPlan=new ProjectPlan();
	@Autowired
	private ProjectPlanHistoryService projectPlanHistoryService;
	@Autowired
	private ProjectPlanService projectPlanService;
	private Map<String, Object> session;
	public String getProjectPlanId() {
		return projectPlanId;
	}
	public void setProjectPlanId(String projectPlanId) {
		this.projectPlanId = projectPlanId;
	}
	public List<ProjectPlanHistory> getProjectPlanHistorys() {
		return projectPlanHistorys;
	}
	public void setProjectPlanHistorys(List<ProjectPlanHistory> projectPlanHistorys) {
		this.projectPlanHistorys = projectPlanHistorys;
	}
	public ProjectPlan getProjectPlan() {
		return projectPlan;
	}
	public void setProjectPlan(ProjectPlan projectPlan) {
		this.projectPlan = projectPlan;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	/**
	 * 根据项目信息主键查询历史记录
	 * @return
	 */
	@org.apache.struts2.convention.annotation.Action(value = "history", results = {
            @Result(name = "success", location = "/project/sysinfo/projectPlanHistory_list.jsp")
    })
	public String history(){
		logger.error("history"+projectPlanId);
		try {
			projectPlanHistorys = projectPlanHistoryService
					.getProjectPlanHistorys(projectPlanId);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("历史记录查询出错："+projectPlanId+"     "+e);
			e.printStackTrace();
		}
		return com.opensymphony.xwork2.Action.SUCCESS;
	}
	
}
