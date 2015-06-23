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

import com.wondersgroup.framework.core5.model.vo.ValueObject;

/**
 * MetroScaleControlʵ�嶨��
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-5-21
 * @author modify by $Author$
 * @since 1.0
 */

public class MetroScaleControlVO implements ValueObject {

	private static final long serialVersionUID = 1L;
	private String id;
	private Long allocateEightSection;
	private Long allocateFourSection;
	private Long allocateSevenSection;
	private Long allocateSixSection;
	private Long allocateThreeSection;
	private String ext1;
	private String ext2;
	private String ext3;
	private String indicatorLine;
	private Double lineDistance;
	private String operatePerson;
	private String operateTime;
	private String removed;
	private Long stationCount;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setAllocateEightSection(Long allocateEightSection) {
		this.allocateEightSection = allocateEightSection;
	}

	public Long getAllocateEightSection() {
		return allocateEightSection;
	}

	public void setAllocateFourSection(Long allocateFourSection) {
		this.allocateFourSection = allocateFourSection;
	}

	public Long getAllocateFourSection() {
		return allocateFourSection;
	}

	public void setAllocateSevenSection(Long allocateSevenSection) {
		this.allocateSevenSection = allocateSevenSection;
	}

	public Long getAllocateSevenSection() {
		return allocateSevenSection;
	}

	public void setAllocateSixSection(Long allocateSixSection) {
		this.allocateSixSection = allocateSixSection;
	}

	public Long getAllocateSixSection() {
		return allocateSixSection;
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

	public void setLineDistance(Double lineDistance) {
		this.lineDistance = lineDistance;
	}

	public Double getLineDistance() {
		return lineDistance;
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

	public void setStationCount(Long stationCount) {
		this.stationCount = stationCount;
	}

	public Long getStationCount() {
		return stationCount;
	}

	public Long getAllocateThreeSection() {
		return allocateThreeSection;
	}

	public void setAllocateThreeSection(Long allocateThreeSection) {
		this.allocateThreeSection = allocateThreeSection;
	}

	
}
