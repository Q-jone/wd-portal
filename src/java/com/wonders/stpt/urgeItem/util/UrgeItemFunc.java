package com.wonders.stpt.urgeItem.util;

import com.wonders.stpt.page.util.PageUtils;
import com.wonders.stpt.urgeItem.model.vo.UrgeItemVo;

public class UrgeItemFunc {
	public static String strSQLCount = null;
	public static String strSQLPage = null;
	
	public static String generateSql(String userId, String loginName,String userName,String userDeptId,String size){
		String deptSql = "";
		//deptSql = " and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ";		
		
		deptSql = " and (instr(t.helpurl,':"+userDeptId+"<+>') >0) ";		

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
        	//" '"+userName+"' as taskuser_name, "+
        	" part.assignedtouser as taskuser_name, "+
        	//" 'ST/"+loginName+"' as UserName"+
        	" e2.name as UserName"+

        	" from incidents inci, "+
        	" cs_user e, cs_user e2, "+
        	" (" +
//        	"(select t.processname as pname, "+
//        	" to_char(t.incident) as pincident, "+
//        	" t.processname as processname, "+
//        	" to_char(t.incident) as incident, "+
//        	" t.steplabel, "+
//        	" t.overduetime, "+
//        	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
//        	" t.taskid, "+
//        	" t.assignedtouser "+
//        	" from tasks t "+
//        	" where exists (select '' "+
//        	" from processes a "+
//        	" where t.processname = a.processname "+
//        	" and a.launchtype = 0 "+
//        	" and a.processname <> '拟办子流程' "+
//        	" and a.processname <> '办结子流程' "+
//        	" ) "+
//        	
//        	" and t.status = 1 and t.assignedtouser like '%ST/%' "+
//        	" and t.assignedtouser = 'ST/" + loginName + "' "+
//        	deptSql + //暂时
//        	" ) " +
//        	
//        	"union "+
        	
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
        	" where b.cname = t.processname " +
        	//zhoushun
        	" and b.cname='部门内部子流程'"+
        	//zhoushun
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
      
        	//" and t.status = 1 "+
        	" and t.status = 1 and t.assignedtouser like '%ST/%' "+
        	//" and t.assignedtouser = 'ST/" + loginName + "' "+
        	" )) part "+
        	" where part.pname = inci.processname "+
        	" and part.pincident = inci.incident  and e.removed=0 "+
        	" and upper(inci.initiator) = 'ST/' || upper(e.login_name)" +
        	" and upper(part.assignedtouser) = 'ST/' || upper(e2.login_name)"+
        	" and to_date(to_char(part.overduetime,'YYYY.MM.DD'),'YYYY.MM.DD')-to_date(to_char(sysdate,'YYYY.MM.DD'),'YYYY.MM.DD')<0 " + 
        	"";
        
        // 设置多久为超时
        //" to_date(to_char(sysdate,'YYYY.MM.DD'),'YYYY.MM.DD')-to_date(to_char(t.starttime,'YYYY.MM.DD'),'YYYY.MM.DD')" +
		//" ) > "+ overdate
                
        strSQL = "select main.*,deptInfo.deptName from (("
        		+strSQL
        		+ ") main inner join  ( select ta.processname, ta.incident,"
        		+ "(select co.name from cs_organ_node co where replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = co.id ) as deptName "
        		+ " from tasks ta, (select distinct processname, processversion, steplabel from processsteps where stepid like '%B' ) tb "
        		+ " where ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
        		+ ")deptInfo on(main.pname = deptInfo.processname and main.pincident = to_char(deptInfo.incident)))" ;
        
      
		strSQL = "select a.*,b.delay_date from ("+strSQL+") a left join t_delay_item b on (a.processname = b.process and a.incident = b.incident and b.status='0' and b.removed='0')";	
		//strSQL = "select * from ( select * from ("+strSQL+")  order by endtime desc) where 1=1";
		
		//System.out.println(strSQL);
		return strSQL;
		
	}
	
	public static String generateSql(String sql, UrgeItemVo vo){
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
	
	

}
