/**
 * 
 */
package com.wonders.stpt.contractManage.instance;

import org.springframework.jdbc.core.JdbcTemplate;

/** 
 * @ClassName: CMInstance 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-13 上午11:03:13 
 *  
 */
public class CMInstance {
	private String reason, attach, operator, mainId, process, incident,operateTime,removed;
	private static final String insertSql = "insert into ht_xx_stop t " +
			"(t.id,t.reason,t.attach,t.operator,t.operate_time,t.main_id,t.removed)values(sys_guid(),?,?,?,?,?,?)";
	private static final String updateSql1 = "update ht_xx h set  h.contract_name =  h.contract_name || '<font color=''red''>（此合同异常终止）</font>' , h.flag='99',h.removed=1 where h.id = ?";
	private static final String updateSql2 = "update incidents i set i.processname=? ,i.status = '33' where i.processname = ?  and i.incident = ?";

	
	public CMInstance(String reason,String attach,String operator,String mainId,String process,String incident,String operateTime,String removed){
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
		int n0 = jt.update(insertSql,new Object[]{reason,attach,operator,operateTime,mainId,removed});
		int n1 = jt.update(updateSql1,new Object[]{mainId});
		int n2 = jt.update(updateSql2,new Object[]{process+"(删除)",process,incident});
		if(n0 > 0 && n1 > 0 && n2>0){
		}else{
			throw new Exception("update failed");
		}
	}
}
