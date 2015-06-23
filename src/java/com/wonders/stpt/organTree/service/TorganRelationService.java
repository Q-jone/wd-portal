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

package com.wonders.stpt.organTree.service;

import java.util.List;
import java.util.Map;

import com.wonders.stpt.organTree.entity.bo.TorganRelation;
import com.wonders.stpt.organTree.entity.vo.NodeVo;
import com.wonders.stpt.organTree.entity.vo.OrganNodeVo;
import com.wonders.stpt.organTree.entity.vo.UserNodeVo;

/**
 * 业务服务
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-5-7
 * @author modify by $Author$
 * @since 1.0
 */

public interface TorganRelationService {
	/**
	 * 删除实体对象
	 * 
	 * @param TorganRelation
	 */
	public void deleteTorganRelation(TorganRelation TorganRelation);

	/**
	 * 
	 * 通过ID装载相应的对象实例，如果对应的实体不存在，返回null
	 * 
	 * @param id
	 *            主键
	 * @return
	 */
	public TorganRelation findTorganRelationById(String id);

	/**
	 * 持久化一个实体对象
	 * 
	 * @param TorganRelation
	 */
	public void addTorganRelation(TorganRelation TorganRelation);

	/**
	 * 更新数据到数据库
	 * 
	 * @param TorganRelation
	 *            实体
	 */
	
	public boolean judgeRootId(String rootId);
	
	public Map<Long,String> findOrganRelation();
	
	public Map<Long,Long> findOrganRelation2();
	
	public List<OrganNodeVo> findCrossNodeByCid(String rootId,String nodeId);
	
	//xfz 得到指定节点信息
	public List<NodeVo> getNodesInfo(String nodesId);
	
	//xfz 得到关联接点
	public List<NodeVo> getRelatedNodes(String nodesId);
	
	//异步加载子节点
	public List<NodeVo> getChildNodes(String pid);
	
	//xfz 得到部门领导层信息
	public List<UserNodeVo> getDeptLeaders(String pid);
	
	//xfz 获取部门大领导信息
	public List<UserNodeVo> getDeptSingleLeader(String pid);
	
	//xfz 获取部门收发员信息
	public List<UserNodeVo> getDeptReceivers(String pid);
	
	//xfz 获取部门人员信息
	public List<UserNodeVo> getDeptUsers(String pid);
	
	//获取部门人员信息(去除领导)
	public List<UserNodeVo> getDeptUsersOffLeaders(String pid);
	
	public Map<String,String> getReceivers();
	
	public Map<String,String> getConfigers();
}
