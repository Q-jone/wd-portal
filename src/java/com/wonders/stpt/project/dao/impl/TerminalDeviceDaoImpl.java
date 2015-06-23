package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.TerminalDeviceDao;
import com.wonders.stpt.project.model.TerminalDevice;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2014/6/27.
 */
@Repository("terminalDeviceDao")
public class TerminalDeviceDaoImpl implements TerminalDeviceDao {

    private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(TerminalDeviceDaoImpl.class);
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
    public TerminalDevice load(String id) throws Exception {
        return (TerminalDevice)getSession().get(TerminalDevice.class,id);
    }

    @Override
    public TerminalDevice save(TerminalDevice terminalDevice) throws Exception {
        getSession().saveOrUpdate(terminalDevice);
        return terminalDevice;
    }

    @Override
    public PageResultSet<TerminalDevice> find(TerminalDevice terminalDevice, int page, int pageSize) throws Exception {
        Criteria c = getSession().createCriteria(TerminalDevice.class);
        c.add(Example.create(terminalDevice).enableLike(MatchMode.START).excludeNone().excludeZeroes());

        c.setProjection(Projections.count("terminalDeviceId"));
        Integer i  = (Integer)c.uniqueResult();
        PageInfo pageInfo = new PageInfo(i,pageSize,page);
        c.setProjection(null);
        c.setFirstResult(pageInfo.getBeginIndex());
        c.setMaxResults(pageSize);

        PageResultSet<TerminalDevice> pageResultSet = new PageResultSet<TerminalDevice>();
        pageResultSet.setList(c.list());
        pageResultSet.setPageInfo(pageInfo);

        return pageResultSet;
    }
}
