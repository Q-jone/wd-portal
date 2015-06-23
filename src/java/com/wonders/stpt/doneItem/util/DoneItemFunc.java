package com.wonders.stpt.doneItem.util;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.doneItem.model.vo.DoneItemVo;
import com.wonders.stpt.page.util.PageUtils;
import com.wonders.stpt.util.StringUtil;


public class DoneItemFunc {
	public static String generateSql(String userId, String newloginName,String loginName,
			String userName,String userDeptId,String size,List<String> loginArr,List<Object> filter){
		filter.add("ST/"+loginName);
		filter.add("ST/"+loginName);
		filter.add("ST/"+loginName);
		filter.add("ST/"+loginName);
		filter.add(userId);
		
		
		String deptSql = "";
		//deptSql = " and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ";		
		
		String strSQL = " select inci.processname,'' as memo,"+
		" case inci.priorities when '' then '' when '急件' then '<font color=''red''><b>' || inci.priorities || '</b></font>' end as priorities, "+
		" to_char(inci.incident) as incident, "+
		" inci.summary, "+
		" inci.initiator, "+
		" to_char(inci.starttime, 'yyyy-mm-dd hh24:mi:ss') starttime, "+
		" to_char(inci.endtime, 'yyyy-mm-dd hh24:mi:ss') endtime, "+
		" case inci.STATUS when 1 then 'on' when 2 then 'off' end as status, "+
		" inci.status as pstatus, "+
		" part1.endtime as userendtime, "+
		" part1.taskid as taskid,part1.cname,part1.cincident "+
		" from (select cname,cincident, taskid, processname, incident, endtime"+
		" from (select cname,cincident,taskid, processname, "+
		" incident, "+
		" endtime, "+
		" row_number() over(partition by processname || ':' || incident order by endtime desc) rn "+
		" from ((select t.taskid,t.processname processname, "+
		" to_char(t.incident) incident, t.processname cname,to_char(t.incident) cincident,"+
		" t.endtime "+
		" from tasks t "+
		" where t.assignedtouser = ? "+
		//" where t.assignedtouser = 'ST/"+loginName+"' "+
		deptSql +//暂时
		" AND EXISTS "+
		" (select '' "+
		" from processes a "+
		" where T.processname = a.processname "+
		" and a.launchtype = 0 "+
		" and a.processname <> '拟办子流程' "+
		" AND a.processname <> '办结子流程') "+
		
		" and not exists(" +
    	" select ''" +
    	" from t_job_contact tj" +
    	" where t.processname = tj.processname" +
    	" and t.incident = tj.instanceid " +
    	" and tj.group_attribute =1" +
    	" )"+
		
		
		" AND t.status = 3" +
		" and t.recipienttype <>19) union "+
		" (select t.taskid, b.pname processname, "+
		" b.pincident incident, b.cname,b.cincident,"+
		" t.endtime "+
		" from T_SUBPROCESS b, tasks t "+
		" where b.cname = t.processname "+
		" and b.cincident = t.incident "+
		" and t.assignedtouser = ? "+
		//" and t.assignedtouser = 'ST/"+loginName+"' "+
		deptSql +//暂时
		" AND NOT EXISTS "+
		" (select '' "+
		" from processes a "+
		" where T.processname = a.processname "+
		" and a.launchtype = 0 "+
		" and a.processname <> '拟办子流程' "+
		" AND a.processname <> '办结子流程') "+
		
		" and not exists(" +
    	" select ''" +
    	" from t_job_contact tj" +
    	" where b.pname = tj.processname" +
    	" and b.pincident = tj.instanceid " +
    	" and tj.group_attribute =1" +
    	" )"+
		
		" AND t.status = 3" +
		" and t.recipienttype <>19))) "+
		" where rn = 1) part1, "+
		" (select i.processname processname, "+
		" i.incident, "+
		" subStr(i.initiator, 4, length(i.initiator)) initiator, "+
		" i.status, "+
		" i.starttime, "+
		" i.endtime, "+
		" i.summary summary, "+
		" i.priorities "+
		" from incidents i "+
		" where i.status = 1 "+
		" or i.status = 2 "+
		" OR I.STATUS = 8) inci "+
		" where part1.processname = inci.processname "+
		" and part1.incident = inci.incident ";
		
		strSQL = "select main.processname processname,main.memo,main.priorities,main.incident,main.summary, main.initiator," +
				"main.starttime,main.endtime,main.status,main.pstatus,csu.name username,"+
				"main.userendtime userendtime,main.taskid as groupid,deptInfo.deptName,main.cname,main.cincident from (("
     			+strSQL
     			+ ") main "
     			+ "inner join  ( select ta.processname, ta.incident,"
     			+ " co.name deptName from tasks ta left join cs_organ_node co on  replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = to_char(co.id) "
     			+ " right join ( select  processname, processversion, steplabel from processsteps " 
     			+ " where stepid like '%B' ) tb "
     			+ " on ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
     			+ " where EXISTS (select '' from processes a where ta.processname = a.processname and a.launchtype = 0 "
     			+ " and a.processname <> '拟办子流程' AND a.processname <> '办结子流程')) deptInfo on(main.processname = deptInfo.processname " 
     			+ " and main.incident = deptInfo.incident)"
     			+ "left join cs_user csu on csu.removed = 0 and csu.login_name = main.initiator) " ;
     
		
		
		
		StringBuffer sb = new StringBuffer();
		String jobSQL = "";
		 /**/
		sb.append(" select");
		sb.append(" '工作联系单' as pname,");
		sb.append(" '<div class=memo groupid='||groupid||'></div>' as memo,");
		sb.append(" tjcg.priorities,");
		sb.append(" '' as pincident,");
		sb.append(" '<div class=summary groupid='||groupid||' ptype=3 dbxtype=all>' ||tjcg.summary || '</div> ' as summary,");
		sb.append(" tjcg.initiator,");
		sb.append(" tjcg.start_time,");
		sb.append(" to_char(part1.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime,");
		sb.append(" case to_number(tjcg.status)");
		sb.append(" when 0 then");
		sb.append(" 'on'");
		sb.append(" when 1 then");
		sb.append(" 'off'");
		sb.append(" end as status,");
		sb.append(" to_number(tjcg.status) as pstatus,");
		sb.append(" tjcg.initiator_name as username,");
		sb.append(" part1.userendtime,");
		sb.append(" part1.groupid as groupid,");
		sb.append(" tjcg.create_deptname as deptname,'' cname ,'' cincident");
		sb.append(" from (select groupid,max(endtime) as endtime,max(userendtime) as userendtime");
		sb.append(" from (");

		sb.append("(");
		sb.append(" select part.pname,");
		sb.append(" part.pincident,");
		sb.append(" part.endtime,");
		sb.append(" part.userendtime,");
		sb.append("  tjcr.business_id as groupid ");
		
		sb.append(" from ((select t.processname as pname,");
		sb.append(" to_char(t.incident) as pincident,");
		sb.append(" t.processname as processname,");
		sb.append(" to_char(t.incident) as incident,");
		sb.append(" t.endtime as userendtime,");
		sb.append(" inci.endtime ");
		sb.append(" from tasks t, incidents inci");                
		sb.append(" where t.processname = '工作联系单'");
		sb.append(" and exists");
		sb.append(" (select ''");
		sb.append(" from t_job_contact tj");
		sb.append(" where t.processname = tj.processname");
		sb.append(" and t.incident = tj.instanceid");
		sb.append(" and tj.group_attribute = 1");
		sb.append(" and tj.removed != '1')");
		//sb.append(deptSql);
		//sb.append(" and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ");
		
		sb.append(" and t.assignedtouser like '%ST/%'");
		sb.append(" AND t.status = 3");
		sb.append(" and t.recipienttype <> 19");
		sb.append(" and t.assignedtouser = ?");
		//sb.append(" and t.assignedtouser = 'ST/"+loginName+"'");
		sb.append(" and t.processname = inci.processname");
		sb.append(" and t.incident = inci.incident");
		sb.append(" )");
		sb.append(" union");
		sb.append(" (select b.pname as pname,");
		sb.append(" b.pincident as pincident,");
		sb.append(" b.cname as processname,");
		sb.append(" b.cincident as incident,");
		sb.append(" t.endtime as userendtime,");
		sb.append(" inci.endtime");
		sb.append(" from t_subprocess b, tasks t,incidents inci");
		sb.append(" where b.cname = t.processname");
		sb.append(" and b.cincident = t.incident");
		sb.append(" and exists");
		sb.append(" (select ''");
		sb.append(" from t_job_contact tj");
		sb.append(" where b.pname = tj.processname");
		sb.append(" and b.pincident = tj.instanceid");
		sb.append(" and tj.group_attribute = 1");
		sb.append(" and tj.removed != '1')");
		sb.append(" and t.assignedtouser like '%ST/%'");
		sb.append(" and b.pname = '工作联系单'");
		sb.append(" and b.cname = '部门内部子流程'");
		//sb.append(deptSql);
		//sb.append(" and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ");
		
		
		sb.append(" AND t.status = 3");
		sb.append(" and t.recipienttype <> 19");
		sb.append(" and t.assignedtouser = ?");
		//sb.append(" and t.assignedtouser = 'ST/"+loginName+"'");
		sb.append(" and b.pname = inci.processname");
		sb.append(" and b.pincident = inci.incident");
		sb.append(" )) part ");
		
		sb.append("left join  t_job_contact tjc  on tjc.processname =part.pname and tjc.instanceid =part.pincident ");
		sb.append("left join t_job_contact_reference tjcr on tjcr.jc_id =tjc.id ");
		sb.append("where tjcr.business_table_name = 'T_JOB_CONTACT_GROUP'");
		sb.append(" and tjc.group_attribute = 1 ");
		sb.append("and tjc.removed != '1') ");
		sb.append("union");         
		
		sb.append("(");
		sb.append(" select '工作联系单' as pname,");
		sb.append(" '' as incident,");
		sb.append(" to_date(max(twr.operate_date), 'YYYY-MM-DD HH24:MI:SS') as endtime,");
		sb.append(" to_date(max(twr.operate_date), 'YYYY-MM-DD HH24:MI:SS') as userendtime,");
		sb.append(" twr.business_id");
		sb.append(" from t_job_contact_group tjcg ");
		sb.append(" right join t_wait_read twr on tjcg.id =twr.business_id where ");
		sb.append(" twr.removed != 1");
		sb.append(" and twr.status = '1'");
		//sb.append(" and twr.wr_user = 'ST/"+user+"'");
		sb.append(" and twr.wr_user = ?");
		//sb.append(" and twr.wr_user = '"+userId+"'");
		//sb.append(" and twr.wr_deptid = '"+userDeptId+"'");
		sb.append(" group by business_id");
		sb.append(" ))");
		sb.append(" group by groupid");
		sb.append(" ) part1,");
		sb.append(" t_job_contact_group tjcg");
		sb.append(" where part1.groupid = tjcg.id ");

		jobSQL = sb.toString();
		//System.out.println("1:"+strSQL1);
		//System.out.println("2:"+jobSQL);
		
		String todoItemsNewJobSql = gengerateTodoItemsNewJobSql(newloginName,loginArr,filter);
		String todoItemsNewSql = gengerateTodoItemsNewSql(newloginName,loginArr,filter);
		strSQL = "select * "
				+ " from (("+strSQL+")union all ("+jobSQL+")" +
				" union all ("+todoItemsNewJobSql+")" +
				" union all ("+todoItemsNewSql+")" +
								") where processname is not null";
		// order by endtime desc
		
		
		
		return strSQL;
		
	}

	
	public static String gengerateTodoItemsNewJobSql(String loginName,List<String> loginArr,List<Object> filter){
		String temp = "";
		for(int i=0;i<loginArr.size();i++){
			temp += "?,";
		}
		if(temp.length() > 0){
			temp = temp.substring(0,temp.length()-1);
		}
		filter.addAll(loginArr);
		String todoItemsNewJobSql = 
				" select processname," +
				" memo," +
				" priorities," +
				" incident," +
				" summary ," +
				" initiator,"+
				" starttime,"+
				" endtime,"+
				" status, "+
				" pstatus,"+ 
				" username,"+
				" to_date(userendtime, 'YYYY-MM-DD HH24:MI:SS') userendtime, "+
				" groupid,"+
				" deptname,"+
				" cname," +
				" cincident from ("+
				" select " +
				" tree.pname processname," +
				" '' memo," +
				" '' priorities," +
				" tree.pincident incident," +
				" main.theme summary ," +
				" main.initiator as initiator,"+
				" main.start_time as starttime,"+
                //" main.update_time as endtime,"+
                " to_char(i.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime,"+
				" case tree.status when 0 then 'on' when 1 then 'off' end as status, "+
				" tree.status pstatus,"+ 
				" main.initiator_name username,"+
				" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') userendtime,"+ 
				" main.id as groupid,"+
				" main.create_deptname as deptname,"+
				" tree.cname cname," +
				" tree.cincident cincident," +
				" row_number() over(partition by tree.pname || ':' || tree.pincident order by t.endtime desc) rn"+
				" from  "+
					" tasks t, "+
					" (select td.pname,td.pincident,td.cname,td.cincident,td.status from t_dept_contact_tree td  "+
					" where td.cname='部门内部子流程' and td.removed!=1 "+
					" union "+
					" select td.cname,td.cincident,td.cname,td.cincident,td.status from t_dept_contact_tree td  "+
					" where td.cname='多级工作联系单' and td.removed!=1 "+
					" ) tree, "+
					" t_dept_contact_main main,incidents i  "+
					" where 1=1  " +
					" and i.processname = tree.pname and i.incident = tree.pincident  "+
					" and i.status in (1,2) and t.status = 3 "+
					" and t.processname = tree.cname and t.incident = tree.cincident "+
					" and tree.pname = main.processname "+
					" and tree.pincident = main.incidentno "+
					" and t.assignedtouser in ("+temp+") " +
					" and t.steplabel != '"+"下级流程"+"') where rn=1";
		
		return todoItemsNewJobSql;
	}
	
	public static String gengerateTodoItemsNewSql(String loginName,List<String> loginArr,List<Object> filter){
		String temp = "";
		for(int i=0;i<loginArr.size();i++){
			temp += "?,";
		}
		if(temp.length() > 0){
			temp = temp.substring(0,temp.length()-1);
		}
		filter.addAll(loginArr);
		filter.addAll(loginArr);
		String todoItemsNewSql =
			" select processname,memo,"+
			" priorities, "+
			" incident, "+
			" summary, "+
			" initiator, "+
			" starttime, "+
			" endtime, "+
			" status, "+
			" pstatus, "+
			" username,"+
			" to_date(userendtime, 'YYYY-MM-DD HH24:MI:SS') userendtime, "+
			" groupid," +
			" deptname, cname,cincident from ("+	
			" select inci.processname,'' as memo,"+
			" case inci.priorities when '' then '' when '急件' then '<font color=''red''><b>' || inci.priorities || '</b></font>' end as priorities, "+
			" to_char(inci.incident) as incident, "+
			" inci.summary, "+
			" inci.initiator, "+
			" to_char(inci.starttime, 'yyyy-mm-dd hh24:mi:ss') starttime, "+
			" to_char(inci.endtime, 'yyyy-mm-dd hh24:mi:ss') endtime, "+
			" case inci.STATUS when 1 then 'on' when 2 then 'off' end as status, "+
			" inci.status as pstatus, "+
			" '' username,"+
			" part.endtime as userendtime, "+
			" part.taskid as groupid," +
			" '' deptname, part.cname,part.cincident, "+
			" row_number() over(partition by inci.processname || ':' || inci.incident order by part.endtime desc) rn"+
        	" from incidents inci,"+
        	" (" +
        	
        	" (select t.processname as pname, "+
        	" to_char(t.incident) as pincident, "+
        	" t.processname as cname, "+
        	" to_char(t.incident) as cincident, "+
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
        	//待修改 新发文流程
        	" and t.processname in ('变更事项审批流程','变更协议后审核流程','变更协议审批流程','新合同审批流程','新项目立项流程','项目销项流程','纪委收文流程','新发文流程','新党委发文流程','新纪委发文流程','新收呈批件','新收文流程','合同后审流程','部门内部审阅')"+
        	" and t.status = 3 "+
        	" and t.assignedtouser in ("+temp+") " +
        	" ) " +
        	
        	"union "+
        	
        	" (select b.pname as pname, "+
        	" b.pincident as pincident, "+
        	" b.cname as cname, "+
        	" b.cincident as cincident, "+
        	" t.steplabel, "+
        	" t.overduetime, "+
        	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
        	" t.taskid, "+
        	" t.assignedtouser "+
        	" from t_subprocess b, tasks t "+
        	" where b.cname = t.processname " +
        	//待修改 新发文流程
        	" and b.pname in ('变更事项审批流程','变更协议后审核流程','变更协议审批流程','新合同审批流程','新项目立项流程','项目销项流程','纪委收文流程','新发文流程','新党委发文流程','新纪委发文流程','新收呈批件','新收文流程','合同后审流程','部门内部审阅')"+
        	" and b.cincident = t.incident "+
        	" and not exists "+
        	" (select '' "+
        	" from processes a "+
        	" where t.processname = a.processname "+
        	" and a.launchtype = 0 "+
        	" and a.processname <> '拟办子流程' "+
        	" and a.processname <> '办结子流程' "+
        	" ) "+
        	" and t.status = 3 "+
        	" and t.assignedtouser in ("+temp+") " +
        	" )" +
        	
        	") part "+
        	
        	" where part.pname = inci.processname and inci.status in (1,2)"+
        	" and part.pincident = inci.incident) where rn=1";
		
		return todoItemsNewSql;
	}
	
	
	public static String generateSql(String sql, DoneItemVo vo,List<Object> filter){
		StringBuffer sb = new StringBuffer(sql);
		if(vo != null){
			String[] queryNameArr = {"processname","summary","starttime","endtime","username","deptname","status"};
			String[] queryTypeArr = {"textType","textType","dateType","dateType","textType","textType","selectType"};
			String[] queryResultArr = {vo.getProcessname(),vo.getSummary(),vo.getStarttime(),vo.getEndtime(),
									vo.getUsername(),vo.getDeptname(),vo.getStatus()};
			for (int i = 0; i < queryNameArr.length; i++) {
				String type = StringUtil.getNotNullValueString(queryTypeArr[i]);
				String name = StringUtil.getNotNullValueString(queryNameArr[i]);
				String value = StringUtil.getNotNullValueString(queryResultArr[i]);
				if (queryResultArr == null || value.equals("") || value.equals("#")) {

				} else {
					if (type.equals("textType")) {
						sb.append(" and " + name + " LIKE ?");
						filter.add("%"+queryResultArr[i]+"%");
					} else if (type.equals("dateType")) {
						// 查询条件为日期的有可能只选一个开始时间或一个结束时间
						String start = "";
						String end = "";
						try {
							start = StringUtil.getNotNullValueString(value.split("#")[0]);
							end = StringUtil.getNotNullValueString(value.split("#")[1]);
						} catch (Exception e) {}

						if (start.length() > 0) {
							sb.append(" and " + name + "" + " >= ?");
							filter.add(start);
						}
						if (end.length() > 0) {
							sb.append(" and " + name + "" + " <= ?");
							filter.add(end);
						}

					} else if (type.equals("selectType")) {
						sb.append(" and " + name + " = ?");
						filter.add(value);
					}
				}
			}
			
			//sql = PageUtils.generateSQLByType(sql, queryNameArr, queryResultArr, queryTypeArr);
			sb.append( " order by userendtime desc");
		}
		//System.out.println("zs++"+sql);
		return sb.toString();
	}
	
	
	
	
	public static String generateSqlThisYear(String userId, String newloginName,String loginName,
			String userName,String userDeptId,String size,List<String> loginArr,List<Object> filter,long days){
		filter.add("ST/"+loginName);
		filter.add(days);
		filter.add("ST/"+loginName);
		filter.add(days);
		filter.add(days);
		filter.add("ST/"+loginName);
		filter.add(days);
		filter.add("ST/"+loginName);
		filter.add(userId);
		
		
		String deptSql = "";
		//deptSql = " and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ";		
		
		String strSQL = " select inci.processname,'' as memo,"+
		" case inci.priorities when '' then '' when '急件' then '<font color=''red''><b>' || inci.priorities || '</b></font>' end as priorities, "+
		" to_char(inci.incident) as incident, "+
		" inci.summary, "+
		" inci.initiator, "+
		" to_char(inci.starttime, 'yyyy-mm-dd hh24:mi:ss') starttime, "+
		" to_char(inci.endtime, 'yyyy-mm-dd hh24:mi:ss') endtime, "+
		" case inci.STATUS when 1 then 'on' when 2 then 'off' end as status, "+
		" inci.status as pstatus, "+
		" part1.endtime as userendtime, "+
		" part1.taskid as taskid,part1.cname,part1.cincident "+
		" from (select cname,cincident, taskid, processname, incident, endtime"+
		" from (select cname,cincident,taskid, processname, "+
		" incident, "+
		" endtime, "+
		" row_number() over(partition by processname || ':' || incident order by endtime desc) rn "+
		" from ((select t.taskid,t.processname processname, "+
		" to_char(t.incident) incident, t.processname cname,to_char(t.incident) cincident,"+
		" t.endtime "+
		" from tasks t "+
		" where t.assignedtouser = ? and t.endtime > sysdate-? "+//今年
		//" where t.assignedtouser = 'ST/"+loginName+"' "+
		deptSql +//暂时
		" AND EXISTS "+
		" (select '' "+
		" from processes a "+
		" where T.processname = a.processname "+
		" and a.launchtype = 0 "+
		" and a.processname <> '拟办子流程' "+
		" AND a.processname <> '办结子流程') "+
		
		" and not exists(" +
    	" select ''" +
    	" from t_job_contact tj" +
    	" where t.processname = tj.processname" +
    	" and t.incident = tj.instanceid " +
    	" and tj.group_attribute =1" +
    	" )"+
		
		
		" AND t.status = 3" +
		" and t.recipienttype <>19) union "+
		" (select t.taskid, b.pname processname, "+
		" b.pincident incident, b.cname,b.cincident,"+
		" t.endtime "+
		" from T_SUBPROCESS b, tasks t "+
		" where b.cname = t.processname "+
		" and b.cincident = t.incident "+
		" and t.assignedtouser = ? and t.endtime > sysdate-? "+//今年
		//" and t.assignedtouser = 'ST/"+loginName+"' "+
		deptSql +//暂时
		" AND NOT EXISTS "+
		" (select '' "+
		" from processes a "+
		" where T.processname = a.processname "+
		" and a.launchtype = 0 "+
		" and a.processname <> '拟办子流程' "+
		" AND a.processname <> '办结子流程') "+
		
		" and not exists(" +
    	" select ''" +
    	" from t_job_contact tj" +
    	" where b.pname = tj.processname" +
    	" and b.pincident = tj.instanceid " +
    	" and tj.group_attribute =1" +
    	" )"+
		
		" AND t.status = 3" +
		" and t.recipienttype <>19))) "+
		" where rn = 1) part1, "+
		" (select i.processname processname, "+
		" i.incident, "+
		" subStr(i.initiator, 4, length(i.initiator)) initiator, "+
		" i.status, "+
		" i.starttime, "+
		" i.endtime, "+
		" i.summary summary, "+
		" i.priorities "+
		" from incidents i "+
		" where i.status = 1 "+
		" or i.status = 2 "+
		" OR I.STATUS = 8) inci "+
		" where part1.processname = inci.processname "+
		" and part1.incident = inci.incident ";
		
		strSQL = "select main.processname processname,main.memo,main.priorities,main.incident,main.summary, main.initiator," +
				"main.starttime,main.endtime,main.status,main.pstatus,csu.name username,"+
				"main.userendtime userendtime,main.taskid as groupid,deptInfo.deptName,main.cname,main.cincident from (("
     			+strSQL
     			+ ") main "
     			+ "inner join  ( select ta.processname, ta.incident,"
     			+ " co.name deptName from tasks ta left join cs_organ_node co on  replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = to_char(co.id) "
     			+ " right join ( select  processname, processversion, steplabel from processsteps " 
     			+ " where stepid like '%B' ) tb "
     			+ " on ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
     			+ " where EXISTS (select '' from processes a where ta.processname = a.processname and a.launchtype = 0 "
     			+ " and a.processname <> '拟办子流程' AND a.processname <> '办结子流程')) deptInfo on(main.processname = deptInfo.processname " 
     			+ " and main.incident = deptInfo.incident)"
     			+ "left join cs_user csu on csu.removed = 0 and csu.login_name = main.initiator) " ;
     
		
		
		
		StringBuffer sb = new StringBuffer();
		String jobSQL = "";
		 /**/
		sb.append(" select");
		sb.append(" '工作联系单' as pname,");
		sb.append(" '<div class=memo groupid='||groupid||'></div>' as memo,");
		sb.append(" tjcg.priorities,");
		sb.append(" '' as pincident,");
		sb.append(" '<div class=summary groupid='||groupid||' ptype=3 dbxtype=all>' ||tjcg.summary || '</div> ' as summary,");
		sb.append(" tjcg.initiator,");
		sb.append(" tjcg.start_time,");
		sb.append(" to_char(part1.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime,");
		sb.append(" case to_number(tjcg.status)");
		sb.append(" when 0 then");
		sb.append(" 'on'");
		sb.append(" when 1 then");
		sb.append(" 'off'");
		sb.append(" end as status,");
		sb.append(" to_number(tjcg.status) as pstatus,");
		sb.append(" tjcg.initiator_name as username,");
		sb.append(" part1.userendtime,");
		sb.append(" part1.groupid as groupid,");
		sb.append(" tjcg.create_deptname as deptname,'' cname ,'' cincident");
		sb.append(" from (select groupid,max(endtime) as endtime,max(userendtime) as userendtime");
		sb.append(" from (");

		sb.append("(");
		sb.append(" select part.pname,");
		sb.append(" part.pincident,");
		sb.append(" part.endtime,");
		sb.append(" part.userendtime,");
		sb.append("  tjcr.business_id as groupid ");
		
		sb.append(" from ((select t.processname as pname,");
		sb.append(" to_char(t.incident) as pincident,");
		sb.append(" t.processname as processname,");
		sb.append(" to_char(t.incident) as incident,");
		sb.append(" t.endtime as userendtime,");
		sb.append(" inci.endtime ");
		sb.append(" from tasks t, incidents inci");                
		sb.append(" where t.processname = '工作联系单' and t.endtime > sysdate-? ");//今年
		sb.append(" and exists");
		sb.append(" (select ''");
		sb.append(" from t_job_contact tj");
		sb.append(" where t.processname = tj.processname");
		sb.append(" and t.incident = tj.instanceid");
		sb.append(" and tj.group_attribute = 1");
		sb.append(" and tj.removed != '1')");
		//sb.append(deptSql);
		//sb.append(" and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ");
		
		sb.append(" and t.assignedtouser like '%ST/%'");
		sb.append(" AND t.status = 3");
		sb.append(" and t.recipienttype <> 19");
		sb.append(" and t.assignedtouser = ?");
		//sb.append(" and t.assignedtouser = 'ST/"+loginName+"'");
		sb.append(" and t.processname = inci.processname");
		sb.append(" and t.incident = inci.incident");
		sb.append(" )");
		sb.append(" union");
		sb.append(" (select b.pname as pname,");
		sb.append(" b.pincident as pincident,");
		sb.append(" b.cname as processname,");
		sb.append(" b.cincident as incident,");
		sb.append(" t.endtime as userendtime,");
		sb.append(" inci.endtime");
		sb.append(" from t_subprocess b, tasks t,incidents inci");
		sb.append(" where b.cname = t.processname and t.endtime > sysdate-? ");//今年
		sb.append(" and b.cincident = t.incident");
		sb.append(" and exists");
		sb.append(" (select ''");
		sb.append(" from t_job_contact tj");
		sb.append(" where b.pname = tj.processname");
		sb.append(" and b.pincident = tj.instanceid");
		sb.append(" and tj.group_attribute = 1");
		sb.append(" and tj.removed != '1')");
		sb.append(" and t.assignedtouser like '%ST/%'");
		sb.append(" and b.pname = '工作联系单'");
		sb.append(" and b.cname = '部门内部子流程'");
		//sb.append(deptSql);
		//sb.append(" and (instr(t.helpurl,'ST/" + loginName + ":"+userDeptId+"<+>') >0 or t.helpurl is null or instr(t.helpurl,'ST/" + loginName + ":<+>') >0) ");
		
		
		sb.append(" AND t.status = 3");
		sb.append(" and t.recipienttype <> 19");
		sb.append(" and t.assignedtouser = ?");
		//sb.append(" and t.assignedtouser = 'ST/"+loginName+"'");
		sb.append(" and b.pname = inci.processname");
		sb.append(" and b.pincident = inci.incident");
		sb.append(" )) part ");
		
		sb.append("left join  t_job_contact tjc  on tjc.processname =part.pname and tjc.instanceid =part.pincident ");
		sb.append("left join t_job_contact_reference tjcr on tjcr.jc_id =tjc.id ");
		sb.append("where tjcr.business_table_name = 'T_JOB_CONTACT_GROUP'");
		sb.append(" and tjc.group_attribute = 1 ");
		sb.append("and tjc.removed != '1') ");
		sb.append("union");         
		
		sb.append("(");
		sb.append(" select '工作联系单' as pname,");
		sb.append(" '' as incident,");
		sb.append(" to_date(max(twr.operate_date), 'YYYY-MM-DD HH24:MI:SS') as endtime,");
		sb.append(" to_date(max(twr.operate_date), 'YYYY-MM-DD HH24:MI:SS') as userendtime,");
		sb.append(" twr.business_id");
		sb.append(" from t_job_contact_group tjcg ");
		sb.append(" right join t_wait_read twr on tjcg.id =twr.business_id where ");
		sb.append(" twr.removed != 1");
		sb.append(" and twr.status = '1'");
		//sb.append(" and twr.wr_user = 'ST/"+user+"'");
		sb.append(" and twr.wr_user = ?");
		//sb.append(" and twr.wr_user = '"+userId+"'");
		//sb.append(" and twr.wr_deptid = '"+userDeptId+"'");
		sb.append(" group by business_id");
		sb.append(" ))");
		sb.append(" group by groupid");
		sb.append(" ) part1,");
		sb.append(" t_job_contact_group tjcg");
		sb.append(" where part1.groupid = tjcg.id ");

		jobSQL = sb.toString();
		//System.out.println("1:"+strSQL1);
		//System.out.println("2:"+jobSQL);
		
		String todoItemsNewJobSql = gengerateTodoItemsNewJobSqlThisYear(newloginName,loginArr,filter,days);
		String todoItemsNewSql = gengerateTodoItemsNewSqlThisYear(newloginName,loginArr,filter,days);
		strSQL = "select * "
				+ " from (("+strSQL+")union all ("+jobSQL+")" +
				" union all ("+todoItemsNewJobSql+")" +
				" union all ("+todoItemsNewSql+")" +
								") where processname is not null";
		// order by endtime desc
		
		
		
		return strSQL;
		
	}
	
	public static String gengerateTodoItemsNewJobSqlThisYear(String loginName,List<String> loginArr,List<Object> filter,long days){
		String temp = "";
		for(int i=0;i<loginArr.size();i++){
			temp += "?,";
		}
		if(temp.length() > 0){
			temp = temp.substring(0,temp.length()-1);
		}
		filter.add(days);
		filter.addAll(loginArr);
		String todoItemsNewJobSql = 
				" select processname," +
				" memo," +
				" priorities," +
				" incident," +
				" summary ," +
				" initiator,"+
				" starttime,"+
				" endtime,"+
				" status, "+
				" pstatus,"+ 
				" username,"+
				" to_date(userendtime, 'YYYY-MM-DD HH24:MI:SS') userendtime, "+
				" groupid,"+
				" deptname,"+
				" cname," +
				" cincident from ("+
				" select " +
				" tree.pname processname," +
				" '' memo," +
				" '' priorities," +
				" tree.pincident incident," +
				" main.theme summary ," +
				" main.initiator as initiator,"+
				" main.start_time as starttime,"+
                //" main.update_time as endtime,"+
                " to_char(i.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime,"+
				" case tree.status when 0 then 'on' when 1 then 'off' end as status, "+
				" tree.status pstatus,"+ 
				" main.initiator_name username,"+
				" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') userendtime,"+ 
				" main.id as groupid,"+
				" main.create_deptname as deptname,"+
				" tree.cname cname," +
				" tree.cincident cincident," +
				" row_number() over(partition by tree.pname || ':' || tree.pincident order by t.endtime desc) rn"+
				" from  "+
					" tasks t, "+
					" (select td.pname,td.pincident,td.cname,td.cincident,td.status from t_dept_contact_tree td  "+
					" where td.cname='部门内部子流程' and td.removed!=1 "+
					" union "+
					" select td.cname,td.cincident,td.cname,td.cincident,td.status from t_dept_contact_tree td  "+
					" where td.cname='多级工作联系单' and td.removed!=1 "+
					" ) tree, "+
					" t_dept_contact_main main,incidents i  "+
					" where 1=1  " +
					" and i.processname = tree.pname and i.incident = tree.pincident  "+
					" and i.status in (1,2) and t.status = 3 and t.endtime > sysdate-? "+//今年
					" and t.processname = tree.cname and t.incident = tree.cincident "+
					" and tree.pname = main.processname "+
					" and tree.pincident = main.incidentno "+
					" and t.assignedtouser in ("+temp+") " +
					" and t.steplabel != '"+"下级流程"+"') where rn=1";
		
		return todoItemsNewJobSql;
	}
	
	public static String gengerateTodoItemsNewSqlThisYear(String loginName,List<String> loginArr,List<Object> filter,long days){
		String temp = "";
		for(int i=0;i<loginArr.size();i++){
			temp += "?,";
		}
		if(temp.length() > 0){
			temp = temp.substring(0,temp.length()-1);
		}
		filter.add(days);
		filter.addAll(loginArr);
		filter.add(days);
		filter.addAll(loginArr);
		String todoItemsNewSql =
			" select processname,memo,"+
			" priorities, "+
			" incident, "+
			" summary, "+
			" initiator, "+
			" starttime, "+
			" endtime, "+
			" status, "+
			" pstatus, "+
			" username,"+
			" to_date(userendtime, 'YYYY-MM-DD HH24:MI:SS') userendtime, "+
			" groupid," +
			" deptname, cname,cincident from ("+	
			" select inci.processname,'' as memo,"+
			" case inci.priorities when '' then '' when '急件' then '<font color=''red''><b>' || inci.priorities || '</b></font>' end as priorities, "+
			" to_char(inci.incident) as incident, "+
			" inci.summary, "+
			" inci.initiator, "+
			" to_char(inci.starttime, 'yyyy-mm-dd hh24:mi:ss') starttime, "+
			" to_char(inci.endtime, 'yyyy-mm-dd hh24:mi:ss') endtime, "+
			" case inci.STATUS when 1 then 'on' when 2 then 'off' end as status, "+
			" inci.status as pstatus, "+
			" '' username,"+
			" part.endtime as userendtime, "+
			" part.taskid as groupid," +
			" '' deptname, part.cname,part.cincident, "+
			" row_number() over(partition by inci.processname || ':' || inci.incident order by part.endtime desc) rn"+
        	" from incidents inci,"+
        	" (" +
        	
        	" (select t.processname as pname, "+
        	" to_char(t.incident) as pincident, "+
        	" t.processname as cname, "+
        	" to_char(t.incident) as cincident, "+
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
        	//待修改 新发文流程
        	" and t.processname in ('变更事项审批流程','变更协议后审核流程','变更协议审批流程','新合同审批流程','新项目立项流程','项目销项流程','纪委收文流程','新发文流程','新党委发文流程','新纪委发文流程','新收呈批件','新收文流程','合同后审流程','部门内部审阅')"+
        	" and t.status = 3 and t.endtime > sysdate-? "+//今年
        	" and t.assignedtouser in ("+temp+") " +
        	" ) " +
        	
        	"union "+
        	
        	" (select b.pname as pname, "+
        	" b.pincident as pincident, "+
        	" b.cname as cname, "+
        	" b.cincident as cincident, "+
        	" t.steplabel, "+
        	" t.overduetime, "+
        	" to_char(t.endtime, 'yyyy-mm-dd hh24:mi:ss') as endtime, "+
        	" t.taskid, "+
        	" t.assignedtouser "+
        	" from t_subprocess b, tasks t "+
        	" where b.cname = t.processname " +
        	//待修改 新发文流程
        	" and b.pname in ('变更事项审批流程','变更协议后审核流程','变更协议审批流程','新合同审批流程','新项目立项流程','项目销项流程','纪委收文流程','新发文流程','新党委发文流程','新纪委发文流程','新收呈批件','新收文流程','合同后审流程','部门内部审阅')"+
        	" and b.cincident = t.incident "+
        	" and not exists "+
        	" (select '' "+
        	" from processes a "+
        	" where t.processname = a.processname "+
        	" and a.launchtype = 0 "+
        	" and a.processname <> '拟办子流程' "+
        	" and a.processname <> '办结子流程' "+
        	" ) "+
        	" and t.status = 3 and t.endtime > sysdate-? "+//今年
        	" and t.assignedtouser in ("+temp+") " +
        	" )" +
        	
        	") part "+
        	
        	" where part.pname = inci.processname and inci.status in (1,2)"+
        	" and part.pincident = inci.incident) where rn=1";
		
		return todoItemsNewSql;
	}
}
