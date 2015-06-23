/**
 * 
 */
package com.wonders.stpt.validFile.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.wonders.stpt.util.DateUtil;
import com.wonders.stpt.util.DbUtil;

/** 
 * @ClassName: ValidFileUtil 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月22日 下午1:05:39 
 *  
 */
@Component("validFileUtil")
public class ValidFileUtil {
	private static DbUtil dbUtil;
	private static PlatformTransactionManager transactionManager;
	public static DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		ValidFileUtil.dbUtil = dbUtil;
	}
	public static PlatformTransactionManager getTransactionManager() {
		return transactionManager;
	}

	@Resource(name="dsTransactionManager")
	public void setTransactionManager(PlatformTransactionManager transactionManager) {
		ValidFileUtil.transactionManager = transactionManager;
	}
	
	
	public static List<Map<String,Object>> getReceipt(String mainId){
		String sql = "select * from v_send_msg t where mainid = ?";
		return dbUtil.getJdbcTemplate().queryForList(sql,new Object[]{mainId});
	}
	
	public static List<Map<String,Object>> getRemark(String mainId){
		String sql = "select t.remark,t.operate_person person,t.operate_time times,t.attach from t_valid_remark t where t.main_id = ? and t.removed=0";
		return dbUtil.getJdbcTemplate().queryForList(sql,new Object[]{mainId});
	}
	
	
	public static boolean addRemark(final String remark,final String attach,final String operator,final String mainId){
		boolean flag = false;
		try{
			TransactionTemplate tt = new TransactionTemplate(transactionManager);
			flag = tt.execute(new TransactionCallback<Boolean>() {
				boolean flag = false;
				public Boolean doInTransaction(TransactionStatus status) {
					try{
						JdbcTemplate jt = dbUtil.getJdbcTemplate();
						int r = jt.update("insert into t_valid_remark (id,main_id,remark,attach,"
								+ "operate_time,operate_person,removed) values (?,?,?,?,?,?,?)",
								new Object[]{UUID.randomUUID().toString(),mainId,remark,attach,
								DateUtil.getCurrDate("yyyy-MM-dd"),operator,"0"});
						int r2 = jt.update("update t_valid_file t  set t.remark = ? ,"
								+ "	t.operate_person = ? ,"
								+ " t.operate_time =? "
								+ " where t.removed=0 and t.id=?", 
								new Object[]{"1",operator,
								DateUtil.getCurrDate("yyyy-MM-DD HH:mm:ss"),mainId});
						
						if(r>0 && r2>0){ flag = true;}
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
	
	public static boolean setInvalid(final String mainId,final String fileStatus){
		boolean flag = false;
		try{
			TransactionTemplate tt = new TransactionTemplate(transactionManager);
			flag = tt.execute(new TransactionCallback<Boolean>() {
				boolean flag = false;
				public Boolean doInTransaction(TransactionStatus status) {
					try{
						JdbcTemplate jt = dbUtil.getJdbcTemplate();
						int r = jt.update("update t_valid_file t  set t.status = '"+fileStatus+"'"
								+ " where t.removed='0' and t.id in ("+mainId+")");
						if(r>0){ flag = true;}
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
	
	public static Map<String,Object> generateFilterMap(Object obj) {
		Map<String,Object> map = new HashMap<String,Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		String varName = null;
		for (int i = 0; i < fields.length; i++) {
			varName = fields[i].getName();
				boolean accessFlag = fields[i].isAccessible();
				fields[i].setAccessible(true);
				Object res = null;
				try {
					res = fields[i].get(obj);
					if(res!=null && res.toString().length() > 0){
						map.put(varName, res);
					}

				} catch (Exception e) {
				}
				fields[i].setAccessible(accessFlag);
			}
		return map;
	}
	
	public static int getPage(String s){
		int page = 1;
		try{
			page = Integer.parseInt(s);
		}catch(Exception e){
			page = 1;
		}
		return page;
	}
	
	public static int getPageSize(String s){
		int pageSize = 10;
		try{
			pageSize = Integer.parseInt(s);
		}catch(Exception e){
			pageSize = 10;
		}
		return pageSize;
	}
}
