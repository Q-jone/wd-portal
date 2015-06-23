package com.wonders.stpt.union.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionMatch;
import com.wonders.stpt.union.service.UnionOrganService;

@Repository("unionOrganService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionOrganServiceImpl implements UnionOrganService{
	
	private UnionTDao<UnionMatch> dao;
	public UnionTDao<UnionMatch> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionMatch> dao) {
		this.dao = dao;
	}

	@Override
	public List<Map> getDepts(){
		return this.dao.findBySql("SELECT ID \"id\",PID \"pId\",NAME \"name\",'d' \"nodeType\",ORDERS,LEVELS,DECODE(LEVELS,'0','true','1','true') \"isParent\" FROM  V_ORGAN_TREE WHERE ID = 2500 OR (LEVELS = 1 and TYPE_ID = 2)");
	}
	
	@Override
	public List<Map> getDirectDepts(){
		return this.dao.findBySql("SELECT ID \"id\",PID \"pId\",NAME \"name\" FROM  V_ORGAN_TREE WHERE LEVELS = 1 and TYPE_ID = 2 and PID = 2500");
	}

	@Override
	public List<Map> getEmployees(String deptId){
		return this.dao.findBySql("SELECT '1'||ID \"id\",DEPT_ID \"pId\",NAME \"name\",LOGIN_NAME \"loginName\",'e' \"nodeType\" FROM V_USERDEP WHERE DEPT_ID='"+deptId+"' ORDER BY NAME");
	}
	
	@Override
	public Map getSingleLeader(String deptId){
		List<Map> l = this.dao.findBySql("SELECT LOGIN_NAME \"loginName\",NAME \"name\" FROM V_DEPT_SINGLE_LEADER WHERE DEPT_ID = '"+deptId+"'");
		if(l.size() > 0){
			return l.get(0);
		}
		return null;
	}
	
	@Override
	public List<Map> getDeptLeaders(String deptId){
		return this.dao.findBySql("SELECT LOGIN_NAME \"loginName\",NAME \"name\" FROM V_DEPT_LEADERS WHERE DEPT_ID = '"+deptId+"'");
	}
	
	@Override
	public List<Map> fuzzySearchEmployees(String deptId, String key){
		String sql = "SELECT DEPT_ID \"deptId\",NAME \"name\",LOGIN_NAME \"loginName\",'e' \"nodeType\" FROM V_USERDEP WHERE DEPT_ID='"+deptId+"'";
		if(!StringUtils.isEmpty(key)){
			sql += " AND (NAME LIKE '%" + key + "%' OR LOGIN_NAME LIKE '%" + key + "%')";
		}
		sql += " ORDER BY NAME";
		return this.dao.findBySql(sql);
	}
}
