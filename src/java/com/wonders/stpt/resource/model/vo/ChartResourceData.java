/**   
* @Title: ChartResourceData.java 
* @Package com.wonders.stpt.resource.model.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午9:05:25 
* @version V1.0   
*/
package com.wonders.stpt.resource.model.vo;

import java.util.List;

import com.wonders.stpt.resource.model.vo.ResourceIndicateVo;

/** 
 * @ClassName: ChartResourceData 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午9:05:25 
 *  
 */
public class ChartResourceData {
	//最近一天的数据
	private ResourceIndicateVo latestResource;
	//最近一天的去年同期
	private ResourceIndicateVo lastYearResource;
	//日期
	private List<String> dateList;
	//牵引
	private List<Double> tractionPowerList;
	//用电
	private List<Double> electricPowerList;

	public ResourceIndicateVo getLatestResource() {
		return latestResource;
	}

	public void setLatestResource(ResourceIndicateVo latestResource) {
		this.latestResource = latestResource;
	}

	public ResourceIndicateVo getLastYearResource() {
		return lastYearResource;
	}

	public void setLastYearResource(ResourceIndicateVo lastYearResource) {
		this.lastYearResource = lastYearResource;
	}

	public List<String> getDateList() {
		return dateList;
	}

	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}

	public List<Double> getTractionPowerList() {
		return tractionPowerList;
	}

	public void setTractionPowerList(List<Double> tractionPowerList) {
		this.tractionPowerList = tractionPowerList;
	}

	public List<Double> getElectricPowerList() {
		return electricPowerList;
	}

	public void setElectricPowerList(List<Double> electricPowerList) {
		this.electricPowerList = electricPowerList;
	}
	
	
}
