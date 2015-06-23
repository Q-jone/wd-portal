package com.wonders.stpt.union.service.impl;

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
import com.wonders.stpt.union.entity.bo.UnionApproval;
import com.wonders.stpt.union.service.UnionApprovalService;

@Repository("unionApprovalService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class UnionApprovalServiceImpl implements UnionApprovalService{
	
	private UnionTDao<UnionApproval> dao;
	public UnionTDao<UnionApproval> getDao() {
		return dao;
	}
	@Autowired(required=false)
	public void setDao(@Qualifier("unionTDao")UnionTDao<UnionApproval> dao) {
		this.dao = dao;
	}

	@Override
	public UnionApproval find(String id) {
		// TODO Auto-generated method stub
		return dao.find(id,UnionApproval.class);
	}
	
	@Override
	public PageResultSet findPageResult(String queryHql,String countHql,int pageNo, int pageSize){
		return dao.findPageResult(queryHql, countHql, pageNo, pageSize);
	}
	
	@Override
	public List<UnionApproval> findByMatchId(String matchId, int stage) {
		String hql = "from UnionApproval where removed = 0 and stage = ? and matchId = ? order by checkTime desc";
		return dao.findByHql(hql,new Object[]{stage, matchId});
	}
	
	@Override
	public List<UnionApproval> findByApplyId(String applyId) {
		String hql = "from UnionApproval where removed = 0 and applyId = ? order by checkTime desc";
		return dao.findByHql(hql,new Object[]{applyId});
	}
	
	@Override
	public List<UnionApproval> findByMatchAndDeptId(String matchId, String deptId) {
		String hql = "from UnionApproval t where t.removed = 0 and exists(select id from UnionApplyMatch where deptId = ? and id = t.applyId) and t.matchId = ? order by t.checkTime desc";
		return dao.findByHql(hql,new Object[]{deptId,matchId});
	}

	@Override
	public UnionApproval save(UnionApproval bo) {
		if(bo.getId()!=null&&!"".equals(bo.getId())){
			dao.update(bo);
		}else{
			dao.save(bo);
		}
		
		return bo;
	}
	
	public Map<String,String> findFinalApprovalInfo(String applyId, String matchId, String themeId) {
		Map<String,String> ret = new HashMap<String,String>();
		String hql = "from UnionApproval where removed = 0 and ((stage = 2 and step = 2 and applyId = ?) or (stage = 1 and step = 22 and matchId = ?) or (stage = 3 and step = 3 and matchId = ?)) order by checkTime";
		List<UnionApproval> approvals = dao.findByHql(hql,new Object[]{applyId, matchId, themeId});
		for(UnionApproval approval : approvals){
			if(approval.getStage() == 2 && approval.getStep() == 2){
				ret.put("deptSuggest", approval.getRemark());
				ret.put("deptPassDate", approval.getCheckTime());
			}else if(approval.getStage() == 1 && approval.getStep() == 22){
				ret.put("assessSuggest", approval.getRemark());
				ret.put("assessPassDate", approval.getCheckTime());
			}else if(approval.getStage() == 3 && approval.getStep() == 3){
				ret.put("leadSuggest", approval.getRemark());
				ret.put("leadPassDate", approval.getCheckTime());
			}
		}
		return ret;
	}
}
