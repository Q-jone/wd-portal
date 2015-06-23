package com.wonders.stpt.delayWarn.dao.impl;

import java.math.BigDecimal;
import java.util.*;

import javax.annotation.Resource;

import com.wonders.stpt.core.page.PageInfo;
import com.wonders.stpt.core.page.PageResultSet;
import com.wonders.stpt.delayWarn.model.bo.DelayProcess;
import com.wonders.stpt.util.SpringBeanUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.wonders.stpt.delayWarn.dao.DelayWarnDao;

@Repository("delayWarnDao")
public class DelayWarnDaoImpl implements DelayWarnDao {
    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    @Resource(name = "hibernateTemplate2")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    public void save(Object obj) {
        hibernateTemplate.saveOrUpdate(obj);
    }

    public void saveOrUpdateAll(Collection cols) {
        hibernateTemplate.saveOrUpdateAll(cols);
    }

    public Object load(String id, Class entityClass) {
        return hibernateTemplate.get(entityClass, id);
    }

    public List<Object[]> findBySql(String sql, List<Object> src) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql);
        if (src != null && src.size() > 0) {
            for (int i = 0; i < src.size(); i++) {
                query.setParameter(i, src.get(i));
            }
        }
        return query.list();
    }

    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> findByPage(int first, int size, String sql, List<Object> src) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        if (src != null && src.size() > 0) {
            for (int i = 0; i < src.size(); i++) {
                query.setParameter(i, src.get(i));
            }
        }
        query.setFirstResult(first);
        query.setMaxResults(size);
        List<Map<String, Object>> list = (List<Map<String, Object>>) query.list();
        return list;
    }

    public int findPageSize(String sql, List<Object> src) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        SQLQuery query = session.createSQLQuery(sql);
        if (src != null && src.size() > 0) {
            for (int i = 0; i < src.size(); i++) {
                query.setParameter(i, src.get(i));
            }
        }
        query.addScalar("count_num", Hibernate.INTEGER);
        return (Integer) query.uniqueResult();
    }

    @Override
    public List<Object[]> countDelayDays(String department, String status, String beginDate, String endDate) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        HashMap parameter = new HashMap();
        String dbUser = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("dbUser").trim();
        String sql = "select delay,num,round(num/sum(num)over(),4)*100 from(select count(delay_info) num,delay_info delay from ( " +
                " " +
                " " +
                "select delays, " +
                "    case when delays between 1 and 2 then '3天以下'  " +
                "    when delays between 3 and 10 then '3-10天'  " +
                "    when delays between 11 and 30 then '10-30天'  " +
                "    when delays between 31 and 100 then '30-100天'  " +
                "    when delays > 100 then '100天以上'  " +
                "  end delay_info  " +
                "  from (select deptname,pname,pincident,count(pname) incident_num,max(delays) delays from ( " +
                "   " +
                "   " +
                "   " +
                "   " +
                " " +
                "select  " +
                "         x.pname,x.pincident,x.cname,x.cincident, " +
                "         getworkdays(starttime,sysdate,5) delays, " +
                "         decode(workflow_type, " +
                "                0, " +
                "                (select decode(name,'维护保障公司','维保公司',name) from cs_organ_node od where od.id = x.deptcode), " +
                "                1, " +
                "                (select t.name " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid = 2500 and t.type_id=2 " +
                "                    and id = x.deptcode)) deptname " +
                "          from ( " +
                "                 " +
                "                SELECT i.pname, " +
                "                        i.pincident, " +
                "                        i.cname, " +
                "                        i.cincident, " +
                "                        ta.starttime, " +
                "                        ta.assignedtouser, " +
                "                        substr(ta.helpurl, " +
                "                               instr(ta.helpurl, ':', 1, 1) + 1, " +
                "                               instr(ta.helpurl, '<', 1, 1) - " +
                "                               instr(ta.helpurl, ':', 1, 1) - 1) deptcode, " +
                "                        decode(length(regexp_substr(ta.assignedtouser, " +
                "                                                    '[[:alnum:]]+', " +
                "                                                    1, " +
                "                                                    2)), " +
                "                               12, " +
                "                               0, " +
                "                               16, " +
                "                               1) workflow_type " +
                "                        " +
                "                  from t_subprocess i " +
                "                  left join tasks ta " +
                "                    on ta.incident = i.cincident " +
                "                   and ta.processname = i.cname " +
                "                 where not exists " +
                "                 (select cname, cincident " +
                "                          from (select sp.cname, sp.cincident " +
                "                                  from (select t.incident, t.processname " +
                "                                          from incidents i " +
                "                                         inner join (select r.instanceid as incident, " +
                "                                                           r.modelid    as processname " +
                "                                                      from T_DOC_RECEIVE r " +
                "                                                     where r.priorities <> '普件') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname) " +
                "                         where cname = '部门内部子流程' " +
                "                           and cincident = i.cincident " +
                "                        union " +
                "                        select cname, cincident " +
                "                          from (select sp.cname, sp.cincident " +
                "                                  from (select t.incident, t.processname " +
                "                                          from incidents i " +
                "                                         inner join (select r.PROCESSINSTANCEID as incident, " +
                "                                                           r.ACTIVEID          as processname " +
                "                                                      from T_RECEIVE_DIRECTIVE r " +
                "                                                     where r.doclevel <> '正常') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname) " +
                "                         where cname = '部门内部子流程' " +
                "                           and cincident = i.cincident) " +
                "                   and i.cname = '部门内部子流程' " +
                "                   and ta.status = 1 " +
                "                   and regexp_like(ta.assignedtouser, '^ST/\\w{12,}$') " +
                "                   and getworkdays(starttime,sysdate,5)>0 " +
                "                  ) x  " +
                "                   " +
                "                 union all  " +
                "                   " +
                "                  select  " +
                "                   x.pname,x.pincident,x.cname,x.cincident, " +
                "        getworkdays(starttime,nvl(x.endtime, sysdate),3) delays, " +
                "         decode(workflow_type, " +
                "                0, " +
                "                (select decode(name,'维护保障公司','维保公司',name) from cs_organ_node od where od.id = x.deptcode), " +
                "                1, " +
                "                (select t.name " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid = 2500 and t.type_id=2 " +
                "                    and id = x.deptcode)) deptname " +
                "          from (select distinct pname, " +
                "                                pincident, " +
                "                                cname, " +
                "                                cincident, " +
                "                                n.starttime, " +
                "                                n.endtime, " +
                "                                substr(ta.helpurl, " +
                "                                       instr(ta.helpurl, ':', 1, 1) + 1, " +
                "                                       instr(ta.helpurl, '<', 1, 1) - " +
                "                                       instr(ta.helpurl, ':', 1, 1) - 1) deptcode, " +
                "                                case when pname ='多级工作联系单' then 1 when instr(pname, '新', 1, 1) = 1 then 1 ELSE 0 end workflow_type " +
                "                  from (select * " +
                "                          from (select sp.pname, " +
                "                                       sp.pincident, " +
                "                                       sp.cname, " +
                "                                       sp.cincident, " +
                "                                       nc.starttime, " +
                "                                       nc.endtime " +
                "                                  from (select t.incident, " +
                "                                               t.processname, " +
                "                                               i.starttime, " +
                "                                               i.endtime " +
                "                                          from incidents i " +
                "                                         inner join (select r.instanceid as incident, " +
                "                                                           r.modelid    as processname " +
                "                                                      from T_DOC_RECEIVE r " +
                "                                                     where r.priorities <> '普件') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname " +
                "                                 ) " +
                "                         where cname = '部门内部子流程') n " +
                "                 inner join tasks ta " +
                "                    on n.cincident = ta.incident " +
                "                   and n.cname = ta.processname " +
                "                 where ta.helpurl is not null) x where getworkdays(starttime,nvl(x.endtime, sysdate),3)>0 " +
                "                   " +
                "                  union all  " +
                "                   " +
                "                  select  " +
                "                   x.pname,x.pincident,x.cname,x.cincident, " +
                "        getworkdays(starttime,nvl(x.endtime, sysdate),3) delays, " +
                "         decode(workflow_type, " +
                "                0, " +
                "                (select decode(name,'维护保障公司','维保公司',name) from cs_organ_node od where od.id = x.deptcode), " +
                "                1, " +
                "                (select t.name " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid = 2500 and t.type_id=2 " +
                "                    and id = x.deptcode)) deptname " +
                "          from (select distinct pname, " +
                "                                pincident, " +
                "                                cname, " +
                "                                cincident, " +
                "                                n.starttime, " +
                "                                n.endtime, " +
                "                                substr(ta.helpurl, " +
                "                                       instr(ta.helpurl, ':', 1, 1) + 1, " +
                "                                       instr(ta.helpurl, '<', 1, 1) - " +
                "                                       instr(ta.helpurl, ':', 1, 1) - 1) deptcode, " +
                "                                case when pname ='多级工作联系单' then 1 when instr(pname, '新', 1, 1) = 1 then 1 ELSE 0 end workflow_type " +
                "                  from (select * " +
                "                          from (select sp.pname, " +
                "                                       sp.pincident, " +
                "                                       sp.cname, " +
                "                                       sp.cincident, " +
                "                                       nc.starttime, " +
                "                                       nc.endtime " +
                "                                  from (select t.incident, " +
                "                                               t.processname, " +
                "                                               i.starttime, " +
                "                                               i.endtime " +
                "                                          from incidents i " +
                "                                         inner join (select r.PROCESSINSTANCEID as incident, " +
                "                                                           r.ACTIVEID          as processname " +
                "                                                      from T_RECEIVE_DIRECTIVE r " +
                "                                                     where r.doclevel <> '正常') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname " +
                "                                 ) " +
                "                         where cname = '部门内部子流程') n " +
                "                 inner join tasks ta " +
                "                    on n.cincident = ta.incident " +
                "                   and n.cname = ta.processname " +
                "                 where ta.helpurl is not null) x where getworkdays(starttime,nvl(x.endtime, sysdate),3)>0  " +
                "                   " +
                "                   " +
                "                  ) where deptname is not null ";
        if (StringUtils.isNotBlank(department)) {
            sql += " and deptname = :deptname";
            parameter.put("deptname", department);
        }
        sql += " group by pname,pincident,deptname)  " +
                "                   " +
                ") group by delay_info) " +
                "                   ";


        System.out.println(sql.toString());

        return session.createSQLQuery(sql.toString()).setProperties(parameter).list();
    }

    @Override
    public String findContractId(String processname, int incident) {

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select id from t_dept_contact_main t where t.processname = :processname   and t.incidentno = :incident";
        List<String> taskids = session.createSQLQuery(sql).setString("processname", processname).setInteger("incident", incident).list();
        if (taskids != null && taskids.size() > 0) {
            return taskids.get(0);
        }
        return null;
    }

    @Override
    public String findTaskId(String processname, int incident) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select taskid from tasks t where t.processname = :processname   and incident = :incident   and t.helpurl is not null order by starttime desc";
        List<String> taskids = session.createSQLQuery(sql).setString("processname", processname).setInteger("incident", incident).list();
        if (taskids != null && taskids.size() > 0) {
            return taskids.get(0);
        }
        return null;
    }

    @Override
    public List<String> findDeptName(List deptIdList) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        String sql = "select t.dept_name from t_delay_leader t where t.new_dept_id in (:ids)";
        return (List<String>) session.createSQLQuery(sql).setParameterList("ids", deptIdList).list();
    }

    @Override
    public int findUserByLoginName(String loginName) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();


        return ((BigDecimal) session.createSQLQuery("select count(1) from v_dept_leaders where login_name=:loginName").setString("loginName", loginName).uniqueResult()).intValue();
    }

    @Override
    public List countDepartmentNums(String delayInfo, String status, String beginDate, String endDate) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        HashMap parameter = new HashMap();
        String dbUser = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("dbUser").trim();
        String sql = "select deptname,num,round(num/sum(num)over(),4)*100 pe from (select deptname,count(deptname) num from ( " +
                " " +
                " " +
                "select deptname,pname,pincident,delays " +
                "   " +
                "   " +
                "  from (select deptname,pname,pincident,count(pname) incident_num,max(delays) delays from ( " +
                "select  " +
                "         x.pname,x.pincident,x.cname,x.cincident, " +
                "         getworkdays(starttime,sysdate,5) delays, " +
                "         decode(workflow_type, " +
                "                0, " +
                "                (select decode(name,'维护保障公司','维保公司',name) from cs_organ_node od where od.id = x.deptcode), " +
                "                1, " +
                "                (select t.name " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid = 2500 and t.type_id=2 " +
                "                    and id = x.deptcode)) deptname " +
                "          from ( " +
                "                 " +
                "                SELECT i.pname, " +
                "                        i.pincident, " +
                "                        i.cname, " +
                "                        i.cincident, " +
                "                        ta.starttime, " +
                "                        ta.assignedtouser, " +
                "                        substr(ta.helpurl, " +
                "                               instr(ta.helpurl, ':', 1, 1) + 1, " +
                "                               instr(ta.helpurl, '<', 1, 1) - " +
                "                               instr(ta.helpurl, ':', 1, 1) - 1) deptcode, " +
                "                        decode(length(regexp_substr(ta.assignedtouser, " +
                "                                                    '[[:alnum:]]+', " +
                "                                                    1, " +
                "                                                    2)), " +
                "                               12, " +
                "                               0, " +
                "                               16, " +
                "                               1) workflow_type " +
                "                        " +
                "                  from t_subprocess i " +
                "                  left join tasks ta " +
                "                    on ta.incident = i.cincident " +
                "                   and ta.processname = i.cname " +
                "                 where not exists " +
                "                 (select cname, cincident " +
                "                          from (select sp.cname, sp.cincident " +
                "                                  from (select t.incident, t.processname " +
                "                                          from incidents i " +
                "                                         inner join (select r.instanceid as incident, " +
                "                                                           r.modelid    as processname " +
                "                                                      from T_DOC_RECEIVE r " +
                "                                                     where r.priorities <> '普件') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname) " +
                "                         where cname = '部门内部子流程' " +
                "                           and cincident = i.cincident " +
                "                        union " +
                "                        select cname, cincident " +
                "                          from (select sp.cname, sp.cincident " +
                "                                  from (select t.incident, t.processname " +
                "                                          from incidents i " +
                "                                         inner join (select r.PROCESSINSTANCEID as incident, " +
                "                                                           r.ACTIVEID          as processname " +
                "                                                      from T_RECEIVE_DIRECTIVE r " +
                "                                                     where r.doclevel <> '正常') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname) " +
                "                         where cname = '部门内部子流程' " +
                "                           and cincident = i.cincident) " +
                "                   and i.cname = '部门内部子流程' " +
                "                   and ta.status = 1 " +
                "                   and regexp_like(ta.assignedtouser, '^ST/\\w{12,}$') " +
                "                   and getworkdays(starttime,sysdate,5)>0 " +
                "                  ) x " +
                "                   " +
                "                 union all  " +
                "                   " +
                "                  select  " +
                "                   x.pname,x.pincident,x.cname,x.cincident, " +
                "        getworkdays(starttime,nvl(x.endtime, sysdate),3) delays, " +
                "         decode(workflow_type, " +
                "                0, " +
                "                (select decode(name,'维护保障公司','维保公司',name) from cs_organ_node od where od.id = x.deptcode), " +
                "                1, " +
                "                (select t.name " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid = 2500 and t.type_id=2 " +
                "                    and id = x.deptcode)) deptname " +
                "          from (select distinct pname, " +
                "                                pincident, " +
                "                                cname, " +
                "                                cincident, " +
                "                                n.starttime, " +
                "                                n.endtime, " +
                "                                substr(ta.helpurl, " +
                "                                       instr(ta.helpurl, ':', 1, 1) + 1, " +
                "                                       instr(ta.helpurl, '<', 1, 1) - " +
                "                                       instr(ta.helpurl, ':', 1, 1) - 1) deptcode, " +
                "                                case when pname ='多级工作联系单' then 1 when instr(pname, '新', 1, 1) = 1 then 1 ELSE 0 end workflow_type " +
                "                  from (select * " +
                "                          from (select sp.pname, " +
                "                                       sp.pincident, " +
                "                                       sp.cname, " +
                "                                       sp.cincident, " +
                "                                       nc.starttime, " +
                "                                       nc.endtime " +
                "                                  from (select t.incident, " +
                "                                               t.processname, " +
                "                                               i.starttime, " +
                "                                               i.endtime " +
                "                                          from incidents i " +
                "                                         inner join (select r.instanceid as incident, " +
                "                                                           r.modelid    as processname " +
                "                                                      from T_DOC_RECEIVE r " +
                "                                                     where r.priorities <> '普件') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname " +
                "                                 ) " +
                "                         where cname = '部门内部子流程') n " +
                "                 inner join tasks ta " +
                "                    on n.cincident = ta.incident " +
                "                   and n.cname = ta.processname " +
                "                 where ta.helpurl is not null) x where getworkdays(starttime,nvl(x.endtime, sysdate),3)>0 " +
                "                   " +
                "                  union all  " +
                "                   " +
                "                  select  " +
                "                   x.pname,x.pincident,x.cname,x.cincident, " +
                "        getworkdays(starttime,nvl(x.endtime, sysdate),3) delays, " +
                "         decode(workflow_type, " +
                "                0, " +
                "                (select decode(name,'维护保障公司','维保公司',name) from cs_organ_node od where od.id = x.deptcode), " +
                "                1, " +
                "                (select t.name " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid = 2500 and t.type_id=2 " +
                "                    and id = x.deptcode)) deptname " +
                "          from (select distinct pname, " +
                "                                pincident, " +
                "                                cname, " +
                "                                cincident, " +
                "                                n.starttime, " +
                "                                n.endtime, " +
                "                                substr(ta.helpurl, " +
                "                                       instr(ta.helpurl, ':', 1, 1) + 1, " +
                "                                       instr(ta.helpurl, '<', 1, 1) - " +
                "                                       instr(ta.helpurl, ':', 1, 1) - 1) deptcode, " +
                "                                case when pname ='多级工作联系单' then 1 when instr(pname, '新', 1, 1) = 1 then 1 ELSE 0 end workflow_type " +
                "                  from (select * " +
                "                          from (select sp.pname, " +
                "                                       sp.pincident, " +
                "                                       sp.cname, " +
                "                                       sp.cincident, " +
                "                                       nc.starttime, " +
                "                                       nc.endtime " +
                "                                  from (select t.incident, " +
                "                                               t.processname, " +
                "                                               i.starttime, " +
                "                                               i.endtime " +
                "                                          from incidents i " +
                "                                         inner join (select r.PROCESSINSTANCEID as incident, " +
                "                                                           r.ACTIVEID          as processname " +
                "                                                      from T_RECEIVE_DIRECTIVE r " +
                "                                                     where r.doclevel <> '正常') t " +
                "                                            on i.incident = t.incident " +
                "                                           and t.processname = i.processname) nc " +
                "                                  left join t_subprocess sp " +
                "                                    on sp.pincident = nc.incident " +
                "                                   and nc.processname = sp.pname " +
                "                                 ) " +
                "                         where cname = '部门内部子流程') n " +
                "                 inner join tasks ta " +
                "                    on n.cincident = ta.incident " +
                "                   and n.cname = ta.processname " +
                "                 where ta.helpurl is not null) x where getworkdays(starttime,nvl(x.endtime, sysdate),3)>0  " +
                "                  ) where deptname is not null ";
        if (StringUtils.isNotBlank(delayInfo)) {
            if ("1".equals(delayInfo))
                sql += "and delays < 3 ";
            if ("2".equals(delayInfo))
                sql += "and delays between 3 and 10 ";
            if ("3".equals(delayInfo))
                sql += "and delays between 11 and 30 ";
            if ("4".equals(delayInfo))
                sql += "and delays between 31 and 100 ";
            if ("5".equals(delayInfo))
                sql += "and delays > 100 ";
        }
        sql = sql + " group by pname,pincident,deptname) " +
                "                   " +
                ")group by deptname) " +
                "                   ";
        System.out.println(sql.toString());

        return session.createSQLQuery(sql.toString()).setProperties(parameter).list();
    }

    @Override
    public HashMap<String, Integer> countApplyDelayNums(String beginDate, String endDate) {
        StringBuffer sql = new StringBuffer();
        sql.append("SELECT DEPT,COUNT(DEPT) FROM T_DELAY_ITEM T WHERE T.DELAY_DATE BETWEEN TO_DATE(:beginDate,'yyyy-mm-dd hh24:mi:ss') AND TO_DATE(:endDate,'yyyy-mm-dd hh24:mi:ss') ");
        sql.append("GROUP BY DEPT");

        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        List<Object[]> result = session.createSQLQuery(sql.toString()).setString("beginDate", beginDate).setString("endDate", endDate).list();
        for (Object[] objects : result) {
            if (objects[1] != null)
                hashMap.put((String) objects[0], ((BigDecimal) objects[1]).intValue());

        }
        return hashMap;
    }

    @Override
    public HashMap<String, String> findDepts() {
        Session session = hibernateTemplate.getSessionFactory().openSession();
        HashMap<String, String> hashMap = new HashMap<String, String>();
        String dbUser = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("dbUser").trim();
        String sql = "                select decode(name,'维护保障公司','维保公司',name),od.id,'0' from cs_organ_node od  union all " +
                "                select t.name,t.id,'1' " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid =:pid and t.type_id=:typeid   ";
        List<Object[]> list = session.createSQLQuery(sql).setInteger("pid", 2500).setInteger("typeid", 2).list();
        for (Object[] objects : list) {
            hashMap.put(((BigDecimal) objects[1]).intValue() + "_" + ((Character) objects[2]).toString(), (String) objects[0]);
        }
        return hashMap;
    }

    public List findDepts(String department) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        HashMap param = new HashMap();
        param.put("pid", 2500);
        param.put("typeid", 2);
        if("维护保障公司".equals(department)||"维保公司".equals(department)||"维保".equals(department)|| department.indexOf("维")==0){

            param.put("department1", "维护保障公司%");
            param.put("department2","维保公司%");
        }else{
            param.put("department1", department+"%");
            param.put("department2", department+"%");
        }
        param.put("department", department+"%");
        String dbUser = SpringBeanUtil.getProperties("classpath:config.properties").getProperty("dbUser").trim();
        String sql = "                select od.id from cs_organ_node od where od.name like:department1  union " +
                "                select t.id " +
                "                   from  " + dbUser + ".v_organ_tree t " +
                "                  where t.pid =:pid and t.type_id=:typeid and t.name like:department2  ";
        List list = session.createSQLQuery(sql).setProperties(param).list();

        return list;
    }


    public PageResultSet<DelayProcess> find(String processname, String summary, String apply, List deptcodes, String status,
                                            Integer minWarn, Integer maxWarn, String beginDate, String endDate, String delayType, int page, int pageSize) {
        Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
        HashMap parameter = new HashMap();
        String sql =
                " " +
                        "                select x.pname,x.summary,x.deptcode,x.status,x.starttime," +
                        "            x.endtime,x.warn,x.delays, x.apply, x.cname,x.cincident,x.pincident,x.workflow_type  from( " +
                        "                select " +
                        "                    distinct x.*, " +
                        "                (select " +
                        "                    count(1)  " +
                        "                from " +
                        "                    t_delay_warn_log d  " +
                        "                where " +
                        "                    d.pname=x.pname  " +
                        "                    and d.pincident=x.pincident) warn, " +
                        "                    decode((select " +
                        "                    count(1)  " +
                        "                from " +
                        "                    t_delay_item d  " +
                        "                where " +
                        "                    d.pname=x.pname  " +
                        "                    and d.pincident=x.pincident), " +
                        "                0, " +
                        "                '未申请', " +
                        "                '已申请') apply " +

                        "                from " +
                        "                    (   " +
                        "                    SELECT sp.pname, " +
                        "       sp.pincident, " +
                        "       sp.cname, " +
                        "       sp.cincident, " +
                        "       i.summary, " +
                        "       i.starttime, " +
                        "       i.endtime, " +
                        "       decode(ta.status, '1', '进行中', '3', '已完成') as status, " +
                        "       getworkdays(ta.starttime, sysdate, 5) delays, " +
                        "       decode(length(regexp_substr(ta.assignedtouser, '[[:alnum:]]+', 1, 2)), " +
                        "              12, " +
                        "              0, " +
                        "              16, " +
                        "              1) workflow_type, " +
                        "       substr(ta.helpurl, " +
                        "              instr(ta.helpurl, ':', 1, 1) + 1, " +
                        "              instr(ta.helpurl, '<', 1, 1) - instr(ta.helpurl, ':', 1, 1) - 1) deptcode " +
                        "  from t_subprocess sp " +
                        " inner join tasks ta " +
                        "    on ta.incident = sp.cincident " +
                        "   and ta.processname = sp.cname " +
                        "  left join incidents i " +
                        "    on i.incident = sp.pincident " +
                        "   and i.processname = sp.pname " +
                        " where not exists " +
                        " ( " +
                        "        select r.instanceid as incident, r.modelid as processname " +
                        "          from T_DOC_RECEIVE r " +
                        "         where r.priorities <> '普件' " +
                        "           and r.instanceid = i.incident " +
                        "           and r.modelid = i.processname " +
                        "        union " +
                        "        select r.PROCESSINSTANCEID as incident, r.ACTIVEID as processname " +
                        "          from T_RECEIVE_DIRECTIVE r " +
                        "         where r.doclevel <> '正常' " +
                        "           and r.processinstanceid = i.incident " +
                        "           and r.activeid = i.processname) " +
                        "   and sp.cname = '部门内部子流程' " +
                        "   and ta.status = 1 " +
                        "   and regexp_like(ta.assignedtouser, '^ST/\\w{12,}$') " +
                        "   and getworkdays(ta.starttime, sysdate, 5) > 0                                     " +
                        "                        union " +
                        "                        all                                     " +
                        "                           select sp.pname, " +
                        "                sp.pincident, " +
                        "                sp.cname, " +
                        "                sp.cincident, " +
                        "                i.summary, " +
                        "                i.starttime, " +
                        "                i.endtime, " +
                        "                decode(i.status, '1', '进行中', '2', '已完成') as status, " +
                        "                getworkdays(i.starttime, nvl(i.endtime, sysdate), 3) delays, " +
                        "                case " +
                        "                  when pname = '多级工作联系单' then " +
                        "                   1 " +
                        "                  when instr(pname, '新', 1, 1) = 1 then " +
                        "                   1 " +
                        "                  ELSE " +
                        "                   0 " +
                        "                end workflow_type, " +
                        "                substr(ta.helpurl, " +
                        "                       instr(ta.helpurl, ':', 1, 1) + 1, " +
                        "                       instr(ta.helpurl, '<', 1, 1) - " +
                        "                       instr(ta.helpurl, ':', 1, 1) - 1) deptcode " +
                        "  from t_subprocess sp " +
                        " inner join incidents i " +
                        "    on sp.pincident = i.incident " +
                        "   and i.processname = sp.pname " +
                        " inner join tasks ta " +
                        "    on sp.cincident = ta.incident " +
                        "   and sp.cname = ta.processname " +
                        " where exists " +
                        " (select r.instanceid as incident, r.modelid as processname " +
                        "          from T_DOC_RECEIVE r " +
                        "         where r.priorities <> '普件' " +
                        "           and r.instanceid = i.incident " +
                        "           and r.modelid = i.processname) " +
                        "   and sp.cname = '部门内部子流程' " +
                        "   and ta.helpurl is not null " +
                        "   and getworkdays(i.starttime, nvl(i.endtime, sysdate), 3) > 0 " +
                        "                        union " +
                        "                        all    " +
                        "                        select sp.pname, " +
                        "                sp.pincident, " +
                        "                sp.cname, " +
                        "                sp.cincident, " +
                        "                i.summary, " +
                        "                i.starttime, " +
                        "                i.endtime, " +
                        "                decode(i.status, '1', '进行中', '2', '已完成') as status, " +
                        "                getworkdays(i.starttime, nvl(i.endtime, sysdate), 3) delays, " +
                        "                case " +
                        "                  when pname = '多级工作联系单' then " +
                        "                   1 " +
                        "                  when instr(pname, '新', 1, 1) = 1 then " +
                        "                   1 " +
                        "                  ELSE " +
                        "                   0 " +
                        "                end workflow_type, " +
                        "                substr(ta.helpurl, " +
                        "                       instr(ta.helpurl, ':', 1, 1) + 1, " +
                        "                       instr(ta.helpurl, '<', 1, 1) - " +
                        "                       instr(ta.helpurl, ':', 1, 1) - 1) deptcode " +
                        "  from t_subprocess sp " +
                        " inner join incidents i " +
                        "    on sp.pincident = i.incident " +
                        "   and i.processname = sp.pname " +
                        " inner join tasks ta " +
                        "    on sp.cincident = ta.incident " +
                        "   and sp.cname = ta.processname " +
                        " where exists " +
                        " (select r.PROCESSINSTANCEID as incident, r.ACTIVEID as processname " +
                        "          from T_RECEIVE_DIRECTIVE r " +
                        "         where r.doclevel <> '正常' " +
                        "           and r.processinstanceid = i.incident " +
                        "           and r.activeid = i.processname) " +
                        "   and sp.cname = '部门内部子流程' " +
                        "   and ta.helpurl is not null and getworkdays(i.starttime,nvl(i.endtime, sysdate),3)>0 " +
                        "                        ) x                     ) x  where  1=1 ";
        if (StringUtils.isNotBlank(processname)) {
            sql += "and pname like :processname ";
            parameter.put("processname", processname + "%");
        }
        if (StringUtils.isNotBlank(summary)) {
            sql += "and summary like :summary ";
            parameter.put("summary", summary + "%");
        }
        if (StringUtils.isNotBlank(apply)) {
            sql += "and apply = :apply ";
            parameter.put("apply", apply);
        }
        if (deptcodes!= null&&deptcodes.size()>0) {
            if (deptcodes.size() > 1) {
                sql += "and deptcode in (:deptcodes) ";
                parameter.put("deptcodes", deptcodes);
            } else {
                sql += "and deptcode = :deptcodes ";
                parameter.put("deptcodes", deptcodes.get(0));
            }
        }
        if (StringUtils.isNotBlank(status)) {
            sql += "and status = :status ";
            parameter.put("status", status);
        }
        if (StringUtils.isNotBlank(delayType)) {
            if ("1".equals(delayType))
                sql += "and delays between 1 and 2 ";
            if ("2".equals(delayType))
                sql += "and delays between 3 and 10 ";
            if ("3".equals(delayType))
                sql += "and delays between 11 and 30 ";
            if ("4".equals(delayType))
                sql += "and delays between 31 and 100 ";
            if ("5".equals(delayType))
                sql += "and delays > 100 ";
        }
        if (StringUtils.isNotBlank(beginDate)) {
            sql += "and starttime >= to_date(:beginDate,'yyyy-mm-dd') ";
            parameter.put("beginDate", beginDate);
        }
        if (StringUtils.isNotBlank(endDate)) {
            sql += "and starttime <= to_date(:endDate,'yyyy-mm-dd') ";
            parameter.put("endDate", endDate);

        }
        if (minWarn != null && minWarn > 0) {
            sql += "and warn >= :minWarn ";
            parameter.put("minWarn", minWarn);
        }
        if (maxWarn != null && maxWarn > 0) {
            sql += "and warn <= :maxWarn ";
            parameter.put("maxWarn", maxWarn);
        }
        System.out.println(sql);
        System.out.println("select count(1) from (" + sql + ")");


        int totalRow = ((BigDecimal) session.createSQLQuery("select count(1) from (" + sql + ")").setProperties(parameter).uniqueResult()).intValue();
        PageInfo pageInfo = new PageInfo(totalRow, pageSize, page);
        List<Object[]> list = session.createSQLQuery(sql).setProperties(parameter).setFirstResult(pageInfo.getBeginIndex()).setMaxResults(pageSize).list();
        ArrayList<DelayProcess> delayProcesses = new ArrayList<DelayProcess>();
        for (Object[] objects : list) {
            DelayProcess delayProcess = new DelayProcess();
            delayProcess.setProcessName((String) objects[0]);
            delayProcess.setSummary((String) objects[1]);
            delayProcess.setDeptcode((String) objects[2]);
            delayProcess.setStatus((String) objects[3]);
            delayProcess.setBeginDate(DateFormatUtils.format((Date) objects[4], "yyyy-MM-dd"));
            if (objects[5] != null)
                delayProcess.setEndDate(DateFormatUtils.format((Date) objects[5], "yyyy-MM-dd"));
//            delayProcess.setTaskid(((String) objects[6]));
            delayProcess.setWarn(((BigDecimal) objects[6]).intValue());
            delayProcess.setDelay(((BigDecimal) objects[7]).intValue());
            delayProcess.setApply((String) objects[8]);
            delayProcess.setCname((String) objects[9]);
            delayProcess.setCincident((String) objects[10]);
            delayProcess.setIncident((String) objects[11]);
            delayProcess.setWorkflowType(((BigDecimal) objects[12]).intValue() + "");
            delayProcesses.add(delayProcess);
        }
        PageResultSet<DelayProcess> rs = new PageResultSet<DelayProcess>();
        rs.setPageInfo(pageInfo);
        rs.setList(delayProcesses);
        return rs;

    }
}
