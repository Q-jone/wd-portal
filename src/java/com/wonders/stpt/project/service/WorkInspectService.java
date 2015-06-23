package com.wonders.stpt.project.service;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.model.WorkInspect;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2014/6/26.
 */
public interface WorkInspectService {

    List<String> imports(File file,String user) throws Exception;
    WorkInspect save(WorkInspect workInspect) throws Exception;
    WorkInspect getInspect(String workInspectId)throws Exception;
    PageResultSet<WorkInspect> getWorkInspects(WorkInspect workInspect,Integer year,int page,int pageSize) throws Exception;
    int delete(String id)throws Exception;

    String countDataToJson(Integer year);

    List<WorkInspect> getDepartmentData(Integer year);
}
