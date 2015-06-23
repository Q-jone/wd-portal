/**
 * 
 */
package com.wonders.stpt.marquee.service;

import com.wonders.stpt.marquee.model.vo.MarqueeMsgVo;

/** 
 * @ClassName: MarqueeService 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月26日 下午1:30:17 
 *  
 */
public interface MarqueeService {
	public MarqueeMsgVo getMsg(String loginNames);
}
