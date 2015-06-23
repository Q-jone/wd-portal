/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.core.userManage.service.impl;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.core.userManage.dao.StptUserDao;
import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.core.userManage.entity.vo.ManagerVo;
import com.wonders.stpt.core.userManage.service.StptUserService;
import com.wondersgroup.framework.core.bo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */
@Service("stptUserService")
@Scope("prototype")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class StptUserServiceImpl implements StptUserService {
	private StptUserDao stptUserDao;


	public StptUserDao getStptUserDao() {
		return stptUserDao;
	}

	@Autowired(required=false)
	public void setStptUserDao(@Qualifier(value="stptUserDao")StptUserDao stptUserDao) {
		this.stptUserDao = stptUserDao;
	}

	public void addStptUser(StptUser stptUser) {
		stptUserDao.save(stptUser);
	}

	public void deleteStptUser(StptUser stptUser) {
		stptUserDao.delete(stptUser);
	}

	public StptUser findStptUserById(Long id) {
		return stptUserDao.load(id);
	}

	public void updateStptUser(StptUser stptUser) {
		stptUserDao.update(stptUser);
	}

	public List<StptUser> stptUserExist(String loginName){
		return stptUserDao.stptUserExist(loginName);
	}
	
	public Page findStptUserByPage(Map<String, Object> filter,
			int pageNo, int pageSize){
		return stptUserDao.findStptUserByPage(filter, pageNo, pageSize);
	}
	
	public List<StptUser> stptUserList(){
		return stptUserDao.stptUserList();
	}
	
	public void addPatch(List<StptUser> list){
		stptUserDao.addPatch(list);
	}
	
	public List<ManagerVo> getAgentInfo(String maxRows , String name_startsWith){
		return stptUserDao.getAgentInfo(maxRows,name_startsWith);
	}
	
	public String getTimeStamp(String i){
		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
		String time=sdf.format(new Date(Long.parseLong(i)));
		return time;
	}
	public List<ManagerVo> getUserInfo(String id){
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql="select v.id,v.login_name,v.name,v.dept_name,u.operate_time,u.rank from v_userdep v,t_user u,t_user_relation r " +
				" where u.removed=0 and r.removed=0 and u.id=r.t_id and r.c_id=v.id  and u.id="+id +" and (r.agent!='1' or r.agent is null)";
		List<ManagerVo> list = new ArrayList<ManagerVo>();
		try{
			
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				ManagerVo vo = new ManagerVo();
				vo.setLoginName(rs.getString("login_name"));
				vo.setName(rs.getString("name"));
				vo.setNickName("");
				vo.setRank(rs.getString("rank"));
				vo.setDept(rs.getString("dept_name"));
				vo.setCreateDate(rs.getString("operate_time"));
				vo.setRole("");
				vo.setCid(rs.getLong("id")+"");
				list.add(vo);
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	public List<ManagerVo> getAgentInfo(String id){
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql="select v.id,v.login_name,v.name,v.dept_name from v_userdep v,t_user u,t_user_relation r " +
				" where u.removed=0 and r.removed=0 and u.id=r.t_id and r.c_id=v.id and r.agent='1' and u.id="+id;
		sql = "select b.*,u.operate_time,u.rank from (" + sql + ") b ,t_user_relation r,t_user u " +
				" where b.id = r.c_id and r.t_id=u.id and r.removed=0 and u.removed=0 " +
				" and (r.agent!='1' or r.agent is null)";
		List<ManagerVo> list = new ArrayList<ManagerVo>();
		try{
			
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				ManagerVo vo = new ManagerVo();
				vo.setLoginName(rs.getString("login_name"));
				vo.setName(rs.getString("name"));
				vo.setNickName("");
				vo.setRank(rs.getString("rank"));
				vo.setDept(rs.getString("dept_name"));
				vo.setCreateDate(rs.getString("operate_time"));
				vo.setRole("");
				vo.setCid(rs.getLong("id")+"");
				list.add(vo);
			}
			rs.close();
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	public void saveAgent(String tid,String cid){
		stptUserDao.saveAgent(tid, cid);
	}
	
	public void deleteAgent(String tid,String cid){
		stptUserDao.deleteAgent(tid, cid);
	}
}
