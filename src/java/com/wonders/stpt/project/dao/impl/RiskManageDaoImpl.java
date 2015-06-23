package com.wonders.stpt.project.dao.impl;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.project.dao.RiskManageDao;
import com.wonders.stpt.project.model.RiskManage;
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
import java.util.*;

/**
 * Created by Administrator on 2014/6/26.
 */
@Repository("riskManageDao")
public class RiskManageDaoImpl implements RiskManageDao {

    private HibernateTemplate hibernateTemplate;
    private final Logger logger = Logger.getLogger(RiskManageDaoImpl.class);

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
    public void save(List<RiskManage> riskManages) throws Exception {
        for (int i = 0; i < riskManages.size(); i++) {
            getSession().saveOrUpdate(riskManages.get(i));

            if (i % 50 == 0) // 以每50个数据作为一个处理单元，也就是我上面说的“一定的量”，这个量是要酌情考虑的

            {
                getSession().flush();
                getSession().clear();
            }
        }
    }

    @Override
    public PageResultSet<RiskManage> find(RiskManage riskManage, int page, int pageSize) throws Exception {
        Criteria c = getSession().createCriteria(RiskManage.class);
        c.add(Example.create(riskManage).enableLike(MatchMode.START).excludeNone().excludeZeroes()
                .excludeProperty("createTime").excludeProperty("updateTime"));

        if (riskManage.getDiscoveryBeginDate() != null)
            c.add(Restrictions.ge("discovery", riskManage.getDiscoveryBeginDate()));


        if (riskManage.getDiscoveryEndDate() != null)
            c.add(Restrictions.le("discovery", riskManage.getDiscoveryEndDate()));

        c.setProjection(Projections.count("riskManageId"));//根据id值进行投影统计（统计记录总数）
        Integer i = (Integer) c.uniqueResult();
        PageInfo pageInfo = new PageInfo(i, pageSize, page);
        c.setProjection(null);
        c.setFirstResult(pageInfo.getBeginIndex());
        c.setMaxResults(pageSize);

        PageResultSet<RiskManage> pageResultSet = new PageResultSet<RiskManage>();
        pageResultSet.setList(c.list());
        pageResultSet.setPageInfo(pageInfo);

        return pageResultSet;
    }

    @Override
    public int deleteAll() {

        return getSession().createSQLQuery("delete from T_risk_manage").executeUpdate();
    }

    @Override
    public RiskManage load(String riskManageId) throws Exception {
        // TODO Auto-generated method stub
        return (RiskManage) getHibernateTemplate().get(RiskManage.class, new String(riskManageId));
    }

    @Override
    public RiskManage save(RiskManage riskManage) throws Exception {
        // TODO Auto-generated method stub
        getHibernateTemplate().saveOrUpdate(riskManage);
        return riskManage;
    }

    @Override
    public int deletes(String riskManageId) {
        // TODO Auto-generated method stub
        return getSession().createQuery("update RiskManage t set t.removed=:removed where t.riskManageId=:riskManageId").setString("removed", "1").setString("riskManageId", riskManageId).executeUpdate();
    }

    @Override
    public List count(Integer year) {
        HashMap param = new HashMap();
        Calendar month = new GregorianCalendar();
        param.put("removed", "0");
        param.put("trackInfo", "已验证");
        param.put("trackInfo2", "未完成");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        param.put("currMonthBegin", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT COUNT(1) FROM T_RISK_MANAGE  WHERE REMOVED =:removed AND TRACK_DATE >= :beginDate  AND TRACK_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_RISK_MANAGE WHERE REMOVED =:removed AND  TRACK_DATE >= :beginDate AND TRACK_INFO =:trackInfo AND TRACK_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_RISK_MANAGE WHERE REMOVED =:removed AND TRACK_DATE >= :beginDate AND DISCOVERY >= :currMonthBegin AND TRACK_DATE <= :endDate");
        sql.append(" UNION ALL ");
        sql.append("SELECT COUNT(1) FROM T_RISK_MANAGE WHERE REMOVED =:removed AND TRACK_DATE >= :beginDate AND SYSDATE > DISCOVERY+DATE_LIMIT AND TRACK_INFO = :trackInfo2 AND TRACK_DATE <= :endDate");
        return getSession().createSQLQuery(sql.toString()).setProperties(param).list();

    }

    @Override
    public List<RiskManage> findRisManageByDiscovery(Integer year, int page, int pageSize) {
        PageInfo pageInfo = new PageInfo(10,pageSize,page);
        HashMap param = new HashMap();
        param.put("removed", "0");
        param.put("trackInfo", "未完成");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        String sql = "SELECT RISK_MANAGE_ID,DEPARTMENT,SYS_NAME,RISK_LEVEL FROM T_RISK_MANAGE WHERE REMOVED =:removed AND TRACK_DATE >= :beginDate AND SYSDATE > DISCOVERY+DATE_LIMIT AND TRACK_INFO = :trackInfo AND TRACK_DATE <= :endDate  order by decode(risk_level,'高',1,'中',2,'低',3) asc";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();

        ArrayList<RiskManage> riskManages = new ArrayList<RiskManage>();
        if (result != null) {

            for (Object[] objects : result) {
                RiskManage risk = new RiskManage();
                risk.setRiskManageId((String) objects[0]);
                risk.setDepartment((String) objects[1]);
                risk.setSysName((String) objects[2]);
                risk.setRiskLevel((String) objects[3]);
                riskManages.add(risk);
            }

        }
        return riskManages;
    }

    @Override
    public List<RiskManage> findByDiscovery(Integer year, int page, int pageSize) {
        PageInfo pageInfo = new PageInfo(10,pageSize,page);
        HashMap param = new HashMap();
        Calendar month = new GregorianCalendar();
        param.put("removed", "0");
        param.put("beginDate", new GregorianCalendar(year, 0, 1).getTime());
        param.put("endDate", new GregorianCalendar(year, 11, 31).getTime());
        param.put("currMonthBegin", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
        String sql = "SELECT RISK_MANAGE_ID,DEPARTMENT,SYS_NAME,RISK_LEVEL FROM T_RISK_MANAGE WHERE REMOVED =:removed AND TRACK_DATE >= :beginDate AND DISCOVERY >= :currMonthBegin AND TRACK_DATE <= :endDate";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();

        ArrayList<RiskManage> riskManages = new ArrayList<RiskManage>();
        if (result != null) {

            for (Object[] objects : result) {
                RiskManage risk = new RiskManage();
                risk.setRiskManageId((String) objects[0]);
                risk.setDepartment((String) objects[1]);
                risk.setSysName((String) objects[2]);
                risk.setRiskLevel((String) objects[3]);
                riskManages.add(risk);
            }

        }
        return riskManages;
    }

    @Override
    public List<RiskManage> countByDepartment(Integer year) {
        HashMap param = new HashMap();
        Calendar month = new GregorianCalendar();
        param.put("removed", "0");
        param.put("high", "高");
        param.put("currMonthBegin", new GregorianCalendar(year, month.get(Calendar.MONTH), 1).getTime());
        String sql = "SELECT DEPARTMENT,count(DEPARTMENT) FROM T_RISK_MANAGE WHERE REMOVED =:removed  AND DISCOVERY >= :currMonthBegin GROUP BY DEPARTMENT";
        List<Object[]> result = getSession().createSQLQuery(sql.toString()).setProperties(param).list();

        String sql2 = "SELECT DEPARTMENT,count(DEPARTMENT) FROM T_RISK_MANAGE WHERE REMOVED =:removed  AND DISCOVERY >= :currMonthBegin and risk_level=:high GROUP BY DEPARTMENT";
        List<Object[]> result2 = getSession().createSQLQuery(sql2.toString()).setProperties(param).list();

        ArrayList<RiskManage> riskManages = new ArrayList<RiskManage>();
        if (result != null) {

            for (Object[] objects : result) {
                RiskManage risk = new RiskManage();
                risk.setDepartment((String) objects[0]);
                risk.setCreator(((BigDecimal) objects[1]).intValue()+"");
                for (Object[] objects2 : result2) {
                    if(risk.getDepartment().equals((String) objects2[0])){
                        risk.setRiskInfo(((BigDecimal) objects2[1]).intValue()+"");
                        break;
                    }
                }
                riskManages.add(risk);
            }

        }
        return riskManages;
    }
}
