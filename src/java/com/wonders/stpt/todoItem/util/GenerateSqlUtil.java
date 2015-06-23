package com.wonders.stpt.todoItem.util;


import com.wonders.stpt.page.util.PageUtils;
import com.wonders.stpt.todoItem.model.vo.TodoItemVo;

public class GenerateSqlUtil {
	public static String strSQLCount = null;
	public static String strSQLPage = null;
	public static String newSql = "";
	public static String todoOldSql = "";
	public static String todoOldJobSql = "";
	//与工作联系单合并
	//public static String todoOldJobDyxSql = "";
	public static String todoNewSql = "";
	public static String todoNewJobSql = "";
	public static void main(String[] args){
		System.out.println(generateSql("11772","G010060005132510","G01006000513","忻然","2111","",null));
	}
	
	public static String generateSql(String userId,String loginName,String ologinName,String userName,String deptId,
			String size,TodoItemVo vo){
		newSql = 
				"select * from ( " +
				"("+
				generateOldSql(ologinName,userName,deptId)
				+") union ("+
				generateOldJobSql(userId,ologinName,userName,deptId)
				+") union ("+
				generateNewSql(loginName,userName)
				+") union ("+
				generateNewJobSql(loginName,userName)
				+")" +
				" )";
		//System.out.println(todoItemsSql);
		return newSql;
	}
	
	public static String generateOldSql(String loginName,String userName,String deptId){
		todoOldSql =
	        	" select t.title summary, "+
	        	" '' as memo,"+
	        	" '' as priorities, " +
	        	" '' as priorities_show, "+
	        	" t.pname,t.pincident,t.cname as processname," +
	        	" t.cincident as incident,t.stepname as steplabel," +
	         	" '' overduetime ,t.occurtime as endtime," +
	         	" t.taskid,t.loginname as assignedtouser," +
	        	" '0' as task_type, "+
	        	" '1' as 连接字符串, "+
	        	" t.initiator, "+
	        	" c.name as apply_username, "+
	        	" '"+userName+"' as taskuser_name, "+
	        	" 'ST/"+loginName+"' as UserName,"+
	        	" '' deptname"+
	        	" "+
	        	"from t_todo_item t,cs_user c" +
	        	" where t.status=0 and t.removed =0 " +
	        	" and 'ST/'||c.login_name = t.initiator and c.removed=0" +
	        	" and t.type=1 and t.typename !='工作联系单' " +
	        	" and t.deptid = '"+deptId+"' and t.loginname='"+"ST/"+loginName+"'";
	        	
	                
//			todoOldSql = "select main.*,deptInfo.deptName from (("
//	        		+todoOldSql
//	        		+ ") main inner join  ( select ta.processname, ta.incident,"
//	        		+ "(select co.name from cs_organ_node co where replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = co.id ) as deptName "
//	        		+ " from tasks ta, (select distinct processname, processversion, steplabel from processsteps where stepid like '%B' ) tb "
//	        		+ " where ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
//	        		+ ")deptInfo on(main.pname = deptInfo.processname and main.pincident = to_char(deptInfo.incident)))" ;
//	        
			return todoOldSql;
	}
	
	public static String generateOldJobSql(String userId, String loginName,String userName,String deptId){
		todoOldJobSql = 
				" select " +
				"'<div class=summary groupid='||t.taskid||' ptype='||t.tasktype||' dbxtype='||t.dbxtype||'>'||t.title||'</div> ' as summary,"+
				"'<div class=memo dbxtype='||t.dbxtype||' groupid='||t.taskid||'></div>' as memo,"+
	        	" '' as priorities, " +
	        	" '' as priorities_show, "+
	        	" t.pname,t.pincident,t.cname as processname," +
	        	" t.cincident as incident," +
	        	"'<div class=steplabel dbxtype='||t.dbxtype||' groupid='||t.taskid||'></div>' as steplabel,"+
	         	" '' overduetime ,t.occurtime as endtime," +
	         	" t.taskid,t.loginname as assignedtouser," +
	        	" '0' as task_type, "+
	        	" '1' as 连接字符串, "+
	        	" t.initiator, "+
	        	" c.name as apply_username, "+
	        	" '"+userName+"' as taskuser_name, "+
	        	" 'ST/"+loginName+"' as UserName,"+
	        	" t.initdept as deptname"+
	        	" "+
	        	"from t_todo_item t,cs_user c" +
	        	" where t.status=0 and t.removed =0 " +
	        	" and 'ST/'||c.login_name = t.initiator and c.removed=0" +
	        	" and t.type=1 and t.typename ='工作联系单'" +
	        	" and t.deptid = '"+deptId+"' " +
	        	" and (t.loginname='"+"ST/"+loginName+"' or t.userId='"+userId+"')";
		
		return todoOldJobSql;
	}
	
	public static String generateNewJobSql(String loginName,String userName){
		todoNewJobSql =
	        	" select t.title summary, "+
	        	" '' as memo,"+
	        	" '' as priorities, " +
	        	" '' as priorities_show, "+
	        	" t.pname,t.pincident,t.cname as processname," +
	        	" t.cincident as incident,t.stepname as steplabel," +
	         	" '' overduetime ,t.occurtime as endtime," +
	         	" t.taskid,t.loginname as assignedtouser," +
	        	" '0' as task_type, "+
	        	" '1' as 连接字符串, "+
	        	" t.initiator, "+
	        	" t.initiator as apply_username, "+
	        	" '"+userName+"' as taskuser_name, "+
	        	" 'ST/"+loginName+"' as UserName,"+
	        	" t.initdept as deptname"+
	        	" "+
	        	"from t_todo_item t" +
	        	" where t.status=0 and t.removed =0 " +
	        	" and t.type=0 and t.typename ='多级工作联系单' " +
	        	" and t.loginname='"+"ST/"+loginName+"'";
	        		        
			return todoNewJobSql;
	}
	
	public static String generateNewSql(String loginName,String userName){
		todoNewSql =
	        	" select t.title summary, "+
	        	" '' as memo,"+
	        	" '' as priorities, " +
	        	" '' as priorities_show, "+
	        	" t.pname,t.pincident,t.cname as processname," +
	        	" t.cincident as incident,t.stepname as steplabel," +
	         	" '' overduetime ,t.occurtime as endtime," +
	         	" t.taskid,t.loginname as assignedtouser," +
	        	" '0' as task_type, "+
	        	" '1' as 连接字符串, "+
	        	" t.initiator, "+
	        	" c.name as apply_username, "+
	        	" '"+userName+"' as taskuser_name, "+
	        	" 'ST/"+loginName+"' as UserName,"+
	        	" '' deptname"+
	        	" "+
	        	"from t_todo_item t,cs_user c" +
	        	" where t.status=0 and t.removed =0 " +
	        	" and 'ST/'||c.login_name = substr(t.initiator,0,15) and c.removed=0" +
	        	" and t.type=0 and t.typename in ('新发文流程','新党委发文流程','新纪委发文流程','新收文流程','新收呈批件')" +
	        	" and t.loginname='"+"ST/"+loginName+"'";
	        	
	                
//			todoOldSql = "select main.*,deptInfo.deptName from (("
//	        		+todoOldSql
//	        		+ ") main inner join  ( select ta.processname, ta.incident,"
//	        		+ "(select co.name from cs_organ_node co where replace(substr(ta.helpurl, instr(ta.helpurl, ':') + 1), '<+>', '') = co.id ) as deptName "
//	        		+ " from tasks ta, (select distinct processname, processversion, steplabel from processsteps where stepid like '%B' ) tb "
//	        		+ " where ta.processname = tb.processname and ta.processversion = tb.processversion and ta.steplabel = tb.steplabel "
//	        		+ ")deptInfo on(main.pname = deptInfo.processname and main.pincident = to_char(deptInfo.incident)))" ;
//	 
	        		        
			return todoNewSql;
	}
	
	
	public static String generateSql(String loginNames, TodoItemVo vo){
		String sql = " select t.title, "+
	        	" t.pname," +
	        	" t.pincident," +
	        	" t.cname," +
	        	" t.cincident ," +
	        	" t.stepname ," +
	         	" t.occurtime," +
	         	" t.taskid,"+
	        	" t.tasktype, "+
	        	" t.loginname,"+
	        	" t.url"+
	        	" from t_todo_item t" +
	        	" where t.status=0 and t.removed =0 " +
	        	" and t.loginname in ("+loginNames+")";
		if(vo != null){
			String[] queryNameArr = {"pname","title","deptname","occurtime"};
			String[] queryTypeArr = {"textType","textType","textType","dateType"};
			String[] queryResultArr = {vo.getTypename(),vo.getTitle(),vo.getApplydept(),vo.getOccurtime()};
			sql = PageUtils.generateSQLByType(sql, queryNameArr, queryResultArr, queryTypeArr);
			sql += " order by occurtime desc";
		}
		//System.out.println("zs++"+sql);
		return sql;
	}
	
	public static String generateReviewSql(String loginNames, TodoItemVo vo){
		String sql = " select t.title, "+
	        	" t.pname," +
	        	" t.pincident," +
	        	" t.cname," +
	        	" t.cincident ," +
	        	" t.stepname ," +
	         	" t.occurtime," +
	         	" t.taskid,"+
	        	" t.tasktype, "+
	        	" t.loginname,"+
	        	" t.url"+
	        	" from t_todo_item t" +
	        	" where t.status=0 and t.removed =0 and t.pname='合同后审流程'" +
	        	" and t.loginname in ("+loginNames+")";
		if(vo != null){
			String[] queryNameArr = {"pname","title","deptname","occurtime"};
			String[] queryTypeArr = {"textType","textType","textType","dateType"};
			String[] queryResultArr = {vo.getTypename(),vo.getTitle(),vo.getApplydept(),vo.getOccurtime()};
			sql = PageUtils.generateSQLByType(sql, queryNameArr, queryResultArr, queryTypeArr);
			sql += " order by occurtime desc";
		}
		//System.out.println("zs++"+sql);
		return sql;
	}
	
	public static String generateElseSql(String loginNames, TodoItemVo vo){
		String sql = " select t.title, "+
	        	" t.pname," +
	        	" t.pincident," +
	        	" t.cname," +
	        	" t.cincident ," +
	        	" t.stepname ," +
	         	" t.occurtime," +
	         	" t.taskid,"+
	        	" t.tasktype, "+
	        	" t.loginname,"+
	        	" t.url"+
	        	" from t_todo_item t" +
	        	" where t.status=0 and t.removed =0 and t.pname !='合同后审流程'" +
	        	" and t.loginname in ("+loginNames+")";
		if(vo != null){
			String[] queryNameArr = {"pname","title","deptname","occurtime"};
			String[] queryTypeArr = {"textType","textType","textType","dateType"};
			String[] queryResultArr = {vo.getTypename(),vo.getTitle(),vo.getApplydept(),vo.getOccurtime()};
			sql = PageUtils.generateSQLByType(sql, queryNameArr, queryResultArr, queryTypeArr);
			sql += " order by occurtime desc";
		}
		//System.out.println("zs++"+sql);
		return sql;
	}
	

}
