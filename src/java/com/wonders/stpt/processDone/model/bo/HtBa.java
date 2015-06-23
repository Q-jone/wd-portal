/** 
 * Copyright (c) 2003-2007 Wonders Information Co.,Ltd. All Rights Reserved.
 * 5-6/F, 20 Bldg, 481 Guiping RD. Shanghai 200233,PRC
 *
 * This software is the confidential and proprietary information of Wonders Group.
 * (Research & Development Center). You shall not disclose such
 * Confidential Information and shall use it only in accordance with 
 * the terms of the license agreement you entered into with Wonders Group. 
 *
 * Distributable under GNU LGPL license by gun.org
 */

package com.wonders.stpt.processDone.model.bo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.wondersgroup.framework.core.bo.BaseBO;
import com.wondersgroup.framework.core.bo.Removable;

/**
 * @author 
 * @version $Revision: 1.2 $
 * @author (lastest modification by $Author: wanglijun $)
 * 
 * @hibernate.class
 * table = "HT_BA"
 * dynamic-update = "true"
 */

@XmlRootElement(name="Htba")  
@XmlAccessorType(XmlAccessType.FIELD)
public class HtBa {
	private long id;
	private long htId;
    private java.lang.String examinePassTime;
    private java.lang.String contractSignTime;
    private java.lang.String performTimelimit;
    private java.lang.String remark;
    private java.lang.String ifSame;
    
	/**
	 * @return the id
	 * 
	 * @hibernate.id
     * column = "ID"
     * generator-class = "native" 
     * unsaved-value = "0"
     * 
     * @hibernate.generator-param 
     * name = "sequence" 
     * value = "SEQ_HTBA"
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	public java.lang.String getIfSame() {
		return ifSame;
	}

	public void setIfSame(java.lang.String ifSame) {
		this.ifSame = ifSame;
	}

	public java.lang.String getContractSignTime() {
		return contractSignTime;
	}

	public void setContractSignTime(java.lang.String contractSignTime) {
		this.contractSignTime = contractSignTime;
	}

	public java.lang.String getExaminePassTime() {
		return examinePassTime;
	}

	public void setExaminePassTime(java.lang.String examinePassTime) {
		this.examinePassTime = examinePassTime;
	}


	public java.lang.String getPerformTimelimit() {
		return performTimelimit;
	}

	public void setPerformTimelimit(java.lang.String performTimelimit) {
		this.performTimelimit = performTimelimit;
	}


	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}

	public long getHtId() {
		return htId;
	}

	public void setHtId(long htId) {
		this.htId = htId;
	}
}

