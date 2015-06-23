/**
 * 
 */
package com.wonders.stpt.todoItem.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.wonders.stpt.util.DbUtil;
import com.wonders.stpt.util.StringUtil;

/** 
 * @ClassName: JobContactUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-3-4 下午02:33:39 
 *  
 */
@Service("jobContactUtil")
public class JobContactUtil {
	private static DbUtil dbUtil;


	public static DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		JobContactUtil.dbUtil = dbUtil;
	}

	/**
	 * 返回工作联系单流程情况集合

	 * 
	 * @param groupId
	 * @param loginName
	 * @param deptId
	 * @param status
	 * @return
	 */
	public static List<Map<String, String>> getListProcessInfo(String groupId, String loginName, String deptId, String status,
			String orderAndWhereStr) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		String sql = "";
		sql = "select * from ("
				+ " select j1.theme,p1.pname,p1.pincident,p1.cname,p1.cincident,p1.steplabel,j1.main_unit,j1.main_unit_id,j1.apply_deptid,p1.taskid,p1.status,'process' as dbxtype from( "
				+ " (select t.processname as pname, " + " to_char(t.incident) as pincident, " + " t.processname as cname,"
				+ " to_char(t.incident) as cincident," + " t.steplabel, " + " t.taskid," + " to_char(t.status) as status"
				+ " from tasks t " + " where t.processname = '工作联系单' " + " and exists " + " (select '' " + " from t_job_contact tj "
				+ " where t.processname = tj.processname " + " and t.incident = tj.instanceid "
				+ " and tj.group_attribute = 1 and tj.removed != '1') " + " and t.assignedtouser like '%ST/%' " + " and t.status = '"
				+ status
				+ "' "
				+ " and t.helpurl like '%"
				+ loginName
				+ ":"
				+ deptId
				+ "<+>%' "
				+ " and t.assignedtouser = '"
				+ loginName
				+ "') "
				+ " union "
				+ " (select b.pname as pname, "
				+ " b.pincident as pincident, "
				+ " b.cname as cname,"
				+ " b.cincident as cincident,"
				+ " t.steplabel,"
				+ " t.taskid,"
				+ " to_char(t.status) as status"
				+ " from t_subprocess b, tasks t "
				+ " where b.cname = t.processname "
				+ " and b.cincident = t.incident "
				+ " and exists "
				+ " (select '' "
				+ " from t_job_contact tj "
				+ " where b.pname = tj.processname "
				+ " and b.pincident = tj.instanceid "
				+ " and tj.group_attribute = 1 and tj.removed != '1') "
				+ " and t.assignedtouser like '%ST/%' "
				+ " and b.pname = '工作联系单' "
				+ " and b.cname = '部门内部子流程' "
				+ " and t.helpurl like '%"
				+ loginName
				+ ":"
				+ deptId
				+ "<+>%' "
				+ " and t.assignedtouser = '"
				+ loginName
				+ "' "
				+ " and t.status = '"
				+ status
				+ "')) p1, "
				+ " ( "
				+ " select * from t_job_contact tj where tj.id in ( "
				+ " select tjcr.jc_id from t_job_contact_reference tjcr where tjcr.business_id = '"
				+ groupId
				+ "' "
				+ " and tjcr.business_table_name = 'T_JOB_CONTACT_GROUP' "
				+ " )) j1 "
				+ " where p1.pname=j1.processname and p1.pincident = j1.instanceid " + " )" + orderAndWhereStr;

		// ;
		// System.out.println(sql);

		List<Map<String, Object>> objList = dbUtil.getJdbcTemplate().queryForList(sql);
		for (int i = 0; i < objList.size(); i++) {
			Map<String, Object> map = objList.get(i);

			Map<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("theme", StringUtil.getNotNullValueString(map.get("theme")));
			tmpMap.put("pname", StringUtil.getNotNullValueString(map.get("pname")));
			tmpMap.put("pincident", StringUtil.getNotNullValueString(map.get("pincident")));
			tmpMap.put("cname", StringUtil.getNotNullValueString(map.get("cname")));
			tmpMap.put("cincident", StringUtil.getNotNullValueString(map.get("cincident")));
			tmpMap.put("steplabel", StringUtil.getNotNullValueString(map.get("steplabel")));
			tmpMap.put("mainUnit", StringUtil.getNotNullValueString(map.get("main_unit")));
			tmpMap.put("mainUnitId", StringUtil.getNotNullValueString(map.get("main_unit_id")));
			tmpMap.put("applyDeptId", StringUtil.getNotNullValueString(map.get("apply_deptid")));
			tmpMap.put("taskId", StringUtil.getNotNullValueString(map.get("taskid")));
			tmpMap.put("groupId", groupId);
			tmpMap.put("deptId", deptId);
			list.add(tmpMap);

		}

		return list;
	}

	/**
	 * 待阅事项流程情况集合
	 * 
	 * @param groupId
	 * @param loginName
	 * @param deptId
	 * @param status
	 * @param orderAndWhereStr
	 * @return
	 */
	public static List<Map<String, String>> getListDyxInfo(String groupId, String loginName, String deptId, String status,
			String orderAndWhereStr) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		String sql = "";
		/**/
		sql = "select * from (select  tjcg.summary as theme, t.processname as pname, t.instanceid as pincident, "
				+ " '' as cname,'' as cincident,'传阅' as steplabel,t.main_unit as main_unit, "
				+ " twr.deptid as main_unit_id,t.apply_deptid,'' as taskid,twr.status as status, "
				+ " twr.roleflag as roleflag,'dyx' as dbxtype "
				+ " from t_job_contact_group tjcg "
				+ " ,"
				// "t_wait_read twr "
				+ " (select t.*,'ST/'||c.login_name as wr_user_loginName from t_wait_read t,cs_user c "
				+ " where t.wr_user = c.id and c.removed != '1') twr" + " ,t_job_contact_reference tjcr,t_job_contact t  " + " where  "
				+ " tjcg.id = twr.business_id and  " + " twr.removed != 1 and twr.status = '" + status + "' and  "
				+ " twr.wr_user_loginName='" + loginName + "' and twr.wr_deptid = '" + deptId + "' and  "
				+ " tjcr.business_table_name='T_JOB_CONTACT_GROUP' and  " + " tjcr.business_id=tjcg.id and  " + " tjcr.jc_id = t.id "
				+ " and t.main_unit_id= twr.deptid " + " and tjcg.id = '" + groupId + "'" + " )" + orderAndWhereStr;

		// ;
		
		//System.out.println(sql);
		List<Map<String,Object>> objList = dbUtil.getJdbcTemplate().queryForList(sql);
		for (int i = 0; i < objList.size(); i++) {
			Map<String, Object> map = objList.get(i);

			Map<String, String> tmpMap = new HashMap<String, String>();
			tmpMap.put("theme", StringUtil.getNotNullValueString(map.get("theme")));
			tmpMap.put("pname", StringUtil.getNotNullValueString(map.get("pname")));
			tmpMap.put("pincident", StringUtil.getNotNullValueString(map.get("pincident")));
			tmpMap.put("cname", StringUtil.getNotNullValueString(map.get("cname")));
			tmpMap.put("cincident", StringUtil.getNotNullValueString(map.get("cincident")));
			tmpMap.put("steplabel", StringUtil.getNotNullValueString(map.get("steplabel")));
			tmpMap.put("mainUnit", StringUtil.getNotNullValueString(map.get("main_unit")));
			tmpMap.put("mainUnitId", StringUtil.getNotNullValueString(map.get("main_unit_id")));
			tmpMap.put("applyDeptId", StringUtil.getNotNullValueString(map.get("apply_deptid")));
			tmpMap.put("taskId", StringUtil.getNotNullValueString(map.get("taskid")));
			tmpMap.put("roleFlag", StringUtil.getNotNullValueString(map.get("roleflag")));
			tmpMap.put("groupId", groupId);
			tmpMap.put("deptId", deptId);
			list.add(tmpMap);

		}

		return list;
	}
	
	
	/**
	 * 工作联系单催办信息

	 * 
	 * @param inciList
	 * @return
	 */
	public static List<String> jobContactCuibanInfo(List<String> inciList) {
		List<String> retList = new ArrayList<String>();
		if (inciList.size() == 0)
			return retList;

		String whereStr = generateWhereInClauseByStr(listToStringBySplit(inciList, ","), ",");

		String sql = "select distinct t.incident from incidents t where t.processname='工作联系单' and t.incident in (" + whereStr + ")"
				+ " and t.status='1'";

		List<Map<String,Object>> list = dbUtil.getJdbcTemplate().queryForList(sql);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			String inci = StringUtil.getNotNullValueString(map.get("incident"));
			retList.add(inci);
		}

		return retList;
	}
	
	/**
	 * 根据字符串生产HQL的where条件
	 * 
	 * @return
	 */
	public static String generateWhereInClauseByStr(String strs, String splitExp) {
		String ret = "";
		String str = StringUtil.getNotNullValueString(strs);

		if (str.length() > 0) {
			if (str.indexOf(splitExp) > 0) {
				str = str.replaceAll(splitExp, "'" + splitExp + "'");
			}
		}
		ret = "'" + str + "'";
		return ret;
	}
	
	
	/**
	 * 以分隔符拼接list内字符串
	 * 
	 * @param list
	 * @param split_str
	 * @return
	 */
	public static String listToStringBySplit(List<String> list, String split_str) {
		String str = "";
		for (int i = 0; i < list.size(); i++) {
			str = str + split_str + list.get(i);
		}

		if (str.length() > 0 && str.startsWith(split_str))
			str = str.substring(1);

		return str;
	}
	
}
