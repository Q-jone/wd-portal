package com.wonders.stpt.union.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.page.model.PageResultSet;
import com.wonders.stpt.union.dao.UnionTDao;
import com.wonders.stpt.union.entity.bo.UnionApplyDepartment;
import com.wonders.stpt.union.entity.bo.prize.UnionPersonalPrize;
import com.wonders.stpt.union.service.UnionApplyDepartmentService;

@Repository("unionApplyDepartmentService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionApplyDepartmentServiceImpl implements UnionApplyDepartmentService{
	
	private UnionTDao<UnionApplyDepartment> dao;
	public UnionTDao<UnionApplyDepartment> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionApplyDepartment> dao) {
		this.dao = dao;
	}

	@Override
	public UnionApplyDepartment find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionApplyDepartment.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}

	@Override
	public UnionApplyDepartment save(UnionApplyDepartment bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	@Override
	public void saveOrUpdateAll(List<UnionApplyDepartment> applyDepartments){
		this.dao.saveOrUpdateAll(applyDepartments);
	}
	
	@Override
	public int logicDelete(String id){
		return this.dao.excuteHQLUpdate("update UnionApplyDepartment set removed = 1 where id = ?", new Object[]{id});
	}
	
	@Override
	public List<UnionApplyDepartment> findByPrizeId(String prizeId) {
		// TODO Auto-generated method stub
		return dao.findByHql("from UnionApplyDepartment where removed = 0 and prizeId = ?",new Object[]{prizeId});
	}
	
	public void updateQuantity(List<UnionApplyDepartment> applyDepartments){
		List<UnionApplyDepartment> updateList = new ArrayList<UnionApplyDepartment>();
		for(UnionApplyDepartment applyDept : applyDepartments){
			UnionApplyDepartment o = this.find(applyDept.getId());
			o.setQuantity(applyDept.getQuantity());
			updateList.add(o);
		}
		this.dao.saveOrUpdateAll(updateList);
	}
	
	@Override
	public List<UnionApplyDepartment> listToApply(String matchId, String deptId){
		String hql = "from UnionApplyDepartment a left join fetch a.prize p where a.removed = 0 and a.deptId = ? and p.matchId = ?";
		return this.dao.findByHql(hql,new Object[]{deptId,matchId});
	}
	
	public List<UnionApplyDepartment> applyDeptsOfMatch(String matchId){
		String hql = "from UnionApplyDepartment a left join fetch a.prize p where a.removed = 0 and p.matchId = ?";
		List<UnionApplyDepartment> allDepts = this.dao.findByHql(hql,new Object[]{matchId});
		List<UnionApplyDepartment> distinctDepts = new ArrayList<UnionApplyDepartment>();
		Map deptIds = new HashMap();
		for(UnionApplyDepartment applyDept : allDepts){
			if(!deptIds.containsKey(applyDept.getDeptId())){
				deptIds.put(applyDept.getDeptId(), null);
				distinctDepts.add(applyDept);
			}
		}
		return distinctDepts;
	}
	
	public List<UnionApplyDepartment> findByPrizeAndDeptId(String prizeId,String deptId){
		String hql = "from UnionApplyDepartment p where p.removed = 0 and p.prizeId = ? and p.deptId = ?";
		return this.dao.findByHql(hql, new Object[]{prizeId,deptId});
	}	
	
	public int getQuantityAssignedSumOfPrize(String prizeId){
		String hql = "select sum(p.quantity) from UnionApplyDepartment p where p.removed = 0 and p.prizeId = '"+prizeId+"'";
		return this.dao.countByHql(hql);
	}
}
