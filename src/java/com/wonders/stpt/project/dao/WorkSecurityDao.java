package com.wonders.stpt.project.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkSecurity;

import java.util.List;

/**
 * Created by Administrator on 2014/6/25.
 */
public interface WorkSecurityDao {
    WorkSecurity save(WorkSecurity workSecurity) throws Exception;
    void save(List<WorkSecurity> workSecurities) throws Exception;
    int delete(String[] id) throws Exception;
    int deleteAll() throws Exception;
    WorkSecurity load(String id) throws Exception;

    PageResultSet<WorkSecurity> find(WorkSecurity workSecurity,int page,int pageSize) throws Exception;

    List count(Integer year);
    List<WorkSecurity> findByRealBeginDate(Integer year,int page,int pageSize);
    List<WorkSecurity> findByRealEndDate(Integer year,int page,int pageSize);
}
