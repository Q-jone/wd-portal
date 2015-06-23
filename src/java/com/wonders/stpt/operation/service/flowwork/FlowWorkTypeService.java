package com.wonders.stpt.operation.service.flowwork;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.operation.dao.OpTDao;
import com.wonders.stpt.operation.entity.bo.FlowWorkType;

@Repository("flowWorkTypeService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class FlowWorkTypeService {
	
	private OpTDao<FlowWorkType> dao;
	public OpTDao<FlowWorkType> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("opTDao")OpTDao<FlowWorkType> dao) {
		this.dao = dao;
	}

	public FlowWorkType findByCode(String code) {
		// TODO Auto-generated method stub
		List<FlowWorkType> flowWorkTypes = dao.findByHql("from FlowWorkType t where t.flowTypeCode = ?",new Object[]{code});
		if(flowWorkTypes != null && flowWorkTypes.size() > 0){
			return flowWorkTypes.get(0);
		}
		return null;
	}
	
	public int doHql(String hql,Object[] obj) {
		// TODO Auto-generated method stub
		return dao.excuteHQLUpdate(hql, obj);
	}
}
