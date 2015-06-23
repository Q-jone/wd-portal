package com.wonders.stpt.processDone.model.vo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="BasicData")  
@XmlAccessorType(XmlAccessType.FIELD)
public class ProjectVo {
	private String projectName;  //pname,项目名称
	private String keywords;  //keywords,关键字
	private String projectNum;  //approvalCode,立项受理编号
	private String projectType;  //立项类型(内部立项)
	private String investCost;  //investCost,投资估算(万元)
	private String planStartDate;  //planStartDate,计划开始时间
	private String planEndDate;  //planEndDate,计划完成时间
	private String moneySource;  //moneySource,出资主体(1为集团，2为项目公司，3为申报人自筹，4为其他，5为上市公司。可多选，用逗号分隔)
	private String moneySourceDetail;  //moneySourceDetail,出资构成
	private String major;  //major,专业分类
	private String projectClass;  //extCode19,项目分类
	private String reportCompany;  //infoDept,申报单位
	private String reportChargePerson;  //mainPerson,申报单位负责人
	private String reportPerson;  //infoMan,申报人
	private String reportCompanyTel;  //infoManTel,申报单位联系电话
	private String excuteCompany;  //mainCompany,执行单位
	private String excuteChargePerson;  //mainCompanyPerson,执行单位负责人
	private String excuteChargePersonTel;  //mainCompanyPersonTel,执行单位负责人联系电话
	private String excuteLinkPerson;  //linkMan,执行单位联系人
	private String excuteLinkPersonTel;  //linkManTel,执行单位联系人联系电话
	
	private String establishAccording;  //legislationInfo,立项依据
	private String establishAccordingAttach;  //legislationInfoAttachId,立项依据附件
	private String establishAccordingAttachNum;  //extCode14,立项依据附件数
	private String excuteSolution;  //excuteSolution,实施方案
	private String excuteSolutionAttach;  //excuteSolutionAttachId,实施方案附件
	private String excuteSolutionAttachNum;  //extCode15,实施方案附件数
	private String projectBudget;  //projectBudget,工程预算
	private String projectBudgetAttach;  //projectBudgetAttachId,工程预算附件
	private String projectBudgetAttachNum;  //extCode16,工程预算附件数
	private String projectPlan;  //projectPlan,工程计划
	private String projectPlanAttach;  //projectPlanAttachId,工程计划附件
	private String projectPlanAttachNum;  //extCode17,工程计划附件数
	private String projectDesign;  //projectDevise,项目设计
	private String projectDesignAttach;  //projectDeviseAttachId,项目设计附件
	private String projectDesignAttachNum;  //extCode18,项目设计附件数
	private String monitor;  //monitor,是否需要监理
	
	private String incident;  //extCode1,实例号
	private String dispatchNo; //dispatchNo,批文编号
	private String indexNum; //extCode8,序列号
	private String dispatchDate; //extCode9,批文时间
	private String projectStatus; //项目状态
	private String processCurrentStep; //审核状态
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getProjectNum() {
		return projectNum;
	}
	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public String getInvestCost() {
		return investCost;
	}
	public void setInvestCost(String investCost) {
		this.investCost = investCost;
	}
	public String getPlanStartDate() {
		return planStartDate;
	}
	public void setPlanStartDate(String planStartDate) {
		this.planStartDate = planStartDate;
	}
	public String getPlanEndDate() {
		return planEndDate;
	}
	public void setPlanEndDate(String planEndDate) {
		this.planEndDate = planEndDate;
	}
	public String getMoneySource() {
		return moneySource;
	}
	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}
	public String getMoneySourceDetail() {
		return moneySourceDetail;
	}
	public void setMoneySourceDetail(String moneySourceDetail) {
		this.moneySourceDetail = moneySourceDetail;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getProjectClass() {
		return projectClass;
	}
	public void setProjectClass(String projectClass) {
		this.projectClass = projectClass;
	}
	public String getReportCompany() {
		return reportCompany;
	}
	public void setReportCompany(String reportCompany) {
		this.reportCompany = reportCompany;
	}
	public String getReportChargePerson() {
		return reportChargePerson;
	}
	public void setReportChargePerson(String reportChargePerson) {
		this.reportChargePerson = reportChargePerson;
	}
	public String getReportPerson() {
		return reportPerson;
	}
	public void setReportPerson(String reportPerson) {
		this.reportPerson = reportPerson;
	}
	public String getReportCompanyTel() {
		return reportCompanyTel;
	}
	public void setReportCompanyTel(String reportCompanyTel) {
		this.reportCompanyTel = reportCompanyTel;
	}
	public String getExcuteCompany() {
		return excuteCompany;
	}
	public void setExcuteCompany(String excuteCompany) {
		this.excuteCompany = excuteCompany;
	}
	public String getExcuteChargePerson() {
		return excuteChargePerson;
	}
	public void setExcuteChargePerson(String excuteChargePerson) {
		this.excuteChargePerson = excuteChargePerson;
	}
	public String getExcuteChargePersonTel() {
		return excuteChargePersonTel;
	}
	public void setExcuteChargePersonTel(String excuteChargePersonTel) {
		this.excuteChargePersonTel = excuteChargePersonTel;
	}
	public String getExcuteLinkPerson() {
		return excuteLinkPerson;
	}
	public void setExcuteLinkPerson(String excuteLinkPerson) {
		this.excuteLinkPerson = excuteLinkPerson;
	}
	public String getExcuteLinkPersonTel() {
		return excuteLinkPersonTel;
	}
	public void setExcuteLinkPersonTel(String excuteLinkPersonTel) {
		this.excuteLinkPersonTel = excuteLinkPersonTel;
	}
	public String getEstablishAccording() {
		return establishAccording;
	}
	public void setEstablishAccording(String establishAccording) {
		this.establishAccording = establishAccording;
	}
	public String getExcuteSolution() {
		return excuteSolution;
	}
	public void setExcuteSolution(String excuteSolution) {
		this.excuteSolution = excuteSolution;
	}
	public String getProjectBudget() {
		return projectBudget;
	}
	public void setProjectBudget(String projectBudget) {
		this.projectBudget = projectBudget;
	}
	public String getProjectPlan() {
		return projectPlan;
	}
	public void setProjectPlan(String projectPlan) {
		this.projectPlan = projectPlan;
	}
	public String getProjectDesign() {
		return projectDesign;
	}
	public void setProjectDesign(String projectDesign) {
		this.projectDesign = projectDesign;
	}
	public String getMonitor() {
		return monitor;
	}
	public void setMonitor(String monitor) {
		this.monitor = monitor;
	}
	public String getEstablishAccordingAttach() {
		return establishAccordingAttach;
	}
	public void setEstablishAccordingAttach(String establishAccordingAttach) {
		this.establishAccordingAttach = establishAccordingAttach;
	}
	public String getEstablishAccordingAttachNum() {
		return establishAccordingAttachNum;
	}
	public void setEstablishAccordingAttachNum(String establishAccordingAttachNum) {
		this.establishAccordingAttachNum = establishAccordingAttachNum;
	}
	public String getExcuteSolutionAttach() {
		return excuteSolutionAttach;
	}
	public void setExcuteSolutionAttach(String excuteSolutionAttach) {
		this.excuteSolutionAttach = excuteSolutionAttach;
	}
	public String getExcuteSolutionAttachNum() {
		return excuteSolutionAttachNum;
	}
	public void setExcuteSolutionAttachNum(String excuteSolutionAttachNum) {
		this.excuteSolutionAttachNum = excuteSolutionAttachNum;
	}
	public String getProjectBudgetAttach() {
		return projectBudgetAttach;
	}
	public void setProjectBudgetAttach(String projectBudgetAttach) {
		this.projectBudgetAttach = projectBudgetAttach;
	}
	public String getProjectBudgetAttachNum() {
		return projectBudgetAttachNum;
	}
	public void setProjectBudgetAttachNum(String projectBudgetAttachNum) {
		this.projectBudgetAttachNum = projectBudgetAttachNum;
	}
	public String getProjectPlanAttach() {
		return projectPlanAttach;
	}
	public void setProjectPlanAttach(String projectPlanAttach) {
		this.projectPlanAttach = projectPlanAttach;
	}
	public String getProjectPlanAttachNum() {
		return projectPlanAttachNum;
	}
	public void setProjectPlanAttachNum(String projectPlanAttachNum) {
		this.projectPlanAttachNum = projectPlanAttachNum;
	}
	public String getProjectDesignAttach() {
		return projectDesignAttach;
	}
	public void setProjectDesignAttach(String projectDesignAttach) {
		this.projectDesignAttach = projectDesignAttach;
	}
	public String getProjectDesignAttachNum() {
		return projectDesignAttachNum;
	}
	public void setProjectDesignAttachNum(String projectDesignAttachNum) {
		this.projectDesignAttachNum = projectDesignAttachNum;
	}
	public String getIncident() {
		return incident;
	}
	public void setIncident(String incident) {
		this.incident = incident;
	}
	public String getDispatchNo() {
		return dispatchNo;
	}
	public void setDispatchNo(String dispatchNo) {
		this.dispatchNo = dispatchNo;
	}
	public String getIndexNum() {
		return indexNum;
	}
	public void setIndexNum(String indexNum) {
		this.indexNum = indexNum;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getProcessCurrentStep() {
		return processCurrentStep;
	}
	public void setProcessCurrentStep(String processCurrentStep) {
		this.processCurrentStep = processCurrentStep;
	}
	
}
