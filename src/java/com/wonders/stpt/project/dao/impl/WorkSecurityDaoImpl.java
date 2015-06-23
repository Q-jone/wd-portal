package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.WorkSecurityDao;
import com.wonders.stpt.project.model.WorkSecurity;
import org.apache.commons.lang.xwork.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2014/6/25.
 */
@Repository("workSecurityDao")
public class WorkSecurityDaoImpl implements WorkSecurityDao {

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
    public WorkSecurity save(WorkSecurity workSecurity) throws Exception {
        getSession().saveOrUpdate(workSecurity);
        return workSecurity;
    }

    @Override
    public void save(List<WorkSecurity> workSecurities) throws Exception {
        for (int i = 0; i < workSecurities.size(); i++) {
            getSession().saveOrUpdate(workSecurities.get(i));

            if (i % 50 == 0) // 以每50个数据作为一个处理单元，也就是我上面说的“一定的量”，这个量是要酌情考虑的

            {
                getSession().flush();
                getSession().clear();
            }
        }

    }

    @Override
    public int delete(String[] id) throws Exception {
        return getSession().createQuery("update WorkSecurity p set p.removed=:removed where p.workSecurityId in (:workSecurityId)").setString("removed", "1").setParameterList("workSecurityId", id).executeUpdate();
    }

    @Override
    public WorkSecurity load(String id) throws Exception {
        return (WorkSecurity) getSession().get(WorkSecurity.class, new String(id));
    }

    @Override
    public PageResultSet<WorkSecurity> find(WorkSecurity workSecurity, int page, int pageSize) throws Exception {
        Criteria c = getSession().createCriteria(WorkSecurity.class);
        c.add(Example.create(workSecurity).enableLike(MatchMode.START).excludeNone().excludeZeroes().excludeProperty("createTime")
                .excludeProperty("updateTime").excludeProperty("planBeginDate").excludeProperty("planEndDate").excludeProperty("confirm"));

        if (workSecurity.getPlanBeginDate() != null) {
            c.add(Restrictions.ge("planBeginDate", workSecurity.getPlanBeginDate()));
        }
        if (workSecurity.getPlanEndDate() != null) {
            c.add(Restrictions.le("planEndDate", workSecurity.getPlanEndDate()));
        }
        if (StringUtils.isNotBlank(workSecurity.getConfirm())) {
            c.add(Restrictions.eq("confirm", workSecurity.getConfirm()));
        }
        c.setProjection(Projections.count("workSecurityId"));//
        Integer i = (Integer) c.uniqueResult();
        PageInfo pageInfo = new PageInfo(i, pageSize, page);
        c.setProjection(null);
        c.setFirstResult(pageInfo.getBeginIndex());
        c.setMaxResults(pageSize);

        PageResultSet<WorkSecurity> pageResultSet = new PageResultSet<WorkSecurity>();
        pageResultSet.setList(c.list());
        pageResultSet.setPageInfo(pageInfo);

        return pageResultSet;
    }

    @Override
    public List count(Integer year) {
        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("confirm", "1");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(1) FROM T_WORK_SECURITY  WHERE REMOVED =:removed AND CONFIRM=:confirm  AND PLAN_BEGIN_DATE >= :beginDate  AND PLAN_END_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_WORK_SECURITY WHERE REMOVED =:removed AND CONFIRM=:confirm AND PLAN_BEGIN_DATE >= :beginDate AND REAL_END_DATE IS NOT NULL AND PLAN_END_DATE <= :endDate");
//        sql.append(" UNION ALL ");
//        sql.append("SELECT COUNT(1) FROM T_WORK_SECURITY WHERE REMOVED =:removed AND CONFIRM=:confirm AND PLAN_BEGIN_DATE >= :beginDate AND REAL_BEGIN_DATE > PLAN_END_DATE AND PLAN_END_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_WORK_SECURITY WHERE REMOVED =:removed AND CONFIRM=:confirm AND PLAN_BEGIN_DATE >= :beginDate AND REAL_END_DATE > PLAN_END_DATE AND PLAN_END_DATE <= :endDate");
//        sql.append(" UNION ALL ");
//  计划内已完成      sql.append("SELECT COUNT(1) FROM T_WORK_SECURITY WHERE REMOVED =:removed AND CONFIRM=:confirm AND PLAN_BEGIN_DATE >= :beginDate AND PLAN_BEGIN_DATE <= REAL_BEGIN_DATE AND PLAN_END_DATE >= REAL_END_DATE AND PLAN_END_DATE <= :endDate");
        return getSession().createSQLQuery(sql.toString()).setProperties(param).list();
    }

    @Override
    public List<WorkSecurity> findByRealBeginDate(Integer year, int page, int pageSize) {
        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("confirm", "1");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        PageInfo pageInfo = new PageInfo(10, pageSize, page);
        String sql = "SELECT WORK_SECURITY_ID,SYS_NAME,DEPARTMENT,real_end_date-plan_end_date FROM T_WORK_SECURITY WHERE REMOVED =:removed AND CONFIRM=:confirm AND PLAN_BEGIN_DATE >= :beginDate AND REAL_BEGIN_DATE > PLAN_END_DATE AND PLAN_END_DATE <= :endDate";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();
        return toEntity(result);
    }

    @Override
    public List<WorkSecurity> findByRealEndDate(Integer year, int page, int pageSize) {

        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("confirm", "1");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        PageInfo pageInfo = new PageInfo(10, pageSize, page);
        String sql = "SELECT WORK_SECURITY_ID,SYS_NAME,DEPARTMENT,real_end_date-plan_end_date FROM T_WORK_SECURITY WHERE REMOVED =:removed AND CONFIRM=:confirm AND PLAN_BEGIN_DATE >= :beginDate AND REAL_END_DATE > PLAN_END_DATE AND PLAN_END_DATE <= :endDate";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();

        return toEntity(result);
    }

    private List<WorkSecurity> toEntity(List<Object[]> result) {
        ArrayList<WorkSecurity> workSecurities = new ArrayList<WorkSecurity>();
        if (result != null) {

            for (Object[] objects : result) {
                WorkSecurity security = new WorkSecurity();
                security.setWorkSecurityId((String) objects[0]);
                security.setSysName((String) objects[1]);
                security.setDepartment((String) objects[2]);
                if (objects[3] != null)
                    security.setYear(((BigDecimal) objects[3]).intValue());
                else
                    security.setYear(0);
                workSecurities.add(security);
            }

        }
        return workSecurities;
    }

    @Override
    public int deleteAll() throws Exception {
        return getSession().createSQLQuery("delete from T_WORK_SECURITY").executeUpdate();
    }
}
