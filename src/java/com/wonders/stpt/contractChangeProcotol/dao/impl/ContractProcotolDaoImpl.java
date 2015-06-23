package com.wonders.stpt.contractChangeProcotol.dao.impl;


import com.wonders.stpt.contractChangeProcotol.dao.ContractProcotolDao;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/2.
 */
@Repository("contractProcotolDao")
public class ContractProcotolDaoImpl implements ContractProcotolDao {

    private HibernateTemplate hibernateTemplate;
    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }
    @Resource(name="hibernateTemplate2")
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public List<Object[]> findContractProcotolByPage(int startRow,int pageSize,String contract_name,
                                                     String  self_no,
                                                     String  change_price_start ,String change_price_end,
                                                     String  reg_person, String reg_time_start,String reg_time_end,
                                                     String flag,
                                                     String date_start, String date_end , String date_start1,
                                                     String date_end1) {
        List<Object[]> list = new ArrayList<Object[]>();
        String sql = "select t.contract_name,t.self_no," +
                "t.change_price,t.change_item_type,t.reg_person,substr(t.reg_time,0,10 )," +
                "t.exec_period_start,t.exec_period_end,t.flag,t.process,t.incidect " +
                "from  t_contract_change_protocol t where t.id is not null and t.flag in('0','1','3')";
        if(contract_name!=null&&!"".equals(contract_name)){
            sql += " and contract_name like '%"+contract_name+"%' ";
        }
        if(self_no!=null&&!"".equals(self_no)){
            sql += " and self_no like '%"+self_no+"%' ";
        }
        if(change_price_start!=null&&!"".equals(change_price_start)){
            sql += " and change_price >= '"+change_price_start+"' ";
        }
        if(change_price_end!=null&&!"".equals(change_price_end)){
            sql += " and change_price <= '"+change_price_end+"' ";
        }
        if(reg_person!=null&&!"".equals(reg_person)){
            sql += " and reg_person like '%"+reg_person+"%' ";
        }
        if(reg_time_start!=null&&!"".equals(reg_time_start)){
            sql += " and reg_time >= '"+reg_time_start+"' ";
        }
        if(reg_time_end!=null&&!"".equals(reg_time_end)){
            sql += " and reg_time <= '"+reg_time_end+"' ";
        }
        if(flag!=null&&!"".equals(flag)){
            sql += " and flag = '"+flag+"' ";
        }
        if(date_start!=null&&!"".equals(date_start)){
            sql += " and exec_period_start >= '"+date_start+"' ";
        }
        if(date_end!=null&&!"".equals(date_end)){
            sql += " and exec_period_start <= '"+date_end+"' ";
        }
        if(date_start1!=null&&!"".equals(date_start1)){
            sql += " and exec_period_end >= '"+date_start1+"' ";
        }
        if(date_end1!=null&&!"".equals(date_end1)){
            sql += " and exec_period_end <= '"+date_end1+"' ";
        }
        sql = "select f.*,c2.taskid from ("
                + sql
                + ") f ,tasks c2 where c2.incident = f.incidect and c2.processname = f.process and c2.steplabel = 'Begin'   ";
        SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
        list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
        return list;
    }
    public int countContractProcotol(String contract_name,String self_no,
                                     String  change_price_start ,String change_price_end,
                                     String  reg_person,String  reg_time_start,String reg_time_end,String  flag,
                                     String date_start, String date_end , String date_start1, String date_end1){
        String sql = "select t.contract_name,t.self_no," +
                "t.change_price,t.change_item_type,t.reg_person,t.reg_time," +
                "t.exec_period_start,t.exec_period_end,t.flag ,t.process,t.incidect" +
                " from  t_contract_change_protocol t where t.id is not null and t.flag in('0','1','3') ";
        if(contract_name!=null&&!"".equals(contract_name)){
            sql += " and contract_name like '%"+contract_name+"%' ";
        }
        if(self_no!=null&&!"".equals(self_no)){
            sql += " and self_no like '%"+self_no+"%' ";
        }
        if(change_price_start!=null&&!"".equals(change_price_start)){
            sql += " and change_price >= '"+change_price_start+"' ";
        }
        if(change_price_end!=null&&!"".equals(change_price_end)){
            sql += " and change_price <= '"+change_price_end+"' ";
        }
        if(reg_person!=null&&!"".equals(reg_person)){
            sql += " and reg_person like '%"+reg_person+"%' ";
        }
        if(reg_time_start!=null&&!"".equals(reg_time_start)){
            sql += " and reg_time >= '"+reg_time_start+"' ";
        }
        if(reg_time_end!=null&&!"".equals(reg_time_end)){
            sql += " and reg_time <= '"+reg_time_end+"' ";
        }
        if(flag!=null&&!"".equals(flag)){
            sql += " and flag = '"+flag+"' ";
        }
        if(date_start!=null&&!"".equals(date_start)){
            sql += " and exec_period_start >= '"+date_start+"' ";
        }
        if(date_end!=null&&!"".equals(date_end)){
            sql += " and exec_period_start <= '"+date_end+"' ";
        }
        if(date_start1!=null&&!"".equals(date_start1)){
            sql += " and exec_period_end >= '"+date_start1+"' ";
        }
        if(date_end1!=null&&!"".equals(date_end1)){
            sql += " and exec_period_end <= '"+date_end1+"' ";
        }
        System.out.println(sql);
        sql = "select f.*,c2.taskid from ("
                + sql
                + ") f ,tasks c2 where c2.incident = f.incidect and c2.processname = f.process and c2.steplabel = 'Begin'  ";
        SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num from ("+sql+") ");
        query.addScalar("count_num", Hibernate.INTEGER);

        return (Integer)query.uniqueResult();
    }
}
