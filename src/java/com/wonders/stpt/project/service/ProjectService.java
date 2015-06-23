package com.wonders.stpt.project.service;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.Project;
import com.wonders.stpt.project.model.ProjectPlan;

import java.util.List;

/**
 * Created by Administrator on 2014/6/16.
 */
public interface ProjectService {
    Project getProject(String projectId) throws Exception;
    PageResultSet<Project> getProjects(Project project,int page,int pageSize) throws Exception;
    int deleteProject(String[] projectIds) throws Exception;
    List<ProjectPlan> getProjectPlans(String projectId,int year) throws Exception;
    Project save(Project project) throws Exception;
    void save(List<ProjectPlan> projectPlans) throws Exception;
    List<Project> getProjectPlanReport(Integer year) throws Exception;
    /**
     * 将项目标记成已完成
     * @param id
     * @return
     * @throws Exception
     */
    int finished(String id)throws Exception;
    /**
     * 单条记录删除
     * @return
     * @throws Exception
     */
    int delete(String id)throws Exception;

    String countDataToJson(Integer year) throws Exception;
}
