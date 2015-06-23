package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkSeclude;

import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
public interface WorkSecludeDao {
    WorkSeclude save(WorkSeclude obj) throws Exception;
    int delete(String[] id) throws Exception;
    WorkSeclude load(String id) throws Exception;

    PageResultSet<WorkSeclude> find(WorkSeclude workSeclude,int page,int pageSize) throws Exception;

}
