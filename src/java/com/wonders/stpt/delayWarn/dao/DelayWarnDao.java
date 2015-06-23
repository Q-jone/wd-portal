package com.wonders.stpt.delayWarn.dao;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.delayWarn.model.bo.DelayProcess;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DelayWarnDao {
	public void save(Object obj);
	public void saveOrUpdateAll(Collection cols);
	public Object load(String id,Class entityClass);
	public List<Object[]> findBySql(String sql,List<Object> src);
	public List<Map<String,Object>> findByPage(int first,int size,String sql,List<Object> src);
	public int findPageSize(String sql,List<Object> src);

    public List<Object[]> countDelayDays(String department,String status,String beginDate,String endDate);
    public List<Object[]> countDepartmentNums(String delayInfo,String status,String beginDate,String endDate);
    public int findUserByLoginName(String loginName);
    public List<String> findDeptName(List deptIdList);
    public String findTaskId(String processname,int incident );
    public String findContractId(String processname,int incident );
    public PageResultSet<DelayProcess> find(String processname, String summary, String apply, List deptcodes, String status,
                               Integer minWarn, Integer maxWarn, String beginDate, String endDate, String delayType,int page,int pageSize);
    public HashMap<String,Integer> countApplyDelayNums(String beginDate,String endDate);
    public HashMap<String, String> findDepts();
    public List findDepts(String deptcode);
}
