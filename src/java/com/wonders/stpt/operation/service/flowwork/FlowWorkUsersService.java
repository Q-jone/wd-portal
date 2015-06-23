package com.wonders.stpt.operation.service.flowwork;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.bo.FlowWorkUsers;

@Repository("flowWorkUsersService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class FlowWorkUsersService {
	
	private OpTDao<FlowWorkUsers> dao;
	public OpTDao<FlowWorkUsers> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("opTDao")OpTDao<FlowWorkUsers> dao) {
		this.dao = dao;
	}

	public List<FlowWorkUsers> findByTypeId(Long typeId) {
		// TODO Auto-generated method stub
		return dao.findByHql("from FlowWorkUsers t where t.flowTypeId = ? order by t.orderIndex",new Object[]{typeId});
	}
	
	public int doHql(String hql,Object[] obj) {
		// TODO Auto-generated method stub
		return dao.excuteHQLUpdate(hql, obj);
	}
}
