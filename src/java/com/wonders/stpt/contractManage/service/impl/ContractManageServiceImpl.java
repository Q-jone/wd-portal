/**
 * 
 */
package com.wonders.stpt.contractManage.service.impl;

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
import com.wonders.stpt.contractManage.dao.ContractManageDao;
import com.wonders.stpt.contractManage.instance.CMInstance;
import com.wonders.stpt.contractManage.model.bo.HtxxStop;
import com.wonders.stpt.contractManage.service.ContractManageService;
import com.wonders.stpt.util.DbUtil;

/** 
 * @ClassName: ContractManageServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-6-9 上午9:56:43 
 *  
 */

@Repository("contractManageService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ContractManageServiceImpl implements ContractManageService{
	private DbUtil dbUtil;
	private ContractManageDao dao;
	private static PlatformTransactionManager transactionManager;
	public ContractManageDao getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("contractManageDao")ContractManageDao dao) {
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
		ContractManageServiceImpl.transactionManager = transactionManager;
	}
	public int countHtspOaView(String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus,String userName){
		return this.dao.countHtspOaView(plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money, pstatus,userName);
	}
	
	public List<Object[]> findHtspOaViewByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus,String userName){
		return this.dao.findHtspOaViewByPage(startRow, pageSize, plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money, pstatus,userName);
	}
	
	public int countHtspOa(String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus){
		return this.dao.countHtspOa(plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money, pstatus);
	}
	
	public List<Object[]> findHtspOaByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
			String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
			String add_person,String contract_money,String pstatus){
		return this.dao.findHtspOaByPage(startRow, pageSize, plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money, pstatus);
	}
	
	
	
	public List<HtxxStop> find(String id){
		return this.dao.find(id);
	}
	

	public boolean save(String reason,String attach,String operator,String mainId,String process,String incident){
		HtxxStop bo = new HtxxStop();
		boolean flag = false;
		try{
			final CMInstance instance = new CMInstance(reason,attach,operator,mainId,process,incident,bo.getOperateTime(),bo.getRemoved());
			
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


	public List<Object[]> findHtNewByPage(int startRow,int pageSize,String plan_num,String contract_num,String self_num,
										  String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
										  String add_person,String contract_money,String contract_money_end,String pstatus){
		return this.dao.findHtNewByPage(startRow, pageSize, plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money,contract_money_end, pstatus);
	}
	public int countHtNew(String plan_num,String contract_num,String self_num,
						  String contract_name,String project_num,String sign_cop,String add_time_start,String add_time_end,
						  String add_person,String contract_money,String contract_money_end,String pstatus){
		return this.dao.countHtNew(plan_num, contract_num, self_num, contract_name, project_num, sign_cop, add_time_start, add_time_end, add_person, contract_money,contract_money_end, pstatus);

	}
}
