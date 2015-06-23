/**
 * 
 */
package com.wonders.stpt.marquee.model.vo;

import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.marquee.model.bo.MarqueeMsgBo;

/** 
 * @ClassName: MarqueeMsgVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月26日 上午11:29:21 
 *  
 */
public class MarqueeMsgVo {
	public int count;
	public int limit;
	public int speed;
	public List<MarqueeMsgBo> list = new ArrayList<MarqueeMsgBo>();
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public List<MarqueeMsgBo> getList() {
		return list;
	}
	public void setList(List<MarqueeMsgBo> list) {
		this.list = list;
	}
	
	
}
