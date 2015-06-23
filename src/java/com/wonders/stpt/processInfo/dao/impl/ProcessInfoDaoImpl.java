package com.wonders.stpt.processInfo.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;

import com.wonders.stpt.processInfo.dao.ProcessInfoDao;
import com.wondersgroup.framework.core5.dao.impl.AbstractHibernateDaoImpl;

@SuppressWarnings("unchecked")
public class ProcessInfoDaoImpl extends AbstractHibernateDaoImpl<Object> implements ProcessInfoDao{
	public List<Object[]> findDocSendByPage(int startRow, int pageSize, String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator, String msgState, String oldLoginName, String newLoginName, String myPage)
	  {
	    List list = new ArrayList();
	    String sql = getSql("docSend");
	    if ("yes".equals(myPage))
	      sql = "select h.* from (select f.*,c2.taskid from (select * " + sql + 
	        ") f ,tasks c2 where c2.incident = f.pinstanceid and c2.processname = f.modelid and c2.steplabel = 'Begin' " + 
	        ") h,( select distinct incident,processname from (" + 
	        " select distinct t.incident,t.processname from tasks t where t.assignedtouser = 'ST/" + oldLoginName + "' and t.processname = '发文流程' " + 
	        " union all " + 
	        " select distinct to_number(s.pincident) incident,s.pname processname from tasks t,t_subprocess s where s.pname = '发文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '发文流程' and t.assignedtouser = 'ST/" + oldLoginName + "' " + 
	        " union all " + 
	        " select distinct t.incident,t.processname from tasks t where t.assignedtouser = 'ST/" + newLoginName + "' and t.processname = '新发文流程' " + 
	        " union all " + 
	        " select distinct to_number(s.pincident) incident,s.pname processname from tasks t,t_subprocess s where s.pname = '新发文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '新发文流程' and t.assignedtouser = 'ST/" + newLoginName + "' " + 
	        ")) j where j.incident = h.pinstanceid and j.processname = h.modelid ";
	    else {
	      sql = "select f.*,c2.taskid from (select * " + sql + ") f ,tasks c2 where c2.incident = f.pinstanceid and c2.processname = f.modelid and c2.steplabel = 'Begin' ";
	    }
	    sql = getDocSendFullSql(sql, doc_title, send_id, doc_count, 
	      send_date_start, send_date_end, content, pstatus, name, operator);

	    if ("1".equals(msgState)) {
	      sql = "select cc.*,count(*) as countRead from t_msg_usermessage u3,t_flowfunction_guanlian u4,( select bb.*,count(*) as countAll from t_msg_usermessage u1,t_flowfunction_guanlian u2, (" + 
	        sql + 
	        " ) bb" + 
	        " where u1.sid = u2.yewu_id and u2.removed = 0 and u2.processname = bb.modelid and u2.incidentno =bb.pinstanceid" + 
	        " group by bb.id,bb.send_date,bb.code1,bb.code2,bb.code3,bb.send_id,bb.doc_title,bb.doc_count,bb.rn," + 
	        " bb.taskuser,bb.name,bb.operator,bb.content,bb.modelid,bb.pinstanceid,bb.remark,bb.pstatus,bb.processstatus,bb.taskid " + 
	        " ) cc " + 
	        " where u3.sid = u4.yewu_id and u4.removed = 0 and u3.state=0 and u4.processname = cc.modelid and u4.incidentno =cc.pinstanceid" + 
	        " group by cc.id,cc.send_date,cc.code1,cc.code2,cc.code3,cc.send_id,cc.doc_title,cc.doc_count,cc.rn," + 
	        " cc.taskuser,cc.name,cc.operator,cc.content,cc.modelid,cc.pinstanceid,cc.remark,cc.pstatus,cc.processstatus,cc.taskid,cc.countAll";

	      sql = "select * from (" + sql + ")dd where dd.countAll>dd.countRead order by dd.send_date desc ";
	    } else {
	      sql = sql + "  order by send_date desc,pinstanceid desc  ";
	    }

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countDocSend(String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator, String msgState, String oldLoginName, String newLoginName, String myPage)
	  {
	    String sql = getSql("docSend");
	    if ("yes".equals(myPage))
	      sql = " from (select * " + sql + 
	        ") h,( select distinct incident,processname from (" + 
	        " select distinct t.incident,t.processname from tasks t where t.assignedtouser = 'ST/" + oldLoginName + "' and t.processname = '发文流程' " + 
	        " union all " + 
	        " select distinct to_number(s.pincident) incident,s.pname processname from tasks t,t_subprocess s where s.pname = '发文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '发文流程' and t.assignedtouser = 'ST/" + oldLoginName + "' " + 
	        " union all " + 
	        " select distinct t.incident,t.processname from tasks t where t.assignedtouser = 'ST/" + newLoginName + "' and t.processname = '新发文流程' " + 
	        " union all " + 
	        " select distinct to_number(s.pincident) incident,s.pname processname from tasks t,t_subprocess s where s.pname = '新发文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '新发文流程' and t.assignedtouser = 'ST/" + newLoginName + "' " + 
	        ")) j where j.incident = h.pinstanceid and j.processname = h.modelid";
	    else {
	      sql = sql + " where 1=1 ";
	    }
	    sql = getDocSendFullSql(sql, doc_title, send_id, doc_count, 
	      send_date_start, send_date_end, content, pstatus, name, operator);

	    if ("1".equals(msgState)) {
	      sql = "select * " + sql;
	      sql = "select cc.countAll,count(*) as countRead from t_msg_usermessage u3,t_flowfunction_guanlian u4,( select bb.*,count(*) as countAll from t_msg_usermessage u1,t_flowfunction_guanlian u2, (" + 
	        sql + 
	        " ) bb" + 
	        " where u1.sid = u2.yewu_id and u2.removed = 0 and u2.processname = bb.modelid and u2.incidentno =bb.pinstanceid" + 
	        " group by bb.id,bb.send_date,bb.code1,bb.code2,bb.code3,bb.send_id,bb.doc_title,bb.doc_count,bb.rn," + 
	        " bb.taskuser,bb.name,bb.operator,bb.content,bb.modelid,bb.pinstanceid,bb.remark,bb.pstatus,bb.processstatus " + 
	        " ) cc " + 
	        " where u3.sid = u4.yewu_id and u4.removed = 0 and u3.state=0 and u4.processname = cc.modelid and u4.incidentno =cc.pinstanceid" + 
	        " group by cc.id,cc.send_date,cc.code1,cc.code2,cc.code3,cc.send_id,cc.doc_title,cc.doc_count,cc.rn," + 
	        " cc.taskuser,cc.name,cc.operator,cc.content,cc.modelid,cc.pinstanceid,cc.remark,cc.pstatus,cc.processstatus,cc.countAll";

	      sql = " select count(*) count_num from (" + sql + ")dd where dd.countAll>dd.countRead ";
	    } else {
	      sql = "select count(*) count_num " + sql;
	    }

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  public List<Object[]> findDocReceiveByPage(int startRow, int pageSize, String title, String swid, String num, String swunit, String filezh, String pstatus, String swdate_start, String swdate_end, String oldLoginName, String newLoginName, String myPage)
	  {
	    List list = new ArrayList();
	    String sql = getSql("docReceive");
	    if ("yes".equals(myPage))
	      sql = "select * from (select c.*,c2.taskid from (select * " + sql + 
	        ") c ,tasks c2 where c2.incident = c.instanceid and c2.processname = c.modelid and c2.steplabel = 'Begin' " + 
	        ") h,( select distinct incident from (" + 
	        " select distinct(t.incident) from tasks t where t.assignedtouser = 'ST/" + oldLoginName + "' and t.processname = '收文流程' " + 
	        " union all " + 
	        " select distinct(to_number(s.pincident)) incident from tasks t,t_subprocess s where s.pname = '收文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '收文流程' and t.assignedtouser = 'ST/" + oldLoginName + "' " + 
	        " union all "+
	        " select distinct(t.incident) from tasks t where t.assignedtouser = 'ST/" + newLoginName + "' and t.processname = '新收文流程' " + 
	        " union all " + 
	        " select distinct(to_number(s.pincident)) incident from tasks t,t_subprocess s where s.pname = '新收文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '新收文流程' and t.assignedtouser = 'ST/" + newLoginName + "' " + 
	        ")) j where j.incident = h.instanceid";
	    else {
	      sql = "select c.*,c2.taskid from (select * " + sql + ") c ,tasks c2 where c2.incident = c.instanceid and c2.processname = c.modelid and c2.steplabel = 'Begin' ";
	    }
	    sql = getDocReceiveFullSql(sql, title, swid, num, swunit, filezh, pstatus, swdate_start, swdate_end);
	    sql = sql + " order by swdate desc,instanceid desc ";

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countDocReceive(String title, String swid, String num, String swunit, String filezh, String pstatus, String swdate_start, String swdate_end, String oldLoginName, String newLoginName, String myPage)
	  {
	    String sql = getSql("docReceive");
	    if ("yes".equals(myPage))
	      sql = " from (select * " + sql + 
	        ") h,( select distinct incident from (" + 
	        " select distinct(t.incident) from tasks t where t.assignedtouser = 'ST/" + oldLoginName + "' and t.processname = '收文流程' " + 
	        " union all " + 
	        " select distinct(to_number(s.pincident)) from tasks t,t_subprocess s where s.pname = '收文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '收文流程' and t.assignedtouser = 'ST/" + oldLoginName + "' " + 
	        " union all "+
	        " select distinct(t.incident) from tasks t where t.assignedtouser = 'ST/" + newLoginName + "' and t.processname = '新收文流程' " + 
	        " union all " + 
	        " select distinct(to_number(s.pincident)) from tasks t,t_subprocess s where s.pname = '新收文流程' and s.cname = t.processname  " + 
	        " and s.cincident = t.incident and s.cname != '新收文流程' and t.assignedtouser = 'ST/" + newLoginName + "' " + 
	        ")) j where j.incident = h.instanceid";
	    else {
	      sql = sql + " where 1=1 ";
	    }
	    sql = getDocReceiveFullSql(sql, title, swid, num, swunit, filezh, pstatus, swdate_start, swdate_end);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num " + sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  public List<Object[]> findDocDirectiveByPage(int startRow, int pageSize, String title, String deptid, String zleader, String submitdate_start, String submitdate_end, String submitdept, String pstatus)
	  {
	    List list = new ArrayList();
	    String sql = getSql("docDirective");
	    sql = "select c.*,c2.taskid from (select * " + sql + ") c ,tasks c2 where c2.incident = c.processinstanceid and c2.processname = c.activeid and c2.steplabel = 'Begin' ";
	    sql = getDocDirectiveFullSql(sql, title, deptid, zleader, submitdate_start, 
	      submitdate_end, submitdept, pstatus);

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countDocDirective(String title, String deptid, String zleader, String submitdate_start, String submitdate_end, String submitdept, String pstatus)
	  {
	    String sql = getSql("docDirective");
	    sql = sql + " where 1=1 ";
	    sql = getDocDirectiveFullSql(sql, title, deptid, zleader, submitdate_start, 
	      submitdate_end, submitdept, pstatus);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num " + sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  public List<Object[]> findHtspOaByPage(int startRow, int pageSize, String plan_num, String contract_num, String self_num, String contract_name, String project_num, String sign_cop, String add_time_start, String add_time_end, String add_person, String contract_money, String pstatus)
	  {
	    List list = new ArrayList();
	    String sql = getSql("htsp_oa");
	    sql = "select * " + sql + " where 1=1 ";
	    sql = getHtspOaFullSql(sql, plan_num, contract_num, self_num, 
	      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
	      add_person, contract_money, pstatus);

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countHtspOa(String plan_num, String contract_num, String self_num, String contract_name, String project_num, String sign_cop, String add_time_start, String add_time_end, String add_person, String contract_money, String pstatus)
	  {
	    String sql = getSql("htsp_oa");
	    sql = sql + " where 1=1 ";
	    sql = getHtspOaFullSql(sql, plan_num, contract_num, self_num, 
	      contract_name, project_num, sign_cop, add_time_start, add_time_end, 
	      add_person, contract_money, pstatus);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num " + sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  public List<Object[]> findDbByPage(int startRow, int pageSize, String bh_all, String depement_z, String depement_x, String flags)
	  {
	    List list = new ArrayList();
	    String sql = getSql("db");
	    sql = "select c.*,c2.taskid from (select * " + sql + ") c ,tasks c2 where c2.incident = c.instant_id and c2.processname = c.process_name and c2.steplabel = 'Begin' ";
	    sql = getDbFullSql(sql, bh_all, depement_z, depement_x, flags);

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countDb(String bh_all, String depement_z, String depement_x, String flags)
	  {
	    String sql = getSql("db");
	    sql = sql + " where 1=1 ";
	    sql = getDbFullSql(sql, bh_all, depement_z, depement_x, flags);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num " + sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  public List<Object[]> findJobContactByPage(int startRow, int pageSize, String serial, String theme, String main_unit, String copy_unit, String flag,String processType)
	  {
	    List list = new ArrayList();
	    String sql = "";
	    if("old".equals(processType)){
	    	sql = getSql("jobContact_old");
	    	sql = "select c.*,c2.taskid from (" + sql + ") c left join tasks c2 on c2.incident = c.instanceid and c2.processname = '工作联系单' and c2.steplabel = 'Begin' ";
	    }else{
	    	sql = getSql("jobContact");
	    }
	    sql = "select * from (" + sql + ") where 1=1 ";
	    sql = getJobContactFullSql(sql, serial, theme, main_unit, copy_unit, flag);
	    sql = sql + " order by contact_date desc";
	    System.out.println(sql);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countJobContact(String serial, String theme, String main_unit, String copy_unit, String flag,String processType)
	  {
		String sql = "";
	    if("old".equals(processType)){
	    	sql = getSql("jobContact_old");
	    }else{
	    	sql = getSql("jobContact");
	    }
	    sql = "select count(*) count_num from (" + sql + ") where 1=1 ";
	    sql = getJobContactFullSql(sql, serial, theme, main_unit, copy_unit, flag);
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  private String getSql(String type)
	  {
	    String sql = "";
	    if ("docSend".equals(type))
	    {
	      sql = " from  (select e.id,e.send_date,e.code1,e.code2,e.code3,e.send_id,e.doc_title,e.doc_count,e.rn,e.taskuser,cs.name,e.operator,e.content,  e.modelid,e.pinstanceid,e.remark,e.pstatus,  case e.pstatus when '1' then '13222' when '2' then '13223' when '3' then '15042' end as processstatus from  (select ta.taskuser,d.* from  (select max(to_number(ts.cincident)) cincident,c.* from   (select t.id,to_char(ins.endtime, 'YYYY-MM-DD') send_date ,t.code1, t.code2, decode(t.code3, null, '0', t.code3) code3,   t.send_id, t.doc_title, t.doc_count,ROW_NUMBER() OVER(PARTITION BY t.modelid || ' ' || t.pinstanceid ORDER BY ins.ENDTIME DESC) RN,      t.operator, t.send_main || ' ' || t.send_inside || ' ' || t.send_main_w || ' ' || t.sned_copy AS CONTENT,     t.modelid, t.pinstanceid, '' REMARK, case t.flag when '2' then '3' when '3' then '4' else to_char(ins.status) end as pstatus      from incidents ins, t_doc_send t where t.removed = 0 and (t.modelid = '发文流程' or t.modelid = '新发文流程')      and t.modelid = ins.processname and t.pinstanceid = TO_CHAR(ins.INCIDENT)     ) c left join t_subprocess ts on ts.pname = c.modelid and ts.pincident = c.pinstanceid and ts.cname = '签发领导子流程'  group by id,send_date,code1,code2,code3,send_id,doc_title,doc_count,rn,operator,content,modelid,pinstanceid,remark,pstatus     ) d left join tasks ta on ta.processname = '签发领导子流程' and ta.incident = d.cincident and ta.steplabel = '签发领导'         ) e left join cs_user cs on 'ST/'||cs.login_name = e.taskuser)";
	    }
	    else if ("docReceive".equals(type)) {
	      sql = "from (select dd.*,case pstatus when '1' then '13222' when '2' then '13223' when '3' then '15042' end as processstatus,to_number(decode(dd.swnumber, '下级单位', '0','上级单位', '0', '外单位', '0','市政府','0', dd.swnumber)) as bb  from (select t.swid,t.filedate swdate,t.id, t.swunit,t.filezh,t.num, t.title, t.swtype, t.modelid, t.instanceid,substr(t.swid, instr(t.swid, '-') + 1, length(t.swid)) as swnumber,case t.flag when '2' then '3' when '3' then '4'  else to_char(ins.status) end as pstatus, T.REMARK  from incidents ins, t_doc_receive t where t.removed = 0  and ins.processname = t.modelid and to_char(ins.incident) = t.instanceid and ins.status in (1, 2, 8) and (t.modelid = '收文流程' or t.modelid = '新收文流程' )  ) dd where 1 = 1)";
	    }
	    else if ("docDirective".equals(type)) {
	      sql = " FROM (select aa.*, case pstatus  when '1' then '13222' when '2' then '13223' when '3' then '15042' end as processstatus from (select t.deptid, t.submitdate,t.id,  t.submitdept,t.doclevel, t.title, t.zleader, t.xdept,t.operator, t.processinstanceid,t.activeid,TO_NUMBER(decode(t.zdept,null,'0',t.zdept)) zdept,'' REMARK, case t.flag  when '2' then '3' when '3' then '4' else to_char(ins.status)  end as pstatus from incidents ins, t_receive_directive t where t.removed = 0 and t.activeid = ins.processname and t.processinstanceid = to_char(ins.incident) and ins.status in (1, 2, 8)  order by submitdate desc,processinstanceid desc ) aa where 1 = 1)";
	    }
	    else if ("htsp_oa".equals(type)) {
	      sql = " from (select case t.flag  when '0' then '13382' when '1' then '13383' else '' end as status, t.plan_num,t.contract_num,t.self_num,t.contract_name,t.project_num,t.sign_cop,t.add_time,t.add_person,t.contract_money, t.flag pstatus,t.pinstance_id,t.model_id  from ht_xx t where t.removed = 0 order by t.add_time desc)  ";
	    }
	    else if ("db".equals(type)) {
	      sql = " from  (select t.id,t.process_name,t.instant_id,t.bh_all||'--'||t.creat_memo as bh_all,t.depement_z,t.depement_x,c.name,t.operate_time,(case when ((select k.status from tasks k where k.processname=t.process_name and k.incident=t.instant_id and k.steplabel='流程结束'))='3' then '预归档' else '进行中' end) as flag,(case when ((select k.status from tasks k where k.processname=t.process_name and k.incident=t.instant_id and k.steplabel='流程结束'))='3' then '1' else '0' end) as flags from t_doc_db t,cs_user c where c.login_name=t.operator order by operate_time desc)  ";
	    }
	    else if ("jobContact".equals(type)) {
	      sql = " select main.theme,main.main_unit,main.copy_unit,main.contact_date, "+
			" to_char(tree.status) flag,main.id  "+
			" from t_dept_contact_main main,t_dept_contact_tree tree "+
			" where main.removed = 0 and tree.removed = 0 and tree.type = 0 "+
			" and tree.cname = main.processname and tree.cincident = main.incidentno";
	    }
	    else if ("jobContact_old".equals(type)) {
	      sql = " select t.theme,t.main_unit,t.copy_unit,t.contact_date,t.flag,t.instanceid,t.serial "+
	    	  " from t_job_contact t where t.removed  = '0' ";
		}
	    else if ("newdocSend".equals(type)) {
	      sql = " from  (select e.id,e.send_date,e.code1,e.code2,e.code3,e.send_id,e.doc_title,e.doc_count,e.rn,e.taskuser,cs.name,e.operator,e.content,  e.modelid,e.pinstanceid,e.remark,e.pstatus,  case e.pstatus when '1' then '13222' when '2' then '13223' when '3' then '15042' end as processstatus from  (select ta.taskuser,d.* from  (select ts.cincident,c.* from   (select t.id,to_char(ins.endtime, 'YYYY-MM-DD') send_date ,t.code1, t.code2, decode(t.code3, null, '0', t.code3) code3,   t.send_id, t.doc_title, t.doc_count,ROW_NUMBER() OVER(PARTITION BY t.modelid || ' ' || t.pinstanceid ORDER BY ins.ENDTIME DESC) RN,      t.operator, t.send_main || ' ' || t.send_inside || ' ' || t.send_main_w || ' ' || t.sned_copy AS CONTENT,     t.modelid, t.pinstanceid, '' REMARK, case t.flag when '2' then '3' else to_char(ins.status) end as pstatus      from incidents ins, t_doc_send t where t.removed = 0 and t.modelid = '新发文流程'      and t.modelid = ins.processname and t.pinstanceid = TO_CHAR(ins.INCIDENT)     ) c left join t_subprocess ts on ts.pname = c.modelid and ts.pincident = c.pinstanceid and ts.cname = '签发领导子流程'     ) d left join tasks ta on ta.processname = '签发领导子流程' and ta.incident = d.cincident and ta.steplabel = '签发领导'         ) e left join cs_user cs on 'ST/'||cs.login_name = e.taskuser  order by send_date desc,pinstanceid desc )";
	    }
	    else if ("partySend".equals(type)) {
		     sql = " from (select case t.PROCESS_STATUS "+
	       "  when '1' then "+
	        "  '13222' "+
	       "  when '2' then "+
	       "   '13223'  "+
	       "  when '3' then "+
	       "   '15042' "+
	       "  else "+
	       "   '' "+
	     "  end as pstatus, "+
	     "  t.* "+
	 " from view_t_doc t  "+
	"  where t.process_name = '党办发文流程'  "+
	 "    or t.process_name = '党委发文流程' "+
	 "    or t.process_name = '新党委发文流程' "+
	 " )where 1=1";
	    }
	    else if ("disciplineSend".equals(type)) {
		     sql = " from (select case t.PROCESS_STATUS "+
	       "  when '1' then "+
	        "  '13222' "+
	       "  when '2' then "+
	       "   '13223'  "+
	       "  when '3' then "+
	       "   '15042' "+
	       "  else "+
	       "   '' "+
	     "  end as pstatus, "+
	     "  t.* "+
	 " from view_t_doc t  "+
	"  where t.process_name = '纪委发文流程'  "+
	 "    or t.process_name = '新纪委发文流程' "+
	 " )where 1=1";
	    }
	    return sql;
	  }

	  public String getDocReceiveFullSql(String sql, String title, String swid, String num, String swunit, String filezh, String pstatus, String swdate_start, String swdate_end)
	  {
	    if ((title != null) && (!"".equals(title))) {
	      sql = sql + " and title like '%" + title + "%' ";
	    }
	    if ((swid != null) && (!"".equals(swid))) {
	      sql = sql + " and swid like '%" + swid + "%' ";
	    }
	    if ((num != null) && (!"".equals(num))) {
	      sql = sql + " and num = '" + num + "' ";
	    }
	    if ((swunit != null) && (!"".equals(swunit))) {
	      sql = sql + " and swunit like '%" + swunit + "%' ";
	    }
	    if ((filezh != null) && (!"".equals(filezh))) {
	      sql = sql + " and filezh like '%" + filezh + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and pstatus = '" + pstatus + "' ";
	    }
	    if ((swdate_start != null) && (!"".equals(swdate_start))) {
	      sql = sql + " and swdate >= '" + swdate_start + "' ";
	    }
	    if ((swdate_end != null) && (!"".equals(swdate_end))) {
	      sql = sql + " and swdate <= '" + swdate_end + "' ";
	    }
	    return sql;
	  }

	  public String getDocDirectiveFullSql(String sql, String title, String deptid, String zleader, String submitdate_start, String submitdate_end, String submitdept, String pstatus)
	  {
	    if ((title != null) && (!"".equals(title))) {
	      sql = sql + " and title like '%" + title + "%' ";
	    }
	    if ((deptid != null) && (!"".equals(deptid))) {
	      sql = sql + " and deptid like '%" + deptid + "%' ";
	    }
	    if ((zleader != null) && (!"".equals(zleader))) {
	      sql = sql + " and zleader = '" + zleader + "' ";
	    }
	    if ((submitdept != null) && (!"".equals(submitdept))) {
	      sql = sql + " and submitdept like '%" + submitdept + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and pstatus = '" + pstatus + "' ";
	    }
	    if ((submitdate_start != null) && (!"".equals(submitdate_start))) {
	      sql = sql + " and submitdate >= '" + submitdate_start + "' ";
	    }
	    if ((submitdate_end != null) && (!"".equals(submitdate_end))) {
	      sql = sql + " and submitdate <= '" + submitdate_end + "' ";
	    }
	    return sql;
	  }

	  public String getDocSendFullSql(String sql, String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator)
	  {
	    if ((doc_title != null) && (!"".equals(doc_title))) {
	      sql = sql + " and doc_title like '%" + doc_title + "%' ";
	    }
	    if ((send_id != null) && (!"".equals(send_id))) {
	      sql = sql + " and send_id like '%" + send_id + "%' ";
	    }
	    if ((doc_count != null) && (!"".equals(doc_count))) {
	      sql = sql + " and doc_count = '" + doc_count + "' ";
	    }
	    if ((content != null) && (!"".equals(content))) {
	      sql = sql + " and content like '%" + content + "%' ";
	    }
	    if ((name != null) && (!"".equals(name))) {
	      sql = sql + " and name like '%" + name + "%' ";
	    }
	    if ((operator != null) && (!"".equals(operator))) {
	      sql = sql + " and operator like '%" + operator + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and pstatus = '" + pstatus + "' ";
	    }
	    if ((send_date_start != null) && (!"".equals(send_date_start))) {
	      sql = sql + " and send_date >= '" + send_date_start + "' ";
	    }
	    if ((send_date_end != null) && (!"".equals(send_date_end))) {
	      sql = sql + " and send_date <= '" + send_date_end + "' ";
	    }
	    return sql;
	  }

	  public String getHtspOaFullSql(String sql, String plan_num, String contract_num, String self_num, String contract_name, String project_num, String sign_cop, String add_time_start, String add_time_end, String add_person, String contract_money, String pstatus)
	  {
	    if ((plan_num != null) && (!"".equals(plan_num))) {
	      sql = sql + " and plan_num like '%" + plan_num + "%' ";
	    }
	    if ((contract_num != null) && (!"".equals(contract_num))) {
	      sql = sql + " and contract_num like '%" + contract_num + "%' ";
	    }
	    if ((self_num != null) && (!"".equals(self_num))) {
	      sql = sql + " and self_num like '%" + self_num + "%' ";
	    }
	    if ((contract_name != null) && (!"".equals(contract_name))) {
	      sql = sql + " and contract_name like '%" + contract_name + "%' ";
	    }
	    if ((project_num != null) && (!"".equals(project_num))) {
	      sql = sql + " and project_num like '%" + project_num + "%' ";
	    }
	    if ((sign_cop != null) && (!"".equals(sign_cop))) {
	      sql = sql + " and sign_cop like '%" + sign_cop + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and pstatus = '" + pstatus + "' ";
	    }
	    if ((add_time_start != null) && (!"".equals(add_time_start))) {
	      sql = sql + " and add_time >= '" + add_time_start + "' ";
	    }
	    if ((add_time_end != null) && (!"".equals(add_time_end))) {
	      sql = sql + " and add_time <= '" + add_time_end + "' ";
	    }
	    if ((add_person != null) && (!"".equals(add_person))) {
	      sql = sql + " and add_person like '%" + add_person + "%' ";
	    }
	    if ((contract_money != null) && (!"".equals(contract_money))) {
	      sql = sql + " and contract_money like '%" + contract_money + "%' ";
	    }
	    return sql;
	  }

	  public String getDbFullSql(String sql, String bh_all, String depement_z, String depement_x, String flags) {
	    if ((bh_all != null) && (!"".equals(bh_all))) {
	      sql = sql + " and bh_all like '%" + bh_all + "%' ";
	    }
	    if ((depement_z != null) && (!"".equals(depement_z))) {
	      sql = sql + " and depement_z like '%" + depement_z + "%' ";
	    }
	    if ((depement_x != null) && (!"".equals(depement_x))) {
	      sql = sql + " and depement_x like '%" + depement_x + "%' ";
	    }
	    if ((flags != null) && (!"".equals(flags))) {
	      sql = sql + " and flags = '" + flags + "' ";
	    }
	    return sql;
	  }

	  public String getJobContactFullSql(String sql, String serial, String theme, String main_unit, String copy_unit, String flag)
	  {
	    if ((serial != null) && (!"".equals(serial))) {
	      sql = sql + " and serial like '%" + serial + "%' ";
	    }
	    if ((theme != null) && (!"".equals(theme))) {
	      sql = sql + " and theme like '%" + theme + "%' ";
	    }
	    if ((main_unit != null) && (!"".equals(main_unit))) {
	      sql = sql + " and main_unit like '%" + main_unit + "%' ";
	    }
	    if ((copy_unit != null) && (!"".equals(copy_unit))) {
	      sql = sql + " and copy_unit like '%" + copy_unit + "%' ";
	    }
	    if ((flag != null) && (!"".equals(flag))) {
	      sql = sql + " and flag = '" + flag + "' ";
	    }
	    return sql;
	  }
	  
	  public String getPartySendFullSql(String sql,String dept,String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end)
	  {
	    if ((dept != null) && (!"".equals(dept))) {
	      sql = sql + " and dept like '%" + dept + "%' ";
	    }
	    if ((title != null) && (!"".equals(title))) {
	      sql = sql + " and title like '%" + title + "%' ";
	    }
	    if ((code != null) && (!"".equals(code))) {
	      sql = sql + " and code like '%" + code + "%' ";
	    }
	    if ((filezh != null) && (!"".equals(filezh))) {
	      sql = sql + " and filezh like '%" + filezh + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and process_status = '" + pstatus + "' ";
	    }
	    if ((processType != null) && (!"".equals(processType))) {
	      sql = sql + " and process_type like '%" + processType + "%' ";
	    }
	    if ((date_start != null) && (!"".equals(date_start))) {
	      sql = sql + " and doc_date >= '" + date_start + "' ";
	    }
	    if ((date_end != null) && (!"".equals(date_end))) {
	      sql = sql + " and doc_date <= '" + date_end + "' ";
	    }
	    return sql;
	  }
	  
	  public String getDisciplineSendFullSql(String sql,String dept,String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end)
	  {
	    if ((dept != null) && (!"".equals(dept))) {
	      sql = sql + " and dept like '%" + dept + "%' ";
	    }
	    if ((title != null) && (!"".equals(title))) {
	      sql = sql + " and title like '%" + title + "%' ";
	    }
	    if ((code != null) && (!"".equals(code))) {
	      sql = sql + " and code like '%" + code + "%' ";
	    }
	    if ((filezh != null) && (!"".equals(filezh))) {
	      sql = sql + " and filezh like '%" + filezh + "%' ";
	    }
	    if ((pstatus != null) && (!"".equals(pstatus))) {
	      sql = sql + " and process_status = '" + pstatus + "' ";
	    }
	    if ((processType != null) && (!"".equals(processType))) {
	      sql = sql + " and process_type like '%" + processType + "%' ";
	    }
	    if ((date_start != null) && (!"".equals(date_start))) {
	      sql = sql + " and doc_date >= '" + date_start + "' ";
	    }
	    if ((date_end != null) && (!"".equals(date_end))) {
	      sql = sql + " and doc_date <= '" + date_end + "' ";
	    }
	    return sql;
	  }

	  public List<Object> findZleader() {
	    List list = new ArrayList();
	    String sql = " select distinct t.zleader  from incidents ins, t_receive_directive t  where t.removed = 0  and t.activeid = ins.processname  and t.processinstanceid = to_char(ins.incident)  and ins.status in (1, 2, 8) and t.zleader is not null ";

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    query.addScalar("zleader", Hibernate.STRING);
	    list = query.list();
	    return list;
	  }

	  public List<Object[]> findNewDocSendByPage(int startRow, int pageSize, String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator)
	  {
	    List list = new ArrayList();
	    String sql = getSql("newdocSend");
	    sql = "select f.*,c2.taskid from (select * " + sql + ") f ,tasks c2 where c2.incident = f.pinstanceid and c2.processname = f.modelid and c2.steplabel = 'Begin' ";
	    sql = getDocSendFullSql(sql, doc_title, send_id, doc_count, 
	      send_date_start, send_date_end, content, pstatus, name, operator);

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
	    return list;
	  }

	  public int countNewDocSend(String doc_title, String send_id, String doc_count, String send_date_start, String send_date_end, String content, String pstatus, String name, String operator)
	  {
	    String sql = getSql("newdocSend");
	    sql = sql + " where 1=1 ";
	    sql = getDocSendFullSql(sql, doc_title, send_id, doc_count, 
	      send_date_start, send_date_end, content, pstatus, name, operator);

	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num " + sql);
	    query.addScalar("count_num", Hibernate.INTEGER);

	    return ((Integer)query.uniqueResult()).intValue();
	  }

	  public String findLoginNameById(String id)
	  {
	    String sql = "select login_name from cs_user where id = " + id;
	    SQLQuery query = getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
	    query.addScalar("login_name", Hibernate.STRING);
	    return (String)query.uniqueResult();
	  }
	
	public List<Object[]> findPartySendByPage(int startRow,int pageSize,String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = getSql("partySend");
		sql = "select * " +sql;
		sql = getPartySendFullSql(sql,dept,title,code,filezh,pstatus,processType,date_start,date_end);
		sql = sql + " order by doc_date desc,instant_id desc";
		System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
		
	}
	
	public int countPartySend(String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
		String sql = getSql("partySend");
		sql = getPartySendFullSql(sql,dept,title,code,filezh,pstatus,processType,date_start,date_end);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num "+sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		//System.out.println("num==="+query.uniqueResult());
		return (Integer)query.uniqueResult();
	}
	
	public List<Object[]> findDisciplineSendByPage(int startRow,int pageSize,String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
		List<Object[]> list = new ArrayList<Object[]>();
		String sql = getSql("disciplineSend");
		sql = "select * " +sql;
		sql = getDisciplineSendFullSql(sql,dept,title,code,filezh,pstatus,processType,date_start,date_end);
		sql = sql + " order by doc_date desc,instant_id desc";
		System.out.println("sql=="+sql);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery(sql);
		list = query.setFirstResult(startRow).setMaxResults(pageSize).list();
		return list;
		
	}
	
	public int countDisciplineSend(String dept,
			String title,String code,String filezh,String pstatus,String processType,String date_start,String date_end){
		String sql = getSql("disciplineSend");
		sql = getDisciplineSendFullSql(sql,dept,title,code,filezh,pstatus,processType,date_start,date_end);
		SQLQuery query  = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select count(*) count_num "+sql);
		query.addScalar("count_num", Hibernate.INTEGER);
		//System.out.println("num==="+query.uniqueResult());
		return (Integer)query.uniqueResult();
	}
}
