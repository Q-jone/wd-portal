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

package com.wonders.stpt.metroIndicator.entity.bo;

import java.util.Date;
import java.util.Set;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TMetroQuality实体定义
 * 
 * @author zhoushun
 * @version $Revision$
 * @date 2012-3-6
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_METRO_QUALITY")
public class MetroQuality implements Serializable, BusinessObject {

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

	public MetroQuality(){
		this.removed = "0";
		this.operateTime =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
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

	public void setIndicatorDate(String indicatorDate) {
		this.indicatorDate = indicatorDate;
	}

	@Column(name = "INDICATOR_DATE", nullable = true, length = 50)
	public String getIndicatorDate() {
		return indicatorDate;
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

	@Column(name = "METRO_ONTIME_DAILY",  precision=20,scale=4)
	public Double getMetroOntimeDaily() {
		return metroOntimeDaily;
	}

	public void setMetroOntimeMonth(Double metroOntimeMonth) {
		this.metroOntimeMonth = metroOntimeMonth;
	}

	@Column(name = "METRO_ONTIME_MONTH",  precision=20,scale=4)
	public Double getMetroOntimeMonth() {
		return metroOntimeMonth;
	}

	public void setMetroOntimeYear(Double metroOntimeYear) {
		this.metroOntimeYear = metroOntimeYear;
	}

	@Column(name = "METRO_ONTIME_YEAR", precision=20,scale=4)
	public Double getMetroOntimeYear() {
		return metroOntimeYear;
	}

	public void setMetroOnworkDaily(Double metroOnworkDaily) {
		this.metroOnworkDaily = metroOnworkDaily;
	}

	@Column(name = "METRO_ONWORK_DAILY",precision=20,scale=4)
	public Double getMetroOnworkDaily() {
		return metroOnworkDaily;
	}

	public void setMetroOnworkMonth(Double metroOnworkMonth) {
		this.metroOnworkMonth = metroOnworkMonth;
	}

	@Column(name = "METRO_ONWORK_MONTH", precision=20,scale=4)
	public Double getMetroOnworkMonth() {
		return metroOnworkMonth;
	}

	public void setMetroOnworkYear(Double metroOnworkYear) {
		this.metroOnworkYear = metroOnworkYear;
	}

	@Column(name = "METRO_ONWORK_YEAR", precision=20,scale=4)
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

}
