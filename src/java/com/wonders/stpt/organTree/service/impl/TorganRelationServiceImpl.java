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

package com.wonders.stpt.organTree.service.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import com.wonders.module.common.ExecuteSql;
import com.wonders.stpt.organTree.dao.TorganRelationDao;
import com.wonders.stpt.organTree.entity.bo.TorganRelation;
import com.wonders.stpt.organTree.entity.vo.NodeVo;
import com.wonders.stpt.organTree.entity.vo.OrganConstants;
import com.wonders.stpt.organTree.entity.vo.OrganNodeVo;
import com.wonders.stpt.organTree.entity.vo.UserNodeVo;
import com.wonders.stpt.organTree.service.TorganRelationService;
import com.wonders.stpt.organTree.util.OrganTreeUtil;


/**
 * @author zhoushun
 * @version $Revision$
 * @date 2012-5-7
 * @author modify by $Author$
 * @since 1.0
 */

public class TorganRelationServiceImpl implements TorganRelationService {
	private TorganRelationDao torganRelationDao;

	

	public TorganRelationDao getTorganRelationDao() {
		return torganRelationDao;
	}

	public void setTorganRelationDao(TorganRelationDao torganRelationDao) {
		this.torganRelationDao = torganRelationDao;
	}

	public void addTorganRelation(TorganRelation TorganRelation) {
		torganRelationDao.save(TorganRelation);
	}

	public void deleteTorganRelation(TorganRelation TorganRelation) {
		torganRelationDao.delete(TorganRelation);
	}

	public TorganRelation findTorganRelationById(String id) {
		return torganRelationDao.load(id);
	}

	public void updateTorganRelation(TorganRelation TorganRelation) {
		torganRelationDao.update(TorganRelation);
	}
	
	public boolean judgeRootId(String rootId){
		boolean flag = false;
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select * from cs_organ_tree t where t.removed=0 and t.root_node_id="+rootId;
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			if(rs.next()){
				flag = true;
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return flag;
	}
	
	public Map<Long,String> findOrganRelation(){
		Map<Long,String> map = new HashMap<Long,String>();
		List<TorganRelation> list = torganRelationDao.findOrganRelation();
		if(list!=null&&list.size()>0){
			for(TorganRelation t:list){
				map.put(t.getNodeId(), t.getTreeCode());
			}
		}
		return map;
	}
	
	public Map<Long,Long> findOrganRelation2(){
		Map<Long,Long> map = new HashMap<Long,Long>();
		List<TorganRelation> list = torganRelationDao.findOrganRelation();
		if(list!=null&&list.size()>0){
			for(TorganRelation t:list){
				map.put(t.getRootNodeId(), t.getNodeId());
			}
		}
		return map;
	}
	
	public List<OrganNodeVo> findCrossNodeByCid(String rootId,String nodeId){
		Map<Long,Long> map = findOrganRelation2();
		List<OrganNodeVo> list = new ArrayList<OrganNodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "";
		String allSql = "select " +
				" case when r.root_node_id is not null then root_node_id else org_node_id end as org_node_id," +
				" r.parent_node_id,name,organ_node_type_id,description from (" +
				" select t.org_tree_id,t.org_node_id,t.parent_node_id,t.name,t.organ_node_type_id," +
				" t.description ,o.root_node_id,t.orders from (" +
				" select c1.*,c2.name,c2.description,c2.organ_node_type_id from cs_organ_model c1,cs_organ_node c2 " +
				" where c1.org_node_id = c2.id " +
				")t left join t_organ_relation o on t.org_node_id = o.node_id ) r";
		sql = "select * from ("+allSql+") s where s.parent_node_id is not null";
		if("2500".equals(rootId)){
			sql += " or s.org_node_id=2500";
		}
		sql = "select * from ("+sql+") g start with g.org_node_id ="+rootId+" connect by prior g.org_node_id=g.parent_node_id" ;
		sql = "select distinct h.* from ("+sql+") h " +
				" start with h.org_node_id in ("+nodeId+") connect by prior h.parent_node_id=h.org_node_id ";
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				OrganNodeVo vo = new OrganNodeVo();
				Long id = rs.getLong("org_node_id");
				Long pid = rs.getLong("parent_node_id");
				if(map.containsKey(id) && !rootId.equals(id+"")){
					vo.setId(map.get(id));
					vo.setIsRoot("1");
					vo.setRootId(id);
				}else{
					vo.setId(id);	
					if(rootId.equals(id+"")){
						vo.setIsRoot("1");					
					}else{
						vo.setIsRoot("0");
					}
					vo.setRootId(0L);
				}
				if(map.containsKey(pid) && !rootId.equals(pid+"") ){
					vo.setParentId(map.get(pid));
				}else{
					vo.setParentId(pid);
				}			
				vo.setName(rs.getString("name"));
				vo.setDescription(rs.getString("description"));
				vo.setType(rs.getLong("organ_node_type_id"));
				
				//根节点为-1
				if(rootId.equals(id+"")){
					vo.setRootId(-1L);
				}
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}


	
	
	
//-------------------------------------------------------------------------------------------------------------------------------------
	public String returnIcon(String typeId){
		String icon = "/portal/cfconsole/users.png";
		if("2".equals(typeId)){
			icon = "/portal/cfconsole/user.png";
		}
		return icon;
	}
	
	public boolean returnParent(String typeId){
		if("2".equals(typeId)){
			return false;
		}
		return true;
	}
	
	//2500 公司 2 单位
	//xfz 得到指定节点信息
	public List<NodeVo> getNodesInfo(String nodesId){
		List<NodeVo> list = new ArrayList<NodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select distinct t.pid,t.id,t.name,t.orders,t.levels ,t.description,t.type_id from v_organ_tree t" +
				" where t.id in("+nodesId+")";
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				NodeVo vo = new NodeVo();
				vo.setId(rs.getLong("id"));
				vo.setPid(rs.getLong("pid"));
				vo.setName(rs.getString("name"));
				vo.setOrders(rs.getLong("orders"));
				vo.setLevels(rs.getLong("levels"));
				vo.setDescription(rs.getString("description"));
				vo.setIcon(returnIcon(rs.getInt("type_id")+""));
				vo.setTypeId(rs.getInt("type_id")+"");
				vo.setIsParent(returnParent(rs.getInt("type_id")+""));
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 得到关联接点
	public List<NodeVo> getRelatedNodes(String nodesId){
		List<NodeVo> list = new ArrayList<NodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select distinct t.pid,t.id,t.name,t.orders,t.levels ,t.description ,t.type_id from v_organ_tree t" +
				" start with t.id in(" +nodesId+")"+
				" connect by prior t.pid = t.id" +
				" order by t.levels,t.orders";
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				if("2".equals(rs.getInt("type_id")+"")){
					
				}else{
					NodeVo vo = new NodeVo();
					vo.setId(rs.getLong("id"));
					vo.setPid(rs.getLong("pid"));
					vo.setName(rs.getString("name"));
					vo.setOrders(rs.getLong("orders"));
					vo.setLevels(rs.getLong("levels"));
					vo.setDescription(rs.getString("description"));
					vo.setIcon(returnIcon(rs.getInt("type_id")+""));
					vo.setTypeId(rs.getInt("type_id")+"");
					vo.setIsParent(returnParent(rs.getInt("type_id")+""));
					list.add(vo);
				}
				
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 异步加载子节点
	public List<NodeVo> getChildNodes(String pid){
		List<NodeVo> list = new ArrayList<NodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select distinct t.pid,t.id,t.name,t.orders,t.levels ,t.description,t.type_id from v_organ_tree t" +
				" where t.pid="+pid +" order by t.orders";
		HashMap<String,String> rmap = OrganTreeUtil.getReceiversMap();
		HashMap<String,String> cmap = OrganTreeUtil.getConfigersMap();
		//System.out.println(rmap.size());
		//System.out.println(cmap.size());
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				NodeVo vo = new NodeVo();
				vo.setId(rs.getLong("id"));
				vo.setPid(rs.getLong("pid"));
				vo.setName(rs.getString("name"));
				vo.setOrders(rs.getLong("orders"));
				vo.setLevels(rs.getLong("levels"));
				vo.setDescription(rs.getString("description"));
				vo.setIcon(returnIcon(rs.getInt("type_id")+""));
				vo.setTypeId(rs.getInt("type_id")+"");
				vo.setIsParent(returnParent(rs.getInt("type_id")+""));
				if(rmap.containsKey(rs.getLong("id")+"")||cmap.containsKey(rs.getLong("id")+"")){
					//System.out.println("可选："+rs.getLong("id"));
					vo.setDoCheck(true);
				}else{
					//System.out.println("禁止选："+rs.getLong("id"));
					vo.setDoCheck(false);
				}
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 得到部门领导层信息
	public List<UserNodeVo> getDeptLeaders(String pid){
		List<UserNodeVo> list = new ArrayList<UserNodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select t.id,t.login_name,t.name,t.dept_id,t.orders from v_dept_leaders t where t.Dept_id in ("+pid+") order by t.Dept_id,t.orders";
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				UserNodeVo vo = new UserNodeVo();
				vo.setId(rs.getLong("id"));
				vo.setPid(rs.getLong("dept_id"));
				vo.setName(rs.getString("name"));
				vo.setOrders(rs.getLong("orders"));
				vo.setLoginName(rs.getString("login_name"));
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 获取部门大领导信息
	public List<UserNodeVo> getDeptSingleLeader(String pid){
		List<UserNodeVo> list = new ArrayList<UserNodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select t.id,t.login_name,t.name,t.dept_id,t.orders from v_dept_single_leader t where t.Dept_id in ("+pid+") order by t.Dept_id,t.orders";
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				UserNodeVo vo = new UserNodeVo();
				vo.setId(rs.getLong("id"));
				vo.setPid(rs.getLong("dept_id"));
				vo.setName(rs.getString("name"));
				vo.setOrders(rs.getLong("orders"));
				vo.setLoginName(rs.getString("login_name"));
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 获取部门收发员信息
	public List<UserNodeVo> getDeptReceivers(String pid){
		List<UserNodeVo> list = new ArrayList<UserNodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select t.id,t.login_name,t.name,t.dept_id,t.orders from v_dept_receivers t where t.Dept_id in ("+pid+") order by t.Dept_id,t.orders";
		try{
			//System.out.println(sql);
			ResultSet rs=dealsql.ExecuteDemandSql(sql);
			while(rs.next()){
				UserNodeVo vo = new UserNodeVo();
				vo.setId(rs.getLong("id"));
				vo.setPid(rs.getLong("dept_id"));
				vo.setName(rs.getString("name"));
				vo.setOrders(rs.getLong("orders"));
				vo.setLoginName(rs.getString("login_name"));
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 获取部门人员信息
	public List<UserNodeVo> getDeptUsers(String pid){
		List<UserNodeVo> list = new ArrayList<UserNodeVo>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select t.id,t.login_name,t.name,t.dept_id,t.orders from v_userdep t where t.Dept_id in ("+pid+") order by t.Dept_id,t.orders";
		try{
			ResultSet rs=dealsql.ExecuteDemandSql(sql);

			while(rs.next()){
				UserNodeVo vo = new UserNodeVo();
				vo.setId(rs.getLong("id"));
				vo.setPid(rs.getLong("dept_id"));
				vo.setName(rs.getString("name"));
				vo.setOrders(rs.getLong("orders"));
				vo.setLoginName(rs.getString("login_name"));
				list.add(vo);
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return list;
	}
	
	//xfz 获取部门收发员
	public Map<String,String> getReceivers(){
		Map<String,String> map = new HashMap<String,String>();
		ExecuteSql dealsql= new ExecuteSql("dataSource");
		String sql = "select t.login_name,t.dept_id from v_dept_receivers t";
		try{
			ResultSet rs=dealsql.ExecuteDemandSql(sql);

			while(rs.next()){
				map.put(rs.getLong("dept_id")+"",rs.getString("login_name") );
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return map;
	}
	
	//xfz 获取部门配置人员
	public Map<String,String> getConfigers(){
		Map<String,String> map = new HashMap<String,String>();
		ExecuteSql dealsql= new ExecuteSql("dataSource2");
		String sql = "select t.deptid,t.initiator from t_dept_contact_config t where t.removed=0";
		try{
			ResultSet rs=dealsql.ExecuteDemandSql(sql);

			while(rs.next()){
				map.put(rs.getString("deptid"), rs.getString("initiator"));
			}
			dealsql.close();
		}catch(Exception e){
			//System.out.println("getDeptNameById has an error::"+e);
		}
		return map;
	}
	
	
	//获取部门人员信息(去除领导)
	public List<UserNodeVo> getDeptUsersOffLeaders(String pid){
		List<UserNodeVo> deptUsers = getDeptUsers(pid);
		List<UserNodeVo> deptLeaders = getDeptLeaders(pid);
		deptUsers.removeAll(deptLeaders);
		return deptUsers;
	}
	
}
