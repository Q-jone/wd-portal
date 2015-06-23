package com.wonders.stpt.union.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionAccountApply;
import com.wonders.stpt.union.service.UnionAccountApplyService;

@Repository("unionAccountApplyService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionAccountApplyServiceImpl implements UnionAccountApplyService{
	
	private UnionTDao<UnionAccountApply> dao;
	public UnionTDao<UnionAccountApply> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionAccountApply> dao) {
		this.dao = dao;
	}

	@Override
	public UnionAccountApply find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionAccountApply.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionAccountApply save(UnionAccountApply bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	@Override
	public void logicDelete(String id){
		this.dao.excuteHQLUpdate("update UnionAccountApply set removed = 1 where id = ?", new Object[]{id});
	}
	
	public int updateStatus(String id, int status){
		return this.dao.excuteHQLUpdate("update UnionAccountApply set status = ? where id = ?", new Object[]{status, id});
	}
	
}
