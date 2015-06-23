package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkSecludeDao;
import com.wonders.stpt.project.model.WorkSeclude;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2014/6/23.
 */
@Repository("workSecludeDao")
public class WorkSecludeDaoImpl implements WorkSecludeDao {

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
    public WorkSeclude save(WorkSeclude workSeclude) throws Exception {
        getSession().saveOrUpdate(workSeclude);
        return workSeclude;
    }

    @Override
    public int delete(String[] id) throws Exception {
        return getSession().createQuery("update WorkSeclude p set p.removed=:removed where p.workSecludeId in (:workSecludeId)").setString("removed", "1").setParameterList("workSecludeId", id).executeUpdate();
    }

    @Override
    public WorkSeclude load(String id) throws Exception {
        return (WorkSeclude)getSession().get(WorkSeclude.class,new String(id));
    }


    @Override
    public PageResultSet<WorkSeclude> find(WorkSeclude workSeclude, int page, int pageSize) throws Exception {
        Criteria c = getSession().createCriteria(WorkSeclude.class);
        //模糊查找匹配在字符串最前端的workSeclude实例参数但拍出0、null和属性值为createTime和updateTime的记录
        c.add(Example.create(workSeclude).enableLike(MatchMode.START).excludeNone().excludeZeroes().excludeProperty("createTime").excludeProperty("updateTime"));
        c.setProjection(Projections.count("workSecludeId"));//根据Id值组投影
        Integer i  = (Integer)c.uniqueResult();//获取记录总数
        PageInfo pageInfo = new PageInfo(i,pageSize,page);
        c.setProjection(null);
        c.setFirstResult(pageInfo.getBeginIndex());//设置当前页的第一条索引
        c.setMaxResults(pageSize);//设置每页显示的记录数


        PageResultSet<WorkSeclude> pageResultSet = new PageResultSet<WorkSeclude>();
        pageResultSet.setList(c.list());
        pageResultSet.setPageInfo(pageInfo);
        logger.debug(pageResultSet.getPageInfo().getPageSize());
        return pageResultSet;
    }
}
