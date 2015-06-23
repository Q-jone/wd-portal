package com.wonders.stpt.project.service;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkSeclude;

import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
public interface WorkSecludeService {

    PageResultSet<WorkSeclude> getWorkSecludes(WorkSeclude workSeclude,int page,int pageSize) throws Exception;
    WorkSeclude save(WorkSeclude workSeclude) throws Exception;
    WorkSeclude getWorkSeclude(String workSecludeId) throws Exception;
    int delete(String[] workSecludeId) throws Exception;

    String countDataToJson(Integer year) throws Exception;
}
