package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkProgressDao;
import com.wonders.stpt.project.dao.WorkSecludeDao;
import com.wonders.stpt.project.model.WorkProgress;
import com.wonders.stpt.project.model.WorkSeclude;
import com.wonders.stpt.project.service.WorkSecludeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
@Repository("workSecludeService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkSecludeServiceImpl implements WorkSecludeService {
    @Autowired
    private WorkSecludeDao workSecludeDao;
    @Autowired
    private WorkProgressDao workProgressDao;

    @Override
    public PageResultSet<WorkSeclude> getWorkSecludes(WorkSeclude workSeclude, int page, int pageSize) throws Exception {
        return workSecludeDao.find(workSeclude, page, pageSize);
    }

    @Override
    public WorkSeclude save(WorkSeclude workSeclude) throws Exception {
        workSeclude = workSecludeDao.save(workSeclude);
        if ("1".equals(workSeclude.getConfirm())) {
            //发布后保存推进情况的历史记录
            WorkProgress workProgress = new WorkProgress();

            BeanUtils.copyProperties(workSeclude, workProgress, new String[]{"createTime", "removed", "updateTime"});
            //workProgressService.save(workProgress);

            workProgress.setWorkSecludeId(workSeclude.getWorkSecludeId());
            workProgressDao.save(workProgress);
        }
        return workSeclude;
    }

    @Override
    public WorkSeclude getWorkSeclude(String workSecludeId) throws Exception {
        return workSecludeDao.load(workSecludeId);
    }

    @Override
    public int delete(String[] workSecludeId) throws Exception {
        return workSecludeDao.delete(workSecludeId);
    }

    @Override
    public String countDataToJson(Integer year) throws Exception {
        WorkSeclude workSeclude = new WorkSeclude();
        workSeclude.setConfirm("1");
        workSeclude.setYear(year);

        JSONObject jsonObject = new JSONObject();
        PageResultSet<WorkSeclude> workSecludePageResultSet = getWorkSecludes(workSeclude, 1, Integer.MAX_VALUE);
        ArrayList<WorkSeclude> exceptionObjs = new ArrayList<WorkSeclude>();
        int normal = 0, exception = 0;

        if (workSecludePageResultSet != null) {
            for (WorkSeclude seclude : workSecludePageResultSet.getList()) {
                if (StringUtils.isBlank(seclude.getProgress())) {
                    if (exceptionObjs.size() < 4)
                        exceptionObjs.add(seclude);
                    exception++;
                } else {
                    normal++;
                }
            }
            jsonObject.put("total", workSecludePageResultSet.getList().size());
            jsonObject.put("normal", normal);
            jsonObject.put("exception", exception);
        } else {
            jsonObject.put("total", 0);
            jsonObject.put("normal", 0);
            jsonObject.put("exception", 0);
        }
        jsonObject.put("exceptionObjs", JSONArray.fromObject(exceptionObjs));


        return jsonObject.toString();
    }
}
