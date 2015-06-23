/**
 * 
 */
package com.wonders.stpt.marquee.dao;

import java.util.List;

import com.wonders.stpt.marquee.model.bo.MarqueeConfigBo;
import com.wonders.stpt.marquee.model.bo.MarqueeMsgBo;

/** 
 * @ClassName: MarqueeDao 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月26日 上午11:31:05 
 *  
 */
public interface MarqueeDao {
	public MarqueeConfigBo getConfig();
	public List<MarqueeMsgBo> getList(int size,String loginNames);
}
