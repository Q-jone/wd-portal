/** 
 * Copyright (c) 1995-2011 Wonders Information Co.,Ltd. 
 * 1518 Lianhang Rd,Shanghai 201112.P.R.C.
 * All Rights Reserved.
 * 
 * This software is the confidential and proprietary information of WondersGroup.
 * You shall not disclose such Confidential Information and shall use it only in accordance 
 * with the terms of the license agreement you entered into with WondersGroup. 
 *
 */

package com.wonders.stpt.operationIndicator.entity.vo;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.wonders.stpt.indicatorControl.entity.vo.MetroQualityControlVO;
import com.wonders.stpt.metroIndicator.entity.bo.MetroQuality;


public class QualityVo {

	private String id; // id

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

	private String indicatorDate; // indicatorDate

	private String indicatorLine; // indicatorLine

	private Double metroOntimeDaily; // metroOntimeDaily

	private Double metroOntimeMonth; // metroOntimeMonth

	private Double metroOntimeYear; // metroOntimeYear

	private Double metroOnworkDaily; // metroOnworkDaily

	private Double metroOnworkMonth; // metroOnworkMonth

	private Double metroOnworkYear; // metroOnworkYear

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private String removed; // removed
	
	private QualityVo lastyearQualityVo;
	
	private MetroQualityControlVO metroQualityControlVO;
	
	public MetroQualityControlVO getMetroQualityControlVO() {
		return metroQualityControlVO;
	}

	public void setMetroQualityControlVO(MetroQualityControlVO metroQualityControlVO) {
		this.metroQualityControlVO = metroQualityControlVO;
	}

	public QualityVo getLastyearQualityVo() {
		return lastyearQualityVo;
	}

	public void setLastyearQualityVo(QualityVo lastyearQualityVo) {
		this.lastyearQualityVo = lastyearQualityVo;
	}

	public QualityVo(){
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
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

	public Double getMetroOntimeDaily() {
		return metroOntimeDaily;
	}

	public void setMetroOntimeDaily(Double metroOntimeDaily) {
		this.metroOntimeDaily = metroOntimeDaily;
	}

	public Double getMetroOntimeMonth() {
		return metroOntimeMonth;
	}

	public void setMetroOntimeMonth(Double metroOntimeMonth) {
		this.metroOntimeMonth = metroOntimeMonth;
	}

	public Double getMetroOntimeYear() {
		return metroOntimeYear;
	}

	public void setMetroOntimeYear(Double metroOntimeYear) {
		this.metroOntimeYear = metroOntimeYear;
	}

	public Double getMetroOnworkDaily() {
		return metroOnworkDaily;
	}

	public void setMetroOnworkDaily(Double metroOnworkDaily) {
		this.metroOnworkDaily = metroOnworkDaily;
	}

	public Double getMetroOnworkMonth() {
		return metroOnworkMonth;
	}

	public void setMetroOnworkMonth(Double metroOnworkMonth) {
		this.metroOnworkMonth = metroOnworkMonth;
	}

	public Double getMetroOnworkYear() {
		return metroOnworkYear;
	}

	public void setMetroOnworkYear(Double metroOnworkYear) {
		this.metroOnworkYear = metroOnworkYear;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getRemoved() {
		return removed;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	//乘100,保留2位小数
	public Object getFormatData(Double data){
		if(data==null) return null;
		data = data*100;
		NumberFormat nf = new DecimalFormat("0.00"); 
		return Double.valueOf(nf.format(data));
	}
	
	public QualityVo(MetroQuality quality) {
		this.id = quality.getId();
		this.ext1 = quality.getExt1();
		this.ext2 = quality.getExt2();
		this.ext3 = quality.getExt3();
		this.indicatorDate = quality.getIndicatorDate();
		this.indicatorLine = quality.getIndicatorLine();
		this.metroOntimeDaily = (Double)getFormatData(quality.getMetroOntimeDaily());
		this.metroOntimeMonth = (Double)getFormatData(quality.getMetroOntimeMonth());
		this.metroOntimeYear = (Double)getFormatData(quality.getMetroOntimeYear());
		this.metroOnworkDaily = (Double)getFormatData(quality.getMetroOnworkDaily());
		this.metroOnworkMonth = (Double)getFormatData(quality.getMetroOnworkMonth());
		this.metroOnworkYear = (Double)getFormatData(quality.getMetroOnworkYear());
		this.operatePerson = quality.getOperatePerson();
		this.operateTime = quality.getOperateTime();
		this.removed = quality.getRemoved();
	}
	
	

}
