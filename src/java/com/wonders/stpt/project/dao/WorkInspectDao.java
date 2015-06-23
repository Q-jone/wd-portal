package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkInspect;

import java.util.List;

/**
 * Created by Administrator on 2014/6/26.
 */
public interface WorkInspectDao {
    void save(List<WorkInspect> workInspects) throws Exception;
    PageResultSet<WorkInspect> find(WorkInspect workInspect,int page,int pageSize) throws Exception;
    WorkInspect load(String workInspectId);
    WorkInspect save(WorkInspect workInspect)throws Exception;
    int delete(String id)throws Exception;

    int deleteAll();

    List count(Integer year);
    List<WorkInspect> findByPlanEndDate(Integer year,int page,int pageSize);

    List<WorkInspect> findByInspectDate(Integer year, int page, int pageSize);List<WorkInspect> countByInspectDate(Integer year);
}
