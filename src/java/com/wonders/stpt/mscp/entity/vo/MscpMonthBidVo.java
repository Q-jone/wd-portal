/**
 * 
 */
package com.wonders.stpt.mscp.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.wonders.stpt.mscp.entity.bo.MscpMonthBid;

/** 
 * @ClassName: MscpMonthBidVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年2月12日 下午1:37:44 
 *  
 */
public class MscpMonthBidVo {
	public List<String> xs = new ArrayList<String>();
	public List<List<MscpMonthBid>> ys = new ArrayList<List<MscpMonthBid>>();
	public List<String> getXs() {
		return xs;
	}
	public void setXs(List<String> xs) {
		this.xs = xs;
	}
	public List<List<MscpMonthBid>> getYs() {
		return ys;
	}
	public void setYs(List<List<MscpMonthBid>> ys) {
		this.ys = ys;
	}
	
	public void putX(String x){
		xs.add(x);
	}
	
	public void putY(List<MscpMonthBid> y){
		ys.add(y);
	}
	
	
}
