package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkInspectDao;
import com.wonders.stpt.project.model.WorkInspect;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2014/6/26.
 */
@Repository("workInspectDao")
public class WorkInspectDaoImpl implements WorkInspectDao {

    private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(WorkSecurityDaoImpl.class);

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    private Session getSession() {
        return hibernateTemplate.getSessionFactory().getCurrentSession();
    }

    @Override
    public void save(List<WorkInspect> workInspects) throws Exception {
        for (int i = 0; i < workInspects.size(); i++) {
            getSession().saveOrUpdate(workInspects.get(i));

            if (i % 50 == 0) // 以每50个数据作为一个处理单元，也就是我上面说的“一定的量”，这个量是要酌情考虑的

            {
                getSession().flush();
                getSession().clear();
            }
        }
    }

    @Override
    public PageResultSet<WorkInspect> find(WorkInspect workInspect, int page, int pageSize) throws Exception {
        Criteria c = getSession().createCriteria(WorkInspect.class);
        c.add(Example.create(workInspect).enableLike(MatchMode.START).excludeNone().excludeZeroes().excludeProperty("createTime")
                .excludeProperty("updateTime").excludeProperty("planBeginDate").excludeProperty("planEndDate"));

        if (workInspect.getPlanBeginDate() != null) {
            c.add(Restrictions.ge("planBeginDate", workInspect.getPlanBeginDate()));
        }
        if (workInspect.getPlanEndDate() != null) {
            c.add(Restrictions.le("planEndDate", workInspect.getPlanEndDate()));
        }

        if (workInspect.getBeginInspectDate() != null) {
            c.add(Restrictions.ge("inspectDate", workInspect.getBeginInspectDate()));
        }
        if (workInspect.getEndInspectDate() != null) {
            c.add(Restrictions.le("inspectDate", workInspect.getEndInspectDate()));
        }

        c.setProjection(Projections.count("workInspectId"));
        Integer i = (Integer) c.uniqueResult();
        PageInfo pageInfo = new PageInfo(i, pageSize, page);
        c.setProjection(null);
        c.setFirstResult(pageInfo.getBeginIndex());
        c.setMaxResults(pageSize);
        c.addOrder(Order.asc("createTime"));
        PageResultSet<WorkInspect> pageResultSet = new PageResultSet<WorkInspect>();
        pageResultSet.setList(c.list());
        pageResultSet.setPageInfo(pageInfo);

        return pageResultSet;
    }

    @Override
    public WorkInspect load(String workInspectId) {
        // TODO Auto-generated method stub
        return (WorkInspect) hibernateTemplate.get(WorkInspect.class, new String(workInspectId));
    }

    @Override
    public WorkInspect save(WorkInspect workInspect) throws Exception {
        // TODO Auto-generated method stub
        hibernateTemplate.saveOrUpdate(workInspect);
        return workInspect;
    }

    @Override
    public int deleteAll() {
        return getSession().createSQLQuery("delete from T_WORK_INSPECT").executeUpdate();
    }

    @Override
    public List count(Integer year) {
        HashMap param = new HashMap();
        Calendar month = new GregorianCalendar();
        param.put("removed", "0");
        param.put("workable", "已整改");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        param.put("currMonthBegin", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(1) FROM T_WORK_INSPECT WHERE REMOVED =:removed AND PLAN_BEGIN_DATE >= :beginDate  AND PLAN_END_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_WORK_INSPECT WHERE REMOVED =:removed AND PLAN_BEGIN_DATE >= :beginDate AND WORKABLE = :workable AND PLAN_END_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_WORK_INSPECT WHERE REMOVED =:removed AND PLAN_BEGIN_DATE >= :beginDate AND INSPECT_DATE >= :currMonthBegin AND PLAN_END_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_WORK_INSPECT WHERE REMOVED =:removed AND PLAN_BEGIN_DATE >= :beginDate AND WORKABLE <> :workable AND PLAN_END_DATE < SYSDATE AND PLAN_END_DATE <= :endDate");
        return getSession().createSQLQuery(sql.toString()).setProperties(param).list();
    }

    @Override
    public List<WorkInspect> findByPlanEndDate(Integer year,int page,int pageSize) {
        PageInfo pageInfo = new PageInfo(10,pageSize,page);
        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("workable", "已整改");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        String sql = "SELECT WORK_INSPECT_ID,DEPARTMENT,SYS_NAME,DEGREE FROM T_WORK_INSPECT WHERE REMOVED =:removed AND PLAN_BEGIN_DATE >= :beginDate AND WORKABLE <> :workable AND PLAN_END_DATE < SYSDATE AND PLAN_END_DATE <= :endDate  order by decode(degree,'高',1,'中',2,'低',3) asc";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();

        ArrayList<WorkInspect> workInspects = new ArrayList<WorkInspect>();
        if (result != null) {

            for (Object[] objects : result) {
                WorkInspect workInspect = new WorkInspect();
                workInspect.setWorkInspectId((String) objects[0]);
                workInspect.setDepartment((String) objects[1]);
                workInspect.setSysName((String) objects[2]);
                workInspect.setDegree((String) objects[3]);
                workInspects.add(workInspect);
            }

        }
        return workInspects;
    }

    @Override
    public List<WorkInspect> findByInspectDate(Integer year, int page, int pageSize) {
        PageInfo pageInfo = new PageInfo(10,pageSize,page);
        HashMap param = new HashMap();
        Calendar month = new GregorianCalendar();
        param.put("removed", "0");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        param.put("currMonthBegin", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
        String sql = "SELECT WORK_INSPECT_ID,DEPARTMENT,SYS_NAME,DEGREE FROM T_WORK_INSPECT WHERE REMOVED =:removed AND PLAN_BEGIN_DATE >= :beginDate AND INSPECT_DATE >= :currMonthBegin AND PLAN_END_DATE <= :endDate";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();

        ArrayList<WorkInspect> workInspects = new ArrayList<WorkInspect>();
        if (result != null) {

            for (Object[] objects : result) {
                WorkInspect workInspect = new WorkInspect();
                workInspect.setWorkInspectId((String) objects[0]);
                workInspect.setDepartment((String) objects[1]);
                workInspect.setSysName((String) objects[2]);
                workInspect.setDegree((String) objects[3]);
                workInspects.add(workInspect);
            }

        }
        return workInspects;
    }

    @Override
    public List<WorkInspect> countByInspectDate(Integer year) {
        HashMap param = new HashMap();
        Calendar month = new GregorianCalendar();
        param.put("removed", "0");
        param.put("high", "高");
        param.put("currMonthBegin", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
        String sql = "SELECT DEPARTMENT,count(DEPARTMENT) FROM T_WORK_INSPECT WHERE REMOVED =:removed  AND INSPECT_DATE >= :currMonthBegin  group by DEPARTMENT";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).list();
        String sql2 = "SELECT DEPARTMENT,count(DEPARTMENT) FROM T_WORK_INSPECT WHERE REMOVED =:removed  AND INSPECT_DATE >= :currMonthBegin and degree=:high  group by DEPARTMENT";
        List<Object[]> result2 = getSession().createSQLQuery(sql2.toString()).setProperties(param).list();

        ArrayList<WorkInspect> workInspects = new ArrayList<WorkInspect>();
        if (result != null) {

            for (Object[] objects : result) {
                WorkInspect workInspect = new WorkInspect();
                workInspect.setDepartment((String) objects[0]);
                workInspect.setCreator(((BigDecimal) objects[1]).intValue()+"");
                for (Object[] objects2 : result2) {
                    workInspect.setTractMemo(((BigDecimal) objects2[1]).intValue()+"");
                }

                workInspects.add(workInspect);
            }

        }
        return workInspects;
    }

    @Override
    public int delete(String id) throws Exception {
        // TODO Auto-generated method stub
        return getSession().createQuery("update WorkInspect p set p.removed=:removed where p.workInspectId=:workInspectId").setString("removed", "1").setString("workInspectId", id).executeUpdate();
    }
}
