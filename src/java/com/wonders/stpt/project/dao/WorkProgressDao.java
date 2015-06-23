package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkProgress;

import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
public interface WorkProgressDao {
    WorkProgress save(WorkProgress obj) throws Exception;
    int delete(String[] id) throws Exception;
    WorkProgress load(String id) throws Exception;

    PageResultSet<WorkProgress> find(WorkProgress workProgress,int page,int pageSize) throws Exception;
    List<WorkProgress> findBySecludeId(String workSecludeId);
}
