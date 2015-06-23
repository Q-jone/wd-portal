/**
 * 
 */
package com.wonders.stpt.operationIndicator.entity.vo;

import java.util.List;

import com.wonders.stpt.indicatorControl.entity.vo.MetroQualityControlVO;

/** 
 * @ClassName: ChartData 
 * @Description: MetroQuality返回到页面的数据
 * @author ycl
 * @date 2012-4-26 上午11:11:54 
 *  
 */
public class ChartDataQuality {

	private QualityVo lastestQuality;		//最近一条友数据
	private QualityVo lastYearQuality;		//去年同期的数据
	private List<String> dateList;				//日期
	private List<Double> onTimeList;			//正点率
	private List<Double> onWorkList;			//兑现率
	
	private List<String> ontimeControlValueList;	//正点率管控值
	private List<String> onworkControlValueList;	//兑现率管控值
	
	private MetroQualityControlVO metroQualityControlVO;	//最近一天有数据的管控值
	
	public MetroQualityControlVO getMetroQualityControlVO() {
		return metroQualityControlVO;
	}
	public void setMetroQualityControlVO(MetroQualityControlVO metroQualityControlVO) {
		this.metroQualityControlVO = metroQualityControlVO;
	}
	public QualityVo getLastestQuality() {
		return lastestQuality;
	}
	public void setLastestQuality(QualityVo lastestQuality) {
		this.lastestQuality = lastestQuality;
	}
	public QualityVo getLastYearQuality() {
		return lastYearQuality;
	}
	public void setLastYearQuality(QualityVo lastYearQuality) {
		this.lastYearQuality = lastYearQuality;
	}
	public List<String> getDateList() {
		return dateList;
	}
	public void setDateList(List<String> dateList) {
		this.dateList = dateList;
	}
	public List<Double> getOnTimeList() {
		return onTimeList;
	}
	public void setOnTimeList(List<Double> onTimeList) {
		this.onTimeList = onTimeList;
	}
	public List<Double> getOnWorkList() {
		return onWorkList;
	}
	public void setOnWorkList(List<Double> onWorkList) {
		this.onWorkList = onWorkList;
	}
	
	public List<String> getOntimeControlValueList() {
		return ontimeControlValueList;
	}
	public void setOntimeControlValueList(List<String> ontimeControlValueList) {
		this.ontimeControlValueList = ontimeControlValueList;
	}
	public List<String> getOnworkControlValueList() {
		return onworkControlValueList;
	}
	public void setOnworkControlValueList(List<String> onworkControlValueList) {
		this.onworkControlValueList = onworkControlValueList;
	}
	
	public ChartDataQuality(QualityVo lastestQuality,
			QualityVo lastYearQuality, List<String> dateList,
			List<Double> onTimeList, List<Double> onWorkList,
			List<String> ontimeControlValueList,
			List<String> onworkControlValueList,
			MetroQualityControlVO metroQualityControlVO) {
		this.lastestQuality = lastestQuality;
		this.lastYearQuality = lastYearQuality;
		this.dateList = dateList;
		this.onTimeList = onTimeList;
		this.onWorkList = onWorkList;
		this.ontimeControlValueList = ontimeControlValueList;
		this.onworkControlValueList = onworkControlValueList;
		this.metroQualityControlVO = metroQualityControlVO;
	}
	public ChartDataQuality() {
	}
	
}
