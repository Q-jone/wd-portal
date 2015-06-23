/**   
* @Title: ResourceIndicateVo.java 
* @Package com.wonders.stpt.resource.model.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhoushun   
* @date 2014年4月29日 下午8:58:10 
* @version V1.0   
*/
package com.wonders.stpt.resource.model.vo;

import com.wonders.stpt.resource.model.bo.ResourceIndicate;

/** 
 * @ClassName: ResourceIndicateVo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2014年4月29日 下午8:58:10 
 *  
 */
public class ResourceIndicateVo {
	private String indicatorDate;
	private String indicatorLine;
	private Double TractionPowerDaily;
	private Double TractionPowerMonth;
	private Double TractionPowerYear;
	private Double ElectricPowerDaily;
	private Double ElectricPowerMonth;
	private Double ElectricPowerYear;
	
	public ResourceIndicateVo(){}
	
	public ResourceIndicateVo(ResourceIndicate bo){
		
	}

	public String getIndicatorDate() {
		return indicatorDate;
	}

	public void setIndicatorDate(String indicatorDate) {
		this.indicatorDate = indicatorDate;
	}

	public String getIndicatorLine() {
		return indicatorLine;
	}

	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}

	public Double getTractionPowerDaily() {
		return TractionPowerDaily;
	}

	public void setTractionPowerDaily(Double tractionPowerDaily) {
		TractionPowerDaily = tractionPowerDaily;
	}

	public Double getTractionPowerMonth() {
		return TractionPowerMonth;
	}

	public void setTractionPowerMonth(Double tractionPowerMonth) {
		TractionPowerMonth = tractionPowerMonth;
	}

	public Double getTractionPowerYear() {
		return TractionPowerYear;
	}

	public void setTractionPowerYear(Double tractionPowerYear) {
		TractionPowerYear = tractionPowerYear;
	}

	public Double getElectricPowerDaily() {
		return ElectricPowerDaily;
	}

	public void setElectricPowerDaily(Double electricPowerDaily) {
		ElectricPowerDaily = electricPowerDaily;
	}

	public Double getElectricPowerMonth() {
		return ElectricPowerMonth;
	}

	public void setElectricPowerMonth(Double electricPowerMonth) {
		ElectricPowerMonth = electricPowerMonth;
	}

	public Double getElectricPowerYear() {
		return ElectricPowerYear;
	}

	public void setElectricPowerYear(Double electricPowerYear) {
		ElectricPowerYear = electricPowerYear;
	}
	
	
}
