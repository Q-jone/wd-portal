/**
 * 
 */
package com.wonders.stpt.bid.service;

import java.util.Map;

import com.wonders.stpt.bid.model.bo.TBidPlan;
import com.wonders.stpt.bid.model.vo.BidPlanListVo;
import com.wonders.stpt.page.model.PageResultSet;

/** 
 * @ClassName: BidService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013年12月7日 下午7:28:16 
 *  
 */
public interface BidService {
	public String save(TBidPlan bo);
	public String update(TBidPlan bo);
	public TBidPlan load(String id);
	public PageResultSet<Map<String,Object>> list(BidPlanListVo vo);
	public PageResultSet<Map<String,Object>> listAll(BidPlanListVo vo);
}
