package com.wonders.stpt.todoItem.util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.page.util.PageUtils;
import com.wonders.stpt.todoItem.model.vo.TodoItemVo;

public class TodoItemFunc {
	public static String strSQLCount = null;
	public static String strSQLPage = null;
	
	public static String generateSql(String userId, String loginName,String userName,String userDeptId,String size,TodoItemVo vo){
		String deptSql = "";
		deptSql = " and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ";		
		String strSQL = "";
       
        strSQL=
        	" select inci.summary, "+
        	" '' as memo,"+
        	" inci.priorities, " +
        	" decode(inci.priorities,'急件','<font color=\"red\"><b>急件</b></font>','')||(case when (to_date(to_char(part.overduetime,'YYYY.MM.DD'),'YYYY.MM.DD')-to_date(to_char(sysdate,'YYYY.MM.DD'),'YYYY.MM.DD'))<0 and part.overduetime is not null then '<font color=\"red\"><b>(超时'||to_char(to_date(to_char(sysdate,'YYYY.MM.DD'),'YYYY.MM.DD')-to_date(to_char(part.overduetime,'YYYY.MM.DD'),'YYYY.MM.DD'))||'天)</b></font>' when (to_date(to_char(sysdate,'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS') - to_date(to_char(part.overduetime, 'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS')) > 0 and to_date(to_char(sysdate,'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS') - to_date(to_char(part.overduetime, 'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS') < 1 and part.overduetime is not null then '<font color=\"red\"><b>(今天超时)</b></font>' end) as priorities_show, "+
        	
        	" part.*, "+
        	" '0' as task_type, "+
        	" '1' as 连接字符串, "+
        	" inci.initiator, "+
        	" e.name as apply_username, "+
        	" '"+userName+"' as taskuser_name, "+
        	" 'ST/"+loginName+"' as UserName"+

        	" from incidents inci, "+
        	" cs_user e, "+
        	" ((select t.processname as pname, "+
        	" to_char(t.incident) as pincident, "+
        	" t.processname as processname, "+
        	" to_char(t.incident) as incident, "+
        	" t.steplabel, "+
        	" t.overduetime, "+
        	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
        	" t.taskid, "+
        	" t.assignedtouser "+
        	" from tasks t "+
        	" where exists (select '' "+
        	" from processes a "+
        	" where t.processname = a.processname "+
        	" and a.launchtype = 0 "+
        	" and a.processname <> '拟办子流程' "+
        	" and a.processname <> '办结子流程' "+
        	" ) "+
        	
        	
        	" and not exists (" +
        	" select ''" +
        	" from t_job_contact tj" +
        	" where t.processname = tj.processname" +
        	" and t.incident = tj.instanceid " +
        	" and tj.group_attribute=1" +
        	" )" +
        	
        	" and t.status = 1 and t.assignedtouser like '%ST/%' "+
        	" and t.assignedtouser = 'ST/" + loginName + "' "+
        	deptSql + //暂时
        	" ) " +
        	
        	"union "+
        	
        	" (select b.pname as pname, "+
        	" b.pincident as pincident, "+
        	" b.cname as processname, "+
        	" b.cincident as incident, "+
        	" t.steplabel, "+
        	" t.overduetime, "+
        	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
        	" t.taskid, "+
        	" t.assignedtouser "+
        	" from t_subprocess b, tasks t "+
        	" where b.cname = t.processname "+
        	" and b.cincident = t.incident "+
        	deptSql +//暂时
        	" and not exists "+
        	" (select '' "+
        	" from processes a "+
        	" where t.processname = a.processname "+
        	" and a.launchtype = 0 "+
        	" and a.processname <> '拟办子流程' "+
        	" and a.processname <> '办结子流程' "+
        	" ) "+
        	
        	" and not exists(" +
        	" select ''" +
        	" from t_job_contact tj" +
        	" where b.pname = tj.processname" +
        	" and b.pincident = tj.instanceid " +
        	" and tj.group_attribute =1" +
        	" )"+
        	
        	//" and t.status = 1 "+
        	" and t.status = 1 and t.assignedtouser like '%ST/%' "+
        	" and t.assignedtouser = 'ST/" + loginName + "' "+
        	" )) part "+
        	" where part.pname = inci.processname "+
        	" and part.pincident = inci.incident  and e.removed=0 "+
        	" and upper(inci.initiator) = 'ST/' || upper(e.login_name)" +
        	"";
                
        strSQL = "select main.*,deptInfo.deptName from (("
        		+strSQL
        		+ ") main inner join  ( select ta.processname, ta.incident,"
        		+ "(select co.name from cs_organ_node co where replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = co.id ) as deptName "
        		+ " from tasks ta, (select distinct processname, processversion, steplabel from processsteps where stepid like '%B' ) tb "
        		+ " where ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
        		+ ")deptInfo on(main.pname = deptInfo.processname and main.pincident = to_char(deptInfo.incident)))" ;
        
        String strJobSQL="";//工作联系单新版本查询用SQL      
        
        /**/
        strJobSQL=
        " select " +
    	" '<div class=summary groupid='||groupid||' ptype=1 dbxtype=process>'||tjcg.summary||'</div> ' as summary,"+
    	" '<div class=memo dbxtype=process groupid='||groupid||'></div>' as memo,"+
    	" tjcg.priorities,"+
		" decode(tjcg.priorities,'急件','<font color=\"red\"><b>急件</b></font>','')||(case when (to_date(to_char(tjcg.overdue_time,'YYYY.MM.DD'),'YYYY.MM.DD')-to_date(to_char(sysdate,'YYYY.MM.DD'),'YYYY.MM.DD'))<0 and tjcg.overdue_time is not null then '<font color=\"red\"><b>(超时'||to_char(to_date(to_char(sysdate,'YYYY.MM.DD'),'YYYY.MM.DD')-to_date(to_char(tjcg.overdue_time,'YYYY.MM.DD'),'YYYY.MM.DD'))||'天)</b></font>' when (to_date(to_char(sysdate,'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS') - to_date(to_char(tjcg.overdue_time, 'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS')) > 0 and to_date(to_char(sysdate,'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS') - to_date(to_char(tjcg.overdue_time, 'YYYY.MM.DD HH24:MI:SS'),'YYYY.MM.DD HH24:MI:SS') < 1 and tjcg.overdue_time is not null then '<font color=\"red\"><b>(今天超时)</b></font>' end) as priorities_show,"+
		" '工作联系单' as pname,"+
		" '' as pincident,"+
		" '工作联系单' as processname,"+
		" '' as incident,"+
		" '<div class=steplabel dbxtype=process groupid='||groupid||'></div>' as steplabel,"+
		" to_date(tjcg.overdue_time) as overduetime,"+
		" to_char(part1.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime,"+
		" groupid as taskid,"+
		" 'ST/" + loginName + "' as assignedtouser,"+
		" '0' as task_type, "+
		" '1' as 连接字符串, "+
		" tjcg.initiator,"+
		" tjcg.initiator_name as apply_username,"+
		" '"+userName+"' as taskuser_name, "+
		" 'ST/"+loginName+"' as UserName,"+
		" tjcg.create_deptname as deptname"+
		" "+
		" from ("+
		" select groupid,max(endtime) as endtime from ("+
		" select part.pname,part.incident,part.endtime"+
		" ,(select tjcr.business_id from t_job_contact_reference tjcr where tjcr.jc_id in ("+
		" select tjc.id from t_job_contact tjc where tjc.processname=part.pname "+
		" and tjc.instanceid = part.pincident and tjc.group_attribute=1 and tjc.removed != '1'"+
		" )and tjcr.business_table_name='T_JOB_CONTACT_GROUP' and tjcr.removed!=1) as groupid"+
		" "+
		" from("+
		" (select t.processname as pname, to_char(t.incident) as pincident, t.processname as processname, to_char(t.incident) as incident, t.endtime from tasks t"+
		" where t.processname='工作联系单' and exists (select '' from t_job_contact tj"+
		" where t.processname = tj.processname and t.incident = tj.instanceid and tj.group_attribute =1 and tj.removed != '1')" +
		//deptSql +//暂时
		" and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) " +
		" and t.assignedtouser like '%ST/%'and t.status = 1 and t.assignedtouser = 'ST/" + loginName + "' )"+
		" union"+
		" (select b.pname as pname,b.pincident as pincident,b.cname as processname,b.cincident as incident,t.endtime "+
		" from t_subprocess b, tasks t where b.cname = t.processname and b.cincident = t.incident "+
		" and exists (select '' from t_job_contact tj where b.pname = tj.processname and b.pincident = tj.instanceid and tj.group_attribute =1 and tj.removed != '1')"+
		" and t.assignedtouser like '%ST/%'"+
		" and b.pname='工作联系单' and b.cname='部门内部子流程'"+
		//deptSql +//暂时
		" and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) " +
		" and t.status = 1 and t.assignedtouser = 'ST/" + loginName + "' "+
		" ))part)"+
		" group by groupid)part1,t_job_contact_group tjcg where part1.groupid = tjcg.id";
        
    
        String dyxSQL = "";
        dyxSQL = 
       	" select '<div class=summary groupid=' || twr.business_id ||' ptype=0 dbxtype=dyx>' || tjcg.summary || '</div> ' as summary," +
       	" '<div class=memo dbxtype=dyx groupid=' || twr.business_id || '></div>' as memo," +
       	" tjcg.priorities," +
       	" '' as priorities_show," +
       	" '工作联系单' as pname," +
       	" '' as pincident," +
       	" '工作联系单' as processname," +
       	" '' as incident," +
       	" '传阅' as steplabel," +
       	" to_date(tjcg.overdue_time) as overduetime," +
       	" twr.operate_date as endtime," +
       	" twr.business_id as taskid," +
       	" 'ST/"+loginName+"' as assignedtouser," +
       	" '0' as task_type," +
       	" '1' as 连接字符串," +
       	" tjcg.initiator," +
       	" tjcg.initiator_name as apply_username," +
       	" '"+userName+"' as taskuser_name," +
       	" 'ST/"+loginName+"' as UserName," +
       	" tjcg.create_deptname as deptname" +
       	" from t_job_contact_group tjcg," +
       	" (select twr.business_id,max(twr.operate_date) as operate_date from t_wait_read twr where " +
       	" twr.removed != 1" +
       	" and twr.status = '0'" +
       	//" and twr.wr_user = 'ST/"+user+"'" +
       	" and twr.wr_user = '"+userId+"'" +
       	
       	" and twr.wr_deptid = '"+userDeptId+"'" +
       	" group by twr.business_id" +
       	" ) twr " +
       	" where tjcg.id = twr.business_id" +
       	"";
//        
//        String dbSQL = "";
//        dbSQL = 
//       	" select t.summary as summary," +
//       	" '<div class=memo dbxtype=dyx groupid=' || t.taskid || '></div>' as memo," +
//       	" t.priorities as priorities," +
//       	" (case when t.priorities=1 then '<font color=\"red\"><b>急件</b></font>' end) as priorities_show," +
//       	" '督办流程' as pname," +
//       	" t.ext2 as pincident," +
//       	" '督办流程' as processname," +
//       	" t.ext2 as incident," +
//       	" '传阅' as steplabel," +
//       	" to_date(t.overdue_time,'yyyy-mm-dd') as overduetime," +
//       	" t.operate_date as endtime," +
//       	" t.taskid as taskid," +
//       	" 'ST/"+loginName+"' as assignedtouser," +
//       	" '0' as task_type," +
//       	" '1' as 连接字符串," +
//       	" t.initiator," +
//       	" t.initiator_name as apply_username," +
//       	" '"+userName+"' as taskuser_name," +
//       	" 'ST/"+loginName+"' as UserName," +
//       	" t.create_deptname as deptname" +
//       	" from t_doc_dbcy t where t.initiator='ST/"+loginName+"' and t.status='0'";
//        
        
		strSQL = "select * from (("+strSQL+")union("+strJobSQL+")union("+dyxSQL+"))";	
		//strSQL = "select * from ( select * from ("+strSQL+")  order by endtime desc) where 1=1";
		
		//System.out.println(strSQL);
		return strSQL;
		
	}
	
	public static String generateSql(String sql, TodoItemVo vo){
		if(vo != null){
			String[] queryNameArr = {"pname","summary","deptname","endtime"};
			String[] queryTypeArr = {"textType","textType","textType","dateType"};
			String[] queryResultArr = {vo.getTypename(),vo.getTitle(),vo.getApplydept(),vo.getOccurtime()};
			sql = PageUtils.generateSQLByType(sql, queryNameArr, queryResultArr, queryTypeArr);
			sql += " order by endtime desc";
		}
		//System.out.println("zs++"+sql);
		return sql;
	}
	
	public static int getCount(){
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		int count = 0;
		try{
			ResultSet rs = dealsql.ExecuteDemandSql(strSQLCount);
			if(rs.next()){
				count = rs.getInt("count");
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return count;
	}
	
	public static List<Map<String,String>> findTodoItem(){
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
			try{
				ResultSet rs=dealsql.ExecuteDemandSql(strSQLPage);
				
				while(rs.next()){
					Map<String,String> processMap = new HashMap<String,String>();
					processMap.put("taskid", rs.getString("taskid"));
					processMap.put("UserName", rs.getString("UserName"));
					processMap.put("pname", rs.getString("pname"));
					processMap.put("pincident", rs.getString("pincident"));
					processMap.put("steplabel", rs.getString("steplabel"));
					processMap.put("task_Type", rs.getString("task_Type"));
					processMap.put("processname", rs.getString("processname"));
					processMap.put("incident", rs.getString("incident"));
					processMap.put("endtime", rs.getString("endtime"));
					processMap.put("summary", rs.getString("summary"));
					processMap.put("summaryZS", rs.getString("summaryZS"));
					String summary = rs.getString("summaryZS");
					String summaryShort ="";
					String distinct ="";
					if(summary.length()>20){
						summaryShort = summary.substring(0,20);
						summaryShort += "...";
					}else{
						summaryShort = summary;
					}
					distinct = rs.getString("summary");
					if(distinct!=null&&distinct.indexOf("div")>=0){
						distinct = "1";
					}else{
						distinct = "0";
					}
					processMap.put("summaryShort", summaryShort);
					processMap.put("distinct", distinct);
					list.add(processMap);
				}
				rs.close();
				dealsql.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return list;
	}
	
	
	//待办项
	public static List<Map<String,String>> findDBX(String sql){

		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
			try{
				ResultSet rs=dealsql.ExecuteDemandSql(sql);
				
				while(rs.next()){
					Map<String,String> processMap = new HashMap<String,String>();
					processMap.put("taskid", rs.getString("taskid"));
					processMap.put("UserName", rs.getString("UserName"));
					processMap.put("pname", rs.getString("pname"));
					processMap.put("pincident", rs.getString("pincident"));
					processMap.put("steplabel", rs.getString("steplabel"));
					processMap.put("task_Type", rs.getString("task_Type"));
					processMap.put("processname", rs.getString("processname"));
					processMap.put("incident", rs.getString("incident"));
					processMap.put("endtime", rs.getString("endtime"));
					processMap.put("summary", rs.getString("summary"));
					processMap.put("summaryZS", rs.getString("summaryZS"));
					String summary = rs.getString("summaryZS");
					String summaryShort ="";
					String distinct ="";
					if(summary.length()>20){
						summaryShort = summary.substring(0,20);
						summaryShort += "...";
					}else{
						summaryShort = summary;
					}
					distinct = rs.getString("summary");
					if(distinct!=null&&distinct.indexOf("div")>=0){
						distinct = "1";
					}else{
						distinct = "0";
					}
					processMap.put("summaryShort", summaryShort);
					processMap.put("distinct", distinct);
					list.add(processMap);
				}
				rs.close();
				dealsql.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			return list;
	}
	
	
	//工作联系单
	public static List<Map<String,String>> findDYX(String sql){
		
 		ExecuteSql dealsql= new ExecuteSql("dataSource2");
         List<Map<String,String>> list = new ArrayList<Map<String,String>>();
         
			try{
				ResultSet rs=dealsql.ExecuteDemandSql(sql);
				
				while(rs.next()){
					Map<String,String> processMap = new HashMap<String,String>();
					processMap.put("taskid", rs.getString("taskid"));
					processMap.put("pname", rs.getString("pname"));
					processMap.put("summaryZS", rs.getString("summaryZS"));
					list.add(processMap);
				}
				rs.close();
				dealsql.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
		return list;
	}
	
	
	
	public static String generateHql(String userId, String loginName,String userName,String userDeptId,String size,TodoItemVo vo){
		String hql = "";
		hql = "from TodoItem t where t.removed = 0 and t.loginName ='"+loginName+
				"' and t.deptId='" +userDeptId +"'";
		if(vo != null){
			String[] queryNameArr = {"typename","title","applydept","occurtime"};
			String[] queryTypeArr = {"textType","textType","textType","dateType"};
			String[] queryResultArr = {vo.getTypename(),vo.getTitle(),vo.getApplydept(),vo.getOccurtime()};
			hql = PageUtils.generateHQLByType(hql, queryNameArr, queryResultArr, queryTypeArr);
		}
			
		//System.out.println(hql);
		return hql;
	}
	
	

}
