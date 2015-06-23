package com.wonders.stpt.delayWarn.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.wonders.stpt.core.login.entity.vo.TaskUserVo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.delayWarn.model.bo.DelayItem;
import com.wonders.stpt.delayWarn.model.bo.DelayItemReportVo;
import com.wonders.stpt.delayWarn.model.bo.DelayProcess;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.delayWarn.dao.DelayWarnDao;
import com.wonders.stpt.delayWarn.service.DelayWarnService;

import javax.annotation.PostConstruct;

@Repository("delayWarnService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class DelayWarnServiceImpl implements DelayWarnService {
    @Autowired
    private DelayWarnDao delayWarnDao;

    public void save(Object obj) {
        delayWarnDao.save(obj);
    }

    public void saveOrUpdateAll(Collection cols) {
        delayWarnDao.saveOrUpdateAll(cols);
    }

    public Object load(String id, Class entityClass) {
        return delayWarnDao.load(id, entityClass);
    }

    public List<Object[]> findBySql(String sql, List<Object> src) {
        return delayWarnDao.findBySql(sql, src);
    }
    public static HashMap<String,String> depts;


    @PostConstruct
    public void initDeptment(){
        depts = delayWarnDao.findDepts();
    }


    @Override
    public PageResultSet<DelayProcess> seach(DelayProcess delayProcess, int page, int pageSize) {
        List deptcodes = null;
        if(StringUtils.isNotBlank(delayProcess.getDepartment()))
            deptcodes =delayWarnDao.findDepts(delayProcess.getDepartment());
        PageResultSet<DelayProcess> pageResultSet = delayWarnDao.find(delayProcess.getProcessName(), delayProcess.getSummary(), delayProcess.getApply(),
                deptcodes, delayProcess.getStatus(), delayProcess.getMinWarn(), delayProcess.getMaxWarn(),
                delayProcess.getBeginDate(), delayProcess.getEndDate(), delayProcess.getDelayType(), page, pageSize);

        for (DelayProcess process : pageResultSet.getList()) {
            process.setDepartment(depts.get(process.getDeptcode()+"_"+process.getWorkflowType()));
        }

        return pageResultSet;

    }

    @Override
    public int isAuthority(String loginName) {
        return delayWarnDao.findUserByLoginName(loginName);
    }

    @Override
    public String getDeptNames(String loginName, Map<String, TaskUserVo> userMap) {
        HashSet<String> deptList = new HashSet<String>();
        List<String> newDeptIdList = new ArrayList<String>();
        String deptNames = "";
        for (Map.Entry<String, TaskUserVo> entry : userMap.entrySet()) {
            deptList.add(entry.getValue().getDeptName());
            newDeptIdList.add(entry.getValue().getDeptId());
        }
        List<String> deptNameList = delayWarnDao.findDeptName(newDeptIdList);
        for (String s : deptNameList) {
            deptList.add(s);
        }
        for (String s : deptList) {
            deptNames = deptNames + s + ",";
        }

        return StringUtils.substringBeforeLast(deptNames, ",");
    }

    @Override
    public List getDelayDaysData(String department, String begin, String end, String status) {
        HashMap<String, Integer> map = null;
        if (StringUtils.isNotBlank(department))
            map = delayWarnDao.countApplyDelayNums(begin, end);
        return toDelayItemReportVoList(delayWarnDao.countDelayDays(department, status, begin, end), map);
    }

    @Override
    public String getDelayProcessTaskId(String processname, int incident, String type) {
        if ("contract".equals(type)) {
            return delayWarnDao.findContractId(processname, incident);
        } else
            return delayWarnDao.findTaskId(processname, incident);
    }

    @Override
    public List getDelayDepartmentsData(String delayInfo, String begin, String end, String status) {
        HashMap<String, Integer> map = delayWarnDao.countApplyDelayNums(begin, end);
        return toDelayItemReportVoList(delayWarnDao.countDepartmentNums(delayInfo, status, begin, end), map);
    }

//    @Override
//    public List<DelayItemReportVo> getDelayDetailData(String type,String delayType,String begin, String end, String status) {
//        List<DelayItemReportVo> list = null;
//        HashMap<String,Integer> map =  delayWarnDao.countApplyDelayNums(begin, end);
//        if ("dept".equals(type)){
//            list = toDelayItemReportVoList(delayWarnDao.countDepartmentNumsDetail(department,status,begin,end),map);
//        }else if ("day".equals(type)){
//            list = toDelayItemReportVoList(delayWarnDao.countDelayDaysDetail(status,begin,end,delayType),map);
//        }
//        return list;
//    }

    private List<DelayItemReportVo> toDelayItemReportVoList(List<Object[]> list, HashMap<String, Integer> map) {

        ArrayList<DelayItemReportVo> data = new ArrayList<DelayItemReportVo>();
        for (Object[] objs : list) {
            DelayItemReportVo vo = new DelayItemReportVo();
            vo.setItemName((String) objs[0]);
            vo.setItemQuantity(((BigDecimal) objs[1]).intValue());
            if (map != null) {
                Integer apply = map.get(vo.getItemName());
                vo.setApplayQuantity(apply == null ? 0 : apply);
            }
            vo.setPercent(((BigDecimal) objs[2]).doubleValue());
            data.add(vo);
        }
        return data;
    }

    public List<Map<String, Object>> findByPage(int first, int size, String sql, List<Object> src) {
        return delayWarnDao.findByPage(first, size, sql, src);
    }

    public int findPageSize(String sql, List<Object> src) {
        return delayWarnDao.findPageSize(sql, src);
    }
}
