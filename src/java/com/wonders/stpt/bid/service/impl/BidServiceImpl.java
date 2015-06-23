/**
 * 
 */
package com.wonders.stpt.bid.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.wonders.stpt.bid.dao.BidDao;
import com.wonders.stpt.bid.model.bo.TBidPlan;
import com.wonders.stpt.bid.model.vo.BidPlanListVo;
import com.wonders.stpt.bid.service.BidService;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: BidServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:28:01 
 *  
 */
@Service("bidService")
@Transactional(value="txManager",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class BidServiceImpl implements BidService{
	private BidDao bidDao;

	public BidDao getBidDao() {
		return bidDao;
	}
	@Autowired(required=false)
	public void setBidDao(@Qualifier("bidDao")BidDao bidDao) {
		this.bidDao = bidDao;
	}
	
	public String save(TBidPlan bo){
		return this.bidDao.save(bo);
	}
	public String update(TBidPlan bo){
		return this.bidDao.update(bo);
	}
	
	public TBidPlan load(String id){
		return this.bidDao.load(id);
	}
	
	public PageResultSet<Map<String,Object>> list(BidPlanListVo vo){
		return this.bidDao.list(vo);
	}
	
	public PageResultSet<Map<String,Object>> listAll(BidPlanListVo vo){
		return this.bidDao.listAll(vo);
	}
}
