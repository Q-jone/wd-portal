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

package com.wonders.stpt.processDone.model.bo;

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
 * PclProjectBasicInfo实体定义
 * 
 * @author Administrator
 * @version $Revision$
 * @date 2012-5-8
 * @author modify by $Author$
 * @since 1.0
 */

@Entity
@Table(name = "PCL_PROJECT_BASIC_INFO")
public class PclProjectBasicInfo implements Serializable, BusinessObject {

	private String pId; // pId--

	private String approvalCode; // 立项受理编号--

	private String approvalType; // approvalType	

	private String excuteSolution; // 实施方案--

	private String excuteSolutionAttachId; // excuteSolutionAttachId--

	private String extCode1; // 实例号--

	private String extCode10; // extCode10

	private String extCode11; // extCode11

	private String extCode12; // extCode12

	private String extCode13; // extCode13

	private String extCode14; // 立项依据附件数--

	private String extCode15; // 实施方案附件数--

	private String extCode16; // 工程预算附件数--

	private String extCode17; // 工程计划附件数--

	private String extCode18; // 项目设计附件数--

	private String extCode19; // 项目分类--

	private String extCode2; // 流程类型--

	private String extCode20; // 既有资产编号--

	private String extCode21; // extCode21

	private Date extCode22; // extCode22

	private String extCode23; // extCode23

	private String extCode3; // extCode3

	private String extCode4; // extCode4

	private String extCode5; // extCode5

	private String extCode6; // extCode6

	private String extCode7; // extCode7

	private String extCode8; // extCode8

	private String extCode9; // extCode9

	private String flag; // flag

	private String infoDept; // 申报单位--

	private String infoMan; // 申报人--

	private String investCost; // investCost--

	private String legislationInfo; // 立项依据--

	private String legislationInfoAttachId; // legislationInfoAttachId--

	private String linkMan; // 执行单位联系人--

	private String linkManTel; // 执行单位联系人电话--

	private String mainDept; // mainDept

	private String mainDeptName; // mainDeptName

	private String mainDeptPersonId; // mainDeptPersonId

	private String mainDeptPersonName; // mainDeptPersonName

	private String mainPerson; // 负责人（有的是工号，有的是名字）--

	private String modified; // modified

	private String moneyNature; // moneyNature

	private String moneySource; // moneySource--

	private String moneySourceInfo; // moneySourceInfo

	private Date planEndDate; // planEndDate--

	private Date planStartDate; // planStartDate--

	private String projectAttachId; // 关联附件字段--

	private String projectAttachType; // projectAttachType

	private String projectBudget; // 工程预算--

	private String projectBudgetAttachId; // projectBudgetAttachId--

	private String projectDevise; // 项目设计

	private String projectDeviseAttachId; // projectDeviseAttachId--

	private String projectPlan; // 工程计划--

	private String projectPlanAttachId; // projectPlanAttachId--

	private String pName; // pName--

	private String removed; // removed

	private String summary; // summary
	
	private String moneySourceDetail;//出资构成
	
	private String keywords;//关键字--
	
	private String projectCompany;
	
	private String dispatchNo;
	
	private String substituteConstruction;
	
	private String ifCopy;
	
	private String major;//专业分类
	private String payType;
	private String assetType;
	private String assetAdd;//新增资产
	private String infoManTel;//申报单位联系电话
	private String mainCompany;//执行单位
	private String mainCompanyPerson;//执行单位负责人
	private String mainCompanyPersonTel;//执行单位负责人联系电话
	private String monitor;//是否需要监理

	public void setPId(String pId) {
		this.pId = pId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "P_ID")
	public String getPId() {
		return pId;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	@Column(name = "APPROVAL_CODE", nullable = true, length = 200)
	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	@Column(name = "APPROVAL_TYPE", nullable = true, length = 50)
	public String getApprovalType() {
		return approvalType;
	}	

	public void setExcuteSolution(String excuteSolution) {
		this.excuteSolution = excuteSolution;
	}

	@Column(name = "EXCUTE_SOLUTION", nullable = true, length = 2000)
	public String getExcuteSolution() {
		return excuteSolution;
	}

	public void setExcuteSolutionAttachId(String excuteSolutionAttachId) {
		this.excuteSolutionAttachId = excuteSolutionAttachId;
	}

	@Column(name = "EXCUTE_SOLUTION_ATTACH_ID", nullable = true, length = 200)
	public String getExcuteSolutionAttachId() {
		return excuteSolutionAttachId;
	}

	public void setExtCode1(String extCode1) {
		this.extCode1 = extCode1;
	}

	@Column(name = "EXT_CODE1", nullable = true, length = 500)
	public String getExtCode1() {
		return extCode1;
	}

	public void setExtCode10(String extCode10) {
		this.extCode10 = extCode10;
	}

	@Column(name = "EXT_CODE10", nullable = true, length = 500)
	public String getExtCode10() {
		return extCode10;
	}

	public void setExtCode11(String extCode11) {
		this.extCode11 = extCode11;
	}

	@Column(name = "EXT_CODE11", nullable = true, length = 500)
	public String getExtCode11() {
		return extCode11;
	}

	public void setExtCode12(String extCode12) {
		this.extCode12 = extCode12;
	}

	@Column(name = "EXT_CODE12", nullable = true, length = 500)
	public String getExtCode12() {
		return extCode12;
	}

	public void setExtCode13(String extCode13) {
		this.extCode13 = extCode13;
	}

	@Column(name = "EXT_CODE13", nullable = true, length = 500)
	public String getExtCode13() {
		return extCode13;
	}

	public void setExtCode14(String extCode14) {
		this.extCode14 = extCode14;
	}

	@Column(name = "EXT_CODE14", nullable = true, length = 500)
	public String getExtCode14() {
		return extCode14;
	}

	public void setExtCode15(String extCode15) {
		this.extCode15 = extCode15;
	}

	@Column(name = "EXT_CODE15", nullable = true, length = 500)
	public String getExtCode15() {
		return extCode15;
	}

	public void setExtCode16(String extCode16) {
		this.extCode16 = extCode16;
	}

	@Column(name = "EXT_CODE16", nullable = true, length = 500)
	public String getExtCode16() {
		return extCode16;
	}

	public void setExtCode17(String extCode17) {
		this.extCode17 = extCode17;
	}

	@Column(name = "EXT_CODE17", nullable = true, length = 500)
	public String getExtCode17() {
		return extCode17;
	}

	public void setExtCode18(String extCode18) {
		this.extCode18 = extCode18;
	}

	@Column(name = "EXT_CODE18", nullable = true, length = 500)
	public String getExtCode18() {
		return extCode18;
	}

	public void setExtCode19(String extCode19) {
		this.extCode19 = extCode19;
	}

	@Column(name = "EXT_CODE19", nullable = true, length = 500)
	public String getExtCode19() {
		return extCode19;
	}

	public void setExtCode2(String extCode2) {
		this.extCode2 = extCode2;
	}

	@Column(name = "EXT_CODE2", nullable = true, length = 200)
	public String getExtCode2() {
		return extCode2;
	}

	public void setExtCode20(String extCode20) {
		this.extCode20 = extCode20;
	}

	@Column(name = "EXT_CODE20", nullable = true)
	public String getExtCode20() {
		return extCode20;
	}

	public void setExtCode21(String extCode21) {
		this.extCode21 = extCode21;
	}

	@Column(name = "EXT_CODE21", nullable = true, length = 500)
	public String getExtCode21() {
		return extCode21;
	}

	public void setExtCode22(Date extCode22) {
		this.extCode22 = extCode22;
	}

	@Column(name = "EXT_CODE22", nullable = true, length = 7)
	public Date getExtCode22() {
		return extCode22;
	}

	public void setExtCode23(String extCode23) {
		this.extCode23 = extCode23;
	}

	@Column(name = "EXT_CODE23", nullable = true, length = 500)
	public String getExtCode23() {
		return extCode23;
	}

	public void setExtCode3(String extCode3) {
		this.extCode3 = extCode3;
	}

	@Column(name = "EXT_CODE3", nullable = true, length = 2000)
	public String getExtCode3() {
		return extCode3;
	}

	public void setExtCode4(String extCode4) {
		this.extCode4 = extCode4;
	}

	@Column(name = "EXT_CODE4", nullable = true, length = 500)
	public String getExtCode4() {
		return extCode4;
	}

	public void setExtCode5(String extCode5) {
		this.extCode5 = extCode5;
	}

	@Column(name = "EXT_CODE5", nullable = true, length = 500)
	public String getExtCode5() {
		return extCode5;
	}

	public void setExtCode6(String extCode6) {
		this.extCode6 = extCode6;
	}

	@Column(name = "EXT_CODE6", nullable = true, length = 500)
	public String getExtCode6() {
		return extCode6;
	}

	public void setExtCode7(String extCode7) {
		this.extCode7 = extCode7;
	}

	@Column(name = "EXT_CODE7", nullable = true, length = 500)
	public String getExtCode7() {
		return extCode7;
	}

	public void setExtCode8(String extCode8) {
		this.extCode8 = extCode8;
	}

	@Column(name = "EXT_CODE8", nullable = true, length = 500)
	public String getExtCode8() {
		return extCode8;
	}

	public void setExtCode9(String extCode9) {
		this.extCode9 = extCode9;
	}

	@Column(name = "EXT_CODE9", nullable = true, length = 500)
	public String getExtCode9() {
		return extCode9;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Column(name = "FLAG", nullable = true, length = 10)
	public String getFlag() {
		return flag;
	}

	public void setInfoDept(String infoDept) {
		this.infoDept = infoDept;
	}

	@Column(name = "INFO_DEPT", nullable = true, length = 50)
	public String getInfoDept() {
		return infoDept;
	}

	public void setInfoMan(String infoMan) {
		this.infoMan = infoMan;
	}

	@Column(name = "INFO_MAN", nullable = true, length = 50)
	public String getInfoMan() {
		return infoMan;
	}

	public void setInvestCost(String investCost) {
		this.investCost = investCost;
	}

	@Column(name = "INVEST_COST", nullable = true, length = 50)
	public String getInvestCost() {
		return investCost;
	}

	public void setLegislationInfo(String legislationInfo) {
		this.legislationInfo = legislationInfo;
	}

	@Column(name = "LEGISLATION_INFO", nullable = true, length = 2000)
	public String getLegislationInfo() {
		return legislationInfo;
	}

	public void setLegislationInfoAttachId(String legislationInfoAttachId) {
		this.legislationInfoAttachId = legislationInfoAttachId;
	}

	@Column(name = "LEGISLATION_INFO_ATTACH_ID", nullable = true, length = 200)
	public String getLegislationInfoAttachId() {
		return legislationInfoAttachId;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	@Column(name = "LINK_MAN", nullable = true, length = 200)
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkManTel(String linkManTel) {
		this.linkManTel = linkManTel;
	}

	@Column(name = "LINK_MAN_TEL", nullable = true, length = 20)
	public String getLinkManTel() {
		return linkManTel;
	}

	public void setMainDept(String mainDept) {
		this.mainDept = mainDept;
	}

	@Column(name = "MAIN_DEPT", nullable = true, length = 200)
	public String getMainDept() {
		return mainDept;
	}

	public void setMainDeptName(String mainDeptName) {
		this.mainDeptName = mainDeptName;
	}

	@Column(name = "MAIN_DEPT_NAME", nullable = true, length = 500)
	public String getMainDeptName() {
		return mainDeptName;
	}

	public void setMainDeptPersonId(String mainDeptPersonId) {
		this.mainDeptPersonId = mainDeptPersonId;
	}

	@Column(name = "MAIN_DEPT_PERSON_ID", nullable = true, length = 200)
	public String getMainDeptPersonId() {
		return mainDeptPersonId;
	}

	public void setMainDeptPersonName(String mainDeptPersonName) {
		this.mainDeptPersonName = mainDeptPersonName;
	}

	@Column(name = "MAIN_DEPT_PERSON_NAME", nullable = true, length = 200)
	public String getMainDeptPersonName() {
		return mainDeptPersonName;
	}

	public void setMainPerson(String mainPerson) {
		this.mainPerson = mainPerson;
	}

	@Column(name = "MAIN_PERSON", nullable = true, length = 200)
	public String getMainPerson() {
		return mainPerson;
	}

	public void setModified(String modified) {
		this.modified = modified;
	}

	@Column(name = "MODIFIED", nullable = true, length = 10)
	public String getModified() {
		return modified;
	}

	public void setMoneyNature(String moneyNature) {
		this.moneyNature = moneyNature;
	}

	@Column(name = "MONEY_NATURE", nullable = true, length = 200)
	public String getMoneyNature() {
		return moneyNature;
	}

	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}

	@Column(name = "MONEY_SOURCE", nullable = true, length = 200)
	public String getMoneySource() {
		return moneySource;
	}

	public void setMoneySourceInfo(String moneySourceInfo) {
		this.moneySourceInfo = moneySourceInfo;
	}

	@Column(name = "MONEY_SOURCE_INFO", nullable = true, length = 100)
	public String getMoneySourceInfo() {
		return moneySourceInfo;
	}

	public void setPlanEndDate(Date planEndDate) {
		this.planEndDate = planEndDate;
	}

	@Column(name = "PLAN_END_DATE", nullable = true, length = 7)
	public Date getPlanEndDate() {
		return planEndDate;
	}

	public void setPlanStartDate(Date planStartDate) {
		this.planStartDate = planStartDate;
	}

	@Column(name = "PLAN_START_DATE", nullable = true, length = 7)
	public Date getPlanStartDate() {
		return planStartDate;
	}

	public void setProjectAttachId(String projectAttachId) {
		this.projectAttachId = projectAttachId;
	}

	@Column(name = "PROJECT_ATTACH_ID", nullable = true, length = 200)
	public String getProjectAttachId() {
		return projectAttachId;
	}

	public void setProjectAttachType(String projectAttachType) {
		this.projectAttachType = projectAttachType;
	}

	@Column(name = "PROJECT_ATTACH_TYPE", nullable = true, length = 10)
	public String getProjectAttachType() {
		return projectAttachType;
	}

	public void setProjectBudget(String projectBudget) {
		this.projectBudget = projectBudget;
	}

	@Column(name = "PROJECT_BUDGET", nullable = true, length = 2000)
	public String getProjectBudget() {
		return projectBudget;
	}

	public void setProjectBudgetAttachId(String projectBudgetAttachId) {
		this.projectBudgetAttachId = projectBudgetAttachId;
	}

	@Column(name = "PROJECT_BUDGET_ATTACH_ID", nullable = true, length = 200)
	public String getProjectBudgetAttachId() {
		return projectBudgetAttachId;
	}

	public void setProjectDevise(String projectDevise) {
		this.projectDevise = projectDevise;
	}

	@Column(name = "PROJECT_DEVISE", nullable = true, length = 10)
	public String getProjectDevise() {
		return projectDevise;
	}

	public void setProjectDeviseAttachId(String projectDeviseAttachId) {
		this.projectDeviseAttachId = projectDeviseAttachId;
	}

	@Column(name = "PROJECT_DEVISE_ATTACH_ID", nullable = true, length = 200)
	public String getProjectDeviseAttachId() {
		return projectDeviseAttachId;
	}

	public void setProjectPlan(String projectPlan) {
		this.projectPlan = projectPlan;
	}

	@Column(name = "PROJECT_PLAN", nullable = true, length = 2000)
	public String getProjectPlan() {
		return projectPlan;
	}

	public void setProjectPlanAttachId(String projectPlanAttachId) {
		this.projectPlanAttachId = projectPlanAttachId;
	}

	@Column(name = "PROJECT_PLAN_ATTACH_ID", nullable = true, length = 200)
	public String getProjectPlanAttachId() {
		return projectPlanAttachId;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	@Column(name = "P_NAME", nullable = true, length = 200)
	public String getPName() {
		return pName;
	}

	public void setRemoved(String removed) {
		this.removed = removed;
	}

	@Column(name = "REMOVED", nullable = true, length = 10)
	public String getRemoved() {
		return removed;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "SUMMARY", nullable = true, length = 2000)
	public String getSummary() {
		return summary;
	}
	
	public void setMoneySourceDetail(String moneySourceDetail) {
		this.moneySourceDetail = moneySourceDetail;
	}

	@Column(name = "MONEY_SOURCE_DETAIL", nullable = true, length = 1000)
	public String getMoneySourceDetail() {
		return moneySourceDetail;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "KEYWORDS", nullable = true, length = 100)
	public String getKeywords() {
		return keywords;
	}
	
	public void setProjectCompany(String projectCompany) {
		this.projectCompany = projectCompany;
	}

	@Column(name = "PROJECT_COMPANY", nullable = true, length = 1000)
	public String getProjectCompany() {
		return projectCompany;
	}
	
	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}

	@Column(name = "DISPATCH_NO", nullable = true, length = 80)
	public String getDispatchNo() {
		return dispatchNo;
	}
	
	public void setSubstituteConstruction(String substituteConstruction) {
		this.substituteConstruction = substituteConstruction;
	}

	@Column(name = "SUBSTITUTE_CONSTRUCTION", nullable = true, length = 2)
	public String getSubstituteConstruction() {
		return substituteConstruction;
	}
	
	public void setIfCopy(String ifCopy) {
		this.ifCopy = ifCopy;
	}

	@Column(name = "IF_COPY", nullable = true, length = 2)
	public String getIfCopy() {
		return ifCopy;
	}

	@Column(name = "MAJOR", nullable = true, length = 100)
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	@Column(name = "PAY_TYPE", nullable = true, length = 1)
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "ASSET_TYPE", nullable = true, length = 1)
	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	@Column(name = "ASSET_ADD", nullable = true)
	public String getAssetAdd() {
		return assetAdd;
	}

	public void setAssetAdd(String assetAdd) {
		this.assetAdd = assetAdd;
	}

	@Column(name = "INFO_MAN_TEL", nullable = true, length = 20)
	public String getInfoManTel() {
		return infoManTel;
	}

	public void setInfoManTel(String infoManTel) {
		this.infoManTel = infoManTel;
	}

	@Column(name = "MAIN_COMPANY", nullable = true, length = 50)
	public String getMainCompany() {
		return mainCompany;
	}

	public void setMainCompany(String mainCompany) {
		this.mainCompany = mainCompany;
	}

	@Column(name = "MAIN_COMPANY_PERSON", nullable = true, length = 20)
	public String getMainCompanyPerson() {
		return mainCompanyPerson;
	}

	public void setMainCompanyPerson(String mainCompanyPerson) {
		this.mainCompanyPerson = mainCompanyPerson;
	}
	
	@Column(name = "MAIN_COMPANY_PERSON_TEL", nullable = true, length = 20)
	public String getMainCompanyPersonTel() {
		return mainCompanyPersonTel;
	}

	public void setMainCompanyPersonTel(String mainCompanyPersonTel) {
		this.mainCompanyPersonTel = mainCompanyPersonTel;
	}
	
	@Column(name = "MONITOR", nullable = true, length = 1)
	public String getMonitor() {
		return monitor;
	}

	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
}
