package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkProgressDao;
import com.wonders.stpt.project.model.WorkProgress;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2014/6/23.
 */
@Repository("workProgressDao")
public class WorkProgressDaoImpl implements WorkProgressDao {

    private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(WorkSecludeDaoImpl.class);
    public HibernateTemplate getHibernateTemplate(){
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession(){
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }
    @Override
    public WorkProgress save(WorkProgress obj) throws Exception {
        getSession().saveOrUpdate(obj);
        return obj;
    }

    @Override
    public int delete(String[] id) throws Exception {
        return getSession().createQuery("update WorkProgress p set p.removed=:removed where p.workProgressId in (:workProgressId)").setString("removed", "1").setParameterList("workProgressId", id).executeUpdate();
    }

    @Override
    public WorkProgress load(String id) throws Exception {
        return (WorkProgress)getSession().get(WorkProgress.class,new String(id));
    }

    @Override
    public PageResultSet<WorkProgress> find(WorkProgress workProgress, int page, int pageSize) throws Exception {
        Criteria c = getSession().createCriteria(WorkProgress.class);
        c.add(Example.create(workProgress).enableLike(MatchMode.START).excludeNone().excludeZeroes().excludeProperty("createTime").excludeProperty("updateTime"));
        c.setProjection(Projections.count("workProgressId"));
        c.addOrder(Order.asc("createTime"));
        Integer i  = (Integer)c.uniqueResult();
        PageInfo pageInfo = new PageInfo(i,pageSize,page);
        c.setProjection(null);
        c.setFirstResult(pageInfo.getBeginIndex());
        c.setMaxResults(pageSize);

        PageResultSet<WorkProgress> pageResultSet = new PageResultSet<WorkProgress>();
        pageResultSet.setList(c.list());
        pageResultSet.setPageInfo(pageInfo);

        return pageResultSet;
    }

    @Override
    public List<WorkProgress> findBySecludeId(String workSecludeId) {
        return getSession().createQuery("from WorkProgress p where p.workSecludeId = :workSecludeId and p.removed=:removed").setString("removed","0").setString("workSecludeId",workSecludeId).list();
    }
}
