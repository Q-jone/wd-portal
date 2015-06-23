package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.Project;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2014/6/16.
 */
public interface ProjectDao {
    Project save(Project obj) throws Exception;
    int delete(String[] id) throws Exception;
    Project load(String id) throws Exception;

    PageResultSet<Project> find(Project project,int page,int pageSize) throws Exception;
    List<Project> find(Date beginDate, Date endDate) throws Exception;
    /**
     * 将项目标记为已完成
     * @param id
     * @return
     * @throws Exception
     */
    int finished(String id)throws Exception;
    /**
     * 单个删除
     * @param id
     * @return
     * @throws Exception
     */
    int delete(String id)throws Exception;
}
