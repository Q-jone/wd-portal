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

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * MetroScaleControlʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "DW_METRO_SCALE_CONTROL")
public class MetroScaleControl implements Serializable, BusinessObject {

	private static final long serialVersionUID = -6493898485938766220L;

	private String id; // id

	private Long allocateEightSection; // allocateEightSection

	private Long allocateFourSection; // allocateFourSection

	private Long allocateSevenSection; // allocateSevenSection

	private Long allocateSixSection; // allocateSixSection
	
	private Long allocateThreeSection; // allocateSixSection

	private String ext1; // ext1

	private String ext2; // ext2

	private String ext3; // ext3

	private String indicatorLine; // indicatorLine

	private Double lineDistance; // lineDistance

	private String operatePerson; // operatePerson

	private String operateTime; // operateTime

	private String removed; // removed

	private Long stationCount; // stationCount

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

	public void setAllocateEightSection(Long allocateEightSection) {
		this.allocateEightSection = allocateEightSection;
	}

	@Column(name = "ALLOCATE_EIGHT_SECTION", nullable = true, length = 20)
	public Long getAllocateEightSection() {
		return allocateEightSection;
	}

	public void setAllocateFourSection(Long allocateFourSection) {
		this.allocateFourSection = allocateFourSection;
	}

	@Column(name = "ALLOCATE_FOUR_SECTION", nullable = true, length = 20)
	public Long getAllocateFourSection() {
		return allocateFourSection;
	}

	public void setAllocateSevenSection(Long allocateSevenSection) {
		this.allocateSevenSection = allocateSevenSection;
	}

	@Column(name = "ALLOCATE_SEVEN_SECTION", nullable = true, length = 20)
	public Long getAllocateSevenSection() {
		return allocateSevenSection;
	}

	public void setAllocateSixSection(Long allocateSixSection) {
		this.allocateSixSection = allocateSixSection;
	}

	@Column(name = "ALLOCATE_SIX_SECTION", nullable = true, length = 20)
	public Long getAllocateSixSection() {
		return allocateSixSection;
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

	public void setLineDistance(Double lineDistance) {
		this.lineDistance = lineDistance;
	}

	@Column(name = "LINE_DISTANCE", nullable = true, length = 20)
	public Double getLineDistance() {
		return lineDistance;
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

	public void setStationCount(Long stationCount) {
		this.stationCount = stationCount;
	}

	@Column(name = "STATION_COUNT", nullable = true, length = 20)
	public Long getStationCount() {
		return stationCount;
	}

	@Column(name = "ALLOCATE_THREE_SECTION", nullable = true, length = 20)
	public Long getAllocateThreeSection() {
		return allocateThreeSection;
	}

	public void setAllocateThreeSection(Long allocateThreeSection) {
		this.allocateThreeSection = allocateThreeSection;
	}

	
}
