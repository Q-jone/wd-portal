package com.wonders.stpt.project.service;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkProgress;

import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
public interface WorkProgressService {
    PageResultSet<WorkProgress> getWorkProgresses(WorkProgress workProgress,int page,int pageSize) throws Exception;
    WorkProgress save(WorkProgress workProgress) throws Exception;
    WorkProgress getWorkProgress(String workProgressId) throws Exception;
    List<WorkProgress> getWorkProgresses(String workSecludeId) throws Exception;
    int delete(String[] workProgressId) throws Exception;
}
