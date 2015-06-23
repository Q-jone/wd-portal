/**   
* @Title: ItemDelayUtil.java 
* @Package com.wonders.stpt.itemDelay.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年5月26日 下午2:56:33 
* @version V1.0   
*/
package com.wonders.stpt.itemDelay.util;

/** 
 * @ClassName: ItemDelayUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年5月26日 下午2:56:33 
 *  
 */
public class ItemDelayUtil {
	
	public static String getOverSql(){
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (");
		sb.append(getRecvSql());
		sb.append(") union (");
		sb.append(getRedvSql());
		sb.append(")");
		return sb.toString();
	}
	
	private static String getRecvSql(){
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" distinct ");
		sb.append(" t.activeid type ,t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime,");
		sb.append(" substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId,");
		sb.append(" case when ");
		sb.append(" sysdate-i.starttime < 3");
		sb.append(" then ");
		sb.append(" ''");
		sb.append(" else");
		sb.append(" round(sysdate-i.starttime-3)||''");
		sb.append(" end overtime ,");
		sb.append(" s.pname pname,s.pincident,s.cname ,s.cincident");
		sb.append(" from t_doc_receive t,");
		sb.append(" t_subprocess s , incidents i , tasks k ");
		sb.append(" where t.priorities='急件' and t.flag = 0 and ");
		sb.append(" t.removed=0 and t.activeid in ('收文流程','新收文流程')");
		sb.append(" and t.activeid=s.pname and t.instanceid=s.pincident and s.cname='部门内部子流程' ");
		sb.append(" and i.status=1 and i.processname=s.cname and i.incident=s.cincident");
		sb.append(" and k.status=1 and k.assignedtouser like 'ST/%' and ");
		sb.append(" k.processname=s.cname and k.incident=s.cincident");
	
		return sb.toString();
	}
	
	private static String getRedvSql(){
		StringBuffer sb = new StringBuffer();
		sb.append("select");
		sb.append(" distinct ");
		sb.append(" t.activeid type ,t.title , to_char(i.starttime,'yyyy-mm-dd') beginTime,");
		sb.append(" substr(k.helpurl,instr(k.helpurl,':')+1,instr(k.helpurl,'<+>')-instr(k.helpurl,':')-1) deptId,");
		sb.append(" case when ");
		sb.append(" sysdate-i.starttime < 3");
		sb.append(" then ");
		sb.append(" ''");
		sb.append(" else");
		sb.append(" round(sysdate-i.starttime-3)||''");
		sb.append(" end overtime ,");
		sb.append(" s.pname pname,s.pincident,s.cname ,s.cincident");
		sb.append(" from t_doc_receive t,");
		sb.append(" t_subprocess s , incidents i , tasks k ");
		sb.append(" where t.doclevel in ('加急','紧急') and t.flag = 0 and ");
		sb.append(" t.removed=0 and  t.activeid in ('收呈批件','新收呈批件')" );
		sb.append(" and t.activeid=s.pname and t.processinstanceid=s.pincident and s.cname='部门内部子流程' ");
		sb.append(" and i.status=1 and i.processname=s.cname and i.incident=s.cincident");
		sb.append(" and k.status=1 and k.assignedtouser like 'ST/%' and ");
		sb.append(" k.processname=s.cname and k.incident=s.cincident");
	
		return sb.toString();
	}
}
