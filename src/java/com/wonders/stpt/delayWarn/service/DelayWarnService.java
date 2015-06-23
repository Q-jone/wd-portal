package com.wonders.stpt.delayWarn.service;

import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.delayWarn.model.bo.DelayItemReportVo;
import com.wonders.stpt.delayWarn.model.bo.DelayProcess;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DelayWarnService {
	public void save(Object obj);
	public void saveOrUpdateAll(Collection cols);
	public Object load(String id,Class entityClass);
	public List<Object[]> findBySql(String sql,List<Object> src);
	public List<Map<String,Object>> findByPage(int first,int size,String sql,List<Object> src);
	public int findPageSize(String sql,List<Object> src);

    public PageResultSet<DelayProcess> seach(DelayProcess delayProcess,int page,int pageSize);
    public int isAuthority(String loginName);
    public String getDeptNames(String loginName,Map<String, TaskUserVo> userMap);
    public List getDelayDaysData(String department,String begin,String end,String status);
    public List getDelayDepartmentsData(String delayInfo,String begin,String end,String status);
    public String getDelayProcessTaskId(String processname,int incident,String type);
//    public List<DelayItemReportVo> getDelayDetailData(String type,String delayType,String begin, String end, String status);
}
