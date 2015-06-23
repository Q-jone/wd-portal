package com.wonders.stpt.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("flowUtil")
public class FlowUtil {
	private static DbUtil dbUtil;
	/**获得待办事项
	 * @param processname
	 * @param incidentNo
	 * @param steplabel
	 * @param userVo
	 * @return
	 */
	public static List<Map<String,Object>> getActiveProcess(String processname, String incidentNo, String steplabel,String loginName,String size){
		processname = StringUtil.getNotNullValueString(processname);
		incidentNo = StringUtil.getNotNullValueString(incidentNo);
		steplabel = StringUtil.getNotNullValueString(steplabel);
		
		String sql = " select main.*,tree.pname,tree.pincident,tree.cname,tree.cincident,t.steplabel from \n"+
					" tasks t,\n"+
					" (select td.pname,td.pincident,td.cname,td.cincident,td.status from t_dept_contact_tree td \n"+
					" where td.cname=? and td.removed!=1\n"+
					" union\n"+
					" select td.cname,td.cincident,td.cname,td.cincident,td.status from t_dept_contact_tree td \n"+
					" where td.cname=? and td.removed!=1\n"+
					" ) tree,\n"+
					" t_dept_contact_main main,incidents i \n"+
					" where 1=1 \n" +
					" and i.processname = tree.pname and i.incident = tree.pincident \n"+
					" and i.status = ? and t.status = ? and tree.status = ?\n"+
					" and t.processname = tree.cname and t.incident = tree.cincident\n"+
					" and tree.pname = main.processname\n"+
					" and tree.pincident = main.incidentno\n"+
					" and t.assignedtouser = ?\n" +
					" and t.steplabel != '"+"下级流程"+"'"+
					//" and t.helpurl like ?\n"+
					(processname.length()>0?" and t.processname = ?\n":"") +
					(incidentNo.length()>0?" and t.incident = ?\n":"") +
					(steplabel.length()>0?" and t.steplabel = ?\n":"") +
					" order by t.starttime desc";
		sql = "select * from ("+sql+") where rownum<="+size;
		List<String> paramsList = new ArrayList<String>();
		//String deptInfo = "%"+loginName +":"+userVo.deptId+"<+>%";
		
//log.debug(loginName+" "+deptInfo+" "+processname+" "+incidentNo+" "+steplabel);
		
		paramsList.add("部门内部子流程");
		paramsList.add("多级工作联系单");
		paramsList.add("1");
		paramsList.add("1");
		paramsList.add("0");
		paramsList.add(loginName);
		
		if(processname.length()>0) paramsList.add(processname);
		if(incidentNo.length()>0) paramsList.add(incidentNo);
		if(steplabel.length()>0) paramsList.add(steplabel);
		
		Object[] params = paramsList.toArray();
//log.debug(sql);
//log.debug(params.length);
		
		List<Map<String,Object>> list = dbUtil.getJdbcTemplate().queryForList(sql,params);
		
		return list;
	}
	
	public static DbUtil getDbUtil() {
		return FlowUtil.dbUtil;
	}
	
	
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		FlowUtil.dbUtil = dbUtil;
	}

}
