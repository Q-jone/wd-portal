package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.ProjectPlan;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/6/16.（项目计划底层实现接口）
 */
public interface ProjectPlanDao {
	
	/**
	 * 根据参数对象新增项目计划信息记录
	 * @param obj
	 * @return
	 */
    ProjectPlan save(ProjectPlan obj)throws Exception;
    
    /**
     * 根据传进来的项目计划主键的数组删除记录
     * @param ids
     * @return
     */
    int delete(String[] ids)throws Exception;
    
    /**主键查找记录信息
     * 根据项目计划信息
     * @param id
     * @return
     */
    ProjectPlan load(String id)throws Exception;
    
    /**
     * 根据项目计划信息参数对象集合批量新增或编辑记录
     * @param projectPlans
     */
    void saveOrUpdateAll(List<ProjectPlan> projectPlans)throws Exception;

    /**
     * 根据项目主键和项目计划的开始结束时间查询项目计划信息记录
     * @param projectId
     * @param begin
     * @param end
     * @return
     */
    List<ProjectPlan> find(String projectId,Date begin,Date end)throws Exception;
    
    /**
     * 根据参数对象、页码、页面大小查询项目计划信息分页
     * @param projectPlan
     * @param page
     * @param pageSize
     * @return
     * @throws Exception
     */
    PageResultSet<ProjectPlan> find(ProjectPlan projectPlan,int page,int pageSize)throws Exception;
    
    /**
     * 根据参数对象查询项目计划信息记录（不分页）
     * @param projectPlan
     * @return
     * @throws Exception
     */
    List<ProjectPlan> find(ProjectPlan projectPlan)throws Exception;
}
