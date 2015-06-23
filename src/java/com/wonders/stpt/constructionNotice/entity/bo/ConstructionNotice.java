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

package com.wonders.stpt.constructionNotice.entity.bo;

import java.util.Date;
import java.io.Serializable;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.*;

import com.wondersgroup.framework.core5.model.bo.BusinessObject;

/**
 * TConstructionNotice实体定义
 * 
 * @author ycl
 * @version $Revision$
 * @date 2012-3-20
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "T_CONSTRUCTION_NOTICE")
public class ConstructionNotice implements Serializable, BusinessObject {

	private String id; // id

	private String applyUnit; // applyUnit

	private Date constructionDate; // constructionDate

	private String constructionDetail; // constructionDetail

	private Date constructionEndDate; // constructionEndDate

	private Date constructionStartDate; // constructionStartDate

	private String constructionUnit; // constructionUnit

	private String construstionRange; // construstionRange

	private Date createDate; // createDate

	private String dragDynamic; // dragDynamic

	private Date lastUpdateDate; // lastUpdateDate

	private String lineNo; // lineNo

	private String powerOffRange; // powerOffRange

	private String projectNo; // projectNo

	private String removed; // removed

	private String responsiblePerson; // responsiblePerson

	private String updatePerson; // updatePerson

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

	public void setApplyUnit(String applyUnit) {
		this.applyUnit = applyUnit;
	}

	@Column(name = "APPLY_UNIT", nullable = true, length = 200)
	public String getApplyUnit() {
		return applyUnit;
	}

	public void setConstructionDate(Date constructionDate) {
		this.constructionDate = constructionDate;
	}

	//@Temporal(TemporalType.DATE)  DATEl类型注解
	@Column(name = "CONSTRUCTION_DATE", nullable = true, length = 7)
	public Date getConstructionDate() {
		return constructionDate;
	}

	public void setConstructionDetail(String constructionDetail) {
		this.constructionDetail = constructionDetail;
	}

	@Column(name = "CONSTRUCTION_DETAIL", nullable = true, length = 600)
	public String getConstructionDetail() {
		return constructionDetail;
	}

	public void setConstructionEndDate(Date constructionEndDate) {
		this.constructionEndDate = constructionEndDate;
	}

	@Column(name = "CONSTRUCTION_END_DATE", nullable = true, length = 7)
	public Date getConstructionEndDate() {
		return constructionEndDate;
	}

	public void setConstructionStartDate(Date constructionStartDate) {
		this.constructionStartDate = constructionStartDate;
	}

	@Column(name = "CONSTRUCTION_START_DATE", nullable = true, length = 7)
	public Date getConstructionStartDate() {
		return constructionStartDate;
	}

	public void setConstructionUnit(String constructionUnit) {
		this.constructionUnit = constructionUnit;
	}

	@Column(name = "CONSTRUCTION_UNIT", nullable = true, length = 200)
	public String getConstructionUnit() {
		return constructionUnit;
	}

	public void setConstrustionRange(String construstionRange) {
		this.construstionRange = construstionRange;
	}

	@Column(name = "CONSTRUSTION_RANGE", nullable = true, length = 600)
	public String getConstrustionRange() {
		return construstionRange;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Column(name = "CREATE_DATE", nullable = true, length = 7)
	public Date getCreateDate() {
		return createDate;
	}

	public void setDragDynamic(String dragDynamic) {
		this.dragDynamic = dragDynamic;
	}

	@Column(name = "DRAG_DYNAMIC", nullable = true, length = 50)
	public String getDragDynamic() {
		return dragDynamic;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	@Column(name = "LAST_UPDATE_DATE", nullable = true, length = 7)
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
	}

	@Column(name = "LINE_NO", nullable = true, length = 40)
	public String getLineNo() {
		return lineNo;
	}

	public void setPowerOffRange(String powerOffRange) {
		this.powerOffRange = powerOffRange;
	}

	@Column(name = "POWER_OFF_RANGE", nullable = true, length = 600)
	public String getPowerOffRange() {
		return powerOffRange;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	@Column(name = "PROJECT_NO", nullable = true, length = 40)
	public String getProjectNo() {
		return projectNo;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 2)
	public String getRemoved() {
		return removed;
	}

	public void setResponsiblePerson(String responsiblePerson) {
		this.responsiblePerson = responsiblePerson;
	}

	@Column(name = "RESPONSIBLE_PERSON", nullable = true, length = 40)
	public String getResponsiblePerson() {
		return responsiblePerson;
	}

	public void setUpdatePerson(String updatePerson) {
		this.updatePerson = updatePerson;
	}

	@Column(name = "UPDATE_PERSON", nullable = true, length = 20)
	public String getUpdatePerson() {
		return updatePerson;
	}

}
