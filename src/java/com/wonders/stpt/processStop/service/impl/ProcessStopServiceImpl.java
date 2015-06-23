/**
 * 
 */
package com.wonders.stpt.processStop.service.impl;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import com.wonders.stpt.processStop.dao.ProcessStopDao;
import com.wonders.stpt.processStop.instance.PMInstance;
import com.wonders.stpt.processStop.model.bo.ProcessStop;
import com.wonders.stpt.processStop.service.ProcessStopService;
import com.wonders.stpt.util.DbUtil;

/** 
 * @ClassName: ProcessStopServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:56:43 
 *  
 */

@Repository("processStopService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ProcessStopServiceImpl implements ProcessStopService{
	private DbUtil dbUtil;
	private ProcessStopDao dao;
	private static PlatformTransactionManager transactionManager;
	public ProcessStopDao getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("processStopDao")ProcessStopDao dao) {
		this.dao = dao;
	}
	
	public DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		this.dbUtil = dbUtil;
	}
	public static PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	@Resource(name="dsTransactionManager")
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		ProcessStopServiceImpl.transactionManager = transactionManager;
	}
	
	public int count(String table,
			String title,String status,String starttime,String endtime){
		return this.dao.count(table, title, status, starttime, endtime);
	}
	
	public List<Object[]> findByPage(int startRow,int pageSize,String table,
			String title,String status,String starttime,String endtime){
		return this.dao.findByPage(startRow, pageSize, table, title, status, starttime, endtime);
	}
	
	
	
	public List<ProcessStop> find(String id){
		return this.dao.find(id);
	}
	

	public boolean save(String reason,String attach,String operator,String mainId,String process,String incident){
		ProcessStop bo = new ProcessStop();
		boolean flag = false;
		try{
			final PMInstance instance = new PMInstance(reason,attach,operator,mainId,process,incident,bo.getOperateTime(),bo.getRemoved());
			
			TransactionTemplate tt = new TransactionTemplate(transactionManager);
			flag = tt.execute(new TransactionCallback<Boolean>() {
				boolean flag = true;
				public Boolean doInTransaction(TransactionStatus status) {
					try{
						JdbcTemplate jt = dbUtil.getJdbcTemplate();
						instance.action(jt);
					}catch(Exception e){
						e.printStackTrace();
						status.setRollbackOnly();
						flag = false;
					}
					return flag;
				}
			});  
		}catch(Exception e){
			
		}
		return flag;
	}
}
