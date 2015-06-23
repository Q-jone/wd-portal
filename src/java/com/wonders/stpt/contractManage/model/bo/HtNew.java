package com.wonders.stpt.contractManage.model.bo;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

/**
 * Created by Administrator on 2015/4/3.
 */
@Entity
@javax.persistence.Table(name = "HT_NEW", schema = "STPT", catalog = "")
public class HtNew {
    private int id;

    @Id
    @javax.persistence.Column(name = "ID", nullable = false, insertable = true, updatable = true, precision = 0)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String planNum;

    @Basic
    @javax.persistence.Column(name = "PLAN_NUM", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    private String contractName;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    private String contractName1;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NAME1", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractName1() {
        return contractName1;
    }

    public void setContractName1(String contractName1) {
        this.contractName1 = contractName1;
    }

    private String projectCoName;

    @Basic
    @javax.persistence.Column(name = "PROJECT_CO_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectCoName() {
        return projectCoName;
    }

    public void setProjectCoName(String projectCoName) {
        this.projectCoName = projectCoName;
    }

    private String contractNum;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_NUM", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    private String projectNum;

    @Basic
    @javax.persistence.Column(name = "PROJECT_NUM", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectNum() {
        return projectNum;
    }

    public void setProjectNum(String projectNum) {
        this.projectNum = projectNum;
    }

    private String contractMoney;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_MONEY", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractMoney() {
        return contractMoney;
    }

    public void setContractMoney(String contractMoney) {
        this.contractMoney = contractMoney;
    }

    private String dealPerson;

    @Basic
    @javax.persistence.Column(name = "DEAL_PERSON", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDealPerson() {
        return dealPerson;
    }

    public void setDealPerson(String dealPerson) {
        this.dealPerson = dealPerson;
    }

    private String dealDepSuggest;

    @Basic
    @javax.persistence.Column(name = "DEAL_DEP_SUGGEST", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getDealDepSuggest() {
        return dealDepSuggest;
    }

    public void setDealDepSuggest(String dealDepSuggest) {
        this.dealDepSuggest = dealDepSuggest;
    }

    private String currentLink;

    @Basic
    @javax.persistence.Column(name = "CURRENT_LINK", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCurrentLink() {
        return currentLink;
    }

    public void setCurrentLink(String currentLink) {
        this.currentLink = currentLink;
    }

    private String addPerson;

    @Basic
    @javax.persistence.Column(name = "ADD_PERSON", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAddPerson() {
        return addPerson;
    }

    public void setAddPerson(String addPerson) {
        this.addPerson = addPerson;
    }

    private String addTime;

    @Basic
    @javax.persistence.Column(name = "ADD_TIME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    private String totalDate;

    @Basic
    @javax.persistence.Column(name = "TOTAL_DATE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTotalDate() {
        return totalDate;
    }

    public void setTotalDate(String totalDate) {
        this.totalDate = totalDate;
    }

    private String currentSj2;

    @Basic
    @javax.persistence.Column(name = "CURRENT_SJ2", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCurrentSj2() {
        return currentSj2;
    }

    public void setCurrentSj2(String currentSj2) {
        this.currentSj2 = currentSj2;
    }

    private String currentSj;

    @Basic
    @javax.persistence.Column(name = "CURRENT_SJ", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCurrentSj() {
        return currentSj;
    }

    public void setCurrentSj(String currentSj) {
        this.currentSj = currentSj;
    }

    private String remark;

    @Basic
    @javax.persistence.Column(name = "REMARK", nullable = true, insertable = true, updatable = true, length = 2000)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private String nextDep;

    @Basic
    @javax.persistence.Column(name = "NEXT_DEP", nullable = true, insertable = true, updatable = true, length = 200)
    public String getNextDep() {
        return nextDep;
    }

    public void setNextDep(String nextDep) {
        this.nextDep = nextDep;
    }

    private String nextPerson;

    @Basic
    @javax.persistence.Column(name = "NEXT_PERSON", nullable = true, insertable = true, updatable = true, length = 200)
    public String getNextPerson() {
        return nextPerson;
    }

    public void setNextPerson(String nextPerson) {
        this.nextPerson = nextPerson;
    }

    private String contractStage;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_STAGE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractStage() {
        return contractStage;
    }

    public void setContractStage(String contractStage) {
        this.contractStage = contractStage;
    }

    private String dealState;

    @Basic
    @javax.persistence.Column(name = "DEAL_STATE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getDealState() {
        return dealState;
    }

    public void setDealState(String dealState) {
        this.dealState = dealState;
    }

    private String currentPerson;

    @Basic
    @javax.persistence.Column(name = "CURRENT_PERSON", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCurrentPerson() {
        return currentPerson;
    }

    public void setCurrentPerson(String currentPerson) {
        this.currentPerson = currentPerson;
    }

    private String currentDep;

    @Basic
    @javax.persistence.Column(name = "CURRENT_DEP", nullable = true, insertable = true, updatable = true, length = 200)
    public String getCurrentDep() {
        return currentDep;
    }

    public void setCurrentDep(String currentDep) {
        this.currentDep = currentDep;
    }

    private String flag1;

    @Basic
    @javax.persistence.Column(name = "FLAG1", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFlag1() {
        return flag1;
    }

    public void setFlag1(String flag1) {
        this.flag1 = flag1;
    }

    private String attr1;

    @Basic
    @javax.persistence.Column(name = "ATTR1", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    private String attr2;

    @Basic
    @javax.persistence.Column(name = "ATTR2", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }

    private String attr3;

    @Basic
    @javax.persistence.Column(name = "ATTR3", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3;
    }

    private String attr4;

    @Basic
    @javax.persistence.Column(name = "ATTR4", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4;
    }

    private String attr5;

    @Basic
    @javax.persistence.Column(name = "ATTR5", nullable = true, insertable = true, updatable = true, length = 200)
    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5;
    }

    private Date attr6;

    @Basic
    @javax.persistence.Column(name = "ATTR6", nullable = true, insertable = true, updatable = true)
    public Date getAttr6() {
        return attr6;
    }

    public void setAttr6(Date attr6) {
        this.attr6 = attr6;
    }

    private String pinstanceId;

    @Basic
    @javax.persistence.Column(name = "PINSTANCE_ID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPinstanceId() {
        return pinstanceId;
    }

    public void setPinstanceId(String pinstanceId) {
        this.pinstanceId = pinstanceId;
    }

    private String workitemId;

    @Basic
    @javax.persistence.Column(name = "WORKITEM_ID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getWorkitemId() {
        return workitemId;
    }

    public void setWorkitemId(String workitemId) {
        this.workitemId = workitemId;
    }

    private String userId;

    @Basic
    @javax.persistence.Column(name = "USER_ID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String modelId;

    @Basic
    @javax.persistence.Column(name = "MODEL_ID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    private String contractMoneyType;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_MONEY_TYPE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractMoneyType() {
        return contractMoneyType;
    }

    public void setContractMoneyType(String contractMoneyType) {
        this.contractMoneyType = contractMoneyType;
    }

    private String flag2;

    @Basic
    @javax.persistence.Column(name = "FLAG2", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFlag2() {
        return flag2;
    }

    public void setFlag2(String flag2) {
        this.flag2 = flag2;
    }

    private String flag3;

    @Basic
    @javax.persistence.Column(name = "FLAG3", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFlag3() {
        return flag3;
    }

    public void setFlag3(String flag3) {
        this.flag3 = flag3;
    }

    private String flag4;

    @Basic
    @javax.persistence.Column(name = "FLAG4", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFlag4() {
        return flag4;
    }

    public void setFlag4(String flag4) {
        this.flag4 = flag4;
    }

    private String taskid;

    @Basic
    @javax.persistence.Column(name = "TASKID", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTaskid() {
        return taskid;
    }

    public void setTaskid(String taskid) {
        this.taskid = taskid;
    }

    private String taskuser;

    @Basic
    @javax.persistence.Column(name = "TASKUSER", nullable = true, insertable = true, updatable = true, length = 200)
    public String getTaskuser() {
        return taskuser;
    }

    public void setTaskuser(String taskuser) {
        this.taskuser = taskuser;
    }

    private String selfNum;

    @Basic
    @javax.persistence.Column(name = "SELF_NUM", nullable = true, insertable = true, updatable = true, length = 200)
    public String getSelfNum() {
        return selfNum;
    }

    public void setSelfNum(String selfNum) {
        this.selfNum = selfNum;
    }

    private String fileNum;

    @Basic
    @javax.persistence.Column(name = "FILE_NUM", nullable = true, insertable = true, updatable = true, length = 200)
    public String getFileNum() {
        return fileNum;
    }

    public void setFileNum(String fileNum) {
        this.fileNum = fileNum;
    }

    private String signCop;

    @Basic
    @javax.persistence.Column(name = "SIGN_COP", nullable = true, insertable = true, updatable = true, length = 200)
    public String getSignCop() {
        return signCop;
    }

    public void setSignCop(String signCop) {
        this.signCop = signCop;
    }

    private String htAttach;

    @Basic
    @javax.persistence.Column(name = "HT_ATTACH", nullable = true, insertable = true, updatable = true, length = 100)
    public String getHtAttach() {
        return htAttach;
    }

    public void setHtAttach(String htAttach) {
        this.htAttach = htAttach;
    }

    private String statusHt;

    @Basic
    @javax.persistence.Column(name = "STATUS_HT", nullable = true, insertable = true, updatable = true, length = 100)
    public String getStatusHt() {
        return statusHt;
    }

    public void setStatusHt(String statusHt) {
        this.statusHt = statusHt;
    }

    private String flag;

    @Basic
    @javax.persistence.Column(name = "FLAG", nullable = true, insertable = true, updatable = true, length = 10)
    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    private Integer autScanFlag;

    @Basic
    @javax.persistence.Column(name = "AUT_SCAN_FLAG", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getAutScanFlag() {
        return autScanFlag;
    }

    public void setAutScanFlag(Integer autScanFlag) {
        this.autScanFlag = autScanFlag;
    }

    private Integer removed;

    @Basic
    @javax.persistence.Column(name = "REMOVED", nullable = true, insertable = true, updatable = true, precision = 0)
    public Integer getRemoved() {
        return removed;
    }

    public void setRemoved(Integer removed) {
        this.removed = removed;
    }

    private String budget;

    @Basic
    @javax.persistence.Column(name = "BUDGET", nullable = true, insertable = true, updatable = true, length = 200)
    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    private String contractType;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    private String transferStatus;

    @Basic
    @javax.persistence.Column(name = "TRANSFER_STATUS", nullable = true, insertable = true, updatable = true, length = 2)
    public String getTransferStatus() {
        return transferStatus;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    private String projectName;

    @Basic
    @javax.persistence.Column(name = "PROJECT_NAME", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    private String projectCompany;

    @Basic
    @javax.persistence.Column(name = "PROJECT_COMPANY", nullable = true, insertable = true, updatable = true, length = 200)
    public String getProjectCompany() {
        return projectCompany;
    }

    public void setProjectCompany(String projectCompany) {
        this.projectCompany = projectCompany;
    }

    private String projectCharge;

    @Basic
    @javax.persistence.Column(name = "PROJECT_CHARGE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectCharge() {
        return projectCharge;
    }

    public void setProjectCharge(String projectCharge) {
        this.projectCharge = projectCharge;
    }

    private String purchaseTypeId;

    @Basic
    @javax.persistence.Column(name = "PURCHASE_TYPE_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getPurchaseTypeId() {
        return purchaseTypeId;
    }

    public void setPurchaseTypeId(String purchaseTypeId) {
        this.purchaseTypeId = purchaseTypeId;
    }

    private String purchaseType2Id;

    @Basic
    @javax.persistence.Column(name = "PURCHASE_TYPE2_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getPurchaseType2Id() {
        return purchaseType2Id;
    }

    public void setPurchaseType2Id(String purchaseType2Id) {
        this.purchaseType2Id = purchaseType2Id;
    }

    private String contractType1Id;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE1_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getContractType1Id() {
        return contractType1Id;
    }

    public void setContractType1Id(String contractType1Id) {
        this.contractType1Id = contractType1Id;
    }

    private String contractType2Id;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE2_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getContractType2Id() {
        return contractType2Id;
    }

    public void setContractType2Id(String contractType2Id) {
        this.contractType2Id = contractType2Id;
    }

    private String contractGroupId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_GROUP_ID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getContractGroupId() {
        return contractGroupId;
    }

    public void setContractGroupId(String contractGroupId) {
        this.contractGroupId = contractGroupId;
    }

    private String projectChargeDept;

    @Basic
    @javax.persistence.Column(name = "PROJECT_CHARGE_DEPT", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectChargeDept() {
        return projectChargeDept;
    }

    public void setProjectChargeDept(String projectChargeDept) {
        this.projectChargeDept = projectChargeDept;
    }

    private String contractStart;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_START", nullable = true, insertable = true, updatable = true, length = 20)
    public String getContractStart() {
        return contractStart;
    }

    public void setContractStart(String contractStart) {
        this.contractStart = contractStart;
    }

    private String contractEnd;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_END", nullable = true, insertable = true, updatable = true, length = 20)
    public String getContractEnd() {
        return contractEnd;
    }

    public void setContractEnd(String contractEnd) {
        this.contractEnd = contractEnd;
    }

    private String projectId;

    @Basic
    @javax.persistence.Column(name = "PROJECT_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    private String contractMoneyTypeId;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_MONEY_TYPE_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getContractMoneyTypeId() {
        return contractMoneyTypeId;
    }

    public void setContractMoneyTypeId(String contractMoneyTypeId) {
        this.contractMoneyTypeId = contractMoneyTypeId;
    }

    private String contractExecute;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_EXECUTE", nullable = true, insertable = true, updatable = true, length = 20)
    public String getContractExecute() {
        return contractExecute;
    }

    public void setContractExecute(String contractExecute) {
        this.contractExecute = contractExecute;
    }

    private String purchaseType;

    @Basic
    @javax.persistence.Column(name = "PURCHASE_TYPE", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType) {
        this.purchaseType = purchaseType;
    }

    private String purchaseType2;

    @Basic
    @javax.persistence.Column(name = "PURCHASE_TYPE2", nullable = true, insertable = true, updatable = true, length = 200)
    public String getPurchaseType2() {
        return purchaseType2;
    }

    public void setPurchaseType2(String purchaseType2) {
        this.purchaseType2 = purchaseType2;
    }

    private String contractType1;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE1", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractType1() {
        return contractType1;
    }

    public void setContractType1(String contractType1) {
        this.contractType1 = contractType1;
    }

    private String contractType2;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_TYPE2", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractType2() {
        return contractType2;
    }

    public void setContractType2(String contractType2) {
        this.contractType2 = contractType2;
    }

    private String contractGroup;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_GROUP", nullable = true, insertable = true, updatable = true, length = 200)
    public String getContractGroup() {
        return contractGroup;
    }

    public void setContractGroup(String contractGroup) {
        this.contractGroup = contractGroup;
    }

    private String contractApprovalType;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_APPROVAL_TYPE", nullable = true, insertable = true, updatable = true, length = 1)
    public String getContractApprovalType() {
        return contractApprovalType;
    }

    public void setContractApprovalType(String contractApprovalType) {
        this.contractApprovalType = contractApprovalType;
    }

    private String signCopId;

    @Basic
    @javax.persistence.Column(name = "SIGN_COP_ID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getSignCopId() {
        return signCopId;
    }

    public void setSignCopId(String signCopId) {
        this.signCopId = signCopId;
    }

    private String contractSpdate;

    @Basic
    @javax.persistence.Column(name = "CONTRACT_SPDATE", nullable = true, insertable = true, updatable = true, length = 50)
    public String getContractSpdate() {
        return contractSpdate;
    }

    public void setContractSpdate(String contractSpdate) {
        this.contractSpdate = contractSpdate;
    }

    private String indexno;

    @Basic
    @javax.persistence.Column(name = "INDEXNO", nullable = true, insertable = true, updatable = true, length = 10)
    public String getIndexno() {
        return indexno;
    }

    public void setIndexno(String indexno) {
        this.indexno = indexno;
    }

    private String typeid;

    @Basic
    @javax.persistence.Column(name = "TYPEID", nullable = true, insertable = true, updatable = true, length = 20)
    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    private String addLoginname;

    @Basic
    @javax.persistence.Column(name = "ADD_LOGINNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAddLoginname() {
        return addLoginname;
    }

    public void setAddLoginname(String addLoginname) {
        this.addLoginname = addLoginname;
    }

    private String addUsername;

    @Basic
    @javax.persistence.Column(name = "ADD_USERNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAddUsername() {
        return addUsername;
    }

    public void setAddUsername(String addUsername) {
        this.addUsername = addUsername;
    }

    private String addDeptid;

    @Basic
    @javax.persistence.Column(name = "ADD_DEPTID", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAddDeptid() {
        return addDeptid;
    }

    public void setAddDeptid(String addDeptid) {
        this.addDeptid = addDeptid;
    }

    private String addDeptname;

    @Basic
    @javax.persistence.Column(name = "ADD_DEPTNAME", nullable = true, insertable = true, updatable = true, length = 50)
    public String getAddDeptname() {
        return addDeptname;
    }

    public void setAddDeptname(String addDeptname) {
        this.addDeptname = addDeptname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HtNew htNew = (HtNew) o;

        if (id != htNew.id) return false;
        if (planNum != null ? !planNum.equals(htNew.planNum) : htNew.planNum != null) return false;
        if (contractName != null ? !contractName.equals(htNew.contractName) : htNew.contractName != null) return false;
        if (contractName1 != null ? !contractName1.equals(htNew.contractName1) : htNew.contractName1 != null)
            return false;
        if (projectCoName != null ? !projectCoName.equals(htNew.projectCoName) : htNew.projectCoName != null)
            return false;
        if (contractNum != null ? !contractNum.equals(htNew.contractNum) : htNew.contractNum != null) return false;
        if (projectNum != null ? !projectNum.equals(htNew.projectNum) : htNew.projectNum != null) return false;
        if (contractMoney != null ? !contractMoney.equals(htNew.contractMoney) : htNew.contractMoney != null)
            return false;
        if (dealPerson != null ? !dealPerson.equals(htNew.dealPerson) : htNew.dealPerson != null) return false;
        if (dealDepSuggest != null ? !dealDepSuggest.equals(htNew.dealDepSuggest) : htNew.dealDepSuggest != null)
            return false;
        if (currentLink != null ? !currentLink.equals(htNew.currentLink) : htNew.currentLink != null) return false;
        if (addPerson != null ? !addPerson.equals(htNew.addPerson) : htNew.addPerson != null) return false;
        if (addTime != null ? !addTime.equals(htNew.addTime) : htNew.addTime != null) return false;
        if (totalDate != null ? !totalDate.equals(htNew.totalDate) : htNew.totalDate != null) return false;
        if (currentSj2 != null ? !currentSj2.equals(htNew.currentSj2) : htNew.currentSj2 != null) return false;
        if (currentSj != null ? !currentSj.equals(htNew.currentSj) : htNew.currentSj != null) return false;
        if (remark != null ? !remark.equals(htNew.remark) : htNew.remark != null) return false;
        if (nextDep != null ? !nextDep.equals(htNew.nextDep) : htNew.nextDep != null) return false;
        if (nextPerson != null ? !nextPerson.equals(htNew.nextPerson) : htNew.nextPerson != null) return false;
        if (contractStage != null ? !contractStage.equals(htNew.contractStage) : htNew.contractStage != null)
            return false;
        if (dealState != null ? !dealState.equals(htNew.dealState) : htNew.dealState != null) return false;
        if (currentPerson != null ? !currentPerson.equals(htNew.currentPerson) : htNew.currentPerson != null)
            return false;
        if (currentDep != null ? !currentDep.equals(htNew.currentDep) : htNew.currentDep != null) return false;
        if (flag1 != null ? !flag1.equals(htNew.flag1) : htNew.flag1 != null) return false;
        if (attr1 != null ? !attr1.equals(htNew.attr1) : htNew.attr1 != null) return false;
        if (attr2 != null ? !attr2.equals(htNew.attr2) : htNew.attr2 != null) return false;
        if (attr3 != null ? !attr3.equals(htNew.attr3) : htNew.attr3 != null) return false;
        if (attr4 != null ? !attr4.equals(htNew.attr4) : htNew.attr4 != null) return false;
        if (attr5 != null ? !attr5.equals(htNew.attr5) : htNew.attr5 != null) return false;
        if (attr6 != null ? !attr6.equals(htNew.attr6) : htNew.attr6 != null) return false;
        if (pinstanceId != null ? !pinstanceId.equals(htNew.pinstanceId) : htNew.pinstanceId != null) return false;
        if (workitemId != null ? !workitemId.equals(htNew.workitemId) : htNew.workitemId != null) return false;
        if (userId != null ? !userId.equals(htNew.userId) : htNew.userId != null) return false;
        if (modelId != null ? !modelId.equals(htNew.modelId) : htNew.modelId != null) return false;
        if (contractMoneyType != null ? !contractMoneyType.equals(htNew.contractMoneyType) : htNew.contractMoneyType != null)
            return false;
        if (flag2 != null ? !flag2.equals(htNew.flag2) : htNew.flag2 != null) return false;
        if (flag3 != null ? !flag3.equals(htNew.flag3) : htNew.flag3 != null) return false;
        if (flag4 != null ? !flag4.equals(htNew.flag4) : htNew.flag4 != null) return false;
        if (taskid != null ? !taskid.equals(htNew.taskid) : htNew.taskid != null) return false;
        if (taskuser != null ? !taskuser.equals(htNew.taskuser) : htNew.taskuser != null) return false;
        if (selfNum != null ? !selfNum.equals(htNew.selfNum) : htNew.selfNum != null) return false;
        if (fileNum != null ? !fileNum.equals(htNew.fileNum) : htNew.fileNum != null) return false;
        if (signCop != null ? !signCop.equals(htNew.signCop) : htNew.signCop != null) return false;
        if (htAttach != null ? !htAttach.equals(htNew.htAttach) : htNew.htAttach != null) return false;
        if (statusHt != null ? !statusHt.equals(htNew.statusHt) : htNew.statusHt != null) return false;
        if (flag != null ? !flag.equals(htNew.flag) : htNew.flag != null) return false;
        if (autScanFlag != null ? !autScanFlag.equals(htNew.autScanFlag) : htNew.autScanFlag != null) return false;
        if (removed != null ? !removed.equals(htNew.removed) : htNew.removed != null) return false;
        if (budget != null ? !budget.equals(htNew.budget) : htNew.budget != null) return false;
        if (contractType != null ? !contractType.equals(htNew.contractType) : htNew.contractType != null) return false;
        if (transferStatus != null ? !transferStatus.equals(htNew.transferStatus) : htNew.transferStatus != null)
            return false;
        if (projectName != null ? !projectName.equals(htNew.projectName) : htNew.projectName != null) return false;
        if (projectCompany != null ? !projectCompany.equals(htNew.projectCompany) : htNew.projectCompany != null)
            return false;
        if (projectCharge != null ? !projectCharge.equals(htNew.projectCharge) : htNew.projectCharge != null)
            return false;
        if (purchaseTypeId != null ? !purchaseTypeId.equals(htNew.purchaseTypeId) : htNew.purchaseTypeId != null)
            return false;
        if (purchaseType2Id != null ? !purchaseType2Id.equals(htNew.purchaseType2Id) : htNew.purchaseType2Id != null)
            return false;
        if (contractType1Id != null ? !contractType1Id.equals(htNew.contractType1Id) : htNew.contractType1Id != null)
            return false;
        if (contractType2Id != null ? !contractType2Id.equals(htNew.contractType2Id) : htNew.contractType2Id != null)
            return false;
        if (contractGroupId != null ? !contractGroupId.equals(htNew.contractGroupId) : htNew.contractGroupId != null)
            return false;
        if (projectChargeDept != null ? !projectChargeDept.equals(htNew.projectChargeDept) : htNew.projectChargeDept != null)
            return false;
        if (contractStart != null ? !contractStart.equals(htNew.contractStart) : htNew.contractStart != null)
            return false;
        if (contractEnd != null ? !contractEnd.equals(htNew.contractEnd) : htNew.contractEnd != null) return false;
        if (projectId != null ? !projectId.equals(htNew.projectId) : htNew.projectId != null) return false;
        if (contractMoneyTypeId != null ? !contractMoneyTypeId.equals(htNew.contractMoneyTypeId) : htNew.contractMoneyTypeId != null)
            return false;
        if (contractExecute != null ? !contractExecute.equals(htNew.contractExecute) : htNew.contractExecute != null)
            return false;
        if (purchaseType != null ? !purchaseType.equals(htNew.purchaseType) : htNew.purchaseType != null) return false;
        if (purchaseType2 != null ? !purchaseType2.equals(htNew.purchaseType2) : htNew.purchaseType2 != null)
            return false;
        if (contractType1 != null ? !contractType1.equals(htNew.contractType1) : htNew.contractType1 != null)
            return false;
        if (contractType2 != null ? !contractType2.equals(htNew.contractType2) : htNew.contractType2 != null)
            return false;
        if (contractGroup != null ? !contractGroup.equals(htNew.contractGroup) : htNew.contractGroup != null)
            return false;
        if (contractApprovalType != null ? !contractApprovalType.equals(htNew.contractApprovalType) : htNew.contractApprovalType != null)
            return false;
        if (signCopId != null ? !signCopId.equals(htNew.signCopId) : htNew.signCopId != null) return false;
        if (contractSpdate != null ? !contractSpdate.equals(htNew.contractSpdate) : htNew.contractSpdate != null)
            return false;
        if (indexno != null ? !indexno.equals(htNew.indexno) : htNew.indexno != null) return false;
        if (typeid != null ? !typeid.equals(htNew.typeid) : htNew.typeid != null) return false;
        if (addLoginname != null ? !addLoginname.equals(htNew.addLoginname) : htNew.addLoginname != null) return false;
        if (addUsername != null ? !addUsername.equals(htNew.addUsername) : htNew.addUsername != null) return false;
        if (addDeptid != null ? !addDeptid.equals(htNew.addDeptid) : htNew.addDeptid != null) return false;
        if (addDeptname != null ? !addDeptname.equals(htNew.addDeptname) : htNew.addDeptname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (planNum != null ? planNum.hashCode() : 0);
        result = 31 * result + (contractName != null ? contractName.hashCode() : 0);
        result = 31 * result + (contractName1 != null ? contractName1.hashCode() : 0);
        result = 31 * result + (projectCoName != null ? projectCoName.hashCode() : 0);
        result = 31 * result + (contractNum != null ? contractNum.hashCode() : 0);
        result = 31 * result + (projectNum != null ? projectNum.hashCode() : 0);
        result = 31 * result + (contractMoney != null ? contractMoney.hashCode() : 0);
        result = 31 * result + (dealPerson != null ? dealPerson.hashCode() : 0);
        result = 31 * result + (dealDepSuggest != null ? dealDepSuggest.hashCode() : 0);
        result = 31 * result + (currentLink != null ? currentLink.hashCode() : 0);
        result = 31 * result + (addPerson != null ? addPerson.hashCode() : 0);
        result = 31 * result + (addTime != null ? addTime.hashCode() : 0);
        result = 31 * result + (totalDate != null ? totalDate.hashCode() : 0);
        result = 31 * result + (currentSj2 != null ? currentSj2.hashCode() : 0);
        result = 31 * result + (currentSj != null ? currentSj.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (nextDep != null ? nextDep.hashCode() : 0);
        result = 31 * result + (nextPerson != null ? nextPerson.hashCode() : 0);
        result = 31 * result + (contractStage != null ? contractStage.hashCode() : 0);
        result = 31 * result + (dealState != null ? dealState.hashCode() : 0);
        result = 31 * result + (currentPerson != null ? currentPerson.hashCode() : 0);
        result = 31 * result + (currentDep != null ? currentDep.hashCode() : 0);
        result = 31 * result + (flag1 != null ? flag1.hashCode() : 0);
        result = 31 * result + (attr1 != null ? attr1.hashCode() : 0);
        result = 31 * result + (attr2 != null ? attr2.hashCode() : 0);
        result = 31 * result + (attr3 != null ? attr3.hashCode() : 0);
        result = 31 * result + (attr4 != null ? attr4.hashCode() : 0);
        result = 31 * result + (attr5 != null ? attr5.hashCode() : 0);
        result = 31 * result + (attr6 != null ? attr6.hashCode() : 0);
        result = 31 * result + (pinstanceId != null ? pinstanceId.hashCode() : 0);
        result = 31 * result + (workitemId != null ? workitemId.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (modelId != null ? modelId.hashCode() : 0);
        result = 31 * result + (contractMoneyType != null ? contractMoneyType.hashCode() : 0);
        result = 31 * result + (flag2 != null ? flag2.hashCode() : 0);
        result = 31 * result + (flag3 != null ? flag3.hashCode() : 0);
        result = 31 * result + (flag4 != null ? flag4.hashCode() : 0);
        result = 31 * result + (taskid != null ? taskid.hashCode() : 0);
        result = 31 * result + (taskuser != null ? taskuser.hashCode() : 0);
        result = 31 * result + (selfNum != null ? selfNum.hashCode() : 0);
        result = 31 * result + (fileNum != null ? fileNum.hashCode() : 0);
        result = 31 * result + (signCop != null ? signCop.hashCode() : 0);
        result = 31 * result + (htAttach != null ? htAttach.hashCode() : 0);
        result = 31 * result + (statusHt != null ? statusHt.hashCode() : 0);
        result = 31 * result + (flag != null ? flag.hashCode() : 0);
        result = 31 * result + (autScanFlag != null ? autScanFlag.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        result = 31 * result + (budget != null ? budget.hashCode() : 0);
        result = 31 * result + (contractType != null ? contractType.hashCode() : 0);
        result = 31 * result + (transferStatus != null ? transferStatus.hashCode() : 0);
        result = 31 * result + (projectName != null ? projectName.hashCode() : 0);
        result = 31 * result + (projectCompany != null ? projectCompany.hashCode() : 0);
        result = 31 * result + (projectCharge != null ? projectCharge.hashCode() : 0);
        result = 31 * result + (purchaseTypeId != null ? purchaseTypeId.hashCode() : 0);
        result = 31 * result + (purchaseType2Id != null ? purchaseType2Id.hashCode() : 0);
        result = 31 * result + (contractType1Id != null ? contractType1Id.hashCode() : 0);
        result = 31 * result + (contractType2Id != null ? contractType2Id.hashCode() : 0);
        result = 31 * result + (contractGroupId != null ? contractGroupId.hashCode() : 0);
        result = 31 * result + (projectChargeDept != null ? projectChargeDept.hashCode() : 0);
        result = 31 * result + (contractStart != null ? contractStart.hashCode() : 0);
        result = 31 * result + (contractEnd != null ? contractEnd.hashCode() : 0);
        result = 31 * result + (projectId != null ? projectId.hashCode() : 0);
        result = 31 * result + (contractMoneyTypeId != null ? contractMoneyTypeId.hashCode() : 0);
        result = 31 * result + (contractExecute != null ? contractExecute.hashCode() : 0);
        result = 31 * result + (purchaseType != null ? purchaseType.hashCode() : 0);
        result = 31 * result + (purchaseType2 != null ? purchaseType2.hashCode() : 0);
        result = 31 * result + (contractType1 != null ? contractType1.hashCode() : 0);
        result = 31 * result + (contractType2 != null ? contractType2.hashCode() : 0);
        result = 31 * result + (contractGroup != null ? contractGroup.hashCode() : 0);
        result = 31 * result + (contractApprovalType != null ? contractApprovalType.hashCode() : 0);
        result = 31 * result + (signCopId != null ? signCopId.hashCode() : 0);
        result = 31 * result + (contractSpdate != null ? contractSpdate.hashCode() : 0);
        result = 31 * result + (indexno != null ? indexno.hashCode() : 0);
        result = 31 * result + (typeid != null ? typeid.hashCode() : 0);
        result = 31 * result + (addLoginname != null ? addLoginname.hashCode() : 0);
        result = 31 * result + (addUsername != null ? addUsername.hashCode() : 0);
        result = 31 * result + (addDeptid != null ? addDeptid.hashCode() : 0);
        result = 31 * result + (addDeptname != null ? addDeptname.hashCode() : 0);
        return result;
    }
}
