package com.wonders.stpt.project.service;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkSecurity;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2014/6/25.
 */
public interface WorkSecurityService {
    PageResultSet<WorkSecurity> getWorkSecurities(WorkSecurity workSecurity,Integer year,int page,int pageSize) throws Exception;
    WorkSecurity save(WorkSecurity workSecurity) throws Exception;
    WorkSecurity getWorkSecurity(String workSecurityId) throws Exception;
    int delete(String[] workSecurityId) throws Exception;
    List<String>  imports(File file,String user) throws Exception;

    String countDataToJson(Integer year) throws Exception;

    List<WorkSecurity> getNotBegin(Integer year);
    List<WorkSecurity> getNotEnd(Integer year);
}
