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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.wondersgroup.framework.core.bo.BaseBO;
import com.wondersgroup.framework.core.bo.Removable;

/**
 * @author 
 * @version $Revision: 1.2 $
 * @author (lastest modification by $Author: wanglijun $)
 * 
 * @hibernate.class
 * table = "HT_XX"
 * dynamic-update = "true"
 */
@XmlRootElement(name="Htxx")  
@XmlAccessorType(XmlAccessType.FIELD)
public class HtXx {
	
	private long id;
	private String PlanNum;
	private String ContractName;
	private String DelegatePerson;//项目公司法人或委托代理人
	private String ProjectCOName;
	private String ContractNum;
	private String ProjectNum;
	private String ContractMoney;
	private String DealPerson;
	private String DealDeptSuggest;
	private String AddPerson;
	private String AddDate;
	private String Remark;
	private String PinstanceId;
	private String ModelId;
	private String ContractMoneyType;
	private String SelfNum;//自有编号
	private String FileNum;//批文号
	private String SignCop;//对方公司
	private String Budget;
	private String HtAttach;
	
	
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
     * value = "SEQ_HT_XX"
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
	
	/**
	 * @hibernate.property column = "ADD_TIME"
	 * length="200"
	 * @return
	 */
	public String getAddDate() {
		return AddDate;
	}

	public void setAddDate(String addDate) {
		AddDate = addDate;
	}
	
	/**
	 * @hibernate.property column = "ADD_PERSON"
	 * length="200"
	 * @return
	 */
	public String getAddPerson() {
		return AddPerson;
	}
	
	public void setAddPerson(String addPerson) {
		AddPerson = addPerson;
	}

	/**
	 * @hibernate.property column = "CONTRACT_MONEY"
	 * length="200"
	 * @return
	 */
	public String getContractMoney() {
		return ContractMoney;
	}

	public void setContractMoney(String contractMoney) {
		ContractMoney = contractMoney;
	}

	/**
	 * @hibernate.property column = "CONTRACT_MONEY_TYPE"
	 * length="200"
	 * @return
	 */
	public String getContractMoneyType() {
		return ContractMoneyType;
	}

	public void setContractMoneyType(String contractMoneyType) {
		ContractMoneyType = contractMoneyType;
	}

	/**
	 * @hibernate.property column = "CONTRACT_NAME"
	 * length="200"
	 * @return
	 */
	public String getContractName() {
		return ContractName;
	}

	public void setContractName(String contractName) {
		ContractName = contractName;
	}

	/**
	 * @hibernate.property column = "CONTRACT_NAME1"
	 * length="200"
	 * @return
	 */
	public String getDelegatePerson() {
		return DelegatePerson;
	}

	public void setDelegatePerson(String delegatePerson) {
		this.DelegatePerson = delegatePerson;
	}

	/**
	 * @hibernate.property column = "CONTRACT_NUM"
	 * length="200"
	 * @return
	 */
	public String getContractNum() {
		return ContractNum;
	}

	public void setContractNum(String contractNum) {
		ContractNum = contractNum;
	}

	/**
	 * @hibernate.property column = "DEAL_DEP_SUGGEST"
	 * length="2000"
	 * @return
	 */
	public String getDealDeptSuggest() {
		return DealDeptSuggest;
	}

	public void setDealDeptSuggest(String dealDeptSuggest) {
		DealDeptSuggest = dealDeptSuggest;
	}

	/**
	 * @hibernate.property column = "DEAL_PERSON"
	 * length="200"
	 * @return
	 */
	public String getDealPerson() {
		return DealPerson;
	}

	public void setDealPerson(String dealPerson) {
		DealPerson = dealPerson;
	}

	/**
	 * @hibernate.property column = "MODEL_ID"
	 * length="200"
	 * @return
	 */
	public String getModelId() {
		return ModelId;
	}

	public void setModelId(String modelId) {
		ModelId = modelId;
	}

	/**
	 * @hibernate.property column = "PINSTANCE_ID"
	 * length="200"
	 * @return
	 */
	public String getPinstanceId() {
		return PinstanceId;
	}

	public void setPinstanceId(String pinstanceId) {
		PinstanceId = pinstanceId;
	}

	/**
	 * @hibernate.property column = "PLAN_NUM"
	 * length="200"
	 * @return
	 */
	public String getPlanNum() {
		return PlanNum;
	}

	public void setPlanNum(String planNum) {
		PlanNum = planNum;
	}

	/**
	 * @hibernate.property column = "PROJECT_CO_NAME"
	 * length="200"
	 * @return
	 */
	public String getProjectCOName() {
		return ProjectCOName;
	}

	public void setProjectCOName(String projectCOName) {
		ProjectCOName = projectCOName;
	}

	/**
	 * @hibernate.property column = "PROJECT_NUM"
	 * length="200"
	 * @return
	 */
	public String getProjectNum() {
		return ProjectNum;
	}

	public void setProjectNum(String projectNum) {
		ProjectNum = projectNum;
	}

	/**
	 * @hibernate.property column = "REMARK"
	 * length="2000"
	 * @return
	 */
	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}


	public String getSelfNum() {
		return SelfNum;
	}

	public void setSelfNum(String selfNum) {
		SelfNum = selfNum;
	}

	public String getFileNum() {
		return FileNum;
	}

	public void setFileNum(String fileNum) {
		FileNum = fileNum;
	}

	public String getSignCop() {
		return SignCop;
	}

	public void setSignCop(String signCop) {
		SignCop = signCop;
	}

	public String getBudget() {
		return Budget;
	}

	public void setBudget(String budget) {
		Budget = budget;
	}

	public String getHtAttach() {
		return HtAttach;
	}

	public void setHtAttach(String htAttach) {
		this.HtAttach = htAttach;
	}
	
	
}

