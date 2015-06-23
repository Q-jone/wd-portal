package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.ProjectDao;
import com.wonders.stpt.project.dao.ProjectPlanDao;
import com.wonders.stpt.project.model.Project;
import com.wonders.stpt.project.model.ProjectPlan;
import com.wonders.stpt.project.service.ProjectService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2014/6/16.
 */
@Repository("projectService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ProjectServiceImpl implements ProjectService {
    private final Logger logger = Logger.getLogger(ProjectServiceImpl.class);

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectPlanDao projectPlanDao;

    @Override
    public Project getProject(String projectId) throws Exception {
        Project project = null;
        if (StringUtils.isBlank(projectId)) {
            Exception ex = new Exception("根据项目ID查询时出错：参数格式不正确," + projectId);
            logger.error(ex.getMessage());
            throw new Exception(ex);
        } else {
            try {
                project = projectDao.load(projectId);
            } catch (Exception ex) {
                logger.error("查找项目出错：ID=" + projectId);
                throw ex;
            }
        }
        return project;
    }

    @Override
    public List<ProjectPlan> getProjectPlans(String projectId, int year) throws Exception {
        Date beginDate = null, endDate = null;
        if (year > 0) {
            beginDate = (new GregorianCalendar(year, 0, 1)).getTime();
            endDate = (new GregorianCalendar(year, 12, 31)).getTime();
        }
        return projectPlanDao.find(projectId, beginDate, endDate);
    }

    @Override
    public PageResultSet<Project> getProjects(Project project, int page, int pageSize) throws Exception {
        return projectDao.find(project, page, pageSize);
    }

    @Override
    public Project save(Project project) throws Exception {

        return projectDao.save(project);
    }

    @Override
    public int deleteProject(String[] projectIds) throws Exception {
        int i = 0;
        try {

            i = projectDao.delete(projectIds);
        } catch (Exception ex) {
            logger.error("删除项目失败：" + projectIds.toString());
            throw ex;

        }
        return i;
    }

    @Override
    public void save(List<ProjectPlan> projectPlans) throws Exception {
        projectPlanDao.saveOrUpdateAll(projectPlans);
    }

    @Override
    public List<Project> getProjectPlanReport(Integer year) throws Exception {
        if (year == null) {
            year = Calendar.getInstance().get(Calendar.YEAR);
        }
        Calendar begin = new GregorianCalendar(year, 0, 1);
        Calendar end = new GregorianCalendar(year, 11, 31);
        return projectDao.find(begin.getTime(), end.getTime());
    }

    @Override
    public int finished(String id) throws Exception {
        // TODO Auto-generated method stub
        return projectDao.finished(id);
    }

    @Override
    public int delete(String id) throws Exception {
        // TODO Auto-generated method stub
        return projectDao.delete(id);
    }

    @Override
    public String countDataToJson(Integer year) throws Exception {

        List<Project> projectList = getProjectPlanReport(year);
        JSONObject jsonObject = new JSONObject();
        int normal = 0, planException = 0, periodException = 0, p1 = 0, p2 = 0, p3 = 0, completed = 0;
        ArrayList<Project> planExceptionObjs = new ArrayList<Project>();
        ArrayList<Project> periodExceptionObjs = new ArrayList<Project>();

        if (projectList != null) {
            for (Project project : projectList) {

                String planStatus = "";
                if (project.getCurrentPlan().getManual() == null) {
                    planStatus = project.getCurrentPlan().getPlanStatus();
                } else {
                    planStatus = project.getCurrentPlan().getManual();
                }


                if (project.getProjectPlans() != null) {
                    ProjectPlan last = project.getProjectPlans().get(project.getProjectPlans().size() - 1);
                    if (last.getRealEndDate() != null){
                        completed++;

                    }
                    else if ("1".equals(project.getCurrentPlan().getPlanName()))
                        p1++;
                    else if ("2".equals(project.getCurrentPlan().getPlanName()))
                        p2++;
                    else if ("3".equals(project.getCurrentPlan().getPlanName()))
                        p3++;

                    if ("1".equals(planStatus)) {
                        planExceptionObjs.add(project);
                        planException++;
                    } else if ("2".equals(planStatus)) {
                        periodExceptionObjs.add(project);
                        periodException++;
                    } else if ("0".equals(planStatus)) {
                        normal++;
                    }
                }







            }
            jsonObject.put("total", projectList.size());
            jsonObject.put("normal", normal);
            jsonObject.put("planException", planException);
            jsonObject.put("periodException", periodException);
            jsonObject.put("p1", p1);
            jsonObject.put("p2", p2);
            jsonObject.put("p3", p3);
            jsonObject.put("completed", completed);
        } else {
            jsonObject.put("total", 0);
            jsonObject.put("normal", 0);
            jsonObject.put("planException", 0);
            jsonObject.put("periodException", 0);
            jsonObject.put("p1", 0);
            jsonObject.put("p2", 0);
            jsonObject.put("p3", 0);
            jsonObject.put("completed", 0);
        }
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"createTime", "updateTime", "currentPlan", "projectPlans", "projectForwardGoals", "projectGoal", "projectDiscription", "projectStatus"});

        jsonObject.put("planExceptionObjs", JSONArray.fromObject(planExceptionObjs, config));
        jsonObject.put("periodExceptionObjs", JSONArray.fromObject(periodExceptionObjs, config));


        return jsonObject.toString();
    }
}
