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

package com.wonders.stpt.indicatorControl.entity.bo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.wonders.stpt.indicatorControl.entity.vo.MetroQualityControlVO;
import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * MetroQualityControlʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_METRO_QUALITY_CONTROL")
public class MetroQualityControl implements Serializable, BusinessObject {

	private static final long serialVersionUID = -8979380838916506955L;

	private String id; // id

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

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
	
	
	private String ontimeControl;	//正点率，管控类型
	private String onworkControl;	//兑现率，管控类型
	private String ontimeDescribe;	//正点率，管控说明
	private String onworkDescribe;	//兑现率，管控说明

	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy = "uuid")
	@Column(name = "ID")
	public String getId() {
		return id;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	@Column(name = "EXT1", nullable = true, length = 200)
	public String getExt1() {
		return ext1;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	@Column(name = "EXT2", nullable = true, length = 200)
	public String getExt2() {
		return ext2;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Column(name = "EXT3", nullable = true, length = 200)
	public String getExt3() {
		return ext3;
	}

	public void setIndicatorLine(String indicatorLine) {
		this.indicatorLine = indicatorLine;
	}

	@Column(name = "INDICATOR_LINE", nullable = true, length = 50)
	public String getIndicatorLine() {
		return indicatorLine;
	}

	public void setMetroOntimeDaily(Double metroOntimeDaily) {
		this.metroOntimeDaily = metroOntimeDaily;
	}

	@Column(name = "METRO_ONTIME_DAILY", nullable = true, length = 20)
	public Double getMetroOntimeDaily() {
		return metroOntimeDaily;
	}

	public void setMetroOntimeMonth(Double metroOntimeMonth) {
		this.metroOntimeMonth = metroOntimeMonth;
	}

	@Column(name = "METRO_ONTIME_MONTH", nullable = true, length = 20)
	public Double getMetroOntimeMonth() {
		return metroOntimeMonth;
	}

	public void setMetroOntimeYear(Double metroOntimeYear) {
		this.metroOntimeYear = metroOntimeYear;
	}

	@Column(name = "METRO_ONTIME_YEAR", nullable = true, length = 20)
	public Double getMetroOntimeYear() {
		return metroOntimeYear;
	}

	public void setMetroOnworkDaily(Double metroOnworkDaily) {
		this.metroOnworkDaily = metroOnworkDaily;
	}

	@Column(name = "METRO_ONWORK_DAILY", nullable = true, length = 20)
	public Double getMetroOnworkDaily() {
		return metroOnworkDaily;
	}

	public void setMetroOnworkMonth(Double metroOnworkMonth) {
		this.metroOnworkMonth = metroOnworkMonth;
	}

	@Column(name = "METRO_ONWORK_MONTH", nullable = true, length = 20)
	public Double getMetroOnworkMonth() {
		return metroOnworkMonth;
	}

	public void setMetroOnworkYear(Double metroOnworkYear) {
		this.metroOnworkYear = metroOnworkYear;
	}

	@Column(name = "METRO_ONWORK_YEAR", nullable = true, length = 20)
	public Double getMetroOnworkYear() {
		return metroOnworkYear;
	}

	public void setOperatePerson(String operatePerson) {
		this.operatePerson = operatePerson;
	}

	@Column(name = "OPERATE_PERSON", nullable = true, length = 50)
	public String getOperatePerson() {
		return operatePerson;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	@Column(name = "OPERATE_TIME", nullable = true, length = 50)
	public String getOperateTime() {
		return operateTime;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 1)
	public String getRemoved() {
		return removed;
	}
	
	@Column(name = "ONTIME_CONTROL", nullable = true, length = 1)
	public String getOntimeControl() {
		return ontimeControl;
	}

	public void setOntimeControl(String ontimeControl) {
		this.ontimeControl = ontimeControl;
	}

	@Column(name = "ONWORK_CONTROL", nullable = true, length = 1)
	public String getOnworkControl() {
		return onworkControl;
	}

	public void setOnworkControl(String onworkControl) {
		this.onworkControl = onworkControl;
	}

	@Column(name = "ONTIME_DESCRIBE", nullable = true, length = 1)
	public String getOntimeDescribe() {
		return ontimeDescribe;
	}

	public void setOntimeDescribe(String ontimeDescribe) {
		this.ontimeDescribe = ontimeDescribe;
	}

	@Column(name = "ONWORK_DESCRIBE", nullable = true, length = 1)
	public String getOnworkDescribe() {
		return onworkDescribe;
	}

	public void setOnworkDescribe(String onworkDescribe) {
		this.onworkDescribe = onworkDescribe;
	}

	public MetroQualityControl(MetroQualityControlVO vo) {
		if(vo!=null){
			this.id = vo.getId();
			this.ext1 = vo.getExt1();
			this.ext2 = vo.getExt2();
			this.ext3 = vo.getExt3();
			this.indicatorLine = vo.getIndicatorLine();
			
			if(vo.getMetroOntimeDaily()!=null && !"".equals(vo.getMetroOntimeDaily())){
				this.metroOntimeDaily = Double.valueOf(vo.getMetroOntimeDaily())/100;
			}
			
			if(vo.getMetroOntimeMonth()!=null && !"".equals(vo.getMetroOntimeMonth())){
				this.metroOntimeMonth = Double.valueOf(vo.getMetroOntimeMonth())/100;
			}
			
			if(vo.getMetroOntimeYear()!=null && !"".equals(vo.getMetroOntimeYear())){
				this.metroOntimeYear = Double.valueOf(vo.getMetroOntimeYear())/100;
			}
			
			if(vo.getMetroOnworkDaily()!=null && !"".equals(vo.getMetroOnworkDaily())){
				this.metroOnworkDaily = Double.valueOf(vo.getMetroOnworkDaily())/100;
			}
			
			if(vo.getMetroOnworkMonth()!=null && !"".equals(vo.getMetroOnworkMonth())){
				this.metroOnworkMonth = Double.valueOf(vo.getMetroOnworkMonth())/100;
			}
			
			if(vo.getMetroOnworkYear()!=null && !"".equals(vo.getMetroOnworkYear())){
				this.metroOnworkYear = Double.valueOf(vo.getMetroOnworkYear())/100;
			}
				
			this.operatePerson = vo.getOperatePerson();
			this.operateTime = vo.getOperateTime();
			this.removed = vo.getRemoved();
			
			this.ontimeControl = vo.getOntimeControl();
			this.ontimeDescribe = vo.getOntimeDescribe();
			this.onworkControl = vo.getOnworkControl();
			this.onworkDescribe = vo.getOnworkDescribe();
		}
	}

	public MetroQualityControl() {
		super();
	}
	
	

}
