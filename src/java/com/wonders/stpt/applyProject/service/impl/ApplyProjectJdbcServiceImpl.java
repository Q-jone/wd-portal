/**
 * 
 */
package com.wonders.stpt.applyProject.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.applyProject.service.ApplyProjectJdbcService;
import com.wonders.stpt.util.DbUtil;

/** 
 * @ClassName: ApplyProjectJdbcService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年1月2日 下午12:41:01 
 *  
 */

@Service("applyProjectJdbcService")
@Transactional(value="dsTransactionManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class ApplyProjectJdbcServiceImpl implements ApplyProjectJdbcService{
	private static DbUtil dbUtil;
	public static DbUtil getDbUtil() {
		return dbUtil;
	}
	@Autowired(required=false)
	public void setDbUtil(@Qualifier("dbUtil")DbUtil dbUtil) {
		ApplyProjectJdbcServiceImpl.dbUtil = dbUtil;
	}
	public int delete(String id){
		int result  = 0;
		try{
			result = dbUtil.getJdbcTemplate().
			update("update t_apply_project t set t.removed='1' where t.id in ("+id+")");
		}catch(Exception e){
			result = 0;
		}
		return result;
	}
}
