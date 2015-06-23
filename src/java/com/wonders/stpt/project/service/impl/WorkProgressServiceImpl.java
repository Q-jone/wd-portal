package com.wonders.stpt.project.service.impl;

import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkProgressDao;
import com.wonders.stpt.project.model.WorkProgress;
import com.wonders.stpt.project.service.WorkProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
@Repository("workProgressService")
@Transactional(value = "txManager2", propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class WorkProgressServiceImpl implements WorkProgressService {
    @Autowired
    private WorkProgressDao workProgressDao;

    @Override
    public PageResultSet<WorkProgress> getWorkProgresses(WorkProgress workProgress, int page, int pageSize) throws Exception {
        return workProgressDao.find(workProgress,page,pageSize);
    }

    @Override
    public List<WorkProgress> getWorkProgresses(String workSecludeId) throws Exception {
        return workProgressDao.findBySecludeId(workSecludeId);
    }

    @Override
    public WorkProgress save(WorkProgress workProgress) throws Exception {
        return workProgressDao.save(workProgress);
    }

    @Override
    public WorkProgress getWorkProgress(String workProgressId) throws Exception {
        return workProgressDao.load(workProgressId);
    }

    @Override
    public int delete(String[] workProgressId) throws Exception {
        return workProgressDao.delete(workProgressId);
    }
}
