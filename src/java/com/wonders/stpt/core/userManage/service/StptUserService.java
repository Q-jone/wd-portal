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

package com.wonders.stpt.core.userManage.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.core.userManage.entity.bo.StptUser;
import com.wonders.stpt.core.userManage.entity.vo.ManagerVo;
import com.wondersgroup.framework.core.bo.Page;

/**
 * 业务服务
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-19
 * @author modify by $Author$
 * @since 1.0
 */

public interface StptUserService {
	/**
	 * 删除实体对象
	 * 
	 * @param tuser
	 */
	public void deleteStptUser(StptUser stptUser);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public StptUser findStptUserById(Long id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param tuser
	 */
	public void addStptUser(StptUser stptUser);

	/**
	 * 更新数据到数据库
	 * 
	 * @param tuser
	 *            实体
	 */
	public void updateStptUser(StptUser stptUser);
	
	public List<StptUser> stptUserExist(String loginName);
	
	public Page findStptUserByPage(Map<String, Object> filter,
			int pageNo, int pageSize);
	
	public List<StptUser> stptUserList();
	
	public void addPatch(List<StptUser> list);
	
	public List<ManagerVo> getUserInfo(String id);
	
	public List<ManagerVo> getAgentInfo(String id);

	public List<ManagerVo> getAgentInfo(String maxRows , String name_startsWith);
	
	public void saveAgent(String tid,String cid);
	
	public void deleteAgent(String tid,String cid);
}
