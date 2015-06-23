package com.wonders.stpt.deptDoc.constants;

import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.deptDoc.util.DeptDocProcessUtil;


public class DeptDocConstants {
	public static final String CATELOG_DOC_PROCESS = "000";
	public static final String CATELOG_DOC_DEPT = "100";
	public static final String CATELOG_DOC_SHARE = "200";
	
	public static final String FILE_DOC_RECEIVE = "001";
	public static final String FILE_RECEIVE_DIRECTIVE = "002";
	public static final String FILE_DOC_SEND = "003";
	public static final String FILE_PARTY_SEND = "004";
	public static final String FILE_DISCIPLINE_SEND = "005";
	public static final String FILE_PASS_SEND = "006";
	
	public static final String FILE_RIGHT_TYPE = "1";
	
	public static final String LINK_FLAG = "3";
	
	public static final List<String> CODE_PROCESS_DEPT_DOC = new ArrayList<String>();

    public static final List<String> CODE_PROCESS_DEPT_DOC_LEADER = new ArrayList<String>();

    public static final List<String> CODE_PROCESS_DEPT_DOC_RECVER = new ArrayList<String>();
	
	public static final List<String> CODE_INNER_DEPT_DOC = new ArrayList<String>();
	
	/**
	 * 附件下载地址
	 */
	public static final String ATTACH_URL_DOWNLOAD = "/portal/attachOld/loadFileOldList.action?procType=view&fileGroupId=";
	
	static{
		CODE_PROCESS_DEPT_DOC.add("dept_leaders");
        CODE_PROCESS_DEPT_DOC.add("deptDoc_manager");
        CODE_PROCESS_DEPT_DOC_LEADER.add("dept_leaders");
        CODE_PROCESS_DEPT_DOC_RECVER.add("deptDoc_manager");
		CODE_INNER_DEPT_DOC.add("dept_leaders");
		CODE_INNER_DEPT_DOC.add("deptDoc_manager");
	}
	
	/** 
	* @Title: getDeptDocRecvSql 
	* @Description: 部门文件柜-行政收文 sql
	* @param @param deptId
	* @param @param loginName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年10月21日 上午11:17:16 
	* @throws 
	*/
	public static final String getDeptDocRecvSql(String deptId,String loginName){
		StringBuffer sb = new StringBuffer();
		if(DeptDocProcessUtil.existInGroup(CODE_PROCESS_DEPT_DOC, loginName)){
			sb.append("select * from v_dept_doc_recv t where t.deptid = '"+deptId+"' and 1=1");
		}else{
			sb.append("select * from (");
			sb.append(" select a.id,a.recvcode,a.recvdate,a.sourcedept,a.sourcecode,a.num,a.title,");
			sb.append(" case when b.fileid is null then '' else a.url end url");
			sb.append(" from ");
			sb.append(" (select * from v_dept_doc_recv t where t.deptid = '"+deptId+"') a");
			sb.append(" left join   (select distinct r.rightid fileId from z_docs_right r ");
			sb.append(" where r.type = '1' and r.empid='"+"ST/"+loginName+"' ) b");
			sb.append(" on a.id = b.fileId ) t where 1=1 ");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/** 
	* @Title: getDeptDocRedvSql 
	* @Description: 部门文件柜-收呈批件 sql
	* @param @param deptId
	* @param @param loginName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年10月21日 上午11:17:13 
	* @throws 
	*/
	public static final String getDeptDocRedvSql(String deptId,String loginName){
		StringBuffer sb = new StringBuffer();
		if(DeptDocProcessUtil.existInGroup(CODE_PROCESS_DEPT_DOC, loginName)){
			sb.append("select * from v_dept_doc_redv t where t.deptid = '"+deptId+"' and 1=1");
		}else{
			sb.append("select * from (");
			sb.append(" select a.id,a.adddept,a.adddate,a.deptcode,a.title,a.addperson,");
			sb.append(" case when b.fileid is null then '' else a.url end url");
			sb.append(" from ");
			sb.append(" (select * from v_dept_doc_redv t where t.deptid = '"+deptId+"') a");
			sb.append(" left join   (select distinct r.rightid fileId from z_docs_right r ");
			sb.append(" where r.type = '1' and r.empid='"+"ST/"+loginName+"' ) b");
			sb.append(" on a.id = b.fileId ) t where 1=1 ");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	

	/** 
	* @Title: getDeptDocPartysendSql 
	* @Description: 部门文件柜-党委发文
	* @param @param deptId
	* @param @param loginName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年10月21日 上午11:17:11 
	* @throws 
	*/
	public static final String getDeptDocPartysendSql(String deptId,String loginName){
		StringBuffer sb = new StringBuffer();
		if(DeptDocProcessUtil.existInGroup(CODE_PROCESS_DEPT_DOC, loginName)){
			sb.append("select * from v_dept_doc_partysend t where t.deptid = '"+deptId+"' and 1=1");
		}else{
			sb.append("select * from (");
			sb.append(" select a.id,a.sendcode,a.senddate,a.title,a.addperson,a.senddept,");
			sb.append(" case when b.fileid is null then '' else a.url end url");
			sb.append(" from ");
			sb.append(" (select * from v_dept_doc_partysend t where t.deptid = '"+deptId+"') a");
			sb.append(" left join   (select distinct r.rightid fileId from z_docs_right r ");
			sb.append(" where r.type = '1' and r.empid='"+"ST/"+loginName+"' ) b");
			sb.append(" on a.id = b.fileId ) t where 1=1 ");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/** 
	* @Title: getDeptDocDcpsendSql 
	* @Description: 部门文件柜-纪委发文
	* @param @param deptId
	* @param @param loginName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年10月21日 上午11:17:08 
	* @throws 
	*/
	public static final String getDeptDocDcpsendSql(String deptId,String loginName){
		StringBuffer sb = new StringBuffer();
		if(DeptDocProcessUtil.existInGroup(CODE_PROCESS_DEPT_DOC, loginName)){
			sb.append("select * from v_dept_doc_dcpsend t where t.deptid = '"+deptId+"' and 1=1");
		}else{
			sb.append("select * from (");
			sb.append(" select a.id,a.sendcode,a.senddate,a.title,a.addperson,a.senddept,");
			sb.append(" case when b.fileid is null then '' else a.url end url");
			sb.append(" from ");
			sb.append(" (select * from v_dept_doc_dcpsend t where t.deptid = '"+deptId+"') a");
			sb.append(" left join   (select distinct r.rightid fileId from z_docs_right r ");
			sb.append(" where r.type = '1' and r.empid='"+"ST/"+loginName+"' ) b");
			sb.append(" on a.id = b.fileId ) t where 1=1 ");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	/** 
	* @Title: getDeptDocSendSql 
	* @Description: 部门文件柜-行政发文
	* @param @param deptId
	* @param @param loginName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年10月21日 上午11:17:06 
	* @throws 
	*/
	public static final String getDeptDocSendSql(String deptId,String loginName){
		StringBuffer sb = new StringBuffer();
		if(DeptDocProcessUtil.existInGroup(CODE_PROCESS_DEPT_DOC, loginName)){
			sb.append("select * from v_dept_doc_send t where t.deptid = '"+deptId+"' and 1=1");
		}else{
			sb.append("select * from (");
			sb.append(" select a.id,a.vid,a.sendcode,a.senddate,a.title,a.addperson,a.senddept,");
			sb.append(" a.normative,a.status,a.remark,a.code1,a.code2,a.code3,");
			sb.append(" case when b.fileid is null then '' else a.url end url");
			sb.append(" from ");
			sb.append(" (select * from v_dept_doc_send t where t.deptid = '"+deptId+"') a");
			sb.append(" left join   (select distinct r.rightid fileId from z_docs_right r ");
			sb.append(" where r.type = '1' and r.empid='"+"ST/"+loginName+"' ) b");
			sb.append(" on a.id = b.fileId ) t where 1=1 ");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	
	/** 
	* @Title: getDeptDocNormativeSql 
	* @Description: 部门文件柜-规范性文件
	* @param @param deptId
	* @param @param loginName
	* @param @return    设定文件 
	* @return String    返回类型 
	* @date 2014年10月21日 上午11:17:03 
	* @throws 
	*/
	public static final String getDeptDocNormativeSql(String deptId,String loginName){
		StringBuffer sb = new StringBuffer();
		if(DeptDocProcessUtil.existInGroup(CODE_PROCESS_DEPT_DOC, loginName)){
			sb.append("select * from v_dept_doc_send t where t.normative='规范性文件' and t.deptid = '"+deptId+"' and 1=1");
		}else{
			sb.append("select * from (");
			sb.append(" select a.id,a.vid,a.sendcode,a.senddate,a.title,a.addperson,a.senddept,");
			sb.append(" a.normative,a.status,a.remark,a.code1,a.code2,a.code3,");
			sb.append(" case when b.fileid is null then '' else a.url end url");
			sb.append(" from ");
			sb.append(" (select * from v_dept_doc_send t where t.normative='规范性文件' and  t.deptid = '"+deptId+"') a");
			sb.append(" left join   (select distinct r.rightid fileId from z_docs_right r ");
			sb.append(" where r.type = '1' and r.empid='"+"ST/"+loginName+"' ) b");
			sb.append(" on a.id = b.fileId ) t where 1=1 ");
		}
		//System.out.println(sb.toString());
		return sb.toString();
	}
}