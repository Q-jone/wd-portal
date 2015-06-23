/**
 * 
 */
package com.wonders.stpt.processStop.instance;

import org.springframework.jdbc.core.JdbcTemplate;

/** 
 * @ClassName: CMInstance 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-13 上午11:03:13 
 *  
 */
public class PMInstance {
	private String reason, attach, operator, mainId, process, incident,operateTime,removed;
	private static final String insertSql = "insert into t_process_stop t " +
			"(t.id,t.process,t.incident,t.reason,t.attach,t.operator,t.operate_time,t.main_id,t.removed)" +
			" values(sys_guid(),?,?,?,?,?,?,?,?)";
	private static final String receive_updateSql = 
			"update t_doc_receive h set  h.title =  h.title || '<font color=''red''>（此流程异常终止）</font>' , h.flag='99',h.removed=1 where h.id = ?";
	private static final String send_updateSql = 
			"update t_doc_send h set  h.doc_title =  h.doc_title || '<font color=''red''>（此流程异常终止）</font>' , h.flag='99',h.removed=1 where h.id = ?";
	private static final String directive_updateSql = 
			"update t_receive_directive h set  h.title =  h.title || '<font color=''red''>（此流程异常终止）</font>' , h.flag='99',h.removed=1 where h.id = ?";
	
	private static final String tasks_updateSql="update tasks t set t.status=3 where t.taskid in (" +
			"select t1.taskid from tasks t1 " +
			" where (t1.processname,t1.incident)" +
			" in(" +
			" (select t.cname ,t.cincident from t_subprocess t where t.pname = ? and t.pincident = ?)" +
			" union" +
			" (select ?,? from dual)" +
			" ) and t1.status=1" +
			")";
	
	private static final String incidents_updateSql = "update incidents i set i.processname=i.processname||'(删除)' ,i.status = '33' " +
			"where (i.processname , i.incident) in " +
			" (select i.processname,i.incident  from  incidents i where (i.processname,i.incident) in " +
			" (" +
			" (select t.cname,t.cincident  from t_subprocess t where t.pname = ? and t.pincident = ? )" +
			" union" +
			" (select ?,? from dual)" +
			" ) and status=1)";

	
	public PMInstance(String reason,String attach,String operator,String mainId,String process,String incident,String operateTime,String removed){
		this.reason = reason;
		this.attach = attach;
		this.operator = operator;
		this.mainId = mainId;
		this.process = process;
		this.incident = incident;
		this.operateTime = operateTime;
		this.removed = removed;
	}
	
	public void action(JdbcTemplate jt) throws Exception{
		int n0 = jt.update(insertSql,new Object[]{process,incident,reason,attach,operator,operateTime,mainId,removed});
		int n1 = 0;
		if("收文流程".equals(process) || "新收文流程".equals(process)){
			n1 = jt.update(receive_updateSql,new Object[]{mainId});
		}else if("发文流程".equals(process) || "新发文流程".equals(process)){
			n1 = jt.update(send_updateSql,new Object[]{mainId});
		}else if("收呈批件".equals(process) || "新收呈批件".equals(process)){
			n1 = jt.update(directive_updateSql,new Object[]{mainId});
		}
		int n2 = jt.update(tasks_updateSql,new Object[]{process,incident,process,incident});
		
		int n3 = jt.update(incidents_updateSql,new Object[]{process,incident,process,incident});
		if(n0 > 0 && n1 > 0 && n2>0 && n3>0){
			
		}else{
			throw new Exception("update failed");
		}
	}
}
