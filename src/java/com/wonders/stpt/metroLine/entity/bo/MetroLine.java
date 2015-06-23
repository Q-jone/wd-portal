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

package com.wonders.stpt.metroLine.entity.bo;

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
 * MetroLine实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_METRO_LINE")
public class MetroLine implements Serializable, BusinessObject {

	private String lineId; // lineId

	private String lineName; // lineName

	private Long sortingOrder; // sortingOrder

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LINE_ID")
	public String getLineId() {
		return lineId;
	}

	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	@Column(name = "LINE_NAME", nullable = true, length = 20)
	public String getLineName() {
		return lineName;
	}

	public void setSortingOrder(Long sortingOrder) {
		this.sortingOrder = sortingOrder;
	}

	@Column(name = "SORTING_ORDER", nullable = true, length = 22)
	public Long getSortingOrder() {
		return sortingOrder;
	}

}
