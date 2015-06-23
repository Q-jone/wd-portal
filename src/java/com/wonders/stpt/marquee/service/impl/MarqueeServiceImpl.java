/**
 * 
 */
package com.wonders.stpt.marquee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.wonders.stpt.marquee.dao.MarqueeDao;
import com.wonders.stpt.marquee.model.bo.MarqueeConfigBo;
import com.wonders.stpt.marquee.model.bo.MarqueeMsgBo;
import com.wonders.stpt.marquee.model.vo.MarqueeMsgVo;
import com.wonders.stpt.marquee.service.MarqueeService;

/** 
 * @ClassName: MarqueeServiceImpl 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月26日 下午1:29:31 
 *  
 */
@Service("marqueeService")
@Transactional(value="txManager2",propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class MarqueeServiceImpl implements MarqueeService{
	private MarqueeDao dao;

	public MarqueeDao getDao() {
		return dao;
	}

	@Autowired(required=false)
	public void setDao(@Qualifier("marqueeDao")MarqueeDao dao) {
		this.dao = dao;
	}
	
	
	public MarqueeMsgVo getMsg(String loginNames){
		MarqueeMsgVo vo = new MarqueeMsgVo();
		MarqueeConfigBo config = this.dao.getConfig();
		int count = 10;
		int limit = 50;
		int speed = 200;
		try{
			count = Integer.parseInt(config.getCount());
		}catch(Exception e){
		}
		try{
			limit = Integer.parseInt(config.getLimit());
		}catch(Exception e){
		}
		try{
			speed = Integer.parseInt(config.getSpeed());
		}catch(Exception e){
		}
		
		List<MarqueeMsgBo> msg = this.dao.getList(count,loginNames);
		vo.setCount(count);
		vo.setLimit(limit);
		vo.setSpeed(speed);
		vo.setList(msg);
		return vo;
	}
	
}
