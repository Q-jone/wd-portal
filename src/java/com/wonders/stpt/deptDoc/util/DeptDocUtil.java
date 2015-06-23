package com.wonders.stpt.deptDoc.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wonders.stpt.deptDoc.constants.DeptDocConstants;
import com.wonders.stpt.util.DbUtil;
import com.wonders.stpt.util.NewDbUtil;


@Component("deptDocUtil")
public class DeptDocUtil {
	private DbUtil dbUtil;
	public  DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	
	private NewDbUtil newDbUtil;
	public NewDbUtil getNewDbUtil() {
		return newDbUtil;
	}
	@Autowired(required=false)
	public void setNewDbUtil(@Qualifier("newDbUtil")NewDbUtil newDbUtil) {
		this.newDbUtil = newDbUtil;
	}
	
	public boolean existInGroup(String code,String loginName){
		String sql = "select pp.id,pp.login_name as login_name,"
				+ " pp.name,d.id as Dept_id,d.name as Dept, "
				+ " d.description as description,  pp.orders"
				+ " from (select u.id,u.login_name,u.name,m.org_node_id,o.orders"
				+ " from cs_user u,cs_user_organnode o,cs_organ_node d,"
				+ " cs_user_group g,cs_group p,cs_organ_model m "
				+ " where o.security_user_id=u.id"
				+ " and o.organ_node_id=d.id"
				+ " and g.security_user_id=u.id"
				+ " and g.security_group_id=p.id"
				+ " and m.org_node_id = d.id"
				+ " and p.code= ? "
				+ "  and u.removed=0 and d.removed=0"
				+ " and p.removed=0 and m.removed=0) pp,cs_organ_node d"
				+ " where pp.org_node_id=d.id and pp.login_name= ? ";
		int result = newDbUtil.getJdbcTemplate().queryForList(sql,new Object[]{code,loginName}).size();
		if(result > 0){
			return true;
		}
		return false;
	}
	
	/** 
	* @Title: getAuthoritySql 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年11月1日 下午8:29:23 
	* @throws 
	*/
	public static String getAuthorityCatalogSql(String operator,String deptId){
		StringBuilder sb = new StringBuilder();
		if(!DeptDocProcessUtil.existInGroup(DeptDocConstants.CODE_INNER_DEPT_DOC, operator)){
			sb.append("select distinct s.*");
			sb.append(" from z_docs_catalog s,z_docs_right r ");
			sb.append(" where s.STATE = '1'");
			sb.append(" and s.FLAG = '100'");
			sb.append(" and s.DEPT_ID = '");
			sb.append(deptId);
			sb.append("' and s.sid = r.rightid and r.type=0");
			sb.append(" and r.empid='ST/");
			sb.append(operator);
			sb.append("' union    select *");
			sb.append(" from z_docs_catalog s");
			sb.append(" where s.STATE = '1'");
			sb.append(" and s.FLAG = '100'");
			sb.append(" and s.DEPT_ID = '");
			sb.append(deptId);
			sb.append("' and s.catalog_name='部门内部资料'");
		}else{
			sb.append("select *");
			sb.append(" from z_docs_catalog s");
			sb.append(" where s.STATE = '1'");
			sb.append(" and s.FLAG = '100'");
			sb.append(" and s.DEPT_ID = '");
			sb.append(deptId);
			sb.append("' ");
		}
		
		
		return sb.toString();
	}
	
	/**
	 * 所有共享目录
	 * @return
	 */
	public List getShareFolders(){
		String sql = "select t.SID, t.CATALOG_NAME, t.PARENT_SID, t.DEPT_ID ,DECODE(level, 1, 'true', 'false') AS \"open\", level from (select * from z_docs_catalog s where s.STATE = '1' and s.FLAG = '200') t start with t.sid='share' connect by prior t.sid = t.parent_sid order siblings by t.UPDATE_DATE";
		List result = dbUtil.getJdbcTemplate().queryForList(sql,new Object[]{});
		return result;
	}
	
	/**
	 * 部门内部资料
	 * @param deptId
	 * @return
	 */
	public List getDeptFolders(String operator,String deptId,String catalogId){
		String sql = "";
		if(catalogId == null || catalogId.length() ==0){
			sql = "select t.SID, t.CATALOG_NAME, t.PARENT_SID, t.DEPT_ID ,DECODE(level, 1, 'true', 'false') AS \"open\", level from "
			+ " ( "+getAuthorityCatalogSql(operator, deptId)+ " ) "
			+ " t start with t.CATALOG_NAME='部门内部资料' connect by prior t.sid = t.parent_sid order siblings by t.UPDATE_DATE";
		}else{
			sql = "select t.SID, t.CATALOG_NAME, t.PARENT_SID, t.DEPT_ID ,DECODE(level, 1, 'true', 'false') AS \"open\", level from "
			+ " ( "+getAuthorityCatalogSql(operator, deptId)+ " ) "
			+ " t start with t.CATALOG_NAME='部门内部资料' connect by prior t.sid = t.parent_sid order siblings by t.UPDATE_DATE";
		}
		List result = dbUtil.getJdbcTemplate().queryForList(sql);
		return result;
	}
	
	/**
	 * 本部门共享目录
	 * @return
	 */
	public List getOwnShareFolders(String deptId,String catalogId){
		String sql = "";
		if(catalogId == null || catalogId.length() ==0){
			sql = "select t.SID, t.CATALOG_NAME, t.PARENT_SID, t.DEPT_ID ,DECODE(level, 1, 'true', 'false') AS \"open\", level from (select * from z_docs_catalog s where s.STATE = '1' and s.DEPT_ID = ? and s.FLAG = '200') t start with t.PARENT_SID = 'share' connect by prior t.sid = t.parent_sid order siblings by t.UPDATE_DATE";
		}else{
			sql = "select t.SID, t.CATALOG_NAME, t.PARENT_SID, t.DEPT_ID ,DECODE(level, 1, 'true', 'false') AS \"open\", level from (select * from z_docs_catalog s where s.STATE = '1' and s.DEPT_ID = ? and s.FLAG = '200') t start with t.PARENT_SID = 'share' connect by prior t.sid = t.parent_sid order siblings by t.UPDATE_DATE";
			
		}
		List result = dbUtil.getJdbcTemplate().queryForList(sql,new Object[]{deptId});
		return result;
	}
	
	public List findParentFolders(String catalogId){
		String sql = "select t.SID, t.CATALOG_NAME, t.PARENT_SID from z_docs_catalog t where t.STATE = '1' start with t.sid = ? connect by prior t.parent_sid = t.sid order by LEVEL desc";
		List result = dbUtil.getJdbcTemplate().queryForList(sql,new Object[]{catalogId});
		return result;
	}
	
	public List getEmps(String deptId){
		String sql = "select 'ST/'||login_name as login_name,name" +
				" from V_USERDEP" +
				" where dept_id = ?";
		List result = newDbUtil.getJdbcTemplate().queryForList(sql,new Object[]{deptId});
		return result;
	}
	
	public boolean hasReadRight(String loginName,String rightId){
		if(!loginName.startsWith("ST")){
			loginName = "ST/"+loginName;
		}
		String sql = "select 1" +
				" from Z_DOCS_RIGHT" +
				" where EMPID = ? and RIGHTID = ?";
		int result = dbUtil.getJdbcTemplate().queryForList(sql,new Object[]{loginName,rightId}).size();
		if(result > 0){
			return true;
		}
		return false;
	}
	
}

