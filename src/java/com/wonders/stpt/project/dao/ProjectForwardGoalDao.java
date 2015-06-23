package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.ProjectForwardGoal;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/6/16.
 */
public interface ProjectForwardGoalDao {
    ProjectForwardGoal save(ProjectForwardGoal obj);
    int delete(String id);
    ProjectForwardGoal load(String id);

    List<ProjectForwardGoal> find(String projectId,Date begin,Date end);
    /**
     * 分页查询年度推进计划目标
     * @param projectId
     * @return
     * @throws Exception
     */
    PageResultSet<ProjectForwardGoal> projectForwardGoals(String projectId,int page,int pageSize)throws Exception;
    /**
     * 批量保存年度推进计划目标
     * @param projectFrowardGoals
     * @throws Exception
     */
    void saves(List<ProjectForwardGoal> projectFrowardGoals)throws Exception;
}
