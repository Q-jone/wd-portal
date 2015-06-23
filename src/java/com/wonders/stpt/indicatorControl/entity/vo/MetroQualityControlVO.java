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

package com.wonders.stpt.indicatorControl.entity.vo;

import java.text.DecimalFormat;

import com.wonders.stpt.indicatorControl.entity.bo.MetroQualityControl;
import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * MetroQualityControlʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroQualityControlVO implements ValueObject {

	private static final long serialVersionUID = 8029769873234589235L;
	private String id;
	private String ext1;
	private String ext2;
	private String ext3;
	private String indicatorLine;
	private String metroOntimeDaily;
	private String metroOntimeMonth;
	private String metroOntimeYear;
	private String metroOnworkDaily;
	private String metroOnworkMonth;
	private String metroOnworkYear;
	private String operatePerson;
	private String operateTime;
	private String removed;
	
	private String ontimeControl;
	private String onworkControl;
	private String ontimeDescribe;
	private String onworkDescribe;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getExt3() {
		return ext3;
	}

	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}

	public String getIndicatorLine() {
		return indicatorLine;
	}

	public void setMetroOntimeDaily(String metroOntimeDaily) {
		this.metroOntimeDaily = metroOntimeDaily;
	}

	public String getMetroOntimeDaily() {
		return metroOntimeDaily;
	}

	public void setMetroOntimeMonth(String metroOntimeMonth) {
		this.metroOntimeMonth = metroOntimeMonth;
	}

	public String getMetroOntimeMonth() {
		return metroOntimeMonth;
	}

	public void setMetroOntimeYear(String metroOntimeYear) {
		this.metroOntimeYear = metroOntimeYear;
	}

	public String getMetroOntimeYear() {
		return metroOntimeYear;
	}

	public void setMetroOnworkDaily(String metroOnworkDaily) {
		this.metroOnworkDaily = metroOnworkDaily;
	}

	public String getMetroOnworkDaily() {
		return metroOnworkDaily;
	}

	public void setMetroOnworkMonth(String metroOnworkMonth) {
		this.metroOnworkMonth = metroOnworkMonth;
	}

	public String getMetroOnworkMonth() {
		return metroOnworkMonth;
	}

	public void setMetroOnworkYear(String metroOnworkYear) {
		this.metroOnworkYear = metroOnworkYear;
	}

	public String getMetroOnworkYear() {
		return metroOnworkYear;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	public String getRemoved() {
		return removed;
	}

	public MetroQualityControlVO() {
		super();
	}

	public String killLastZero(String str){
		if(str.contains(".") && (str.endsWith("0") || str.endsWith("."))){
			return killLastZero(str.substring(0,str.length()-1));
		}
		return str;
	}
	
	public String format(Double d){
		DecimalFormat df = new DecimalFormat("#0.00");
		return df.format(d);
	}
	
	public String getOntimeControl() {
		return ontimeControl;
	}

	public void setOntimeControl(String ontimeControl) {
		this.ontimeControl = ontimeControl;
	}

	public String getOnworkControl() {
		return onworkControl;
	}

	public void setOnworkControl(String onworkControl) {
		this.onworkControl = onworkControl;
	}

	public String getOntimeDescribe() {
		return ontimeDescribe;
	}

	public void setOntimeDescribe(String ontimeDescribe) {
		this.ontimeDescribe = ontimeDescribe;
	}

	public String getOnworkDescribe() {
		return onworkDescribe;
	}

	public void setOnworkDescribe(String onworkDescribe) {
		this.onworkDescribe = onworkDescribe;
	}

	public MetroQualityControlVO(MetroQualityControl mqc) {
		this.id = mqc.getId();
		this.ext1 = mqc.getExt1();
		this.ext2 = mqc.getExt2();
		this.ext3 = mqc.getExt3();
		this.indicatorLine = mqc.getIndicatorLine();
		if(mqc.getMetroOntimeDaily()!=null && !"".equals(mqc.getMetroOntimeDaily())){
			this.metroOntimeDaily = killLastZero(format(mqc.getMetroOntimeDaily()*100D));
		}
		
		if(mqc.getMetroOntimeMonth()!=null && !"".equals(mqc.getMetroOntimeMonth())){
			this.metroOntimeMonth = killLastZero(format(mqc.getMetroOntimeMonth()*100D));
		}
		
		if(mqc.getMetroOntimeYear()!=null && !"".equals(mqc.getMetroOntimeYear())){
			this.metroOntimeYear = killLastZero(format(mqc.getMetroOntimeYear()*100D));
		}
		
		if(mqc.getMetroOnworkDaily()!=null && !"".equals(mqc.getMetroOnworkDaily())){
			this.metroOnworkDaily = killLastZero(format(mqc.getMetroOnworkDaily()*100D));
		}
		
		if(mqc.getMetroOnworkMonth()!=null && !"".equals(mqc.getMetroOnworkMonth())){
			this.metroOnworkMonth = killLastZero(format(mqc.getMetroOnworkMonth()*100D));
		}
		
		if(mqc.getMetroOnworkYear()!=null && !"".equals(mqc.getMetroOnworkYear())){
			this.metroOnworkYear = killLastZero(format(mqc.getMetroOnworkYear()*100D));
		}
		
		this.operatePerson = mqc.getOperatePerson();
		this.operateTime = mqc.getOperateTime();
		this.removed = mqc.getRemoved();
		
		this.ontimeControl = mqc.getOntimeControl();
		this.ontimeDescribe = mqc.getOntimeDescribe();
		this.onworkControl = mqc.getOnworkControl();
		this.onworkDescribe = mqc.getOnworkDescribe();
	}
	
	

}
